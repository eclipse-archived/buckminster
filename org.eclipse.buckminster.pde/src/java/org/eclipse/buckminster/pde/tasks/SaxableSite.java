/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.equinox.internal.p2.updatesite.SiteCategory;
import org.eclipse.equinox.internal.p2.updatesite.SiteFeature;
import org.eclipse.equinox.internal.p2.updatesite.SiteModel;
import org.eclipse.equinox.p2.publisher.eclipse.URLEntry;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings( { "restriction" })
public class SaxableSite extends AbstractSaxableElement implements ISaxable
{
	public static final String TAG = "site"; //$NON-NLS-1$

	public static final String ATTR_ASSOCIATE_SITES_URL = "associateSitesURL"; //$NON-NLS-1$

	public static final String ATTR_MIRRORS_URL = "mirrorsURL"; //$NON-NLS-1$

	private static void addOptionalAttribute(AttributesImpl attrs, String name, String value)
	{
		if(value != null)
			Utils.addAttribute(attrs, name, value);
	}

	private static void writeArchives(ContentHandler handler, URLEntry[] archiveReferenceModels) throws SAXException
	{
		int top = archiveReferenceModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			URLEntry sm = archiveReferenceModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			Utils.addAttribute(attrs, "path", sm.getAnnotation()); //$NON-NLS-1$
			Utils.addAttribute(attrs, "url", sm.getURL()); //$NON-NLS-1$
			handler.startElement("", "", "archive", attrs); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			handler.endElement("", "", "archive"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	private static void writeCategories(ContentHandler handler, SiteCategory[] categoryModels) throws SAXException
	{
		int top = categoryModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			SiteCategory cm = categoryModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			String name = Trivial.trim(cm.getName());
			if(name != null)
				Utils.addAttribute(attrs, "name", name); //$NON-NLS-1$
			String label = Trivial.trim(cm.getLabel());
			if(label != null)
				Utils.addAttribute(attrs, "label", label); //$NON-NLS-1$
			handler.startElement("", "", "category-def", attrs); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			String desc = cm.getDescription();
			if(desc != null)
				writeDescription(handler, new URLEntry(null, desc));
			handler.endElement("", "", "category-def"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	private static void writeDescription(ContentHandler handler, URLEntry urlEntryModel) throws SAXException
	{
		String url = Trivial.trim(urlEntryModel.getURL());
		String text = Trivial.trim(urlEntryModel.getAnnotation());
		if(url != null || text != null)
		{
			Attributes attrs;
			if(url == null)
				attrs = ISaxableElement.EMPTY_ATTRIBUTES;
			else
			{
				attrs = new AttributesImpl();
				Utils.addAttribute((AttributesImpl)attrs, "url", url); //$NON-NLS-1$
			}
			handler.startElement("", "", "description", attrs); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if(text != null)
				handler.characters(text.toCharArray(), 0, text.length());
			handler.endElement("", "", "description"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	private static void writeFeatures(ContentHandler handler, SiteFeature[] featureReferenceModels) throws SAXException
	{
		int top = featureReferenceModels.length;
		for(int idx = 0; idx < top; ++idx)
		{
			SiteFeature sm = featureReferenceModels[idx];
			AttributesImpl attrs = new AttributesImpl();
			Utils.addAttribute(attrs, "url", sm.getURLString()); //$NON-NLS-1$
			Utils.addAttribute(attrs, "id", sm.getFeatureIdentifier()); //$NON-NLS-1$

			addOptionalAttribute(attrs, "version", sm.getFeatureVersion()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "label", sm.getLabelNonLocalized()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "type", sm.getType()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "os", sm.getOS()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "ws", sm.getWS()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "nl", sm.getNL()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "arch", sm.getOSArch()); //$NON-NLS-1$
			addOptionalAttribute(attrs, "patch", sm.getPatch()); //$NON-NLS-1$

			handler.startElement("", "", "feature", attrs); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			String[] categoryNames = sm.getCategoryNames();
			for(int cn = 0; cn < categoryNames.length; cn++)
			{
				AttributesImpl nameAttr = new AttributesImpl();
				Utils.addAttribute(nameAttr, "name", categoryNames[cn]); //$NON-NLS-1$
				handler.startElement("", "", "category", nameAttr); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				handler.endElement("", "", "category"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			handler.endElement("", "", "feature"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	private final SiteModel m_site;

	private final String m_mirrorsURL;

	private final String m_associateSitesURL;

	public SaxableSite(SiteModel site)
	{
		this(site, null, null);
	}

	public SaxableSite(SiteModel site, String mirrorsURL, String associateSitesURL)
	{
		m_site = site;
		m_mirrorsURL = mirrorsURL;
		m_associateSitesURL = associateSitesURL;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public SiteModel getSite()
	{
		return m_site;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, "", "", TAG); //$NON-NLS-1$ //$NON-NLS-2$
		receiver.endDocument();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		String type = m_site.getType();
		if(type != null)
			Utils.addAttribute(attrs, "type", type); //$NON-NLS-1$

		String urlStr = m_site.getLocationURIString();
		if(urlStr != null)
			Utils.addAttribute(attrs, "url", urlStr); //$NON-NLS-1$

		if(m_mirrorsURL != null)
			Utils.addAttribute(attrs, ATTR_MIRRORS_URL, m_mirrorsURL);

		if(m_associateSitesURL != null)
			Utils.addAttribute(attrs, ATTR_ASSOCIATE_SITES_URL, m_associateSitesURL);

		if(m_site.isPack200Supported())
			Utils.addAttribute(attrs, "pack200", "true"); //$NON-NLS-1$ //$NON-NLS-2$

		String digestURL = Trivial.trim(m_site.getDigestURIString());
		if(digestURL != null)
			Utils.addAttribute(attrs, "digestURL", digestURL); //$NON-NLS-1$

		// TODO:
		// String[] availableLocales = m_site.getAvailableLocales();
		// if(availableLocales != null)
		// {
		// StringBuilder bld = new StringBuilder();
		//			TextUtils.concat(bld, availableLocales, ","); //$NON-NLS-1$
		// if(bld.length() > 0)
		//				Utils.addAttribute(attrs, "availableLocales", bld.toString()); //$NON-NLS-1$
		// }
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		URLEntry description = m_site.getDescription();
		if(description != null)
			writeDescription(handler, description);

		writeFeatures(handler, m_site.getFeatures());
		writeCategories(handler, m_site.getCategories());
		writeArchives(handler, m_site.getArchives());
	}
}
