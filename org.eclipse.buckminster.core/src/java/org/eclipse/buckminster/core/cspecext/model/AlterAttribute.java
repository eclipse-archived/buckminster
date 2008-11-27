/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.MissingPathException;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.PathAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.PropertyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public abstract class AlterAttribute<T extends TopLevelAttribute>
{
	public static final String ELEM_ALTER_INSTALLER_HINTS = "alterInstallerHints";

	static void alterPaths(String compName, String attrName, Set<IPath> original, Set<IPath> added, Set<IPath> removed)
			throws CoreException
	{
		if(!(added.isEmpty() && removed.isEmpty()))
		{
			for(IPath path : removed)
				if(!original.contains(path))
					throw new MissingPathException(compName, attrName, path);

			for(IPath path : added)
				if(original.contains(path))
					throw new PathAlreadyDefinedException(compName, attrName, path);

			original.addAll(original);
			original.removeAll(removed);
			original.addAll(added);
		}
	}

	static void performPropertyAlterations(String compName, String attrName, String propertyCategory,
			Map<String, String> original, Map<String, String> altered, Map<String, String> added, Set<String> removed)
			throws CoreException
	{
		// Assert that all hints to remove really exists
		//
		if(removed.isEmpty() && altered.isEmpty() && added.isEmpty())
			return;

		for(String key : removed)
			if(!original.containsKey(key))
				throw new MissingPropertyException(compName, attrName, propertyCategory, key);

		// Assert that all hints to be altered really exists
		//
		for(String key : altered.keySet())
			if(!original.containsKey(key))
				throw new MissingPropertyException(compName, attrName, propertyCategory, key);

		// Assert that we don't already have hints to be added
		//
		for(String key : added.keySet())
			if(original.containsKey(key))
				throw new PropertyAlreadyDefinedException(compName, attrName, propertyCategory, key);

		for(String key : removed)
			original.remove(key);
		original.putAll(added);
		original.putAll(altered);
	}

	private final T m_base;

	private final Map<String, String> m_alteredHints;

	private final Set<String> m_removedHints;

	protected AlterAttribute(T base, Set<String> removedHints, Map<String, String> alteredHints)
	{
		m_base = base;
		m_removedHints = Utils.createUnmodifiableSet(removedHints);
		m_alteredHints = ExpandingProperties.createUnmodifiableProperties(alteredHints);
	}

	/**
	 * Creates a new Attribute instance from the <code>originalAttribute</code> that has the alterations defined by this
	 * instance applied.
	 * 
	 * @param name
	 *            The original attribute.
	 * @throws CoreException
	 */
	public abstract void alterAttribute(TopLevelAttributeBuilder attrBld) throws CoreException;

	public void alterDocumentation(TopLevelAttributeBuilder original)
	{
		Documentation origDoc = original.getDocumentation();
		Documentation baseDoc = m_base.getDocumentation();
		original.setDocumentation(origDoc == null
				? baseDoc
				: origDoc.merge(baseDoc));
	}

	public void alterInstallerHints(TopLevelAttributeBuilder original) throws CoreException
	{
		// Assert that all hints to remove really exists
		//
		performPropertyAlterations(original.getCSpecName(), original.getName(), "installer hint", original
				.getInstallerHints(), m_alteredHints, m_base.getInstallerHints(), m_removedHints);
	}

	/**
	 * Returns the base for the alteration image. The base represents all attribute chagnes and collection additions.
	 * Collection removals and alterations to collection elements will be handled by extra additions.
	 * 
	 * @return THe base for the alteration image.
	 */
	protected final T getBase()
	{
		return m_base;
	}

	/**
	 * Returns the name of this alteration. The name must correspond to the attribute for which the alteration will be
	 * applied.
	 * 
	 * @return The name of the alteration.
	 */
	public String getName()
	{
		return m_base.getName();
	}

	/**
	 * Checks if this alteration is applicable for the given attribute with respect to its type.
	 * 
	 * @param originalAttribute
	 *            The attribute to check
	 * @return true if the type is conformant.
	 */
	public boolean isTypeConformant(TopLevelAttribute origAttr)
	{
		return origAttr.getClass().equals(m_base.getClass());
	}
}
