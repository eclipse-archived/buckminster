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
			return Messages.ResolverDecisionType_Branch_0_rejected_1;
		}
	},

	COMPONENT_TYPE_MISMATCH
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Wrong_component_type_0;
		}
	},

	EXCEPTION
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Resolution_attempt_ended_with_exception_0;
		}
	},

	FILTER_MISMATCH
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Filter_0_does_not_match_the_current_property_set;
		}
	},

	MATCH_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Found_match_0;
		}
	},

	MATCH_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Match_0_was_rejected_1;
		}
	},

	MAIN_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Trunk_Head_rejected_0;
		}
	},

	NO_BRANCHES_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_No_branches_were_found;
		}
	},

	NO_TAGS_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_No_tags_were_found;
		}
	},

	PROVIDER_NOT_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_No_provider_was_found_that_could_resolve_the_request;
		}
	},

	REDIRECT_TO_RESOURCE_MAP
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Redirecting_to_resource_map_0;
		}
	},

	REJECTING_PROVIDER
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Rejecting_provider_0_1_2;
		}
	},

	REVISION_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Revision_0_rejected_1;
		}
	},

	SEARCH_PATH_NOT_FOUND
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_No_searchPath_was_found_with_a_matching_pattern;
		}
	},

	SEARCHING_TRUNK
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_trunk_head_will_be_searched;
		}
	},

	SEARCHING_BRANCHES
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_branches_will_be_searched;
		}
	},

	SEARCHING_TAGS
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_tags_will_be_searched;
		}
	},

	SPACE_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Space_0_rejected_1;
		}
	},

	TAG_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Tag_0_rejected_1;
		}
	},

	TIMESTAMP_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Timestamp_0_rejected_1;
		}
	},

	TRYING_PROVIDER
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Trying_provider_0_1;
		}
	},

	USING_PROVIDER
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Using_provider_0_1;
		}
	},

	USING_RESOLVER
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Using_resolver_0;
		}
	},

	USING_RESOURCE_MAP
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Using_resource_map_0;
		}
	},

	USING_SEARCH_PATH
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Using_search_path_0;
		}
	},

	VERSION_SELECTOR_MISMATCH
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_VersionSelector_for_0_discriminates_all_1;
		}
	},

	VERSION_REJECTED
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Version_0_rejected_1;
		}
	},

	USING_VERSION_CONVERTER
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Using_version_converter_0_trunk_head_not_considered;
		}
	},

	USING_BRANCH_CONVERTED_VERSION
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Version_0_was_converted_from_branch_1;
		}
	},

	USING_TAG_CONVERTED_VERSION
	{
		@Override
		public String toString()
		{
			return Messages.ResolverDecisionType_Version_0_was_converted_from_tag_1;
		}
	};

	public final String getMessage(Object... args)
	{
		return NLS.bind(toString(), args);
	}
}
