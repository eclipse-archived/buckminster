/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.test.junit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.mail.internet.MimeUtility;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.test.junit.TestCommand.TestLocationResolver.ResolutionException;
import org.eclipse.buckminster.test.junit.TestCommand.TestLocationResolver.TestSuiteDescriptor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * <p>
 * JUnit testing support for Buckminster.
 * </p>
 * <p>
 * The class implements the actual execution of the test.
 * </p>
 * 
 * @author Michal Rùžièka
 */
public class TestRunner
{
	/**
	 * <p>
	 * A test result holder helper class. The class implements the {@link org.eclipse.buckminster.sax.ISaxableElement}
	 * interface and is thus capable of emitting the result in a form of SAX event stream.
	 * </p>
	 */
	public static class TestSuiteResult implements TestListener, ISaxableElement
	{
		/** the testsuites element for the aggregate document */
		public static final String TESTSUITES = "testsuites";

		/** constant for unnamed testsuites/cases */
		public static final String UNKNOWN = "unknown";

		/** the testsuite element */
		public static final String TESTSUITE = "testsuite";

		/** name attribute for testsuite and testcase elements */
		public static final String ATTR_NAME = "name";

		/** time attribute for testsuite and testcase elements */
		public static final String ATTR_TIME = "time";

		/** timestamp attribute for testsuite and testcase elements */
		public static final String ATTR_TIMESTAMP = "timestamp";

		/** errors attribute for testsuite elements */
		public static final String ATTR_ERRORS = "errors";

		/** failures attribute for testsuite elements */
		public static final String ATTR_FAILURES = "failures";

		/** tests attribute for testsuite elements */
		public static final String ATTR_TESTS = "tests";

		/** the system-err element */
		public static final String SYSTEM_ERR = "system-err";

		/** the system-out element */
		public static final String SYSTEM_OUT = "system-out";

		protected static class ExceptionWrapper implements ISaxableElement {
			/** the error element */
			public static final String ERROR = "error";
		
			/** the failure element */
			public static final String FAILURE = "failure";

			/** type attribute for error and failure elements */
			public static final String ATTR_TYPE = "type";

			/** message attribute for error and failure elements */
			public static final String ATTR_MESSAGE = "message";

			private String m_type;
			private Throwable m_exception;

			public ExceptionWrapper(String type, Throwable exception) {
				m_type = type;
				m_exception = exception;
			}

			public String getDefaultTag() {
				return m_type;
			}

			protected static char[] getStackTrace(Throwable t)
			{
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw, true);

				t.printStackTrace(pw);
				pw.close();

				StringBuffer buf = sw.getBuffer();
				char[] target = new char[buf.length()];

				buf.getChars(0, buf.length(), target, 0);

				return target;
			}

			public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
					throws SAXException
			{
				String qName = Utils.makeQualifiedName(prefix, localName);
				AttributesImpl attrs = new AttributesImpl();
				String message = m_exception.getMessage();
				char[] text = getStackTrace(m_exception);

				if(message != null && message.length() > 0)
				{
					Utils.addAttribute(attrs, ATTR_MESSAGE, message);
				}
				Utils.addAttribute(attrs, ATTR_TYPE, m_exception.getClass().getName());

				receiver.startElement(namespace, localName, qName, attrs);
				receiver.characters(text, 0, text.length);
				receiver.endElement(namespace, localName, qName);
			}
		}

		protected static class OutputWrapper implements ISaxableElement {
			private String m_type;
			private byte[] m_data;

			public OutputWrapper(String type, byte[] data) {
				m_type = type;
				m_data = data;
			}

			public String getDefaultTag() {
				return m_type;
			}

			public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
					throws SAXException
			{
				String qName = Utils.makeQualifiedName(prefix, localName);
				char[] encoded;
				try
				{
					encoded = MimeUtility.encodeText(new String(m_data), null, null).toCharArray();
				}
				catch(UnsupportedEncodingException e)
				{
					throw new RuntimeException("Default charset not supported ?!?", e);
				}

				receiver.startElement(namespace, localName, qName, EMPTY_ATTRIBUTES);
				receiver.characters(encoded, 0, encoded.length);
				receiver.endElement(namespace, localName, qName);
			}
		}

		/**
		 * <p>
		 * A single test case result holder helper class. The class implements the
		 * {@link org.eclipse.buckminster.sax.ISaxableElement} interface and is thus capable of emitting the result in a
		 * form of SAX event stream.
		 * </p>
		 */
		protected static class TestCaseResult implements ISaxableElement
		{
			/** the testcase element */
			public static final String TESTCASE = "testcase";

			/** classname attribute for testcase elements */
			public static final String ATTR_CLASSNAME = "classname";

			/** name attribute for testcase elements */
			@SuppressWarnings("hiding")
			public static final String ATTR_NAME = TestSuiteResult.ATTR_NAME;
		
