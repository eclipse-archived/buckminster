package org.eclipse.buckminster.jarprocessor;

import java.io.File;
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
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.internal.provisional.equinox.p2.jarprocessor.JarProcessorExecutor;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class JarProcessorActor extends AbstractActor
{
	public static final String ACTOR_ID = "jarProcessor"; //$NON-NLS-1$

	public static final String ALIAS_JAR_FOLDER = "jar.folder"; //$NON-NLS-1$

	public static final String PROP_COMMAND = "command"; //$NON-NLS-1$

	public static final String COMMAND_REPACK = "repack"; //$NON-NLS-1$

	public static final String COMMAND_PACK = "pack"; //$NON-NLS-1$

	public static final String COMMAND_UNPACK = "unpack"; //$NON-NLS-1$

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		Action action = ctx.getAction();
		IPath outputPath = AbstractActor.getSingleProductPath(ctx, action, false);
		IPath jarFolder = null;
		CSpec cspec = action.getCSpec();

		for(Prerequisite preq : action.getPrerequisites())
		{
			if(ALIAS_JAR_FOLDER.equals(preq.getAlias()))
			{
				// This prerequisite should appoint the folder that contains
				// the jar to be packed.
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if(rt != null)
					jarFolder = AbstractActor.getSingleProductPath(ctx, rt, true);
				continue;
			}
			throw new IllegalPrerequisiteException(action, preq.getName());
		}

		if(jarFolder == null)
			throw new MissingPrerequisiteException(action, ALIAS_JAR_FOLDER);

		Map<String, ? extends Object> props = ctx.getProperties();
		String command = (String)props.get(PROP_COMMAND);
		if(command == null)
			throw new MissingPropertyException(action, PROP_COMMAND);

		if(!jarFolder.hasTrailingSeparator())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.input_of_action_0_must_be_folder, action
					.getQualifiedName()));

		if(!outputPath.hasTrailingSeparator())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.output_of_action_0_must_be_folder, action
					.getQualifiedName()));

		File outputDir = outputPath.toFile().getAbsoluteFile();
		outputDir.mkdirs();

		JarProcessorExecutor.Options options = new JarProcessorExecutor.Options();
		options.input = jarFolder.toFile().getAbsoluteFile();
		options.outputDir = outputDir.getAbsolutePath();
		options.processAll = true;

		if(COMMAND_REPACK.equals(command))
			options.repack = true;
		else if(COMMAND_PACK.equals(command))
			options.pack = true;
		else if(COMMAND_UNPACK.equals(command))
			options.unpack = true;
		else
			throw BuckminsterException.fromMessage(NLS.bind(org.eclipse.buckminster.jarprocessor.Messages.action_0_does_not_recognize_command_1, action
					.getQualifiedName(), command));

		JarProcessorExecutor jarProcessorExecutor = new JarProcessorExecutor();
		jarProcessorExecutor.runJarProcessor(options);
		return Status.OK_STATUS;
	}
}
