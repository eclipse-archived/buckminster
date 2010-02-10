/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;

/**
 * This class will generate qualifiers based on the last modification timestamp.
 * The timestamp is obtained using the same @ IReaderType} that was used when
 * the component was first materialized
 * 
 * @author Thomas Hallgren
 */
public class BuildTimestampQualifierGenerator extends AbstractExtension implements IQualifierGenerator {
	public static final String DEFAULT_FORMAT = "'v'yyyyMMddHHmm"; //$NON-NLS-1$

	public static final String FORMAT_PROPERTY = "generator.buildTimestamp.format"; //$NON-NLS-1$

	public static final String TIMESTAMP_PROPERTY = "buckminster.build.timestamp"; //$NON-NLS-1$

	@Override
	public Version generateQualifier(IActionContext context, ComponentIdentifier cid, List<ComponentIdentifier> dependencies) throws CoreException {
		Version currentVersion = cid.getVersion();
		if (currentVersion == null)
			return null;

		Date timestamp = null;
		Object isoTS = context.getProperties().get(TIMESTAMP_PROPERTY);
		if (isoTS instanceof String) {
			try {
				timestamp = DateAndTimeUtils.fromISOFormat((String) isoTS);
			} catch (ParseException e) {
				throw BuckminsterException.fromMessage(e, NLS.bind(Messages.property_0_not_ISO_8601_conformant_timestamp_string, TIMESTAMP_PROPERTY));
			}
		} else
			timestamp = new Date();

		Map<String, ? extends Object> props = context.getProperties();
		String format = (String) props.get(FORMAT_PROPERTY);
		if (format == null)
			format = DEFAULT_FORMAT;

		DateFormat mf = new SimpleDateFormat(format);
		mf.setTimeZone(DateAndTimeUtils.UTC);
		mf.setLenient(false);

		String newQual = mf.format(timestamp);
		newQual = VersionHelper.getQualifier(currentVersion).replace("qualifier", newQual); //$NON-NLS-1$
		return VersionHelper.replaceQualifier(currentVersion, newQual);
	}
}
