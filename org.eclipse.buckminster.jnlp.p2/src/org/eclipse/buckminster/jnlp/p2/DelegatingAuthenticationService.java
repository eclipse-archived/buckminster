/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.p2;

import java.security.cert.Certificate;

import org.eclipse.buckminster.jnlp.p2.ui.UserValidationDialog;
import org.eclipse.buckminster.jnlp.p2.ui.certificates.CertificateLabelProvider;
import org.eclipse.buckminster.jnlp.p2.ui.certificates.TrustCertificateDialog;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.internal.provisional.p2.core.IServiceUI;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
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

	public Certificate[] showCertificates(final Certificate[][] certificates)
	{
		final Object[] result = new Object[1];
		final TreeNode[] input = createTreeNodes(certificates);
		Display.getCurrent().syncExec(new Runnable()
		{
			public void run()
			{
				ILabelProvider labelProvider = new CertificateLabelProvider();
				TreeNodeContentProvider contentProvider = new TreeNodeContentProvider();
				Image windowImage = (Image)JNLPPlugin.getRegistered(JNLPPlugin.OBJECT_WINDOW_IMAGE);
				Image wizardImage = (Image)JNLPPlugin.getRegistered(JNLPPlugin.OBJECT_WIZARD_IMAGE);

				TrustCertificateDialog trustCertificateDialog = new TrustCertificateDialog(null, windowImage,
						wizardImage, input, labelProvider, contentProvider);
				trustCertificateDialog.open();
				Certificate[] values = new Certificate[trustCertificateDialog.getResult() == null
						? 0
						: trustCertificateDialog.getResult().length];
				for(int i = 0; i < values.length; i++)
				{
					values[i] = (Certificate)((TreeNode)trustCertificateDialog.getResult()[i]).getValue();
				}
				result[0] = values;
			}
		});
		return (Certificate[])result[0];
	}

	private TreeNode[] createTreeNodes(Certificate[][] certificates)
	{
		TreeNode[] children = new TreeNode[certificates.length];
		for(int i = 0; i < certificates.length; i++)
		{
			TreeNode head = new TreeNode(certificates[i][0]);
			TreeNode parent = head;
			children[i] = head;
			for(int j = 0; j < certificates[i].length; j++)
			{
				TreeNode node = new TreeNode(certificates[i][j]);
				node.setParent(parent);
				parent.setChildren(new TreeNode[] { node });
				parent = node;
			}
		}
		return children;
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
