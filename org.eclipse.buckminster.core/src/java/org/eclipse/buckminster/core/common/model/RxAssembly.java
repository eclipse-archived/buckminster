/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class RxAssembly extends AbstractSaxableElement
{
	public static final String TAG = "rxAssembly"; //$NON-NLS-1$

	public static final String ATTR_REPLACEMENT = "replacement"; //$NON-NLS-1$

	private final List<RxPart> m_parts;

	private final Pattern m_pattern;

	private final List<RxPart> m_parameters = new ArrayList<RxPart>();

	public RxAssembly(List<RxPart> parts) throws CoreException, PatternSyntaxException
	{
		m_parts = Utils.createUnmodifiableList(parts);

		StringBuilder bld = new StringBuilder();
		bld.append('^');
		for(RxPart part : parts)
			part.addPattern(bld, m_parameters);
		bld.append('$');

		String patternStr = bld.toString();
		m_pattern = Pattern.compile(patternStr);
		CorePlugin.getLogger().debug("URI pattern %s created", patternStr); //$NON-NLS-1$
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		for(RxPart part : m_parts)
			part.toSax(handler, namespace, prefix, part.getDefaultTag());
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Map<String, String> getMatchMap(CharSequence input)
	{
		if(input == null)
			return null;

		Logger logger = CorePlugin.getLogger();
		Matcher m = m_pattern.matcher(input);
		if(!m.matches())
		{
			logger.debug("URI pattern does not match %s", input); //$NON-NLS-1$
			return null;
		}

		int groupCount = m.groupCount();
		int top = m_parameters.size();
		if(logger.isDebugEnabled())
		{
			logger.debug("URI pattern captured %d groups in %s", Integer.valueOf(groupCount), input); //$NON-NLS-1$
		}

		if(top != groupCount)
		{
			logger.warning(NLS.bind(Messages.URI_pattern_group_count_was_0_expected_1, Integer
					.valueOf(groupCount), Integer.valueOf(top)));
			top = groupCount;
		}

		if(top == 0)
			return Collections.emptyMap();

		HashMap<String, String> matchMap = new HashMap<String, String>();

		for(int idx = 0; idx < top; ++idx)
		{
			RxPart param = m_parameters.get(idx);
			String value = TextUtils.notEmptyString(m.group(idx + 1));
			if(value != null)
			{
				logger.debug("Assigning URI pattern parameter %s=\"%s\"", param.getName(), value); //$NON-NLS-1$
				matchMap.put(param.getName(), value);
			}
		}
		return matchMap;
	}

	public Pattern getPattern()
	{
		return m_pattern;
	}
}
