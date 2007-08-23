/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.Comparator;

import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.ui.ExternalFileEditorInput;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IURIEditorInput;

/**
 * @author Karel Brezina
 *
 */
public class EditorUtils
{
	static class PathComparator implements Comparator<IPath>
	{
		public int compare(IPath o1, IPath o2)
		{
			return o1.toOSString().compareTo(o2.toOSString());
		}
	}
	
	private static Comparator<IPath> s_pathComparator = new PathComparator();

	private EditorUtils()
	{
	}
	
	public static Composite getNamedTabComposite(Composite parent, String header)
	{
		Composite tabComposite = new Composite(parent, SWT.NONE);
		tabComposite.setLayout(new GridLayout(1, true));
		tabComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		tabComposite.setBackgroundMode(SWT.INHERIT_FORCE);

		Label headerLabel = new Label(tabComposite, SWT.BOLD);
		headerLabel.setText(header);
		headerLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		FontData fontData = new FontData();
		fontData.setHeight(14);
		headerLabel.setFont(new Font(tabComposite.getDisplay(), fontData));
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridData.heightHint = 30;
		headerLabel.setLayoutData(gridData);

		return tabComposite;
	}
	
	public static Label createHeaderLabel(Composite parent, String headerText, int horizontalSpan)
	{
		Label label = UiUtils.createGridLabel(parent, headerText, horizontalSpan, 0, SWT.NONE);
		label.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

		return label;
	}
	
	public static Comparator<IPath> getPathComparator()
	{
		return s_pathComparator;
	}

	public static ExternalFileEditorInput getExternalFileEditorInput(IURIEditorInput input, ArtifactType artifactType) throws IOException
	{
		URI uri = input.getURI();
		URL url = uri.toURL();
		String protocol = url.getProtocol();
		
		File cspecFile = null;
		
		if(protocol == null || "file".equals(protocol))
		{
			cspecFile = new File(uri);
		}
		
		if(cspecFile == null || !cspecFile.canWrite())
		{
			cspecFile = File.createTempFile(artifactType.getTempPrefix(), artifactType.getTempExtension());
			cspecFile.deleteOnExit();
			InputStream is = null;
			OutputStream os = null;
			try
			{
				is = URLUtils.openStream(url, null);
				os = new FileOutputStream(cspecFile);
				IOUtils.copy(is, os);
			}
			finally
			{
				IOUtils.close(is);
				IOUtils.close(os);
			}
		}
		
		return new ExternalFileEditorInput(cspecFile, new Path(uri.getPath()).lastSegment(), uri.toString());
	}
}
