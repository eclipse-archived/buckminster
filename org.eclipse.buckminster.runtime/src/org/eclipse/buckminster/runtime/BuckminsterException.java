/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.runtime;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This class contains basic wrap and unwrap functionality for various commonly
 * used exceptions. The idea is to normalize exception into a {@link CoreException}
 *
 * @author Thomas Hallgren
 */
public class BuckminsterException extends CoreException
{
	private static final long serialVersionUID = -3152601911941317801L;

	// Special class that gives us access to the setMessage
	//
	private static class BMStatus extends Status
	{
		public BMStatus(int severity, String pluginId, int code, String message, Throwable exception)
		{
			super(severity, pluginId, code, message, exception);
		}

		protected void setMessage(String message)
		{
			super.setMessage(message);
		}
	}

	public static IStatus createStatus(String message, Throwable cause)
	{
		return new BMStatus(IStatus.ERROR, Buckminster.PLUGIN_ID, IStatus.OK, message, cause);
	}

	public static void deeplyPrint(IStatus status, PrintStream strm, boolean stackTrace)
	{
		deeplyPrint(status, strm, stackTrace, 0);
	}

	public static void deeplyPrint(Throwable e, PrintStream strm, boolean stackTrace)
	{
		deeplyPrint(e, strm, stackTrace, 0);
	}

	public static CoreException fromMessage(String message)
	{
		return fromMessage(message, null);
	}

	public static CoreException fromMessage(String message, Throwable cause)
	{
		return new CoreException(createStatus(message, cause));
	}

	public static Throwable unwind(Throwable t)
	{
		for(;;)
		{
			Class tc = t.getClass();

			// We don't use instanceof operator since we want
			// the explicit class, not subclasses.
			//
			if(tc != RuntimeException.class
			&& tc != InvocationTargetException.class
			&& tc != SAXException.class
			&& tc != IOException.class)
				break;

			Throwable cause = t.getCause();
			if(cause == null)
				break;

			String msg = t.getMessage();
			if(msg != null && !msg.equals(cause.toString()))
				break;

			t = cause;
		}
		return t;
	}

	public static CoreException unwindCoreException(CoreException exception)
	{
		IStatus status = exception.getStatus();
		while(status != null && status.getException() instanceof CoreException)
		{
			exception = (CoreException)status.getException();
			status = exception.getStatus();
		}
		return exception;
	}

	public static CoreException wrap(Throwable t)
	{
		t = unwind(t);
		if(t instanceof CoreException)
			return unwindCoreException((CoreException)t);

		if(t instanceof OperationCanceledException || t instanceof InterruptedException)
			return new CoreException(Status.CANCEL_STATUS);

		String msg = t.toString();
		if(t instanceof SAXParseException)
		{
			SAXParseException se = (SAXParseException)t;
			StringBuffer bld = new StringBuffer(msg);
			bld.append(": ");
			bld.append(se.getSystemId());
			bld.append(" at line: ");
			bld.append(se.getLineNumber());
			bld.append(" column: ");
			bld.append(se.getColumnNumber());
			msg = bld.toString();
		}
		
		return new BuckminsterException(msg, t);
	}

	private static void appendLevelString(PrintStream strm, int level)
	{
		if(level > 0)
		{
			strm.print("[0");
			for(int idx = 1; idx < level; ++idx)
			{
				strm.print('.');
				strm.print(level);
			}
			strm.print(']');
		}
	}

	private static void deeplyPrint(CoreException ce, PrintStream strm, boolean stackTrace, int level)
	{
		appendLevelString(strm, level);
		if(stackTrace)
			ce.printStackTrace(strm);
		deeplyPrint(ce.getStatus(), strm, stackTrace, level);
	}

	private static void deeplyPrint(IStatus status, PrintStream strm, boolean stackTrace, int level)
	{
		appendLevelString(strm, level);
		String msg = status.getMessage();
		strm.println(msg);
		Throwable cause = status.getException();
		if(cause != null)
		{
			strm.print("Caused by: ");
			if(stackTrace || !(msg.equals(cause.getMessage()) || msg.equals(cause.toString())))
				deeplyPrint(cause, strm, stackTrace, level);
		}

		if(status.isMultiStatus())
		{
			IStatus[] children = status.getChildren();
			for(int i = 0; i < children.length; i++)
				deeplyPrint(children[i], strm, stackTrace, level + 1);
		}
	}

	private static void deeplyPrint(Throwable t, PrintStream strm, boolean stackTrace, int level)
	{
		if(t instanceof CoreException)
			deeplyPrint((CoreException)t, strm, stackTrace, level);
		else
		{
			appendLevelString(strm, level);
			if(stackTrace)
				t.printStackTrace(strm);
			else
			{
				strm.println(t.toString());
				Throwable cause = t.getCause();
				if(cause != null)
				{
					strm.print("Caused by: ");
					deeplyPrint(cause, strm, stackTrace, level);
				}
			}
		}
	}

	public BuckminsterException()
	{
		super(createStatus(null, null));
	}

	public BuckminsterException(String message)
	{
		super(createStatus(message, null));
	}

	public BuckminsterException(String message, Throwable cause)
	{
		super(createStatus(message, cause));
		this.initCause(cause);
	}

	protected void setMessage(String message)
	{
		((BMStatus)this.getStatus()).setMessage(message);
	}

}
