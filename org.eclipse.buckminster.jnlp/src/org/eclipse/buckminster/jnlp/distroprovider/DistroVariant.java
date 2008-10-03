/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karel Brezina
 * 
 */
public class DistroVariant
{
	public static final String TARGET_PREFIX = "target";

	public static final String TARGET_OS = TARGET_PREFIX + ".os";

	public static final String TARGET_WS = TARGET_PREFIX + ".ws";

	public static final String TARGET_ARCH = TARGET_PREFIX + ".arch";

	public static final String TARGET_NL = TARGET_PREFIX + ".nl";

	public static final String RELEASE = "org.eclipse.release";

	public static final String[] SUPPORTED_PROPERTIES = {TARGET_OS, TARGET_WS, TARGET_ARCH, TARGET_NL, RELEASE};
	
	private Long m_distroId;

	private boolean m_broken;

	private String m_arch;

	private boolean m_archCompatible = true;

	private String m_os;

	private boolean m_osCompatible = true;

	private String m_ws;

	private boolean m_wsCompatible = true;

	private String m_nl;

	private boolean m_nlCompatible = true;

	private String m_release;

	private boolean m_releaseCompatible = true;

	public String getArch()
	{
		return m_arch;
	}

	public Long getDistroId()
	{
		return m_distroId;
	}

	public String getNL()
	{
		return m_nl;
	}

	public String getOS()
	{
		return m_os;
	}

	public String getPlatformString()
	{
		List<String> platform = new ArrayList<String>();
		
		platform.add(m_arch);
		platform.add(m_os);
		platform.add(m_ws);
		platform.add(m_release);
		platform.add(m_nl);
		
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		
		for(String item: platform)
		{
			if(item == null)
				continue;
			
			if(first)
				first = false;
			else
				sb.append(',');
			
			sb.append(item);
		}
		
		return sb.toString();
	}
	
	public String getRelease()
	{
		return m_release;
	}

	public String getWS()
	{
		return m_ws;
	}

	public boolean isArchCompatible()
	{
		return m_archCompatible;
	}

	public boolean isBroken()
	{
		return m_broken;
	}

	public boolean isCompatible()
	{
		return m_archCompatible && m_osCompatible && m_wsCompatible && m_nlCompatible && m_releaseCompatible;
	}

	public boolean isNLCompatible()
	{
		return m_nlCompatible;
	}

	public boolean isOSCompatible()
	{
		return m_osCompatible;
	}

	public boolean isReleaseCompatible()
	{
		return m_releaseCompatible;
	}

	public boolean isSimplePackaging()
	{
		return m_arch == null && m_os == null && m_ws == null && m_nl == null && m_release == null;
	}
	
	public boolean isWSCompatible()
	{
		return m_wsCompatible;
	}

	public void setArch(String arch)
	{
		m_arch = arch;
	}

	public void setArchCompatible(boolean archCompatible)
	{
		m_archCompatible = archCompatible;
	}

	public void setBroken(boolean broken)
	{
		m_broken = broken;
	}

	public void setDistroId(Long distroId)
	{
		m_distroId = distroId;
	}

	public void setNL(String nl)
	{
		m_nl = nl;
	}

	public void setNLCompatible(boolean nlCompatible)
	{
		m_nlCompatible = nlCompatible;
	}

	public void setOS(String os)
	{
		m_os = os;
	}

	public void setOSCompatible(boolean osCompatible)
	{
		m_osCompatible = osCompatible;
	}

	public void setRelease(String release)
	{
		m_release = release;
	}

	public void setReleaseCompatible(boolean releaseCompatible)
	{
		m_releaseCompatible = releaseCompatible;
	}

	public void setWS(String ws)
	{
		m_ws = ws;
	}

	public void setWSCompatible(boolean wsCompatible)
	{
		m_wsCompatible = wsCompatible;
	}

}
