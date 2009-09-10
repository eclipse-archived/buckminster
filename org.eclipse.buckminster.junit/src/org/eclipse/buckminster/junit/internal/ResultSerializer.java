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

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import org.eclipse.jdt.junit.model.ITestSuiteElement;
import org.eclipse.jdt.junit.model.ITestElement.FailureTrace;
import org.eclipse.jdt.junit.model.ITestElement.Result;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Serializes a given {@link ITestRunSession} into an ant-junit-like XML file. Most of the code is copied from JDT
 * JUnit's TestRunSessionSerializer class, but it only uses public API and tries to conform to the (unspecified)
 * ant-junit format.
 */
public class ResultSerializer implements XMLReader
{
	private static final String EMPTY = ""; //$NON-NLS-1$

	private static final String CDATA = "CDATA"; //$NON-NLS-1$

	private static final NumberFormat timeFormat = new DecimalFormat("0.0##", new DecimalFormatSymbols(Locale.US)); //$NON-NLS-1$ // not localized, parseable by Double.parseDouble(..)

	private static final Attributes NO_ATTS = new AttributesImpl();

	private ITestRunSession m_testRunSession;

	private ContentHandler m_contentHandler;

	private ErrorHandler m_errorHandler;

	private TestListener m_testListener;

	private String m_stdOut;

	private String m_stdErr;

	public ResultSerializer(TestListener listener, String stdout, String stderr)
	{
		this.m_testListener = listener;
		this.m_testRunSession = listener.getTestRunSession();
		this.m_stdOut = stdout;
		this.m_stdErr = stderr;
	}

	public ContentHandler getContentHandler()
	{
		return m_contentHandler;
	}

	public DTDHandler getDTDHandler()
	{
		return null;
	}

	public EntityResolver getEntityResolver()
	{
		return null;
	}

