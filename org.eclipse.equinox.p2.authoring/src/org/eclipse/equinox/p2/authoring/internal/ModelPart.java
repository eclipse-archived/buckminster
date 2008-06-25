// TODO: Remove license that should not be used
/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/
/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

public class ModelPart implements IAdaptable
{
	ModelPart m_parent;

	/**
	 * Returns the model part's parent, or null if it does not have a parent. Adapt to ModelRoot to check
	 * if the object is the root of the model.
	 * @return
	 */
	public ModelPart getParent()
	{
		return m_parent;
	}

	public void setParent(ModelPart parent)
	{
		m_parent = parent;
	}
	public void notifyChanged()
	{
		if(m_parent != null)
			m_parent.notifyChanged();
	}
	/**
	 * Default implementation of {@link IAdaptable#getAdapter(Class)}.
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(adapter.isInstance(this))
			return this;
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}
	
	/**
	 * Adds a model part at a given index. If index is outside of range (or specifically -1), the new part is added
	 * last. The modified model part array is returned, and the index array is updated with the index where the part was
	 * added.
	 * 
	 * @param array
	 *            the array where the add takes place
	 * @param part
	 *            the part to add
	 * @param index
	 *            in/out array with wanted index (in), and resulting index (out)
	 * @return the index where the part was added in index[], and the resulting array
	 */
	protected ModelPart[] addModelPart(ModelPart[] array, ModelPart part, int[] index)
	{
		Class<?> clazz = array.getClass().getComponentType();
		ModelPart[] tmp = (ModelPart[])java.lang.reflect.Array.newInstance(clazz, array.length + 1);
		int idx = index[0];
		int j = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(i == idx)
				tmp[j++] = part;
			tmp[j++] = array[i];
		}
		if(idx < 0 || idx >= array.length)
		{
			idx = array.length;
			tmp[idx] = part;
		}
		part.setParent(this);
		index[0] = idx;
		return tmp;
	}
	/**
	 * Removes the model part from the array of parts.
	 * 
	 * @param array
	 *            the model part array to remove the part from
	 * @param part
	 *            the part to remove
	 * @param index
	 *            an array of size 1 where the index to the removed artifact was found is returned
	 * @return the array of model parts with the part removed - same array is returned if part was not found and index
	 *         returned is then -1
	 */
	protected static ModelPart[] removeModelPart(ModelPart[] array, ModelPart part, int[] index)
	{
		int idx = -1; // not found (yet)
		for(int i = 0; i < array.length; i++)
			if(array[i] == part)
			{
				idx = i;
				break;
			}
		if(idx == -1)
		{
			index[0] = idx;
			return array; // not found
		}
		Class<?> clazz = array.getClass().getComponentType();
		ModelPart[] tmp = (ModelPart[])java.lang.reflect.Array.newInstance(clazz, array.length - 1);

		int j = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(i == idx)
				continue; // skip the item to remove
			tmp[j++] = array[i];
		}
		index[0] = idx;
		part.setParent(null);
		return tmp;
	}

	/**
	 * Moves the model part up (+1) or down(-1) in the array of model parts
	 * 
	 * @param part
	 * @param delta
	 *            - +1 or -1 (throws IllegalArgumentException of not +1 or -1)
	 * @return -1 if move was not made, else the position before the move is returned
	 */
	protected static int moveModelPart(ModelPart[] array, ModelPart part, int delta)
	{
		if(!(delta == 1 || delta == -1))
			throw new IllegalArgumentException("can only move +1 or -1");
		int index = -1; // not found (yet)
		for(int i = 0; i < array.length; i++)
			if(array[i] == part)
			{
				index = i;
				break;
			}
		if(index == -1)
			return index; // not found
		int swapIndex = index + delta;
		if(swapIndex < 0 || swapIndex >= array.length)
			return -1; // outside of range - no move

		ModelPart tmp = array[swapIndex];
		array[swapIndex] = array[index];
		array[index] = tmp;
		return index;
	}
	
}