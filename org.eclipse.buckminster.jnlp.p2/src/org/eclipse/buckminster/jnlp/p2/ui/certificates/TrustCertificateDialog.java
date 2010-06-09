package org.eclipse.buckminster.jnlp.p2.ui.certificates;

import java.security.cert.X509Certificate;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * A dialog that displays a certificate chain and asks the user if they trust the certificate providers.
 */
public class TrustCertificateDialog extends ListSelectionDialog
{

	private TreeViewer certificateChainViewer;

	private Button detailsButton;

	protected TreeNode parentElement;

	protected Object selectedCertificate;

	private Image m_windowImage;

	private Image m_titleImage;

	public TrustCertificateDialog(Shell parentShell, Image windowImage, Image titleImage, Object input,
			ILabelProvider labelProvider, ITreeContentProvider contentProvider)
	{
		super(parentShell, input, contentProvider, labelProvider, "Do you trust these certificates?");
		setShellStyle(SWT.DIALOG_TRIM | SWT.MODELESS | SWT.RESIZE | getDefaultOrientation());
		m_windowImage = windowImage;
		m_titleImage = titleImage;
	}

	public TreeViewer getCertificateChainViewer()
	{
		return certificateChainViewer;
	}

	@Override
	protected void configureShell(Shell shell)
	{
		super.configureShell(shell);
		if(m_windowImage != null)
		{
			shell.setImage(m_windowImage);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = (Composite)super.createDialogArea(parent);
		certificateChainViewer = new TreeViewer(composite, SWT.BORDER);
		GridLayout layout = new GridLayout();
		certificateChainViewer.getTree().setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.grabExcessHorizontalSpace = true;
		certificateChainViewer.getTree().setLayoutData(data);
		certificateChainViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
		certificateChainViewer.setContentProvider(new TreeNodeContentProvider());
		certificateChainViewer.setLabelProvider(new CertificateLabelProvider());
		certificateChainViewer.addSelectionChangedListener(getChainSelectionListener());
		Object input = getViewer().getInput();
		if(input instanceof Object[])
		{
			ISelection selection = null;
			Object[] nodes = (Object[])input;
			if(nodes.length > 0)
			{
				selection = new StructuredSelection(nodes[0]);
				certificateChainViewer.setInput(new TreeNode[] { (TreeNode)nodes[0] });
				selectedCertificate = nodes[0];
			}
			getViewer().setSelection(selection);
		}
		getViewer().addDoubleClickListener(getDoubleClickListener());
		getViewer().addSelectionChangedListener(getParentSelectionListener());
		createButtons(composite);
		return composite;
	}

	private void createButtons(Composite composite)
	{
		// Details button to view certificate chain
		detailsButton = new Button(composite, SWT.NONE);
		detailsButton.setText("Details");
		detailsButton.addSelectionListener(new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent e)
			{
				if(selectedCertificate != null)
				{
					X509Certificate cert = (X509Certificate)((TreeNode)selectedCertificate).getValue();
					X509CertificateViewDialog certificateDialog = new X509CertificateViewDialog(getShell(),
							m_windowImage, m_titleImage, cert);
					certificateDialog.open();
				}
			}

			public void widgetSelected(SelectionEvent e)
			{
				widgetDefaultSelected(e);
			}
		});
	}

	private ISelectionChangedListener getChainSelectionListener()
	{
		return new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				ISelection selection = event.getSelection();
				if(selection instanceof StructuredSelection)
				{
					selectedCertificate = ((StructuredSelection)selection).getFirstElement();
				}
			}
		};
	}

	private IDoubleClickListener getDoubleClickListener()
	{
		return new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				Object selectedElement = selection.getFirstElement();
				if(selectedElement instanceof TreeNode)
				{
					TreeNode treeNode = (TreeNode)selectedElement;
					// create and open dialog for certificate chain
					X509CertificateViewDialog certificateViewDialog = new X509CertificateViewDialog(getShell(),
							m_windowImage, m_titleImage, (X509Certificate)treeNode.getValue());
					certificateViewDialog.open();
				}
			}
		};
	}

	private ISelectionChangedListener getParentSelectionListener()
	{
		return new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				ISelection selection = event.getSelection();
				if(selection instanceof StructuredSelection)
				{
					getCertificateChainViewer().setInput(
							new TreeNode[] { (TreeNode)((StructuredSelection)selection).getFirstElement() });
					getCertificateChainViewer().refresh();
				}
			}
		};
	}
}
