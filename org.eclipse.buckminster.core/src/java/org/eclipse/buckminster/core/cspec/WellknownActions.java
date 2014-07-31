/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 */
public abstract class WellknownActions {
	public enum BUCKMINSTER {
		CLEAN {
			@Override
			public String toString() {
				return "buckminster.clean"; //$NON-NLS-1$
			}
		},

		/**
		 * The prebind action is executed just prior to when a component is
		 * bound into the Eclipse workspace. The result of the prebind action is
		 * ignored.
		 */
		PREBIND {
			@Override
			public String toString() {
				return "buckminster.prebind"; //$NON-NLS-1$
			}
		},

		/**
		 * Similar to the {@link #PREBIND}, this action is executed just prior
		 * to when a component is bound into the Eclipse workspace. The
		 * difference is that the result of this action will be bound to the
		 * workspace instead of the component root.
		 */
		BIND_ENTRYPOINT {
			@Override
			public String toString() {
				return "buckminster.bind.entrypoint"; //$NON-NLS-1$
			}
		},

		/**
		 * Call after the binding has completed
		 */
		POSTBIND {
			@Override
			public String toString() {
				return "buckminster.postbind"; //$NON-NLS-1$
			}
		}
	}

	public enum ECLIPSE {
		CLEAN {
			@Override
			public int kind() {
				return IncrementalProjectBuilder.CLEAN_BUILD;
			}

			@Override
			public String toString() {
				return "eclipse.clean"; //$NON-NLS-1$
			}
		},
		AUTO // not used as an actual action
		{
			@Override
			public int kind() {
				return IncrementalProjectBuilder.AUTO_BUILD;
			}

			@Override
			public String toString() {
				return "eclipse.auto"; //$NON-NLS-1$
			}
		},
		INCREMENTAL // not used as an actual action
		{
			@Override
			public int kind() {
				return IncrementalProjectBuilder.INCREMENTAL_BUILD;
			}

			@Override
			public String toString() {
				return "eclipse.incremental"; //$NON-NLS-1$
			}
		},
		FULL // not used as an actual action
		{
			@Override
			public int kind() {
				return IncrementalProjectBuilder.FULL_BUILD;
			}

			@Override
			public String toString() {
				return "eclipse.full"; //$NON-NLS-1$
			}
		},
		BUILD {
			@Override
			public int kind() {
				return IncrementalProjectBuilder.FULL_BUILD;
			}

			@Override
			public String toString() {
				return "eclipse.build"; //$NON-NLS-1$
			}
		};

		public static int name2Kind(String name) {
			for (ECLIPSE e : ECLIPSE.values())
				if (name.equals(e.toString()))
					return e.kind();
			throw new InternalError(NLS.bind(Messages.Unexpected_name_0, name));
		}

		public abstract int kind();
	}
}
