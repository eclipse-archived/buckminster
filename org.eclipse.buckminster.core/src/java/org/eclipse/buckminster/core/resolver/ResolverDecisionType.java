/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public enum ResolverDecisionType
{
	BRANCH_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Branch_0_rejected_1;
		}
	},

	COMPONENT_TYPE_MISMATCH
	{
		@Override
		public String toString()
		{
			return Messages.Wrong_component_type_0;
		}
	},

	EXCEPTION
	{
		@Override
		public String toString()
		{
			return Messages.Resolution_attempt_ended_with_exception_0;
		}
	},

	FILTER_MISMATCH
	{
		@Override
		public String toString()
		{
			return Messages.Filter_0_does_not_match_the_current_property_set;
		}
	},

	MATCH_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.Found_match_0;
		}
	},

	MATCH_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Match_0_was_rejected_1;
		}
	},

	MAIN_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Trunk_Head_rejected_0;
		}
	},

	NO_BRANCHES_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.No_branches_were_found;
		}
	},

	NO_TAGS_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.No_tags_were_found;
		}
	},

	PROVIDER_NOT_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.No_provider_was_found_that_could_resolve_the_request;
		}
	},

	REDIRECT_TO_RESOURCE_MAP
	{
		@Override
		public String toString()
		{
			return Messages.Redirecting_to_resource_map_0;
		}
	},

	REJECTING_PROVIDER
	{
		@Override
		public String toString()
		{
			return Messages.Rejecting_provider_0_1_2;
		}
	},

	RESOLVER_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Rejecting_resolver_0;
		}
	},

	REVISION_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Revision_0_rejected_1;
		}
	},

	SEARCH_PATH_NOT_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.No_searchPath_was_found_with_a_matching_pattern;
		}
	},

	SEARCHING_TRUNK
	{
		@Override
		public String toString()
		{
			return Messages.Trunk_head_will_be_searched;
		}
	},

	SEARCHING_BRANCHES
	{
		@Override
		public String toString()
		{
			return Messages.Branches_will_be_searched;
		}
	},

	SEARCHING_TAGS
	{
		@Override
		public String toString()
		{
			return Messages.Tags_will_be_searched;
		}
	},

	SPACE_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Space_0_rejected_1;
		}
	},

	TAG_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Tag_0_rejected_1;
		}
	},

	TIMESTAMP_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Timestamp_0_rejected_1;
		}
	},

	TRYING_PROVIDER
	{
		@Override
		public String toString()
		{
			return Messages.Trying_provider_0_1;
		}
	},

	USING_PROVIDER
	{
		@Override
		public String toString()
		{
			return Messages.Using_provider_0_1;
		}
	},

	USING_RESOLVER
	{
		@Override
		public String toString()
		{
			return Messages.Using_resolver_0;
		}
	},

	USING_RESOURCE_MAP
	{
		@Override
		public String toString()
		{
			return Messages.Using_resource_map_0;
		}
	},

	USING_SEARCH_PATH
	{
		@Override
		public String toString()
		{
			return Messages.Using_search_path_0;
		}
	},

	VERSION_SELECTOR_MISMATCH
	{
		@Override
		public String toString()
		{
			return Messages.VersionSelector_for_0_discriminates_all_1;
		}
	},

	VERSION_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.Version_0_rejected_1;
		}
	},

	USING_VERSION_CONVERTER
	{
		@Override
		public String toString()
		{
			return Messages.Using_version_converter_0_trunk_head_not_considered;
		}
	},

	USING_BRANCH_CONVERTED_VERSION
	{
		@Override
		public String toString()
		{
			return Messages.Version_0_was_converted_from_branch_1;
		}
	},

	USING_TAG_CONVERTED_VERSION
	{
		@Override
		public String toString()
		{
			return Messages.Version_0_was_converted_from_tag_1;
		}
	};

	public final String getMessage(Object... args)
	{
		return NLS.bind(toString(), args);
	}
}
