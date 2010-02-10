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

public class ProgressStatistics {
	public interface AmountConverter {
		String convert(long amount);
	}

	static class FileSizeConverter implements AmountConverter {

		@Override
		public String convert(long amount) {
			if (amount < 1024)
				return String.format(Locale.US, "%dB", Long.valueOf(amount)); //$NON-NLS-1$
			else if (amount < 1024 * 1024)
				return String.format(Locale.US, "%.2fkB", Double.valueOf(((double) amount) / 1024)); //$NON-NLS-1$
			else
				return String.format(Locale.US, "%.2fMB", Double.valueOf(((double) amount) / (1024 * 1024))); //$NON-NLS-1$
		}

	}

	static class TrivialConverter implements AmountConverter {

		@Override
		public String convert(long amount) {
			return "" + amount; //$NON-NLS-1$
		}

	}

	public static final AmountConverter TRIVIAL_CONVERTER = new TrivialConverter();

	public static final AmountConverter FILESIZE_CONVERTER = new FileSizeConverter();

	public static final int DEFAULT_REPORT_INTERVAL = 1000;

	public static final int DEFAULT_RECENT_SPEED_INTERVAL = 5000;

	public static final int DEFULAT_RECENT_SPEED_RESOLUTION = 1000;

	private long current;

	private long total;

	private Date startTime;

	private AmountConverter converter;

	private Date lastReportTime;

	private int reportInterval;

	private int recentSpeedInterval;

	private int recentSpeedResolution;

	private SortedMap<Long, Long> recentSpeedMap;

	private long recentSpeedMapKey;

	public ProgressStatistics() {
		current = 0;
		total = -1;
		startTime = new Date();
		lastReportTime = null;
		reportInterval = DEFAULT_REPORT_INTERVAL;
		recentSpeedInterval = DEFAULT_RECENT_SPEED_INTERVAL;
		recentSpeedResolution = DEFULAT_RECENT_SPEED_RESOLUTION;
		converter = TRIVIAL_CONVERTER;

		recentSpeedMap = new TreeMap<Long, Long>();
		recentSpeedMapKey = 0L;
	}

	public ProgressStatistics(long total) {
		this();
		this.total = total;
	}

	public long getAverageSpeed() {
		long dur = getDuration();

		if (dur >= 1000)
			return current / (dur / 1000);

		return 0L;
	}

	public long getDuration() {
		return (new Date()).getTime() - startTime.getTime();
	}

	public double getPercentage() {
		if (total > 0)
			return ((double) current) / ((double) total);

		return 0.0;
	}

	synchronized public long getRecentSpeed() {
		removeObsoleteRecentSpeedData(getDuration() / recentSpeedResolution);
		long dur = 0L;
		long amount = 0L;
		SortedMap<Long, Long> relevantData = recentSpeedMap.headMap(Long.valueOf(recentSpeedMapKey));

		for (Entry<Long, Long> entry : relevantData.entrySet()) {
			dur += recentSpeedResolution;
			amount += entry.getValue().longValue();
		}

		if (dur >= 1000)
			return amount / (dur / 1000);

		return 0L;
	}

	public int getRecentSpeedInterval() {
		return recentSpeedInterval;
	}

	public int getRecentSpeedResolution() {
		return recentSpeedResolution;
	}

	public int getReportInterval() {
		return reportInterval;
	}

	public void increase(long inc) {
		registerRecentSpeed(getDuration() / recentSpeedResolution, inc);
		current += inc;
	}

	public String report() {
		return converter.convert(current) + (total != -1 ? " of " + converter.convert(total) //$NON-NLS-1$
		: "") + " at " + converter.convert(getRecentSpeed()) + "/s"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public void setConverter(AmountConverter converter) {
		if (converter == null)
			converter = TRIVIAL_CONVERTER;
		this.converter = converter;
	}

	public void setRecentSpeedInterval(int recentSpeedInterval) {
		if (recentSpeedInterval <= 0)
			recentSpeedInterval = DEFAULT_RECENT_SPEED_INTERVAL;
		this.recentSpeedInterval = recentSpeedInterval;
	}

	public void setRecentSpeedResolution(int recentSpeedResolution) {
		if (recentSpeedResolution <= 0)
			recentSpeedResolution = DEFULAT_RECENT_SPEED_RESOLUTION;

		this.recentSpeedResolution = recentSpeedResolution;
	}

	public void setReportInterval(int reportInterval) {
		this.reportInterval = reportInterval;
	}

	public boolean shouldReport() {
		Date now = new Date();

		if (lastReportTime == null || now.getTime() - lastReportTime.getTime() >= reportInterval) {
			lastReportTime = now;
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return report();
	}

	synchronized private void registerRecentSpeed(long key, long inc) {
		Long keyL = Long.valueOf(key);
		Long currentValueL = recentSpeedMap.get(keyL);
		long currentValue = 0L;
		if (currentValueL != null)
			currentValue = currentValueL.longValue();

		recentSpeedMap.put(keyL, Long.valueOf(inc + currentValue));

		if (recentSpeedMapKey != key) {
			recentSpeedMapKey = key;
			removeObsoleteRecentSpeedData(key);
		}
	}

	synchronized private void removeObsoleteRecentSpeedData(long lastKey) {
		long threshold = lastKey - recentSpeedInterval / recentSpeedResolution;
		recentSpeedMap.headMap(Long.valueOf(threshold)).clear();
	}
}
