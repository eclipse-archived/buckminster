package org.eclipse.buckminster.cspec.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.cspec.Action;
import org.eclipse.buckminster.cspec.ActionAttribute;
import org.eclipse.buckminster.cspec.Artifact;
import org.eclipse.buckminster.cspec.Attribute;
import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecFactory;
import org.eclipse.buckminster.cspec.Generator;
import org.eclipse.buckminster.cspec.Group;
import org.eclipse.buckminster.cspec.PathGroup;
import org.eclipse.buckminster.cspec.Prerequisite;
import org.eclipse.buckminster.cspec.UpToDatePolicy;
import org.eclipse.buckminster.cspec.impl.CSpecImpl;
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
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.impl.ComponentIdentifierImpl;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class ImportCSpexXML {
	private static IPath createPathFromString(String path) {
		return path == null ? null : Path.fromPortableString(path);
	}

	private CSpecImpl cspec;

	private IComponentSpec xmlSpec;

	public CSpec importCSpec(IComponentSpec xmlSpc) {
		xmlSpec = xmlSpc;
		cspec = (CSpecImpl) CspecFactory.eINSTANCE.createCSpec();
		cspec.setId(xmlSpc.getName());
		cspec.setType(xmlSpc.getComponentType());
		cspec.setVersion(xmlSpc.getVersion());
		cspec.setDocumentation(xmlSpc.getDocumentation());
		cspec.setFilter(xmlSpc.getFilter());
		cspec.setShortDesc(xmlSpc.getShortDesc());
		cspec.setProjectInfo(xmlSpc.getProjectInfo());
		cspec.getAttributes().add(CspecFactory.eINSTANCE.createSelfArtifact());
		copyDependencies();
		copyGenerators();
		copyArtifacts();
		copyActions();
		copyGroups();
		return cspec;
	}

	private void copyAction(IAction xmlAttr, Action attr, boolean asPublic) {
		copyAttribute(xmlAttr, attr, asPublic);
		attr.setFilter(xmlAttr.getFilter());
		IPrerequisites xps = xmlAttr.getPrerequisites().get(0);
		attr.setPrerequisitesAlias(xps.getAlias());
		attr.setPrerequisitesRebase(createPathFromString(xps.getRebase()));
		copyPrerequisites(xps.getAttribute(), attr.getPrerequisites());
		if (!xmlAttr.getProducts().isEmpty()) {
			IProductsType pt = xmlAttr.getProducts().get(0);
			List<IActionArtifact> aaPriv = pt.getPrivate();
			List<IActionArtifact> aaPubl = pt.getPublic();
			if (aaPriv.isEmpty() && aaPubl.isEmpty()) {
				// Single anonymous product. No actions
				PathGroup pathGroup = CspecFactory.eINSTANCE.createPathGroup();
				copyPathGroup(pt.getBase(), pt.getPath(), pathGroup);
				attr.setProduct(pathGroup);
			} else {
				copyActionArtifacts(aaPriv, attr, false);
				copyActionArtifacts(aaPubl, attr, true);
			}
			attr.setProductAlias(pt.getAlias());
			attr.setProductFileCount(pt.getFileCount());
			attr.setUpToDatePolicy(UpToDatePolicy.valueOf(pt.getUpToDatePolicy().getName()));
			attr.setPattern(pt.getPattern());
			attr.setReplacement(pt.getReplacement());
		}
	}

	private void copyActionArtifacts(List<IActionArtifact> xmlAttrs, Action action, boolean asPublic) {
		List<Attribute> attrs = cspec.getAttributes();
		CspecFactory cspecFactory = CspecFactory.eINSTANCE;
		for (IActionArtifact xmlArtifact : xmlAttrs) {
			ActionAttribute artifact = cspecFactory.createActionAttribute();
			artifact.setAction(action);
			artifact.setAlias(xmlArtifact.getAlias());
			artifact.setBase(createPathFromString(xmlArtifact.getBase()));
			copyArtifact(xmlArtifact, artifact, asPublic);
			attrs.add(artifact);
		}
	}

	private void copyActions() {
		for (IActionsType at : xmlSpec.getActions()) {
			copyActions(at.getPrivate(), false);
			copyActions(at.getPublic(), true);
		}
	}

	private void copyActions(List<IAction> xmlAttrs, boolean asPublic) {
		List<Attribute> attrs = cspec.getAttributes();
		CspecFactory cspecFactory = CspecFactory.eINSTANCE;
		for (IAction xmlAttr : xmlAttrs) {
			Action attr = cspecFactory.createAction();
			copyAction(xmlAttr, attr, asPublic);
			attrs.add(attr);
		}
	}

	private void copyArtifact(IArtifact xmlAttr, Artifact attr, boolean asPublic) {
		copyAttribute(xmlAttr, attr, asPublic);
		List<org.eclipse.buckminster.cspecxml.IPath> paths = xmlAttr.getPath();
		if (paths.isEmpty() && xmlAttr.getPath1() != null) {
			org.eclipse.buckminster.cspecxml.IPath path = ICSpecXMLFactory.eINSTANCE.createPath();
			path.setPath(xmlAttr.getPath1());
			paths = Collections.singletonList(path);
		}
		copyPathGroup(xmlAttr.getBase(), paths, attr);
		attr.setFilter(xmlAttr.getFilter());
	}

	private void copyArtifacts() {
		for (IArtifactsType at : xmlSpec.getArtifacts()) {
			copyArtifacts(at.getPrivate(), false);
			copyArtifacts(at.getPublic(), true);
		}
	}

	private void copyArtifacts(List<IArtifact> xmlAttrs, boolean asPublic) {
		List<Attribute> attrs = cspec.getAttributes();
		CspecFactory cspecFactory = CspecFactory.eINSTANCE;
		for (IArtifact xmlArtifact : xmlAttrs) {
			Artifact artifact = cspecFactory.createArtifact();
			copyArtifact(xmlArtifact, artifact, asPublic);
			attrs.add(artifact);
		}
	}

	private void copyAttribute(IAttribute xmlAttr, Attribute attr, boolean asPublic) {
		attr.setDocumentation(xmlAttr.getDocumentation());
		attr.setName(xmlAttr.getName());
		attr.setPublic(asPublic);
		attr.setCspec(cspec);
	}

	private void copyDependencies() {
		List<ComponentRequest> deps = cspec.getDependencies();
		CommonFactory commonFactory = CommonFactory.eINSTANCE;
		for (IDependenciesType dt : xmlSpec.getDependencies())
			for (IComponentRequest xmlDep : dt.getDependency()) {
				ComponentRequest dep = commonFactory.createComponentRequest();
				dep.setId(xmlDep.getName());
				dep.setType(xmlDep.getComponentType());
				dep.setRange(xmlDep.getRange());
				dep.setFilter(xmlDep.getFilter());
				deps.add(dep);
			}
	}

	private void copyGenerators() {
		CommonFactory commonFactory = CommonFactory.eINSTANCE;
		CspecFactory cspecFactory = CspecFactory.eINSTANCE;
		List<Generator> generators = cspec.getGenerators();
		for (IGeneratorsType gt : xmlSpec.getGenerators()) {
			for (IGenerator xmlGen : gt.getGenerator()) {
				Generator generator = cspecFactory.createGenerator();
				generator.setCspec(cspec);
				ComponentIdentifierImpl ciImpl = (ComponentIdentifierImpl) commonFactory.createComponentIdentifier();
				ciImpl.setId(xmlGen.getGenerates());
				ciImpl.setType(xmlGen.getGeneratesType());
				ciImpl.setVersion(xmlGen.getGeneratesVersion());
				generator.setGenerates(ciImpl);
				generator.setAttribute(xmlGen.getAttribute());
				generator.setComponent(findComponent(xmlGen.getComponent(), xmlGen.getComponentType()));
				generators.add(generator);
			}
		}
	}

	private void copyGroup(IGroup xmlAttr, Group attr, boolean asPublic) {
		copyAttribute(xmlAttr, attr, asPublic);
		attr.setFilter(xmlAttr.getFilter());
		attr.setRebase(createPathFromString(xmlAttr.getRebase()));
		copyPrerequisites(xmlAttr.getAttribute(), attr.getPrerequisites());
	}

	private void copyGroups() {
		for (IGroupsType at : xmlSpec.getGroups()) {
			copyGroups(at.getPrivate(), false);
			copyGroups(at.getPublic(), true);
		}
	}

	private void copyGroups(List<IGroup> xmlAttrs, boolean asPublic) {
		List<Attribute> attrs = cspec.getAttributes();
		CspecFactory cspecFactory = CspecFactory.eINSTANCE;
		for (IGroup xmlAttr : xmlAttrs) {
			Group attr = cspecFactory.createGroup();
			copyGroup(xmlAttr, attr, asPublic);
			attrs.add(attr);
		}
	}

	private void copyPathGroup(String base, List<org.eclipse.buckminster.cspecxml.IPath> xmlPaths, PathGroup attr) {
		List<IPath> paths = attr.getPaths();
		for (org.eclipse.buckminster.cspecxml.IPath xmlPath : xmlPaths)
			paths.add(createPathFromString(xmlPath.getPath()));
		attr.setBase(createPathFromString(base));
	}

	private void copyPrerequisites(List<IPrerequisite> xmlPreqs, List<Prerequisite> preqs) {
		CspecFactory cspecFactory = CspecFactory.eINSTANCE;
		for (IPrerequisite xmlPreq : xmlPreqs) {
			Prerequisite preq = cspecFactory.createPrerequisite();
			preq.setAlias(xmlPreq.getAlias());
			preq.setAttribute(xmlPreq.getName());
			preq.setContributor(xmlPreq.isContributor());
			preq.setOptional(xmlPreq.isOptional());
			preq.setFilter(xmlPreq.getFilter());
			preq.setIncludePattern(xmlPreq.getIncludePattern());
			preq.setExcludePattern(xmlPreq.getExcludePattern());
			preq.setComponent(findComponent(xmlPreq.getComponent(), xmlPreq.getComponentType()));
			preqs.add(preq);
		}
	}

	private ComponentRequest findComponent(String compId, String type) {
		if (compId != null)
			for (ComponentRequest dep : cspec.getDependencies())
				if (compId.equals(dep.getId()) && (type == null || type.equals(dep.getType())))
					return dep;
		return null;
	}
}
