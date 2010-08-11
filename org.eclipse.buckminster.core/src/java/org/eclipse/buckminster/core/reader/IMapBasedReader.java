package org.eclipse.buckminster.core.reader;

import java.util.Map;

import org.eclipse.buckminster.rmap.util.IComponentReader;

public interface IMapBasedReader extends IComponentReader {
	/**
	 * Returns a map that contains entries in the following form:
	 * 
	 * <pre>
	 * &lt;elementType&gt;@&lt;elementID&gt; = &lt;REPOSITORYgt;, &lt;TAG&gt;, [...]
	 * </pre>
	 * 
	 * @return
	 */
	Map<String, String> getMap();
}
