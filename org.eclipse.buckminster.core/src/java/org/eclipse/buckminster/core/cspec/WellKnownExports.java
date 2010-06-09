package org.eclipse.buckminster.core.cspec;

public abstract class WellKnownExports {
	/**
	 * Denotes the export of jar files. Should always be accompanied with a
	 * corresponding {@link #JAVA_BINARIES} export to enable imports insensitive
	 * to if the export denotes folders of class files or jars.
	 */
	public static final String JAVA_BINARY_ARCHIVES = "java.binary.archives"; //$NON-NLS-1$

	/**
	 * Denotes the export of folders containing java binary artifacts (class
	 * files most typically). Should always be accompanied with a corresponding
	 * {@link #JAVA_BINARIES} export to enable imports insensitive to if the
	 * export denotes folders of class files or jars.
	 */
	public static final String JAVA_BINARY_FOLDERS = "java.binary.folders"; //$NON-NLS-1$

	/**
	 * Denotes the export of a jar file or a folder containing java binary
	 * artifacts (class files most typically). Should always be accompanied with
	 * a corresponding {@link #JAVA_BINARY_ARCHIVE} or a
	 * {@link #JAVA_BINARY_FOLDER}.
	 */
	public static final String JAVA_BINARIES = "java.binaries"; //$NON-NLS-1$

	/**
	 * Denotes the export of a zip files containing java source artifacts.
	 */
	public static final String JAVA_SOURCE_ARCHIVES = "java.source.archives"; //$NON-NLS-1$

	/**
	 * Denotes the export of folders containing java source artifacts.
	 */
	public static final String JAVA_SOURCE_FOLDERS = "java.source.folders"; //$NON-NLS-1$

	/**
	 * Denotes the export of a zip file or a folder containing java source
	 * artifacts.
	 */
	public static final String JAVA_SOURCES = "java.sources"; //$NON-NLS-1$
}
