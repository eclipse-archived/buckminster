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
		File commandFile = File.createTempFile("testMultipleCommands", ".bcmd");
		File bomFile = File.createTempFile("testMultipleCommands", ".bom");
		try
		{
			PrintStream cmdOut = new PrintStream(commandFile);
			cmdOut.println("resolve --noimport --bomfile '" + escapeBS(bomFile) + "' http://www.eclipse.org/buckminster/samples/queries/demo.cquery");
			cmdOut.println("import '" + escapeBS(bomFile) + "'");
			cmdOut.close();
			assertEquals(((Integer)headless.run(new String[] { "--scriptfile", commandFile.toString() })).intValue(), 0);
		}
		finally
		{
			commandFile.delete();
			bomFile.delete();
		}
	}
}
