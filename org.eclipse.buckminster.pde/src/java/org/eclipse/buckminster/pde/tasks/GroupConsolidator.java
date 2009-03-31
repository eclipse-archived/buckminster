/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.buckminster.ant.tasks.VersionQualifierTask;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.p2.publisher.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.plugin.ExternalFragmentModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
abstract class GroupConsolidator extends VersionQualifierTask implements IPDEConstants, IBuildPropertiesConstants
{
	static void appendEncodedCharacter(StringBuilder buffer, int c)
	{
		while(c > 62)
		{
			buffer.append('z');
			c -= 63;
		}
		buffer.append(base64Character(c));
	}

	// Integer to character conversion in our base-64 encoding scheme. If the
	// input is out of range, an illegal character will be returned.
	//
	static char base64Character(int number)
	{
		return (number < 0 || number > 63)
				? ' '
				: BASE_64_ENCODING.charAt(number);
	}

	static int charValue(char c)
	{
		int index = BASE_64_ENCODING.indexOf(c);
		// The "+ 1" is very intentional. For a blank (or anything else that
		// is not a legal character), we want to return 0. For legal
		// characters, we want to return one greater than their position, so
		// that a blank is correctly distinguished from '-'.
		return index + 1;
	}

	static Version findBestVersion(Map<String, Version[]> versionMap, String id, String componentType, String refId,
			Version version) throws CoreException
	{
		if(version != null && Version.emptyVersion.equals(version))
			version = null;

		Version candidate = null;
		Version[] versions = versionMap.get(refId);
		if(versions != null)
		{
			for(Version v : versions)
			{
				if(v == null)
					continue;

				if(version == null)
				{
					// Highest found version wins
					//
					if(candidate == null || v.compareTo(candidate) > 0)
						candidate = v;
					continue;
				}

				if(version.getMajor() == v.getMajor() && version.getMinor() == v.getMinor()
						&& version.getMicro() == v.getMicro())
				{
					if(candidate == null || v.compareTo(candidate) > 0)
						candidate = v;
				}
			}
		}
		if(candidate == null)
			//
			// Nothing found that can replace the version
			//
			candidate = version;

		return candidate;
	}

	static InputStream getInput(File dirOrZip, String fileName) throws CoreException, FileNotFoundException
	{
		if(dirOrZip.isDirectory())
			return new BufferedInputStream(new FileInputStream(new File(dirOrZip, fileName)));

		JarFile jarFile = null;
		try
		{
			jarFile = new JarFile(dirOrZip);
			JarEntry entry = jarFile.getJarEntry(fileName);
			if(entry == null)
				throw new FileNotFoundException(String.format("%s[%s]", dirOrZip, fileName)); //$NON-NLS-1$

			// Closing the jarFile is hereby the responsibility of the user of
			// the returned InputStream
			//
			final JarFile innerJarFile = jarFile;
			jarFile = null;
			return new FilterInputStream(innerJarFile.getInputStream(entry))
			{
				@Override
				public void close() throws IOException
				{
					try
					{
						super.close();
					}
					catch(IOException e)
					{
					}
					innerJarFile.close();
				}
			};
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw BuckminsterException.fromMessage(e, NLS.bind(Messages.unable_to_read_0, dirOrZip));
		}
		finally
		{
			if(jarFile != null)
			{
				try
				{
					jarFile.close();
				}
				catch(IOException e)
				{
					PDEPlugin.getLogger().error(e, NLS.bind(Messages.error_while_closing_0, dirOrZip));
				}
			}
		}
	}

