package org.eclipse.b3.coloring;

import org.eclipse.b3.beeLang.BeeModel;
import org.eclipse.b3.beeLang.PathVector;
import org.eclipse.b3.beeLang.RealLiteral;
import org.eclipse.b3.beeLang.Version;
import org.eclipse.b3.beeLang.VersionRange;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.parsetree.LeafNode;
import org.eclipse.xtext.parsetree.NodeAdapter;
import org.eclipse.xtext.parsetree.NodeUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.ISemanticHighlightingCalculator;

public class BeeLangSemanticHighlightingCalculator implements
ISemanticHighlightingCalculator {

	public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor) {
		if(resource == null)
			return;
//		Iterable<AbstractNode> allNodes = NodeUtil.getAllContents(
//				resource.getParseResult().getRootNode());
		EList<EObject> contents = resource.getContents();
		if(contents == null || contents.size() == 0)
			return; // nothing there at all - probably an empty file
		BeeModel model = (BeeModel) contents.get(0);
//		BuildUnit bu = model.getBody();
		TreeIterator<EObject> all = model.eAllContents();
		while(all.hasNext())
		{
			EObject o = all.next();
			if( o instanceof RealLiteral)
				highlightReal((RealLiteral)o, acceptor);
			else if( o instanceof Version)
				highlightVersion((Version)o, acceptor);
			else if (o instanceof VersionRange)
				highlightVersionRange((VersionRange)o, acceptor);
			else if(o instanceof PathVector)
				highlightPaths((PathVector)o, acceptor);
		}
		
	}
	public void highlightReal(RealLiteral v, IHighlightedPositionAcceptor acceptor){
		highlightObject(v, BeeLangSemanticHighligtConfiuration.REAL_ID, acceptor);
	}
	public void highlightPaths(PathVector v, IHighlightedPositionAcceptor acceptor){
		highlightObject(v, BeeLangSemanticHighligtConfiuration.PATH_ID, acceptor);
	}

	public void highlightVersion(Version v, IHighlightedPositionAcceptor acceptor){
		highlightObject(v, BeeLangSemanticHighligtConfiuration.VERSION_ID, acceptor);
	}
	public void highlightVersionRange(VersionRange v, IHighlightedPositionAcceptor acceptor){
		highlightObject(v, BeeLangSemanticHighligtConfiuration.VERSION_ID, acceptor);

//		highlightFirstFeature(v, "minLimit", BeeLangSemanticHighligtConfiuration.VERSION_ID, acceptor);
//		highlightFirstFeature(v, "maxLimit", BeeLangSemanticHighligtConfiuration.VERSION_ID, acceptor);

	}
	private void highlightObject(EObject semantic, String highlightID, IHighlightedPositionAcceptor acceptor) {
		NodeAdapter adapter = NodeUtil.getNodeAdapter(semantic);
		if(adapter == null) {
			// TODO: WARNING - Could not find node
			return;
		}
		CompositeNode node = adapter.getParserNode();
		if(node == null) {
			// TODO: WARNING - Could not find node
			return;
		}
		acceptor.addPosition(node.getOffset(), node.getLength(), highlightID);
	}
	
	// helper method that takes care of highlighting the first feature element
	// of a semantic object using a given text style ID
	@SuppressWarnings("unused")
	private void highlightFirstFeature(EObject semobject, String featurename, 
			String highlightID, IHighlightedPositionAcceptor acceptor) {
		// fetch the parse node for the entity
		LeafNode nodetohighlight = getFirstFeatureNode(semobject, featurename);
		if(nodetohighlight == null)
		{
			// TODO: WARNING - Could not find node
			return;
		}
		acceptor.addPosition(nodetohighlight.getOffset(), nodetohighlight
				.getLength(), highlightID);
	}

	// adapted from Sebastian Zarnekow's semantic highlighting implementation
	// navigate to the parse node corresponding to the semantic object and
	// fetch the leaf node that corresponds to the first feature with the given
	// name
	public LeafNode getFirstFeatureNode(EObject semantic, String feature) {
		NodeAdapter adapter = NodeUtil.getNodeAdapter(semantic);
		if (adapter != null) {
			CompositeNode node = adapter.getParserNode();
			if (node != null) {
				if (feature == null)
					return null;
				for (AbstractNode child : node.getChildren()) {
					if (child instanceof LeafNode) {
						if (feature.equals(((LeafNode) child).getFeature())) {
							return (LeafNode) child;
						}
					}
// Hack while debugging - should be a recursive operation
//					else if (child instanceof CompositeNode) {
//						for(AbstractNode child2 : ((CompositeNode) child).getChildren())
//							if(child2 instanceof LeafNode) {
//								if (feature.equals(((LeafNode) child2).getFeature())) {
//									return (LeafNode) child2;
//								}
//								
//							}
//					}
				}
			}
		}
		return null;
	}
}
