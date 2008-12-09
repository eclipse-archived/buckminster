package org.eclipse.buckminster.subclipse.internal;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.tigris.subversion.svnclientadapter.SVNUrl;

/**
 * Translates Subclipse types to more standard eclipse SVN types Used to unify the logic between the two SVN clients
 * (Subclipse and Subversive)
 * 
 * @author Guillaume Chatelet
 * 
 */
public class TypeTranslator
{
	public static URI from(SVNUrl url) throws CoreException
	{
		try
		{
			return new URI(url.toString());
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static SVNUrl from(URI url) throws CoreException
	{
		try
		{
			return new SVNUrl(url.toString());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

}
