/* 
 * Copyright (c) 2004-2007 QOS.ch
 * All rights reserved.
 * 
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 * 
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 * 
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.slf4j.impl;

import org.eclipse.osgi.util.NLS;
import org.slf4j.ILoggerFactory;
import org.slf4j.Messages;

public class StaticLoggerBinder
{
	public static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	private final ILoggerFactory m_loggerFactory;

	private StaticLoggerBinder()
	{
		m_loggerFactory = ExtensionPointLoader.loadLoggerFactory();
	}

	public ILoggerFactory getLoggerFactory()
	{
		if(m_loggerFactory == null)
			throw new UnsupportedOperationException(NLS.bind(Messages.no_0_has_been_provided, "ILoggerFactory")); //$NON-NLS-1$
		return m_loggerFactory;
	}

	public String getLoggerFactoryClassStr()
	{
		return m_loggerFactory == null
				? StaticLoggerBinder.class.getName()
				: m_loggerFactory.getClass().getName();
	}
}
