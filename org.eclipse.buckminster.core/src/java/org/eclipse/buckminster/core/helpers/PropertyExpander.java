package org.eclipse.buckminster.core.helpers;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * A property expander that expands properties and NamedPath within actions
 * 
 * @author Guillaume CHATELET
 */
public class PropertyExpander
{
	/**
	 * Prepares a map with all the namedPath of the current action. The association is prerequisites.alias =
	 * paths.comma.separated
	 * 
	 * @param ctx
	 * @return
	 * @throws CoreException
	 */
	final private static Map<String, String> getNamedPathMap(IActionContext ctx) throws CoreException
	{
		final Map<String, String> map = new HashMap<String, String>();
		final Map<String, PathGroup[]> namedPathGroupArrays = ctx.getNamedPathGroupArrays();
		final Set<String> keySet = namedPathGroupArrays.keySet();
		for(String pathGroupKey : keySet)
		{
			final PathGroup[] pathGroups = namedPathGroupArrays.get(pathGroupKey);
			final Set<String> pathSet = new HashSet<String>(5);
			for(PathGroup pathGroup : pathGroups)
			{
				final String base = pathGroup.getBase().toOSString();
				final IPath[] paths = pathGroup.getPaths();
				// if only a base path, adding it
				if(paths.length == 0)
				{
					pathSet.add(base);
				}
				else
				// otherwise adding all the paths
				{
					for(IPath path : paths)
						pathSet.add(base + path.toOSString());
				}
			}
			final StringBuffer buffer = new StringBuffer();
			for(String path : pathSet)
			{
				buffer.append(path).append(File.pathSeparatorChar);
			}
			final int lastCharIndex = buffer.length() - 1;
			if(lastCharIndex > 0 && buffer.charAt(lastCharIndex) == File.pathSeparatorChar)
				map.put(pathGroupKey, buffer.substring(0, lastCharIndex));
		}
		return map;
	}

	/**
	 * Returns a map that contains variables to replace This contains the properties and the NamedPath defined in the
	 * Action
	 * 
	 * @param ctx
	 * @return
	 * @throws CoreException
	 */
	final private static Map<String, String> getVariables(IActionContext ctx) throws CoreException
	{
		final Map<String, String> map = new HashMap<String, String>();
		map.putAll(getNamedPathMap(ctx));
		map.putAll(ctx.getProperties());
		return map;
	}

	private final Map<String, String> m_variableMap;

	public PropertyExpander(IActionContext ctx) throws CoreException
	{
		m_variableMap = getVariables(ctx);
	}

	public String expand(String string)
	{
		return ExpandingProperties.expand(m_variableMap, string, 1);
	}
}
