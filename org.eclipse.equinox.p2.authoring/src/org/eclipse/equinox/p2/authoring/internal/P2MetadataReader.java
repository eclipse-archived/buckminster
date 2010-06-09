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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.core.helpers.LogHelper;
import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.p2.metadata.repository.Activator;
import org.eclipse.equinox.internal.p2.metadata.repository.Messages;
import org.eclipse.equinox.internal.p2.metadata.repository.io.MetadataParser;
import org.eclipse.equinox.internal.p2.persistence.XMLWriter;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * P2 Metadata Reader used to centralize reading of P2 metadata as API is likely to change.
 * The InstallableUnit reader reuses the MetadataRepository handler for InstallableUnit but in an 
 * ugly way.
 * 
 * TODO: The format, parser and writer should either be private, and use its own impl of
 * the IInstallableUnit interface for the sole purpose of editing, or the Metadata API should
 * allow creation of mutable instances.
 * 
 * @author Henrik Lindberg
 */
@SuppressWarnings("restriction")
public class P2MetadataReader implements P2MetadataConstants
{

	/**
	 * Reads metadata from the given stream, and returns the contained array
	 * of abstract metadata repositories.
	 * This method performs buffering, and closes the stream when finished.
	 */
	public static InstallableUnit readInstallableUnit(URL location, InputStream input, IProgressMonitor monitor) throws ProvisionException {
		BufferedInputStream bufferedInput = null;
		try {
			try {
				bufferedInput = new BufferedInputStream(input);

				Parser repositoryParser = new Parser(Activator.getContext(), Activator.ID);
				repositoryParser.parse(input, monitor);
				IStatus result = repositoryParser.getStatus();
				switch (result.getSeverity()) {
					case IStatus.CANCEL :
						throw new OperationCanceledException();
					case IStatus.ERROR :
						throw new ProvisionException(result);
					case IStatus.WARNING :
					case IStatus.INFO :
						LogHelper.log(result);
				}
				return repositoryParser.getInstallableUnit();
			} 
			catch(Exception e)
			{
				e.printStackTrace(); // REMOVE ENTIRE CATCH
				return null;
			}
			finally {
				if (bufferedInput != null)
					bufferedInput.close();
			}
		} catch (IOException ioe) {
			String msg = NLS.bind(Messages.io_failedRead, location);
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID, ProvisionException.REPOSITORY_FAILED_READ, msg, ioe));
		}
	}

	private interface XMLConstants extends org.eclipse.equinox.internal.p2.metadata.repository.io.XMLConstants {

		// Constants defining the structure of the XML for a MetadataRepository

		// A format version number for metadata repository XML.
		public static final String XML_VERSION = "1.0.0"; //$NON-NLS-1$
		public static final Version CURRENT_VERSION = new Version(XML_VERSION);
		public static final VersionRange XML_TOLERANCE = new VersionRange(CURRENT_VERSION, true, new Version(2, 0, 0), false);

		// Constants for processing Instructions
		public static final String PI_IU_TARGET = "InstallableUnit"; //$NON-NLS-1$

		// Constants for metadata IU elements
		public static final String IU_ELEMENT = "installable"; //$NON-NLS-1$

	}

	@SuppressWarnings("unchecked")
	protected XMLWriter.ProcessingInstruction[] createPI(Class iuClass) {
		//TODO We should remove this processing instruction, but currently old clients rely on this. See bug 210450.
		return new XMLWriter.ProcessingInstruction[] {XMLWriter.ProcessingInstruction.makeClassVersionInstruction(XMLConstants.PI_IU_TARGET, iuClass, XMLConstants.CURRENT_VERSION)};
	}


	/*
	 * 	Parser for the contents of a installable unit,
	 * 	as written by the Writer class.
	 */
	private static class Parser extends MetadataParser implements XMLConstants {

		private InstallableUnit theInstallableUnit = null;

		public Parser(BundleContext context, String bundleId) {
			super(context, bundleId);
		}

		public synchronized void parse(InputStream stream, IProgressMonitor monitor) throws IOException {
			this.status = null;
			setProgressMonitor(monitor);
			monitor.beginTask("Loading installable unit", IProgressMonitor.UNKNOWN);
			try {
				// TODO: currently not caching the parser since we make no assumptions
				//		 or restrictions on concurrent parsing
				getParser();
				IUHandler iuHandler = new IUHandler();
				xmlReader.setContentHandler(new IUDocHandler(IU_ELEMENT, iuHandler));
				xmlReader.parse(new InputSource(stream));
				if (isValidXML()) {
					theInstallableUnit = iuHandler.getInstallableUnit();
				}
			} catch (SAXException e) {
				throw new IOException(e.getMessage());
			} catch (ParserConfigurationException e) {
				throw new IOException(e.getMessage());
			} finally {
				monitor.done();
				stream.close();
			}
		}

		public InstallableUnit getInstallableUnit() {
			return theInstallableUnit;
		}

		@Override
		protected Object getRootObject() {
			return getInstallableUnit();
		}

		private final class IUDocHandler extends DocHandler {

			public IUDocHandler(String rootName, RootHandler rootHandler) {
				super(rootName, rootHandler);
			}

			@Override
			public void processingInstruction(String target, String data) throws SAXException {
				if (PI_IU_TARGET.equals(target)) {
					Version repositoryVersion = extractPIVersion(target, data);
					if (!P2MetadataReader.XMLConstants.XML_TOLERANCE.isIncluded(repositoryVersion)) {
						throw new SAXException(NLS.bind(Messages.io_IncompatibleVersion, repositoryVersion, P2MetadataReader.XMLConstants.XML_TOLERANCE));
					}
				}
			}

		}

		private final class IUHandler extends RootHandler {
			private InstallableUnitHandler unitHandler = null;
			ArrayList<InstallableUnitDescription> units = null;

			private InstallableUnit installableUnit = null;

			public IUHandler() {
				super();
			}

			public InstallableUnit getInstallableUnit() {
				return this.installableUnit;
			}

			@Override
			protected void handleRootAttributes(Attributes attributes) {
				// root element *is* the Installable Unit - the default parser is from
				// the meta data repository parser, and it expects to parse a number of
				// units, returning InstallableUnitDescription instances in a List.
			}

			@Override
			public void startElement(String name, Attributes attributes) {
				checkCancel();
				if (name.equals(INSTALLABLE_UNIT_ELEMENT)) {
					this.units = new ArrayList<InstallableUnitDescription>(1);
					this.unitHandler = new InstallableUnitHandler(this, attributes, units);	
				} else {
					invalidElement(name, attributes);
				}
			}

			@Override
			protected void finished() {
				if (isValidXML()) {
					// TODO: This is an Ugly cast because the unit handler returns an interface rather than the mutable instance.
					//
					 this.installableUnit = (InstallableUnit) this.unitHandler.getInstallableUnit();
				}
			}
		}

		@Override
		protected String getErrorMessage() {
			return Messages.io_parseError;
		}

		@Override
		public String toString() {
			// TODO:
			return null;
		}
	}
}
