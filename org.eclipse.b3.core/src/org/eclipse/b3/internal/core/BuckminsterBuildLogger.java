/*******************************************************************************
 * Copyright (c) 2009, Cloudsmith Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc - initial API and implementation
 *******************************************************************************/

package org.eclipse.b3.internal.core;
import org.eclipse.b3.provisional.core.ILogger;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * Temporary class that adapts the ILogger interface to a Buckminster Logger.
 * The intent is to provide ILogger as an OSGi service. This class makes it possible to
 * write code using the wanted logger interface.
 *
 */
public class BuckminsterBuildLogger extends Logger implements ILogger{

	public BuckminsterBuildLogger(Bundle bundle) {
		super(bundle);
	}

	@Override
	public void debug(String msg, Object... args) {
		if(isDebugEnabled())
			super.log(ILogger.DEBUG, NLS.bind(msg, args));
	}

	@Override
	public void debug(Throwable t, String msg, Object... args) {
		if(isDebugEnabled())
			super.log(ILogger.DEBUG, t, NLS.bind(msg, args));	
	}

	@Override
	public void error(String msg, Object... args) {
		if(isErrorEnabled())
			super.log(ILogger.ERROR, NLS.bind(msg, args));		
	}

	@Override
	public void error(Throwable t, String msg, Object... args) {
		if(isErrorEnabled())
			super.log(ILogger.ERROR, t, NLS.bind(msg, args));
	}

	@Override
	public void info(String msg, Object... args) {
		if(isInfoEnabled())
			super.log(ILogger.INFO, NLS.bind(msg, args));
	}

	@Override
	public void info(Throwable t, String msg, Object... args) {
		if(isInfoEnabled())
			super.log(ILogger.INFO, t, NLS.bind(msg, args));
	}

	@Override
	public void log(int level, String msg, Object... args) { 
			super.log(level, NLS.bind(msg, args));
	}

	@Override
	public void log(int level, Throwable t, String msg, Object... args) {
		super.log(level,t, NLS.bind(msg, args));		
	}

	@Override
	public void warning(String msg, Object... args) {
		if(isWarningEnabled())
			super.log(ILogger.WARNING, NLS.bind(msg, args));
	}

	@Override
	public void warning(Throwable t, String msg, Object... args) {
		if(isInfoEnabled())
			super.log(ILogger.WARNING, t, NLS.bind(msg, args));
	}

}
