package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.IllegalPrerequisiteException;
import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.actor.MissingPropertyException;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

public class JarProcessorActor extends AbstractActor {
	public static final String ACTOR_ID = "jarProcessor"; //$NON-NLS-1$

	public static final String ALIAS_JAR_FOLDER = "jar.folder"; //$NON-NLS-1$

	public static final String PROP_COMMAND = "command"; //$NON-NLS-1$

	public static final String COMMAND_REPACK = "repack"; //$NON-NLS-1$

	public static final String COMMAND_PACK = "pack"; //$NON-NLS-1$

	public static final String COMMAND_UNPACK = "unpack"; //$NON-NLS-1$

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		Action action = ctx.getAction();
		IPath outputPath = AbstractActor.getSingleAttributePath(ctx, action, false);
		IPath jarFolder = null;
		CSpec cspec = action.getCSpec();

		for (Prerequisite preq : action.getPrerequisites()) {
			if (ALIAS_JAR_FOLDER.equals(preq.getAlias())) {
				// This prerequisite should appoint the folder that contains
				// the jar to be packed.
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if (rt != null)
					jarFolder = AbstractActor.getSingleAttributePath(ctx, rt, true);
				continue;
			}
			throw new IllegalPrerequisiteException(action, preq.getName());
		}

		if (jarFolder == null)
			throw new MissingPrerequisiteException(action, ALIAS_JAR_FOLDER);

		Map<String, ? extends Object> props = ctx.getProperties();
		String command = (String) props.get(PROP_COMMAND);
		if (command == null)
			throw new MissingPropertyException(action, PROP_COMMAND);

		if (!jarFolder.hasTrailingSeparator())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.input_of_action_0_must_be_folder, action.getQualifiedName()));

		if (!outputPath.hasTrailingSeparator())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.output_of_action_0_must_be_folder, action.getQualifiedName()));

		File outputDir = outputPath.toFile().getAbsoluteFile();
		outputDir.mkdirs();
		try {
			if (COMMAND_REPACK.equals(command))
				repackJars(jarFolder.toFile(), outputDir);
			else if (COMMAND_PACK.equals(command))
				packJars(jarFolder.toFile(), outputDir);
			else if (COMMAND_UNPACK.equals(command))
				unpackJars(jarFolder.toFile(), outputDir);
			else
				throw BuckminsterException.fromMessage(NLS.bind(org.eclipse.buckminster.jarprocessor.Messages.action_0_does_not_recognize_command_1,
						action.getQualifiedName(), command));
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
		return Status.OK_STATUS;
	}

	private void packJars(File inputDir, File outputDir) throws CoreException, IOException {
		File[] files = inputDir.listFiles();
		for (File file : files) {
			String name = file.getName();
			if (file.isDirectory()) {
				File childOutputDir = new File(outputDir, name);
				childOutputDir.mkdir();
				packJars(file, childOutputDir);
				continue;
			}

			FileUtils.copyFile(file, outputDir, name, null);
			if (!name.endsWith(IConstants.JAR_SUFFIX))
				continue;

			RecursivePacker rpacker = new RecursivePacker(null, true);
			rpacker.pack(new File(outputDir, name));
		}
	}

	private void repackJars(File inputDir, File outputDir) throws CoreException, IOException {
		File[] files = inputDir.listFiles();
		for (File file : files) {
			String name = file.getName();
			if (file.isDirectory()) {
				File childOutputDir = new File(outputDir, name);
				childOutputDir.mkdir();
				repackJars(file, childOutputDir);
				continue;
			}

			if (name.endsWith(IConstants.JAR_SUFFIX)) {
				RecursiveConditioner rcond = new RecursiveConditioner(null);
				rcond.condition(file, new File(outputDir, name));
			} else
				FileUtils.copyFile(file, outputDir, name, null);
		}
	}

	private void unpackJars(File inputDir, File outputDir) {
	}
}
