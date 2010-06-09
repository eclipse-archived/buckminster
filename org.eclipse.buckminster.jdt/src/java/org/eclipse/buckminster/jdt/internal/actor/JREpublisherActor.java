/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jdt.internal.actor;

import java.net.URI;
import java.util.Map;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.jdt.JdtPlugin;
import org.eclipse.buckminster.jdt.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.actions.JREAction;
import org.eclipse.osgi.util.NLS;

/**
 * A JRE IU Publisher Actor.
 * 
 * @author michal.ruzicka@cloudsmith.com
 */
@SuppressWarnings("restriction")
public class JREpublisherActor extends AbstractActor {

	public static final String PROPERTY_JRE_ENVIRONMENT_NAME = "jre.environment.name"; //$NON-NLS-1$

	public static final String ALIAS_METADATA_REPOSITORY = "metadata.repository"; //$NON-NLS-1$

	public static IStatus createStatus(String message, Throwable t) {
		return new Status(IStatus.ERROR, JdtPlugin.PLUGIN_ID, message, t);
	}

	@Override
	protected IStatus internalPerform(IActionContext actionContext, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(Messages.publishing_jre, 10);

		PublisherInfo provider = new PublisherInfo();
		provider.setArtifactOptions(provider.getArtifactOptions() | IPublisherInfo.A_PUBLISH);

		try {
			String jreEnvironment = processActorProperties(actionContext);
			URI metadataLocation = processPrerequisites(actionContext);

			provider.setMetadataRepository(Publisher.createMetadataRepository(Buckminster.getDefault().getService(IProvisioningAgent.class),
					metadataLocation, null, true, false));

			monitor.worked(3);

			IPublisherAction action = new JREAction(jreEnvironment);

			return new Publisher(provider).publish(new IPublisherAction[] { action }, MonitorUtils.subMonitor(monitor, 7));
		} finally {
			monitor.done();
		}
	}

	protected String processActorProperties(IActionContext actionContext) throws CoreException {
		String jreEnvironment = null;
		StringBuilder message = null;

		for (Map.Entry<String, ?> property : actionContext.getAction().getActorProperties().entrySet()) {
			String name = property.getKey();

			if (PROPERTY_JRE_ENVIRONMENT_NAME.equals(name)) {
				jreEnvironment = ExpandingProperties.expand(actionContext.getProperties(), (String) property.getValue(), 0);
				continue;
			}

			if (message == null)
				message = new StringBuilder();
			else
				message.append(Messages.list_separator);
			message.append(name);
		}

		if (message != null)
			throw new CoreException(createStatus(NLS.bind(Messages.unrecognized_properties_supplied_0, message.toString()) + '\n'
					+ NLS.bind(Messages.recognized_properties_0, PROPERTY_JRE_ENVIRONMENT_NAME), null));

		return jreEnvironment;
	}

	protected URI processPrerequisites(IActionContext actionContext) throws CoreException {
		CSpec cspec = actionContext.getCSpec();

		for (Prerequisite prerequisite : actionContext.getAction().getPrerequisites()) {
			if (ALIAS_METADATA_REPOSITORY.equals(prerequisite.getAlias())) {
				// This prerequisite should appoint the metadata repository to
				// publish the JRE to
				Attribute attr = prerequisite.getReferencedAttribute(cspec, actionContext);
				if (attr != null)
					return AbstractActor.getSingleAttributePath(actionContext, attr, false).toFile().toURI();
			}
		}

		throw new MissingPrerequisiteException(actionContext.getAction(), ALIAS_METADATA_REPOSITORY);
	}
}
