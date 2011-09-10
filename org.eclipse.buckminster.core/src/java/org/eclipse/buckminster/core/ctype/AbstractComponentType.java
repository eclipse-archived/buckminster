/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.P2ReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractComponentType extends AbstractExtension implements IComponentType {
	static class MetaFile implements IMetaFile {
		private final IPath[] aliases;

		private final boolean optional;

		private final IPath path;

		public MetaFile(IPath path, boolean optional, IPath[] aliases) {
			this.path = path;
			this.optional = optional;
			this.aliases = aliases;
		}

		@Override
		public IPath[] getAliases() {
			return aliases;
		}

		@Override
		public IPath getPath() {
			return path;
		}

		@Override
		public boolean isOptional() {
			return optional;
		}

	}

	private static final IMetaFile[] noMetaFiles = new IMetaFile[0];

	/**
	 * Helper methods used by component types that manifest themselfs as one
	 * single jar file.
	 * 
	 * @param cspec
	 * @return An export where other jars that this component depends on can be
	 *         added
	 */
	public static GroupBuilder addSelfAsJarArtifactGroups(CSpecBuilder cspec) throws PrerequisiteAlreadyDefinedException,
			AttributeAlreadyDefinedException {
		GroupBuilder archives = cspec.createGroupBuilder();
		archives.setName(WellKnownExports.JAVA_BINARY_ARCHIVES);
		archives.setPublic(true);
		archives.addSelfRequirement();
		cspec.addAttribute(archives);

		GroupBuilder generic = cspec.createGroupBuilder();
		generic.setName(WellKnownExports.JAVA_BINARIES);
		generic.setPublic(true);
		generic.addLocalPrerequisite(archives);
		cspec.addAttribute(generic);
		return generic;
	}

	public static String[] getComponentTypeIDs(boolean includeEmptyEntry) {
		IConfigurationElement[] elems = getElements();
		int idx = elems.length;
		ArrayList<String> names = new ArrayList<String>(idx + 1);
		if (includeEmptyEntry)
			names.add(""); //$NON-NLS-1$
		while (--idx >= 0)
			names.add(elems[idx].getAttribute("id")); //$NON-NLS-1$
		Collections.sort(names);
		return names.toArray(new String[names.size()]);
	}

	public static IComponentType[] getComponentTypes() throws CoreException {
		CorePlugin plugin = CorePlugin.getDefault();
		String[] cids = getComponentTypeIDs(false);
		int idx = cids.length;
		IComponentType[] ctypes = new IComponentType[idx];
		while (--idx >= 0)
			ctypes[idx] = plugin.getComponentType(cids[idx]);
		return ctypes;
	}

	private static IConfigurationElement[] getElements() {
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		return exReg.getConfigurationElementsFor(CorePlugin.COMPONENT_TYPE_POINT);
	}

	private Pattern desiredNamePattern;

	private IMetaFile[] metaFiles = noMetaFiles;

	private String nameSubstitution;

	private IPath relativeLocation;

	private Pattern substituteNamePattern;

	@Override
	public Version getComponentVersion(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException {
		BOMNode node = getResolution(rInfo, true, monitor);
		return node.getResolution().getComponentIdentifier().getVersion();
	}

	@Override
	public Pattern getDesiredNamePattern() {
		return desiredNamePattern;
	}

	@Override
	public IMetaFile[] getMetaFiles() {
		return metaFiles;
	}

	@Override
	public String getNameSubstitution() {
		return nameSubstitution;
	}

	@Override
	public String getProjectName(String componentName) throws CoreException {
		if (componentName == null)
			return null;

		Pattern desiredMatch = getDesiredNamePattern();
		if (desiredMatch == null || desiredMatch.matcher(componentName).find())
			//
			// We have a component type but no desire to change the name
			//
			return componentName;

		Pattern repFrom = getSubstituteNamePattern();
		String repTo = getNameSubstitution();

		if (repFrom == null || repTo == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Component_type_0_defines_desiredNamePattern_but_no_substitution, getId()));

		Matcher matcher = repFrom.matcher(componentName);
		if (matcher.matches()) {
			String repl = matcher.replaceAll(repTo).trim();
			if (repl.length() > 0)
				componentName = repl;
		}
		return componentName;
	}

	@Override
	public IPath getRelativeLocation() {
		return relativeLocation;
	}

	@Override
	public final BOMNode getResolution(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException {
		return getResolution(rInfo, false, monitor);
	}

	@Override
	public Pattern getSubstituteNamePattern() {
		return substituteNamePattern;
	}

	@Override
	public VersionRange getTypeSpecificDesignator(VersionRange designator) {
		return designator;
	}

	@Override
	public boolean hasAllRequiredMetaFiles(IPath path) {
		for (IMetaFile metaFile : getMetaFiles()) {
			if (metaFile.isOptional() || path.append(metaFile.getPath()).toFile().exists())
				continue;

			boolean found = false;
			for (IPath alias : metaFile.getAliases()) {
				if (path.append(alias).toFile().exists()) {
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}

	@Override
	public boolean isMetaFileBased() {
		for (IMetaFile metaFile : getMetaFiles())
			if (!metaFile.isOptional())
				return true;
		return false;
	}

	@Override
	public void setExtensionParameter(String key, String value) throws CoreException {
		if ("relativeLocation".equals(key)) //$NON-NLS-1$
			relativeLocation = value == null ? null : Path.fromPortableString(value);
		else if ("desiredNamePattern".equals(key)) //$NON-NLS-1$
			desiredNamePattern = value == null ? null : Pattern.compile(value);
		else if ("substituteNamePattern".equals(key)) //$NON-NLS-1$
			substituteNamePattern = value == null ? null : Pattern.compile(value);
		else if ("nameSubstitution".equals(key)) //$NON-NLS-1$
			nameSubstitution = value;
		else
			super.setExtensionParameter(key, value);
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		String tmp = config.getAttribute("relativeLocation"); //$NON-NLS-1$
		this.relativeLocation = tmp == null ? null : Path.fromPortableString(tmp);
		tmp = config.getAttribute("desiredNamePattern"); //$NON-NLS-1$
		this.desiredNamePattern = tmp == null ? null : Pattern.compile(tmp);
		tmp = config.getAttribute("substituteNamePattern"); //$NON-NLS-1$
		this.substituteNamePattern = tmp == null ? null : Pattern.compile(tmp);
		this.nameSubstitution = config.getAttribute("nameSubstitution"); //$NON-NLS-1$

		ArrayList<IMetaFile> metaFileList = null;
		for (IConfigurationElement metaFile : config.getChildren("metaFile")) //$NON-NLS-1$
		{
			tmp = metaFile.getAttribute("path"); //$NON-NLS-1$
			if (tmp != null) {
				tmp = tmp.trim();
				if (tmp.length() == 0)
					tmp = null;
			}
			if (tmp == null)
				continue;

			IPath path = Path.fromPortableString(tmp);
			boolean optional = "true".equalsIgnoreCase(metaFile.getAttribute("optional")); //$NON-NLS-1$ //$NON-NLS-2$
			List<IPath> aliasesBld = null;
			for (String alias : TextUtils.split(metaFile.getAttribute("aliases"), ",")) //$NON-NLS-1$ //$NON-NLS-2$
			{
				alias = alias.trim();
				if (alias.length() > 0) {
					if (aliasesBld == null)
						aliasesBld = new ArrayList<IPath>();
					aliasesBld.add(new Path(alias));
				}
			}
			IPath[] aliases = (aliasesBld == null) ? Trivial.EMPTY_PATH_ARRAY : aliasesBld.toArray(new IPath[aliasesBld.size()]);
			if (metaFileList == null)
				metaFileList = new ArrayList<IMetaFile>();
			metaFileList.add(new MetaFile(path, optional, aliases));
		}
		this.metaFiles = (metaFileList == null) ? noMetaFiles : metaFileList.toArray(new IMetaFile[metaFileList.size()]);
	}

	protected BOMNode getResolution(ProviderMatch rInfo, boolean forResolutionAidOnly, IProgressMonitor monitor) throws CoreException {
		IReaderType readerType = rInfo.getReaderType();
		if (readerType instanceof P2ReaderType) {
			// Component type etc. is given by the IU
			return ((P2ReaderType) readerType).getResolution(rInfo, monitor);
		}

		monitor.beginTask(null, 2000);
		IComponentReader[] readerHandle = new IComponentReader[1];
		try {
			IComponentReader reader = rInfo.getReader(MonitorUtils.subMonitor(monitor, 200));
			if (forResolutionAidOnly && reader.isFileSystemReader())
				forResolutionAidOnly = false;
			readerHandle[0] = reader;
			ComponentRequest request = rInfo.getNodeQuery().getComponentRequest();
			String componentType = request.getComponentTypeID();
			if (componentType != null && !getId().equals(componentType))
				throw new ComponentTypeMismatchException(request.getName(), componentType, getId());

			IResolutionBuilder builder = getResolutionBuilder(readerHandle[0], MonitorUtils.subMonitor(monitor, 800));
			return builder.build(readerHandle, forResolutionAidOnly, MonitorUtils.subMonitor(monitor, 1000));
		} finally {
			IOUtils.close(readerHandle[0]);
		}
	}
}
