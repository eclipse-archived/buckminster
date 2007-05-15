package org.eclipse.buckminster.jnlp;

public class MissingPropertyException extends Exception
{
	private static final long serialVersionUID = 41992797592163523L;

	public MissingPropertyException(String propertyKey)
	{
		super("Missing required property '" + propertyKey + '\'');
	}
}
