package org.eclipse.buckminster.core.test.rmap;

import java.io.InputStream;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.IResolverFactory;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.resolver.ResolverFactoryMaintainer;
import org.eclipse.buckminster.core.resolver.ResourceMapResolverFactory;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class UnresolvedBOMTest extends AbstractTestCase
{
	/**
	 * Same as testReResolve() but uses the MainResolver
	 * @throws Exception
	 */
	public void testReResolve2() throws Exception
	{
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest("buckminster.test.simple_d", null, null));
		ComponentQuery query = queryBld.createComponentQuery();

		// Fake extension point registration
		//
		ResolverFactoryMaintainer.getInstance().setResolverFactories(new IResolverFactory[] {
			createRMAPResolverFactory("rmap_c.rmap"), createRMAPResolverFactory("rmap_abd.rmap") });

		// Resolve using the MainResolver
		//
		IResolver resolver = new MainResolver(new ResolutionContext(query));
		BillOfMaterials bom = resolver.resolve(new NullProgressMonitor());
		assertTrue("bom is not fully resolved", bom.isFullyResolved());
	}

	public void testReResolve() throws Exception
	{
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest("buckminster.test.simple_d", null, null));
		ComponentQuery query = queryBld.createComponentQuery();
		
		ResolutionContext context = new ResolutionContext(query);
		IResolver resolver1 = createRMAPResolverFactory("rmap_c.rmap").createResolver(context);
		resolver1.getContext().setContinueOnError(true);
		IResolver resolver2 = createRMAPResolverFactory("rmap_abd.rmap").createResolver(context);
		resolver2.getContext().setContinueOnError(true);

		IProgressMonitor nullMon = new NullProgressMonitor();
		BillOfMaterials bom1 = resolver1.resolve(query.getRootRequest(), nullMon);
		Utils.serialize(bom1, System.out);
		BillOfMaterials bom2 = resolver2.resolveRemaining(bom1, nullMon);
		Utils.serialize(bom2, System.out);
		BillOfMaterials bom3 = resolver1.resolveRemaining(bom2, nullMon);
		Utils.serialize(bom3, System.out);
		assertTrue("bom3 is not fully resolved", bom3.isFullyResolved());
	}

	public void testUnresolvedBOM() throws Exception
	{
		// Get the ComponentQuery
		//
		URL queryURL = new URL("http://www.eclipse.org/buckminster/samples/queries/actor_a.cquery");
		InputStream input = queryURL.openStream();
		ComponentQuery query = ComponentQuery.fromStream(queryURL.toString(), input);
		input.close();

		// Create our dependency to the top node
		//
		ComponentRequest rootRequest = query.getRootRequest();
		QualifiedDependency qDep = new QualifiedDependency(rootRequest, query.getAttributes(rootRequest));

		// Create a resolver that uses the RMAP from the query, i.e.
		// just bluntly override anything that has been set through
		// extension points.
		//
		ResolverFactoryMaintainer.getInstance().setResolverFactories(new IResolverFactory[] { new ResourceMapResolverFactory() });
		final IResolver resolver = new MainResolver(new ResolutionContext(query));
		BillOfMaterials unresolved = BillOfMaterials.create(new UnresolvedNode(qDep), query);

		final BillOfMaterials[] bucket = new BillOfMaterials[] { unresolved };

		Job resolveJob = new Job("Resolving")
		{
			@Override
			public boolean belongsTo(Object family)
			{
				// I'm the sole member of this family
				//
				return family == this;
			}

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				try
				{
					bucket[0] = bucket[0].fullyResolve(resolver, monitor);
					return Status.OK_STATUS;
				}
				catch(CoreException e)
				{
					CorePlugin.getLogger().error(e, e.toString());
					return e.getStatus();
				}
			}	
		};

		// Wait for job completion
		//
		resolveJob.schedule();
		Job.getJobManager().join(resolveJob, null);

		// Are we resolved now?
		//
		assertTrue("bom is not fully resolved", bucket[0].isFullyResolved());
	}

	public void testBug170839() throws Exception
	{
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest("buckminster.test.simple_d", null, null));
		ComponentQuery query = queryBld.createComponentQuery();

		ResolutionContext context = new ResolutionContext(query);
		IResolver resolver1 = createRMAPResolverFactory("rmap_abd.rmap").createResolver(context);
		resolver1.getContext().setContinueOnError(true);
		IResolver resolver2 = createRMAPResolverFactory("rmap_d.rmap").createResolver(context);
		resolver2.getContext().setContinueOnError(true);

		IProgressMonitor nullMon = new NullProgressMonitor();
		BillOfMaterials bom1 = resolver1.resolve(query.getRootRequest(), nullMon);
		BillOfMaterials bom2 = resolver2.resolveRemaining(bom1, nullMon);
		assertEquals("bom1 is not equal to bom2", bom1, bom2);
	}

	private IResolverFactory createRMAPResolverFactory(String rmap) throws CoreException
	{
		URL rmapURL = getClass().getResource(rmap);
		ResourceMapResolverFactory f = new ResourceMapResolverFactory();
		f.setExtensionParameter(
			ResourceMapResolverFactory.RESOURCE_MAP_URL_PARAM, rmapURL.toString());
		f.setExtensionParameter(
			ResourceMapResolverFactory.OVERRIDE_QUERY_URL_PARAM, "true");
		return f;
	}
}
