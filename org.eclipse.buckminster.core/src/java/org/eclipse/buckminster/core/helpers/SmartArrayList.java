/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * @author Filip Hrbek
 *
 *         The <CODE>SmartArrayList</CODE> class extends class ArrayList. It has
 *         the same counstructors. It has methods list transformations (reverse,
 *         rotate, shuffle) and easy-to-use formatted printing its values (using
 *         the constructor with element mapper).<BR>
 */

public class SmartArrayList<E> extends ArrayList<E> {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5853596854483494180L;

	/**
	 * Creates a SmartArrayList of String from comma separated values. If a
	 * value should contain spaces, commas or double quotes, it should be double
	 * quoted. Any backslashes must be doubled unless they are used as escape
	 * characters for double quotes inside a string.<BR>
	 *
	 * Examples:<BR>
	 * fromCommaSeparatedStrings("abc, def, ghi");<BR>
	 * fromCommaSeparatedStrings("\"ab c\", \"de\\\"f\", \"gh,i\"");<BR>
	 *
	 * @param values
	 *            Initial comma separated values
	 * @return List of values
	 * @throws Exception
	 *             if the string cannot be parsed
	 */
	public static SmartArrayList<String> fromCommaSeparatedStrings(String values) throws Exception {
		return fromSeparatedStrings(values, ',');
	}

