package org.eclipse.buckminster.core.distro;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.core.query.IComponentQuery;
import org.eclipse.buckminster.opml.IOPML;

public interface IDistro
{
	ICSpecData getCspec();

	IComponentQuery getCquery();

	IMaterializationSpec getMspec();

	IOPML getOpml();
}
