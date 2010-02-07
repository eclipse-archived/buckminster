/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.lang.reflect.Array;
import java.util.HashSet;

/**
 * Stuff that ought to be in java.util.Arrays
 * 
 * @author Thomas Hallgren
 */
public class ArrayUtils {
	public static <T> T[] appendFirst(T[] array, T[] values) {
		return concatenate(values, array);
	}

	public static <T> T[] appendLast(T[] array, T[] values) {
		return concatenate(array, values);
	}

	public static <T> T[] concatenate(Class<T> componentType, T[] a1, T[] a2) {
		int a1len = a1.length;
		if (a1len == 0)
			return a2;

		int a2len = a2.length;
		if (a2len == 0)
			return a1;

		T[] newArray = newInstance(componentType, a1len + a2len);
		System.arraycopy(a1, 0, newArray, 0, a1len);
		System.arraycopy(a2, 0, newArray, a1len, a2len);
		return newArray;
	}

	public static <T> T[] concatenate(T[] a1, T[] a2) {
		int a1len = a1.length;
		if (a1len == 0)
			return a2;

		int a2len = a2.length;
		if (a2len == 0)
			return a1;

		T[] newArray = newInstance(a1, a1len + a2len);
		System.arraycopy(a1, 0, newArray, 0, a1len);
		System.arraycopy(a2, 0, newArray, a1len, a2len);
		return newArray;
	}

	public static <T> T[] concatenateUnique(T[] a1, T[] a2) {
		HashSet<T> unique = new HashSet<T>();
		int idx = a1.length;
		while (--idx >= 0)
			unique.add(a1[idx]);
		idx = a2.length;
		while (--idx >= 0)
			unique.add(a2[idx]);
		return unique.toArray(newInstance(a1, unique.size()));
	}

	public static boolean contains(Object[] array, Object value) {
		for (Object x : array)
			if (x.equals(value))
				return true;
		return false;
	}

	public static boolean equals(byte[] a, byte[] b, int start, int end) {
		if (start > end || a.length < end || b.length < end)
			return false;

		while (start < end) {
			if (a[start] != b[start])
				return false;
			++start;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] newInstance(Class<T> componentType, int size) {
		return (T[]) Array.newInstance(componentType, size);
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] newInstance(T[] template, int size) {
		return (T[]) Array.newInstance(template.getClass().getComponentType(), size);
	}
}
