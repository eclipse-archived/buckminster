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

package org.eclipse.b3.provisional.core;

import org.eclipse.core.runtime.IStatus;

public interface ILogger {
	public static final int SILENT = IStatus.CANCEL; // We use this constant to
														// avoid collisions

	public static final int DEBUG = IStatus.OK;

	public static final int ERROR = IStatus.ERROR;

	public static final int INFO = IStatus.INFO;

	public static final int WARNING = IStatus.WARNING;

	public void debug(String msg, Object... args);

	public void debug(Throwable t, String msg, Object... args);

	public void error(String msg, Object... args);

	public void error(Throwable t, String msg, Object... args);

	public void info(String msg, Object... args);

	public void info(Throwable t, String msg, Object... args);

	public boolean isDebugEnabled();

	public boolean isErrorEnabled();

	public boolean isInfoEnabled();

	public boolean isWarningEnabled();

	public void log(int level, String msg, Object... args);

	public void log(int level, Throwable t, String msg, Object... args);

	public void warning(String msg, Object... args);

	public void warning(Throwable t, String msg, Object... args);
}
