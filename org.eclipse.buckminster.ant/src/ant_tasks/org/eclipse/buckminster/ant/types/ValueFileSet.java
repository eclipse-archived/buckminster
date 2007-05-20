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

import org.apache.tools.ant.types.FileSet;
import org.eclipse.buckminster.core.helpers.FileUtils;

/**
 * @author Thomas Hallgren
 */
public class ValueFileSet extends FileSet
{
	public void setValue(String value)
	{
		if(value == null)
			return;

		if(value == null)
			return;

		// We might have several fileset declarations but we are only
		// interested in the first one.
		//
		StringTokenizer fileSetDecls = new StringTokenizer(value.substring(1), "?");
		if(!fileSetDecls.hasMoreTokens())
			return;

		StringTokenizer tokens = new StringTokenizer(fileSetDecls.nextToken(), FileUtils.PATH_SEP);
		if(!tokens.hasMoreTokens())
			return;

		setDir(new File(tokens.nextToken()));
		setDefaultexcludes(true);

		while(tokens.hasMoreTokens())
			createInclude().setName(tokens.nextToken());
	}
}
