/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.util;

import java.util.Iterator;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.EnabledStatusProvider;
import org.eclipse.buckminster.aggregator.InfosProvider;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.util.AggregatorResourceFactoryImpl
 * @generated
 */
public class AggregatorResourceImpl extends XMIResourceImpl implements AggregatorResource
{
	class NotifyAnalyzeResourceFinished extends NotificationImpl
	{
		public NotifyAnalyzeResourceFinished()
		{
			super(Notification.SET, false, true);
		}

		@Override
		public int getFeatureID(Class<?> expectedClass)
		{
			return RESOURCE__ANALYSIS_FINISHED;
		}

		@Override
		public Object getNotifier()
		{
			return AggregatorResourceImpl.this;
		}
	}

	class NotifyAnalyzeResourceStarted extends NotificationImpl
	{
		public NotifyAnalyzeResourceStarted()
		{
			super(Notification.SET, true, false);
		}

		@Override
		public int getFeatureID(Class<?> expectedClass)
		{
			return RESOURCE__ANALYSIS_STARTED;
		}

		@Override
		public Object getNotifier()
		{
			return AggregatorResourceImpl.this;
		}
	}

	/**
	 * The infos.
	 * 
	 * @see #getInfos
	 */
	protected EList<Diagnostic> infos;

	private boolean analysisRequest;

	private boolean analysisIsRunning;

	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param uri
	 *            the URI of the new resource.
	 * @generated
	 */
	public AggregatorResourceImpl(URI uri)
	{
		super(uri);
	}

	/*
	 * Analysis aggregator errors, warnings & infos
	 */
	public void analyzeResource()
	{
		Runnable runnable = new Runnable()
		{
			public void run()
			{
				synchronized(AggregatorResourceImpl.this)
				{
					while(analysisRequest)
						try
						{
							analysisRequest = false;
							analysisIsRunning = true;

							eNotify(new NotifyAnalyzeResourceStarted());

							if(errors != null)
							{
								Iterator<Diagnostic> iterator = errors.iterator();

								while(iterator.hasNext())
									if(iterator.next() instanceof ResourceDiagnosticImpl)
										iterator.remove();
							}

							if(warnings != null)
							{
								Iterator<Diagnostic> iterator = warnings.iterator();

								while(iterator.hasNext())
									if(iterator.next() instanceof ResourceDiagnosticImpl)
										iterator.remove();
							}

							if(infos != null)
							{
								Iterator<Diagnostic> iterator = infos.iterator();

								while(iterator.hasNext())
									if(iterator.next() instanceof ResourceDiagnosticImpl)
										iterator.remove();
							}

							Aggregator aggregator = (Aggregator)getContents().get(0);

							analyze((EObject)aggregator);
						}
						finally
						{
							eNotify(new NotifyAnalyzeResourceFinished());

							analysisIsRunning = false;
						}
				}
			}
		};

		if(analysisRequest)
			return;

		analysisRequest = true;

		if(analysisIsRunning)
			return;

		new Thread(runnable).start();
	}

	public EList<Diagnostic> getInfos()
	{
		if(infos == null)
		{
			infos = new NotifyingListImpl<Diagnostic>()
			{
				private static final long serialVersionUID = 1L;

				@Override
				public int getFeatureID()
				{
					return RESOURCE__INFOS;
				}

				@Override
				public Object getNotifier()
				{
					return AggregatorResourceImpl.this;
				}

				@Override
				protected boolean isNotificationRequired()
				{
					return AggregatorResourceImpl.this.eNotificationRequired();
				}
			};
		}
		return infos;
	}

	private void analyze(EObject object)
	{
		if(object instanceof EnabledStatusProvider && !((EnabledStatusProvider)object).isEnabled())
			return;

		if(object instanceof InfosProvider)
		{
			InfosProvider iProvider = (InfosProvider)object;

			for(String error : iProvider.getErrors())
				getErrors().add(new ResourceDiagnosticImpl(error, EcoreUtil.getURI(object).toString()));

			for(String warning : iProvider.getWarnings())
				getWarnings().add(new ResourceDiagnosticImpl(warning, EcoreUtil.getURI(object).toString()));

			for(String info : iProvider.getInfos())
				getInfos().add(new ResourceDiagnosticImpl(info, EcoreUtil.getURI(object).toString()));
		}

		if(object.eContents() == null)
			return;

		for(EObject childObject : object.eContents())
			analyze(childObject);
	}

} // AggregatorResourceImpl
