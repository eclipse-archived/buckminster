/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.model.common.ComponentIdentifier;

/**
 * @author Thomas Hallgren
 */
public class MaterializationStatistics {
	private List<ComponentIdentifier> failed = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> kept = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> replaced = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> skipped = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> updated = new ArrayList<ComponentIdentifier>();

	public void addFailed(ComponentIdentifier ci) {
		failed.add(ci);
	}

	public void addKept(ComponentIdentifier ci) {
		kept.add(ci);
	}

	public void addReplaced(ComponentIdentifier ci) {
		replaced.add(ci);
	}

	public void addSkipped(ComponentIdentifier ci) {
		skipped.add(ci);
	}

	public void addUpdated(ComponentIdentifier ci) {
		updated.add(ci);
	}

	public List<ComponentIdentifier> getFailed() {
		return failed;
	}

	public List<ComponentIdentifier> getKept() {
		return kept;
	}

	public List<ComponentIdentifier> getReplaced() {
		return replaced;
	}

	public List<ComponentIdentifier> getSkipped() {
		return skipped;
	}

	public List<ComponentIdentifier> getUpdated() {
		return updated;
	}

	public boolean isIncluded(ComponentIdentifier ci) {
		return (failed.contains(ci) || kept.contains(ci) || replaced.contains(ci) || skipped.contains(ci) || updated.contains(ci));
	}
}