	public ErrorHandler getErrorHandler()
	{
		return m_errorHandler;
	}

	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		return false;
	}

	public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		return null;
	}

	public void parse(InputSource input) throws IOException, SAXException
	{
		if(m_contentHandler == null)
			throw new SAXException("ContentHandler missing"); //$NON-NLS-1$

		m_contentHandler.startDocument();
		handleTestRun();
		m_contentHandler.endDocument();
	}

	public void parse(String systemId) throws IOException, SAXException
	{
		// ignore
	}

	public void setContentHandler(ContentHandler handler)
	{
		this.m_contentHandler = handler;
	}

	public void setDTDHandler(DTDHandler handler)
	{
		// ignore
	}

	public void setEntityResolver(EntityResolver resolver)
	{
		// ignore
	}

	public void setErrorHandler(ErrorHandler handler)
	{
		this.m_errorHandler = handler;
	}

	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		// ignore
	}

	public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException
	{
		// ignore
	}

	private void addCDATA(AttributesImpl atts, String name, String value)
	{
		atts.addAttribute(EMPTY, EMPTY, name, CDATA, value);
	}

	private void addFailure(ITestElement testElement) throws SAXException
	{
		FailureTrace failureTrace = testElement.getFailureTrace();
		if(failureTrace != null)
		{
			AttributesImpl failureAtts = new AttributesImpl();
			String failureKind = testElement.getTestResult(false) == Result.ERROR
					? IXMLTags.NODE_ERROR
					: IXMLTags.NODE_FAILURE;
			startElement(failureKind, failureAtts);
			String expected = failureTrace.getExpected();
			String actual = failureTrace.getActual();
			if(expected != null)
			{
				startElement(IXMLTags.NODE_EXPECTED, NO_ATTS);
				m_contentHandler.characters(expected.toCharArray(), 0, expected.length());
				endElement(IXMLTags.NODE_EXPECTED);
			}
			if(actual != null)
			{
				startElement(IXMLTags.NODE_ACTUAL, NO_ATTS);
				m_contentHandler.characters(actual.toCharArray(), 0, actual.length());
				endElement(IXMLTags.NODE_ACTUAL);
			}
			String trace = failureTrace.getTrace();
			m_contentHandler.characters(trace.toCharArray(), 0, trace.length());
			endElement(failureKind);
		}
	}

	private void endElement(String name) throws SAXException
	{
		m_contentHandler.endElement(EMPTY, name, name);
	}

	private void handleTestElement(ITestElement testElement) throws SAXException
	{
		if(testElement instanceof ITestSuiteElement)
		{
			ITestSuiteElement testSuiteElement = (ITestSuiteElement)testElement;

			AttributesImpl atts = new AttributesImpl();
			addCDATA(atts, IXMLTags.ATTR_NAME, testSuiteElement.getSuiteTypeName());
			if(!Double.isNaN(testSuiteElement.getElapsedTimeInSeconds()))
				addCDATA(atts, IXMLTags.ATTR_TIME, timeFormat.format(testSuiteElement.getElapsedTimeInSeconds()));

			startElement(IXMLTags.NODE_TESTSUITE, atts);
			addFailure(testElement);

			ITestElement[] children = testSuiteElement.getChildren();
			for(int i = 0; i < children.length; i++)
			{
				handleTestElement(children[i]);
			}
			endElement(IXMLTags.NODE_TESTSUITE);

		}
		else if(testElement instanceof ITestCaseElement)
		{
			ITestCaseElement testCaseElement = (ITestCaseElement)testElement;

			AttributesImpl atts = new AttributesImpl();
			addCDATA(atts, IXMLTags.ATTR_NAME, testCaseElement.getTestMethodName());
			addCDATA(atts, IXMLTags.ATTR_CLASSNAME, testCaseElement.getTestClassName());
			if(!Double.isNaN(testCaseElement.getElapsedTimeInSeconds()))
				addCDATA(atts, IXMLTags.ATTR_TIME, timeFormat.format(testCaseElement.getElapsedTimeInSeconds()));
			if(testCaseElement.getTestResult(false) == ITestElement.Result.IGNORED)
				addCDATA(atts, IXMLTags.ATTR_IGNORED, Boolean.TRUE.toString());

			startElement(IXMLTags.NODE_TESTCASE, atts);
			addFailure(testElement);
			endElement(IXMLTags.NODE_TESTCASE);
		}
		else
		{
			throw new IllegalStateException(String.valueOf(testElement));
		}
	}

	private void handleTestRun() throws SAXException
	{
		AttributesImpl atts = new AttributesImpl();
		addCDATA(atts, IXMLTags.ATTR_NAME, m_testRunSession.getTestRunName());
		addCDATA(atts, IXMLTags.ATTR_TESTS, String.valueOf(m_testListener.getOverallCount()));
		addCDATA(atts, IXMLTags.ATTR_ERRORS, String.valueOf(m_testListener.getErrorCount()));
		addCDATA(atts, IXMLTags.ATTR_FAILURES, String.valueOf(m_testListener.getFailureCount()));
		addCDATA(atts, IXMLTags.ATTR_IGNORED, String.valueOf(m_testListener.getIgnoreCount()));
		if(!Double.isNaN(m_testRunSession.getElapsedTimeInSeconds()))
			addCDATA(atts, IXMLTags.ATTR_TIME, timeFormat.format(m_testRunSession.getElapsedTimeInSeconds()));
		startElement(IXMLTags.NODE_TESTSUITES, atts);

		for(ITestElement element : m_testRunSession.getChildren())
			handleTestElement(element);

		writeStdOut();
		writeStdErr();

		endElement(IXMLTags.NODE_TESTSUITES);
	}

	private void startElement(String name, Attributes atts) throws SAXException
	{
		m_contentHandler.startElement(EMPTY, name, name, atts);
	}

	private void writeStdErr() throws SAXException
	{
		if(m_stdErr != null && m_stdErr.length() > 0)
		{
			startElement(IXMLTags.NODE_SYSTEM_ERR, NO_ATTS);
			m_contentHandler.characters(m_stdErr.toCharArray(), 0, m_stdErr.length());
			endElement(IXMLTags.NODE_SYSTEM_ERR);
		}
	}

	private void writeStdOut() throws SAXException
	{
		if(m_stdOut != null && m_stdOut.length() > 0)
		{
			startElement(IXMLTags.NODE_SYSTEM_OUT, NO_ATTS);
			m_contentHandler.characters(m_stdOut.toCharArray(), 0, m_stdOut.length());
			endElement(IXMLTags.NODE_SYSTEM_OUT);
		}
	}
}
