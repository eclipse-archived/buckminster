/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.IGlobalContext;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.resolver.ResourceMapResolver;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Generator extends NamedElement implements IGenerator {
	public static final String ATTR_ATTRIBUTE = "attribute"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT = "component"; //$NON-NLS-1$

	public static final String ATTR_GENERATES = "generates"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_TYPE = "generatesType"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_VERSION = "generatesVersion"; //$NON-NLS-1$

	public static final String TAG = "generator"; //$NON-NLS-1$

	private final CSpec cspec;

	private final String attribute;

	private final String component;

	private final String generatesType;

	private final Version generatesVersion;

	public Generator(CSpec cspec, String component, String attribute, ComponentIdentifier generates) {
		super(generates);
		this.cspec = cspec;
		this.component = component;
		this.attribute = attribute;
		this.generatesType = generates.getType();
		this.generatesVersion = generates.getVersion();
	}

	@Override
	public String getAttribute() {
		return attribute;
	}

	@Override
	public String getComponent() {
		return component;
	}

	public CSpec getCSpec() {
		return cspec;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public ComponentIdentifier getGeneratedIdentifier() {
		ComponentIdentifier ci = CommonFactory.eINSTANCE.createComponentIdentifier();
		ci.setId(getName());
		ci.setType(generatesType);
		ci.setVersion(generatesVersion);
		return ci;
	}

	public String getGenerates() {
		return getName();
	}

	@Override
	public String getNameAttributeName() {
		return ATTR_GENERATES;
	}

	public void registerGeneratedResolution(PathGroup[] result, IGlobalContext ctx, IProgressMonitor monitor) throws CoreException {
		// Register the resolution from this generator
		if (result.length != 1)
			return;

		PathGroup pg = result[0];
		HashMap<String, Long> fileNames = new HashMap<String, Long>();
		pg.appendRelativeFiles(fileNames);
		if (fileNames.isEmpty())
			return;

		Set<String> paths = fileNames.keySet();
		String readerType = IReaderType.LOCAL;
		String uri;
		boolean isFile = false;
		IPath componentLocation;
		if (paths.size() == 1) {
			IPath path = Path.fromOSString(paths.iterator().next());
			String suffix = path.getFileExtension();
			if ("zip".equals(suffix) || "jar".equals(suffix)) //$NON-NLS-1$ //$NON-NLS-2$
			{
				readerType = IReaderType.URL_ZIPPED;
				isFile = true;
			}
			componentLocation = pg.getBase().append(path);
		} else
			componentLocation = pg.getBase();
		try {
			uri = URLUtils.normalizeToURL(componentLocation.toOSString()).toString();
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}

		ComponentIdentifier ci = getGeneratedIdentifier();
		String cType = ci.getType();
		RmapFactory factory = RmapFactory.eINSTANCE;
		ResourceMap rmap = factory.createResourceMap();
		SearchPath searchPath = factory.createSearchPath();
		searchPath.setName("default"); //$NON-NLS-1$
		rmap.getSearchPaths().add(searchPath);
		Provider provider = ResourceMapResolver.immutableProvider(readerType, cType, uri, null);
		searchPath.getProviders().add(provider);
		Map<String, String> props = provider.getProperties();
		props.put(KeyConstants.IS_MUTABLE, "false"); //$NON-NLS-1$
		props.put(KeyConstants.IS_SOURCE, "false"); //$NON-NLS-1$

		Locator locator = factory.createLocator();
		locator.setSearchPath(searchPath);
		rmap.getMatchers().add(locator);

		ComponentQueryBuilder query = new ComponentQueryBuilder();
		ComponentRequest cr = CommonFactory.eINSTANCE.createComponentRequest();
		cr.setId(ci.getId());
		cr.setType(ci.getType());
		query.setRootRequest(cr);
		ResolutionContext rctx = new ResolutionContext(query.createComponentQuery());
		BOMNode node = ResourceMapResolver.resolve(rmap, rctx.getRootNodeQuery(), monitor);
		Resolution res = node.getResolution();
		if (res != null) {
			if (isFile)
				componentLocation = componentLocation.removeLastSegments(1);
			ctx.addGeneratedResolution(res, componentLocation);
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_ATTRIBUTE, attribute);
		if (component != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, component);
		Utils.addAttribute(attrs, ATTR_GENERATES, getName());

		if (generatesType != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_TYPE, generatesType);
		if (generatesVersion != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_VERSION, generatesVersion.toString());
	}
}