			/** time attribute for testcase elements */
			@SuppressWarnings("hiding")
			public static final String ATTR_TIME = TestSuiteResult.ATTR_TIME;
		
			/** timestamp attribute for testcase elements */
			@SuppressWarnings("hiding")
			public static final String ATTR_TIMESTAMP =  TestSuiteResult.ATTR_TIMESTAMP;

			// back reference to the Test Object 
			protected Test m_test;

			protected long m_startTimestamp;
			protected long m_endTimestamp;

			protected List<ISaxableElement> m_additionalInfo = new LinkedList<ISaxableElement>();

			public TestCaseResult(Test test)
			{
				m_endTimestamp = m_startTimestamp = System.currentTimeMillis();
				m_test = test;
			}

			public void setEndTimestamp(long stamp)
			{
				if(stamp > m_startTimestamp)
					m_endTimestamp = stamp;
			}

			public void addInfo(ISaxableElement child)
			{
				m_additionalInfo.add(child);
			}

			public Test getTest()
			{
				return m_test;
			}

			public String getDefaultTag()
			{
				return TESTCASE;
			}

			public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
					throws SAXException
			{
				String qName = Utils.makeQualifiedName(prefix, localName);
				AttributesImpl attrs = new AttributesImpl();

				Utils.addAttribute(attrs, ATTR_NAME, getTestCaseName(m_test));
				// a TestSuite can contain Tests from multiple classes,
				// even tests with the same name - disambiguate them.
				Utils.addAttribute(attrs, ATTR_CLASSNAME, m_test.getClass().getName());

				Utils.addAttribute(attrs, ATTR_TIMESTAMP, "" + m_startTimestamp);
				Utils.addAttribute(attrs, ATTR_TIME, "" + ((m_endTimestamp - m_startTimestamp) / 1000.0));

				receiver.startElement(namespace, localName, qName, attrs);

				for(ISaxableElement info : m_additionalInfo) {
					info.toSax(receiver, namespace, prefix, info.getDefaultTag());
				}

				receiver.endElement(namespace, localName, qName);
			}
		}

		/**
		 * <p>
		 * A {@link junit.framework.TestResult} subclass that is aware of being monitored by a
		 * {@link org.eclipse.core.runtime.IProgressMonitor}.
		 * </p>
		 */
		protected class MonitoredTestResult extends TestResult
		{
			@Override
			public synchronized boolean shouldStop()
			{
				return super.shouldStop() || m_monitor.isCanceled();
			}
		}

		/** The name of the testsuite. */
		protected String m_testSuiteName;

		/** The JUnit result of the test suite. */
		protected TestResult m_testResult;

		/** The timestamp of the start of the testsuite execution. */
		protected long m_startTimestamp;

		/** The timestamp of the end of the testsuite execution. */
		protected long m_endTimestamp;

		/** Map of running tests. */
		protected Map<Test, TestCaseResult> m_runningTests = new HashMap<Test, TestCaseResult>();

		/** Map of failed tests. */
		protected Map<Test, TestCaseResult> m_failedTests = new HashMap<Test, TestCaseResult>();

		/** Map of finished tests. */
		protected Map<Test, TestCaseResult> m_finishedTests = new HashMap<Test, TestCaseResult>();

		/** Throwable thrown by the test suite execution code. */
		protected Throwable m_error;

		/** System.out captured during the execution of the test suite. */
		protected byte[] m_out;

		/** System.err captured during the execution of the test suite. */
		protected byte[] m_err;

		/** A progress monitor monitoring the test suite execution. */
		protected IProgressMonitor m_monitor;


		public TestSuiteResult(String testSuiteName, IProgressMonitor monitor)
		{
			m_testSuiteName = testSuiteName;
			m_monitor = monitor;
		}

		protected static String getTestCaseName(Test test)
		{
			try
			{
				Class<?> clazz = test.getClass();
				Method getNameMethod;

				try
				{
					getNameMethod = clazz.getMethod("getName");
				}
				catch(NoSuchMethodException e)
				{
					getNameMethod = clazz.getMethod("name");
				}

				if(getNameMethod.getReturnType() == String.class)
				{
					return (String)getNameMethod.invoke(test);
				}
			}
			catch(Throwable e)
			{
				// ignore
			}

			return UNKNOWN;
		}

		public void startTest(Test test)
		{
			long timestamp = System.currentTimeMillis();
			TestCaseResult result = m_runningTests.get(test);

			// just return if the test is already running
			if(result != null)
				return;

			result = m_failedTests.get(test);

			// if the test is failed then finish it
			if(result != null)
			{
				m_failedTests.remove(test);
				result.setEndTimestamp(timestamp);
				m_finishedTests.put(test, result);
			}

			m_runningTests.put(test, new TestCaseResult(test));
		}

