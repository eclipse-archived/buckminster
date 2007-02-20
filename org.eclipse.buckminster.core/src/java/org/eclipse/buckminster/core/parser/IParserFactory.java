/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.parser;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.xml.sax.SAXException;

/**
 * A Factory for the SAX parsers used in Buckminster
 * @author thhal
 */
public interface IParserFactory
{
	/**
	 * Creates a SAX parser that can parse a BillOfMaterial.
	 * @param validating <code>true</code> if a validating parser is desired
	 * @return a BillOfMaterial parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<BillOfMaterials> getBillOfMaterialsParser(boolean validating)
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a WorkspaceBinding.
	 * @return a WorkspaceBinding parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<WorkspaceBinding> getWorkspaceBindingParser()
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a ComponentQuery.
	 * @param validating <code>true</code> if a validating parser is desired
	 * @return a ComponentQuery parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<ComponentQuery> getComponentQueryParser(boolean validating)
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a Materializations.
	 * @return a Materializations parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<Materialization> getMaterializationParser()
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a CSpec.
	 * @param validating <code>true</code> if a validating parser is desired
	 * @return a CSpec parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<CSpec> getCSpecParser(boolean validating)
	throws SAXException;


	/**
	 * Creates a SAX parser that can parse a DepNode.
	 * @return a DepNode parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<DepNode> getDepNodeParser()
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a CSpecExtension.
	 * @param validating <code>true</code> if a validating parser is desired
	 * @return a CSpecExtension parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<CSpecExtension> getAlterCSpecParser(boolean validating)
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a Resolution.
	 * @return a Resolution parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<Resolution> getResolutionParser()
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a CSpec.
	 * @param validating <code>true</code> if a validating parser is desired
	 * @return a CSpec parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<ResourceMap> getResourceMapParser(boolean validating)
	throws SAXException;

	/**
	 * Creates a SAX parser that can parse a IProvider.
	 * @param validating <code>true</code> if a validating parser is desired
	 * @return a IProvider parser.
	 * @throws SAXException if the Java Runtime cannot support
	 * SAX parsers due to configuration problems.
	 */
	IParser<Provider> getProviderParser(boolean validating)
	throws SAXException;
}

