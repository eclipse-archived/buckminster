/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.PathGroup;
import org.eclipse.buckminster.cspec.SelfArtifact;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Self Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class SelfArtifactImpl extends ArtifactImpl implements SelfArtifact {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected SelfArtifactImpl() {
		super();
	}

	@Override
	public String getName() {
		return CSpec.SELF_ARTIFACT;
	}

	@Override
	public PathGroup resolve(IPath path) {
		PathGroup resolved = new PathGroupImpl();
		if (path.hasTrailingSeparator())
			//
			// A folder will act as the base for the component
			//
			resolved.setBase(path);
		else {
			// The parent folder will be the base since the component itself
			// is a file.
			//
			resolved.setBase(path.removeLastSegments(1).addTrailingSeparator());
			resolved.getPaths().add(Path.fromPortableString(path.lastSegment()));
		}
		return resolved;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CspecPackage.Literals.SELF_ARTIFACT;
	}
} // SelfArtifactImpl
