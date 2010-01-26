package org.eclipse.buckminster.core.cspec;

public interface IGenerator
{
	String getAttribute();

	String getComponent();

	IComponentIdentifier getGeneratedIdentifier();
}
