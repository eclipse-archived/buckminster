/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.team;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.reader.ITeamReaderType;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.core.RepositoryProvider;

/**
 * Implementation of the Workspace Resolution Tagging actor. When invoked on a
 * component this actor finds all its dependencies (even transitive) present in
 * the workspace and tags them with a tag according to the actor properties.
 * 
 * @author michal.ruzicka@cloudsmith.com
 */
public class TagTeamActor extends AbstractTeamActor<TagTeamActor.TagContext> {

	protected static class TagContext extends TeamPerformContext {

		private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+"); //$NON-NLS-1$

		private String tag;

		private HashMap<String, LinkedHashMap<String, String>> mappings = new HashMap<String, LinkedHashMap<String, String>>();

		private Pattern[] includes, excludes;

		public TagContext(IActionContext actionContext) throws CoreException {
			super(actionContext);

			TreeMap<Integer, Map.Entry<String, ?>> sortedMappings = new TreeMap<Integer, Map.Entry<String, ?>>();
			TreeMap<Integer, Map.Entry<String, ?>> sortedIncludes = new TreeMap<Integer, Map.Entry<String, ?>>();
			TreeMap<Integer, Map.Entry<String, ?>> sortedExcludes = new TreeMap<Integer, Map.Entry<String, ?>>();
			StringBuilder message = null;

			// walk through the supplied actor properties and process those of
			// interest
			for (Map.Entry<String, ?> property : actionContext.getAction().getActorProperties().entrySet()) {
				String name = property.getKey();
				if (name.startsWith(MAPPING_PROPERTY_PREFIX)) {
					try {
						int key = Integer.parseInt(name.substring(MAPPING_PROPERTY_PREFIX.length()));
						if (key >= 0) {
							sortedMappings.put(Integer.valueOf(key), property);
							continue;
						}
					} catch (NumberFormatException e) {
						// fall through
					}
				} else if (name.startsWith(EXCLUDE_PROPERTY_PREFIX)) {
					try {
						int key = Integer.parseInt(name.substring(EXCLUDE_PROPERTY_PREFIX.length()));
						if (key >= 0) {
							sortedExcludes.put(Integer.valueOf(key), property);
							continue;
						}
					} catch (NumberFormatException e) {
						// fall through
					}
				} else if (name.startsWith(INCLUDE_PROPERTY_PREFIX)) {
					try {
						int key = Integer.parseInt(name.substring(INCLUDE_PROPERTY_PREFIX.length()));
						if (key >= 0) {
							sortedIncludes.put(Integer.valueOf(key), property);
							continue;
						}
					} catch (NumberFormatException e) {
						// fall through
					}
				} else if (name.equals(TAG_PROPERTY_NAME)) {
					tag = ExpandingProperties.expand(actionContext.getProperties(), (String) property.getValue(), 0);
					continue;
				}
				if (message == null)
					message = new StringBuilder();
				else
					message.append(Messages.list_separator);
				message.append(name);
			}

			if (tag == null || tag.length() == 0)
				throw new CoreException(createStatus(NLS.bind(Messages.required_properties_not_supplied_0, TAG_PROPERTY_NAME)));

			if (message != null)
				throw new CoreException(createStatus(NLS.bind(Messages.unrecognized_properties_supplied_0, message.toString()) + '\n'
						+ NLS.bind(Messages.recognized_properties_0, TAG_PROPERTY_NAME + Messages.list_separator + MAPPING_PROPERTY_PREFIX + "<n>" //$NON-NLS-1$
								+ Messages.list_separator + INCLUDE_PROPERTY_PREFIX + "<n>" + Messages.list_separator + EXCLUDE_PROPERTY_PREFIX //$NON-NLS-1$
								+ "<n>"))); //$NON-NLS-1$

			for (Map.Entry<String, ?> mapping : sortedMappings.values()) {
				String mappingString = ExpandingProperties.expand(actionContext.getProperties(), (String) mapping.getValue(), 0);
				String[] mappingFields = WHITESPACE_PATTERN.split(mappingString.trim(), 0);
				if (mappingFields.length != 3) {
					if (message == null)
						message = new StringBuilder();
					else
						message.append('\n');
					message.append(NLS.bind(Messages._0_entry_1_invalid_2, new Object[] { Messages.mapping_label, mapping.getKey(),
							Messages.mapping_entry_not_exactly_three_fields }));
					continue;
				}

				LinkedHashMap<String, String> readerTypeSpecificMappings = mappings.get(mappingFields[0]);
				if (readerTypeSpecificMappings == null) {
					readerTypeSpecificMappings = new LinkedHashMap<String, String>();
					mappings.put(mappingFields[0], readerTypeSpecificMappings);
				}
				readerTypeSpecificMappings.put(mappingFields[1], mappingFields[2]);
			}

			ArrayList<Pattern> patternList = new ArrayList<Pattern>(Math.max(sortedIncludes.size(), sortedExcludes.size()));
			for (Map.Entry<String, ?> include : sortedIncludes.values()) {
				String includeString = ExpandingProperties.expand(actionContext.getProperties(), (String) include.getValue(), 0);
				Pattern includePattern;
				try {
					includePattern = Pattern.compile(includeString);
				} catch (PatternSyntaxException e) {
					if (message == null)
						message = new StringBuilder();
					else
						message.append('\n');
					message.append(NLS.bind(Messages._0_entry_1_invalid_2, new Object[] { Messages.include_label, include.getKey(),
							NLS.bind(Messages.specified_pattern_is_invalid_0, e.getMessage()) }));
					continue;
				}

				patternList.add(includePattern);
			}

			includes = patternList.toArray(new Pattern[patternList.size()]);

			patternList.clear();
			for (Map.Entry<String, ?> exclude : sortedExcludes.values()) {
				String excludeString = ExpandingProperties.expand(actionContext.getProperties(), (String) exclude.getValue(), 0);
				Pattern excludePattern;
				try {
					excludePattern = Pattern.compile(excludeString);
				} catch (PatternSyntaxException e) {
					if (message == null)
						message = new StringBuilder();
					else
						message.append('\n');
					message.append(NLS.bind(Messages._0_entry_1_invalid_2, new Object[] { Messages.exclude_label, exclude.getKey(),
							NLS.bind(Messages.specified_pattern_is_invalid_0, e.getMessage()) }));
					continue;
				}

				patternList.add(excludePattern);
			}

			if (message != null)
				throw new CoreException(createStatus(NLS.bind(Messages.property_settings_problems_0, message.toString())));

			excludes = patternList.toArray(new Pattern[patternList.size()]);
		}

