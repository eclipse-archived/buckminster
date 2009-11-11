package org.eclipse.b3;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.conversion.impl.AbstractNullSafeConverter;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.util.Strings;// 
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import com.google.inject.Inject;

/**
 * Converters for BeeLang terminals.
 */
@SuppressWarnings("restriction")
public class BeeLangTerminalConverters extends  AbstractDeclarativeValueConverterService {

	private Grammar grammar;

	@Inject
	public void setGrammar(IGrammarAccess grammarAccess) {
		this.grammar = grammarAccess.getGrammar();
	}

	protected Grammar getGrammar() {
		return grammar;
	}

	@ValueConverter(rule = "ID")
	public IValueConverter<String> ID() {
		return new AbstractNullSafeConverter<String>() {
			@Override
			protected String internalToValue(String string, AbstractNode node) {
				return string.startsWith("^") ? string.substring(1) : string;
			}

			@Override
			protected String internalToString(String value) {
				if (GrammarUtil.getAllKeywords(getGrammar()).contains(value)) {
					return "^" + value;
				}
				return value;
			}
		};
	}

	@ValueConverter(rule = "STRING")
	public IValueConverter<String> STRING() {
		return new AbstractNullSafeConverter<String>() {
			@Override
			protected String internalToValue(String string, AbstractNode node) {
				return Strings.convertFromJavaString(string.substring(1, string.length() - 1));
			}

			@Override
			protected String internalToString(String value) {
				return '"' + Strings.convertToJavaString(value) + '"';
			}
		};
	}
	@ValueConverter(rule = "DOCUMENTATION")
	public IValueConverter<String> DOCUMENTATION() {
		return new AbstractNullSafeConverter<String>() {
			@Override
			protected String internalToValue(String string, AbstractNode node) {
				String lines[] = string.split("[\n\r]");
				StringBuffer buf = new StringBuffer();
				for(int i = 0; i < lines.length;i++)
				{
					// get rid of documentation start
					String s = lines[i];
					if(s.startsWith("/**"))
						lines[i] = s = s.substring(3);
					// get rid of documentation end
					if(s.endsWith("*/"))
						lines[i] = s = s.substring(0,s.length()-2);
					String trimmed = s.trim();
					if(!trimmed.startsWith("*")) {
						// no leading *, keep the whitespace at the beginning
						int pos = s.indexOf(trimmed);
						if(pos > 0)
							lines[i] = s.substring(0, pos)+trimmed;
					} else {
						while(trimmed.startsWith("*"))
							trimmed = trimmed.substring(1, trimmed.length());
						lines[i] = trimmed;
					}
					if(i != 0)
						buf.append("\n");
					buf.append(lines[i]);
				}
				return Strings.convertFromJavaString(buf.toString());
			}

			@Override
			protected String internalToString(String value) {
				return '"' + Strings.convertToJavaString(value) + '"';
			}
		};
	}

	@ValueConverter(rule = "IntValue")
	public IValueConverter<RadixInteger> IntValue() {
		return new IValueConverter<RadixInteger>() {
			
			public RadixInteger toValue(String string, AbstractNode node) throws ValueConverterException
			{
				int radix = 10;
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Can not convert empty string to int", node, null);
				try {
					if(string.startsWith("0x") || string.startsWith("0X")) {
						radix = 16;
						string = string.substring(2);
					}
					else if(string.startsWith("0") && string.length() > 1)
						radix = 8;

					return new RadixInteger(string,radix);
				} catch (NumberFormatException e) {
					String format = "";
					switch(radix){ case 8: format="octal"; break; case 10: format="decimal"; break; 
					case 16: format="hexadecimal"; break;
					}
					throw new ValueConverterException("Can not convert to "+ format +" integer : "+string, node, null);
				}
			}
			public String toString(RadixInteger value) {
				return value.toString();
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
					throw new ValueConverterException("Could not convert '"+ string + "' to double", node, null);
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
	@ValueConverter(rule = "URI")
	public IValueConverter<URI> URI() {
		return new IValueConverter<URI>() {
			
			public URI toValue(String string, AbstractNode node) throws ValueConverterException
			{
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Can not convert empty string to URI", node, null);
				try {
					string = Strings.convertFromJavaString(string.substring(1, string.length() - 1));

					return new URI(string);
				} catch (URISyntaxException e) {
					throw new ValueConverterException("Value'"+string+"' is not a valid URI :" +e.getMessage(), node, null);
				}
			}
			public String toString(URI value) {
				return '"' + value.toString()+'"';
			}

		};
	}
	@ValueConverter(rule = "VersionLiteral")
	public IValueConverter<Version> Version() {
		return new IValueConverter<Version>() {
			
			public Version toValue(String string, AbstractNode node) throws ValueConverterException
			{
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Can not convert empty string to Version", node, null);
				try {
					char c = string.charAt(0);
					if(c == '"'||  c == '\"')
						string = Strings.convertFromJavaString(string.substring(1, string.length() - 1));

					return Version.create(string);
				} catch (IllegalArgumentException e) {
					throw new ValueConverterException("Version '"+string+"' is not a valid version: "+e.getMessage(), node, null);
				}
			}
			public String toString(Version value) {
				return value.toString();
			}

		};
	}	
	@ValueConverter(rule = "VersionRangeLiteral")
	public IValueConverter<VersionRange> VersionRange() {
		return new IValueConverter<VersionRange>() {
			
			public VersionRange toValue(String string, AbstractNode node) throws ValueConverterException
			{
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Can not convert empty string to VersionRange", node, null);
				try {
					char c = string.charAt(0);
					if(c == '"'||  c == '\"')
						string = Strings.convertFromJavaString(string.substring(1, string.length() - 1));

					return new VersionRange(string);
				} catch (IllegalArgumentException e) {
					throw new ValueConverterException("VersionRange '"+string+"' is not a valid range: "+e.getMessage(), node, null);
				}
			}
			public String toString(VersionRange value) {
				return value.toString();
			}

		};
	}	
}
