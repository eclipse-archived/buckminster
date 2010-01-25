/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.IGlobalContext;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Locator;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Generator extends NamedElement implements IGenerator
{
	public static final String ATTR_ATTRIBUTE = "attribute"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT = "component"; //$NON-NLS-1$

	public static final String ATTR_GENERATES = "generates"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_TYPE = "generatesType"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_VERSION = "generatesVersion"; //$NON-NLS-1$

	public static final String TAG = "generator"; //$NON-NLS-1$

	private final CSpec m_cspec;

	private final String m_attribute;

	private final String m_component;

	private final String m_generatesType;

	private final Version m_generatesVersion;

	public Generator(CSpec cspec, String component, String attribute, ComponentIdentifier generates)
	{
		super(generates);
		m_cspec = cspec;
		m_component = component;
		m_attribute = attribute;
		m_generatesType = generates.getComponentTypeID();
		m_generatesVersion = generates.getVersion();
	}

	/**
	 * @deprecated Use {@link #Generator(CSpec, String, String, ComponentIdentifier)}
	 */
	@Deprecated
	public Generator(CSpec cspec, String component, String attribute, String generates)
	{
		super(generates);
		m_cspec = cspec;
		m_component = component;
		m_attribute = attribute;
		m_generatesType = null;
		m_generatesVersion = null;
	}

	public String getAttribute()
	{
		return m_attribute;
	}

	public String getComponent()
	{
		return m_component;
	}

	public CSpec getCSpec()
	{
		return m_cspec;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public IComponentIdentifier getGeneratedIdentifier()
	{
		return new ComponentIdentifier(getName(), m_generatesType, m_generatesVersion);
	}

	public String getGenerates()
	{
		return getName();
	}

	@Override
	public String getNameAttributeName()
	{
		return ATTR_GENERATES;
	}

	public void registerGeneratedResolution(PathGroup[] result, IGlobalContext ctx, IProgressMonitor monitor)
			throws CoreException
	{
		// Register the resolution from this generator
		if(result.length != 1)
			return;

		PathGroup pg = result[0];
		HashMap<String, Long> fileNames = new HashMap<String, Long>();
		pg.appendRelativeFiles(fileNames);
		if(fileNames.isEmpty())
			return;

		Set<String> paths = fileNames.keySet();
		String readerType = IReaderType.LOCAL;
		Format uri;
		boolean isFile = false;
		IPath componentLocation;
		if(paths.size() == 1)
		{
			IPath path = Path.fromOSString(paths.iterator().next());
			String suffix = path.getFileExtension();
			if("zip".equals(suffix) || "jar".equals(suffix)) //$NON-NLS-1$ //$NON-NLS-2$
			{
				readerType = IReaderType.URL_ZIPPED;
				isFile = true;
			}
			componentLocation = pg.getBase().append(path);
		}
		else
			componentLocation = pg.getBase();
		try
		{
			uri = new Format(URLUtils.normalizeToURL(componentLocation.toOSString()).toString());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}

		IComponentIdentifier ci = getGeneratedIdentifier();
		String cType = ci.getComponentTypeID();
		ResourceMap rmap = new ResourceMap(null);
		SearchPath searchPath = new SearchPath(rmap, "default"); //$NON-NLS-1$
		Map<String, String> props = new HashMap<String, String>(2);
		props.put(KeyConstants.IS_MUTABLE, "false"); //$NON-NLS-1$
		props.put(KeyConstants.IS_SOURCE, "false"); //$NON-NLS-1$
		Provider provider = new Provider(searchPath, readerType, new String[] { cType }, null, uri, null, null, null,
				props, null, null);
		searchPath.addProvider(provider);
		rmap.addSearchPath(searchPath);
		rmap.addMatcher(new Locator(rmap, null, "default")); //$NON-NLS-1$
		ComponentQueryBuilder query = new ComponentQueryBuilder();
		query.setRootRequest(new ComponentRequest(ci.getName(), ci.getComponentTypeID(), null));
		ResolutionContext rctx = new ResolutionContext(query.createComponentQuery());
		BOMNode node = rmap.resolve(rctx.getRootNodeQuery(), monitor);
		Resolution res = node.getResolution();
		if(res != null)
		{
			if(isFile)
				componentLocation = componentLocation.removeLastSegments(1);
			ctx.addGeneratedResolution(res, componentLocation);
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_ATTRIBUTE, m_attribute);
		if(m_component != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, m_component);
		Utils.addAttribute(attrs, ATTR_GENERATES, getName());

		if(m_generatesType != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_TYPE, m_generatesType);
		if(m_generatesVersion != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_VERSION, m_generatesVersion.toString());
	}
}
