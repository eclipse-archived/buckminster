/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The Replace class will perform a regular expression based match and replace
 * on its source. The pattern can be quoted in order to make the replace
 * function as a normal (non expression based) replace.
 * 
 * @author Thomas Hallgren
 */
public class Replace extends ValueHolderFilter {
	public static final class Match extends AbstractSaxableElement {
		private final Pattern pattern;

		private final String patternString;

		private final String replacement;

		private final boolean quotePattern;

		/**
		 * Create a Replace.Match that will replace every subsequence of the
		 * value provided by a <code>valueProvider</code> that matches the
		 * <code>pattern</code> with the given <code>replacement</code> string.
		 * 
		 * @param pattern
		 *            The pattern that defines the replacement
		 * @param replacement
		 *            The replacement string
		 * @param quotePattern
		 *            Set to true if the pattern should be quoted.
		 * @see java.util.regex.Matcher#replaceAll(String)
		 * @see java.util.regex.Pattern#quote(String)
		 */
		public Match(String pattern, String replacement, boolean quotePattern) {
			this.patternString = pattern;
			this.quotePattern = quotePattern;
			this.replacement = replacement;
			if (quotePattern)
				pattern = Pattern.quote(pattern);
			this.pattern = Pattern.compile(pattern);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;

			if (!(o instanceof Match))
				return false;

			return quotePattern == ((Match) o).quotePattern && patternString.equals(((Match) o).patternString)
					&& replacement.equals(((Match) o).replacement);
		}

		@Override
		public String getDefaultTag() {
			return MATCH_TAG;
		}

		@Override
		public int hashCode() {
			int hc = patternString.hashCode();
			hc = 37 * hc + replacement.hashCode();
			hc = 37 * hc + (quotePattern ? 17 : 0);
			return hc;
		}

		@Override
		protected void addAttributes(AttributesImpl attrs) throws SAXException {
			Utils.addAttribute(attrs, ATTR_PATTERN, patternString);
			Utils.addAttribute(attrs, ATTR_REPLACEMENT, replacement);
			if (quotePattern)
				Utils.addAttribute(attrs, ATTR_QUOTE_PATTERN, "true"); //$NON-NLS-1$
		}

		String match(String resolved) {
			Matcher matcher = pattern.matcher(resolved);
			if (matcher.find()) {
				StringBuffer sb = new StringBuffer();
				do {
					matcher.appendReplacement(sb, replacement);
				} while (matcher.find());
				matcher.appendTail(sb);
				return sb.toString();
			}
			return null;
		}
	}

	public static final String TAG = "replace"; //$NON-NLS-1$

	public static final String MATCH_TAG = "match"; //$NON-NLS-1$

	public static final String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	public static final String ATTR_REPLACEMENT = "replacement"; //$NON-NLS-1$

	public static final String ATTR_QUOTE_PATTERN = "quotePattern"; //$NON-NLS-1$

	private final ArrayList<Match> matchers = new ArrayList<Match>();

	public void addMatch(Match match) {
		matchers.add(match);
	}

	@Override
	public String checkedGetValue(Map<String, ? extends Object> props, int recursionGuard) {
		String resolved = this.checkedGetSourceValue(props, recursionGuard);
		return (resolved == null || NO_VALUE.equals(resolved)) ? NO_VALUE : replace(resolved);
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && matchers.equals(((Replace) o).matchers);
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc = 37 * hc + matchers.hashCode();
		return hc;
	}

	public String replace(String value) {
		for (Match match : matchers) {
			String result = match.match(value);
			if (result != null)
				return result;
		}
		return value;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		// If we have exactly one, then use attributes instead of
		// subelements
		//
		if (matchers.size() == 1)
			matchers.get(0).addAttributes(attrs);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		super.emitElements(handler, namespace, prefix);
		int top = matchers.size();

		// If there's only one it will be added as an attribute.
		//
		if (top > 1) {
			for (int idx = 0; idx < top; ++idx) {
				Match match = matchers.get(idx);
				match.toSax(handler, namespace, prefix, match.getDefaultTag());
			}
		}
	}
}
