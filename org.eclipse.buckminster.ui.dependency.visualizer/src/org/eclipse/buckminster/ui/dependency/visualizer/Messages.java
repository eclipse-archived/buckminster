package org.eclipse.buckminster.ui.dependency.visualizer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.ui.dependency.visualizer.messages"; //$NON-NLS-1$

	public static String All;

	public static String Blacklist;

	public static String Branch;

	public static String Dependencies;

	public static String DependencyVisualisation;

	public static String DirectedLayout;

	public static String Filters;

	public static String FilterTargetPlatform;

	public static String Graph;

	public static String GridLayout;

	public static String HorizontalLayout;

	public static String HorizontalTreeLayout;

	public static String Layout;

	public static String ListMustContainExactlyOneBom;

	public static String Navigation;

	public static String None;

	public static String OpenDepencencyGraphAction_OpenEditorErrorMessage;

	public static String OpenDepencencyGraphAction_ProcessingGraphMainTaskLabel;

	public static String OpenDepencencyGraphAction_ProcessingItemTaskLabel;

	public static String OpenDepencencyGraphAction_ResolvingDependencyGraphJobTitle;

	public static String PathHighlighting;

	public static String PathToRoot;

	public static String RadialLayout;

	public static String ReaderType;

	public static String Repository;

	public static String SelectedDate;

	public static String SelectedRevision;

	public static String Settings;

	public static String Shortest;

	public static String SpringLayout;

	public static String Tag;

	public static String TreeLayout;

	public static String Type;

	public static String UnresolvedNode;

	public static String Usage;

	public static String Version;

	public static String VersionRange;

	public static String VerticalLayout;

	public static String Whilelist;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
