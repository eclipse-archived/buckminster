package org.eclipse.buckminster.aggregator.engine;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class BuilderPhase
{
	private final Builder builder;

	protected BuilderPhase(Builder builder)
	{
		this.builder = builder;
	}

	public Builder getBuilder()
	{
		return builder;
	}

	public abstract void run(IProgressMonitor monitor) throws CoreException;
}
