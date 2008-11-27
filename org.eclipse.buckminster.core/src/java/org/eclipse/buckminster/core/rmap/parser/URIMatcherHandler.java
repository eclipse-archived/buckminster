package org.eclipse.buckminster.core.rmap.parser;

import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.parser.RxAssemblyHandler;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.rmap.model.URIMatcher;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class URIMatcherHandler extends RxAssemblyHandler
{
	public static final String TAG = URIMatcher.TAG;

	private String m_base;

	private IVersionType m_versionType;

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

	URIMatcher createURIMetaData() throws CoreException, PatternSyntaxException
	{
		return new URIMatcher(getParts(), m_base, m_versionType, m_componentType);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_base = getStringValue(attrs, URIMatcher.ATTR_BASE);
		String tmp = getOptionalStringValue(attrs, URIMatcher.ATTR_VERSION_TYPE);
		if(tmp == null)
			m_versionType = null;
		else
			try
			{
				m_versionType = CorePlugin.getDefault().getVersionType(tmp);
			}
			catch(CoreException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
	}
}
