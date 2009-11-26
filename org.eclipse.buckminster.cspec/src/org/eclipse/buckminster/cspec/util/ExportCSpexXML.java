package org.eclipse.buckminster.cspec.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cspec.Action;
import org.eclipse.buckminster.cspec.ActionAttribute;
import org.eclipse.buckminster.cspec.Artifact;
import org.eclipse.buckminster.cspec.Attribute;
import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.Generator;
import org.eclipse.buckminster.cspec.Group;
import org.eclipse.buckminster.cspec.PathGroup;
import org.eclipse.buckminster.cspec.Prerequisite;
import org.eclipse.buckminster.cspecxml.IAction;
import org.eclipse.buckminster.cspecxml.IActionArtifact;
import org.eclipse.buckminster.cspecxml.IActionsType;
import org.eclipse.buckminster.cspecxml.IArtifact;
import org.eclipse.buckminster.cspecxml.IArtifactsType;
import org.eclipse.buckminster.cspecxml.IAttribute;
import org.eclipse.buckminster.cspecxml.ICSpecXMLFactory;
import org.eclipse.buckminster.cspecxml.IComponentRequest;
import org.eclipse.buckminster.cspecxml.IComponentSpec;
import org.eclipse.buckminster.cspecxml.IDependenciesType;
import org.eclipse.buckminster.cspecxml.IGenerator;
import org.eclipse.buckminster.cspecxml.IGeneratorsType;
import org.eclipse.buckminster.cspecxml.IGroup;
import org.eclipse.buckminster.cspecxml.IGroupsType;
import org.eclipse.buckminster.cspecxml.IPrerequisite;
import org.eclipse.buckminster.cspecxml.IPrerequisites;
import org.eclipse.buckminster.cspecxml.IProductsType;
import org.eclipse.buckminster.cspecxml.impl.ComponentSpecImpl;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

public class ExportCSpexXML
{
	private static String createStringFromPath(IPath path)
	{
		return path == null
				? null
				: path.toPortableString();
	}

	private ComponentSpecImpl m_xmlSpec;

	private CSpec m_cspec;

	public IComponentSpec exportCSpec(CSpec cspec)
	{
		m_cspec = cspec;
		m_xmlSpec = (ComponentSpecImpl)ICSpecXMLFactory.eINSTANCE.createComponentSpec();
		m_xmlSpec.setName(cspec.getId());
		m_xmlSpec.setComponentType(cspec.getType());
		m_xmlSpec.setVersion(cspec.getVersion());
		m_xmlSpec.setDocumentation(cspec.getDocumentation());
		m_xmlSpec.setFilter(cspec.getFilter());
		m_xmlSpec.setShortDesc(cspec.getShortDesc());
		m_xmlSpec.setProjectInfo(cspec.getProjectInfo());
		copyDependencies();
		copyGenerators();
		copyAttributes();
		return m_xmlSpec;
	}

	private void copyAction(Action attr, IAction xmlAttr)
	{
		copyAttribute(attr, xmlAttr);
		xmlAttr.setFilter(attr.getFilter());

		if(!attr.getPrerequisites().isEmpty())
		{
			IPrerequisites xps = ICSpecXMLFactory.eINSTANCE.createPrerequisites();
			xmlAttr.getPrerequisites().add(xps);
			xps.setAlias(attr.getPrerequisitesAlias());
			xps.setRebase(createStringFromPath(attr.getPrerequisitesRebase()));
			copyPrerequisites(attr.getPrerequisites(), xps.getAttribute());
		}
		IProductsType pt = ICSpecXMLFactory.eINSTANCE.createProductsType();
		xmlAttr.getProducts().add(pt);
		PathGroup product = attr.getProduct();
		if(product != null)
		{
			pt.setBase(createStringFromPath(product.getBase()));
			copyPathGroup(product, pt);
		}
		else
		{
			for(ActionAttribute aa : attr.getProducts())
			{
				IActionArtifact xmlAa = ICSpecXMLFactory.eINSTANCE.createActionArtifact();
				xmlAa.setAlias(aa.getAlias());
				copyArtifact(aa, xmlAa);
				if(aa.isPublic())
					pt.getPublic().add(xmlAa);
				else
					pt.getPrivate().add(xmlAa);
			}
		}
		pt.setAlias(attr.getProductAlias());
		pt.setFileCount(attr.getProductFileCount());
		pt.setUpToDatePolicy(org.eclipse.buckminster.cspecxml.UpToDatePolicy.valueOf(attr.getUpToDatePolicy().getName()));
		pt.setPattern(attr.getPattern());
		pt.setReplacement(attr.getReplacement());
	}

