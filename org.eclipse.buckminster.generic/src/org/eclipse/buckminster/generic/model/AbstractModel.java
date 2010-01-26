/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

/**
 * AbstractModel handles delegation to an instance of PropertyChangeSupport. This makes it possible to handle an
 * extending class as a Model in a Model-View relationship, and using eclipse data binding in particular.
 * 
 * Abstract Model also has a basic implementation of IAdaptable returning the instance if it is an instance of the
 * requested class.
 * 
 * @author Henrik Lindberg
 */
public class AbstractModel implements IPropertyChange, IAdaptable
{
	private final PropertyChangeSupport m_changeSupport;

	public AbstractModel()
	{
		m_changeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		m_changeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		m_changeSupport.addPropertyChangeListener(propertyName, listener);

	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter)
	{
		if(adapter.isInstance(this))
			return this;
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public PropertyChangeListener[] getPropertyChangeListeners()
	{
		return m_changeSupport.getPropertyChangeListeners();
	}

	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName)
	{
		return m_changeSupport.getPropertyChangeListeners(propertyName);
	}

	public boolean hasListeners(String propertyName)
	{
		return m_changeSupport.hasListeners(propertyName);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		m_changeSupport.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		m_changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	protected void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue)
	{
		m_changeSupport.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	protected void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue)
	{
		m_changeSupport.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	protected void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue)
	{
		m_changeSupport.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	protected void firePropertyChange(PropertyChangeEvent evt)
	{
		m_changeSupport.firePropertyChange(evt);
	}

	protected void firePropertyChange(String propertyName, boolean oldValue, boolean newValue)
	{
		m_changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	protected void firePropertyChange(String propertyName, int oldValue, int newValue)
	{
		m_changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
	{
		m_changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

}
