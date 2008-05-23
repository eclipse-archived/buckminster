/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cloudsmith.componentinfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.jnlp.componentinfo.IComponentInfoProvider;
import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author Karel Brezina
 * 
 */
public class ComponentInfoProvider implements IComponentInfoProvider
{
	public static final String SRC_HTML_FOLDER = "html";

	public static final String SRC_HTML_IMG_FOLDER = "html" + IPath.SEPARATOR + "img";

	public static final String IMG_FOLDER = "img";
	
	public static final String HTML_TEMPLATE = "componentinfo.page.template.html";

	public static final String HTML_ENCODING = "UTF-8";

	public static final String HTML_DOCTYPE_PUBLIC = "-//W3C//DTD XHTML 1.0 Strict//EN";

	public static final String HTML_DOCTYPE_SYSTEM = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";

	public static final String HTML_BASEPATH_PLACEHOLDER = "#{basePath}";

	public static final String IMG_ARROW = "arrow.blue2.png";

	public static final String IMG_FOOTER = "footer.bar.2.png";

	public static final String IMG_EMPTY_PROFILE = "avatar.fpo.png";

	public static final String IMG_FAVICON = "favicon.ico";

	public static final String IMG_LOGO = "logotype.2.png";

	public static final String IMG_RSS = "rsslink.gif";

	public static final String GIF_CONTENT_TYPE = "image/gif";

	public static final String GIF_FILE_EXTENSION = "gif";

	public static final String PNG_CONTENT_TYPE = "image/png";

	public static final String PNG_FILE_EXTENSION = "png";

	public static final String JPG_CONTENT_TYPE = "image/jpg";

	public static final String JPG_FILE_EXTENSION = "jpg";

	public static final String PROP_PROVIDER_LOGO_URL = "providerLogo";
	
	public static final String PROP_PROFILE_IMAGE_ID = "profileImageID";

	public static final String PROP_PROFILE_IMAGE_URL = "profileImageURL";

	public static final String PROP_PROFILE_IMAGE_FILE_NAME = "profileImageFileName";

	public static final String PROP_PUBLISHER_NAME = "publisherName";

	public static final String PROP_PROFILE_TEXT = "profileText";

	public static final String PROP_MORE_INFO_URL = "moreInfoURL";

	public static final String PROP_ARTIFACT_NAME = "artifactName";

	public static final String PROP_ARTIFACT_VERSION = "artifactVersion";

	public static final String PROP_ARTIFACT_DESCRIPTION = "artifactDescription";

	public static final String PROP_ARTIFACT_DOCUMENTATION = "artifactDocumentation";

	public static final String PROP_CSPEC_NAME = "cspecName";

	public static final String PROP_CSPEC_VERSION_STRING = "cspecVersionString";

	public static final String PROP_BASE_PATH_URL = "basePathURL";

	public static final String PROP_HOME_PAGE_URL = "homePageURL";

	public static final String PROP_CLOUDPAGE_URL = "cloudpageURL";

	public static final String ID_HOME_PAGE_URL = "homeLink";

	public static final String ID_CLOUDPAGE_URL = "cloudpageLink";

	public static final String ID_DISTRO_NAME = "distroNameField";

	public static final String ID_DISTRO_VERSION = "distroVersionField";

	public static final String ID_DISTRO_DESC = "distroComponentDescField";

	public static final String ID_DISTRO_DOC = "distroComponentDocField";

	public static final String ID_PUBLISHER_IMG = "publisherImage";

	public static final String ID_PUBLISHER_NAME = "publisherName";

	public static final String ID_PUBLISHER_INFO = "publisherInfo";

	public static final String ID_PUBLISHER_LINK = "publisherURL";

	public static final String ID_DISTRO_INFO = "distroInfo";

	public static final String TAG_UL = "ul";

	public static final String TAG_LI = "li";

	public static final String TAG_SPAN = "span";

	public static final String TAG_A = "a";

	public static final String TAG_IMG = "img";

	public static final String ATTR_HREF = "href";

	public static final String ATTR_VALUE = "value";

	public static final String ATTR_SRC = "src";

	public static final String ATTR_CLASS = "class";

	public static final String ATTR_ALT = "alt";

	public static final String ATTR_TITLE = "title";

	public static final String CLASS_BLUE_LINK = "BlueLink";

	private static InputStream getResource(String resourcePath)
	{
		return ComponentInfoProvider.class.getResourceAsStream(resourcePath);
	}

	private final IProgressMonitor m_nullMonitor = new NullProgressMonitor();

	private Document m_xml;

	private Map<String, String> m_properties;

	private IOPML m_opml;

