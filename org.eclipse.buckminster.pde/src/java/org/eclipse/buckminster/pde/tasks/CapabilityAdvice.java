package org.eclipse.buckminster.pde.tasks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.equinox.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.publisher.AbstractAdvice;
import org.eclipse.equinox.p2.publisher.actions.ICapabilityAdvice;

@SuppressWarnings("restriction")
public class CapabilityAdvice extends AbstractAdvice implements ICapabilityAdvice {
	private static final IRequirement[] noRequirements = new IRequirement[0];
	private static final IProvidedCapability[] noCapabilities = new IProvidedCapability[0];
	private final String id;

	private final Version version;

	private List<IProvidedCapability> capabilities;
	private List<IRequirement> requirements;
	private List<IRequirement> metaRequirements;

	public CapabilityAdvice(String id, Version version) {
		this.id = id;
		this.version = version;
	}

	public void addMetaRequirement(IRequirement requirement) {
		if (metaRequirements == null)
			metaRequirements = new ArrayList<IRequirement>();
		metaRequirements.add(requirement);
	}

	public void addProvidedCapability(IProvidedCapability capability) {
		if (capabilities == null)
			capabilities = new ArrayList<IProvidedCapability>();
		capabilities.add(capability);
	}

	public void addRequirement(IRequirement requirement) {
		if (requirements == null)
			requirements = new ArrayList<IRequirement>();
		requirements.add(requirement);
	}

	@Override
	public IRequirement[] getMetaRequiredCapabilities(InstallableUnitDescription iu) {
		return metaRequirements == null ? noRequirements : metaRequirements.toArray(new IRequirement[metaRequirements.size()]);
	}

	@Override
	public IProvidedCapability[] getProvidedCapabilities(InstallableUnitDescription iu) {
		return capabilities == null ? noCapabilities : capabilities.toArray(new IProvidedCapability[capabilities.size()]);
	}

	@Override
	public IRequirement[] getRequiredCapabilities(InstallableUnitDescription iu) {
		return requirements == null ? noRequirements : requirements.toArray(new IRequirement[requirements.size()]);
	}

	@Override
	public boolean isApplicable(String configSpec, boolean includeDefault, String candidateId, Version candidateVersion) {
		return id.equals(candidateId) && (version == null || version.equals(candidateVersion));
	}

	boolean isEmpty() {
		return metaRequirements == null && capabilities == null && requirements == null;
	}
}