	private void copyActions(List<Action> actions)
	{
		if(actions.isEmpty())
			return;

		ICSpecXMLFactory factory = ICSpecXMLFactory.eINSTANCE;
		IActionsType at = factory.createActionsType();
		m_xmlSpec.getActions().add(at);
		for(Action action : actions)
		{
			IAction xmlAction = factory.createAction();
			copyAction(action, xmlAction);
			if(action.isPublic())
				at.getPublic().add(xmlAction);
			else
				at.getPrivate().add(xmlAction);
		}
	}

	private void copyArtifact(Artifact attr, IArtifact xmlAttr)
	{
		copyAttribute(attr, xmlAttr);
		copyPathGroup(attr, xmlAttr);
		attr.setFilter(xmlAttr.getFilter());
	}

	private void copyArtifacts(List<Artifact> artifacts)
	{
		if(artifacts.isEmpty())
			return;

		ICSpecXMLFactory factory = ICSpecXMLFactory.eINSTANCE;
		IArtifactsType at = factory.createArtifactsType();
		m_xmlSpec.getArtifacts().add(at);
		for(Artifact artifact : artifacts)
		{
			IArtifact xmlArtifact = factory.createArtifact();
			copyArtifact(artifact, xmlArtifact);
			if(artifact.isPublic())
				at.getPublic().add(xmlArtifact);
			else
				at.getPrivate().add(xmlArtifact);
		}
	}

	private void copyAttribute(Attribute attr, IAttribute xmlAttr)
	{
		xmlAttr.setDocumentation(attr.getDocumentation());
		xmlAttr.setName(attr.getName());
	}

	private void copyAttributes()
	{
		List<Group> groups = new ArrayList<Group>();
		List<Artifact> artifacts = new ArrayList<Artifact>();
		List<Action> actions = new ArrayList<Action>();
		for(Attribute attr : m_cspec.getAttributes())
		{
			if(attr instanceof Action)
				actions.add((Action)attr);
			else if(attr instanceof Group)
				groups.add((Group)attr);
			else if(!(attr instanceof ActionAttribute))
				artifacts.add((Artifact)attr);
		}
		copyArtifacts(artifacts);
		copyGroups(groups);
		copyActions(actions);
	}

	private void copyDependencies()
	{
		List<ComponentRequest> deps = m_cspec.getDependencies();
		if(deps.isEmpty())
			return;
		IDependenciesType dt = ICSpecXMLFactory.eINSTANCE.createDependenciesType();
		m_xmlSpec.getDependencies().add(dt);

		for(ComponentRequest dep : deps)
		{
			IComponentRequest xmlDep = ICSpecXMLFactory.eINSTANCE.createComponentRequest();
			xmlDep.setName(dep.getId());
			xmlDep.setComponentType(dep.getType());
			xmlDep.setRange(dep.getRange());
			xmlDep.setFilter(dep.getFilter());
			dt.getDependency().add(xmlDep);
		}
	}

