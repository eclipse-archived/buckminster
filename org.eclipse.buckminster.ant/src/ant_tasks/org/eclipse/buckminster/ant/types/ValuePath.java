/**************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
***************************************************************************/
package org.eclipse.buckminster.ant.types;

import java.io.File;
import java.util.StringTokenizer;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.eclipse.buckminster.core.helpers.FileUtils;

/**
 * @author Thomas Hallgren
 */
public class ValuePath extends Path
{
	public ValuePath(Project project)
	{
		super(project);
	}

	public ValuePath(Project project, String path)
	{
		super(project, path);
	}

	public void setValue(String value)
	{
		if(value == null)
			return;

		if(value == null)
			return;

		StringTokenizer fileSetDecls = new StringTokenizer(value.substring(1), "?");
		while(fileSetDecls.hasMoreTokens())
		{
			StringTokenizer tokens = new StringTokenizer(fileSetDecls.nextToken(), FileUtils.PATH_SEP);
			if(!tokens.hasMoreElements())
				return;

			File pathRoot = new File(tokens.nextToken());
			if(tokens.hasMoreTokens())
			{
				do
				{
					Path.PathElement pathElem = createPathElement();
					pathElem.setLocation(new File(pathRoot, tokens.nextToken()));
				} while(tokens.hasMoreElements());
			}
			else
			{
				// Empty root. Use as path anyway.
				//
				Path.PathElement pathElem = createPathElement();
				pathElem.setLocation(pathRoot);
			}
		}
	}
}
