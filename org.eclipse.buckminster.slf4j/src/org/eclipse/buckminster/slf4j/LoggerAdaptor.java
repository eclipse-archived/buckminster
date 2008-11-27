package org.eclipse.buckminster.slf4j;

import org.eclipse.buckminster.runtime.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

public class LoggerAdaptor extends MarkerIgnoringBase
{
	private final Logger m_logger;

	LoggerAdaptor(Logger logger)
	{
		m_logger = logger;
	}

	public void debug(String msg)
	{
		m_logger.debug(msg);
	}

	public void debug(String format, Object arg)
	{
		if(m_logger.isDebugEnabled())
			m_logger.debug(MessageFormatter.format(format, arg));
	}

	public void debug(String format, Object arg1, Object arg2)
	{
		if(m_logger.isDebugEnabled())
			m_logger.debug(MessageFormatter.format(format, arg1, arg2));
	}

	public void debug(String format, Object[] argArray)
	{
		if(m_logger.isDebugEnabled())
			m_logger.debug(MessageFormatter.arrayFormat(format, argArray));
	}

	public void debug(String msg, Throwable t)
	{
		m_logger.debug(t, msg);
	}

	public void error(String msg)
	{
		m_logger.error(msg);
	}

	public void error(String format, Object arg)
	{
		if(m_logger.isErrorEnabled())
			m_logger.error(MessageFormatter.format(format, arg));
	}

	public void error(String format, Object arg1, Object arg2)
	{
		if(m_logger.isErrorEnabled())
			m_logger.error(MessageFormatter.format(format, arg1, arg2));
	}

	public void error(String format, Object[] argArray)
	{
		if(m_logger.isErrorEnabled())
			m_logger.error(MessageFormatter.arrayFormat(format, argArray));
	}

	public void error(String msg, Throwable t)
	{
		m_logger.error(t, msg);
	}

	public String getName()
	{
		return "buckminster"; //$NON-NLS-1$
	}

	public void info(String msg)
	{
		m_logger.info(msg);
	}

	public void info(String format, Object arg)
	{
		if(m_logger.isInfoEnabled())
			m_logger.info(MessageFormatter.format(format, arg));
	}

	public void info(String format, Object arg1, Object arg2)
	{
		if(m_logger.isInfoEnabled())
			m_logger.info(MessageFormatter.format(format, arg1, arg2));
	}

	public void info(String format, Object[] argArray)
	{
		if(m_logger.isInfoEnabled())
			m_logger.info(MessageFormatter.arrayFormat(format, argArray));
	}

	public void info(String msg, Throwable t)
	{
		m_logger.info(t, msg);
	}

	public boolean isDebugEnabled()
	{
		return m_logger.isDebugEnabled();
	}

	public boolean isErrorEnabled()
	{
		return m_logger.isErrorEnabled();
	}

	public boolean isInfoEnabled()
	{
		return m_logger.isInfoEnabled();
	}

	public boolean isTraceEnabled()
	{
		return false;
	}

	public boolean isWarnEnabled()
	{
		return m_logger.isWarningEnabled();
	}

	public void trace(String msg)
	{
	}

	public void trace(String format, Object arg)
	{
	}

	public void trace(String format, Object arg1, Object arg2)
	{
	}

	public void trace(String format, Object[] argArray)
	{
	}

	public void trace(String msg, Throwable t)
	{
	}

	public void warn(String msg)
	{
		m_logger.warning(msg);
	}

	public void warn(String format, Object arg)
	{
		if(m_logger.isWarningEnabled())
			m_logger.warning(MessageFormatter.format(format, arg));
	}

	public void warn(String format, Object arg1, Object arg2)
	{
		if(m_logger.isWarningEnabled())
			m_logger.warning(MessageFormatter.format(format, arg1, arg2));
	}

	public void warn(String format, Object[] argArray)
	{
		if(m_logger.isWarningEnabled())
			m_logger.warning(MessageFormatter.arrayFormat(format, argArray));
	}

	public void warn(String msg, Throwable t)
	{
		m_logger.warning(t, msg);
	}
}
