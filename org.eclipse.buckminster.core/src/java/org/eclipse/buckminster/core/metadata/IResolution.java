package org.eclipse.buckminster.core.metadata;

import java.util.List;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.runtime.IFileInfo;

public interface IResolution extends IFileInfo
{
	List<String> getAttributes();

	/**
	 * Returns the CSpec at the time when this resolution was created. The actual cspec in the
	 * workspace might have changed since then.
	 * @return The resolved cspec.
	 */
	ICSpecData getCSpec();

	/**
	 * Returns the OPML document
	 * @return The OPML or <code>null</code> if no OPML was present
	 */
	IOPML getOPML();

	String getComponentTypeId();

	/**
	 * Returns the provider used when reading the repository.
	 * @return the repository provider.
	 */
	Provider getProvider();

	String getRepository();

	/**
	 * @return Returns the properties.
	 */
	IComponentRequest getRequest();

	VersionMatch getVersionMatch();

	/**
	 * Returns <code>true</code> if the reader associated with the component will be able to
	 * materialized the component. Readers that check for the existence of pre-installed components
	 * (such as Eclipse plugins that are already present in the running eclipse installation) will
	 * return <code>false</code>.
	 * @return <code>true</code> if the component can be materialized on disk.
	 */
	boolean isMaterializable();

}
