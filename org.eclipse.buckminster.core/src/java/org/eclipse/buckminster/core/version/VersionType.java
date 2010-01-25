package org.eclipse.buckminster.core.version;

import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;

public class VersionType
{
	public static final String OSGI = "OSGi"; //$NON-NLS-1$

	public static final String STRING = "String"; //$NON-NLS-1$

	public static final String TIMESTAMP = "Timestamp"; //$NON-NLS-1$

	public static final String TRIPLET = "Triplet"; //$NON-NLS-1$

	public static final String RAW = "Raw Omni Version"; //$NON-NLS-1$

	private final IVersionFormat m_format;

	private final String[] m_labels;

	public VersionType(IVersionFormat format, String... labels)
	{
		m_format = format;
		m_labels = labels;
	}

	/**
	 * Coerce the object argument into a version of this type if possible.
	 * 
	 * @param object
	 *            The object to coerce
	 * @return the version that is the result of the coercion or <code>null</code> if coercion was impossible.
	 */
	public Version coerce(Object object)
	{
		if(object == null)
			return null;
		if(object instanceof Version)
			return (Version)object;
		return fromString(object.toString());
	}

	/**
	 * Parse a version from the given versionString. A <code>null</code> input, or an input that of &quot;0.0.0&quot;
	 * will cause a <code>null</code> return.
	 * 
	 * @param versionString
	 *            The string to parse
	 * @return The parsed version or <code>null</code>.
	 * @throws IllegalArgumentException
	 */
	public Version fromString(String versionString)
	{
		return m_format.parse(versionString);
	}

	/**
	 * Return the version format for this type.
	 * 
	 * @return The version format
	 */
	public IVersionFormat getFormat()
	{
		return m_format;
	}

	public String getId()
	{
		return m_labels[0];
	}

	/**
	 * Returns an array of labels known to identify this type. The first entry in this array is always equal to the ID
	 * of the type.
	 * 
	 * @return An array of at least one element.
	 */
	public String[] getLabels()
	{
		return m_labels;
	}
}
