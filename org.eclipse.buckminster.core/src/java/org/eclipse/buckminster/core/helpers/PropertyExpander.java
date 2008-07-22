package org.eclipse.buckminster.core.helpers;

import java.util.HashMap;
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
	private final Map<String, String> m_variableMap;

	public PropertyExpander(IActionContext ctx) throws CoreException
	{
		m_variableMap = getVariables(ctx);
	}

	public String expand(String string)
	{
		return ExpandingProperties.expand(m_variableMap, string, 1);
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
			final StringBuffer buffer = new StringBuffer();
			for(PathGroup pathGroup : pathGroups)
			{
				final IPath[] paths = pathGroup.getPaths();
				for(IPath path : paths)
					buffer.append(pathGroup.getBase() + path.toOSString()).append(';');
			}
			if(buffer.length() > 0)
				buffer.deleteCharAt(buffer.length() - 1);
			map.put(pathGroupKey, buffer.toString());
		}
		return map;
	}
}
