/*****************************************************************************
 * Copyright (c) 2006-2011, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ant.actor;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;

public class CopyActor extends AbstractActor {
	public static final String ID = "copy"; //$NON-NLS-1$
	public static final String PROP_INCLUDES = "includes"; //$NON-NLS-1$
	public static final String PROP_EXCLUDES = "excludes"; //$NON-NLS-1$
	public static final String PROP_DEFAULT_EXCLUDES = "defaultExcludes"; //$NON-NLS-1$

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		Action action = ctx.getAction();
		File outputPath = AbstractActor.getSingleAttributePath(ctx, action, true).toFile();
		outputPath.mkdirs();

		Map<String, String> properties = action.getProperties();
		String[] includes = TextUtils.splitAndTrim(properties.get(PROP_INCLUDES), ","); //$NON-NLS-1$
		String[] excludes = TextUtils.splitAndTrim(properties.get(PROP_EXCLUDES), ","); //$NON-NLS-1$
		String dfltExcl = properties.get(PROP_DEFAULT_EXCLUDES);
		boolean defaultExcludes = dfltExcl == null ? true : Boolean.parseBoolean(dfltExcl);
		PathGroup[] pgs = action.getPrerequisiteGroup().getPathGroups(ctx, null);
		SubMonitor subMon = SubMonitor.convert(monitor, pgs.length * 100);
		for (PathGroup pg : pgs)
			scanAndCopy(pg, includes, excludes, defaultExcludes, outputPath, subMon.newChild(100));
		monitor.done();
		return Status.OK_STATUS;
	}

	private void scanAndCopy(PathGroup pg, String[] includes, String[] excludes, boolean defaultExcludes, File outputPath, IProgressMonitor monitor)
			throws CoreException {
		File base = pg.getBase().toFile();
		DirectoryScanner scanner = new DirectoryScanner();
		scanner.setBasedir(base);
		scanner.setIncludes(includes.length == 0 ? null : includes);
		scanner.setExcludes(excludes.length == 0 ? null : excludes);
		if (defaultExcludes)
			scanner.addDefaultExcludes();
		scanner.scan();

		String[] includedFiles = scanner.getIncludedFiles();
		if (includedFiles.length == 0)
			return;

		IPath[] paths = pg.getPaths();
		int npaths = paths.length;
		if (npaths > 0) {
			// Discriminate to only use these paths
			String[] osPaths = new String[npaths];
			for (int idx = 0; idx < npaths; ++idx)
				osPaths[idx] = paths[idx].toOSString();

			ArrayList<String> limitedList = new ArrayList<String>(paths.length);
			nextFile: for (String includedFile : includedFiles) {
				for (int idx = 0; idx < npaths; ++idx) {
					String osPath = osPaths[idx];
					if (includedFile.startsWith(osPath) && (includedFile.length() == osPath.length() || paths[idx].hasTrailingSeparator())) {
						limitedList.add(includedFile);
						continue nextFile;
					}
				}
			}
			includedFiles = limitedList.toArray(new String[limitedList.size()]);
			if (includedFiles.length == 0)
				return;
		}

		File lastOutputParent = null;
		SubMonitor subMon = SubMonitor.convert(monitor, includedFiles.length);
		for (String includedFile : includedFiles) {
			File outputFile = new File(outputPath, includedFile);
			File outputParent = outputFile.getParentFile();
			if (lastOutputParent == null || !lastOutputParent.equals(outputParent)) {
				outputParent.mkdirs();
				lastOutputParent = outputParent;
			}
			File inputFile = new File(base, includedFile);
			FileUtils.copyFile(inputFile, outputParent, outputFile.getName(), subMon.newChild(1));
		}
	}
}