		protected TestCaseResult getTestCaseResult(Test test) throws NoSuchElementException
		{
			TestCaseResult result = m_runningTests.get(test);

			if(result != null)
			{
				m_runningTests.remove(test);
				return result;
			}

			result = m_failedTests.get(test);

			if(result != null)
			{
				m_failedTests.remove(test);
				return result;
			}

			if(m_finishedTests.get(test) != null)
				return null;

			// throw an exception if the test is unknown
			throw new NoSuchElementException();
		}

		public void endTest(Test test)
		{
			long timestamp = System.currentTimeMillis();
			TestCaseResult result;

			try
			{
				result = getTestCaseResult(test);

				// if the test is already finished then ignore this event
				if(result == null)
					return;

				result.setEndTimestamp(timestamp);
			}
			catch(NoSuchElementException e)
			{
				// Fix for bug #5637 - if a junit.extensions.TestSetup is
				// used and throws an exception during setUp then startTest
				// would never have been called
				result = new TestCaseResult(test);
			}

			m_finishedTests.put(test, result);
		}

		protected void addException(Test test, String type, Throwable t)
		{
			TestCaseResult result = m_runningTests.get(test);

			// ignore this event if the test is not running
			if(result == null)
				return;

			m_runningTests.remove(test);
			result.addInfo(new ExceptionWrapper(type, t));
			m_failedTests.put(test, result);
		}

		public void addFailure(Test test, AssertionFailedError e)
		{
			addException(test, ExceptionWrapper.FAILURE, e);
		}

		public void addError(Test test, Throwable t)
		{
			addException(test, ExceptionWrapper.ERROR, t);
		}

		protected void addError(Throwable t)
		{
			if(m_error == null)
			{
				m_error = t;
			}
		}

		public void addOutput(byte[] out, byte[] err)
		{
			m_out = out;
			m_err = err;
		}

		/**
		 * <p>
		 * Run the whole testsuite.
		 * </p>
		 */
		public void run(Test suite)
		{
			try
			{
				m_testResult = new MonitoredTestResult();

				m_testResult.addListener(this);

				m_startTimestamp = System.currentTimeMillis();

				try
				{
					try
					{
						if(suite != null)
							suite.run(m_testResult);
					}
					finally
					{
						if(!m_runningTests.isEmpty())
						{
							for(TestCaseResult result : m_runningTests.values())
							{
								// Note that this moves the test from running to failed 
								addError(result.getTest(), new Exception("Test not completed"));
							}

							throw new RuntimeException("Tests not completed");
						}
					}
				}
				finally
				{
					m_endTimestamp = System.currentTimeMillis();

					for(TestCaseResult result : m_failedTests.values())
					{
						Test test = result.getTest();

						m_failedTests.remove(test);
						result.setEndTimestamp(m_endTimestamp);
						m_finishedTests.put(test, result);
					}
				}
			}
			catch(Throwable t)
			{
				addError(t);
			}
		}

		public String getDefaultTag()
		{
			return TESTSUITE;
		}

		public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
		{
			if(m_testResult == null)
			{
				throw new IllegalStateException("Test suite not run: " + m_testSuiteName);
			}

			String qName = Utils.makeQualifiedName(prefix, localName);
			AttributesImpl attrs = new AttributesImpl();

			Utils.addAttribute(attrs, ATTR_NAME, m_testSuiteName);
			Utils.addAttribute(attrs, ATTR_TESTS, "" + m_testResult.runCount());
			Utils.addAttribute(attrs, ATTR_FAILURES, "" + m_testResult.failureCount());
			Utils.addAttribute(attrs, ATTR_ERRORS, "" + m_testResult.errorCount());
			Utils.addAttribute(attrs, ATTR_TIMESTAMP, "" + m_startTimestamp);
			Utils.addAttribute(attrs, ATTR_TIME, "" + ((m_endTimestamp - m_startTimestamp) / 1000.0));

			receiver.startElement(namespace, localName, qName, attrs);

			for(TestCaseResult result : m_finishedTests.values())
			{
				result.toSax(receiver, namespace, prefix, result.getDefaultTag());
			}

			if(m_error != null)
			{
				ISaxableElement element = new ExceptionWrapper(ExceptionWrapper.ERROR, m_error);

				element.toSax(receiver, namespace, prefix, element.getDefaultTag());
			}

			if(m_out != null)
			{
				ISaxableElement element = new OutputWrapper(SYSTEM_OUT, m_out);

				element.toSax(receiver, namespace, prefix, element.getDefaultTag());
			}

			if(m_err != null)
			{
				ISaxableElement element = new OutputWrapper(SYSTEM_ERR, m_err);

				element.toSax(receiver, namespace, prefix, element.getDefaultTag());
			}

			receiver.endElement(namespace, localName, qName);
		}

