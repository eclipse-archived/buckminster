/*****************************************************************************
 * Copyright (c) 2020, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde;

import org.eclipse.pde.core.plugin.IMatchRules;

public enum MatchRule {
	/**
	 * No match rule has been specified
	 */
	NONE,

	/**
	 * Dependent plug-in version must match exactly the specified version.
	 */
	PERFECT,

	/**
	 * dependent plug-in version must match the specified version with respect
	 * to service, minor, and major levels. The qualifier may vary.
	 */
	UNQUALIFIED,

	/**
	 * dependent plug-in version must be at least at the version specified, or
	 * at a higher service level (major and minor version levels must equal the
	 * specified version).
	 */
	EQUIVALENT,

	/**
	 * dependent plug-in version must be at least at the version specified, or
	 * at a higher service level or minor level (major version level must equal
	 * the specified version).
	 */
	COMPATIBLE,

	/**
	 * dependent plug-in version must be at least at the version specified, or
	 * at a higher service, minor or major level.
	 */
	GREATER_OR_EQUAL;

	/**
	 * Create a MatchRule from a PDE match rule in integer form
	 * 
	 * @param match
	 *            The integer representing the PDE match rule
	 * @return The MatchRule that corresponds to the PDE match rule
	 */
	public static MatchRule fromPDE(int match) {
		MatchRule rule;
		switch (match) {
			case IMatchRules.PERFECT:
				rule = PERFECT;
				break;
			case IMatchRules.EQUIVALENT:
				rule = EQUIVALENT;
				break;
			case IMatchRules.GREATER_OR_EQUAL:
				rule = GREATER_OR_EQUAL;
				break;
			default:
				rule = NONE;
		}
		return rule;
	}

	/**
	 * Like {@link #valueOf(String)} but returns {@link #NONE} for illegal
	 * input.
	 * 
	 * @param matchRuleString
	 *            The string to match. Can be <code>null</code>.
	 * @return The MatchRule that matches the input or {@link #NONE} if no
	 *         match.
	 */
	public static MatchRule getMatchRule(String matchRuleString) {
		if (matchRuleString == null)
			return NONE;

		try {
			return valueOf(matchRuleString.toUpperCase());
		} catch (IllegalArgumentException e) {
			return NONE;
		}
	}
}
