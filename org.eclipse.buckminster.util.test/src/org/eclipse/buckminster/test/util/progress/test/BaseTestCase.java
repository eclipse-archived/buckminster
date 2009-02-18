/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.test.util.progress.test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;

import org.eclipse.buckminster.util.test.Activator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

import junit.framework.TestCase;

public abstract class BaseTestCase extends TestCase
{

	/**
	 * Fails the test due to the given throwable.
	 */
	public static void fail(String message, Throwable e)
	{
		// If the exception is a CoreException with a multistatus
		// then print out the multistatus so we can see all the info.
		if(e instanceof CoreException)
		{
			IStatus status = ((CoreException)e).getStatus();
			// if the status does not have an exception, print the stack for this one
			if(status.getException() == null)
				e.printStackTrace();
			write(status, 0, System.err);
		}
		else
		{
			if(e != null)
				e.printStackTrace();
		}
		fail(message + ": " + e);
	}

	private static void indent(OutputStream output, int indent)
	{
		for(int i = 0; i < indent; i++)
			try
			{
				output.write("\t".getBytes());
			}
			catch(IOException e)
			{
				// ignore
			}
	}

	private static void write(IStatus status, int indent, PrintStream output)
	{
		indent(output, indent);
		output.println("Severity: " + status.getSeverity());
	
		indent(output, indent);
		output.println("Plugin ID: " + status.getPlugin());
	
		indent(output, indent);
		output.println("Code: " + status.getCode());
	
		indent(output, indent);
		output.println("Message: " + status.getMessage());
	
		if(status.getException() != null)
		{
			indent(output, indent);
			output.print("Exception: ");
			status.getException().printStackTrace(output);
		}
	
		if(status.isMultiStatus())
		{
			IStatus[] children = status.getChildren();
			for(int i = 0; i < children.length; i++)
				write(children[i], indent + 1, output);
		}
	}

	protected File getTestData(String message, String entry)
	{
		if(entry == null)
			fail(message + " entry is null.");
		URL base = Activator.getContext().getBundle().getEntry(entry);
		if(base == null)
			fail(message + " entry not found in bundle: " + entry);
		try
		{
			String osPath = new Path(FileLocator.toFileURL(base).getPath()).toOSString();
			File result = new File(osPath);
			if(!result.getCanonicalPath().equals(result.getPath()))
				fail(message + " result path: " + result.getPath() + " does not match canonical path: "
						+ result.getCanonicalFile().getPath());
			return result;
		}
		catch(IOException e)
		{
			fail(message, e);
		}
		// avoid compile error... should never reach this code
		return null;
	}

}
