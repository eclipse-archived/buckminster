/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Karel Brezina
 * 
 */
public class TwoColumnMatrix<K, V>
{
	class MatrixEntry
	{
		K m_key;

		V m_value;

		public MatrixEntry(K key, V value)
		{
			m_key = key;
			m_value = value;
		}

		public K getKey()
		{
			return m_key;
		}

		public V getValue()
		{
			return m_value;
		}
	}

	class MatrixIterator implements ExtendedListIterator<MatrixEntry>
	{
		private TwoColumnMatrix<K, V> m_matrix;

		private int m_index = -1;

		public MatrixIterator(TwoColumnMatrix<K, V> matrix)
		{
			m_matrix = matrix;
		}

		public void add(MatrixEntry entry)
		{
			m_matrix.add(m_index++, entry);
		}

		public MatrixEntry first()
		{
			m_index = 0;
			return m_matrix.getEntry(m_index);
		}

		public boolean hasNext()
		{
			return m_index < (m_matrix.size() - 1);
		}

		public boolean hasPrevious()
		{
			return m_index > 0;
		}

		public MatrixEntry last()
		{
			m_index = m_matrix.size() - 1;
			return m_matrix.getEntry(m_index);
		}

		public MatrixEntry next()
		{
			m_index++;

			if(m_index >= m_matrix.size())
				throw new NoSuchElementException();

			return m_matrix.getEntry(m_index);
		}

		public int nextIndex()
		{
			return m_index + 1;
		}

		public MatrixEntry previous()
		{
			m_index--;

			if(m_index < 0)
				throw new NoSuchElementException();

			return m_matrix.getEntry(m_index);
		}

		public int previousIndex()
		{
			return m_index - 1;
		}

		public void remove()
		{
			m_matrix.remove(m_index);
		}

		public void set(MatrixEntry entry)
		{
			m_matrix.set(m_index, entry);
		}
	}

	List<K> m_keys;

	List<V> m_values;

	public TwoColumnMatrix()
	{
		this(10);
	}

	public TwoColumnMatrix(int size)
	{
		m_keys = new ArrayList<K>(size);
		m_values = new ArrayList<V>(size);
	}

	public void add(int index, K key, V value)
	{
		m_keys.add(index, key);
		m_values.add(index, value);
	}

	public void add(int index, MatrixEntry entry)
	{
		add(index, entry.getKey(), entry.getValue());
	}

	public void add(K key, V value)
	{
		m_keys.add(key);
		m_values.add(value);
	}

	public void add(MatrixEntry entry)
	{
		add(entry.getKey(), entry.getValue());
	}

	public void addAll(TwoColumnMatrix<K, V> matrix)
	{
		m_keys.addAll(matrix.m_keys);
		m_values.addAll(matrix.m_values);
	}

	public void clear()
	{
		m_keys.clear();
		m_values.clear();
	}

	public MatrixEntry getEntry(int index)
	{
		return index < size()
				? new MatrixEntry(m_keys.get(index), m_values.get(index))
				: null;
	}

	public K getKey(int index)
	{
		return m_keys.get(index);
	}

	public V getValue(int index)
	{
		return m_values.get(index);
	}

	public int indexOf(K key)
	{
		return m_keys.indexOf(key);
	}

	public void remove(int index)
	{
		m_keys.remove(index);
		m_values.remove(index);
	}

	public void set(int index, K key, V value)
	{
		m_keys.set(index, key);
		m_values.set(index, value);
	}

	public void set(int index, MatrixEntry entry)
	{
		set(index, entry.getKey(), entry.getValue());
	}

	public int size()
	{
		return m_keys.size();
	}
}
