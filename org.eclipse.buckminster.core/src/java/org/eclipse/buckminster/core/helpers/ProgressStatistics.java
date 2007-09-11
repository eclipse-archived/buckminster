/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.util.Date;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ProgressStatistics
{
	public static final AmountConverter TRIVIAL_CONVERTER = new TrivialConverter();

	public static final AmountConverter FILESIZE_CONVERTER = new FileSizeConverter();

	public static final int DEFAULT_REPORT_INTERVAL = 1000;

	public static final int DEFAULT_RECENT_SPEED_INTERVAL = 5000;

	public static final int DEFULAT_RECENT_SPEED_RESOLUTION = 1000;

	private long m_current;

	private long m_total;

	private Date m_startTime;

	private AmountConverter m_converter;

	private Date m_lastReportTime;

	private int m_reportInterval;

	private int m_recentSpeedInterval;

	private int m_recentSpeedResolution;

	private SortedMap<Long, Long> m_recentSpeedMap;

	private long m_recentSpeedMapKey;

	public ProgressStatistics()
	{
		m_current = 0;
		m_total = -1;
		m_startTime = new Date();
		m_lastReportTime = null;
		m_reportInterval = DEFAULT_REPORT_INTERVAL;
		m_recentSpeedInterval = DEFAULT_RECENT_SPEED_INTERVAL;
		m_recentSpeedResolution = DEFULAT_RECENT_SPEED_RESOLUTION;
		m_converter = TRIVIAL_CONVERTER;

		m_recentSpeedMap = new TreeMap<Long, Long>();
		m_recentSpeedMapKey = 0L;
	}

	public ProgressStatistics(long total)
	{
		this();
		m_total = total;
	}

	public void setConverter(AmountConverter converter)
	{
		if(converter == null)
			setConverter(TRIVIAL_CONVERTER);
		else
			m_converter = converter;
	}

	public void increase(long inc)
	{
		registerRecentSpeed(getDuration() / m_recentSpeedResolution, inc);
		m_current += inc;
	}

	public long getDuration()
	{
		return (new Date()).getTime() - m_startTime.getTime();
	}

	public long getAverageSpeed()
	{
		long dur = getDuration();

		if(dur >= 1000)
			return m_current / (dur / 1000);

		return 0L;
	}

	synchronized public long getRecentSpeed()
	{
		removeObsoleteRecentSpeedData(getDuration() / m_recentSpeedResolution);
		long dur = 0L;
		long amount = 0L;
		SortedMap<Long, Long> relevantData = m_recentSpeedMap.headMap(Long.valueOf(m_recentSpeedMapKey));

		for(Entry<Long, Long> entry : relevantData.entrySet())
		{
			dur += m_recentSpeedResolution;
			amount += entry.getValue().longValue();
		}

		if(dur >= 1000)
			return amount / (dur / 1000);

		return 0L;
	}

	public int getRecentSpeedInterval()
	{
		return m_recentSpeedInterval;
	}

	public void setRecentSpeedInterval(int recentSpeedInterval)
	{
		if(recentSpeedInterval <= 0)
			recentSpeedInterval = DEFAULT_RECENT_SPEED_INTERVAL;
		m_recentSpeedInterval = recentSpeedInterval;
	}

	public int getRecentSpeedResolution()
	{
		return m_recentSpeedResolution;
	}

	public void setRecentSpeedResolution(int recentSpeedResolution)
	{
		if(recentSpeedResolution <= 0)
			recentSpeedResolution = DEFULAT_RECENT_SPEED_RESOLUTION;

		m_recentSpeedResolution = recentSpeedResolution;
	}

	public double getPercentage()
	{
		if(m_total > 0)
			return ((double)m_current) / ((double)m_total);

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

		if(m_lastReportTime == null || current.getTime() - m_lastReportTime.getTime() >= m_reportInterval)
		{
			m_lastReportTime = current;
			return true;
		}

		return false;
	}

	public String report()
	{
		return m_converter.convert(m_current) + (m_total != -1
				? " of " + m_converter.convert(m_total)
				: "") + " at " + m_converter.convert(getRecentSpeed()) + "/s";
	}

	@Override
	public String toString()
	{
		return report();
	}

	synchronized private void registerRecentSpeed(long key, long inc)
	{
		Long keyL = Long.valueOf(key);
		Long currentValueL = m_recentSpeedMap.get(keyL);
		long currentValue = 0L;
		if(currentValueL != null)
			currentValue = currentValueL.longValue();

		m_recentSpeedMap.put(keyL, Long.valueOf(inc + currentValue));

		if(m_recentSpeedMapKey != key)
		{
			m_recentSpeedMapKey = key;
			removeObsoleteRecentSpeedData(key);
		}
	}

	synchronized private void removeObsoleteRecentSpeedData(long lastKey)
	{
		long threshold = lastKey - m_recentSpeedInterval / m_recentSpeedResolution;
		m_recentSpeedMap.headMap(Long.valueOf(threshold)).clear();
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
			if(amount < 1024)
				return String.format(Locale.US, "%dB", Long.valueOf(amount));
			else if(amount < 1024 * 1024)
				return String.format(Locale.US, "%.2fkB", Double.valueOf(((double)amount) / 1024));
			else
				return String.format(Locale.US, "%.2fMB", Double.valueOf(((double)amount) / (1024 * 1024)));
		}

	}
}
