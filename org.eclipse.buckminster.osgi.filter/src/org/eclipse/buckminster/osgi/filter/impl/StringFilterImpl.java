/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

class StringFilterImpl extends FilterImpl {

	private static class SetAccessibleAction implements PrivilegedAction<Object> {
		private final AccessibleObject accessible;

		SetAccessibleAction(AccessibleObject accessible) {
			this.accessible = accessible;
		}

		@Override
		public Object run() {
			accessible.setAccessible(true);
			return null;
		}
	}

	private static final Class<?>[] constructorType = new Class<?>[] { String.class };

	private final String stringValue;

	StringFilterImpl(int operation, String attr, String value) {
		super(operation, attr);
		this.stringValue = value;
	}

	@Override
	public int compareTo(FilterImpl filter) {
		int cmp = internalCompareTo(filter);
		if (cmp == 0)
			cmp = stringValue.compareTo(((StringFilterImpl) filter).stringValue);
		return cmp;
	}

	@Override
	String getValueAsString() {
		return stringValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	boolean internalCompare(Object value) {
		if (value instanceof String)
			return compare((String) value);

		Class<?> clazz = value.getClass();
		if (clazz.isArray())
			return compare(clazz.getComponentType(), value);

		if (value instanceof Integer)
			return compare(((Integer) value).intValue());

		if (value instanceof Boolean)
			return compare(((Boolean) value).booleanValue());

		if (value instanceof Long)
			return compare(((Long) value).longValue());

		if (value instanceof Byte)
			return compare(((Byte) value).byteValue());

		if (value instanceof Short)
			return compare(((Short) value).shortValue());

		if (value instanceof Character)
			return compare(((Character) value).charValue());

		if (value instanceof Float)
			return compare(((Float) value).floatValue());

		if (value instanceof Double)
			return compare(((Double) value).doubleValue());

		if (value instanceof Comparable<?>)
			return compareAsComparable((Comparable<Object>) value);

		return compareUnknown(value); // RFC 59
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties) {
		Object prop = (properties == null) ? null : properties.get(getAttr());

		return compare(prop);
	}

	@Override
	void toString(StringBuilder sb) {
		sb.append('(');
		sb.append(getAttr());
		switch (getOp()) {
			case EQUAL:
				sb.append('=');
				sb.append(encodeValue(stringValue));
				break;
			case GREATER:
				sb.append(">="); //$NON-NLS-1$
				sb.append(encodeValue(stringValue));
				break;
			case LESS:
				sb.append("<="); //$NON-NLS-1$
				sb.append(encodeValue(stringValue));
				break;
			case APPROX:
				sb.append("~="); //$NON-NLS-1$
				sb.append(encodeValue(approxString(stringValue.toString())));
				break;
		}
		sb.append(')');
	}

	private boolean compare(boolean boolval) {
		boolean boolval2 = Boolean.parseBoolean(stringValue.trim());

		switch (getOp()) {
			case EQUAL:
				return boolval == boolval2;
			case APPROX:
				return boolval == boolval2;
			case GREATER:
				return boolval == boolval2;
			default:
				return boolval == boolval2;
		}
	}

	private boolean compare(byte byteval) {
		byte byteval2;
		try {
			byteval2 = Byte.parseByte(stringValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (getOp()) {
			case EQUAL:
				return byteval == byteval2;
			case APPROX:
				return byteval == byteval2;
			case GREATER:
				return byteval >= byteval2;
			default:
				return byteval <= byteval2;
		}
	}

	private boolean compare(char charval) {
		// Find first non whitespace character
		int end = stringValue.length();
		int idx = 0;
		char charval2 = 0;
		for (; idx < end; ++idx) {
			charval2 = stringValue.charAt(idx);
			if (charval2 > ' ')
				break;
		}
		if (idx == end)
			return false;

		switch (getOp()) {
			case EQUAL:
				return charval == charval2;
			case APPROX:
				return (charval == charval2) || (Character.toUpperCase(charval) == Character.toUpperCase(charval2))
						|| (Character.toLowerCase(charval) == Character.toLowerCase(charval2));
			case GREATER:
				return charval >= charval2;
			default:
				return charval <= charval2;
		}
	}

	private boolean compare(Class<?> type, Object primarray) {
		if (Integer.TYPE.isAssignableFrom(type)) {
			int[] array = (int[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Long.TYPE.isAssignableFrom(type)) {
			long[] array = (long[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Byte.TYPE.isAssignableFrom(type)) {
			byte[] array = (byte[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Short.TYPE.isAssignableFrom(type)) {
			short[] array = (short[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Character.TYPE.isAssignableFrom(type)) {
			char[] array = (char[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Float.TYPE.isAssignableFrom(type)) {
			float[] array = (float[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Double.TYPE.isAssignableFrom(type)) {
			double[] array = (double[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		if (Boolean.TYPE.isAssignableFrom(type)) {
			boolean[] array = (boolean[]) primarray;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}

		return false;
	}

	private boolean compare(double doubleval) {
		double doubleval2;
		try {
			doubleval2 = Double.parseDouble(stringValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (getOp()) {
			case EQUAL:
				return Double.compare(doubleval, doubleval2) == 0;
			case APPROX:
				return Double.compare(doubleval, doubleval2) == 0;
			case GREATER:
				return Double.compare(doubleval, doubleval2) >= 0;
			default:
				return Double.compare(doubleval, doubleval2) <= 0;
		}
	}

	private boolean compare(float floatval) {
		float floatval2;
		try {
			floatval2 = Float.parseFloat(stringValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (getOp()) {
			case EQUAL:
				return Float.compare(floatval, floatval2) == 0;
			case APPROX:
				return Float.compare(floatval, floatval2) == 0;
			case GREATER:
				return Float.compare(floatval, floatval2) >= 0;
			default:
				return Float.compare(floatval, floatval2) <= 0;
		}
	}

	private boolean compare(int intval) {
		int intval2;
		try {
			intval2 = Integer.parseInt(stringValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (getOp()) {
			case EQUAL:
				return intval == intval2;
			case APPROX:
				return intval == intval2;
			case GREATER:
				return intval >= intval2;
			default:
				return intval <= intval2;
		}
	}

	private boolean compare(long longval) {
		long longval2;
		try {
			longval2 = Long.parseLong(stringValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (getOp()) {
			case EQUAL:
				return longval == longval2;
			case APPROX:
				return longval == longval2;
			case GREATER:
				return longval >= longval2;
			default:
				return longval <= longval2;
		}
	}

	private boolean compare(short shortval) {
		short shortval2;
		try {
			shortval2 = Short.parseShort(stringValue.trim());
		} catch (NumberFormatException e) {
			return false;
		}

		switch (getOp()) {
			case EQUAL:
				return shortval == shortval2;
			case APPROX:
				return shortval == shortval2;
			case GREATER:
				return shortval >= shortval2;
			default:
				return shortval <= shortval2;
		}
	}

	private boolean compare(String string) {
		switch (getOp()) {
			case EQUAL:
				return string.equals(stringValue);
			case APPROX:
				string = approxString(string);
				String string2 = approxString(stringValue);

				return string.equalsIgnoreCase(string2);
			case GREATER:
				return string.compareTo(stringValue) >= 0;
			default:
				return string.compareTo(stringValue) <= 0;
		}
	}

	private boolean compareAsComparable(Comparable<Object> value1) {
		try {
			Constructor<?> constructor = value1.getClass().getConstructor(constructorType);
			if (!constructor.isAccessible())
				AccessController.doPrivileged(new SetAccessibleAction(constructor));
			Object value2 = constructor.newInstance(new Object[] { stringValue.trim() });

			switch (getOp()) {
				case EQUAL:
				case APPROX:
					return value1.compareTo(value2) == 0;
				case GREATER:
					return value1.compareTo(value2) >= 0;
				default:
					return value1.compareTo(value2) <= 0;
			}
		} catch (NoSuchMethodException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (InstantiationException e) {
		}
		return false;
	}

	private boolean compareUnknown(Object value1) { // RFC 59
		try {
			Constructor<?> constructor = value1.getClass().getConstructor(constructorType);
			if (!constructor.isAccessible())
				AccessController.doPrivileged(new SetAccessibleAction(constructor));
			Object value2 = constructor.newInstance(new Object[] { stringValue.trim() });
			return value1.equals(value2);
		} catch (NoSuchMethodException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (InstantiationException e) {
		}
		return false;
	}
}