	/**
	 * Creates a SmartArrayList of String from delimiter separated values. If a
	 * value should contain spaces, delimiters itself or double quotes, it
	 * should be double quoted. Any backslashes must be doubled unless they are
	 * used as escape characters for double quotes inside a string.<BR>
	 *
	 * Examples:<BR>
	 * fromSeparatedStrings("abc, def, ghi", ',');<BR>
	 * fromSeparatedStrings("\"ab c\", \"de\\\"f\", \"gh,i\"", ',');<BR>
	 * fromSeparatedStrings("\"ab c\"def, abc\"de\\\"f\", abcdef\"gh,i\"jkl",
	 * ',');<BR>
	 *
	 * @param values
	 *            Initial comma separated values
	 * @param delimiter
	 *            Delimitting character
	 * @return List of values
	 * @throws Exception
	 *             if the string cannot be parsed
	 */
	public static SmartArrayList<String> fromSeparatedStrings(String values, char delimiter) throws Exception {
		final String bareStringPattern = "(?:[^\\\\\"" + delimiter + "]|\\\\.)+"; //$NON-NLS-1$ //$NON-NLS-2$
		final String doubleQuotedStringPattern = "\"(?:[^\"\\\\]|\\\\.)*\""; //$NON-NLS-1$
		final Pattern tokenPattern = Pattern.compile("^\\s*((?:" + bareStringPattern + "|" + doubleQuotedStringPattern //$NON-NLS-1$ //$NON-NLS-2$
				+ ")*)(?:\\s*" + delimiter + "(.*))?$"); //$NON-NLS-1$ //$NON-NLS-2$
		final Pattern elementPattern = Pattern.compile("(" + bareStringPattern + "|" + doubleQuotedStringPattern + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		Matcher tokenMatcher = tokenPattern.matcher(values);
		SmartArrayList<String> result = new SmartArrayList<String>();
		boolean firstMatch = false;

		while (tokenMatcher.matches()) {
			firstMatch = true;
			String value = tokenMatcher.group(1);
			StringBuilder strippedValue = new StringBuilder();
			Matcher elementMatcher = elementPattern.matcher(value);

			while (elementMatcher.find()) {
				String aux = elementMatcher.group(1);
				if (aux.startsWith("\"")) //$NON-NLS-1$
				{
					if (!aux.endsWith("\"")) //$NON-NLS-1$
					{
						throw new Exception(NLS.bind(Messages.Unbalanced_double_quotes_0, values));
					}
					aux = aux.substring(1, aux.length() - 1);
				}
				strippedValue.append(aux);
			}

			result.add(StringUtil.stripBackslashes(strippedValue.toString()));
			String rest = tokenMatcher.group(2);
			if (rest == null) {
				break;
			}
			tokenMatcher = tokenPattern.matcher(tokenMatcher.group(2));
		}

		if (!firstMatch) {
			throw new Exception(NLS.bind(Messages.Bad_value_list_0, values));
		}

		return result;
	}

	/**
	 * Constructs an empty list with an initial capacity of ten.
	 */

	public SmartArrayList() {
		super();
	}

	/**
	 * Constructs a list containing the elements of the specified collection, in
	 * the order they are returned by the collection's iterator. The
	 * SmartArrayList instance has an initial capacity of 110% the size of the
	 * specified collection.
	 *
	 * @param c
	 *            the collection whose elements are to be placed into this list
	 */

	public SmartArrayList(Collection<? extends E> c) {
		super(c);
	}

	/**
	 * Constructs a list containing the result of the specified mapping applied
	 * on the elements of the specified list
	 *
	 * @param c
	 *            The list whose data are to be mapped into this list
	 * @param m
	 *            An instance of an ElementMapper
	 * @throws MappingException
	 */

	public <S> SmartArrayList(Collection<? extends S> c, ElementMapper<S, E> m) throws MappingException {
		super(c.size());

		E newElem;

		for (S elem : c) {
			if ((newElem = m.mapping(elem)) != null)
				add(newElem);
		}
	}

	/**
	 * Constructs a list containing the elements of the specified list of
	 * arguments, in the order they are specified. The SmartArrayList instance
	 * has an initial capacity of 110% the size of the specified collection.
	 *
	 * @param args
	 *            the list of elements which are to be placed into this list
	 */

	public SmartArrayList(@SuppressWarnings("unchecked") E... args) {
		super(Arrays.asList(args));
	}

	/**
	 * Constructs an empty list with the specified initial capacity.
	 *
	 * @param initialCapacity
	 *            the initial capacity of the list
	 * @throws IllegalArgumentException
	 *             if the specified initial capacity is negative
	 */

	public SmartArrayList(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * The same as equals but the lists does not have to have the same element
	 * order
	 *
	 * @param otherList
	 * @return
	 */
	public boolean hasEqualContent(SmartArrayList<E> otherList) {

		for (E item : this) {
			if (!otherList.contains(item)) {
				return false;
			}
		}

		for (E item : otherList) {
			if (!this.contains(item)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Reverses the order of the SmartArrayList's elements.
	 *
	 * @return reversed SmartArrayList
	 */
	public SmartArrayList<E> reverse() {
		Collections.reverse(this);

		return this;
	}

	/**
	 * Rotates the elements of the SmartArrayList's elements by the specified
	 * distance.
	 *
	 * @param distance
	 *            the distance to rotate the list. There are no constraints on
	 *            this value.
	 * @return changed SmartArrayList
	 */
	public SmartArrayList<E> rotate(int distance) {
		Collections.rotate(this, distance);

		return this;
	}

	/**
	 * Randomly permutes the list using a default source of randomness.
	 *
	 * @return changed SmartArrayList
	 */
	public SmartArrayList<E> shuffle() {
		Collections.shuffle(this);

		return this;
	}

	/**
	 * Creates a string, which contains all values of an instance separated by
	 * ', '.
	 *
	 * @return a string containing all values of an instance separated by ', '
	 */
	@Override
	public String toString() {
		return toString(", "); //$NON-NLS-1$
	}

	/**
	 * Creates a string, which contains all values of an instance separated by
	 * specified delimeter.
	 *
	 * @param delimiter
	 *            delimiter to separate the values
	 * @return a string containing all values of an instance separated by the
	 *         specified delimiter
	 */

	public String toString(String delimiter) {
		Object[] arr = toArray();
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			result.append((i > 0 ? delimiter : "") + arr[i]); //$NON-NLS-1$
		}

		return result.toString();
	}
}
