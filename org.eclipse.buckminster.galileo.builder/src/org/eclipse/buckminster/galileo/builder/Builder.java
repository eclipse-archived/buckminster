package org.eclipse.buckminster.galileo.builder;

import java.io.File;

import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.BuildPackage;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class Builder
{
	private final Build m_build;

	private final File m_tempFolder;

	public Builder(File buildModelLocation, File tempFolder) throws CoreException
	{
		// Create a resource set.
		ResourceSet resourceSet = new ResourceSetImpl();

		// Register the default resource factory -- only needed for stand-alone!
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		// Register the package -- only needed for stand-alone!
		BuildPackage.eINSTANCE.eClass();

		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(buildModelLocation.getAbsolutePath());

		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		EList<EObject> content = resource.getContents();
		if(content.size() != 1)
			throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d", //$NON-NLS-1$
					Integer.valueOf(content.size()));

		m_build = (Build)content.get(0);
		m_tempFolder = tempFolder;
	}

	public void runCompositeGenerator(IProgressMonitor monitor) throws CoreException
	{
		CompositeRepoGenerator repoGenerator = new CompositeRepoGenerator(m_tempFolder, m_build.getLabel()
				+ " Composite"); //$NON-NLS-1$
		repoGenerator.run(m_build, monitor);
	}

	public void runExtraRepoGenerator(IProgressMonitor monitor) throws Exception
	{
		ExtraRepoGenerator extraGenerator = new ExtraRepoGenerator(m_tempFolder, m_build.getLabel() + " Categories"); //$NON-NLS-1$
		extraGenerator.run(m_build, monitor);
	}

	public void runMirroring(IProgressMonitor monitor, File targetRepoLocation) throws Exception
	{
		MirrorGenerator mirrorGenerator = new MirrorGenerator(m_tempFolder.toURI(), targetRepoLocation,
				m_build.getLabel());
		mirrorGenerator.run(monitor);
	}

	public void runPlatformRepoGenerator(IProgressMonitor monitor, File targetPlatformLocation) throws Exception
	{
		PlatformRepoGenerator tpGenerator = new PlatformRepoGenerator(m_tempFolder, targetPlatformLocation);
		tpGenerator.run(monitor);
	}

	public void runRepositoryVerifier(IProgressMonitor monitor) throws Exception
	{
		RepositoryVerifier ipt = new RepositoryVerifier(m_tempFolder.toURI(), "org.eclipse.galileo", null); //$NON-NLS-1$
		ipt.run(m_build.getConfigs(), monitor);
	}
}
