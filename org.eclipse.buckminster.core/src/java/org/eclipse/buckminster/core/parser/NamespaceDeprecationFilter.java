package org.eclipse.buckminster.core.parser;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceDeprecationFilter extends XMLFilterImpl {
	private final String oldNamespace;

	private final String newNamespace;

	private final String systemID;

	public NamespaceDeprecationFilter(String systemID, String oldNamespace, String newNamespace) {
		this.oldNamespace = oldNamespace;
		this.newNamespace = newNamespace;
		this.systemID = systemID;
	}

	@Override
	public void startPrefixMapping(String prefix, String namespace) throws SAXException {
		if (oldNamespace.equals(namespace)) {
			CorePlugin.getLogger().warning(
					NLS.bind(Messages.XML_namespace_0_deprecated_Use_1_instead_2, new Object[] { oldNamespace, newNamespace, systemID }));
			namespace = newNamespace;
		}
		super.startPrefixMapping(prefix, namespace);
	}
}
