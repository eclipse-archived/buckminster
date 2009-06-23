package org.eclipse.buckminster.aggregator;

public interface StatusProvider
{
	public static final int OK = 0;

	public static final int BROKEN = 1;

	public static final int BROKEN_CHILD = 2;

	public int getStatus();
}
