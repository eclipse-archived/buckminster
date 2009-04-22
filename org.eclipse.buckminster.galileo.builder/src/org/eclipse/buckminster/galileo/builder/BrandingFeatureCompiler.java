package org.eclipse.buckminster.galileo.builder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.amalgam.releng.build.BuildFactory;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.amalgam.releng.build.Repository;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.IGlobalContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.commands.Build;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Locator;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.mapprovider.PDEMapProvider;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.xml.sax.SAXException;

@SuppressWarnings("restriction")
public class BrandingFeatureCompiler extends BuilderPhase {

	private final Feature brandingFeature;

	private final ComponentIdentifier brandingCI;

	protected BrandingFeatureCompiler(Builder builder, Feature brandingFeature) {
		super(builder);
		this.brandingFeature = brandingFeature;
		brandingCI = new ComponentIdentifier(brandingFeature.getId(), IComponentType.ECLIPSE_FEATURE, Version.parseVersion(brandingFeature
				.getVersion()));
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		if (brandingFeature == null)
			return;

		MonitorUtils.begin(monitor, 100);
		populateWorkspace(MonitorUtils.subMonitor(monitor, 80));
		buildSite(MonitorUtils.subMonitor(monitor, 20));
	}

	private void buildSite(IProgressMonitor monitor) throws CoreException {
		Logger log = Buckminster.getLogger();
		log.info("Building update site containing %s", brandingFeature);
		long now = System.currentTimeMillis();

		MonitorUtils.begin(monitor, 100);
		try {
			List<String> errors = null;
			for (IMarker problem : Build.build(MonitorUtils.subMonitor(monitor, 18), true)) {
				switch (problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO)) {
					case IMarker.SEVERITY_ERROR:
						String error = Build.formatMarkerMessage("Error", problem); //$NON-NLS-1$
						log.error(error);
						if (errors == null)
							errors = new ArrayList<String>();
						errors.add(error);
						break;
					case IMarker.SEVERITY_WARNING:
						log.warning(Build.formatMarkerMessage("Warning", problem)); //$NON-NLS-1$
						break;
					case IMarker.SEVERITY_INFO:
						log.info(Build.formatMarkerMessage("Info", problem)); //$NON-NLS-1$
				}
			}
			if (errors != null) {
				getBuilder().sendEmail(null, errors);
				throw BuckminsterException.fromMessage("Build failed");
			}
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}

		CSpec cspec = WorkspaceInfo.getResolution(brandingCI).getCSpec();
		Attribute attr = cspec.getRequiredAttribute(IPDEConstants.ATTRIBUTE_SITE_P2);
		IPerformManager pm = CorePlugin.getPerformManager();
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(KeyConstants.ACTION_OUTPUT_ROOT, new File(getBuilder().getBuildRoot(), "branding").toString());
		props.put("qualifier.replacement.*", "generator:lastModified");
		props.put("generator.lastModified.format", "'v'yyyyMMdd-HHmm");
		props.put("site.pack200", "true");
		props.put("site.include.top", "true");

		IGlobalContext ctx = pm.perform(cspec, attr.getName(), props, false, true, MonitorUtils.subMonitor(monitor, 70));

		// If everything went OK, we should not be able to access the repository
		Map<String, ? extends Object> siteBuildProps = ctx.getExecutionProperties(attr);
		IPath buildOutput = Path.fromOSString(ExpandingProperties.expand(siteBuildProps, IPDEConstants.OUTPUT_DIR_SITE_P2.toOSString(), 0));
		URI brandingRepo = buildOutput.toFile().toURI();
		Buckminster bucky = Buckminster.getDefault();
		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		try {
			IMetadataRepository mdr = mdrMgr.loadRepository(brandingRepo, MonitorUtils.subMonitor(monitor, 2));
			IInstallableUnit bfIU = Builder.getIU(mdr, cspec.getName() + Builder.FEATURE_GROUP_SUFFIX, null);
			if (bfIU == null)
				throw BuckminsterException.fromMessage("No %s feature was generated during workspace build", brandingCI.getName());
			brandingFeature.setVersion(bfIU.getVersion().toString());
			Repository repo = BuildFactory.eINSTANCE.createRepository();
			repo.setLocation(brandingRepo.toString());
			repo.setLabel(getBuilder().getBuild().getLabel() + " branding feature repository");
			brandingFeature.setRepo(repo);

			URI globalLocationURI = getBuilder().getGlobalRepoURI();
			((CompositeMetadataRepository) mdrMgr.loadRepository(globalLocationURI, MonitorUtils.subMonitor(monitor, 5))).addChild(brandingRepo);
			((CompositeArtifactRepository) arMgr.loadRepository(globalLocationURI, MonitorUtils.subMonitor(monitor, 5))).addChild(brandingRepo);

		} finally {
			bucky.ungetService(mdrMgr);
		}

