/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.test.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class VersionTestCase extends TestCase
{
	private static final Pattern s_labelExp = Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_.-]*)$"); //$NON-NLS-1$

	private static final Pattern s_versionExp = Pattern.compile("^\\s*(.*?)?([@/#][^@/#]*?)?\\s*$"); //$NON-NLS-1$

	private static final Pattern s_timestampExp = Pattern
			.compile("^((?:19|2[01])\\d{2})-?(0[1-9]|1[012])-?(0[1-9]|[12][0-9]|3[01])(?:T([01][0-9]|2[0-3]):?([0-5][0-9]):?([0-5][0-9]))?$"); //$NON-NLS-1$

	public void testVersionExpr() throws Exception
	{
		Matcher m = s_versionExp.matcher("  LATEST  "); //$NON-NLS-1$
		if(m.matches())
		{
			int c = m.groupCount();
			for(int i = 1; i <= c; ++i)
				System.out.println("Group " + i + " '" + m.group(i) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println();
		}
		m = s_versionExp.matcher(" LATEST "); //$NON-NLS-1$
		if(m.matches())
		{
			int c = m.groupCount();
			for(int i = 1; i <= c; ++i)
				System.out.println("Group " + i + " '" + m.group(i) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println();
		}
		m = s_versionExp.matcher("main/"); //$NON-NLS-1$
		if(m.matches())
		{
			int c = m.groupCount();
			for(int i = 1; i <= c; ++i)
				System.out.println("Group " + i + " '" + m.group(i) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println();
		}
		m = s_labelExp.matcher("A$45"); //$NON-NLS-1$
		if(m.matches())
		{
			int c = m.groupCount();
			for(int i = 1; i <= c; ++i)
				System.out.println("Group " + i + " '" + m.group(i) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println();
		}
		m = s_timestampExp.matcher("2005-07-24"); //$NON-NLS-1$
		if(m.matches())
		{
			int c = m.groupCount();
			for(int i = 1; i <= c; ++i)
				System.out.println("Group " + i + " '" + m.group(i) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println();
		}
		m = s_timestampExp.matcher("20050724T101523"); //$NON-NLS-1$
		if(m.matches())
		{
			int c = m.groupCount();
			for(int i = 1; i <= c; ++i)
				System.out.println("Group " + i + " '" + m.group(i) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			System.out.println();
		}
	}
}
