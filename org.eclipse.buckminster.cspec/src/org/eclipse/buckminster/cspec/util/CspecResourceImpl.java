/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspecxml.ICSpecXMLFactory;
import org.eclipse.buckminster.cspecxml.IDocumentRoot;
import org.eclipse.buckminster.cspecxml.util.CSpecXMLResourceImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspec.util.CspecResourceFactoryImpl
 * @generated NOT
 */
public class CspecResourceImpl extends CSpecXMLResourceImpl
{
	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param uri
	 *            the URI of the new resource.
	 * @generated
	 */
	public CspecResourceImpl(URI uri)
	{
		super(uri);
	}

	@Override
	public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException
	{
		super.doLoad(inputStream, options);
		ImportCSpexXML importer = new ImportCSpexXML();
		EList<EObject> roots = getContents();
		IDocumentRoot cspecDoc = (IDocumentRoot)roots.get(0);
		roots.set(0, importer.importCSpec(cspecDoc.getCspec()));
	}

	@Override
	public void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException
	{
		ExportCSpexXML exporter = new ExportCSpexXML();
		IDocumentRoot docRoot = ICSpecXMLFactory.eINSTANCE.createDocumentRoot();
		EList<EObject> roots = getContents();
		CSpec cspec = (CSpec)roots.get(0);
		docRoot.setCspec(exporter.exportCSpec(cspec));
		roots.set(0, docRoot);
		super.doSave(outputStream, options);
		roots.set(0, cspec);
	}
} // CspecResourceImpl
