package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.prefs.IPreferenceDescriptor;
import org.eclipse.core.runtime.CoreException;

/**
 * Interface for factories that can create {@link IResolver} instances.
 * @author Thomas Hallgren
 */
public interface IResolverFactory extends IBuckminsterExtension
{
	/**
	 * Initialize default preferences (if any)
	 */
	void initDefaultPreferences();

	/**
	 * Create an IResolver implementation and associate it with
	 * the context
	 * @param context The context to associate with the new resolver
	 * @return The resolver
	 */
	IResolver createResolver(RMContext context) throws CoreException;

	/**
	 * Returns descriptors for the preferences managed by this resolver
	 * factory.
	 * @return descriptors used when setting up a preference page.
	 */
	IPreferenceDescriptor[] getPreferenceDescriptors();
}
