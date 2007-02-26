/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.europatools.model.Category;
import org.eclipse.buckminster.europatools.model.Feature;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteContentProvider;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.ArchiveReferenceModel;
import org.eclipse.update.core.model.CategoryModel;
import org.eclipse.update.core.model.DefaultSiteParser;
import org.eclipse.update.core.model.URLEntryModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.eclipse.update.internal.core.ExtendedSiteURLFactory;
import org.eclipse.update.internal.core.SiteFileContentProvider;
import org.eclipse.update.internal.core.UpdateSiteFeatureReference;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class SiteGenerator extends AbstractGenerator
{
	private Site m_site;

	public SiteGenerator(String topProject, File generatedDir)
	{
		super(topProject, generatedDir);
	}

	@Override
	public void generate(SiteContribution sc) throws CoreException
	{
		Site site = getSite();

		// The feature entries are just dummies. They will be
		// replaced when the merge with the actual site takes
		// place
		//
		List<Feature> features = sc.getFeatures();
		for(SiteFeatureReferenceModel model : site.getFeatureReferenceModels())
		{
			int idx = features.size();
			while(--idx >= 0)
			{
				if(model.getFeatureIdentifier().equals(features.get(idx).getName()))
					site.removeFeatureReferenceModel(model);
			}
		}

		StringBuilder urlBuilder = new StringBuilder("features/");
		for(Feature feature : features)
		{
			SiteFeatureReferenceModel model = new UpdateSiteFeatureReference();
			String name = feature.getName();
			urlBuilder.setLength(9);
			urlBuilder.append(name);
			model.setFeatureIdentifier(name);
			IVersionDesignator vd = feature.getVersionDesignator();
			if(vd != null)
			{
				String version = vd.getVersion().toString();
				urlBuilder.append('_');
				urlBuilder.append(version);
				model.setFeatureVersion(version);
			}
			urlBuilder.append(".jar");
			String category = feature.getCategory();
			if(category != null)
				model.addCategoryName(feature.getCategory());
			model.setURLString(urlBuilder.toString());
			model.setSiteModel(site);
			site.addFeatureReferenceModel(model);
		}

		List<Category> categories = sc.getCategories();
		for(CategoryModel model : site.getCategoryModels())
		{
			int idx = categories.size();
			while(--idx >= 0)
				if(model.getName().equals(categories.get(idx).getName()))
					site.removeCategoryModel(model);
		}

		for(Category category : categories)
		{
			CategoryModel model = new CategoryModel();
			model.setName(category.getName());
			model.setLabel(category.getLabel());
			
			Documentation doc = category.getDocumentation();
			if(doc != null)
			{
				URLEntryModel description = new URLEntryModel();
				description.setAnnotation(doc.toString());
				model.setDescriptionModel(description);
			}
			site.addCategoryModel(model);
		}
	}

	@Override
	protected File getArtifactFile()
	{
		return new File(getWorkingDir(), getTopProject() + ".site");
	}

	@Override
	protected ISaxable getGeneratedArtifact() throws CoreException
	{
		return new ISaxable()
		{
			public void toSax(ContentHandler receiver) throws SAXException
			{
				receiver.startDocument();
				writeSite(receiver, m_site);
				receiver.endDocument();
			}	
		};
	}

	private Site getSite() throws CoreException
	{
		if(m_site == null)
		{
			try
			{
				InputStream input = null;
				File siteFile = getArtifactFile();
				URL url = siteFile.toURI().toURL();
				SiteContentProvider contentProvider = new SiteFileContentProvider(url);
				try
				{
					input = new BufferedInputStream(new FileInputStream(siteFile));
	
					ExtendedSiteURLFactory factory = new ExtendedSiteURLFactory();
					DefaultSiteParser parser = new DefaultSiteParser();
					parser.init(factory);
					m_site = (Site)parser.parse(input);
					IStatus status = parser.getStatus();
					if(status != null)
						throw new CoreException(status);
				}
				catch(FileNotFoundException e)
				{
					m_site = new ExtendedSite();
				}
				finally
				{
					IOUtils.close(input);
				}
				m_site.setSiteContentProvider(contentProvider);
				contentProvider.setSite(m_site);
				m_site.resolve(url, url);
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		return m_site;
	}

	private static void writeSite(ContentHandler handler, Site site) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		String type = site.getType();
		if(type != null)
			Utils.addAttribute(attrs, "type", type);

		String urlStr = site.getLocationURLString();
		if(urlStr != null)
			Utils.addAttribute(attrs, "url", urlStr);

		if(site instanceof ExtendedSite)
		{
			ExtendedSite extSite = (ExtendedSite)site;
			if(extSite.supportsPack200())
				Utils.addAttribute(attrs, "pack200", "true");
			
			String digestURL = Trivial.trim(extSite.getDigestURL());
			if(digestURL != null)
				Utils.addAttribute(attrs, "digestURL", digestURL);

			String[] availableLocales = extSite.getAvailableLocals();
			if(availableLocales != null)
			{
				StringBuilder bld = new StringBuilder();
				TextUtils.buildCommaSeparatedList(bld, availableLocales);
				if(bld.length() > 0)
					Utils.addAttribute(attrs, "availableLocales", bld.toString());
			}
		}
		handler.startElement("", "", "site", attrs);
		URLEntryModel description = site.getDescriptionModel();
		if(description != null)
			writeDescription(handler, description);
		
		writeFeatures(handler, site.getFeatureReferenceModels());
		writeCategories(handler, site.getCategoryModels());
		writeArchives(handler, site.getArchiveReferenceModels());
		handler.endElement("", "", "site");
	}

	private static void writeArchives(ContentHandler handler, ArchiveReferenceModel[] archiveReferenceModels) throws SAXException
	{
		int top = archiveReferenceModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			ArchiveReferenceModel sm = archiveReferenceModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			Utils.addAttribute(attrs, "path", sm.getPath());
			Utils.addAttribute(attrs, "url", sm.getURLString());
			handler.startElement("", "", "archive", attrs);
			handler.endElement("", "", "archive");
		}
	}

	private static void addOptionalAttribute(AttributesImpl attrs, String name, String value)
	{
		if(value != null)
			Utils.addAttribute(attrs, name, value);
	}

	private static void writeFeatures(ContentHandler handler, SiteFeatureReferenceModel[] featureReferenceModels) throws SAXException
	{
		int top = featureReferenceModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			SiteFeatureReferenceModel sm = featureReferenceModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			Utils.addAttribute(attrs, "url", "features/"
					+ sm.getFeatureIdentifier() + "_"
					+ sm.getFeatureVersion() + ".jar");
			Utils.addAttribute(attrs, "id", sm.getFeatureIdentifier());
			Utils.addAttribute(attrs, "version", sm.getFeatureVersion());

			addOptionalAttribute(attrs, "label", sm.getLabel());
			addOptionalAttribute(attrs, "type", sm.getType());
			addOptionalAttribute(attrs, "os", sm.getOS());
			addOptionalAttribute(attrs, "ws", sm.getWS());
			addOptionalAttribute(attrs, "nl", sm.getNL());
			addOptionalAttribute(attrs, "arch", sm.getOSArch());
			addOptionalAttribute(attrs, "patch", sm.getPatch());

			handler.startElement("", "", "feature", attrs);
			String[] categoryNames = sm.getCategoryNames();
			for(int cn = 0; cn < categoryNames.length; cn++)
			{
				AttributesImpl nameAttr = new AttributesImpl();
				Utils.addAttribute(nameAttr, "name", categoryNames[cn]);
				handler.startElement("", "", "category", nameAttr);
				handler.endElement("", "", "category");
			}
			handler.endElement("", "", "feature");
		}
	}

	private static void writeCategories(ContentHandler handler, CategoryModel[] categoryModels) throws SAXException
	{
		int top = categoryModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			CategoryModel cm = categoryModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			String name = Trivial.trim(cm.getName());
			if(name != null)
				Utils.addAttribute(attrs, "name", name);
			String label = Trivial.trim(cm.getLabel());
			if(label != null)
				Utils.addAttribute(attrs, "label", label);
			handler.startElement("", "", "category-def", attrs);
			URLEntryModel desc = cm.getDescriptionModel();
			if(desc != null)
				writeDescription(handler, desc);
			handler.endElement("", "", "category-def");
		}
	}

	private static void writeDescription(ContentHandler handler, URLEntryModel urlEntryModel) throws SAXException
	{
		String url = Trivial.trim(urlEntryModel.getURLString());
		String text = Trivial.trim(urlEntryModel.getAnnotationNonLocalized());
		if(url != null || text != null)
		{
			Attributes attrs;
			if(url == null)
				attrs = ISaxableElement.EMPTY_ATTRIBUTES;
			else
			{
				attrs = new AttributesImpl();
				Utils.addAttribute((AttributesImpl)attrs, "url", url);
			}
			handler.startElement("", "", "description", attrs);
			if(text != null)
				handler.characters(text.toCharArray(), 0, text.length());
			handler.endElement("", "", "description");
		}
	}
}
