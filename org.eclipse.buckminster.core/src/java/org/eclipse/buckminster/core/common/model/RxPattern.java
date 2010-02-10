/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.List;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class RxPattern extends RxPart {
	public static final String TAG = "match"; //$NON-NLS-1$

	public static final String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	public static final String ATTR_PREFIX = "prefix"; //$NON-NLS-1$

	public static final String ATTR_SUFFIX = "suffix"; //$NON-NLS-1$

	private static void addEscapedPattern(StringBuilder bld, String pattern, boolean willBeGroup) throws CoreException {
		// No capturing groups permitted. All groups must therefore be converted
		// into non capturing groups
		//
		int orDepth = -1;
		int startPos = bld.length();
		int parenDepth = 0;
		boolean inCharGroup = false;
		boolean stripOuter = false;

		int top = pattern.length();
		for (int idx = 0; idx < top;) {
			char c = pattern.charAt(idx++);
			switch (c) {
				case '\\':
					// Next is escaped
					//
					bld.append(c);
					if (idx < top)
						c = pattern.charAt(idx++);
					break;
				case '|':
					if (orDepth == -1 || orDepth > parenDepth)
						orDepth = parenDepth;
					break;
				case '[':
					inCharGroup = true;
					break;
				case ']':
					inCharGroup = false;
					break;
				case '(':
					if (inCharGroup)
						break;

					++parenDepth;
					if (idx == top)
						break;

					if (pattern.charAt(idx) != '?') {
						// If the pattern starts with a group and this group
						// contains the whole pattern
						// then it should be stripped off
						//
						if (idx == 1)
							stripOuter = true;
						bld.append("(?"); //$NON-NLS-1$
						c = ':';
					} else {
						if (idx == 1 && top > 2)
							stripOuter = (pattern.charAt(2) == ':');
					}
					break;
				case ')':
					if (inCharGroup)
						break;

					parenDepth--;
					if (parenDepth < 0)
						break;

					if (parenDepth == 0 && idx < top)
						stripOuter = false;
					break;
			}
			bld.append(c);
		}

		if (parenDepth != 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Unbalanced_parenthesis_in_pattern_0, pattern));

		if (stripOuter) {
			if (willBeGroup || orDepth != 1) {
				int tpos = startPos;
				int fpos = startPos + 3; // We strip '(?:'
				int epos = bld.length() - 1; // and ')'
				while (fpos < epos)
					bld.setCharAt(tpos++, bld.charAt(fpos++));
				bld.setLength(tpos);
			}
		} else if (!willBeGroup && orDepth == 0) {
			// A group must be added to limit what's affected by the
			// OR expression
			//
			String subExpr = bld.substring(startPos, bld.length());
			bld.setLength(startPos);
			bld.append("(?:"); //$NON-NLS-1$
			bld.append(subExpr);
			bld.append(')');
		}
	}

	private static void addQuotedString(StringBuilder bld, String str) throws CoreException {
		int top = str.length();
		for (int idx = 0; idx < top; ++idx) {
			char c = str.charAt(idx);
			switch (c) {
				case '\\':
				case '(':
				case ')':
				case '[':
				case ']':
				case '{':
				case '}':
				case '.':
				case '?':
				case '+':
				case '*':
				case '|':
				case '^':
				case '$':
					bld.append('\\');
			}
			bld.append(c);
		}
	}

	private final String pattern;

	private final String prefix;

	private final String suffix;

	public RxPattern(String name, boolean optional, String pattern, String prefix, String suffix) {
		super(name, optional);
		this.pattern = pattern;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	@Override
	public void addPattern(StringBuilder bld, List<RxPart> namedParts) throws CoreException {
		if (!isOptional()) {
			addInnerPattern(bld, namedParts, false);
			return;
		}

		// Everything must be in a group that is marked as optional
		//
		bld.append('(');
		if (prefix == null && suffix == null) {
			String name = getName();
			if (name == null)
				bld.append("?:"); // Non capturing group //$NON-NLS-1$
			else
				namedParts.add(this);
			addEscapedPattern(bld, pattern, true);
		} else {
			// Group as a whole must be a non capturing group
			//
			bld.append("?:"); //$NON-NLS-1$
			addInnerPattern(bld, namedParts, true);
		}
		bld.append(")?"); //$NON-NLS-1$
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public String getPattern() {
		return pattern;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_PATTERN, pattern);
		if (prefix != null)
			Utils.addAttribute(attrs, ATTR_PREFIX, prefix);
		if (suffix != null)
			Utils.addAttribute(attrs, ATTR_SUFFIX, suffix);
	}

	private void addInnerPattern(StringBuilder bld, List<RxPart> namedParts, boolean willBeGroup) throws CoreException {
		if (prefix != null)
			addQuotedString(bld, prefix);

		String name = getName();
		if (name != null) {
			// Pattern must be a capturing group
			//
			bld.append('(');
			addEscapedPattern(bld, pattern, true);
			bld.append(')');
			namedParts.add(this);
		} else
			addEscapedPattern(bld, pattern, willBeGroup && prefix == null && suffix == null);

		if (suffix != null)
			addQuotedString(bld, suffix);
	}
}
