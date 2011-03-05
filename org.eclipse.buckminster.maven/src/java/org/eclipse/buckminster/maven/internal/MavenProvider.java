/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.maven.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.maven.Messages;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class MavenProvider extends Provider {
	public static final String BM_MAVEN_PROVIDER_NS = XMLConstants.BM_PREFIX + "MavenProvider-1.0"; //$NON-NLS-1$

	public static final String BM_MAVEN_PROVIDER_PREFIX = "mp"; //$NON-NLS-1$

	public static final String ELEM_MAPPINGS = "mappings"; //$NON-NLS-1$

	public static final String ELEM_RULE = "rule"; //$NON-NLS-1$

	public static final String ELEM_SCOPES = "scopes"; //$NON-NLS-1$

	public static final String ATTR_TRANSITIVE = "transitive"; //$NON-NLS-1$

	/**
	 * Apply default rules. I.e.
	 * <ul>
	 * <li>If the component name contains a dot, then separate the group and
	 * artifact using the last dot.</li>
	 * <li>If no dot is found, then use the same name for the group and artifact
	 * </li>
	 * </ul>
	 * 
	 * @param name
	 *            the name of the component
	 * @return an entry with a Maven groupId and artifactId
	 */
	public static IMapEntry getDefaultGroupAndArtifact(String name) {
		int dotIdx = name.lastIndexOf('/');
		return (dotIdx > 0) ? new MapEntry(name, name.substring(0, dotIdx), name.substring(dotIdx + 1), null) : new MapEntry(name, name, name, null);
	}

	public static boolean getDefaultIsScopeExcluded() {
		return false;
	}

	/**
	 * Apply default rules. I.e.
	 * <ul>
	 * <li>If the <code>groupId</code> and <code>artifactId</code> are equal,
	 * use the <code>artifactId</code></li>
	 * <li>If the <code>groupId</code> and <code>artifactId</code> are
	 * different, use <code>groupId.artifactId</code></li>
	 * </ul>
	 * 
	 * @param groupId
	 *            the Maven group id
	 * @param artifactId
	 *            the Maven artifact id
	 * @return The default component name.
	 */
	public static String getDefaultName(String groupId, String artifactId) {
		if (groupId.equals(artifactId)) {
			// Contructs like <groupId>:<artifactId> are known to exist
			//
			int colonIdx = artifactId.indexOf(':');
			if (colonIdx < 0)
				return artifactId;

			groupId = artifactId.substring(0, colonIdx);
			artifactId = artifactId.substring(colonIdx + 1);
		}
		return groupId + '/' + artifactId;
	}

	public static boolean getDefaultTransitive() {
		return true;
	}

	private final Map<String, MapEntry> mappings;

	private final List<BidirectionalTransformer> rules;

	private final Map<String, Scope> scopes;

	private final boolean transitive;

	public MavenProvider(SearchPath searchPath, String remoteReaderType, String[] componentTypes, VersionConverterDesc versionConverterDesc,
			Format uri, Filter resolutionFilter, Map<String, String> properties, Documentation documentation, Map<String, MapEntry> mappings,
			List<BidirectionalTransformer> rules) {
		this(searchPath, remoteReaderType, componentTypes, versionConverterDesc, uri, resolutionFilter, properties, documentation, mappings, rules,
				Collections.<String, Scope> emptyMap(), true);
	}

	public MavenProvider(SearchPath searchPath, String remoteReaderType, String[] componentTypes, VersionConverterDesc versionConverterDesc,
			Format uri, Filter resolutionFilter, Map<String, String> properties, Documentation documentation, Map<String, MapEntry> mappings,
			List<BidirectionalTransformer> rules, Map<String, Scope> scopes, boolean transitive) {
		super(searchPath, remoteReaderType, componentTypes, versionConverterDesc, uri, null, null, resolutionFilter, properties, null, documentation);
		if (mappings == null)
			mappings = Collections.emptyMap();
		if (rules == null)
			rules = Collections.emptyList();
		if (scopes == null)
			scopes = Collections.emptyMap();
		this.mappings = mappings;
		this.rules = rules;
		this.scopes = scopes;
		this.transitive = transitive;
	}

	@Override
	public void addPrefixMappings(HashMap<String, String> prefixMappings) {
		super.addPrefixMappings(prefixMappings);
		prefixMappings.put(BM_MAVEN_PROVIDER_PREFIX, BM_MAVEN_PROVIDER_NS);
	}

	@Override
	public IVersionConverter getVersionConverter() throws CoreException {
		return CorePlugin.getDefault().getVersionConverter(IVersionConverter.TAG);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		attrs.addAttribute(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type", "xsi:type", //$NON-NLS-1$ //$NON-NLS-2$
				"CDATA", BM_MAVEN_PROVIDER_PREFIX + ":MavenProvider"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		super.emitElements(handler, namespace, prefix);
		if (mappings.size() == 0 && rules.size() == 0 && scopes.size() == 0)
			return;

		String qName = Utils.makeQualifiedName(BM_MAVEN_PROVIDER_PREFIX, ELEM_MAPPINGS);
		handler.startElement(BM_MAVEN_PROVIDER_NS, ELEM_MAPPINGS, qName, ISaxableElement.EMPTY_ATTRIBUTES);
		for (MapEntry mapping : mappings.values())
			mapping.toSax(handler, BM_MAVEN_PROVIDER_NS, BM_MAVEN_PROVIDER_PREFIX, mapping.getDefaultTag());
		for (BidirectionalTransformer rule : rules)
			rule.toSax(handler, BM_MAVEN_PROVIDER_NS, BM_MAVEN_PROVIDER_PREFIX, ELEM_RULE);
		handler.endElement(BM_MAVEN_PROVIDER_NS, ELEM_MAPPINGS, qName);

		String sqName = Utils.makeQualifiedName(BM_MAVEN_PROVIDER_PREFIX, ELEM_SCOPES);
		handler.startElement(BM_MAVEN_PROVIDER_NS, ELEM_SCOPES, sqName, ISaxableElement.EMPTY_ATTRIBUTES);
		for (Scope scope : scopes.values())
			scope.toSax(handler, BM_MAVEN_PROVIDER_NS, BM_MAVEN_PROVIDER_PREFIX, scope.getDefaultTag());
		handler.endElement(BM_MAVEN_PROVIDER_NS, ELEM_SCOPES, sqName);
	}

	String getComponentName(String groupId, String artifactId) throws CoreException {
		for (IMapEntry me : mappings.values()) {
			if (me.isMatchFor(groupId, artifactId))
				return me.getName();

			List<GroupAndArtifact> aliases = me.getAliases();
			int idx = aliases.size();
			while (--idx >= 0) {
				GroupAndArtifact alias = aliases.get(idx);
				if (alias.isMatchFor(groupId, artifactId))
					return me.getName();
			}
		}

		if (rules.size() > 0) {
			String compiled = groupId + '/' + artifactId;
			for (BidirectionalTransformer rule : rules) {
				String transformed = rule.transformTo(compiled);
				if (transformed != null)
					return transformed;
			}
		}
		return getDefaultName(groupId, artifactId);
	}

	IMapEntry getGroupAndArtifact(String name) throws CoreException {
		if (name.endsWith(".source")) //$NON-NLS-1$
			return new SourceMapEntry(getGroupAndArtifact(name.substring(0, name.length() - 7)));

		IMapEntry entry = mappings.get(name);
		if (entry != null)
			return entry;

		String transformed = null;
		for (BidirectionalTransformer rule : rules) {
			transformed = rule.transformFrom(name);
			if (transformed != null)
				break;
		}

		if (transformed == null)
			return getDefaultGroupAndArtifact(name);

		int slashPos = transformed.indexOf('/');
		if (slashPos < 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.the_result_of_applying_a_match_rule_had_no_separator_slash_0, transformed));

		return new MapEntry(name, transformed.substring(0, slashPos), transformed.substring(slashPos + 1), null);
	}

	boolean isScopeExcluded(String name) {
		if (name == null)
			return getDefaultIsScopeExcluded();

		if (scopes.size() == 0)
			// This specific scope isn't found, and no other scopes are
			// mentioned so default behaviour will be to fall back to default
			// behaviour of getting everything
			return getDefaultIsScopeExcluded();

		Scope scope = scopes.get(name);
		if (scope == null)
			// This specific scope isn't found, but our config does mention
			// other scopes so this should be excluded. We assume the user using
			// scopes will only want to mention scopes they care about,
			// otherwise they would have to know the names of every single scope
			// used throughout their maven repository
			return true;

		return scope.isExcluded();
	}

	boolean isTransitive() {
		return transitive;
	}
}
