package org.eclipse.buckminster.subversion;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.buckminster.runtime.Buckminster;

/**
 * This class has been introduced to <a
 * href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=258734">correct bug
 * 258734</a> and handle internationalized exceptions from SVN clients .<br>
 * 
 * 
 * @author Guillaume Chatelet
 * 
 */
public class SvnExceptionHandler {
	public static enum EExceptionType {
		FILE_NOT_FOUND, PATH_NOT_FOUND, URL_NON_EXISTENT, OTHER;
	}

	private static final Map<String, String> MAPS = new HashMap<String, String>();

	static {
		initializeExceptionMessages();
	}

	/**
	 * In the presence of nested exceptions use this function to retrieve the
	 * root cause.
	 * 
	 * @param e
	 * @return
	 */
	public static Throwable getRootCause(Throwable e) {
		Throwable p = e;
		Throwable t;
		String msg = e.getMessage();
		while (msg == null && (t = p.getCause()) != null)
			p = t;
		if (msg == null)
			return e;
		return p;
	}

	/**
	 * Will return a list of found exceptions in the Throwable's message
	 * 
	 * @param e
	 * @return
	 */
	public static EExceptionType[] getSvnExceptionTypes(Throwable e) {
		final Set<EExceptionType> exceptions = new HashSet<EExceptionType>();
		final String message = e.getMessage();

		final Set<String> keys = MAPS.keySet();
		for (String key : keys)
			if (message.indexOf(key) != -1)
				exceptions.add(getExceptionTypeFromKey(key));
		return exceptions.toArray(new EExceptionType[exceptions.size()]);
	}

	/**
	 * Will return whether this Throwable is detected as an SVN exception.
	 * 
	 * @param e
	 * @return
	 */
	public static boolean hasSvnException(Throwable e) {
		final String message = e.getMessage();

		final Set<String> keys = MAPS.keySet();
		for (String key : keys)
			if (message.indexOf(key) != -1) {
				Buckminster.getLogger().debug("Found " + getExceptionTypeFromKey(key) + " exception from " + getDescription(key));
				return true;
			}
		return false;
	}

	private static String getDescription(String key) {
		final String exceptionKey = MAPS.get(key);
		final String[] parts = exceptionKey.split("\\|");
		final String langage = parts.length == 2 ? "default" : parts[2];
		return "subversion version " + parts[1] + " langage " + langage;
	}

	private static EExceptionType getExceptionTypeFromKey(String key) {
		final String originalExceptionName = MAPS.get(key);
		if (originalExceptionName.startsWith("file_not_found"))
			return EExceptionType.FILE_NOT_FOUND;
		if (originalExceptionName.startsWith("path_not_found"))
			return EExceptionType.PATH_NOT_FOUND;
		if (originalExceptionName.startsWith("URL_non_existent"))
			return EExceptionType.URL_NON_EXISTENT;
		return EExceptionType.OTHER;
	}

	private static void initializeExceptionMessages() {
		try {
			Properties props = new Properties();
			props.load(SvnExceptionHandler.class.getResourceAsStream("svn_exception_messages.properties"));
			for (Entry<Object, Object> entry : props.entrySet()) {
				final String exception = (String) entry.getKey();
				final String message = (String) entry.getValue();
				MAPS.put(message, exception);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
