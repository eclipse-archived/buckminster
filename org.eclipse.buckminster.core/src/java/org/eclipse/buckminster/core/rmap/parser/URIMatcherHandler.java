package org.eclipse.buckminster.core.rmap.parser;

import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.common.parser.RxAssemblyHandler;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.rmap.model.URIMatcher;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.FormatException;
import org.eclipse.equinox.internal.provisional.p2.core.VersionFormat;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@SuppressWarnings("restriction")
public class URIMatcherHandler extends RxAssemblyHandler
{
	public static final String TAG = URIMatcher.TAG;

	private String m_base;

	private VersionFormat m_versionFormat;

	private String m_componentType = IComponentType.UNKNOWN;

	public URIMatcherHandler(AbstractHandler parent)
	{
		super(parent);
		if(parent instanceof ProviderHandler)
		{
			ProviderHandler parentHandler = (ProviderHandler)getParentHandler();
			String[] componentTypes = parentHandler.getComponentTypes();
			if(componentTypes.length == 1)
			{
				m_componentType = componentTypes[0];
			}
		}
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_base = getStringValue(attrs, URIMatcher.ATTR_BASE);
		String tmp = getOptionalStringValue(attrs, URIMatcher.ATTR_VERSION_FORMAT);
		if(tmp != null)
		{
			try
			{
				m_versionFormat = VersionFormat.compile(tmp);
			}
			catch(FormatException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}
		else
		{
			tmp = getOptionalStringValue(attrs, URIMatcher.ATTR_VERSION_TYPE);
			if(tmp == null)
				m_versionFormat = null;
			else
				try
				{
					m_versionFormat = VersionHelper.getVersionType(tmp).getFormat();
				}
				catch(CoreException e)
				{
					throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
				}
		}
	}

	URIMatcher createURIMetaData() throws CoreException, PatternSyntaxException
	{
		return new URIMatcher(getParts(), m_base, m_versionFormat, m_componentType);
	}
}
