package org.slf4j.impl;

import org.slf4j.helpers.NOPMakerAdapter;
import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder
{
	public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

	private final MDCAdapter m_mdcAdapter;

	private StaticMDCBinder()
	{
		MDCAdapter mdcAdapter = ExtensionPointLoader.loadMDCAdapter();
		if(mdcAdapter == null)
			mdcAdapter = new NOPMakerAdapter();
		m_mdcAdapter = mdcAdapter;
	}

	public MDCAdapter getMDCA()
	{
		return m_mdcAdapter;
	}

	public String getMDCAdapterClassStr()
	{
		return m_mdcAdapter.getClass().getName();
	}
}
