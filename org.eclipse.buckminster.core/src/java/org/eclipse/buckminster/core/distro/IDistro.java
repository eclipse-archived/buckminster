package org.eclipse.buckminster.core.distro;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.opml.IOPML;

public interface IDistro
{
	CSpec getCspec();

	ComponentQuery getCquery();

	MaterializationSpec getMspec();

	IOPML getOpml();
}
