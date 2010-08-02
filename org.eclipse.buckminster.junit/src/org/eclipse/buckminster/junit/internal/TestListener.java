/*******************************************************************************
 * Copyright (c) 2009, eXXcellent solutions gmbh and others
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 *     Achim Demelt - initial API and implementation
 *     Matthias Kappeller - Bug 321064 - No JUnit TestReport created for huge report files
 *******************************************************************************/
package org.eclipse.buckminster.junit.internal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.jdt.junit.TestRunListener;
import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestElement.FailureTrace;
import org.eclipse.jdt.junit.model.ITestElement.Result;
import org.eclipse.jdt.junit.model.ITestRunSession;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.text.MessageFormat;

public class TestListener extends TestRunListener {
	private static final Logger logger = CorePlugin.getLogger();

	private final boolean quiet;

	private int successCount;

	private int failCount;

	private int errorCount;

	private int ignoreCount;

	private int overallCount;

	private final CountDownLatch latch = new CountDownLatch(1);

	private ITestRunSession session;

	public TestListener(boolean quiet) {
		this.quiet = quiet;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public int getFailureCount() {
		return failCount;
	}

	public int getIgnoreCount() {
		return ignoreCount;
	}

	public int getOverallCount() {
		return overallCount;
	}

	/**
	 * @return the {@link ITestRunSession} after it has completed. Otherwise
	 *         <code>null</code> will be returned.
	 * @see #waitForFinish(int, TimeUnit)
	 */
	public ITestRunSession getTestRunSession() {
		return session;
	}

	@Override
	public void sessionFinished(ITestRunSession testSession) {
		this.session = testSession;
		latch.countDown();
		if (!quiet) {
			logger.info(Messages.TestListener_Tests_finished);
			logger.info(MessageFormat.format(Messages.TestListener_Elapsed_time, new Object[] { Double.valueOf(session.getElapsedTimeInSeconds()) }));
			logger.info(NLS.bind(Messages.TestListener_Total_number_of_tests, Integer.valueOf(overallCount)));
			logger.info(NLS.bind(Messages.TestListener_Successful_tests, Integer.valueOf(successCount)));
			logger.info(NLS.bind(Messages.TestListener_Failed_tests, Integer.valueOf(failCount)));
			logger.info(NLS.bind(Messages.TestListener_Errors, Integer.valueOf(errorCount)));
			logger.info(NLS.bind(Messages.TestListener_Ignored_tests, Integer.valueOf(ignoreCount)));
			logger.info(NLS.bind(Messages.TestListener_Overall_status, session.getTestResult(true)));
			logFailureTrace(session.getFailureTrace());
		}
	}

	@Override
	public void sessionStarted(ITestRunSession testSession) {
		if (!quiet)
			logger.info(NLS.bind(Messages.TestListener_Starting_test_session, testSession.getTestRunName()));
	}

	@Override
	public void testCaseFinished(ITestCaseElement testCaseElement) {
		overallCount++;
		Result result = testCaseElement.getTestResult(false);
		if (result == Result.ERROR)
			errorCount++;
		if (result == Result.FAILURE)
			failCount++;
		if (result == Result.IGNORED)
			ignoreCount++;
		if (result == Result.OK)
			successCount++;

		if (!quiet) {
			logger.info("  ..." + testCaseElement.getTestResult(false) //$NON-NLS-1$
					+ MessageFormat.format(" [{0,number,###.###}s]", new Object[] { Double.valueOf(testCaseElement.getElapsedTimeInSeconds()) })); //$NON-NLS-1$
			logFailureTrace(testCaseElement.getFailureTrace());
		}
	}

	@Override
	public void testCaseStarted(ITestCaseElement testCaseElement) {
		if (!quiet)
			logger.info(Messages.TestListener_Running_test + testCaseElement.getTestClassName() + "." + testCaseElement.getTestMethodName() + "..."); //$NON-NLS-1$//$NON-NLS-2$
	}

	public void waitForFinish(int duration, TimeUnit timeUnit) throws InterruptedException {
		latch.await(duration, timeUnit);
	}

	private void logFailureTrace(FailureTrace failure) {
		if (failure == null)
			return;

		if (failure.getActual() != null)
			logger.warning(NLS.bind(Messages.TestListener_Actual, failure.getActual()));
		if (failure.getExpected() != null)
			logger.warning(NLS.bind(Messages.TestListener_Expected, failure.getExpected()));
		if (failure.getTrace() != null)
			logger.warning(failure.getTrace());
	}
}
