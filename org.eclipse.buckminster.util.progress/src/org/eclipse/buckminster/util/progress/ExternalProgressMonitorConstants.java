/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.util.progress;

/**
 * Constants used in the ExternalProcessMonitor protocol.
 * @author henrik.lindberg@cloudsmith.com
 *
 */
public interface ExternalProgressMonitorConstants {
	public static final char BEGIN = 'B';
	public static final char CLEAR_BLOCKED = 'x';
	public static final char DONE = 'D';
	public static final char INTERNAL_WORKED = 'w';
	public static final char WORKED = 'W';
	public static final char SET_BLOCKED = 'X';
	public static final char SET_CANCELED = 'C';
	public static final char SET_TASK_NAME = 'N';
	public static final char SUB_TASK = 'S';
	public static final char WORKED_ONE = '1';
	public static final char CHECK_CANCEL = '?';

	/**
	 * Communication prefix - lines received from proxy can be prefixed. By default
	 * DEFAULT_PREFIX is used. A setup can use some other prefix if required.
	 * Lines without a matching prefix are simply filtered out.
	 */
	public static final String DEFAULT_PREFIX = "";

	/**
	 * Special command - if passed this symbolic name as the process to execute,
	 * the ExternalProcessMonitor will not exec. This is used primarily to be able to test
	 * without launching external process.
	 */
	public static final String LOOPBACK_COMMAND = "---loopback---";

}
