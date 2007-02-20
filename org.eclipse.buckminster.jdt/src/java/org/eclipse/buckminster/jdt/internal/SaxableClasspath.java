package org.eclipse.buckminster.jdt.internal;

import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

@SuppressWarnings("restriction")
public class SaxableClasspath implements ISaxable
{
	private static void emitAccessRule(IAccessRule accessRule, ContentHandler receiver) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ClasspathEntry.TAG_PATTERN, accessRule.getPattern().toString());

		String kindString;
		switch(accessRule.getKind())
		{
		case IAccessRule.K_NON_ACCESSIBLE:
			kindString = ClasspathEntry.TAG_NON_ACCESSIBLE;
			break;
		case IAccessRule.K_DISCOURAGED:
			kindString = ClasspathEntry.TAG_DISCOURAGED;
			break;
		default:
			kindString = ClasspathEntry.TAG_ACCESSIBLE;
		}
		Utils.addAttribute(attrs, ClasspathEntry.TAG_KIND, kindString);
		if(accessRule.ignoreIfBetter())
			Utils.addAttribute(attrs, ClasspathEntry.TAG_IGNORE_IF_BETTER, "true"); //$NON-NLS-1$
		receiver.startElement("", "", ClasspathEntry.TAG_ACCESS_RULE, attrs);
		receiver.endElement("", "", ClasspathEntry.TAG_ACCESS_RULE);
	}

	private static void emitAttribute(IClasspathAttribute attribute, ContentHandler receiver) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ClasspathEntry.TAG_ATTRIBUTE_NAME, attribute.getName());
		Utils.addAttribute(attrs, ClasspathEntry.TAG_ATTRIBUTE_VALUE, attribute.getValue());
		receiver.startElement("", "", ClasspathEntry.TAG_ATTRIBUTE, attrs);
		receiver.endElement("", "", ClasspathEntry.TAG_ATTRIBUTE);
	}

	private static void encodePatterns(IPath[] patterns, String tag, AttributesImpl attrs)
	{
		if(patterns != null)
		{
			int top = patterns.length;
			if(top > 0)
			{
				StringBuilder rule = new StringBuilder(10);
				rule.append(patterns[0]);
				for(int idx = 1; idx < top; ++idx)
				{
					rule.append('|');
					rule.append(patterns[idx]);
				}
				Utils.addAttribute(attrs, tag, rule.toString());
			}
		}
	}

	private static String kindToString(int kind)
	{
		String kindStr;
		switch(kind)
		{
		case IClasspathEntry.CPE_PROJECT:
		case IClasspathEntry.CPE_SOURCE:
			kindStr = "src";
			break;
		case IClasspathEntry.CPE_LIBRARY:
			kindStr = "lib";
			break;
		case IClasspathEntry.CPE_VARIABLE:
			kindStr = "var";
			break;
		case IClasspathEntry.CPE_CONTAINER:
			kindStr = "con";
			break;
		case ClasspathEntry.K_OUTPUT:
			kindStr = "output";
			break;
		default:
			kindStr ="unknown";
		}
		return kindStr;
	}

	private final IClasspathEntry[] m_entries;

	private final IPath m_projectPath;

	public SaxableClasspath(IPath projectPath, IClasspathEntry[] entries)
	{
		m_projectPath = projectPath;
		m_entries = entries;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		receiver.startElement("", "", ClasspathEntry.TAG_CLASSPATH, ISaxableElement.EMPTY_ATTRIBUTES);
		for(IClasspathEntry entry : m_entries)
			emitEntry(entry, receiver);
		receiver.endElement("", "", ClasspathEntry.TAG_CLASSPATH);
		receiver.endDocument();
	}

	private void emitEntry(IClasspathEntry entry, ContentHandler receiver) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		int entryKind = entry.getEntryKind();
		Utils.addAttribute(attrs, ClasspathEntry.TAG_KIND, kindToString(entryKind));

		IPath xmlPath = makeProjectRelative(entry.getPath(), entryKind);
		if(xmlPath != null)
			Utils.addAttribute(attrs, ClasspathEntry.TAG_PATH, xmlPath.toString());

		xmlPath = makeProjectRelative(entry.getSourceAttachmentPath(), entryKind);
		if(xmlPath != null)
			Utils.addAttribute(attrs, ClasspathEntry.TAG_SOURCEPATH, xmlPath.toString());

		xmlPath = entry.getSourceAttachmentRootPath();
		if(xmlPath != null)
			Utils.addAttribute(attrs, ClasspathEntry.TAG_ROOTPATH, xmlPath.toString());

		if(entry.isExported())
			Utils.addAttribute(attrs, ClasspathEntry.TAG_EXPORTED, "true");//$NON-NLS-1$

		encodePatterns(entry.getInclusionPatterns(), ClasspathEntry.TAG_INCLUDING, attrs);
		encodePatterns(entry.getExclusionPatterns(), ClasspathEntry.TAG_EXCLUDING, attrs);

		if(entryKind == IClasspathEntry.CPE_PROJECT && !entry.combineAccessRules())
			Utils.addAttribute(attrs, ClasspathEntry.TAG_COMBINE_ACCESS_RULES, "false"); //$NON-NLS-1$

		IPath outputLocation = entry.getOutputLocation();
		if(outputLocation != null)
		{
			outputLocation = outputLocation.removeFirstSegments(1);
			Utils.addAttribute(attrs, ClasspathEntry.TAG_OUTPUT, String.valueOf(outputLocation));
		}

		receiver.startElement("", "", ClasspathEntry.TAG_CLASSPATHENTRY, attrs);

		IClasspathAttribute[] attributes = entry.getExtraAttributes();
		if(attributes.length > 0)
		{
			receiver.startElement("", "", ClasspathEntry.TAG_ATTRIBUTES, ISaxableElement.EMPTY_ATTRIBUTES);
			for(IClasspathAttribute attribute : attributes)
				emitAttribute(attribute, receiver);
			receiver.endElement("", "", ClasspathEntry.TAG_ATTRIBUTES);
		}

		IAccessRule accessRules[] = entry.getAccessRules();
		if(accessRules.length > 0)
		{
			receiver.startElement("", "", ClasspathEntry.TAG_ACCESS_RULES, ISaxableElement.EMPTY_ATTRIBUTES);
			for(IAccessRule accessRule : accessRules)
				emitAccessRule(accessRule, receiver);
			receiver.endElement("", "", ClasspathEntry.TAG_ACCESS_RULES);
		}
		receiver.endElement("", "", ClasspathEntry.TAG_CLASSPATHENTRY);
	}

	private IPath makeProjectRelative(IPath path, int entryKind)
	{
		if(path == null || !path.isAbsolute())
			return path;

		if(entryKind == IClasspathEntry.CPE_VARIABLE || entryKind == IClasspathEntry.CPE_CONTAINER)
			return path;

		// translate to project relative from absolute (unless a device path)
		//
		if(m_projectPath == null || !m_projectPath.isPrefixOf(path))
			return path;

		return path.removeFirstSegments(m_projectPath.segmentCount()).setDevice(null);
	}
}
