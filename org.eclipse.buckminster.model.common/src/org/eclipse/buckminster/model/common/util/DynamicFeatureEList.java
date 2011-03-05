package org.eclipse.buckminster.model.common.util;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap.Internal;
import org.eclipse.emf.ecore.util.FeatureMapUtil.FeatureEList;

public abstract class DynamicFeatureEList<E> extends FeatureEList<E> {

	public DynamicFeatureEList(EStructuralFeature feature, Internal featureMap) {
		super(feature, featureMap);
	}

	@Override
	public void add(int index, Object object) {
		featureMap.add(getEStructuralFeature(object), index, object);
	}

	@Override
	public boolean add(Object object) {
		return featureMap.add(getEStructuralFeature(object), object);
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean result = false;
		for (E object : collection)
			if (featureMap.add(getEStructuralFeature(object), object))
				result = true;
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		if (collection.isEmpty())
			return false;
		for (E object : collection)
			featureMap.add(index++, getEStructuralFeature(object), object);
		return true;
	}

	@Override
	public boolean addAllUnique(Collection<? extends E> collection) {
		if (collection.isEmpty())
			return false;
		for (E object : collection)
			featureMap.addUnique(getEStructuralFeature(object), object);
		return true;
	}

	@Override
	public boolean addAllUnique(int index, Collection<? extends E> collection) {
		if (collection.isEmpty())
			return false;
		for (E object : collection)
			featureMap.addUnique(getEStructuralFeature(object), index++, object);
		return true;
	}

	@Override
	public void addUnique(int index, Object object) {
		featureMap.addUnique(getEStructuralFeature(object), index, object);
	}

	@Override
	public void addUnique(Object object) {
		featureMap.addUnique(getEStructuralFeature(object), object);
	}

	@Override
	public NotificationChain basicAdd(E object, NotificationChain notifications) {
		return featureMap.basicAdd(getEStructuralFeature(object), object, notifications);
	}

	@Override
	public boolean basicContains(Object object) {
		return featureMap.basicContains(getEStructuralFeature(object), object);
	}

	@Override
	public boolean basicContainsAll(Collection<?> collection) {
		for (Object object : collection)
			if (!featureMap.basicContains(getEStructuralFeature(object), object))
				return false;
		return true;
	}

	@Override
	public int basicIndexOf(Object object) {
		return featureMap.basicIndexOf(getEStructuralFeature(object), object);
	}

	@Override
	public int basicLastIndexOf(Object object) {
		return featureMap.basicLastIndexOf(getEStructuralFeature(object), object);
	}

	@Override
	public NotificationChain basicRemove(Object object, NotificationChain notifications) {
		return featureMap.basicRemove(getEStructuralFeature(object), object, notifications);
	}

	@Override
	public boolean contains(Object object) {
		return featureMap.contains(getEStructuralFeature(object), object);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object object : collection)
			if (!featureMap.contains(getEStructuralFeature(object), object))
				return false;
		return true;
	}

	@Override
	public int indexOf(Object object) {
		return featureMap.indexOf(getEStructuralFeature(object), object);
	}

	@Override
	public int lastIndexOf(Object object) {
		return featureMap.lastIndexOf(getEStructuralFeature(object), object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public E move(int targetIndex, int sourceIndex) {
		Object sourceObj = get(sourceIndex);
		return (E) featureMap.move(getEStructuralFeature(sourceObj), targetIndex, sourceIndex);
	}

	@Override
	public void move(int index, Object object) {
		featureMap.move(getEStructuralFeature(object), index, object);
	}

	@Override
	public boolean remove(Object object) {
		return featureMap.remove(getEStructuralFeature(object), object);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean result = false;
		for (Object object : collection)
			if (featureMap.remove(getEStructuralFeature(object), object))
				result = true;
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E object) {
		return (E) featureMap.set(getEStructuralFeature(object), index, object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public E setUnique(int index, E object) {
		return (E) featureMap.setUnique(getEStructuralFeature(object), index, object);
	}

	protected abstract EStructuralFeature getEStructuralFeature(Object value);
}
