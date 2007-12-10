/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class UnresolvedNode extends DepNode
{
	public static final String ELEM_ATTRIBUTE = "attribute";

	@SuppressWarnings("hiding")
	public static final String TAG = "unresolvedNode";

	private final QualifiedDependency m_dependency;

	public UnresolvedNode(QualifiedDependency dependency)
	{
		m_dependency = dependency;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public QualifiedDependency getQualifiedDependency()
	{
		return m_dependency;
	}

	@Override
	public ComponentRequest getRequest()
	{
		return m_dependency.getRequest();
	}

	@Override
	public String getViewName() throws CoreException
	{
		return getRequest().getViewName() + ":unresolved";
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix)
	throws SAXException
	{
		ComponentRequest request = m_dependency.getRequest();
		request.toSax(receiver, namespace, prefix, request.getDefaultTag());
		Set<String> names = m_dependency.getAttributeNames();
		int top = names.size();
		if(top > 0)
		{
			String[] attrNames = names.toArray(new String[top]);
			Arrays.sort(attrNames);
			String qName = Utils.makeQualifiedName(prefix, ELEM_ATTRIBUTE);
			for(int idx = 0; idx < top; ++idx)
			{
				AttributesImpl attrs = new AttributesImpl();
				Utils.addAttribute(attrs, NamedElement.ATTR_NAME, attrNames[idx]);
				receiver.startElement(namespace, ELEM_ATTRIBUTE, qName, attrs);
				receiver.endElement(namespace, ELEM_ATTRIBUTE, qName);
			}
		}
	}

	@Override
	void addMaterializationCandidates(RMContext context, List<Resolution> resolutions, ComponentQuery query, MaterializationSpec mspec, Set<Resolution> perused)
	throws CoreException
	{
		try
		{
			ComponentRequest request = getRequest();
			if(!(query.skipComponent(request) || mspec.isExcluded(request)))
				throw new UnresolvedNodeException(request);
		}
		catch(CoreException e)
		{
			if(!context.isContinueOnError())
				throw e;
			context.addException(getRequest(), e.getStatus());
		}
	}
}