	private void copyGenerators()
	{
		List<Generator> generators = m_cspec.getGenerators();
		if(generators.isEmpty())
			return;

		IGeneratorsType gt = ICSpecXMLFactory.eINSTANCE.createGeneratorsType();
		m_xmlSpec.getGenerators().add(gt);
		for(Generator generator : generators)
		{
			IGenerator xmlGen = ICSpecXMLFactory.eINSTANCE.createGenerator();
			ComponentIdentifier cid = generator.getGenerates();
			xmlGen.setGenerates(cid.getId());
			xmlGen.setGeneratesType(cid.getType());
			Version version = cid.getVersion();
			if(version != null)
				xmlGen.setGeneratesVersionString(version.toString());
			xmlGen.setAttribute(generator.getAttribute());
			ComponentRequest component = generator.getComponent();
			if(component != null)
			{
				xmlGen.setComponent(component.getId());
				xmlGen.setComponentType(component.getType());
			}
			gt.getGenerator().add(xmlGen);
		}
	}

	private void copyGroup(Group attr, IGroup xmlAttr)
	{
		copyAttribute(attr, xmlAttr);
		xmlAttr.setFilter(attr.getFilter());
		xmlAttr.setRebase(createStringFromPath(attr.getRebase()));
		copyPrerequisites(attr.getPrerequisites(), xmlAttr.getAttribute());
	}

	private void copyGroups(List<Group> groups)
	{
		if(groups.isEmpty())
			return;

		ICSpecXMLFactory factory = ICSpecXMLFactory.eINSTANCE;
		IGroupsType gt = factory.createGroupsType();
		m_xmlSpec.getGroups().add(gt);
		for(Group group : groups)
		{
			IGroup xmlGroup = factory.createGroup();
			copyGroup(group, xmlGroup);
			if(group.isPublic())
				gt.getPublic().add(xmlGroup);
			else
				gt.getPrivate().add(xmlGroup);
		}
	}

	private void copyPathGroup(PathGroup attr, IArtifact artifact)
	{
		artifact.setBase(createStringFromPath(attr.getBase()));
		List<IPath> paths = attr.getPaths();
		if(paths.size() == 0)
			return;
		if(paths.size() == 1)
			artifact.setPath1(createStringFromPath(paths.get(0)));
		else
		{
			List<org.eclipse.buckminster.cspecxml.IPath> xmlPaths = artifact.getPath();
			for(IPath path : paths)
			{
				org.eclipse.buckminster.cspecxml.IPath xmlPath = ICSpecXMLFactory.eINSTANCE.createPath();
				xmlPath.setPath(createStringFromPath(path));
				xmlPaths.add(xmlPath);
			}
		}
	}

	private void copyPathGroup(PathGroup attr, IProductsType product)
	{
		product.setBase(createStringFromPath(attr.getBase()));
		List<IPath> paths = attr.getPaths();
		if(paths.size() == 0)
			return;

		List<org.eclipse.buckminster.cspecxml.IPath> xmlPaths = product.getPath();
		for(IPath path : paths)
		{
			org.eclipse.buckminster.cspecxml.IPath xmlPath = ICSpecXMLFactory.eINSTANCE.createPath();
			xmlPath.setPath(createStringFromPath(path));
			xmlPaths.add(xmlPath);
		}
	}

	private void copyPrerequisites(List<Prerequisite> preqs, List<IPrerequisite> xmlPreqs)
	{
		ICSpecXMLFactory cspecFactory = ICSpecXMLFactory.eINSTANCE;
		for(Prerequisite preq : preqs)
		{
			IPrerequisite xmlPreq = cspecFactory.createPrerequisite();
			xmlPreq.setAlias(preq.getAlias());
			xmlPreq.setName(preq.getAttribute());
			xmlPreq.setContributor(preq.isContributor());
			xmlPreq.setOptional(preq.isOptional());
			xmlPreq.setFilter(preq.getFilter());
			xmlPreq.setIncludePattern(preq.getIncludePattern());
			xmlPreq.setExcludePattern(preq.getExcludePattern());
			ComponentRequest c = preq.getComponent();
			if(c != null)
			{
				xmlPreq.setComponentType(c.getType());
				xmlPreq.setComponent(c.getId());
			}
			xmlPreqs.add(xmlPreq);
		}
	}
}