		/**
		 * <p>
		 * Emits SAX events opening the results report document.
		 * </p>
		 * 
		 * @param report
		 *            the SAX handler to emit the events to
		 */ 
		public static void startReport(ContentHandler report) throws SAXException
		{
			report.startDocument();
			report.startElement(null, null, TestSuiteResult.TESTSUITES, ISaxableElement.EMPTY_ATTRIBUTES);
		}

		/**
		 * <p>
		 * Emits SAX events closing the results report document.
		 * </p>
		 * 
		 * @param report
		 *            the SAX handler to emit the events to
		 */ 
		public static void endReport(ContentHandler report) throws SAXException
		{
			report.endElement(null, null, TestSuiteResult.TESTSUITES);
			report.endDocument();
		}
	}

	protected ByteArrayOutputStream m_outStreamBuffer;
	protected ByteArrayOutputStream m_errStreamBuffer;

	protected PrintStream m_bufferedOut;
	protected PrintStream m_bufferedErr;

	protected PrintStream m_originalSystemOut;
	protected PrintStream m_originalSystemErr;

	protected PrintStream m_originalLoggerOut;
	protected PrintStream m_originalLoggerErr;

	public TestRunner()
	{
		m_outStreamBuffer = new ByteArrayOutputStream();
		m_errStreamBuffer = new ByteArrayOutputStream();
	}

	@SuppressWarnings("unchecked")
	protected static Class<? extends TestCase> getTestCaseClass(Class clazz)
	{
		return clazz;
	}

	protected void bufferStreams()
	{
		if(m_bufferedOut != null)
			return;

		m_outStreamBuffer.reset();
		m_errStreamBuffer.reset();

		m_bufferedOut = new PrintStream(m_outStreamBuffer);
		m_bufferedErr = new PrintStream(m_errStreamBuffer);

		m_originalSystemOut = System.out;
		m_originalSystemErr = System.err;

		m_originalLoggerOut = Logger.getOutStream();
		m_originalLoggerErr = Logger.getErrStream();

		System.setOut(m_bufferedOut);
		System.setErr(m_bufferedErr);

		Logger.setOutStream(m_bufferedOut);
		Logger.setErrStream(m_bufferedErr);
	}

	protected void unbufferStreams()
	{
		if(m_bufferedOut == null)
			return;

		System.setOut(m_originalSystemOut);
		System.setErr(m_originalSystemErr);

		Logger.setOutStream(m_originalLoggerOut);
		Logger.setErrStream(m_originalLoggerErr);

		m_bufferedOut.close();
		m_bufferedErr.close();

		m_bufferedOut = null;
		m_bufferedErr = null;
	}

	/**
	 * <p>
	 * Executes the specified <code>testSuite</code>.
	 * </p>
	 * 
	 * @param testSuite
	 *            the test suite to execute
	 * @param monitor
	 *            a monitor that monitors the execution (used only for testing if the cancellation of the test execution
	 *            was requested by means of calling the
	 *            {@link org.eclipse.core.runtime.IProgressMonitor#isCanceled() monitor.isCanceled()})
	 * @return <code>TestSuiteResult</code> holding the result of the <code>testSuite</code> execution.
	 */
	public TestSuiteResult run(TestSuiteDescriptor testSuite, IProgressMonitor monitor)
	{
		TestSuiteResult result = new TestSuiteResult(testSuite.getSuiteName(), monitor);
		 monitor.isCanceled();
		Test suite = null;

		bufferStreams();

		try
		{
			try
			{
				Class<?> suiteClass = testSuite.getSuiteClass();

				if((suiteClass.getModifiers() & (Modifier.INTERFACE | Modifier.ABSTRACT)) != 0)
				{
					throw new InstantiationException();
				}

				{
					Method suiteMethod = null;

					try
					{
						// Check if there is a suite method.
						suiteMethod = suiteClass.getMethod("suite", new Class[0]);
					}
					catch(NoSuchMethodException e)
					{
						// No appropriate suite method found. We don't report any
						// error here since it might be perfectly normal.
					}

					if(suiteMethod != null)
					{
						// If there is a suite method available, then try
						// to extract the suite from it. If there is an error
						// here it will be caught below and reported.
						suite = (Test)suiteMethod.invoke(null);
						if(suite == null)
							throw new Exception("suite method returned null");
					}
					else
					{
						// Try to extract a test suite automatically; this
						// will generate warnings if the class is not
						// a suitable Test.
						suite = new TestSuite(getTestCaseClass(suiteClass));
					}
				}
			}
			finally
			{
				result.run(suite);
			}
		}
		catch(Throwable t)
		{
			result.addError((t instanceof ResolutionException) ? t.getCause() : new Exception("Failed to create test suite", t));
		}

		unbufferStreams();

		result.addOutput(m_outStreamBuffer.toByteArray(), m_errStreamBuffer.toByteArray());

		return result;
	}
}
