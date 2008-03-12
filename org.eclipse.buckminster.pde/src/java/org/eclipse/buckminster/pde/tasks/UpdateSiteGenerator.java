/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.site.SaxableSite;
import org.eclipse.buckminster.core.site.SiteReader;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.CategoryModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.eclipse.update.internal.core.UpdateSiteFeatureReference;
import org.xml.sax.SAXException;

/**
 * <p>This class can perform two tasks.</p>
 * <ul>
 * <li>Create a site.xml style file based on the list of features, a template,
 * and the {@link CSpec} of the current {@link IActionContext}.</li>
 * <li>Calculate the version qualifier of the feature and assign it to a
 * property. The version is fetched from the {@link CSpec} of the current
 * {@link IActionContext} and if it ends with &quot;qualifier&quot; normal
 * qualifier replacement algorithms take place.</li>
 * </ul>
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class UpdateSiteGenerator extends VersionConsolidator
{
	private final List<File> m_features;
	private final IActionContext m_actionContext;
	private final SaxableSite m_saxableSite;

	public UpdateSiteGenerator(List<File> features, File template, File outputFile, File propertiesFile, String qualifier) throws CoreException, IOException
	{
		super(outputFile, propertiesFile, qualifier);
		m_features = features;
		m_actionContext = AbstractActor.getActiveContext();
		if(template != null)
			m_saxableSite = SiteReader.getSite(template, null);
		else
			m_saxableSite = new SaxableSite(new ExtendedSite());
	}

	public IVersion run(boolean generateQualifier) throws CoreException
	{
		OutputStream output = null;
		try
		{
			CSpec cspec = m_actionContext.getCSpec();
			IVersionType osgiType = VersionFactory.OSGiType;

			ArrayList<ComponentIdentifier> deps = null;
			if(generateQualifier)
				deps = new ArrayList<ComponentIdentifier>();

			File outputFile = getOutputFile();
			if(outputFile != null)
				output = new BufferedOutputStream(new FileOutputStream(outputFile));
			else if(!generateQualifier)
				//
				// Nothing left to do
				//
				return null;

			for(File file : m_features)
			{
				String leafName = file.getName();
				if(!leafName.endsWith(".jar"))
					continue;

				JarFile jarFile = null;
				try
				{
					jarFile = new JarFile(file);

					JarEntry entry = jarFile.getJarEntry(IPDEConstants.FEATURE_FILE);
					if(entry == null)
						continue;

					IFeatureModel model = FeatureModelReader.readFeatureModel(jarFile.getInputStream(entry));
					IFeature feature = model.getFeature();
					if(outputFile != null)
						generateFromFeature(cspec, file, feature);

					if(generateQualifier)
						deps.add(new ComponentIdentifier(feature.getId(), IComponentType.ECLIPSE_FEATURE, osgiType.fromString(feature.getVersion())));
				}
				finally
				{
					if(jarFile != null)
						jarFile.close();
				}
			}

			if(outputFile != null)
				Utils.serialize(m_saxableSite, output);

			ComponentIdentifier ci = cspec.getComponentIdentifier();
			return generateQualifier
				? replaceQualifier(cspec.getComponentIdentifier(), deps)
				: ci.getVersion();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	private static boolean categoryExists(CategoryModel[] categories, String categoryName)
	{
		int idx = categories.length;
		while(--idx >= 0)
			if(categories[idx].getName().equals(categoryName))
				return true;
		return false;
	}

	private void generateFromFeature(CSpec cspec, File file, IFeature feature) throws CoreException
	{
		IVersionType osgiType = VersionFactory.OSGiType;
		String featureName = feature.getId();
		StringBuilder urlBuilder = new StringBuilder(IPDEConstants.FEATURES_FOLDER);
		urlBuilder.append('/');
		urlBuilder.append(file.getName());

		// --We look for a category for the feature in two ways.
		//
		// First we check if an entry that matches the feature by name and unqualified version
		// exists in the template. If it does, we use that entry
		//
		String verStr = feature.getVersion();
		if(verStr == null || verStr.length() == 0)
			verStr = "0.0.0";
		SiteFeatureReferenceModel model = null;
		Site site = m_saxableSite.getSite();
		for(SiteFeatureReferenceModel oldModel : site.getFeatureReferenceModels())
		{
			if(featureName.equals(oldModel.getFeatureIdentifier()))
			{
				IVersion oldVer = osgiType.fromString(oldModel.getFeatureVersion());
				IVersion ver = osgiType.fromString(verStr);
				if(ver.equalsUnqualified(oldVer))
				{
					model = oldModel;
					break;
				}
			}
		}

		// When no entry was found, we check the cspec to see if the feature is represented
		// in a group that maps to a category of the feature template
		//
		if(model == null)
		{
			CategoryModel[] categories = site.getCategoryModels();
			Collection<Attribute> attributes = cspec.getAttributes().values();
	
			model = new UpdateSiteFeatureReference();
			model.setFeatureIdentifier(featureName);

			for(Attribute attr : attributes)
			{
				if(!(attr instanceof Group))
					continue;
	
				String categoryName = attr.getName();
				if(!categoryExists(categories, attr.getName()))
					continue;
	
				for(Prerequisite included : attr.getPrerequisites())
				{
					if(!featureName.equals(included.getComponentName()))
						continue;

					model.addCategoryName(categoryName);
					break;
				}
			}
			site.addFeatureReferenceModel(model);
		}
		model.setURLString(urlBuilder.toString());
		model.setFeatureVersion(verStr);
		model.setSiteModel(site);
	}
}