		log.info("Done building update site. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
	}

	private ComponentQuery createQuery() throws CoreException {
		ComponentQueryBuilder bld = new ComponentQueryBuilder();
		Version v = brandingCI.getVersion();
		VersionRange vr = Version.emptyVersion.equals(v) ? null : new VersionRange(v, true, v, true);
		bld.setRootRequest(new ComponentRequest(brandingCI.getName(), brandingCI.getComponentTypeID(), vr));
		bld.setPlatformAgnostic(true);
		bld.setResourceMapURL(createResourceMap().toURI().toString());
		return bld.createComponentQuery();
	}

	private File createResourceMap() throws CoreException {
		try {
			File rmapFile = new File(getBuilder().getBuildRoot(), "buckminster.rmap");
			if (rmapFile.exists())
				return rmapFile;

			File mapFolder = new File(getBuilder().getBuildModelLocation().getParent(), "maps");
			ResourceMap rmap = new ResourceMap(null);
			SearchPath searchPath = new SearchPath(rmap, "builder.map");
			searchPath.addProvider(new PDEMapProvider(searchPath, "url.catalog", new String[] { IComponentType.ECLIPSE_FEATURE,
					IComponentType.OSGI_BUNDLE }, null, new Format(mapFolder.toURI().toString()), null, false, true, null));
			rmap.addSearchPath(searchPath);

			searchPath = new SearchPath(rmap, "contributions");
			searchPath.addProvider(new Provider(searchPath, "eclipse.import", new String[] { IComponentType.ECLIPSE_FEATURE,
					IComponentType.OSGI_BUNDLE }, null, new Format(getBuilder().getGlobalRepoURI().toString()), null, null, null, false, false, null,
					null));
			rmap.addSearchPath(searchPath);

			rmap.addMatcher(new Locator(rmap, null, "builder.map", false));
			rmap.addMatcher(new Locator(rmap, null, "contributions", false));

			OutputStream out = new BufferedOutputStream(new FileOutputStream(rmapFile));
			try {
				Utils.serialize(rmap, out);
			} finally {
				IOUtils.close(out);
			}
			return rmapFile;
		} catch (SAXException e) {
			throw BuckminsterException.wrap(e);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	private void populateWorkspace(IProgressMonitor monitor) throws CoreException {
		Logger log = Buckminster.getLogger();
		log.info("Populating workspace");
		MonitorUtils.begin(monitor, 100);
		long now = System.currentTimeMillis();

		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IWorkspaceDescription wsDesc = ws.getDescription();
		wsDesc.setAutoBuilding(false);
		wsDesc.setSnapshotInterval(Long.MAX_VALUE);
		ws.setDescription(wsDesc);
		ws.save(true, MonitorUtils.subMonitor(monitor, 5));

		ComponentQuery query = createQuery();
		ResolutionContext ctx = new ResolutionContext(query);
		IResolver resolver = new MainResolver(ctx);
		BillOfMaterials bom = resolver.resolve(MonitorUtils.subMonitor(monitor, 95));
		MaterializationSpecBuilder mspecBld = new MaterializationSpecBuilder();
		mspecBld.setName(bom.getViewName());
		mspecBld.setMaterializerID(IMaterializer.WORKSPACE);
		bom.addMaterializationNodes(mspecBld);
		MaterializationContext matCtx = new MaterializationContext(bom, mspecBld.createMaterializationSpec());
		MaterializationJob.run(matCtx, true);
		MonitorUtils.done(monitor);
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
	}
}
