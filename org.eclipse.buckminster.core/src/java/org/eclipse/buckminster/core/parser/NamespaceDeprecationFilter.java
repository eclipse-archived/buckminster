package org.eclipse.buckminster.core.parser;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceDeprecationFilter extends XMLFilterImpl
{
	private final String m_oldNamespace;

	private final String m_newNamespace;

	private final String m_systemID;

	public NamespaceDeprecationFilter(String systemID, String oldNamespace, String newNamespace)
	{
		m_oldNamespace = oldNamespace;
		m_newNamespace = newNamespace;
		m_systemID = systemID;
	}

	@Override
	public void startPrefixMapping(String prefix, String namespace) throws SAXException
	{
		if(m_oldNamespace.equals(namespace))
		{
			CorePlugin.getLogger().warning(
					NLS.bind(Messages.XML_namespace_0_deprecated_Use_1_instead_2,
							new Object[] { m_oldNamespace, m_newNamespace, m_systemID }));
			namespace = m_newNamespace;
		}
		super.startPrefixMapping(prefix, namespace);
	}
}
