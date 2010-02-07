package org.eclipse.buckminster.core.rmap.parser;

import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.common.parser.RxAssemblyHandler;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.rmap.model.URIMatcher;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionFormatException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class URIMatcherHandler extends RxAssemblyHandler {
	public static final String TAG = URIMatcher.TAG;

	private String base;

	private IVersionFormat versionFormat;

	private String componentType = IComponentType.UNKNOWN;

	public URIMatcherHandler(AbstractHandler parent) {
		super(parent);
		if (parent instanceof ProviderHandler) {
			ProviderHandler parentHandler = (ProviderHandler) getParentHandler();
			String[] componentTypes = parentHandler.getComponentTypes();
			if (componentTypes.length == 1) {
				componentType = componentTypes[0];
			}
		}
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		base = getStringValue(attrs, URIMatcher.ATTR_BASE);
		String tmp = getOptionalStringValue(attrs, URIMatcher.ATTR_VERSION_FORMAT);
		if (tmp != null) {
			try {
				versionFormat = Version.compile(tmp);
			} catch (VersionFormatException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		} else {
			tmp = getOptionalStringValue(attrs, URIMatcher.ATTR_VERSION_TYPE);
			if (tmp == null)
				versionFormat = null;
			else
				try {
					versionFormat = VersionHelper.getVersionType(tmp).getFormat();
				} catch (CoreException e) {
					throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
				}
		}
	}

	URIMatcher createURIMetaData() throws CoreException, PatternSyntaxException {
		return new URIMatcher(getParts(), base, versionFormat, componentType);
	}
}
