package org.eclipse.buckminster.core.test.command;

import java.io.File;
import java.io.PrintStream;

import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.core.test.AbstractTestCase;

public class CommandsTest extends AbstractTestCase {
	public void testMultipleCommands() throws Exception {
		Headless headless = new Headless();
		File commandFile = File.createTempFile("testMultipleCommands", ".bcmd"); //$NON-NLS-1$ //$NON-NLS-2$
		File bomFile = File.createTempFile("testMultipleCommands", ".bom"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			PrintStream cmdOut = new PrintStream(commandFile);
			cmdOut.println("lscmds");
			cmdOut.println("lsprefs download");
			cmdOut.close();
			assertEquals(((Integer) headless.run(new String[] { "--scriptfile", commandFile.toString() })).intValue(), 0); //$NON-NLS-1$
		} finally {
			commandFile.delete();
			bomFile.delete();
		}
	}
}
