/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.componentinfo.cloudsmith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
import org.eclipse.buckminster.jnlp.p2.ui.UiUtils;
import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.OutlineType;
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
	private static final String SRC_HTML_FOLDER = "html";

	private static final String SRC_HTML_IMG_FOLDER = "html" + IPath.SEPARATOR + "img";

	private static final String IMG_FOLDER = "img";
	
	private static final String HTML_TEMPLATE = "componentinfo.page.template.html";

	private static final String HTML_ENCODING = "UTF-8";

	private static final String HTML_DOCTYPE_PUBLIC = "-//W3C//DTD XHTML 1.0 Strict//EN";

	private static final String HTML_DOCTYPE_SYSTEM = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";

	private static final String HTML_BASEPATH_PLACEHOLDER = "#{basePath}";

	private static final String IMG_FOOTER = "footer.bar.2.png";

	private static final String IMG_FAVICON = "favicon.ico";

	private static final String IMG_LOGO = "logotype.2.png";

	private static final String IMG_RSS = "rsslink.gif";

	private static final String PROP_PROVIDER_LOGO_URL = "providerLogo";
	
	private static final String PROP_ARTIFACT_NAME = "artifactName";

	private static final String PROP_CSPEC_NAME = "cspecName";

	private static final String PROP_CSPEC_VERSION_STRING = "cspecVersionString";

	private static final String PROP_BASE_PATH_URL = "basePathURL";

	private static final String PROP_HOME_PAGE_URL = "homePageURL";

	private static final String PROP_CLOUDPAGE_URL = "cloudpageURL";

	private static final String ID_HOME_PAGE_URL = "homeLink";

	private static final String ID_CLOUDPAGE_URL = "cloudpageLink";

	private static final String ID_DISTRO_NAME = "distroNameField";

	private static final String ID_DISTRO_VERSION = "distroVersionField";

	private static final String ID_FEED_TABLE = "feedTable";

	private static final String TAG_TR = "tr";

	private static final String TAG_TD = "td";

	private static final String TAG_A = "a";

	private static final String TAG_IMG = "img";

	private static final String ATTR_HREF = "href";

	private static final String ATTR_VALUE = "value";
	
	private static final String ATTR_SRC = "src";

	private static final String ATTR_CLASS = "class";

	private static final String ATTR_ALT = "alt";

	private static final String ATTR_TITLE = "title";
	
	private static final String CELL_1 = "Cell-1";
	
	private static final String CELL_N = "Cell-n";
	
	private static final String NBSP_VALUE = "\u00A0";

	private static final String CLASS_BLUE_LINK = "BlueLink";

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

		if(!new File(imgDestDir, IMG_FOOTER).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_FOOTER), imgDestDir,
					IMG_FOOTER, m_nullMonitor);
		if(!new File(imgDestDir, IMG_FAVICON).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_FAVICON), imgDestDir,
					IMG_FAVICON, m_nullMonitor);
		if(!new File(imgDestDir, IMG_RSS).exists())
			FileUtils.copyFile(getResource(IPath.SEPARATOR + SRC_HTML_IMG_FOLDER + IPath.SEPARATOR + IMG_RSS), imgDestDir,
					IMG_RSS, m_nullMonitor);
		if(!new File(imgDestDir, IMG_LOGO).exists())
			FileUtils.copyFile(new URL(m_properties.get(PROP_PROVIDER_LOGO_URL)).openStream(), imgDestDir,
					IMG_LOGO, m_nullMonitor);

		return htmlURL;
	}

	private Element addOutline(IOutline outline)
	{
		Element tr = null;
		
		if(outline.getType() == OutlineType.RSS)
		{
			tr = m_xml.createElement(TAG_TR);
			
			Element td = m_xml.createElement(TAG_TD);
			tr.appendChild(td);
			td.setAttribute(ATTR_CLASS, CELL_1);
			td.setTextContent(outline.getText());
			
			td = m_xml.createElement(TAG_TD);
			tr.appendChild(td);
			td.setAttribute(ATTR_CLASS, CELL_N);
			String string = UiUtils.trimmedValue(outline.getDescription());
			td.setTextContent(string == null ? NBSP_VALUE : string);
			
			td = m_xml.createElement(TAG_TD);
			tr.appendChild(td);
			td.setAttribute(ATTR_CLASS, CELL_N);
			Element a = m_xml.createElement(TAG_A);
			td.appendChild(a);
			a.setAttribute(ATTR_HREF, outline.getXmlUrl().toString());
			a.setAttribute(ATTR_CLASS, CLASS_BLUE_LINK);
			a.setAttribute(ATTR_TITLE, outline.getDescription());
			Element rssImg = m_xml.createElement(TAG_IMG);
			a.appendChild(rssImg);
			rssImg.setAttribute(ATTR_ALT, "rss icon");
			rssImg.setAttribute(ATTR_SRC, IMG_FOLDER + IPath.SEPARATOR + IMG_RSS);
			
			td = m_xml.createElement(TAG_TD);
			tr.appendChild(td);
			td.setAttribute(ATTR_CLASS, CELL_N);
			a = m_xml.createElement(TAG_A);
			td.appendChild(a);
			a.setAttribute(ATTR_HREF, outline.getXmlUrl().toString());
			a.setAttribute(ATTR_CLASS, CLASS_BLUE_LINK);
			a.setAttribute(ATTR_TITLE, outline.getDescription());
			Text linkText = m_xml.createTextNode("subscribe");
			a.appendChild(linkText);
		}

		return tr;
	}

	private void addOutlines(Element tableElement, IBody body, int level)
	{
		if(body != null && body.getOutlines() != null)
			for(IOutline outline : body.getOutlines())
			{
				Element childElement = addOutline(outline);

				if(childElement != null)
					tableElement.appendChild(childElement);

				addOutlines(childElement, outline, level + 1);
			}
	}

	private void fillTemplate()
	{
		setAttribute(ID_HOME_PAGE_URL, ATTR_HREF, PROP_HOME_PAGE_URL, false);
		setAttribute(ID_CLOUDPAGE_URL, ATTR_HREF, PROP_CLOUDPAGE_URL, false);
		setAttribute(ID_DISTRO_NAME, ATTR_VALUE, PROP_ARTIFACT_NAME, false);
		setAttribute(ID_DISTRO_VERSION, ATTR_VALUE, PROP_CSPEC_VERSION_STRING, false);

		Element element = m_xml.getElementById(ID_FEED_TABLE);
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