	// Encode a non-negative number as a variable length string, with the
	// property that if X > Y then the encoding of X is lexicographically
	// greater than the enocding of Y. This is accomplished by encoding the
	// length of the string at the beginning of the string. The string is a
	// series of base 64 (6-bit) characters. The first three bits of the first
	// character indicate the number of additional characters in the string.
	// The last three bits of the first character and all of the rest of the
	// characters encode the actual value of the number. Examples:
	// 0 --> 000 000 --> "-"
	// 7 --> 000 111 --> "6"
	// 8 --> 001 000 001000 --> "77"
	// 63 --> 001 000 111111 --> "7z"
	// 64 --> 001 001 000000 --> "8-"
	// 511 --> 001 111 111111 --> "Dz"
	// 512 --> 010 000 001000 000000 --> "E7-"
	// 2^32 - 1 --> 101 011 111111 ... 111111 --> "fzzzzz"
	// 2^45 - 1 --> 111 111 111111 ... 111111 --> "zzzzzzzz"
	// (There are some wasted values in this encoding. For example,
	// "7-" through "76" and "E--" through "E6z" are not legal encodings of
	// any number. But the benefit of filling in those wasted ranges would not
	// be worth the added complexity.)
	static String lengthPrefixBase64(long number)
	{
		int length = 7;
		for(int i = 0; i < 7; ++i)
		{
			if(number < (1L << ((i * 6) + 3)))
			{
				length = i;
				break;
			}
		}
		StringBuilder result = new StringBuilder(length + 1);
		result.append(base64Character((length << 3) + (int)((number >> (6 * length)) & 0x7)));
		while(--length >= 0)
		{
			result.append(base64Character((int)((number >> (6 * length)) & 0x3f)));
		}
		return result.toString();
	}

	private final Map<String, Integer> m_contextQualifierLengths = new HashMap<String, Integer>();

	static final int QUALIFIER_SUFFIX_VERSION = 1;

	// The 64 characters that are legal in a version qualifier, in lexicographical order.
	static final String BASE_64_ENCODING = "-0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; //$NON-NLS-1$

	static void addVersion(Map<String, Version[]> versionMap, String id, String versionStr)
	{
		if(versionStr == null)
			return;

		Version version = Version.parseVersion(versionStr);
		Version[] arr = versionMap.get(id);
		if(arr == null)
			arr = new Version[] { version };
		else
		{
			for(Version old : arr)
				if(old.equals(version))
					return;

			Version[] newArr = new Version[arr.length + 1];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			newArr[arr.length] = version;
			arr = newArr;
		}
		versionMap.put(id, arr);
	}

	private final File m_outputFile;

	private final boolean m_generateVersionSuffix;

	private final int m_maxVersionSuffixLength;

	private final int m_significantDigits;

	private final Map<String, Version[]> m_pluginVersions = new HashMap<String, Version[]>();

	private final Map<String, Version[]> m_featureVersions = new HashMap<String, Version[]>();

