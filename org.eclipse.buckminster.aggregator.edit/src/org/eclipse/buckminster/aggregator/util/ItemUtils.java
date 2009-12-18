/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MapRule;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2view.IUPresentation;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Karel Brezina
 * 
 */
public class ItemUtils
{

	/**
	 * Tries to add an InstallableUnit to a MappedRepository
	 * 
	 * @param mappedRepo
	 *            mapped repository
	 * @param iu
	 *            installable unit
	 * @return null if the MappedRepository already contains the InstallableUnit or MappedUnit (created from the IU) if
	 *         the InstallableUnit was added
	 */
	public static MappedUnit addIU(MappedRepository mappedRepo, InstallableUnit iu)
	{
		if(iu == null)
			return null;

		MappedUnit foundUnit = null;

		for(MappedUnit unit : mappedRepo.getUnits(false))
			if(unit.getInstallableUnit() != null
					&& (iu == unit.getInstallableUnit() || iu.getId() != null
							&& unit.getInstallableUnit().getId() != null
							&& iu.getId().equals(unit.getInstallableUnit().getId())))
			{
				foundUnit = unit;
			}

		if(foundUnit != null)
			return null;

		MappedUnit newMU = AggregatorFactory.eINSTANCE.createMappedUnit(iu);
		mappedRepo.addUnit(newMU);

		return newMU;
	}

	/**
	 * Tries to add a MappedRule to a MappedRepository
	 * 
	 * @param mappedRepo
	 *            mapped repository
	 * @param iu
	 *            installable unit
	 * @param ruleClass
	 *            defines which kind of rule is required
	 * @return null if the MappedRepository already contains a MapRule created from the InstallableUnit or MapRule
	 *         (created from the IU) if the InstallableUnit was added
	 */
	public static MapRule addMapRule(MappedRepository mappedRepo, InstallableUnit iu, Class<? extends MapRule> ruleClass)
	{
		if(iu == null)
			return null;

		MapRule foundRule = null;

		for(MapRule rule : mappedRepo.getMapRules())
			if(rule.getInstallableUnit() != null
					&& (iu == rule.getInstallableUnit() || iu.getId() != null
							&& rule.getInstallableUnit().getId() != null
							&& iu.getId().equals(rule.getInstallableUnit().getId())))
			{
				foundRule = rule;
			}

		if(foundRule != null)
			return null;

		MapRule newMR = AggregatorFactory.eINSTANCE.createMapRule(iu, ruleClass);
		mappedRepo.getMapRules().add(newMR);

		return newMR;
	}

	/**
	 * Tries to add a MetadataRepository to a Contribution
	 * 
	 * @param contribution
	 *            contribution
	 * @param mdr
	 *            metadata repository
	 * @return null if the Contribution already contains the MetadataRepository or MappedRepository (created from the
	 *         MDR) if the MetadataRepository was added
	 */
	public static MappedRepository addMDR(Contribution contribution, MetadataRepository mdr)
	{
		if(mdr == null)
			return null;

		if(findMappedRepository(contribution, mdr) != null)
			return null;

		MappedRepository newMappedRepo = AggregatorFactory.eINSTANCE.createMappedRepository(mdr);
		contribution.getRepositories().add(newMappedRepo);

		return newMappedRepo;
	}

	public static MappedRepository findMappedRepository(Aggregator aggregator, MetadataRepository mdr)
	{
		for(Contribution contribution : aggregator.getContributions())
		{
			MappedRepository repo = findMappedRepository(contribution, mdr);
			if(repo != null)
				return repo;
		}

		return null;
	}

	/**
	 * Searches for a MappedRepository with the same location
	 * 
	 * @param mappedRepos
	 * @param mappedRepo
	 * @return
	 */
	public static MappedRepository findMappedRepository(Contribution contribution, MetadataRepository mdr)
	{
		if(mdr == null)
			return null;

		for(MappedRepository repo : contribution.getRepositories())
			if(mdr.equals(repo.getMetadataRepository()) && repo.isBranchEnabled())
				return repo;

		return null;
	}

	public static MappedUnit findMappedUnit(Aggregator aggregator, InstallableUnit iu)
	{
		MetadataRepository mdr = (MetadataRepository)((EObject)iu).eContainer();

		for(Contribution contribution : aggregator.getContributions())
			for(MappedRepository repo : contribution.getRepositories())
			{
				if(!mdr.equals(repo.getMetadataRepository()))
					continue;

				for(MappedUnit unit : repo.getUnits(false))
					if(iu.equals(unit.getInstallableUnit()))
						return unit;
			}

		return null;
	}

	public static MappedUnit findMappedUnit(MappedRepository mappedRepo, InstallableUnit iu)
	{
		for(MappedUnit unit : mappedRepo.getUnits(false))
			if(iu.equals(unit.getInstallableUnit()))
				return unit;

		return null;
	}

	public static Collection<? extends InstallableUnit> getIUs(Collection<? extends IUPresentation> iups)
	{
		Set<InstallableUnit> set = new HashSet<InstallableUnit>();

		for(IUPresentation iup : iups)
			if(iup.getInstallableUnit() != null)
				set.add(iup.getInstallableUnit());

		return set;
	}

	public static Collection<MetadataRepository> getMDRs(Collection<? extends MetadataRepositoryStructuredView> mdrsvs)
	{
		Set<MetadataRepository> set = new HashSet<MetadataRepository>();

		for(MetadataRepositoryStructuredView mdrsv : mdrsvs)
			if(mdrsv.getMetadataRepository() != null)
				set.add(mdrsv.getMetadataRepository());

		return set;
	}

	/**
	 * Tests if selectedUIs come from a MDR with the same location as mappedRepo
	 * 
	 * @param mappedRepo
	 * @param selectedIUs
	 * @return
	 */
	public static boolean haveSameLocation(MappedRepository mappedRepo,
			Collection<? extends InstallableUnit> selectedIUs)
	{
		String location = mappedRepo.getResolvedLocation();

		if(location == null)
			return false;

		for(InstallableUnit iu : selectedIUs)
		{
			if(!(((EObject)iu).eContainer() instanceof MetadataRepository))
				return false;
			MetadataRepository mdr = (MetadataRepository)((EObject)iu).eContainer();
			if(mdr.getLocation() == null)
				return false;
			if(!(location.equalsIgnoreCase(mdr.getLocation().toString())))
				return false;
		}

		return true;
	}
}
