/*******************************************************************************
 * Copyright (c) 2009, eXXcellent solutions gmbh
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 *     Achim Demelt - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.junit.internal;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.jdt.junit.TestRunListener;
import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import org.eclipse.jdt.junit.model.ITestElement.FailureTrace;
import org.eclipse.jdt.junit.model.ITestElement.Result;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.text.MessageFormat;

public class TestListener extends TestRunListener
{
	private static final Logger logger = CorePlugin.getLogger();

	private final boolean m_quiet;

	private int m_successCount;

	private int m_failCount;

	private int m_errorCount;

	private int m_ignoreCount;

	private int m_overallCount;

	private ITestRunSession m_session;

	public TestListener(boolean quiet)
	{
		this.m_quiet = quiet;
	}

	public int getErrorCount()
	{
		return m_errorCount;
	}

	public int getFailureCount()
	{
		return m_failCount;
	}

	public int getIgnoreCount()
	{
		return m_ignoreCount;
	}

	public int getOverallCount()
	{
		return m_overallCount;
	}

	public ITestRunSession getTestRunSession()
	{
		return m_session;
	}

	@Override
	public void sessionFinished(ITestRunSession session)
	{
		this.m_session = session;
		if(!m_quiet)
		{
			logger.info(Messages.TestListener_Tests_finished);
			logger.info(MessageFormat.format(Messages.TestListener_Elapsed_time,
					new Object[] { Double.valueOf(session.getElapsedTimeInSeconds()) }));
			logger.info(NLS.bind(Messages.TestListener_Total_number_of_tests, Integer.valueOf(m_overallCount)));
			logger.info(NLS.bind(Messages.TestListener_Successful_tests, Integer.valueOf(m_successCount)));
			logger.info(NLS.bind(Messages.TestListener_Failed_tests, Integer.valueOf(m_failCount)));
			logger.info(NLS.bind(Messages.TestListener_Errors, Integer.valueOf(m_errorCount)));
			logger.info(NLS.bind(Messages.TestListener_Ignored_tests, Integer.valueOf(m_ignoreCount)));
			logger.info(NLS.bind(Messages.TestListener_Overall_status, session.getTestResult(true)));
			logFailureTrace(session.getFailureTrace());
		}
	}

	@Override
	public void sessionStarted(ITestRunSession session)
	{
		if(!m_quiet)
			logger.info(NLS.bind(Messages.TestListener_Starting_test_session, session.getTestRunName()));
	}

	@Override
	public void testCaseFinished(ITestCaseElement testCaseElement)
	{
		m_overallCount++;
		Result result = testCaseElement.getTestResult(false);
		if(result == Result.ERROR)
			m_errorCount++;
		if(result == Result.FAILURE)
			m_failCount++;
		if(result == Result.IGNORED)
			m_ignoreCount++;
		if(result == Result.OK)
			m_successCount++;

		if(!m_quiet)
		{
			logger.info("  ..." + testCaseElement.getTestResult(false) //$NON-NLS-1$
					+ MessageFormat.format(
							" [{0,number,###.###}s]", new Object[] { Double.valueOf(testCaseElement.getElapsedTimeInSeconds()) })); //$NON-NLS-1$
			logFailureTrace(testCaseElement.getFailureTrace());
		}
	}

	@Override
	public void testCaseStarted(ITestCaseElement testCaseElement)
	{
		if(!m_quiet)
			logger.info(Messages.TestListener_Running_test + testCaseElement.getTestClassName()
					+ "." + testCaseElement.getTestMethodName() + "..."); //$NON-NLS-1$//$NON-NLS-2$
	}

	private void logFailureTrace(FailureTrace failure)
	{
		if(failure == null)
			return;

		if(failure.getActual() != null)
			logger.warning(NLS.bind(Messages.TestListener_Actual, failure.getActual()));
		if(failure.getExpected() != null)
			logger.warning(NLS.bind(Messages.TestListener_Expected, failure.getExpected()));
		if(failure.getTrace() != null)
			logger.warning(failure.getTrace());
	}
}
