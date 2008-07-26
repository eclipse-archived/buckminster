package org.eclipse.buckminster.fetcher.actor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.helpers.PropertyExpander;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.fetcher.IResourceFetcher;
import org.eclipse.buckminster.fetcher.StreamProcessorFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Buckminster's Actor to fetch resources from the web. It can uncompress archives, flatten and filter contents.
 * 
 * @author Guillaume CHATELET
 */
public class FetcherActor extends AbstractActor
{
	private static final String INCLUDE = "include=";

	private static final String FETCHER_URL = "url";

	private static final String FETCHER_TO_DIR = "dir";

	private static final String FETCHER_OPTIONS = "options";

	private static final String FETCHER_UNCOMPRESS = "uncompress";

	private static final String FETCHER_FLATTEN = "flatten";

	private static final String FETCHER_LOGIN = "login";

	private static final String FETCHER_PASSWORD = "pass";

	private static final String[] validProperties = { FETCHER_URL, FETCHER_TO_DIR, FETCHER_OPTIONS, FETCHER_LOGIN,
			FETCHER_PASSWORD };

	private static final String[] validOptions = { FETCHER_FLATTEN, FETCHER_UNCOMPRESS };

	private static final String PLUGIN_ID = "org.eclipse.buckminster.fetcher";

	private PropertyExpander m_expander;

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		try
		{
			m_expander = new PropertyExpander(ctx);
			checkProperties();
			monitor.beginTask(null, 1);
			final IResourceFetcher resourceFetcher = getResourceFetcher();
			final String login = getSafeProperty(FETCHER_LOGIN);
			final String password = getSafeProperty(FETCHER_PASSWORD);
			if(login != null && password != null)
				resourceFetcher.setBasicAuthCredential(login, password);
			resourceFetcher.fetch(monitor);
		}
		catch(IOException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage());
		}
		catch(CoreException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			throw e;
		}
		finally
		{
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	/**
	 * Checks the defined ActorProperties to warn user for misspelled keywords
	 * 
	 * @throws CoreException
	 */
	private void checkProperties() throws CoreException
	{
		HashSet<String> validSet = new HashSet<String>(Arrays.asList(validProperties));
		final Map<String, String> actorProperties = getActiveContext().getAction().getActorProperties();
		final Set<String> keySet = actorProperties.keySet();
		for(String property : keySet)
		{
			if(validSet.contains(property) == false)
			{
				final StringBuffer buffer = new StringBuffer("ActorProperty \"" + property
						+ "\" is not a valid one. Valid keys are :\n");
				for(String validProperty : validSet)
					buffer.append(validProperty).append(' ');
				throw new IllegalStateException(buffer.toString());
			}
		}
		validSet.clear();
		validSet.addAll(Arrays.asList(validOptions));
		final HashSet<String> options = getOptions();
		for(String option : options)
		{
			if(validSet.contains(option) == false && option.startsWith(INCLUDE) == false)
			{
				final StringBuffer buffer = new StringBuffer("ActorProperty options \"" + option
						+ "\" is not a valid one. Valid options are :\n");
				for(String validProperty : validSet)
					buffer.append(validProperty).append(' ');
				buffer.append(INCLUDE);
				throw new IllegalStateException(buffer.toString());
			}
		}
	}

	/**
	 * @return the correct IResourceFetcher according to the uncompress flag.
	 * @throws MalformedURLException
	 * @throws CoreException
	 */
	private IResourceFetcher getResourceFetcher() throws MalformedURLException, CoreException
	{
		final URL url = getUrl();
		final String destinationDirectory = getDestinationDirectory();
		if(shouldUncompress())
		{
			return StreamProcessorFactory.getUncompressInstance(url, destinationDirectory, getIncludes(),
					shouldFlatten());
		}
		// regular file copy
		final String localFileName = getLocalFileNameFromUrl(url);
		return StreamProcessorFactory.getCopyInstance(url, destinationDirectory, localFileName);
	}

	/**
	 * @param url
	 * @return the name of the archive as stated by the provided url ie : http://www.example.com/download/file.tar.gz
	 *         returns file.tar.gz
	 */
	private String getLocalFileNameFromUrl(URL url)
	{
		return new File(url.getFile()).getName();
	}

	/**
	 * @return the destination directory if set or the component home otherwise
	 * @throws CoreException
	 */
	private String getDestinationDirectory() throws CoreException
	{
		final String homePath = getActiveContext().getProperties().get("buckminster.home");
		final String toDir = getSafeProperty(FETCHER_TO_DIR);
		if(toDir == null)
		{
			return homePath;
		}
		if(new File(toDir).isAbsolute())
			return toDir;
		return homePath + toDir + File.separatorChar;
	}

	/**
	 * @return the URL to use
	 * @throws MalformedURLException
	 * @throws CoreException
	 */
	private URL getUrl() throws MalformedURLException, CoreException
	{
		return new URL(getSafeProperty(FETCHER_URL));
	}

	/**
	 * @param property
	 * @return an expanded property or null if property is not set
	 * @throws CoreException
	 */
	final private String getSafeProperty(String property) throws CoreException
	{
		final String p = TextUtils.notEmptyTrimmedString(this.getActorProperty(property));
		if(p == null)
			return null;
		return m_expander.expand(p);
	}

	/**
	 * @return a set of options
	 * @throws CoreException
	 */
	private HashSet<String> getOptions() throws CoreException
	{
		final HashSet<String> options = new HashSet<String>();
		final String optionActorProperty = getSafeProperty(FETCHER_OPTIONS);
		if(optionActorProperty == null)
			return options;
		final String[] split = optionActorProperty.split(";");
		for(String option : split)
			options.add(option);
		return options;
	}

	private boolean hasOption(String option) throws CoreException
	{
		return getOptions().contains(option);
	}

	private boolean shouldUncompress() throws CoreException
	{
		return hasOption(FETCHER_UNCOMPRESS);
	}

	private boolean shouldFlatten() throws CoreException
	{
		return hasOption(FETCHER_FLATTEN);
	}

	/**
	 * @return a list of regexp to use as include filter while uncompressing the archive.
	 * @throws CoreException
	 */
	private List<String> getIncludes() throws CoreException
	{
		final List<String> includes = new ArrayList<String>();
		final HashSet<String> options = getOptions();
		for(String option : options)
		{
			if(option.startsWith(INCLUDE))
			{
				int start = option.indexOf('=');
				includes.add(option.substring(start + 1));
			}
		}
		return includes;
	}
}
