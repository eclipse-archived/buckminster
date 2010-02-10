/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.rmap.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class BidirectionalTransformer extends AbstractSaxableElement {
	public static final String TAG = "transform"; //$NON-NLS-1$

	public static final String ATTR_TO_PATTERN = "toPattern"; //$NON-NLS-1$

	public static final String ATTR_TO_REPLACEMENT = "toReplacement"; //$NON-NLS-1$

	public static final String ATTR_FROM_PATTERN = "fromPattern"; //$NON-NLS-1$

	public static final String ATTR_FROM_REPLACEMENT = "fromReplacement"; //$NON-NLS-1$

	private static String replace(String source, Pattern pattern, String replacement) {
		Matcher matcher = pattern.matcher(source);
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

	private final Pattern toPattern;

	private final String toReplacement;

	private final Pattern fromPattern;

	private final String fromReplacement;

	public BidirectionalTransformer(Pattern toPattern, String toReplacement, Pattern fromPattern, String fromReplacement) {
		this.toPattern = toPattern;
		this.toReplacement = toReplacement;
		this.fromPattern = fromPattern;
		this.fromReplacement = fromReplacement;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public final Pattern getFromPattern() {
		return fromPattern;
	}

	public final String getFromReplacement() {
		return fromReplacement;
	}

	public final Pattern getToPattern() {
		return toPattern;
	}

	public final String getToReplacement() {
		return toReplacement;
	}

	public String transformFrom(String source) throws TransformerMismatchException {
		return transform(source, fromPattern, fromReplacement, toPattern, toReplacement);
	}

	public String transformTo(String source) throws TransformerMismatchException {
		return transform(source, toPattern, toReplacement, fromPattern, fromReplacement);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_TO_PATTERN, toPattern.toString());
		Utils.addAttribute(attrs, ATTR_TO_REPLACEMENT, toReplacement);
		Utils.addAttribute(attrs, ATTR_FROM_PATTERN, fromPattern.toString());
		Utils.addAttribute(attrs, ATTR_FROM_REPLACEMENT, fromReplacement);
	}

	private String transform(String source, Pattern pattern, String replacement, Pattern reversePattern, String reverseReplacement)
			throws TransformerMismatchException {
		String result = replace(source, pattern, replacement);
		if (result == null)
			return null;

		String reverse = replace(result, reversePattern, reverseReplacement);
		if (reverse == null)
			//
			// Matches only one direction. Don't replace then.
			//
			return null;

		// This pattern was possible to replace in both directions. The result
		// should
		// be the same in that case.
		//
		if (source.equals(reverse))
			return result;

		throw new TransformerMismatchException(this);
	}
}
