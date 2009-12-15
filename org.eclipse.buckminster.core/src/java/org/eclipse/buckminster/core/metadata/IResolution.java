package org.eclipse.buckminster.core.metadata;

import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.IFileInfo;

public interface IResolution extends IFileInfo
{
	String getArtifactInfo();

	List<String> getAttributes();

	String getComponentTypeId();

	/**
	 * Returns the CSpec at the time when this resolution was created. The actual cspec in the workspace might have
	 * changed since then.
	 * 
	 * @return The resolved cspec.
	 */
	ICSpecData getCSpec();

	/**
	 * If a branch or tag was involved when this resolution was found, that branch or tag will be returned by this
	 * method.
	 * 
	 * @return The matched selector or <code>null</code>.
	 */
	VersionSelector getMatchedBranchOrTag();

	/**
	 * Returns the OPML document
	 * 
	 * @return The OPML or <code>null</code> if no OPML was present
	 */
	IOPML getOPML();

	/**
	 * Returns the persistent identifier for the resolution. This is intended to be used when resolutions are stored in
	 * a database.
	 * 
	 * @return
	 */
	String getPersistentId();

	/**
	 * Returns the provider used when reading the repository.
	 * 
	 * @return the repository provider.
	 */
	Provider getProvider();

	/**
	 * The id of the reader type that can materialize this resolution.
	 * 
	 * @return The id of the reader type.
	 */
	String getReaderTypeId();

	/**
	 * The repository URI used in conjunction with the reader type when materializing this resolution.
	 * 
	 * @return The repository URI.
	 */
	String getRepository();

	/**
	 * @return Returns the properties.
	 */
	IComponentRequest getRequest();

	/**
	 * An additional filter that a provider or other mechanism can assing to the resolution. This filter is involved
	 * when resolving dependencies against this resolution.
	 * 
	 * @return A filter or <code>null</code>.
	 */
	Filter getResolutionFilter();

	/**
	 * If the resolution was based on a revision query, the matched revision is returned
	 * 
	 * @return The matched revision or <code>null</code> of the match did not involve revisions.
	 */
	String getSelectedRevision();

	/**
	 * If the resolution was based on a timestapm query, then the matched timestamp is returned here.
	 * 
	 * @return A timestamp or <code>null</code> if not applicable.
	 */
	Date getSelectedTimestamp();

	/**
	 * A version match instance assembled from other values of this resolution.
	 * 
	 * @return A version match instance.
	 */
	VersionMatch getVersionMatch();

	/**
	 * Returns <code>true</code> if the reader associated with the component will be able to materialized the component.
	 * Readers that check for the existence of pre-installed components (such as Eclipse plugins that are already
	 * present in the running eclipse installation) will return <code>false</code>.
	 * 
	 * @return <code>true</code> if the component can be materialized on disk.
	 */
	boolean isMaterializable();

	/**
	 * Returns true if the component should be unpacked after it has been materialized. The semantics of this attribute
	 * is determined by the materializer but it will typcially mean unzipping or unjaring the file.
	 * 
	 * @return <code>true</code> if the component should be unpacked after installation.
	 */
	boolean isUnpack();
}
