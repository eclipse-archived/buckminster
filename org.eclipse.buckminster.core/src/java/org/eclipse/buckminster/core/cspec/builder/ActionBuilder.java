/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.HashSet;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.Prerequisites;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class ActionBuilder extends AttributeBuilder
{
	private final ExpandingProperties m_actorProperties = new ExpandingProperties();

	private final HashSet<IPath> m_productPaths = new HashSet<IPath>();

	private final ExpandingProperties m_properties = new ExpandingProperties();

	private final PrerequisitesBuilder m_prerequisitesBuilder;

	private String m_actorName;

	private boolean m_always = Action.ALWAYS_DEFAULT;;

	private boolean m_assignConsoleSupport = Action.ASSIGN_CONSOLE_SUPPORT_DEFAULT;

	private boolean m_enabled = Action.ENABLED_DEFAULT;;

	private String m_productAlias;

	private IPath m_productBase;

	ActionBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
		m_prerequisitesBuilder = new PrerequisitesBuilder(cspecBuilder);
	}

	public void addActorProperty(String key, String propVal, boolean mutable)
	{
		m_actorProperties.put(key, propVal, mutable);
	}

	public void addProductPath(IPath path)
	{
		m_productPaths.add(path);
	}

	public void addProperty(String key, String propVal, boolean mutable)
	{
		m_properties.put(key, propVal, mutable);
	}

	public void removeProductPath(IPath path)
	{
		m_productPaths.remove(path);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_actorName = null;
		m_always = Action.ALWAYS_DEFAULT;
		m_assignConsoleSupport = Action.ASSIGN_CONSOLE_SUPPORT_DEFAULT;
		m_enabled = Action.ENABLED_DEFAULT;
		m_actorProperties.clear();
		m_prerequisitesBuilder.clear();
		m_productPaths.clear();
		m_properties.clear();
	}

	@Override
	public Action createAttribute()
	{
		return new Action(getName(), isPublic(), getInstallerHints(), getDocumentation(), m_actorName, m_actorProperties,
				m_properties, (Prerequisites)m_prerequisitesBuilder.createAttribute(), m_productAlias, m_productBase, m_productPaths, m_always, m_enabled,
				m_assignConsoleSupport);
	}

	public String getActorName()
	{
		return m_actorName;
	}

	public ExpandingProperties getActorProperties()
	{
		return m_actorProperties;
	}

	public GroupBuilder getPrerequisitesBuilder()
	{
		return m_prerequisitesBuilder;
	}

	public String getProductAlias()
	{
		return m_productAlias;
	}

	public ArtifactBuilder getProductArtifact(String name)
	{
		ArtifactBuilder bld = getCSpecBuilder().getArtifact(name);
		return (bld instanceof ActionArtifactBuilder) ? bld : null;
	}

	public IPath getProductBase()
	{
		return m_productBase;
	}

	public HashSet<IPath> getProductPaths()
	{
		return m_productPaths;
	}

	public ExpandingProperties getProperties()
	{
		return m_properties;
	}

	@Override
	public void initFrom(NamedElement namedElement)
	{
		Action action = (Action)namedElement;
		super.initFrom(action);
		m_actorName = action.getActorName();
		m_actorProperties.putAll(action.getActorProperties());
		m_always = action.isAlways();
		m_assignConsoleSupport = action.assignConsoleSupport();
		m_enabled = action.isEnabled(null);
		m_prerequisitesBuilder.initFrom(action.getPrerequisiteGroup());
		m_productAlias = action.getProductAlias();
		m_productBase = action.getProductBase();
		m_productPaths.addAll(action.getProductPaths());
		m_properties.putAll(action.getProperties());
	}

	public boolean isAlways()
	{
		return m_always;
	}

	public boolean isAssignConsoleSupport()
	{
		return m_assignConsoleSupport;
	}

	public boolean isEnabled()
	{
		return m_enabled;
	}

	public void setActorName(String actorName)
	{
		m_actorName = actorName;
	}

	public void setAlways(boolean always)
	{
		m_always = always;
	}

	public void setAssignConsoleSupport(boolean assignConsoleSupport)
	{
		m_assignConsoleSupport = assignConsoleSupport;
	}

	public void setEnabled(boolean enabled)
	{
		m_enabled = enabled;
	}

	public void setPrerequisites(Group prerequisites)
	{
		m_prerequisitesBuilder.initFrom(prerequisites);
	}

	public void setProductAlias(String productAlias)
	{
		m_productAlias = productAlias;
	}

	public void setProductBase(IPath productBase)
	{
		m_productBase = productBase;
	}

	public void setPrerequisitesAlias(String alias)
	{
		m_prerequisitesBuilder.setName(alias);
	}

	public void setPrerequisitesRebase(IPath rebase)
	{
		m_prerequisitesBuilder.setRebase(rebase);
	}

	@Override
	public void addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException
	{
		m_prerequisitesBuilder.addPrerequisite(prerequisite);
	}

	public ArtifactBuilder addProductArtifact(String name, boolean publ, String type, IPath output) throws AttributeAlreadyDefinedException
	{
		CSpecBuilder cspecBuilder = getCSpecBuilder();
		ActionArtifactBuilder bld = cspecBuilder.createActionArtifactBuilder();
		bld.setActionName(getName());
		bld.setName(name);
		bld.setPublic(publ);
		bld.setType(type);
		bld.setBase(output);
		cspecBuilder.addAttribute(bld);
		return bld;
	}

	@Override
	public void removePrerequisite(String prerequisteName)
	{
		m_prerequisitesBuilder.removePrerequisite(prerequisteName);
	}
}
