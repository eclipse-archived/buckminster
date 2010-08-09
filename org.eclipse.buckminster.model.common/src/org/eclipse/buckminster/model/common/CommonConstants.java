package org.eclipse.buckminster.model.common;

public interface CommonConstants {
	String COMPONENT_NAME = "buckminster.component"; //$NON-NLS-1$

	String COMPONENT_VERSION = "buckminster.version"; //$NON-NLS-1$

	String COMPONENT_TYPE = "buckminster.component.type"; //$NON-NLS-1$

	String COMPONENT_RANGE = "buckminster.version.designator"; //$NON-NLS-1$

	String FILTER_ECLIPSE_P2_OPTIONAL = "(!(eclipse.p2.optional=false))"; //$NON-NLS-1$

	String FILTER_SOURCE_BUNDLE = "(buckminster.download.source=true)"; //$NON-NLS-1$

	String FILTER_OPTIONAL_SOURCE_BUNDLE = "(&" + FILTER_ECLIPSE_P2_OPTIONAL + FILTER_SOURCE_BUNDLE + ')'; //$NON-NLS-1$
}
