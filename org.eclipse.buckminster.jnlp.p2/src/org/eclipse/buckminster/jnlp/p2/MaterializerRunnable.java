/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.jnlp.p2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.xml.sax.SAXException;

public class MaterializerRunnable implements IRunnableWithProgress
{
	private final MaterializationSpec m_mspec;
	
	private MaterializationContext m_context;
	
	public MaterializerRunnable(MaterializationSpec mspec)
	{
		m_mspec = mspec;
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
	{
		try
		{
			m_context = createContext();
			JNLPMaterializationJob.runDelegated(m_context, monitor);
			IStatus status = m_context.getStatus();
			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		catch(OperationCanceledException e)
		{
			throw new InterruptedException();
		}
		catch(Throwable t)
		{
			throw new InvocationTargetException(t);
		}
	}

	public MaterializationContext getContext()
	{
		return m_context;
	}
	
	private MaterializationContext createContext() throws CoreException, IOException, SAXException
	{
		URL url = m_mspec.getResolvedURL();
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(url.openStream());
			IParser<BillOfMaterials> bomParser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);
			BillOfMaterials bom = bomParser.parse(url.toString(), input);
			return new MaterializationContext(bom, m_mspec);
		}
		finally
		{
			IOUtils.close(input);
		}
	}
}
