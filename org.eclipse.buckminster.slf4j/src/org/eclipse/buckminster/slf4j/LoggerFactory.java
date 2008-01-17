package org.eclipse.buckminster.slf4j;

import org.eclipse.buckminster.runtime.Buckminster;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class LoggerFactory implements ILoggerFactory
{
	public Logger getLogger(String name)
	{
		return new LoggerAdaptor(Buckminster.getLogger());
	}		
}
