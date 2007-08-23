/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.core.runtime.IPath;

/**
 * @author Karel Brezina
 *
 */
public class CSpecEditorUtils
{
	static class CSpecElementComparator implements Comparator<CSpecElementBuilder>
	{
		public int compare(CSpecElementBuilder o1, CSpecElementBuilder o2)
		{
			if(o1.getName() == null && o2.getName() == null)
				return 0;
			
			if(o1.getName() == null && o2.getName() != null)
				return -1;
			
			if(o1.getName() != null && o2.getName() == null)
				return 1;
			
			return o1.getName().compareTo(o2.getName());
		}
	}
	
	static class AttributeComparator implements Comparator<AttributeBuilder>
	{
		public int compare(AttributeBuilder o1, AttributeBuilder o2)
		{
			if(o1.isPublic() && !o2.isPublic())
				return -1;
			
			if(!o1.isPublic() && o2.isPublic())
				return 1;
			
			if(o1.getName() == null && o2.getName() == null)
				return 0;
			
			if(o1.getName() == null && o2.getName() != null)
				return -1;
			
			if(o1.getName() != null && o2.getName() == null)
				return 1;
			
			return o1.getName().compareTo(o2.getName());
		}
	}
	
	static class PropertyComparator implements Comparator<Property>
	{
		public int compare(Property o1, Property o2)
		{
			return o1.getKey().compareTo(o2.getKey());
		}
	};

	private static Comparator<CSpecElementBuilder> s_cspecElementComparator = new CSpecElementComparator();
	private static Comparator<AttributeBuilder> s_attributeComparator = new AttributeComparator();
	private static Comparator<Property> s_propertyComparator = new PropertyComparator();

	private CSpecEditorUtils()
	{
	}

	public static Comparator<CSpecElementBuilder> getCSpecElementComparator()
	{
		return s_cspecElementComparator;
	}

	public static Comparator<AttributeBuilder> getAttributeComparator()
	{
		return s_attributeComparator;
	}

	public static Comparator<Property> getPropertyComparator()
	{
		return s_propertyComparator;
	}
	
	public static void copyAndSortItems(ExpandingProperties src, List<Property> trgt)
	{
		trgt.clear();
		if(src != null)
		{
			List<Property> hlpList = new ArrayList<Property>();
			for(String key : src.keySet())
			{
				hlpList.add(new Property(key, src.get(key)));
			}
			Property[] propertyArray = hlpList.toArray(new Property[0]);
			Arrays.sort(propertyArray, CSpecEditorUtils.getPropertyComparator());
			
			for(Property property : propertyArray)
			{
				trgt.add(property);
			}
		}
	}
	
	public static void copyAndSortItems(Collection<IPath> src, List<PathWrapper> trgt)
	{
		trgt.clear();
		if(src != null)
		{
			List<IPath> hlpList = new ArrayList<IPath>();
			for(IPath path : src)
			{
				hlpList.add(path);
			}
			IPath[] pathArray = hlpList.toArray(new IPath[0]);
			Arrays.sort(pathArray, EditorUtils.getPathComparator());
			
			for(IPath path : pathArray)
			{
				trgt.add(new PathWrapper(path));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void copyAndSortItems(Collection<T> src, List<? super T> trgt, Comparator<? super T> comparator)
	{
		trgt.clear();
		if(src != null)
		{
			T[] pathArray = (T[]) src.toArray();
			Arrays.sort(pathArray, comparator);
			
			for(T path : pathArray)
			{
				trgt.add(path);
			}
		}
	}
}
