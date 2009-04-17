/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Hrbek
 * 
 */
public class TailLineBuffer
{
	private String[] m_linebuffer;

	private int m_size;

	private int m_pointer;

	/**
	 * Creates a new TailLineBuffer with specified number of lines.
	 * 
	 * @param lines
	 */
	public TailLineBuffer(int lines)
	{
		if(lines < 0)
			lines = 0;

		m_linebuffer = new String[lines];
		m_size = 0;
		m_pointer = 0;
	}

	/**
	 * Get list of all lines saved in the buffer.
	 * 
	 * @return
	 */
	public List<String> getLines()
	{
		List<String> lines = new ArrayList<String>();

		int start = m_size <= m_linebuffer.length
				? 0
				: m_pointer - 1;

		if(start < 0)
			start += m_linebuffer.length;

		for(int i = 0; i < m_size; i++)
		{
			int pointer = (start + i) % m_linebuffer.length;
			lines.add(m_linebuffer[pointer]);
		}

		return lines;
	}

	/**
	 * Get list of all lines saved in the buffer concatenated into a single string. The lines are delimited with the
	 * newline character for current platform.
	 * 
	 * @return
	 */
	public String getLinesAsString()
	{
		StringBuilder lines = new StringBuilder();

		int start = m_size <= m_linebuffer.length
				? 0
				: m_pointer - 1;

		if(start < 0)
			start += m_linebuffer.length;

		for(int i = 0; i < m_size; i++)
		{
			int pointer = (start + i) % m_linebuffer.length;
			lines.append(m_linebuffer[pointer]);
			lines.append('\n');
		}

		return lines.length() > 0
				? lines.toString()
				: null;
	}

	/**
	 * Write a line to the buffer. The line can actually contain any characters including new lines, however it's
	 * recommended that the line is really a single line. For a single line, there should be no line terminator at the
	 * end of the line.
	 * 
	 * @param line
	 */
	public void writeLine(String line)
	{
		if(m_linebuffer.length == 0)
			return;

		if(m_size < m_linebuffer.length)
			m_size++;

		m_linebuffer[m_pointer++] = line;

		if(m_pointer >= m_linebuffer.length)
			m_pointer = 0;
	}
}
