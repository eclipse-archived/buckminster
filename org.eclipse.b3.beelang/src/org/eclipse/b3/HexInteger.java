package org.eclipse.b3;

public class HexInteger extends Number {
	private static final long serialVersionUID = -278530439066752315L;
	Long value;
	HexInteger(int intval) {
			value = new Long(intval);
	}
	HexInteger(Long intval) {
		value = intval;
}
	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return value.doubleValue();
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return value.floatValue();
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return value.intValue();
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return value.longValue();
	}
	public String toString() {
		return "0x"+Long.toString(value.intValue(), 16);
	}

}
