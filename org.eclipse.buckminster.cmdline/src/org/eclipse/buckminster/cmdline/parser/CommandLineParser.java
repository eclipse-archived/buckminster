/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.cmdline.parser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;

/**
 * A simple command line parser that implements a subset of the parsing that is
 * performed by a normal bourne shell. Environment substitution is performed
 * only if the Java runtime is of version 1.5 or higher.
 * 
 * @author Thomas Hallgren
 * @see java.util.Iterator
 */
public class CommandLineParser implements Iterator<String> {
	private static char getEscapedChar(char escaped) {
		switch (escaped) {
			case 't':
				return '\t';
			case 'n':
				return '\n';
			case 'r':
				return '\r';
			default:
				return escaped;
		}
	}

	private final StringBuffer innerBld = new StringBuffer();

	private final StringBuffer outerBld = new StringBuffer();

	private final String line;

	private String nextToken;

	private int pos;

	public CommandLineParser(String line) {
		this.line = line;
		int top = line.length();
		while (pos < top) {
			char c = line.charAt(pos);
			if (Character.isWhitespace(c)) {
				++pos;
				continue;
			}

			// Lines where first non-space character is a '#' are considered
			// to be comments
			//
			if (c == '#')
				pos = top;
			break;
		}
	}

	@Override
	public boolean hasNext() {
		if (nextToken == null)
			nextToken = this.nextToken();
		return nextToken != null;
	}

	@Override
	public String next() {
		if (!this.hasNext())
			throw new NoSuchElementException();
		String nxt = nextToken;
		nextToken = null;
		return nxt;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private void getExpanded(StringBuffer bld, String string) {
		Logger logger = Buckminster.getLogger();
		int top = string.length();
		int idx = 0;
		while (idx < top) {
			char c = string.charAt(idx++);
			if (c != '$') {
				bld.append(c);
				continue;
			}
			if (idx == top)
				break;

			int start;
			int end;
			c = string.charAt(idx);
			if (c == '{') {
				start = ++idx;
				while (idx < top && string.charAt(idx) != '}')
					++idx;
				end = idx++; // Skip trailing '}'
			} else {
				start = idx;
				while (idx < top && Character.isJavaIdentifierPart(string.charAt(idx)))
					++idx;
				end = idx;
			}
			if (end > start) {
				String key = string.substring(start, end);
				String value = (key.length() > 4 && "env:".equalsIgnoreCase(string.substring(0, 4))) //$NON-NLS-1$
						? System.getenv(key.substring(4)) : System.getProperty(key);
				logger.debug("key '%s' expanded to '%s'", key, value); //$NON-NLS-1$
				if (value != null)
					bld.append(value);
			}
		}
	}

	private String getQuoted(char quote) {
		innerBld.setLength(0);
		int top = line.length();
		while (pos < top) {
			char c = line.charAt(pos++);
			if (c == quote)
				break;

			if (c == '\\') {
				if (pos == top)
					break;
				c = getEscapedChar(line.charAt(pos++));
			}
			innerBld.append(c);
		}
		return innerBld.toString();
	}

	private String getSpaceDelimited() {
		innerBld.setLength(0);
		int top = line.length();
		while (pos < top) {
			char c = line.charAt(pos);
			if (Character.isWhitespace(c) || c == '\'' || c == '"')
				break;

			++pos;
			if (c == '\\') {
				if (pos == top)
					break;

				// The sequence '\ ' should not cause a break since that
				// is an escaped space. The sequence '\t' however, should
				// since that is an unescaped tab
				//
				c = line.charAt(pos++);
				if (!Character.isWhitespace(c)) {
					c = getEscapedChar(c);
					if (Character.isWhitespace(c))
						break;
				}
			}
			innerBld.append(c);
		}
		return innerBld.toString();
	}

	private String nextToken() {
		outerBld.setLength(0);
		int top = line.length();
		if (pos == top)
			return null;

		while (pos < top) {
			char c = line.charAt(pos);
			switch (c) {
				case '\'':
					// Find matching end quote. No expansion is performed
					//
					++pos;
					outerBld.append(getQuoted('\''));
					continue;
				case '"':
					// Find matching end quote and perform expansion
					//
					++pos;
					getExpanded(outerBld, getQuoted('"'));
					continue;

				default:
					if (Character.isWhitespace(c)) {
						++pos;
						while (pos < top && Character.isWhitespace(line.charAt(pos)))
							++pos;
						break;
					}
					getExpanded(outerBld, getSpaceDelimited());
					continue;
			}
			break;
		}
		return outerBld.toString();
	}
}
