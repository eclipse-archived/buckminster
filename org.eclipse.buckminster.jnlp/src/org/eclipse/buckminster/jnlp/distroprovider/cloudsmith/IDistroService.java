/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider.cloudsmith;

import java.util.List;

import org.eclipse.buckminster.jnlp.distroprovider.DistroVariant;
import org.eclipse.buckminster.jnlp.distroprovider.PropertySet;

/**
 * @author Karel Brezina
 *
 */
public interface IDistroService
{
	List<DistroVariant> getDistroVariants(Long stackId, PropertySet platformProperties);
	
	DistroContent getDistro(Long variantId);
}
