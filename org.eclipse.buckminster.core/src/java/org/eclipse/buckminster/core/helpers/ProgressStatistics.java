/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.util.Date;

public class ProgressStatistics
{
	public static final AmountConverter TRIVIAL_CONVERTER = new TrivialConverter();
	public static final AmountConverter FILESIZE_CONVERTER = new FileSizeConverter();

	private long m_current;
	private long m_total;
	private Date m_startTime;
	private AmountConverter m_converter;
	private Date m_lastReportTime;
	private int m_reportInterval;
	
	public ProgressStatistics()
	{
		m_current = 0;
		m_total = -1;
		m_startTime = new Date();
		m_lastReportTime = null;
		m_reportInterval = 1000;
		m_converter = TRIVIAL_CONVERTER;
	}

	public ProgressStatistics(long total)
	{
		this();
		m_total = total;
	}

	public void setConverter(AmountConverter converter)
	{
		if (converter == null)
			setConverter(TRIVIAL_CONVERTER);
		else
			m_converter = converter;
	}

	public void increase(long inc)
	{
		m_current += inc;
	}
	
	public long getDuration()
	{
		return (new Date()).getTime() - m_startTime.getTime();
	}
	
	public long getAverageSpeed()
	{
		long dur = getDuration();
		
		if (dur >= 1000)
			return m_current / (dur / 1000);

		return 0L;
	}
	
	public double getPercentage()
	{
		if (m_total > 0)
			return ((double) m_current) / ((double) m_total);
		
		return 0.0;
	}

	public int getReportInterval()
	{
		return m_reportInterval;
	}

	public void setReportInterval(int reportInterval)
	{
		m_reportInterval = reportInterval;
	}

	public boolean shouldReport()
	{
		Date current = new Date();
		
		if (m_lastReportTime == null || current.getTime() - m_lastReportTime.getTime() >= m_reportInterval)
		{
			m_lastReportTime = current;
			return true;
		}
		
		return false;
	}

	public String report()
	{
		return
			m_converter.convert(m_current) +
			(m_total != -1 ? " of " + m_converter.convert(m_total) : "") + " at " +
			m_converter.convert(getAverageSpeed()) + "/s"; 
	}

	@Override
	public String toString()
	{
		return report(); 
	}
	
	public interface AmountConverter
	{
		String convert(long amount);
	}
	
	static class TrivialConverter implements AmountConverter
	{

		public String convert(long amount)
		{
			return "" + amount;
		}
		
	}
	
	static class FileSizeConverter implements AmountConverter
	{

		public String convert(long amount)
		{
			if (amount < 1024)
				return String.format("%dB", Long.valueOf(amount));
			else if (amount < 1024*1024)
				return String.format("%.2fkB", Double.valueOf(((double) amount)/1024));
			else
				return String.format("%.2fMB", Double.valueOf(((double) amount)/(1024*1024)));
		}
		
	}
}


