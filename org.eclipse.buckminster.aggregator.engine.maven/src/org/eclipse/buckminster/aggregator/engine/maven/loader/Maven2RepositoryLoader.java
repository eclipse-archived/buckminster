/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine.maven.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.aggregator.engine.maven.MavenManager;
import org.eclipse.buckminster.aggregator.engine.maven.MavenMetadata;
import org.eclipse.buckminster.aggregator.engine.maven.POM;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency;
import org.eclipse.buckminster.aggregator.engine.maven.pom.License;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Model;
import org.eclipse.buckminster.aggregator.loader.IRepositoryLoader;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;
import org.eclipse.buckminster.aggregator.p2.impl.ArtifactKeyImpl;
import org.eclipse.buckminster.aggregator.p2.impl.CopyrightImpl;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.LicenseImpl;
import org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl;
import org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl;
import org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl;
import org.eclipse.buckminster.aggregator.p2.impl.TouchpointTypeImpl;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.aggregator.util.UriIterator;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.ArtifactDescriptor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Query;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;

public class Maven2RepositoryLoader implements IRepositoryLoader
{
	static class VersionEntry
	{
		String groupId;

		String artifactId;

		Version version;

		VersionEntry(String groupId, String artifactId, Version version)
		{
			this.groupId = groupId;
			this.artifactId = artifactId;
			this.version = version;
		}

		public String toString()
		{
			StringBuilder sb = new StringBuilder(groupId);
			sb.append('/');
			sb.append(artifactId);
			sb.append('#');
			sb.append(version.getOriginal());
			return sb.toString();
		}
	}

	private class LicenseHelper
	{
		URI location;

		String body;

		BigInteger getDigest()
		{
			String message = normalize(body);
			try
			{
				MessageDigest algorithm = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
				algorithm.reset();
				algorithm.update(message.getBytes("UTF-8")); //$NON-NLS-1$
				byte[] digestBytes = algorithm.digest();
				return new BigInteger(1, digestBytes);
			}
			catch(NoSuchAlgorithmException e)
			{
				throw new RuntimeException(e);
			}
			catch(UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
		}

		/**
		 * Replace all sequences of whitespace with a single whitespace character. (copied from
		 * org.eclipse.equinox.internal.p2.metadata.License)
		 */
		private String normalize(String license)
		{
			String text = license.trim();
			StringBuffer result = new StringBuffer();
			int length = text.length();
			for(int i = 0; i < length; i++)
			{
				char c = text.charAt(i);
				boolean foundWhitespace = false;
				while(Character.isWhitespace(c) && i < length)
				{
					foundWhitespace = true;
					c = text.charAt(++i);
				}
				if(foundWhitespace)
					result.append(' ');
				if(i < length)
					result.append(c);
			}
			return result.toString();
		}
	}

	private static Pattern s_versionRangePattern = Pattern.compile("^(\\([)([^,]+),([^,]+)(\\)])$");

	// We are not interested in folder names that starts with a digit and a dot since
	// that's the version folders.
	//
	private static final Pattern s_folderExcludePattern = Pattern.compile("^.*/[0-9]+\\.[^/]*/?$");

	private static final String MAVEN_METADATA = "maven-metadata.xml";

