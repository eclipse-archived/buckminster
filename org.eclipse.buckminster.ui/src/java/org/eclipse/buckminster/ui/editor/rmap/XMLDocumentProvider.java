/*******************************************************************************
 * Copyright (c) 2007-2008, Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *     Remy Chi Jian Suen (Versant Corp.) - Bug 250379
 *******************************************************************************/
package org.eclipse.buckminster.ui.editor.rmap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;

public class XMLDocumentProvider extends TextFileDocumentProvider
{

	@Override
	protected FileInfo createFileInfo(Object element) throws CoreException
	{
		FileInfo fileInfo = super.createFileInfo(element);
		if(fileInfo == null)
		{
			return null;
		}

		IDocument document = fileInfo.fTextFileBuffer.getDocument();
		if(document != null)
		{
			IDocumentPartitioner partitioner = new FastPartitioner(new XMLPartitionScanner(), new String[] {
					XMLPartitionScanner.XML_TAG, XMLPartitionScanner.XML_COMMENT });
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}

		return fileInfo;
	}
}
