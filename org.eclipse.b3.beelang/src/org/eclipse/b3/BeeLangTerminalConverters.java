package org.eclipse.b3;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.util.Strings;

public class BeeLangTerminalConverters extends DefaultTerminalConverters {
	@ValueConverter(rule = "HEX")
	public IValueConverter<HexInteger> HEX() {
		return new IValueConverter<HexInteger>() {

			public HexInteger toValue(String string, AbstractNode node) {
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Could not convert empty string to int", node, null);
				try {
					if(string.startsWith("0x") || string.startsWith("0X"))
						string = string.substring(2);
					return new HexInteger(Long.valueOf(string, 16));
				} catch (NumberFormatException e) {
					throw new ValueConverterException("Could not convert '"+ string + "' to int", node, e);
				}
			}

			public String toString(HexInteger value) {
				return Long.toHexString(value.intValue());
			}

		};
	}
	@ValueConverter(rule = "RealValue")
	public IValueConverter<Double> RealValue() {
		return new IValueConverter<Double>() {

			public Double toValue(String string, AbstractNode node) {
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Could not convert empty string to double", node, null);
				try {
					return new Double(string);
				} catch (NumberFormatException e) {
					throw new ValueConverterException("Could not convert '"+ string + "' to double", node, e);
				}
			}

			public String toString(Double value) {
				return value.toString();
			}

		};
	}
	@ValueConverter(rule = "REGEX")
	public IValueConverter<RegularExpression> RegularExpression() {
		return new IValueConverter<RegularExpression>() {

			public RegularExpression toValue(String string, AbstractNode node) {
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Could not convert empty string to regular expression", node, null);
				try {
					return new RegularExpression(string);
				} catch (NumberFormatException e) {
					throw new ValueConverterException("Could not convert '"+ string + "' to regular expression", node, e);
				}
			}

			public String toString(RegularExpression value) {
				return value.toString();
			}

		};
	}
}
