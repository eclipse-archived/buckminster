package org.eclipse.buckminster.core.test.command;

import java.io.File;
import java.io.PrintStream;

import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.core.test.AbstractTestCase;

public class CommandsTest extends AbstractTestCase
{
	private static String escapeBS(File file)
	{
		StringBuilder bld = new StringBuilder();
		String fileStr = file.toString();
		int length = fileStr.length();
		for(int idx = 0; idx < length; ++idx)
		{
			char c = fileStr.charAt(idx);
			if(c == '\\')
				bld.append('\\');
			bld.append(c);
		}
		return bld.toString();
	}

	public void testMultipleCommands() throws Exception
	{
		Headless headless = new Headless();
		File commandFile = File.createTempFile("testMultipleCommands", ".bcmd"); //$NON-NLS-1$ //$NON-NLS-2$
		File bomFile = File.createTempFile("testMultipleCommands", ".bom"); //$NON-NLS-1$ //$NON-NLS-2$
		try
		{
			PrintStream cmdOut = new PrintStream(commandFile);
			cmdOut.println("resolve --noimport --bomfile '" + escapeBS(bomFile) //$NON-NLS-1$
					+ "' http://www.eclipse.org/buckminster/samples/queries/demo.cquery"); //$NON-NLS-1$
			cmdOut.println("import '" + escapeBS(bomFile) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			cmdOut.close();
			assertEquals(((Integer)headless.run(new String[] { "--scriptfile", commandFile.toString() })).intValue(), 0); //$NON-NLS-1$
		}
		finally
		{
			commandFile.delete();
			bomFile.delete();
		}
	}
}
