package org.eclipse.buckminster.core.common.model;

import java.util.Map;

public interface IExpandingMap<K, V> {
	V get(Object key, Map<K, V> expansionScope);
}
