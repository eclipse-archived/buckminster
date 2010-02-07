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
final class StreamGobblerRedirector extends Thread {
	private final InputStream is;

	private final PrintStream os;

	StreamGobblerRedirector(InputStream is, PrintStream os) {
		this.is = is;
		this.os = os;
	}

	@Override
	public void run() {
		try {
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader bufferedReader = new BufferedReader(isr);
			String readLine;
			while ((readLine = bufferedReader.readLine()) != null)
				os.println(readLine);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
