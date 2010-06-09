/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.buckminster.aggregator.engine.maven.util.XMLResourceImplWithCheck;
import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.util.PomResourceFactoryImpl
 * @generated NOT
 */
public class PomResourceImpl extends XMLResourceImplWithCheck
{
	private String md5;

	private String sha1;

	private Long timestamp;

	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param uri
	 *            the URI of the new resource.
	 * @generated NOT
	 */
	public PomResourceImpl(URI uri)
	{
		super(uri);
	}

	@Override
	public void doLoad(InputStream is, Map<?, ?> options) throws IOException
	{
		if(is instanceof InputStreamWithInfo)
		{
			InputStreamWithInfo isi = (InputStreamWithInfo)is;
			timestamp = isi.getTimestamp();
			md5 = isi.getMd5();
			sha1 = isi.getSha1();
		}

		super.doLoad(is, options);
	}

	public String getMd5()
	{
		return md5;
	}

	public String getSha1()
	{
		return sha1;
	}

	public Long getTimestamp()
	{
		return timestamp;
	}
} // PomResourceImpl
