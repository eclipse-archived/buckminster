/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.pde.test;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.buckminster.core.reader.P2ReaderType;
import org.eclipse.core.resources.IFolder;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;

/**
 * @author Dennis Huebner
 * 
 */
public class CategoryActionTest extends CommonPdeProjectTest {

	private static final String COMMON_PROJECT_TEST_FEATURE = "common.project.test.feature";

	public void testHiddenCategory() throws Exception {
		cleanBuild();
		runBuildFeatureAction("site.p2");
		IFolder buildFeatureOutput = getBuildFeatureOutputFolder();
		IFolder p2repo = buildFeatureOutput.getFolder("site.p2");
		assertNotNull("site.p2 folder was not created", p2repo);
		IMetadataRepository metadataRepository = P2ReaderType.getMetadataRepository(p2repo.getLocationURI(), monitor());
		// Check that the p2 repository contains our feature
		String iuID = COMMON_PROJECT_TEST_FEATURE + ".feature.group";
		IQueryResult<IInstallableUnit> queryResult = metadataRepository.query(QueryUtil.createIUQuery(iuID), monitor());
		Iterator<IInstallableUnit> iterator = queryResult.iterator();
		assertTrue(iuID + " not found in the given p2 repository", iterator.hasNext());
		IInstallableUnit hiddenFeature = iterator.next();
		assertFalse("Only one feature occurence is expected.", iterator.hasNext());
		// Iterate over all categories assert that our feature is not referenced
		for (IInstallableUnit iu : metadataRepository.query(QueryUtil.createIUCategoryQuery(), monitor()).toSet()) {
			Set<IInstallableUnit> categoryIUs = metadataRepository.query(QueryUtil.createIUCategoryMemberQuery(iu), monitor()).toSet();
			assertFalse("Found hidden feature in category '" + iu.getId() + "'", categoryIUs.contains(hiddenFeature));
		}
	}
}
