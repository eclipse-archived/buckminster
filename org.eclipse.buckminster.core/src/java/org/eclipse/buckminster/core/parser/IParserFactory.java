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
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.core.runtime.CoreException;

/**
 * A Factory for the SAX parsers used in Buckminster
 * 
 * @author thhal
 */
public interface IParserFactory
{
	/**
	 * Creates a SAX parser that can parse a CSpecExtension.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a CSpecExtension parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<CSpecExtension> getAlterCSpecParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a BillOfMaterial.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a BillOfMaterial parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<BillOfMaterials> getBillOfMaterialsParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a ComponentQuery.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a ComponentQuery parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<ComponentQuery> getComponentQueryParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a CSpec.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a CSpec parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<CSpec> getCSpecParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a DepNode.
	 * 
	 * @return a DepNode parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<BOMNode> getDepNodeParser() throws CoreException;

	/**
	 * Creates a SAX parser that can parse a Materializations.
	 * 
	 * @return a Materializations parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<Materialization> getMaterializationParser() throws CoreException;

	/**
	 * Creates a SAX parser that can parse a Materialization specifications.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a MaterializationSpec parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<MaterializationSpec> getMaterializationSpecParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a IProvider.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a IProvider parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<Provider> getProviderParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a Resolution.
	 * 
	 * @return a Resolution parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<Resolution> getResolutionParser() throws CoreException;

	/**
	 * Creates a SAX parser that can parse a CSpec.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a CSpec parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<ResourceMap> getResourceMapParser(boolean validating) throws CoreException;

	/**
	 * Creates a SAX parser that can parse a WorkspaceBinding.
	 * 
	 * @param validating
	 *            <code>true</code> if a validating parser is desired
	 * @return a IProvider parser.
	 * @throws CoreException
	 *             if the Java Runtime cannot support SAX parsers due to configuration problems.
	 */
	IParser<WorkspaceBinding> getWorkspaceBindingParser(boolean validating) throws CoreException;
}
