/**
 * 
 */
package org.eclipse.buckminster.executor.actor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * A class to redirect the output of an executed program to a PrintStream
 * 
 * @author Guillaume CHATELET
 */
final class StreamGobblerRedirector extends Thread
{
	private final InputStream m_is;

	private final PrintStream m_os;

	StreamGobblerRedirector(InputStream is, PrintStream os)
	{
		m_is = is;
		m_os = os;
	}

	@Override
	public void run()
	{
		try
		{
			final InputStreamReader isr = new InputStreamReader(m_is);
			final BufferedReader bufferedReader = new BufferedReader(isr);
			String readLine;
			while((readLine = bufferedReader.readLine()) != null)
				m_os.println(readLine);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
