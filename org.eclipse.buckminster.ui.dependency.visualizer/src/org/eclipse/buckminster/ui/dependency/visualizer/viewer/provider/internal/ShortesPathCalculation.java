/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.metadata.model.BOMNode;

/**
 * This helper class is used for shortest path calculation between two nodes.
 * 
 * @author Johannes Utzig
 * 
 */
public class ShortesPathCalculation {

	private static final Integer VERY_LARGE_NUMBER = Integer.valueOf(1000000000);

	/**
	 * calculates the shortest path from the given root to the target node
	 * 
	 * @param root
	 * @param target
	 * @return a list with the path segments
	 */
	public static List<BOMNode> calculatePath(BOMNode root, BOMNode target) {
		List<BOMNode> queue = new LinkedList<BOMNode>();
		Set<BOMNode> nodes = new HashSet<BOMNode>();
		LinkedList<BOMNode> orderedList = new LinkedList<BOMNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BOMNode head = queue.remove(0);
			if (!nodes.contains(head)) {
				nodes.add(head);
				orderedList.add(head);
				queue.addAll(head.getChildren());
			}
		}
		List<BOMNode> path = internalCalulcation(orderedList, root, target);
		return path;
	}

	private static Map<BOMNode, Integer> initializeLengthMap(List<BOMNode> queue, BOMNode root) {
		Map<BOMNode, Integer> map = new HashMap<BOMNode, Integer>();
		for (BOMNode bomNode : queue) {
			map.put(bomNode, VERY_LARGE_NUMBER);
			map.put(root, Integer.valueOf(0));
		}
		return map;
	}

	private static List<BOMNode> internalCalulcation(List<BOMNode> queue, BOMNode root, BOMNode target) {
		Map<BOMNode, BOMNode> previous = new HashMap<BOMNode, BOMNode>();
		Map<BOMNode, Integer> lengthMap = initializeLengthMap(queue, root);

		while (!queue.isEmpty()) {
			BOMNode head = queue.remove(0);
			List<BOMNode> outgoing = head.getChildren();
			for (BOMNode current : outgoing) {
				int headLength = lengthMap.get(head).intValue();
				int currentLength = lengthMap.get(current).intValue();
				if (headLength + 1 < currentLength) {
					previous.put(current, head);
					lengthMap.put(current, Integer.valueOf(headLength + 1));
				}
			}
		}
		List<BOMNode> path = new ArrayList<BOMNode>(previous.size());

		while (previous.containsKey(target)) {
			path.add(target);
			target = previous.get(target);
		}
		path.add(target);
		return path;
	}

}
