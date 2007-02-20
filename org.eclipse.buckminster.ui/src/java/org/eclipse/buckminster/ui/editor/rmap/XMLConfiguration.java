package org.eclipse.buckminster.ui.editor.rmap;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class XMLConfiguration extends SourceViewerConfiguration
{
	private XMLDoubleClickStrategy m_doubleClickStrategy;
	private XMLTagScanner m_tagScanner;
	private XMLScanner m_scanner;
	private final ColorManager m_colorManager;

	public XMLConfiguration(ColorManager colorManager)
	{
		m_colorManager = colorManager;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer)
	{
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, XMLPartitionScanner.XML_COMMENT,
				XMLPartitionScanner.XML_TAG };
	}

	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType)
	{
		if(m_doubleClickStrategy == null)
			m_doubleClickStrategy = new XMLDoubleClickStrategy();
		return m_doubleClickStrategy;
	}

	protected XMLScanner getXMLScanner()
	{
		if(m_scanner == null)
		{
			m_scanner = new XMLScanner(m_colorManager);
			m_scanner.setDefaultReturnToken(new Token(
					new TextAttribute(m_colorManager.getColor(IXMLColorConstants.DEFAULT))));
		}
		return m_scanner;
	}

	protected XMLTagScanner getXMLTagScanner()
	{
		if(m_tagScanner == null)
		{
			m_tagScanner = new XMLTagScanner(m_colorManager);
			m_tagScanner
					.setDefaultReturnToken(new Token(new TextAttribute(m_colorManager.getColor(IXMLColorConstants.TAG))));
		}
		return m_tagScanner;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
	{
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getXMLTagScanner());
		reconciler.setDamager(dr, XMLPartitionScanner.XML_TAG);
		reconciler.setRepairer(dr, XMLPartitionScanner.XML_TAG);

		dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(new TextAttribute(m_colorManager
				.getColor(IXMLColorConstants.XML_COMMENT)));
		reconciler.setDamager(ndr, XMLPartitionScanner.XML_COMMENT);
		reconciler.setRepairer(ndr, XMLPartitionScanner.XML_COMMENT);

		return reconciler;
	}

}
