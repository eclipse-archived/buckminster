package org.eclipse.b3;

public class RadixInteger extends Number {
	private static final long serialVersionUID = -278530439066752315L;
	Long value;
	int radix;
	RadixInteger(int intval, int radix) {
			value = new Long(intval);
			this.radix = radix;
	}
	RadixInteger(long longval, int radix) {
		value = new Long(longval);
		this.radix = radix;
}
	RadixInteger(Long intval, int radix) {
		value = intval;
		this.radix = radix;
	}
	RadixInteger(String string, int radix) throws NumberFormatException
	{
		value = Long.valueOf(string, radix);
		this.radix = radix;
	}
	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public long longValue() {
		return value.longValue();
	}
	public String toString() {
		String prefix = radix == 16 ? "0x" : radix == 8 ? "0" : "";
		return prefix+Long.toString(value.longValue(), radix);
	}

}