	private static final Query QUERY_ALL_IUS = new MatchQuery()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			return candidate instanceof IInstallableUnit;
		}
	};

	private static Version createVersionFromFormatAndOriginal(String format, String versionStr)
	{
		return Version.parseVersion("format(" + format + "):" + versionStr);
	}

	private Stack<UriIterator> m_iteratorStack;

	private Iterator<VersionEntry> m_versionEntryItor;

	private URI m_location;

	private MetadataRepositoryImpl m_repository;

	private static final Pattern s_timestampPattern = Pattern.compile(//
	"^((?:19|20)\\d{2}(?:0[1-9]|1[012])(?:0[1-9]|[12][0-9]|3[01]))" + // //$NON-NLS-1$
			"(?:\\.((?:[01][0-9]|2[0-3])[0-5][0-9][0-5][0-9]))?$"); //$NON-NLS-1$

	private static final String MAVEN_EMPTY_RANGE_STRING = "0.0.0";

	private static final String REPOSITORY_CANCELLED_MESSAGE = "Repository loading was cancelled";

	private static final String MAVEN_ID_PROPERTY = "maven.artifactId";

	private static final String MAVEN_GROUP_PROPERTY = "maven.groupId";

	private static Version createVersion(String versionStr) throws CoreException
	{
		versionStr = GeneralUtils.trimmedOrNull(versionStr);
		if(versionStr == null)
			return null;

		Matcher m = s_timestampPattern.matcher(versionStr);
		if(m.matches())
			// Timestamp
			return createVersionFromFormatAndOriginal("S=[0-9];={8};[.S=[0-9];={6};='000000';]", versionStr);

		try
		{
			// Triplet
			return createVersionFromFormatAndOriginal("n[.n=0;[.n=0;]][d?S=M;]", versionStr);
		}
		catch(IllegalArgumentException e)
		{
			// String
			return createVersionFromFormatAndOriginal("S", versionStr);
		}
	}

	private static VersionRange createVersionRange(String versionRangeString) throws CoreException
	{
		String vr = GeneralUtils.trimmedOrNull(versionRangeString);
		if(vr == null)
			return VersionRange.emptyRange;

		Matcher m = s_versionRangePattern.matcher(vr);
		if(m.matches())
		{
			return new VersionRange(createVersion(m.group(2)), "[".equals(m.group(1)), createVersion(m.group(3)),
					"[".equals(m.group(4)));
		}
		else
		{
			Version v = createVersion(vr);
			return new VersionRange(v, true, Version.MAX_VERSION, true);
		}
	}

	public void close()
	{
		// do nothing
	}

	@SuppressWarnings("unchecked")
	public IArtifactRepository getArtifactRepository(IMetadataRepository mdr, IProgressMonitor monitor)
			throws CoreException
	{
		monitor.beginTask("Generating artifact repository", 100);
		SubMonitor subMon = SubMonitor.convert(monitor);
		SimpleArtifactRepository ar = new SimpleArtifactRepository(mdr.getName(), mdr.getLocation(), null)
		{
			public void save(boolean compress)
			{
				// no-op
			}
		};

		Collector collector = mdr.query(QUERY_ALL_IUS, new Collector(), subMon.newChild(40));

		IProgressMonitor fetchMon = new SubProgressMonitor(subMon, 20);
		fetchMon.beginTask("Collecting all IUs", collector.size());
		Iterator<IInstallableUnit> itor = collector.iterator();
		ArrayList<InstallableUnit> ius = new ArrayList<InstallableUnit>();
		while(itor.hasNext())
		{
			fetchMon.worked(1);
			ius.add(InstallableUnitImpl.importToModel(itor.next()));
		}
		Collections.sort(ius);
		subMon.worked(20);

		IProgressMonitor targetMon = new SubProgressMonitor(subMon, 20);
		targetMon.beginTask("Collecting all IUs", ius.size());
		for(IInstallableUnit iu : ius)
		{
			for(IArtifactKey key : iu.getArtifacts())
			{
				ArtifactDescriptor ad = new ArtifactDescriptor(key);
				String groupPath = iu.getProperty(MAVEN_GROUP_PROPERTY);
				if(groupPath != null)
					groupPath = groupPath.replace('.', '/');
				String id = iu.getProperty(MAVEN_ID_PROPERTY);

				String version = MavenManager.getVersionString(iu.getVersion());
				ad.setRepositoryProperty(ArtifactDescriptor.ARTIFACT_REFERENCE, mdr.getLocation().toString() + '/'
						+ groupPath + '/' + id + '/' + version + '/' + id + '-' + version + '.' + key.getClassifier());
				ar.addDescriptor(ad);
			}
			targetMon.worked(1);
		}

		return ar;
	}

	public void load(IProgressMonitor monitor) throws CoreException
	{
		load(monitor, false);
	}

	public void open(URI location, MetadataRepositoryImpl mdr) throws CoreException
	{
		if(!robotSafe(location.toString(), "/"))
			throw BuckminsterException.fromMessage("Crawling of %1$s is discouraged (see %1$s/robots.txt)",
					location.toString());

		m_location = location;
		m_repository = mdr;

		m_iteratorStack = new Stack<UriIterator>();
		m_versionEntryItor = Collections.<VersionEntry> emptyList().iterator();
	}

	public void reload(IProgressMonitor monitor) throws CoreException
	{
		load(monitor, true);
	}

	private void append(StringBuilder sb, String name, boolean newLines)
	{
		name = GeneralUtils.trimmedOrNull(name);
		if(name != null)
		{
			sb.append(name);
			if(newLines)
			{
				sb.append('\n');
				sb.append('\n');
			}
		}
	}

	private LicenseHelper buildLicense(List<License> licenses)
	{
		URI location = null;
		StringBuilder body = new StringBuilder();
		int cnt = licenses.size();

		if(cnt == 1)
		{
			License license = licenses.get(0);
			try
			{
				if(GeneralUtils.trimmedOrNull(license.getUrl()) != null)
					location = URI.create(license.getUrl());
				append(body, license.getName(), true);
				append(body, license.getComments(), true);
				append(body, "Distribution: " + license.getDistribution(), false);

				LicenseHelper helper = new LicenseHelper();
				helper.body = body.toString();
				helper.location = location;
				return helper;
			}
			catch(IllegalArgumentException e)
			{
				// bad location - put location in the body as text
			}
		}

		int i = 0;
		for(License license : licenses)
		{
			append(body, license.getName(), true);
			append(body, license.getComments(), true);
			append(body, license.getUrl(), true);
			append(body, "Distribution: " + license.getDistribution(), i++ < cnt);
		}

		LicenseHelper helper = new LicenseHelper();
		helper.body = body.toString();
		helper.location = location;
		return helper;
	}

	private InstallableUnit createIU(VersionEntry versionEntry, IProgressMonitor monitor) throws IOException
	{
		InstallableUnitImpl iu = (InstallableUnitImpl)P2Factory.eINSTANCE.createInstallableUnit();

		// TODO What about the groupId ? See discussion below
		iu.setId((versionEntry.artifactId.equals(versionEntry.groupId) || versionEntry.artifactId.startsWith(versionEntry.groupId + '.'))
				? versionEntry.artifactId
				: (versionEntry.groupId + '/' + versionEntry.artifactId));
		iu.setVersion(versionEntry.version);
		iu.getPropertyMap().put(MAVEN_ID_PROPERTY, versionEntry.artifactId);
		iu.getPropertyMap().put(MAVEN_GROUP_PROPERTY, versionEntry.groupId);
		iu.getPropertyMap().put(ORIGINAL_PATH_PROPERTY, versionEntry.groupId.replace('.', '/'));
		iu.getPropertyMap().put(ORIGINAL_ID_PROPERTY, versionEntry.artifactId);
		iu.setFilter(null);

		POM pom;
		Model model;
		try
		{
			pom = POM.getPOM(m_location.toString(), versionEntry.groupId, versionEntry.artifactId,
					versionEntry.version.getOriginal());

			if(!versionEntry.groupId.equals(pom.obtainGroupId()))
				throw new IOException(String.format("Bad groupId in POM: expected %s, found %s", versionEntry.groupId,
						pom.obtainGroupId()));
			if(!versionEntry.artifactId.equals(pom.obtainArtifactId()))
				throw new IOException(String.format("Bad artifactId in POM: expected %s, found %s",
						versionEntry.artifactId, pom.obtainArtifactId()));

			model = pom.getProject();

			if(model.getDependencies() != null)
			{
				for(Dependency dependency : model.getDependencies().getDependency())
				{
					// TODO What about the namespace ?
					String namespace = dependency.isSetType()
							? POM.expandProperties(dependency.getType(), pom.getFullPropertyMap())
							: "jar";

					// TODO What about the groupId ?
					// Yes, include: good for native maven, but not for "mavenized" p2 since artifactId is full
					// No, don't include: good for "mavenized" p2, but not for native maven (may lead to duplicities)
					// For now: include if artifactId is not equals to groupId or does not start with groupId followed
					// by a dot
					String groupId = POM.expandProperties(dependency.getGroupId(), pom.getFullPropertyMap());
					String artifactId = POM.expandProperties(dependency.getArtifactId(), pom.getFullPropertyMap());
					String versionRange = POM.expandProperties(dependency.getVersion(), pom.getFullPropertyMap());
					if(versionRange == null)
						versionRange = MAVEN_EMPTY_RANGE_STRING;
					RequiredCapabilityImpl rc = (RequiredCapabilityImpl)P2Factory.eINSTANCE.createRequiredCapability();

					rc.setNamespace(namespace);

					rc.setName(artifactId.equals(groupId) || artifactId.startsWith(groupId + '.')
							? artifactId
							: (groupId + '.' + artifactId));

					VersionRange vr = createVersionRange(versionRange);
					rc.setRange(vr);
					rc.setGreedy(true);

					if(dependency.isSetOptional())
						rc.setOptional(dependency.isOptional());

					iu.getRequiredCapabilityList().add(rc);
				}
			}

			// Add 2 provided capabilities - one for an IU, another one for packaging
			ProvidedCapabilityImpl pc = (ProvidedCapabilityImpl)P2Factory.eINSTANCE.createProvidedCapability();
			String version = pom.obtainVersion();

			pc.setNamespace(IInstallableUnit.NAMESPACE_IU_ID);
			pc.setName(iu.getId());

			pc.setVersion(createVersion(version));
			iu.getProvidedCapabilityList().add(pc);

			pc = (ProvidedCapabilityImpl)P2Factory.eINSTANCE.createProvidedCapability();
			// TODO Namespace? See discussion above (regarding dependencies)
			pc.setNamespace(model.getPackaging());
			pc.setName(iu.getId());

			pc.setVersion(createVersion(version));
			iu.getProvidedCapabilityList().add(pc);

			if(model.getLicenses() != null)
			{
				List<License> toLicense = new ArrayList<License>();
				List<License> toCopyright = new ArrayList<License>();

				for(License license : model.getLicenses().getLicense())
				{
					String match = "copyright";
					String name = license.getName();
					String comments = license.getComments();

					if(name != null && name.toLowerCase().contains(match) || comments != null
							&& comments.toLowerCase().contains(match))
						toCopyright.add(license);
					else
						toLicense.add(license);
				}

				if(toCopyright.size() > 0)
				{
					LicenseHelper copyrightHelper = buildLicense(toCopyright);
					CopyrightImpl copyright = (CopyrightImpl)P2Factory.eINSTANCE.createCopyright();
					copyright.setBody(copyrightHelper.body);
					copyright.setLocation(copyrightHelper.location);
					iu.setCopyright(copyright);
				}

				if(toLicense.size() > 0)
				{
					LicenseHelper licenseHelper = buildLicense(toLicense);
					LicenseImpl license = (LicenseImpl)P2Factory.eINSTANCE.createLicense();
					license.setBody(licenseHelper.body);
					license.setLocation(licenseHelper.location);
					license.setDigest(licenseHelper.getDigest());
					iu.setLicense(license);
				}
			}

			if(!"pom".equals(model.getPackaging()))
			{
				ArtifactKeyImpl artifact = (ArtifactKeyImpl)P2Factory.eINSTANCE.createArtifactKey();
				artifact.setId(iu.getId());
				artifact.setVersion(iu.getVersion());
				artifact.setClassifier(model.getPackaging());
				iu.getArtifactList().add(artifact);
			}
		}
		catch(CoreException e)
		{
			throw new IOException(e.getMessage(), e);
		}

		TouchpointTypeImpl touchpointType = (TouchpointTypeImpl)P2Factory.eINSTANCE.createTouchpointType();

		// TODO Set up a touchpoint! What is an installation of a maven artifact supposed to do?
		touchpointType.setId(ITouchpointType.NONE.getId());
		touchpointType.setVersion(ITouchpointType.NONE.getVersion());
		iu.setTouchpointType(touchpointType);

		Buckminster.getLogger().debug("Adding IU: %s#%s", iu.getId(), MavenManager.getVersionString(iu.getVersion()));
		return iu;
	}

	private MavenMetadata findNextComponent(IProgressMonitor monitor) throws CoreException
	{
		if(m_iteratorStack.isEmpty())
			//
			// All iterators exhausted
			//
			return null;

		UriIterator itor = m_iteratorStack.peek();
		outer: while(itor.hasNext())
		{
			URI uri = itor.next();
			IPath path = Path.fromPortableString(uri.getPath());

			if(path.hasTrailingSeparator())
			{
				// This was a folder. Push it on the stack and
				// scan it.
				//
				for(UriIterator prev : m_iteratorStack)
				{
					if(uri.equals(prev.getRoot()))
						//
						// Circular reference detected. This iteration
						// cannot be used.
						//
						continue outer;
				}

				UriIterator subItor;
				try
				{
					subItor = new UriIterator(uri, s_folderExcludePattern, monitor);
				}
				catch(CoreException e)
				{
					Buckminster.getLogger().warning(e.getMessage());
					continue;
				}

				URI[] uris = subItor.getURIs();
				int idx = uris.length;
				URI mavenMetadataURI = null;
				while(--idx >= 0)
				{
					URI subUri = uris[idx];
					IPath subPath = Path.fromPortableString(subUri.getPath());
					String name = subPath.lastSegment();
					if(MAVEN_METADATA.equals(name))
					{
						mavenMetadataURI = subUri;
						break;
					}
				}

				if(mavenMetadataURI != null)
				{
					// This folder has a maven-metadata.xml document. Let's filter out
					// all sub folders that just reflect versions of that document since
					// we will visit them anyway in due course if needed.
					//
					List<String> versions;
					try
					{
						MavenMetadata md = new MavenMetadata(
								org.eclipse.emf.common.util.URI.createURI(mavenMetadataURI.toString()));
						versions = md.getMetaData().getVersioning().getVersions().getVersion();
					}
					catch(Exception e)
					{
						Buckminster.getLogger().warning(e.getMessage());
						continue;
					}
					int top = uris.length;
					List<URI> uriList = new ArrayList<URI>();
					for(idx = 0; idx < top; ++idx)
					{
						URI subUri = uris[idx];
						IPath subPath = Path.fromPortableString(subUri.getPath());
						if(!versions.contains(subPath.lastSegment()))
							uriList.add(subUri);
					}
					if(uriList.size() < top)
						subItor = new UriIterator(uri, s_folderExcludePattern, uriList.toArray(new URI[uriList.size()]));
				}

				m_iteratorStack.push(subItor);
				return findNextComponent(monitor);
			}

			try
			{
				if(MAVEN_METADATA.equals(path.lastSegment()))
					return new MavenMetadata(org.eclipse.emf.common.util.URI.createURI(uri.toString()));
			}
			catch(Exception e)
			{
				Buckminster.getLogger().warning(e.getMessage());
			}
		}

		// This iterator is exhausted. Pop it from the stack
		//
		m_iteratorStack.pop();
		return findNextComponent(monitor);
	}

	private InstallableUnit findNextIU(IProgressMonitor monitor) throws CoreException
	{
		while(true)
		{
			while(!m_versionEntryItor.hasNext())
			{
				if(monitor.isCanceled())
					throw new OperationCanceledException(REPOSITORY_CANCELLED_MESSAGE);

				MavenMetadata md = findNextComponent(monitor);
				if(md == null)
					return null;
				m_versionEntryItor = getVersions(md).iterator();
			}

			if(monitor.isCanceled())
				throw new OperationCanceledException(REPOSITORY_CANCELLED_MESSAGE);

			VersionEntry ve = m_versionEntryItor.next();
			try
			{
				return createIU(ve, monitor);
			}
			catch(Exception e)
			{
				Buckminster.getLogger().warning("Skipping component " + ve.toString() + ": " + e.getMessage());
			}
		}
	}

	private List<VersionEntry> getVersions(MavenMetadata md) throws CoreException
	{
		List<String> versions = md.getMetaData().getVersioning().getVersions().getVersion();
		List<VersionEntry> versionEntries = new ArrayList<VersionEntry>(versions.size());
		String groupId = md.getMetaData().getGroupId();
		String artifactId = md.getMetaData().getArtifactId();
		for(String versionString : versions)
		{
			try
			{
				versionEntries.add(new VersionEntry(groupId, artifactId, createVersion(versionString)));
			}
			catch(IllegalArgumentException e)
			{
				Buckminster.getLogger().warning(
						"Skipping component " + groupId + '/' + artifactId + '#' + versionString + ": "
								+ e.getMessage());
			}
		}

		return versionEntries;
	}

	private void load(IProgressMonitor monitor, boolean avoidCache) throws CoreException
	{
		SubMonitor subMon = SubMonitor.convert(monitor, 100);

		try
		{
			m_repository.setName(null);
			m_repository.setLocation(m_location);
			m_repository.setDescription(null);
			m_repository.setProvider(null);
			m_repository.setType("maven2");
			m_repository.setVersion(null);
			m_repository.getPropertyMap().putAll(Collections.<String, String> emptyMap());

			UriIterator itor;
			final SubMonitor[] crawlMonRef = new SubMonitor[1];
			itor = new UriIterator(m_location, s_folderExcludePattern, subMon.newChild(1))
			{
				@Override
				public URI next()
				{
					URI nextURI = super.next();
					if(nextURI != null)
						crawlMonRef[0].worked(1);

					return nextURI;
				}
			};
			SubMonitor crawlMon = subMon.newChild(99);
			crawlMon.beginTask("Scanning repository", itor.size());
			crawlMonRef[0] = crawlMon;

			m_iteratorStack.add(itor);
			List<InstallableUnit> ius = m_repository.getInstallableUnits();
			Map<String, InstallableUnit> categoryMap = new HashMap<String, InstallableUnit>();

			InstallableUnit iu;
			IProgressMonitor cancellationOnlyMonitor = new SubProgressMonitor(monitor, 0)
			{
				@Override
				public void beginTask(String name, int work)
				{
					// no-op
				}

				@Override
				public void done()
				{
					// no-op
				}

				@Override
				public void internalWorked(double work)
				{
					// no-op
				}

				@Override
				public void setTaskName(String name)
				{
					// no-op
				}

				@Override
				public void subTask(String name)
				{
					// no-op
				}

				@Override
				public void worked(int work)
				{
					// no-op
				}
			};
			while((iu = findNextIU(cancellationOnlyMonitor)) != null)
			{
				if(crawlMon.isCanceled())
					throw new OperationCanceledException(REPOSITORY_CANCELLED_MESSAGE);

				String groupId = iu.getProperty(MAVEN_GROUP_PROPERTY);
				InstallableUnitImpl category = (InstallableUnitImpl)categoryMap.get(groupId);
				if(category == null)
				{
					category = (InstallableUnitImpl)P2Factory.eINSTANCE.createInstallableUnit();
					category.setId(groupId);
					category.setVersion(Version.emptyVersion);
					category.getPropertyMap().put(IInstallableUnit.PROP_TYPE_CATEGORY, "true");
					category.getPropertyMap().put(IInstallableUnit.PROP_NAME, "Group " + groupId);
					ius.add(category);
					categoryMap.put(groupId, category);
				}

				RequiredCapabilityImpl rc = (RequiredCapabilityImpl)P2Factory.eINSTANCE.createRequiredCapability();
				rc.setName(iu.getId());
				rc.setNamespace(IInstallableUnit.NAMESPACE_IU_ID);
				rc.setRange(new VersionRange(iu.getVersion(), true, iu.getVersion(), true));
				List<RequiredCapability> rcList = category.getRequiredCapabilityList();
				rcList.add(rc);

				ius.add(iu);
			}
		}
		finally
		{
			subMon.done();
		}
	}

	private boolean robotSafe(String baseURL, String path) throws CoreException
	{
		String robotStr = baseURL + "/robots.txt";
		URL robotURL;
		try
		{
			robotURL = new URL(robotStr);
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.fromMessage(e.getMessage());
		}

		StringBuilder commands = new StringBuilder();
		InputStream robotStream = null;

		try
		{
			robotStream = robotURL.openStream();
			byte buffer[] = new byte[1024];
			int read;
			while((read = robotStream.read(buffer)) != -1)
				commands.append(new String(buffer, 0, read));
		}
		catch(IOException e)
		{
			// no robots.txt file => safe to crawl
			return true;
		}
		finally
		{
			if(robotStream != null)
				try
				{
					robotStream.close();
				}
				catch(IOException e)
				{
					// ignore
				}
		}

		// search for "Disallow:" commands.
		int index = 0;
		String disallow = "Disallow:";
		while((index = commands.indexOf(disallow, index)) != -1)
		{
			index += disallow.length();
			String commandPath = commands.substring(index);
			StringTokenizer tokenizer = new StringTokenizer(commandPath);

			if(!tokenizer.hasMoreTokens())
				break;

			String disallowedPath = tokenizer.nextToken();

			// if the URL starts with a disallowed path, it is not safe
			if(path.indexOf(disallowedPath) == 0)
				return false;
		}

		return true;
	}
}
