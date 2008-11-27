/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider.cloudsmith;

import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.jnlp.distroprovider.DistroVariant;
import org.eclipse.buckminster.jnlp.p2.installer.P2PropertyKeys;

/**
 * @author Karel Brezina
 *
 */
public interface IDistroService extends P2PropertyKeys
{
	List<DistroVariant> getDistroVariants(boolean draft, Long stackId, Map<String, String> platformProperties);
	
	DistroContent getDistro(boolean draft, Long cspecId, Long distroId);
	
	Map<String, String> getDistroP2Properties(boolean draft, Long cspecId, Long distroId);
}
