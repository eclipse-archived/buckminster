package org.eclipse.buckminster.core.cspec;

public interface IGenerator
{
	String getAttribute();

	String getComponent();

	IComponentIdentifier getGeneratedIdentifier();

	/**
	 * @deprecated Use {@link #getGeneratedIdentifier()}
	 */
	@Deprecated
	String getGenerates();
}
