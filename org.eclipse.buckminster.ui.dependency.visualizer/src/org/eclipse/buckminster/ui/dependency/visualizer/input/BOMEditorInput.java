/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.ui.dependency.visualizer.input;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.ui.dependency.visualizer.DependencyVisualizer;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * Used to wrap an in-memory model of a BillOfMaterials into an IEditorInput for
 * the {@link DependencyVisualizer}
 * 
 * @author Johannes Utzig
 * 
 */
public class BOMEditorInput implements IEditorInput {

	private BillOfMaterials BillOfMaterials;

	public BOMEditorInput(BillOfMaterials bom) {
		BillOfMaterials = bom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public BillOfMaterials getBillOfMaterials() {
		return BillOfMaterials;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {

		return getBillOfMaterials().getResolution().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return getBillOfMaterials().getResolution().getComponentIdentifier().toString();
	}

}
