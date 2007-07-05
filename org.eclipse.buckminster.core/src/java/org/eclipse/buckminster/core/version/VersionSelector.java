/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.helpers.TextUtils;

/**
 * An instance of this class represents a branch or a tag in a Source Code Control System such as CVS, Subversion, or
 * Perforce.
 * 
 * @author Thomas Hallgren
 */
public abstract class VersionSelector
{
	/**
	 * The name of the main (also know as default) branch.
	 */
	public static final String DEFAULT_BRANCH = "main";

	public static final int BRANCH = 0;

	public static final VersionSelector[] EMPTY_PATH = new VersionSelector[0];

	public static final int TAG = 1;

	public static final char TAG_PREFIX = '/';

	/**
	 * Create an array from a comma separated String.
	 * 
	 * @param string
	 *            The comma separated string. Can be <code>null</code>
	 * @return The path. It may be empty but it is never <code>null</code>.
	 */
	public static VersionSelector[] fromPath(String string)
	{
		String[] strings = TextUtils.split(string, ",");
		int idx = strings.length;
		if(idx == 0)
			return EMPTY_PATH;

		VersionSelector[] path = new VersionSelector[idx];
		while(--idx >= 0)
			path[idx] = fromString(strings[idx]);
		return path;
	}

	/**
	 * Create an instance from a String. A string starting with &quot;/&quot; is considered a Tag. All other strings are
	 * considered to be a Branch.
	 */
	public static VersionSelector fromString(String string)
	{
		boolean isTag = false;
		if(string != null && string.length() > 0 && string.charAt(0) == '/')
		{
			string = string.substring(1);
			isTag = true;
		}
		return fromString(string, isTag);
	}

	/**
	 * Create an branch instance from a String.
	 * @param The string representation of the branch
	 * @return A BranchOrTag instance that reflects a branch
	 */
	public static VersionSelector branch(String string)
	{
		return fromString(string, false);
	}

	/**
	 * Create an tag instance from a String.
	 * @param The string representation of the tag
	 * @return A BranchOrTag instance that reflects a tag
	 */
	public static VersionSelector tag(String string)
	{
		return fromString(string, true);
	}

	private static VersionSelector fromString(String string, boolean isTag)
	{
		if(string != null)
		{
			int top = string.length();
			for(int idx = 0; idx < top; ++idx)
			{
				char c = string.charAt(idx);
				if(c == '/')
					throw new IllegalArgumentException(
							"The '/' character is only legal at first position of a branch/tag qualifier");

				if(c == ',' || Character.isWhitespace(c) || Character.isISOControl(c))
					throw new IllegalArgumentException("The '" + c + "' character is illegal in a branch/tag qualifier");
			}
			if(top > 0)
				return isTag ? new Tag(string) : new Branch(string);
		}
		throw new IllegalArgumentException("A branch/tag qualifier cannot be empty");
	}

	/**
	 * Returns the index of <code>branchOrTag</code> in the array <code>path</code> or <code>-1</code> if
	 * <code>branchOrTag</code> is not equal to any of the <code>path</code> elements. The method will return
	 * <code>-1</code> if any of the arguments is <code>null</code>.
	 * 
	 * @param path
	 *            The path to search in
	 * @param branchOrTag
	 *            The element to find
	 * @return The index of the found element or <code>-1</code>
	 */
	public static int indexOf(VersionSelector[] path, VersionSelector branchOrTag)
	{
		if(path == null)
			return -1;

		int idx = path.length;
		while(--idx >= 0)
		{
			VersionSelector pathEntry = path[idx];
			if(branchOrTag == null)
			{
				if(pathEntry.isDefault())
					break;
			}
			else if(branchOrTag.equals(path[idx]))
				break;
		}
		return idx;
	}

	/**
	 * Create a comma separated string from an array.
	 * 
	 * @param path
	 *            The array. Can be <code>null</code> or empty.
	 * @return The comma separated string or <code>null</code> if the array was <code>null</code> or empty.
	 */
	public static String toString(VersionSelector[] path)
	{
		int top = (path == null)
				? 0
				: path.length;
		if(top == 0)
			return null;

		VersionSelector first = path[0];
		if(top == 1)
			return first.toString();

		StringBuilder bld = new StringBuilder();
		first.toString(bld);
		for(int idx = 1; idx < top; ++idx)
		{
			bld.append(',');
			path[idx].toString(bld);
		}
		return bld.toString();
	}

	private final String m_name;

	VersionSelector(String name)
	{
		m_name = name;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof VersionSelector)
		{
			VersionSelector that = (VersionSelector)o;
			return getType() == that.getType() && m_name.equals(that.m_name);
		}
		return false;
	}

	/**
	 * Returns the name of this instance without any prefix character
	 * 
	 * @return The name of the qualifier
	 */
	public String getName()
	{
		return m_name;
	}

	/**
	 * Returns the instance type as either {@link #BRANCH} or {@link #TAG}
	 * 
	 * @return The type
	 */
	public abstract int getType();

	@Override
	public int hashCode()
	{
		return (m_name.hashCode() << 1) | getType();
	}

	/**
	 * Returns true if this instance is the default branch
	 * @return <code>true</code> if this is the default branch
	 */
	public abstract boolean isDefault();

	/**
	 * Returns the string form with the prefix (if any) appended
	 * 
	 * @return The string form
	 */
	@Override
	public String toString()
	{
		return m_name;
	}

	/**
	 * Appends the string form of the instance onto the <code>bld</code> buffer.
	 * 
	 * @param bld
	 *            The buffer that will receive the string form of the instance
	 */
	public void toString(StringBuilder bld)
	{
		bld.append(m_name);
	}

	/**
	 * Returns the verbose string form of the instance. The verbose form will be &quot;Branch: &lt;name&gt;&quot; or
	 * &quot;Tag: &lt;name&gt;&quot;. of the instance.
	 * 
	 * @return a verbose string
	 */
	public String viewNameToString()
	{
		StringBuilder bld = new StringBuilder();
		viewNameToString(bld);
		return bld.toString();
	}

	/**
	 * Appends the verbose string form of the instance onto the <code>bld</code> buffer. The verbose form will be
	 * &quot;Branch: &lt;name&gt;&quot; or &quot;Tag: &lt;name&gt;&quot;.
	 * 
	 * @param bld
	 *            The buffer that will receive the verbose string representation
	 */
	public abstract void viewNameToString(StringBuilder bld);
}

class Branch extends VersionSelector
{
	Branch(String name)
	{
		super(name);
	}

	@Override
	public int getType()
	{
		return BRANCH;
	}

	@Override
	public boolean isDefault()
	{
		return getName().equals(DEFAULT_BRANCH);
	}

	@Override
	public void viewNameToString(StringBuilder bld)
	{
		bld.append("Branch: ");
		bld.append(getName());
	}
}

class Tag extends VersionSelector
{
	Tag(String name)
	{
		super(name);
	}

	@Override
	public int getType()
	{
		return TAG;
	}

	@Override
	public boolean isDefault()
	{
		// There is no default tag
		//
		return false;
	}

	@Override
	public void toString(StringBuilder bld)
	{
		bld.append('/');
		super.toString(bld);
	}

	@Override
	public void viewNameToString(StringBuilder bld)
	{
		bld.append("Tag: ");
		bld.append(getName());
	}
}
