/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.site;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.ArchiveReferenceModel;
import org.eclipse.update.core.model.CategoryModel;
import org.eclipse.update.core.model.URLEntryModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class SaxableSite extends AbstractSaxableElement implements ISaxable
{
	public static final String TAG = "site";

	public static final String ATTR_ASSOCIATE_SITES_URL = "associateSitesURL";

	public static final String ATTR_MIRRORS_URL = "mirrorsURL";

	private static void addOptionalAttribute(AttributesImpl attrs, String name, String value)
	{
		if(value != null)
			Utils.addAttribute(attrs, name, value);
	}

	private static void writeArchives(ContentHandler handler, ArchiveReferenceModel[] archiveReferenceModels)
			throws SAXException
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
			String label = Trivial.trim(cm.getLabelNonLocalized());
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

	private static void writeFeatures(ContentHandler handler, SiteFeatureReferenceModel[] featureReferenceModels)
			throws SAXException
	{
		int top = featureReferenceModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			SiteFeatureReferenceModel sm = featureReferenceModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			Utils.addAttribute(attrs, "url", sm.getURLString());
			Utils.addAttribute(attrs, "id", sm.getFeatureIdentifier());

			addOptionalAttribute(attrs, "version", sm.getFeatureVersion());
			addOptionalAttribute(attrs, "label", sm.getLabelNonLocalized());
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

	private final Site m_site;

	private final String m_mirrorsURL;

	private final String m_associateSitesURL;

	public SaxableSite(Site site)
	{
		this(site, null, null);
	}

	public SaxableSite(Site site, String mirrorsURL, String associateSitesURL)
	{
		m_site = site;
		m_mirrorsURL = mirrorsURL;
		m_associateSitesURL = associateSitesURL;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		String type = m_site.getType();
		if(type != null)
			Utils.addAttribute(attrs, "type", type);

		String urlStr = m_site.getLocationURLString();
		if(urlStr != null)
			Utils.addAttribute(attrs, "url", urlStr);

		if(m_mirrorsURL != null)
			Utils.addAttribute(attrs, ATTR_MIRRORS_URL, m_mirrorsURL);

		if(m_associateSitesURL != null)
			Utils.addAttribute(attrs, ATTR_ASSOCIATE_SITES_URL, m_associateSitesURL);

		if(m_site instanceof ExtendedSite)
		{
			ExtendedSite extSite = (ExtendedSite)m_site;
			if(extSite.supportsPack200())
				Utils.addAttribute(attrs, "pack200", "true");

			String digestURL = Trivial.trim(extSite.getDigestURL());
			if(digestURL != null)
				Utils.addAttribute(attrs, "digestURL", digestURL);

			String[] availableLocales = extSite.getAvailableLocals();
			if(availableLocales != null)
			{
				StringBuilder bld = new StringBuilder();
				TextUtils.concat(bld, availableLocales, ",");
				if(bld.length() > 0)
					Utils.addAttribute(attrs, "availableLocales", bld.toString());
			}
		}
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		URLEntryModel description = m_site.getDescriptionModel();
		if(description != null)
			writeDescription(handler, description);

		writeFeatures(handler, m_site.getFeatureReferenceModels());
		writeCategories(handler, m_site.getCategoryModels());
		writeArchives(handler, m_site.getArchiveReferenceModels());
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Site getSite()
	{
		return m_site;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, "", "", TAG);
		receiver.endDocument();
	}
}