	public String prepareHTML(Map<String, String> properties, IOPML opml, String destination) throws Exception
	{
		if(opml == null)
			return null;
		if(properties == null)
			throw new IllegalArgumentException("Properties are not set");
		if(destination == null)
			throw new IllegalArgumentException("Target destination is not set");

		m_properties = properties;
		m_opml = opml;

		String htmlURL = null;

		InputStream is = getResource(IPath.SEPARATOR + SRC_HTML_FOLDER + IPath.SEPARATOR + HTML_TEMPLATE);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		FileUtils.copyFile(is, bos, m_nullMonitor);
		String string = bos.toString(HTML_ENCODING);

		String basePath = m_properties.get(PROP_BASE_PATH_URL);
		if(basePath == null)
			throw new Exception("Missing required property '" + PROP_BASE_PATH_URL + '\'');

		string = string.replaceAll(Pattern.quote(HTML_BASEPATH_PLACEHOLDER), basePath);

		m_xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new ByteArrayInputStream(string.getBytes(HTML_ENCODING)));

		String profileImgName = null;
		m_properties.put(PROP_PROFILE_IMAGE_FILE_NAME, IMG_FOLDER + IPath.SEPARATOR + IMG_EMPTY_PROFILE);

		String imageID = m_properties.get(PROP_PROFILE_IMAGE_ID);
		if(imageID != null)
		{
			URLConnection connection = new URL(m_properties.get(PROP_PROFILE_IMAGE_URL)).openConnection();
			String contentType = connection.getContentType();
			String fileExt = null;

			if(contentType != null)
			{
				if(contentType.contains(GIF_CONTENT_TYPE))
					fileExt = GIF_FILE_EXTENSION;
				else if(contentType.contains(PNG_CONTENT_TYPE))
					fileExt = PNG_FILE_EXTENSION;
				else if(contentType.contains(JPG_CONTENT_TYPE))
					fileExt = JPG_FILE_EXTENSION;
			}

			if(fileExt != null)
			{
				profileImgName = "profile" + imageID + "." + fileExt;
				m_properties.put(PROP_PROFILE_IMAGE_FILE_NAME, IMG_FOLDER + IPath.SEPARATOR + profileImgName);
			}
		}

		fillTemplate();

		File htmlDestDir = new File(destination);

		if(!(htmlDestDir.exists() && htmlDestDir.isDirectory()))
			FileUtils.createDirectory(htmlDestDir, m_nullMonitor);

		File imgDestDir = new File(htmlDestDir, "img");

