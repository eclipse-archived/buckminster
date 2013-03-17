/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import java.util.UUID;

/**
 * Base class for immutable UUID keyed classes. The contract specifies that such
 * a class may only declare immutable (i.e. final) properties since the id of
 * the instance is calculated from the XML serialization.
 * 
 * @author Thomas Hallgren
 */
public abstract class UUIDKeyed extends AbstractSaxableElement implements ISaxable {
	private transient UUID id;

	private transient byte[] image;

	@Override
	public final boolean equals(Object o) {
		return o == this || ((o instanceof UUIDKeyed && ((UUIDKeyed) o).getId().equals(this.getId())));
	}

	public synchronized final UUID getId() {
		if (id == null) {
			image = Utils.getImage(this);
			id = UUID.nameUUIDFromBytes(image);
		}
		return id;
	}

	public synchronized final byte[] getImage() {
		if (image == null) {
			image = Utils.getImage(this);
			if (id == null)
				id = UUID.nameUUIDFromBytes(image);
		}
		return image;
	}

	@Override
	public final int hashCode() {
		return this.getId().hashCode();
	}

	public final synchronized void setId(UUID id) {
		this.id = id;
	}
}
