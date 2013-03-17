/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.internal;

import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.eclipse.buckminster.download.Messages;
import org.eclipse.osgi.util.NLS;

public class ProgressStatistics {
	private static final int DEFAULT_REPORT_INTERVAL = 1000;

	private static final int SPEED_INTERVAL = 5000;

	private static final int SPEED_RESOLUTION = 1000;

	private static String convert(long amount) {
		if (amount < 1024)
			return String.format(Locale.US, "%dB", Long.valueOf(amount)); //$NON-NLS-1$

		if (amount < 1024 * 1024)
			return String.format(Locale.US, "%.2fkB", Double.valueOf(((double) amount) / 1024)); //$NON-NLS-1$

		return String.format(Locale.US, "%.2fMB", Double.valueOf(((double) amount) / (1024 * 1024))); //$NON-NLS-1$
	}

	private final String fileName;

	private final long total;

	private final long startTime;

	private long current;

	private long lastReportTime;

	private int reportInterval;

	private SortedMap<Long, Long> recentSpeedMap;

	private long recentSpeedMapKey;

	public ProgressStatistics(String fileName, long total) {
		this.startTime = System.currentTimeMillis();
		this.fileName = fileName;
		this.total = total;
		this.current = 0;
		this.lastReportTime = 0;
		this.reportInterval = DEFAULT_REPORT_INTERVAL;
		this.recentSpeedMap = new TreeMap<Long, Long>();
		this.recentSpeedMapKey = 0L;
	}

	public long getAverageSpeed() {
		long dur = getDuration();

		if (dur >= 1000)
			return current / (dur / 1000);

		return 0L;
	}

	public long getDuration() {
		return System.currentTimeMillis() - startTime;
	}

	public double getPercentage() {
		if (total > 0)
			return ((double) current) / ((double) total);

		return 0.0;
	}

	synchronized public long getRecentSpeed() {
		removeObsoleteRecentSpeedData(getDuration() / SPEED_RESOLUTION);
		long dur = 0L;
		long amount = 0L;
		SortedMap<Long, Long> relevantData = recentSpeedMap.headMap(Long.valueOf(recentSpeedMapKey));

		for (Entry<Long, Long> entry : relevantData.entrySet()) {
			dur += SPEED_RESOLUTION;
			amount += entry.getValue().longValue();
		}

		if (dur >= 1000)
			return amount / (dur / 1000);

		return 0L;
	}

	public int getReportInterval() {
		return reportInterval;
	}

	public long getTotal() {
		return total;
	}

	public void increase(long inc) {
		registerRecentSpeed(getDuration() / SPEED_RESOLUTION, inc);
		current += inc;
	}

	public synchronized String report() {
		return total != -1 ? NLS.bind(Messages.fetching_0_1_of_2_at_3, new String[] { fileName, convert(current), convert(total),
				convert(getRecentSpeed()) }) : NLS.bind(Messages.fetching_0_1_at_2, new String[] { fileName, convert(current),
				convert(getRecentSpeed()) });
	}

	public void setReportInterval(int reportInterval) {
		this.reportInterval = reportInterval;
	}

	public boolean shouldReport() {
		long currentTime = System.currentTimeMillis();
		if (lastReportTime == 0 || currentTime - lastReportTime >= reportInterval) {
			lastReportTime = currentTime;
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
		long threshold = lastKey - SPEED_INTERVAL / SPEED_RESOLUTION;
		recentSpeedMap.headMap(Long.valueOf(threshold)).clear();
	}
}
