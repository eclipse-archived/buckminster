package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.buckminster.core.prefedit.IPreferenceDescriptor;
import org.eclipse.core.runtime.CoreException;

/**
 * Interface for factories that can create {@link IResolver} instances.
 * 
 * @author Thomas Hallgren
 */
public interface IResolverFactory extends IBuckminsterExtension {
	/**
	 * Create an IResolver implementation and associate it with the context
	 * 
	 * @param context
	 *            The context to associate with the new resolver
	 * @return The resolver
	 */
	IResolver createResolver(ResolutionContext context) throws CoreException;

	/**
	 * Returns descriptors for the preferences managed by this resolver factory.
	 * 
	 * @return descriptors used when setting up a preference page.
	 */
	IPreferenceDescriptor[] getPreferenceDescriptors();

	/**
	 * Returns the priority for the resolver. The priority determines the order
	 * in which the resolver will be placed into the active resolver list. A
	 * negative value indicates that the resolver is optional and must be added
	 * manually. The resolver with the value zero will be placed first in the
	 * list.
	 * 
	 * @return The resolver priority
	 */
	int getResolutionPriority();

	/**
	 * Initialize default preferences (if any)
	 */
	void initDefaultPreferences();
}
