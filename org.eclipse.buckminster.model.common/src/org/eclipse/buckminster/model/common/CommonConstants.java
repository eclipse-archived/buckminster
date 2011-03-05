package org.eclipse.buckminster.model.common;

import org.eclipse.buckminster.runtime.Buckminster;


public interface CommonConstants {
	String COMPONENT_NAME = "buckminster.component"; //$NON-NLS-1$

	String COMPONENT_VERSION = "buckminster.version"; //$NON-NLS-1$

	String COMPONENT_TYPE = "buckminster.component.type"; //$NON-NLS-1$

	String COMPONENT_RANGE = "buckminster.version.designator"; //$NON-NLS-1$

	String FILTER_ECLIPSE_P2_OPTIONAL = "(!(eclipse.p2.optional=false))"; //$NON-NLS-1$

	String FILTER_SOURCE_BUNDLE = "(buckminster.download.source=true)"; //$NON-NLS-1$

	String FILTER_OPTIONAL_SOURCE_BUNDLE = "(&" + FILTER_ECLIPSE_P2_OPTIONAL + FILTER_SOURCE_BUNDLE + ')'; //$NON-NLS-1$

	String MUTABLERULE = "buckminster.rule.mutable"; //$NON-NLS-1$

	String SOURCERULE = "buckminster.rule.source"; //$NON-NLS-1$

	String LOGIN_NAME = "buckminster.login"; //$NON-NLS-1$

	String LOGIN_PASSWORD = "buckminster.password"; //$NON-NLS-1$

	String REFERENCE_REPOSITORY = "buckminster.reference.repository"; //$NON-NLS-1$

	String SNAPSHOT = "buckminster.snapshot"; //$NON-NLS-1$

	String OVERRIDE_ECLIPSE_INSTALLED = "buckminster.override.eclipse.installed"; //$NON-NLS-1$

	String ACTION_OUTPUT = "buckminster.output"; //$NON-NLS-1$

	String ACTION_HOME = "buckminster.home"; //$NON-NLS-1$

	String ACTION_TEMP = "buckminster.temp"; //$NON-NLS-1$

	String ACTION_HOME_REF = "${" + ACTION_HOME + "}"; //$NON-NLS-1$ //$NON-NLS-2$

	String ACTION_OUTPUT_REF = "${" + ACTION_OUTPUT + "}"; //$NON-NLS-1$ //$NON-NLS-2$

	String ACTION_TEMPDIR_REF = "${" + ACTION_TEMP + "}"; //$NON-NLS-1$ //$NON-NLS-2$

	String ACTION_TEMPROOT_REF = Buckminster.ACTION_TEMP_ROOT; //$NON-NLS-1$ //$NON-NLS-2$

	String BUILD_ID = "buckminster.build.id"; //$NON-NLS-1$
}
