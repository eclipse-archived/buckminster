/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jdt.ant;

import org.apache.tools.ant.BuildException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.publisher.ant.AbstractPublishTask;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.actions.JREAction;

@SuppressWarnings("restriction")
public class PublishJRETask extends AbstractPublishTask
{
	protected String jreEnvironment;

	@Override
	public void execute() throws BuildException
	{
		try
		{
			initializeRepositories(getInfo());
		}
		catch(ProvisionException e)
		{
			throw new BuildException("Failed to configure repositories.", e); //$NON-NLS-1$
		}

		IPublisherAction action = new JREAction(jreEnvironment);
		IStatus status = new Publisher(getInfo()).publish(new IPublisherAction[] { action }, new NullProgressMonitor());

		if(!status.isOK())
		{
			throw new BuildException("JRE publishing failed.", new CoreException(status)); //$NON-NLS-1$
		}
	}

	public void setJREEnvironment(String jreEnvironment)
	{
		jreEnvironment = jreEnvironment;
	}
}