		if(!(imgDestDir.exists() && imgDestDir.isDirectory()))
			FileUtils.createDirectory(imgDestDir, m_nullMonitor);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(stream);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, HTML_ENCODING);
		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, HTML_DOCTYPE_PUBLIC);
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, HTML_DOCTYPE_SYSTEM);
		transformer.transform(new DOMSource(m_xml), result);

		String htmlFileName = "distro." + m_properties.get(PROP_CSPEC_NAME) + "."
				+ m_properties.get(PROP_CSPEC_VERSION_STRING) + ".html";
		htmlFileName = htmlFileName.replaceAll("(?:/|\\\\|:|\\*|\\?|\"|<|>|\\|)", "_");

		htmlURL = destination + File.separator + htmlFileName;

		FileUtils.copyFile(new ByteArrayInputStream(stream.toByteArray()), htmlDestDir, htmlFileName, m_nullMonitor);

		if(!new File(imgDestDir, IMG_ARROW).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_ARROW), imgDestDir,
					IMG_ARROW, m_nullMonitor);
		if(!new File(imgDestDir, IMG_FOOTER).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_FOOTER), imgDestDir,
					IMG_FOOTER, m_nullMonitor);
		if(!new File(imgDestDir, IMG_EMPTY_PROFILE).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_EMPTY_PROFILE),
					imgDestDir, IMG_EMPTY_PROFILE, m_nullMonitor);
		if(!new File(imgDestDir, IMG_FAVICON).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_FAVICON), imgDestDir,
					IMG_FAVICON, m_nullMonitor);
		if(!new File(imgDestDir, IMG_RSS).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_RSS), imgDestDir,
					IMG_RSS, m_nullMonitor);
		if(!new File(imgDestDir, IMG_LOGO).exists())
			FileUtils.copyFile(new URL(m_properties.get(PROP_PROVIDER_LOGO_URL)).openStream(), imgDestDir,
					IMG_LOGO, m_nullMonitor);
		if(profileImgName != null)
			if(!new File(imgDestDir, profileImgName).exists())
				FileUtils.copyFile(new URL(m_properties.get(PROP_PROFILE_IMAGE_URL)).openStream(), imgDestDir,
						profileImgName, m_nullMonitor);

		return htmlURL;
	}

	private Element addOutline(IOutline outline)
	{
		Element childElement = null;

		switch(outline.getType())
		{
		case RSS:
			childElement = m_xml.createElement(TAG_A);
			childElement.setAttribute(ATTR_HREF, outline.getXmlUrl().toString());
			childElement.setAttribute(ATTR_CLASS, CLASS_BLUE_LINK);
			childElement.setAttribute(ATTR_TITLE, outline.getTitle());
			Element rssImg = m_xml.createElement(TAG_IMG);
			rssImg.setAttribute(ATTR_ALT, "rss icon");
			rssImg.setAttribute(ATTR_SRC, IMG_FOLDER + IPath.SEPARATOR + IMG_RSS);
			Text linkText = m_xml.createTextNode(" " + outline.getText());
			childElement.appendChild(rssImg);
			childElement.appendChild(linkText);
			break;
		case LINK:
			childElement = m_xml.createElement(TAG_A);
			childElement.setAttribute(ATTR_HREF, outline.getUrl().toString());
			childElement.setAttribute(ATTR_CLASS, CLASS_BLUE_LINK);
			childElement.setAttribute(ATTR_TITLE, outline.getTitle());
			childElement.setTextContent(outline.getText());
			break;
		case TEXT:
			childElement = m_xml.createElement(TAG_SPAN);
			childElement.setAttribute(ATTR_TITLE, outline.getTitle());
			childElement.setTextContent(outline.getText());
			break;
		}

		if(childElement == null)
			return null;

		Element li = m_xml.createElement(TAG_LI);
		li.appendChild(childElement);

		return li;
	}

	private void addOutlines(Element element, IBody body, int level)
	{
		Element ul = null;

		if(body != null && body.getOutlines() != null)
			for(IOutline outline : body.getOutlines())
			{
				Element childElement = addOutline(outline);

				if(childElement == null)
					continue;

				if(ul == null)
				{
					ul = m_xml.createElement(TAG_UL);
					element.appendChild(ul);
				}

				ul.appendChild(childElement);
				addOutlines(childElement, outline, level + 1);
			}
	}

	private void fillTemplate()
	{
		setAttribute(ID_HOME_PAGE_URL, ATTR_HREF, PROP_HOME_PAGE_URL, false);
		setAttribute(ID_CLOUDPAGE_URL, ATTR_HREF, PROP_CLOUDPAGE_URL, false);
		setAttribute(ID_DISTRO_NAME, ATTR_VALUE, PROP_ARTIFACT_NAME, false);
		setAttribute(ID_DISTRO_VERSION, ATTR_VALUE, PROP_ARTIFACT_VERSION, false);
		setAttribute(ID_DISTRO_DESC, ATTR_VALUE, PROP_ARTIFACT_DESCRIPTION, true);

		// textarea tag needs a closing tag - cannot generalize
		// setAttribute(ID_DISTRO_DOC, null, PROP_ARTIFACT_DOCUMENTATION);
		Element element = m_xml.getElementById(ID_DISTRO_DOC);
		if(element != null)
		{
			String doc = m_properties.get(PROP_ARTIFACT_DOCUMENTATION);
			if(doc == null || doc.length() == 0)
			{
				doc = " ";
			}
			else
			{
				try
				{
					doc = new String(Base64.decodeBase64(doc.getBytes()), "UTF-8");
				}
				catch(UnsupportedEncodingException e)
				{
					doc = " ";
					e.printStackTrace();
				}
			}
			element.setTextContent(doc);
		}

		setAttribute(ID_PUBLISHER_NAME, null, PROP_PUBLISHER_NAME, false);
		setAttribute(ID_PUBLISHER_INFO, null, PROP_PROFILE_TEXT, false);
		setAttribute(ID_PUBLISHER_LINK, ATTR_HREF, PROP_MORE_INFO_URL, false);
		setAttribute(ID_PUBLISHER_LINK, null, PROP_MORE_INFO_URL, false);
		setAttribute(ID_PUBLISHER_IMG, ATTR_SRC, PROP_PROFILE_IMAGE_FILE_NAME, false);

		element = m_xml.getElementById(ID_DISTRO_INFO);
		if(element != null)
			if(m_opml != null)
				addOutlines(element, m_opml.getBody(), 0);
	}

	private void setAttribute(String nodeId, String attibuteName, String propertyName, boolean base64)
	{
		Element element = m_xml.getElementById(nodeId);
		if(element != null)
		{
			String value = m_properties.get(propertyName);
			if(base64)
				try
				{
					if(value != null && value.length() > 0)
						value = new String(Base64.decodeBase64(value.getBytes()), "UTF-8");
				}
				catch(UnsupportedEncodingException e)
				{
					value = null;
					e.printStackTrace();
				}

			if(attibuteName == null)
				element.setTextContent(value);
			else
				element.setAttribute(attibuteName, value);
		}
	}
}
