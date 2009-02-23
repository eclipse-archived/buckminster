/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class AlterGroup extends AlterAttribute<Group>
{
	public static final String ELEM_ALTER_ATTRIBUTE = "alterAttribute"; //$NON-NLS-1$

	public static final String ELEM_REMOVE = "remove"; //$NON-NLS-1$

	private final Map<String, Prerequisite> m_alteredPrerequisites;

	private final Set<String> m_removedPrerequisites;

	public AlterGroup(Group base, Set<String> removedHints, Map<String, String> alteredHints,
			Set<String> removedPrerequisites, Map<String, Prerequisite> alteredPrerequisites)
	{
		super(base, removedHints, alteredHints);
		m_removedPrerequisites = Utils.createUnmodifiableSet(removedPrerequisites);
		m_alteredPrerequisites = Utils.createUnmodifiableMap(alteredPrerequisites);
	}

	@Override
	public void alterAttribute(TopLevelAttributeBuilder original) throws CoreException
	{
		Group base = getBase();
		GroupBuilder gBld = (GroupBuilder)original;
		alterInstallerHints(gBld);
		alterPrerequisiteMap(gBld);
		alterDocumentation(gBld);
		gBld.setPrerequisiteRebase(CSpecExtension.overrideCheckNull(gBld.getPrerequisiteRebase(), base
				.getPrerequisiteRebase()));
	}

	protected void alterPrerequisiteMap(GroupBuilder original) throws CoreException
	{
		Group base = this.getBase();
		String attrName = original.getName();
		String compName = original.getCSpecName();
		List<? extends IPrerequisite> pqs = original.getPrerequisites();
		List<Prerequisite> addedPqs = base.getPrerequisites();

		if(!(addedPqs.isEmpty() && m_alteredPrerequisites.isEmpty() && m_removedPrerequisites.isEmpty()))
		{
			for(String pqName : m_removedPrerequisites)
				if(GroupBuilder.indexOfPrerequisite(pqs, pqName) < 0)
					throw new MissingPrerequisiteException(compName, attrName, pqName);

			for(String pqName : m_alteredPrerequisites.keySet())
				if(GroupBuilder.indexOfPrerequisite(pqs, pqName) < 0)
					throw new MissingPrerequisiteException(compName, attrName, pqName);

			for(IPrerequisite pq : addedPqs)
			{
				String pqName = pq.toString();
				if(GroupBuilder.indexOfPrerequisite(pqs, pqName) >= 0)
					throw new PrerequisiteAlreadyDefinedException(compName, attrName, pqName);
			}

			for(String pqName : m_removedPrerequisites)
				pqs.remove(pqName);
			for(IPrerequisite pq : m_alteredPrerequisites.values())
				((PrerequisiteBuilder)pqs.get(GroupBuilder.indexOfPrerequisite(pqs, pq.toString()))).initFrom(pq);
			for(Prerequisite pq : addedPqs)
			{
				PrerequisiteBuilder bld = original.createPrerequisiteBuilder();
				bld.initFrom(pq);
				original.addPrerequisite(bld);
			}
		}
	}

}
