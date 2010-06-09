/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.InvalidSyntaxException;

/**
 * Parser class for OSGi filter strings. This class parses the complete filter
 * string and builds a tree of Filter objects rooted at the parent.
 */
public class Parser {
	private static class Compacter {
		private FilterImpl base;

		private List<FilterImpl> parts;

		private int op;

		Compacter(FilterImpl base, int op) {
			this.base = base;
			this.op = op;
		}

		FilterImpl getResultingFilter() {
			if (parts == null)
				return base;

			int partsOp = op == FilterImpl.AND ? FilterImpl.OR : FilterImpl.AND;
			return base.addFilter(normalize(parts, partsOp), op);
		}

		boolean merge(FilterImpl b) {
			FilterImpl[] aArr;
			FilterImpl[] bArr;
			if (base.getOp() == op)
				aArr = base.getFilterImpls();
			else
				aArr = new FilterImpl[] { base };

			if (b.getOp() == op)
				bArr = b.getFilterImpls();
			else
				bArr = new FilterImpl[] { b };

			List<FilterImpl> common = null;
			List<FilterImpl> onlyA = null;

			int atop = aArr.length;
			int btop = bArr.length;
			int aidx;
			int bidx;
			for (aidx = 0; aidx < atop; ++aidx) {
				FilterImpl af = aArr[aidx];
				for (bidx = 0; bidx < btop; ++bidx) {
					FilterImpl bf = bArr[bidx];
					if (af.equals(bf)) {
						if (common == null)
							common = new ArrayList<FilterImpl>();
						common.add(af);
						break;
					}
				}
				if (bidx == btop) {
					if (onlyA == null)
						onlyA = new ArrayList<FilterImpl>();
					onlyA.add(af);
				}
			}
			if (common == null)
				// Nothing in common
				return false;

			if (onlyA == null && parts == null)
				return true;

			List<FilterImpl> onlyB = null;
			for (bidx = 0; bidx < btop; ++bidx) {
				FilterImpl bf = bArr[bidx];
				for (aidx = 0; aidx < atop; ++aidx)
					if (bf.equals(aArr[aidx]))
						break;
				if (aidx == atop) {
					if (onlyB == null)
						onlyB = new ArrayList<FilterImpl>();
					onlyB.add(bf);
				}
			}

			if (onlyB == null && parts == null) {
				// All of B is already covered by base
				base = b;
				return true;
			}

			if (parts == null)
				parts = new ArrayList<FilterImpl>();

			if (onlyA != null) {
				base = normalize(common, op);
				FilterImpl af = normalize(onlyA, op);
				if (!parts.contains(af))
					parts.add(af);
			}
			FilterImpl bf = normalize(onlyB, op);
			if (!parts.contains(bf))
				parts.add(bf);
			return true;
		}
	}

	public static Filter parse(String filterString) throws InvalidSyntaxException {
		return new Parser(filterString).internalParse();
	}

	static FilterImpl normalize(List<FilterImpl> operands, int op) {
		int top = operands.size();
		if (top == 1)
			return operands.get(0);

		// a | (b | c) becomes a | b | c
		// a & (b & c) becomes a & b & c
		//
		for (int idx = 0; idx < top; ++idx) {
			FilterImpl f = operands.get(idx);
			if (f.getOp() != op)
				continue;

			FilterImpl[] sfs = f.getFilterImpls();
			operands.remove(idx);
			--top;
			for (int ndx = 0; ndx < sfs.length; ++ndx) {
				FilterImpl nf = sfs[ndx];
				if (!operands.contains(nf))
					operands.add(nf);
			}
		}
		if (top == 1)
			return operands.get(0);

		Collections.sort(operands);
		List<Compacter> splits = new ArrayList<Compacter>();
		int reverseOp = op == FilterImpl.AND ? FilterImpl.OR : FilterImpl.AND;

		for (int idx = 0; idx < top; ++idx)
			merge(splits, operands.get(idx), reverseOp);

		operands.clear();
		top = splits.size();
		for (int idx = 0; idx < top; ++idx) {
			FilterImpl filter = splits.get(idx).getResultingFilter();
			if (!operands.contains(filter))
				operands.add(filter);
		}
		top = operands.size();
		if (top == 1)
			return operands.get(0);

		Collections.sort(operands);
		return new AndOrFilterImpl(op, operands.toArray(new FilterImpl[top]));
	}

	private static void merge(List<Compacter> splits, FilterImpl filterImpl, int op) {
		int top = splits.size();
		for (int idx = 0; idx < top; ++idx) {
			Compacter split = splits.get(idx);
			if (split.merge(filterImpl))
				return;
		}
		splits.add(new Compacter(filterImpl, op));
	}

	private final String filterString;

	private final StringBuilder sb = new StringBuilder();

	private int pos;

	private Parser(String filter) {
		this.filterString = filter;
		this.pos = 0;
	}

	private Filter internalParse() throws InvalidSyntaxException {
		try {
			FilterImpl filter = parse_filter();
			if (pos != filterString.length())
				throw syntaxException(Messages.FILTER_TRAILING_CHARACTERS);
			return filter;
		} catch (StringIndexOutOfBoundsException e) {
			throw new InvalidSyntaxException(NLS.bind(Messages.FILTER_PREMATURE_END, filterString), filterString);
		}
	}

	private FilterImpl parse_and() throws InvalidSyntaxException {
		skipWhiteSpace();
		char c = filterString.charAt(pos);
		if (c != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);

		ArrayList<FilterImpl> operands = new ArrayList<FilterImpl>();
		while (c == '(') {
			FilterImpl child = parse_filter();
			if (!operands.contains(child))
				operands.add(child);
			c = filterString.charAt(pos);
		}
		return normalize(operands, FilterImpl.AND);
	}