		public Map<String, String> getMappings(String readerTypeId) {
			LinkedHashMap<String, String> readerTypeSpecificMappings = mappings.get(readerTypeId);
			if (readerTypeSpecificMappings == null)
				return Collections.emptyMap();
			return readerTypeSpecificMappings;
		}

		public String getTag() {
			return tag;
		}

		public boolean shouldExclude(String name) {
			for (Pattern include : includes) {
				if (include.matcher(name).find())
					return false;
			}
			for (Pattern exclude : excludes) {
				if (exclude.matcher(name).find())
					return true;
			}
			return false;
		}
	}

	public static final String TAG_PROPERTY_NAME = "tag"; //$NON-NLS-1$

	public static final String MAPPING_PROPERTY_PREFIX = "mapping."; //$NON-NLS-1$

	public static final String INCLUDE_PROPERTY_PREFIX = "include."; //$NON-NLS-1$

	public static final String EXCLUDE_PROPERTY_PREFIX = "exclude."; //$NON-NLS-1$

	@Override
	public String getActionName(IActionContext actionContext) {
		return NLS.bind(Messages.workspace_resolution_tagging_of_0, actionContext.getAction().getCSpec().getComponentIdentifier().toString());
	}

	@Override
	protected TagContext createTeamPerformContext(IActionContext actionContext) throws CoreException {
		return new TagContext(actionContext);
	}

	@Override
	protected String getTaskName(RepositoryProvider provider) {
		return NLS.bind(Messages.tagging_project_0, provider.getProject().getName());
	}

	@Override
	protected void processResources(TagContext tagContext, RepositoryProvider provider, IResource[] resources, boolean recurse,
			IProgressMonitor monitor) throws CoreException, InterruptedException {
		ITeamReaderType readerType = tagContext.getCachedReaderTypeForRepositoryProvider(provider.getID());
		Map<String, String> mappings = tagContext.getMappings(readerType.getId());
		String tag = tagContext.getTag();

		IStatus status = readerType.tag(provider, resources, mappings, tag, recurse, monitor);
		if (status.matches(IStatus.INFO | IStatus.WARNING)) {
			PrintStream outputPrintStream = tagContext.getActionContext().getOutputStream();
			BuckminsterException.deeplyPrint(new CoreException(status), outputPrintStream, false);
		} else
			tagContext.collectStatus(status);
	}

	@Override
	protected boolean shouldExclude(TagContext tagContext, IResource resource) {
		return tagContext.shouldExclude(resource.getName());
	}

}
