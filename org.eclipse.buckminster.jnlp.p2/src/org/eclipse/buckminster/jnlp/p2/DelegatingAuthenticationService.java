/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.jnlp.p2;

import java.security.cert.Certificate;

import org.eclipse.buckminster.jnlp.p2.ui.UserValidationDialog;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.internal.provisional.p2.core.IServiceUI;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class DelegatingAuthenticationService implements IServiceUI
{
	public static final QualifiedName SUPPRESS_AUTHENTICATION_JOB_MARKER = new QualifiedName(JNLPPlugin.ID,
			"SUPPRESS_AUTHENTICATION_REQUESTS"); //$NON-NLS-1$

	public AuthenticationInfo getUsernamePassword(final String location)
	{
		return getUsernamePassword(location, null);
	}

	public AuthenticationInfo getUsernamePassword(final String location, final AuthenticationInfo previousInfo)
	{
		final AuthenticationInfo[] result = new AuthenticationInfo[1];
		if(!suppressAuthentication())
		{
			Display.getCurrent().syncExec(new Runnable()
			{
				public void run()
				{
					Image windowImage = (Image)JNLPPlugin.getRegistered(JNLPPlugin.OBJECT_WINDOW_IMAGE);
					Image wizardImage = (Image)JNLPPlugin.getRegistered(JNLPPlugin.OBJECT_WIZARD_IMAGE);
					String windowTitle = (String)JNLPPlugin.getRegistered(JNLPPlugin.OBJECT_WINDOW_TITLE);

					if(windowTitle == null)
						windowTitle = "Login required";

					UserValidationDialog dialog = new UserValidationDialog(null, windowImage, windowTitle, wizardImage,
							"Login required", "Please provide login details for " + location, previousInfo);
					if(dialog.open() == Window.OK)
					{
						result[0] = dialog.getResult();
					}
				}

			});
		}
		return result[0];
	}

	public Certificate[] showCertificates(Certificate[][] certificates)
	{
		// TODO implement
		throw new RuntimeException("Cannot show certificates - not implemented yet.");
	}

	private boolean suppressAuthentication()
	{
		Job job = Job.getJobManager().currentJob();
		if(job != null)
		{
			return job.getProperty(SUPPRESS_AUTHENTICATION_JOB_MARKER) != null;
		}
		return false;
	}

}