	private String parse_attr() throws InvalidSyntaxException {
		skipWhiteSpace();

		int begin = pos;
		int end = pos;

		char c = filterString.charAt(begin);
		while (!(c == '~' || c == '<' || c == '>' || c == '=' || c == '(' || c == ')')) {
			pos++;
			if (!Character.isWhitespace(c))
				end = pos;
			c = filterString.charAt(pos);
		}
		if (end == begin)
			throw syntaxException(Messages.FILTER_MISSING_ATTR);
		return filterString.substring(begin, end);
	}

	private FilterImpl parse_filter() throws InvalidSyntaxException {
		FilterImpl filter;
		skipWhiteSpace();

		if (filterString.charAt(pos) != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);

		pos++;
		filter = parse_filtercomp();

		skipWhiteSpace();

		if (filterString.charAt(pos) != ')')
			throw syntaxException(Messages.FILTER_MISSING_RIGHTPAREN);

		pos++;
		skipWhiteSpace();

		return filter;
	}

	private FilterImpl parse_filtercomp() throws InvalidSyntaxException {
		skipWhiteSpace();

		char c = filterString.charAt(pos);

		switch (c) {
			case '&': {
				pos++;
				return parse_and();
			}
			case '|': {
				pos++;
				return parse_or();
			}
			case '!': {
				pos++;
				return parse_not();
			}
		}
		return parse_item();
	}

	private FilterImpl parse_item() throws InvalidSyntaxException {
		String attr = parse_attr();

		skipWhiteSpace();

		char c2 = filterString.charAt(pos + 1);
		switch (filterString.charAt(pos)) {
			case '~':
				if (c2 == '=') {
					pos += 2;
					return new StringFilterImpl(FilterImpl.APPROX, attr, parse_value());
				}
				break;
			case '>':
				if (c2 == '=') {
					pos += 2;
					return new StringFilterImpl(FilterImpl.GREATER, attr, parse_value());
				}
				break;
			case '<':
				if (c2 == '=') {
					pos += 2;
					return new StringFilterImpl(FilterImpl.LESS, attr, parse_value());
				}
				break;
			case '=':
				if (c2 == '*') {
					int oldpos = pos;
					pos += 2;
					skipWhiteSpace();
					if (filterString.charAt(pos) == ')')
						return new PresentFilterImpl(attr);
					pos = oldpos;
				}

				pos++;
				Object string = parse_substring();

				return (string instanceof String) ? new StringFilterImpl(FilterImpl.EQUAL, attr, (String) string) : new SubstringFilterImpl(attr,
						(String[]) string);
		}
		throw syntaxException(Messages.FILTER_INVALID_OPERATOR);
	}

	private FilterImpl parse_not() throws InvalidSyntaxException {
		skipWhiteSpace();

		if (filterString.charAt(pos) != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);
		FilterImpl child = parse_filter();
		return child.getOp() == FilterImpl.NOT ? ((NotFilterImpl) child).getFilter() : new NotFilterImpl(child);
	}

	private FilterImpl parse_or() throws InvalidSyntaxException {
		skipWhiteSpace();
		char c = filterString.charAt(pos);
		if (c != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);

		ArrayList<FilterImpl> operands = new ArrayList<FilterImpl>();
		while (c == '(') {
			FilterImpl child = parse_filter();
			operands.add(child);
			c = filterString.charAt(pos);
		}
		return normalize(operands, FilterImpl.OR);
	}

	private Object parse_substring() throws InvalidSyntaxException {
		List<String> operands = null;
		sb.setLength(0);
		parseloop: while (true) {
			char c = filterString.charAt(pos);

			switch (c) {
				case ')':
					if (sb.length() > 0) {
						String val = sb.toString();
						if (operands == null)
							return val;
						operands.add(val);
					}
					break parseloop;
				case '(':
					throw syntaxException(Messages.FILTER_INVALID_VALUE);
				case '*':
					if (operands == null)
						operands = new ArrayList<String>();
					if (sb.length() > 0) {
						operands.add(sb.toString());
						sb.setLength(0);
					}
					operands.add(null);
					pos++;
					break;

				case '\\':
					c = filterString.charAt(++pos);
					/* fall through into default */

				default:
					sb.append(c);
					pos++;
					break;
			}
		}

		if (operands == null)
			throw syntaxException(Messages.FILTER_MISSING_VALUE);

		int size = operands.size();
		if (size == 1) {
			String single = operands.get(0);
			if (single != null)
				return single;
		}
		return operands.toArray(new String[size]);
	}

	private String parse_value() throws InvalidSyntaxException {
		sb.setLength(0);
		parseloop: while (true) {
			char c = filterString.charAt(pos);
			switch (c) {
				case ')':
					break parseloop;

				case '(':
					throw syntaxException(Messages.FILTER_INVALID_VALUE);

				case '\\':
					c = filterString.charAt(++pos);
					/* fall through into default */

				default:
					sb.append(c);
					pos++;
					break;
			}
		}
		int len = sb.length();
		while (len > 0 && sb.charAt(len - 1) <= ' ')
			--len;

		if (len == 0)
			throw syntaxException(Messages.FILTER_MISSING_VALUE);
		sb.setLength(len);
		return sb.toString();
	}

	private void skipWhiteSpace() {
		for (int top = filterString.length(); pos < top && Character.isWhitespace(filterString.charAt(pos)); ++pos)
			;
	}

	private InvalidSyntaxException syntaxException(String msg) {
		return new InvalidSyntaxException(NLS.bind(msg, filterString, Integer.valueOf(pos)), filterString);
	}
}
