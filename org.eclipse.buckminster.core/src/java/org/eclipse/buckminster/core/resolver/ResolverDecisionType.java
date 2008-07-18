/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;


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
			return "Branch %s rejected: %s";
		}
	},

	COMPONENT_TYPE_MISMATCH
	{
		@Override
		public String toString()
		{
			return "Wrong component type: %s";
		}
	},

	EXCEPTION
	{
		@Override
		public String toString()
		{
			return "Resolution attempt ended with exception: %s";
		}
	},

	FILTER_MISMATCH
	{
		@Override
		public String toString()
		{
			return "Filter %s does not match the current property set";
		}
	},

	MATCH_FOUND
	{
		@Override
		public String toString()
		{
			return "Found match %s";
		}
	},

	MATCH_REJECTED
	{
		@Override
		public String toString()
		{
			return "Match %s was rejected: %s";
		}
	},
	
	MAIN_REJECTED
	{
		@Override
		public String toString()
		{
			return "Trunk/Head rejected: %s";
		}
	},

	NO_BRANCHES_FOUND
	{
		@Override
		public String toString()
		{
			return "No branches were found";
		}
	},

	NO_TAGS_FOUND
	{
		@Override
		public String toString()
		{
			return "No tags were found";
		}
	},

	PROVIDER_NOT_FOUND
	{
		@Override
		public String toString()
		{
			return "No provider was found that could resolve the request";
		}
	},

	REDIRECT_TO_RESOURCE_MAP
	{
		@Override
		public String toString()
		{
			return "Redirecting to resource map %s";
		}
	},

	REJECTING_PROVIDER
	{
		@Override
		public String toString()
		{
			return "Rejecting provider %s(%s): %s";
		}
	},

	REVISION_REJECTED
	{
		@Override
		public String toString()
		{
			return "Revision %s rejected: %s";
		}
	},

	SEARCH_PATH_NOT_FOUND
	{
		@Override
		public String toString()
		{
			return "No searchPath was found with a matching pattern";
		}
	},

	SEARCHING_TRUNK
	{
		@Override
		public String toString()
		{
			return "trunk/head will be searched";
		}
	},

	SEARCHING_BRANCHES
	{
		@Override
		public String toString()
		{
			return "branches will be searched";
		}
	},

	SEARCHING_TAGS
	{
		@Override
		public String toString()
		{
			return "tags will be searched";
		}
	},

	SPACE_REJECTED
	{
		@Override
		public String toString()
		{
			return "Space %s rejected: %s";
		}
	},

	TAG_REJECTED
	{
		@Override
		public String toString()
		{
			return "Tag %s rejected: %s";
		}
	},

	TIMESTAMP_REJECTED
	{
		@Override
		public String toString()
		{
			return "Timestamp %s rejected: %s";
		}
	},

	TRYING_PROVIDER
	{
		@Override
		public String toString()
		{
			return "Trying provider %s(%s)";
		}
	},

	USING_PROVIDER
	{
		@Override
		public String toString()
		{
			return "Using provider %s(%s)";
		}
	},

	USING_RESOLVER
	{
		@Override
		public String toString()
		{
			return "Using resolver %s";
		}
	},

	USING_RESOURCE_MAP
	{
		@Override
		public String toString()
		{
			return "Using resource map %s";
		}
	},

	USING_SEARCH_PATH
	{
		@Override
		public String toString()
		{
			return "Using search path %s";
		}
	},

	VERSION_SELECTOR_MISMATCH
	{
		@Override
		public String toString()
		{
			return "VersionSelector for %s discriminates all %s";
		}
	},

	VERSION_REJECTED
	{
		@Override
		public String toString()
		{
			return "Version %s rejected: %s";
		}
	},

	USING_VERSION_CONVERTER
	{
		@Override
		public String toString()
		{
			return "Using version converter %s. trunk/head will not be considered";
		}
	};

	public final String getMessage(Object...args)
	{
		return String.format(toString(), args);
	}
}
