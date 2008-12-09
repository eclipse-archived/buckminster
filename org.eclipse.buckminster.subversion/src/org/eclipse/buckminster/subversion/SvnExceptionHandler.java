package org.eclipse.buckminster.subversion;

public class SvnExceptionHandler
{

	public static boolean hasParts(Throwable e, String... parts)
	{
		return hasParts(e.getMessage(), parts);
	}

	public static boolean hasParts(String message, String... parts)
	{
		if(message == null)
			return false;
		final String lowerMsg = message.toLowerCase();
		for(String part : parts)
			if(lowerMsg.contains(part))
				return true;
		return false;
	}

	public static Throwable getRootCause(Throwable e)
	{
		Throwable p = e;
		Throwable t;
		String msg = e.getMessage();
		while(msg == null && (t = p.getCause()) != null)
			p = t;
		if(msg == null)
			return e;
		return p;
	}
}