	GroupConsolidator(File outputFile, File propertiesFile, List<File> featuresAndBundles, String qualifier,
			boolean generateVersionSuffix, int maxVersionSuffixLength, int significantDigits) throws CoreException
	{
		super(propertiesFile, qualifier);
		m_outputFile = outputFile;
		m_generateVersionSuffix = generateVersionSuffix;

		if(significantDigits == -1)
			significantDigits = Integer.MAX_VALUE;

		if(maxVersionSuffixLength == -1)
			maxVersionSuffixLength = 28;

		m_significantDigits = getIntProperty(PROPERTY_SIGNIFICANT_VERSION_DIGITS, significantDigits);
		m_maxVersionSuffixLength = getIntProperty(PROPERTY_GENERATED_VERSION_LENGTH, maxVersionSuffixLength);

		for(File featureOrBundle : featuresAndBundles)
		{
			InputStream input = null;
			try
			{
				try
				{
					input = getInput(featureOrBundle, FEATURE_FILE);
					IFeatureModel model = FeatureModelReader.readFeatureModel(input);
					IFeature feature = model.getFeature();
					String id = feature.getId();
					String version = feature.getVersion();

					int ctxQualLen = -1;
					if(version.indexOf('-') > 0)
					{
						IOUtils.close(input);
						input = getInput(featureOrBundle, FEATURE_FILE);
						ctxQualLen = EditableFeatureModel.getContextQualifierLength(input);
					}
					m_contextQualifierLengths.put(id, Integer.valueOf(ctxQualLen));
					addVersion(m_featureVersions, id, version);
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
				try
				{
					input = getInput(featureOrBundle, BUNDLE_FILE);
					ExternalBundleModel model = new ExternalBundleModel();
					model.load(input, true);
					IBundlePluginModelBase bmodel = model.isFragmentModel()
							? new BundleFragmentModel()
							: new BundlePluginModel();

					bmodel.setEnabled(true);
					bmodel.setBundleModel(model);
					IPluginBase pb = bmodel.getPluginBase();

					addVersion(m_pluginVersions, pb.getId(), pb.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
				try
				{
					input = getInput(featureOrBundle, PLUGIN_FILE);
					ExternalPluginModel model = new ExternalPluginModel();
					model.load(input, true);
					IPluginBase pb = model.getPluginBase();
					addVersion(m_pluginVersions, pb.getId(), pb.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
				try
				{
					input = getInput(featureOrBundle, FRAGMENT_FILE);
					ExternalFragmentModel model = new ExternalFragmentModel();
					model.load(input, true);
					IPluginBase pb = model.getPluginBase();
					addVersion(m_pluginVersions, pb.getId(), pb.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
			}
			finally
			{
				IOUtils.close(input);
			}
		}
	}

	/**
	 * Version suffix generation. Modeled after
	 * {@link org.eclipse.pde.internal.build.builder.FeatureBuildScriptGenerator#generateFeatureVersionSuffix(org.eclipse.pde.internal.build.site.BuildTimeFeature buildFeature)}
	 * 
	 * @return The generated suffix or <code>null</code>
	 * @throws CoreException
	 */
	String generateFeatureVersionSuffix(List<VersionedName> features, List<VersionedName> bundles) throws CoreException
	{
		if(!m_generateVersionSuffix || m_maxVersionSuffixLength <= 0)
			return null; // do nothing

		long majorSum = 0L;
		long minorSum = 0L;
		long serviceSum = 0L;

		// Include the version of this algorithm as part of the suffix, so that
		// we have a way to make sure all suffixes increase when the algorithm
		// changes.
		//
		majorSum += QUALIFIER_SUFFIX_VERSION;

		int numElements = features.size() + bundles.size();
		if(numElements == 0)
			//
			// This feature is empty so there will be no suffix
			//
			return null;

		String[] qualifiers = new String[numElements];

		// Loop through the included features, adding the version number parts
		// to the running totals and storing the qualifier suffixes.
		//
		int idx = 0;
		for(VersionedName refFeature : features)
		{
			Version version = refFeature.getVersion();
			majorSum += version.getMajor();
			minorSum += version.getMinor();
			serviceSum += version.getMicro();

			String qualifier = version.getQualifier();
			Integer ctxLen = m_contextQualifierLengths.get(refFeature.getId());
			int contextLength = (ctxLen == null)
					? -1
					: ctxLen.intValue();
			++contextLength; // account for the '-' separating the context qualifier and suffix

			// The entire qualifier of the nested feature is often too long to
			// include in the suffix computation for the containing feature,
			// and using it would result in extremely long qualifiers for
			// umbrella features. So instead we want to use just the suffix
			// part of the qualifier, or just the context part (if there is no
			// suffix part). See bug #162022.
			//
			if(qualifier != null && qualifier.length() > contextLength)
				qualifier = qualifier.substring(contextLength);

			qualifiers[idx++] = qualifier;
		}

		// Loop through the included plug-ins and fragments, adding the version
		// number parts to the running totals and storing the qualifiers.
		//
		for(VersionedName refBundle : bundles)
		{
			Version version = refBundle.getVersion();
			String qualifier = version.getQualifier();
			if(qualifier != null && qualifier.endsWith(PROPERTY_QUALIFIER))
			{
				int resultingLength = qualifier.length() - PROPERTY_QUALIFIER.length();
				if(qualifier.charAt(resultingLength - 1) == '.')
					resultingLength--;
				qualifier = qualifier.substring(0, resultingLength);
				version = new Version(version.getMajor(), version.getMinor(), version.getMicro(), qualifier);
			}
			majorSum += version.getMajor();
			minorSum += version.getMinor();
			serviceSum += version.getMicro();
			qualifiers[idx++] = version.getQualifier();
		}

		// Limit the qualifiers to the specified number of significant digits,
		// and figure out what the longest qualifier is.
		//
		int longestQualifier = 0;
		while(--idx >= 0)
		{
			String qualifier = qualifiers[idx];
			if(qualifier == null)
				continue;

			if(qualifier.length() > m_significantDigits)
			{
				qualifier = qualifier.substring(0, m_significantDigits);
				qualifiers[idx] = qualifier;
			}
			if(qualifier.length() > longestQualifier)
				longestQualifier = qualifier.length();
		}

		StringBuilder result = new StringBuilder();

		// Encode the sums of the first three parts of the version numbers.
		result.append(lengthPrefixBase64(majorSum));
		result.append(lengthPrefixBase64(minorSum));
		result.append(lengthPrefixBase64(serviceSum));

		if(longestQualifier > 0)
		{
			// Calculate the sum at each position of the qualifiers.
			int[] qualifierSums = new int[longestQualifier];
			for(idx = 0; idx < numElements; ++idx)
			{
				String qualifier = qualifiers[idx];
				if(qualifier == null)
					continue;

				int top = qualifier.length();
				for(int j = 0; j < top; ++j)
					qualifierSums[j] += charValue(qualifier.charAt(j));
			}

			// Normalize the sums to be base 65.
			int carry = 0;
			for(int k = longestQualifier - 1; k >= 1; --k)
			{
				qualifierSums[k] += carry;
				carry = qualifierSums[k] / 65;
				qualifierSums[k] = qualifierSums[k] % 65;
			}
			qualifierSums[0] += carry;

			// Always use one character for overflow. This will be handled
			// correctly even when the overflow character itself overflows.
			result.append(lengthPrefixBase64(qualifierSums[0]));
			for(int m = 1; m < longestQualifier; ++m)
				appendEncodedCharacter(result, qualifierSums[m]);
		}

		// If the resulting suffix is too long, shorten it to the designed length.
		//
		if(result.length() > m_maxVersionSuffixLength)
			result.setLength(m_maxVersionSuffixLength);

		// It is safe to strip any '-' characters from the end of the suffix.
		// (This won't happen very often, but it will save us a character or
		// two when it does.)
		//
		int len = result.length();
		while(len > 0 && result.charAt(len - 1) == '-')
			result.setLength(--len);
		return result.toString();
	}

	Map<String, Version[]> getFeatureVersions()
	{
		return m_featureVersions;
	}

	int getIntProperty(String property, int defaultValue)
	{
		int result = defaultValue;

		Object value = getProperties().get(property);
		if(value instanceof String)
		{
			try
			{
				result = Integer.parseInt((String)value);
				if(result < 1)
					// It has to be a positive integer. Use the default.
					result = defaultValue;
			}
			catch(NumberFormatException e)
			{
				// Leave as default value
			}
		}
		return result;
	}

	File getOutputFile()
	{
		return m_outputFile;
	}

	Map<String, Version[]> getPluginVersions()
	{
		return m_pluginVersions;
	}

	ComponentIdentifier replaceFeatureReferenceVersion(String id, VersionedName ref) throws CoreException
	{
		Version version = findBestVersion(m_featureVersions, id, "feature", ref.getId(), ref.getVersion()); //$NON-NLS-1$
		if(version != null)
			return new ComponentIdentifier(ref.getId(), IComponentType.ECLIPSE_FEATURE, version);
		return null;
	}

	ComponentIdentifier replacePluginReferenceVersion(String id, VersionedName ref) throws CoreException
	{
		Version version = findBestVersion(m_pluginVersions, id, "plugin", ref.getId(), ref.getVersion()); //$NON-NLS-1$
		if(version != null)
			return new ComponentIdentifier(ref.getId(), IComponentType.OSGI_BUNDLE, version);
		return null;
	}
}
