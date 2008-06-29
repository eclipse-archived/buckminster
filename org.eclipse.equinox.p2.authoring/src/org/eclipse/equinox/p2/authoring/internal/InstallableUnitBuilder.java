/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.eclipse.equinox.internal.p2.metadata.ArtifactKey;
import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.Copyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;
import org.eclipse.equinox.internal.provisional.p2.metadata.License;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.ProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.RequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.TouchpointData;
import org.eclipse.equinox.internal.provisional.p2.metadata.TouchpointType;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.osgi.framework.Version;

/**
 * A mutable version of InstallableUnit and its subclasses used for authoring.
 * 
 * @author Henrik Lindberg
 * 
 */
@SuppressWarnings("restriction")
public class InstallableUnitBuilder extends ModelRoot
{
	public static class ArtifactKeyBuilder extends ModelPart
	{
		private String m_classifier;

		private String m_id;

		private String m_version;

		public ArtifactKeyBuilder(IArtifactKey key)
		{
			m_classifier = key.getClassifier();
			m_id = key.getId();
			m_version = key.getVersion().toString();

		}

		public ArtifactKeyBuilder(String classifier, String id, String version)
		{
			m_classifier = classifier;
			m_id = id;
			m_version = version;
		}

		public IArtifactKey createArtifactKey()
		{
			return new ArtifactKey(m_classifier, m_id, new Version(m_version));
		}

		public String getClassifier()
		{
			return m_classifier;
		}

		public String getId()
		{
			return m_id;
		}

		public String getVersion()
		{
			return m_version;
		}

		public void setClassifier(String classifier)
		{
			m_classifier = classifier;
			notifyChanged();
		}

		public void setId(String id)
		{
			m_id = id;
			notifyChanged();
		}

		public void setVersion(String version)
		{
			m_version = version;
			notifyChanged();
		}
	}

	/**
	 * Common subclass for license, and copyright
	 * @author Henrik Lindberg
	 *
	 */
	public static class IUInfoBuilder extends ModelPart
	{
		protected String m_body;

		protected String m_url;

		public IUInfoBuilder()
		{
			m_body = ""; //$NON-NLS-1$
			m_url = ""; //$NON-NLS-1$
		}

		public IUInfoBuilder(String url, String body)
		{
			m_url = url;
			m_body = body;
		}

		public String getBody()
		{
			return m_body;
		}

		public String getUrl()
		{
			return m_url;
		}

		public void setBody(String body)
		{
			m_body = body;
			notifyChanged();
		}

		public void setUrl(String url)
		{
			m_url = url;
			notifyChanged();
		}

	}

	public static class CopyrightBuilder extends IUInfoBuilder
	{
		public CopyrightBuilder(Copyright copyright)
		{
			this(copyright.getURL() != null
					? copyright.getURL().toString()
					: "", copyright.getBody()); //$NON-NLS-1$
		}

		public CopyrightBuilder(String url, String body)
		{
			super(url, body);
		}

		public CopyrightBuilder()
		{
			super();
		}

		public Copyright createCopyright()
		{
			return new Copyright(m_url, m_body);
		}

	}

	public static class LicenseBuilder extends IUInfoBuilder
	{

		public LicenseBuilder(License license)
		{
			m_url = license.getURL() != null
					? license.getURL().toString()
					: ""; //$NON-NLS-1$
			m_body = license.getBody();
		}

		public LicenseBuilder()
		{
			super();
		}

		public LicenseBuilder(String url, String body)
		{
			super(url, body);
		}

		public License createLicense()
		{
			return new License(m_url, m_body);
		}
	}

	public static class ProvidedCapabilityBuilder extends ModelPart
	{
		private String m_name;

		private String m_namespace;

		private String m_version;

		public ProvidedCapabilityBuilder(ProvidedCapability capability)
		{
			m_namespace = capability.getNamespace();
			m_name = capability.getName();
			m_version = capability.getVersion().toString();
		}

		public ProvidedCapabilityBuilder(String namespace, String name, String version)
		{
			m_name = name;
			m_namespace = namespace;
			m_version = version;
		}

		public ProvidedCapability createProvidedCapability()
		{
			return MetadataFactory.createProvidedCapability(m_namespace, m_name, new Version(m_version));
		}

		public String getName()
		{
			return m_name;
		}

		public String getNamespace()
		{
			return m_namespace;
		}

		public String getVersion()
		{
			return m_version;
		}

		public void setName(String name)
		{
			m_name = name;
			notifyChanged();
		}

		public void setNamespace(String namespace)
		{
			m_namespace = namespace;
			notifyChanged();
		}

		public void setVersion(String version)
		{
			m_version = version;
			notifyChanged();
		}
	}

	public static class RequiredCapabilityBuilder extends ModelPart
	{
		private String m_capfilter;

		private boolean m_greedy;

		private boolean m_multiple;

		private String m_name;

		private String m_namespace;

		private boolean m_optional;

		private String m_range;

		public RequiredCapabilityBuilder(RequiredCapability capability)
		{
			m_capfilter = capability.getFilter();
			m_name = capability.getName();
			m_namespace = capability.getNamespace();
			m_range = capability.getRange().toString();
			m_greedy = capability.isGreedy();
			m_multiple = capability.isMultiple();
			m_optional = capability.isOptional();
		}

		public RequiredCapabilityBuilder(String filter, String name, String namespace, String range, boolean greedy,
				boolean multiple, boolean optional)
		{
			m_capfilter = filter;
			m_name = name;
			m_namespace = namespace;
			m_range = range;
			m_greedy = greedy;
			m_multiple = multiple;
			m_optional = optional;
		}

		public RequiredCapability createRequiredCapability()
		{
			return MetadataFactory.createRequiredCapability(m_namespace, m_name, new VersionRange(m_range),
					m_capfilter, m_optional, m_multiple, m_greedy);
		}

		public String getCapfilter()
		{
			return m_capfilter;
		}

		public String getName()
		{
			return m_name;
		}

		public String getNamespace()
		{
			return m_namespace;
		}

		public String getRange()
		{
			return m_range;
		}

		public boolean isGreedy()
		{
			return m_greedy;
		}

		public boolean isMultiple()
		{
			return m_multiple;
		}

		public boolean isOptional()
		{
			return m_optional;
		}

		public void setCapfilter(String capfilter)
		{
			m_capfilter = capfilter;
			notifyChanged();
		}

		public void setGreedy(boolean greedy)
		{
			m_greedy = greedy;
			notifyChanged();
		}

		public void setMultiple(boolean multiple)
		{
			m_multiple = multiple;
			notifyChanged();
		}

		public void setName(String name)
		{
			m_name = name;
			notifyChanged();
		}

		public void setNamespace(String namespace)
		{
			m_namespace = namespace;
			notifyChanged();
		}

		public void setOptional(boolean optional)
		{
			m_optional = optional;
			notifyChanged();
		}

		public void setRange(String range)
		{
			m_range = range;
			notifyChanged();
		}
	}
	/**
	 * TouchpointDataBuilder manages actions for a set of p2 engine phases. Note that no attempt
	 * is made to validate if instructons are valid for the touchpoint type, or if actions have
	 * the correct set of parameters etc.
	 * 
	 * TODO: Supports named touchpoint data, but this is not supported in the meta data format, so now names are generated
	 * by the InstallableUnitBuilder.
	 * TODO: Does not handle the phases COLLECT, and CHECKTRUST (don't know if such support is needed).
	 * @author Henrik Lindberg
	 *
	 */
	public static class TouchpointDataBuilder extends ModelPart
	{
		LinkedHashMap<String, TouchpointInstructionBuilder> m_instructions;
		private String m_name; 

		public static final String INSTALL = "install"; //$NON-NLS-1$
		public static final String UNINSTALL = "uninstall"; //$NON-NLS-1$
		public static final String CONFIGURE = "configure"; //$NON-NLS-1$
		public static final String UNCONFIGURE = "unconfigure"; //$NON-NLS-1$
		
		/**
		 * Creates a TouchpointDataBuilder. Adds one TouchpointInstructionBuilder 
		 * per possible instruction
		 * (This way, there is no need to explicitly add an instruction - it is enough to edit
		 * the actions per instruction).
		 * @param touchpointData
		 */
		public TouchpointDataBuilder()
		{
			initializeFromMap(Collections.EMPTY_MAP);
		}
		
		/**
		 * Creates a TouchpointDataBuilder from TouchpointData. Adds one TouchpointInstructionBuilder 
		 * per possible instruction and replaces it with instructions from the TouchpointData.
		 * (This way, there is no need to explicitly add an instruction - it is enough to edit
		 * the actions per instruction).
		 * @param touchpointData
		 */
		public TouchpointDataBuilder(TouchpointData touchpointData)
		{	
			initializeFromMap(touchpointData.getInstructions());
		}

		@SuppressWarnings("unchecked")
		private void initializeFromMap(Map m)
		{
			m_instructions = new LinkedHashMap<String, TouchpointInstructionBuilder>(m.size());
			
			// initialize with default instructions
			m_instructions.put(INSTALL, new TouchpointInstructionBuilder(this, INSTALL));
			m_instructions.put(UNINSTALL, new TouchpointInstructionBuilder(this, UNINSTALL));
			m_instructions.put(CONFIGURE, new TouchpointInstructionBuilder(this, CONFIGURE));
			m_instructions.put(UNCONFIGURE, new TouchpointInstructionBuilder(this, UNCONFIGURE));
			
			// Replace with the instructions set in the TouchpointData
			for(Object e : m.entrySet())
			{
				String phaseId = (String)((Map.Entry)e).getKey();				
				String statements = (String)((Map.Entry)e).getValue();
				TouchpointInstructionBuilder instruction = new TouchpointInstructionBuilder(phaseId, statements);
				m_instructions.put(phaseId, instruction);
				instruction.setParent(this);
			}
			// TODO: should set the name when that is supported in TouchpointData - now the InstallableUnitBuilder
			// sets the name.
			m_name = "";
		}

		/**
		 * Creates the TouchpointData by re-assembling the TouchpointInstructionBuilder and
		 * TouchpointActionBuilder(s) to the required form <"instructionKey", "action(...);action(...);"
		 * @return a new TouchpointData
		 */
		public TouchpointData createTouchpointData()
		{
			Map<String, String> m = new LinkedHashMap<String, String>();
			// Create a new map with all non empty instructions
			for(TouchpointInstructionBuilder instruction : m_instructions.values())
			{
				// Do not output empty instructions
				TouchpointActionBuilder[] actions = instruction.getActions();
				if(actions == null || actions.length < 1)
					continue;
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i < actions.length; i++)
					actions[i].append(builder);
				m.put(instruction.getPhaseId(), builder.toString());
			}
			// create the TouchpointData from the constructed map
			return MetadataFactory.createTouchpointData(m);
		}

		public TouchpointInstructionBuilder getInstruction(String key)
		{
			return m_instructions.get(key);
		}

		public LinkedHashMap<String, TouchpointInstructionBuilder> getInstructions()
		{
			return m_instructions;
		}

		public void putInstruction(String key, TouchpointInstructionBuilder value)
		{
			m_instructions.put(key, value);
			notifyChanged();
		}

		public void removeInstruction(String key)
		{
			m_instructions.remove(key);
			notifyChanged();
		}
		public String getName()
		{
			return m_name;
		}

		public void setName(String name)
		{
			m_name = name;
			notifyChanged();
		}

	}
	/**
	 * The TouchpointInstructionBuilder manages one instruction (i.e. actions to execute
	 * in a p2 engine phase like INSTALL, UNINSTALL,...). A TouchpointDataBuilder has one instance
	 * of TouchpointInstructionBuilder per engine phase.
	 * @author Henrik Lindberg
	 *
	 */
	public static class TouchpointInstructionBuilder extends ModelPart
	{
		private String m_phaseId;
		private TouchpointActionBuilder[] m_actions;
		
		/**
		 * Create an instruction with no actions.
		 * @param phaseId
		 */
		public TouchpointInstructionBuilder(ModelPart parent, String phaseId)
		{
			m_phaseId = phaseId;
			m_actions = new TouchpointActionBuilder[0];
			setParent(parent);
		}
		/**
		 * Create an instruction with actions described in the 'statements' parameter.
		 * @param phaseId - the instruction/phase id (INSTALL, UNINSTALL, ... etc)
		 * @param statements - a sequence of action statements "action(...);action(...);"
		 */
		public TouchpointInstructionBuilder(String phaseId, String statements)
		{
			m_phaseId = phaseId;
			// parse the statements
			List<TouchpointActionBuilder> actions = new ArrayList<TouchpointActionBuilder>();
			StringTokenizer tokenizer = new StringTokenizer(statements, ";");
			while(tokenizer.hasMoreTokens())
				actions.add(TouchpointActionBuilder.parse(this, tokenizer.nextToken()));
			// keep the list (they are all now parented by this TouchpointInstructionBuilder)
			m_actions = actions.toArray(new TouchpointActionBuilder[actions.size()]);
		}
		public String getPhaseId()
		{
			return m_phaseId;
		}
		public TouchpointActionBuilder[] getActions()
		{
			return m_actions;
		}
		/**
		 * Add action last.
		 * 
		 * @param action
		 * @return index where this artifact key was added
		 */
		public int addAction(TouchpointActionBuilder action)
		{
			return addAction(action, -1);
		}

		/**
		 * Adds an action at a given index. If index is outside of range (or more specifically is -1), the new action
		 * is added last.
		 * 
		 * @param artifact
		 * @param index
		 * @return the index where the artifact key was added.
		 */
		public int addAction(TouchpointActionBuilder action, int index)
		{
			int[] ix = { index };
			m_actions = (TouchpointActionBuilder[])addModelPart(m_actions, action, ix);
			notifyChanged();
			return ix[0];
		}

		/**
		 * Removes the action from the set of actions
		 * 
		 * @param action
		 * @return the index where the action was found, -1 if not found
		 */
		public int removeAction(TouchpointActionBuilder action)
		{
			int[] index = { 0 };
			m_actions = (TouchpointActionBuilder[])removeModelPart(m_actions, action, index);
			if(index[0] == -1)
				return -1;
			notifyChanged();
			return index[0];
		}

		/**
		 * Moves the action up (+1) or down(-1) in the array of actions
		 * 
		 * @param action
		 * @param delta
		 *            - +1 or -1 (throws IllegalArgumentException of not +1 or -1)
		 * @return -1 if move was not made, else the position before the move is returned
		 */
		public int moveAction(TouchpointActionBuilder action, int delta)
		{
			int index = moveModelPart(m_actions, action, delta);
			notifyChanged();
			return index;
		}
	}
	/**
	 * A TouchpointActionBuilder manages the parameters and parameter values for one action
	 * being part of a TouchpointInstructionBuilder.
	 * TODO: it suffers from the same problem as the rest of p2 - A "," is not allowed in a parameter value
	 * @author Henrik Lindberg
	 */
	public static class TouchpointActionBuilder extends ModelPart
	{
		private String m_actionKey;
		private Parameter m_parameters[];
//		private Map<String, Parameter>m_actionParams;
		/**
		 * Creates an TouchpointActionBuilder from a statement on the form:
		 * "action(param:value,param:value,param:value)"
		 * and sets the parent of the created object to 'parent'
		 * @param parent - the parent of the newly created object
		 * @param statement - the statement to parse
		 * @return a newly created TouchpointActionBuilder
		 */
		public static TouchpointActionBuilder parse(ModelPart parent, String statement)
		{
			int openBracket = statement.indexOf('(');
			int closeBracket = statement.lastIndexOf(')');
			String actionName = statement.substring(0, openBracket).trim();
			String nameValuePairs = statement.substring(openBracket + 1, closeBracket);
			// TODO: Fix comma problem
			StringTokenizer tokenizer = new StringTokenizer(nameValuePairs, ","); //$NON-NLS-1$
			List<Parameter> parameters = new ArrayList<Parameter>(5);
			while (tokenizer.hasMoreTokens()) {
				String nameValuePair = tokenizer.nextToken();
				int colonIndex = nameValuePair.indexOf(":"); //$NON-NLS-1$
				String name = nameValuePair.substring(0, colonIndex).trim();
				String value = nameValuePair.substring(colonIndex + 1).trim();	
				parameters.add(new Parameter(name, value));
			}
			TouchpointActionBuilder a = new TouchpointActionBuilder(actionName, parameters);
			a.setParent(parent);
			return a;
		}
		
		public TouchpointActionBuilder(String actionKey)
		{
			m_actionKey = actionKey;
			m_parameters = null;
		}
		public TouchpointActionBuilder(String actionKey, List<Parameter> parameters)
		{
			m_actionKey = actionKey;
			m_parameters = parameters.toArray(new Parameter[parameters.size()]);
			for(int i = 0; i < m_parameters.length; i++)
				m_parameters[i].setParent(this);
		}
		public String getActionKey()
		{
			return m_actionKey;
		}
		public void setActionKey(String actionKey)
		{
			m_actionKey = actionKey;
			notifyChanged();
		}
		public void setParameters(List<Parameter> parameters)
		{
			for(int i = 0; i < m_parameters.length; i++)
				m_parameters[i].setParent(null);
			m_parameters = parameters.toArray(new Parameter[parameters.size()]);
			for(int i = 0; i < m_parameters.length; i++)
				m_parameters[i].setParent(this);
			notifyChanged();
		}
		public void append(StringBuilder builder)
		{
			builder.append(m_actionKey);
			builder.append('(');
			boolean first = true;
			for(int i = 0; i < m_parameters.length; i++)
			{					
				Parameter p = m_parameters[i];
				if(p.getValue() == null)
					continue; // do not output parameters that have null value
				if(!first)
					builder.append(", ");//$NON-NLS-1$
				first = false;
				builder.append(p.getName());
				builder.append(':');
				builder.append(p.getValue());
			}
			builder.append(");"); //$NON-NLS-1$
		}
		/**
		 * Returns the value of a parameter. Throws IllegalArgumentException if the parameter is not
		 * specified for the action.
		 * @param parameterName
		 * @return a string value (can be ""), or is null if parameter is not set
		 */
		public String getParameter(String parameterName)
		{
			if(m_parameters == null)
				throw new IllegalArgumentException("No such parameter: " + parameterName);
			for(int i = 0; i < m_parameters.length;i++)
				if(m_parameters[i].getName().equals(parameterName))
					return m_parameters[i].getValue();
			
			throw new IllegalArgumentException("No such parameter: " + parameterName);
		}
		/**
		 * Sets the value of a parameter. Throws IllegalArgumentException if the parameter is not
		 * specified for the action.
		 * @param parameterName
		 * @param parameterValue
		 */
		public void setParameter(String parameterName, String parameterValue)
		{

			if(m_parameters == null)
				throw new IllegalArgumentException("Action has no parameters - can not set parameter: " + parameterName);
			for(int i = 0; i < m_parameters.length;i++)
				if(m_parameters[i].getName().equals(parameterName))
				{
					m_parameters[i].setValue(parameterValue);
					return;
				}
			throw new IllegalArgumentException("No such parameter: " + parameterName);
		}
		public Parameter[] getParameters()
		{
			return m_parameters;
		}
	}
	/**
	 * Value holder class used to differentiate between a null value and non existing parameter.
	 * @author Henrik Lindberg
	 *
	 */
	public static class Parameter extends ModelPart
	{
		private String m_value;
		private String m_name;

		public String getName()
		{
			return m_name;
		}
		public void setName(String name)
		{
			m_name = name;
			notifyChanged();
		}
		public Parameter(String name, String value)
		{
			m_name = name;
			m_value = value;
		}

		public String getValue()
		{
			return m_value;
		}

		public void setValue(String value)
		{
			m_value = value;
			notifyChanged();
		}
	}
	public static class TouchpointTypeBuilder extends ModelPart
	{
		private String m_typeid;

		private String m_version;

		public TouchpointTypeBuilder(TouchpointType type)
		{
			m_typeid = type.getId();
			m_version = type.getVersion().toString();
		}
		public TouchpointTypeBuilder(String typeId, String version)
		{
			m_typeid = typeId;
			m_version = version;
		}
		public TouchpointType createTouchpointType()
		{
			return MetadataFactory.createTouchpointType(m_typeid, new Version(m_version));
		}

		public String getTypeid()
		{
			return m_typeid;
		}

		public String getVersion()
		{
			return m_version;
		}

		public void setTypeid(String typeid)
		{
			m_typeid = typeid;
			notifyChanged();
		}

		public void setVersion(String version)
		{
			m_version = version;
			notifyChanged();
		}
	}

	public static class UpdateDescriptorBuilder extends ModelPart
	{
		private String m_description;

		private String m_range;

		private int m_severity;

		private String m_updateid;

		public UpdateDescriptorBuilder(IUpdateDescriptor updateDescriptor)
		{
			if(updateDescriptor == null)
				return;

			m_description = updateDescriptor.getDescription();
			m_updateid = updateDescriptor.getId();
			m_range = updateDescriptor.getRange().toString();
			m_severity = updateDescriptor.getSeverity();
		}

		public IUpdateDescriptor createUpdateDescriptor()
		{
			return MetadataFactory.createUpdateDescriptor(m_updateid, new VersionRange(m_range), m_severity,
					m_description);
		}

		public String getDescription()
		{
			return m_description;
		}

		public String getRange()
		{
			return m_range;
		}

		public int getSeverity()
		{
			return m_severity;
		}

		public String getUpdateid()
		{
			return m_updateid;
		}

		public boolean isEmpty()
		{
			if((m_description == null || m_description.length() < 1) && (m_range == null || m_range.length() < 1)
					&& (m_updateid == null || m_updateid.length() < 1))
				return true;
			return false;
		}

		public void setDescription(String description)
		{
			m_description = description;
			notifyChanged();
		}

		public void setRange(String range)
		{
			m_range = range;
			notifyChanged();
		}

		public void setSeverity(int severity)
		{
			m_severity = severity;
			notifyChanged();
		}

		public void setUpdateid(String updateid)
		{
			m_updateid = updateid;
			notifyChanged();
		}
	}

	private ArtifactKeyBuilder[] m_artifacts;

	private CopyrightBuilder m_copyright;

	private String m_filter;

	private String m_id;

	private LicenseBuilder m_license;

	private LinkedHashMap<String, String> m_properties;

	private ProvidedCapabilityBuilder[] m_providedCapabilities;

	private RequiredCapabilityBuilder[] m_requiredCapabilities;

	private boolean m_singleton;

	private TouchpointDataBuilder[] m_touchpointData;

	private TouchpointTypeBuilder m_touchpointType;

	private UpdateDescriptorBuilder m_updateDescriptor;

	private String m_version;

	@SuppressWarnings("unchecked")
	public InstallableUnitBuilder(IInstallableUnit unit)
	{
		// Artifact keys
		IArtifactKey[] artifactKeys = unit.getArtifacts();
		m_artifacts = new ArtifactKeyBuilder[artifactKeys.length];
		for(int i = 0; i < artifactKeys.length; i++)
		{
			m_artifacts[i] = new ArtifactKeyBuilder(artifactKeys[i]);
			m_artifacts[i].setParent(this);
		}
		m_copyright = new CopyrightBuilder(unit.getCopyright());
		m_copyright.setParent(this);

		m_filter = unit.getFilter();

		// Editing of bound fragments not supported - this is only for resolved IUs
		// unit.getFragments();

		m_id = unit.getId();

		m_license = new LicenseBuilder(unit.getLicense());
		m_license.setParent(this);

		m_properties = new LinkedHashMap();
		m_properties.putAll(unit.getProperties());

		ProvidedCapability[] providedCapabilities = unit.getProvidedCapabilities();
		m_providedCapabilities = new ProvidedCapabilityBuilder[providedCapabilities.length];
		for(int i = 0; i < providedCapabilities.length; i++)
		{
			m_providedCapabilities[i] = new ProvidedCapabilityBuilder(providedCapabilities[i]);
			m_providedCapabilities[i].setParent(this);
		}
		RequiredCapability[] requiredCapabilities = unit.getRequiredCapabilities();
		m_requiredCapabilities = new RequiredCapabilityBuilder[requiredCapabilities.length];
		for(int i = 0; i < requiredCapabilities.length; i++)
		{
			m_requiredCapabilities[i] = new RequiredCapabilityBuilder(requiredCapabilities[i]);
			m_requiredCapabilities[i].setParent(this);
		}
		TouchpointData[] touchpointData = unit.getTouchpointData();
		m_touchpointData = new TouchpointDataBuilder[touchpointData.length];
		for(int i = 0; i < touchpointData.length; i++)
		{
			m_touchpointData[i] = new TouchpointDataBuilder(touchpointData[i]);
			// TODO: Replace this when IU meta data contains a name/label - for now generate a name
			m_touchpointData[i].setName("Instruction block "+Integer.toString(i+1));
			m_touchpointData[i].setParent(this);
		}
		m_touchpointType = new TouchpointTypeBuilder(unit.getTouchpointType());
		m_touchpointType.setParent(this);

		m_updateDescriptor = new UpdateDescriptorBuilder(unit.getUpdateDescriptor());
		m_updateDescriptor.setParent(this);

		m_version = unit.getVersion().toString();
		m_singleton = unit.isSingleton();

	}

	public InstallableUnit createInstallableUnit()
	{
		InstallableUnit iud = new InstallableUnit();

		// Artifact keys
		IArtifactKey[] keys = new IArtifactKey[m_artifacts.length];
		for(int i = 0; i < m_artifacts.length; i++)
			keys[i] = m_artifacts[i].createArtifactKey();

		iud.setArtifacts(keys);

		iud.setCopyright(m_copyright.createCopyright());
		iud.setFilter(m_filter);
		iud.setId(m_id);
		iud.setLicense(m_license.createLicense());
		iud.setSingleton(m_singleton);

		// properties can not be set in bulk
		for(Entry<String, String> entry : m_properties.entrySet())
			iud.setProperty(entry.getKey(), entry.getValue());

		// provided capabilities
		ProvidedCapability[] providedCapabilities = new ProvidedCapability[m_providedCapabilities.length];
		for(int i = 0; i < m_providedCapabilities.length; i++)
			providedCapabilities[i] = m_providedCapabilities[i].createProvidedCapability();
		iud.setCapabilities(providedCapabilities);

		// required capabilities
		RequiredCapability[] requiredCapabilities = new RequiredCapability[m_requiredCapabilities.length];
		for(int i = 0; i < m_requiredCapabilities.length; i++)
			requiredCapabilities[i] = m_requiredCapabilities[i].createRequiredCapability();
		iud.setRequiredCapabilities(requiredCapabilities);

		for(int i = 0; i < m_touchpointData.length; i++)
			iud.addTouchpointData(m_touchpointData[i].createTouchpointData());

		iud.setUpdateDescriptor(m_updateDescriptor.createUpdateDescriptor());
		iud.setTouchpointType(m_touchpointType.createTouchpointType());

		iud.setVersion(new Version(m_version));
		return iud;
	}

	public ArtifactKeyBuilder[] getArtifacts()
	{
		return m_artifacts;
	}

	/**
	 * Add artifact key last.
	 * 
	 * @param artifact
	 * @return index where this artifact key was added
	 */
	public int addArtifactKey(ArtifactKeyBuilder artifact)
	{
		return addArtifactKey(artifact, -1);
	}

	/**
	 * Adds a artifact key at a given index. If index is outside of range (or more specifically is -1), the new artifact
	 * key is added last.
	 * 
	 * @param artifact
	 * @param index
	 * @return the index where the artifact key was added.
	 */
	public int addArtifactKey(ArtifactKeyBuilder artifact, int index)
	{
		int[] ix = { index };
		m_artifacts = (ArtifactKeyBuilder[])addModelPart(m_artifacts, artifact, ix);
		notifyChanged();
		return ix[0];
	}

	/**
	 * Removes the artifact key from the set of artifact keys
	 * 
	 * @param artifact
	 * @return the index where the artifact key was found, -1 if not found
	 */
	public int removeArtifactKey(ArtifactKeyBuilder artifact)
	{
		int[] index = { 0 };
		m_artifacts = (ArtifactKeyBuilder[])removeModelPart(m_artifacts, artifact, index);
		if(index[0] == -1)
			return -1;
		notifyChanged();
		return index[0];
	}

	/**
	 * Moves the artifact key up (+1) or down(-1) in the array of artifact keys
	 * 
	 * @param provided
	 * @param delta
	 *            - +1 or -1 (throws IllegalArgumentException of not +1 or -1)
	 * @return -1 if move was not made, else the position before the move is returned
	 */
	public int moveArtifactKey(ArtifactKeyBuilder provided, int delta)
	{
		int index = moveModelPart(m_artifacts, provided, delta);
		notifyChanged();
		return index;
	}



	public CopyrightBuilder getCopyright()
	{
		return m_copyright;
	}

	public String getFilter()
	{
		return m_filter;
	}

	public String getId()
	{
		return m_id;
	}

	public LicenseBuilder getLicense()
	{
		return m_license;
	}

	public LinkedHashMap<String, String> getProperties()
	{
		return m_properties;
	}

	public String getProperty(String key)
	{
		return m_properties.get(key);
	}

	public ProvidedCapabilityBuilder[] getProvidedCapabilities()
	{
		return m_providedCapabilities;
	}

	/**
	 * Add a provided capability last.
	 * 
	 * @param provided
	 * @return index where this capability was added
	 */
	public int addProvidedCapability(ProvidedCapabilityBuilder provided)
	{
		return addProvidedCapability(provided, -1);
	}

	/**
	 * Adds a provided capability at a given index. If index is outside of range (or more specifically is -1), the new
	 * provided capability is added last.
	 * 
	 * @param artifact
	 * @param index
	 * @return the index where the capability was added.
	 */
	public int addProvidedCapability(ProvidedCapabilityBuilder provided, int index)
	{
		int[] ix = { index };
		m_providedCapabilities = (ProvidedCapabilityBuilder[])addModelPart(m_providedCapabilities, provided, ix);
		notifyChanged();
		return ix[0];
	}

	/**
	 * Removes the provided capability from the set of provided capabilities.
	 * 
	 * @param artifact
	 * @return the index where the provided capability was found, -1 if not found
	 */
	public int removeProvidedCapability(ProvidedCapabilityBuilder provided)
	{
		int[] index = { 0 };
		m_providedCapabilities = (ProvidedCapabilityBuilder[])removeModelPart(m_providedCapabilities, provided, index);
		if(index[0] == -1)
			return -1;
		notifyChanged();
		return index[0];
	}

	/**
	 * Moves the provided capability (+1) or down(-1) in the array of provided capabilities
	 * 
	 * @param provided
	 * @param delta
	 *            - +1 or -1 (throws IllegalArgumentException of not +1 or -1)
	 * @return -1 if move was not made, else the position before the move is returned
	 */
	public int moveProvidedCapability(ProvidedCapabilityBuilder provided, int delta)
	{
		int index = moveModelPart(m_providedCapabilities, provided, delta);
		notifyChanged();
		return index;
	}

	public RequiredCapabilityBuilder[] getRequiredCapabilities()
	{
		return m_requiredCapabilities;
	}

	/**
	 * Adds a required capability last.
	 * 
	 * @param required
	 * @return index where this required capability was added
	 */
	public int addRequiredCapability(RequiredCapabilityBuilder required)
	{
		return addRequiredCapability(required, -1);
	}

	/**
	 * Adds a required capability at a given index. If index is outside of range (or more specifically is -1), the new
	 * required capability is added last.
	 * 
	 * @param artifact
	 * @param index
	 * @return the index where the required capability was added.
	 */
	public int addRequiredCapability(RequiredCapabilityBuilder required, int index)
	{
		int[] ix = { index };
		m_requiredCapabilities = (RequiredCapabilityBuilder[])addModelPart(m_requiredCapabilities, required, ix);
		notifyChanged();
		return ix[0];
	}

	/**
	 * Removes the required capability from the set of required capabilities.
	 * 
	 * @param artifact
	 * @return the index where the required capability was found, -1 if not found
	 */
	public int removeRequiredCapability(RequiredCapabilityBuilder required)
	{
		int[] index = { 0 };
		m_requiredCapabilities = (RequiredCapabilityBuilder[])removeModelPart(m_requiredCapabilities, required, index);
		if(index[0] == -1)
			return -1;
		notifyChanged();
		return index[0];
	}

	/**
	 * Moves the required capability (+1) or down(-1) in the array of required capabilities
	 * 
	 * @param required
	 * @param delta
	 *            - +1 or -1 (throws IllegalArgumentException of not +1 or -1)
	 * @return -1 if move was not made, else the position before the move is returned
	 */
	public int moveRequiredCapability(RequiredCapabilityBuilder required, int delta)
	{
		int index = moveModelPart(m_requiredCapabilities, required, delta);
		notifyChanged();
		return index;
	}

	public  TouchpointDataBuilder[] getTouchpointData()
	{
		return m_touchpointData;
	}

	/**
	 * Adds a touchpoint data last.
	 * 
	 * @param data the touchpoint to add
	 * @return index where this touchpoint data was added
	 */
	public int addTouchpointData(TouchpointDataBuilder data)
	{
		return addTouchpointData(data, -1);
	}

	/**
	 * Adds a touchpoint data at a given index. If index is outside of range (or more specifically is -1), the new
	 * touchpoint data is added last.
	 * 
	 * @param data
	 * @param index
	 * @return the index where the touchpoint data was added.
	 */
	public int addTouchpointData(TouchpointDataBuilder data, int index)
	{
		int[] ix = { index };
		m_touchpointData = (TouchpointDataBuilder[])addModelPart(m_touchpointData, data, ix);
		notifyChanged();
		return ix[0];
	}

	/**
	 * Removes the touchpoint data from the set of touchpoint data.
	 * 
	 * @param artifact
	 * @return the index where the required capability was found, -1 if not found
	 */
	public int removeTouchpointData(TouchpointDataBuilder data)
	{
		int[] index = { 0 };
		m_touchpointData = (TouchpointDataBuilder[])removeModelPart(m_touchpointData, data, index);
		if(index[0] == -1)
			return -1;
		notifyChanged();
		return index[0];
	}

	/**
	 * Moves the touchpoint data (+1) or down(-1) in the array of touchpoint data.
	 * 
	 * @param data the touchpoint data to move
	 * @param delta
	 *            - +1 or -1 (throws IllegalArgumentException of not +1 or -1)
	 * @return -1 if move was not made, else the position before the move is returned
	 */
	public int moveTouchpointData(TouchpointDataBuilder data, int delta)
	{
		int index = moveModelPart(m_touchpointData, data, delta);
		notifyChanged();
		return index;
	}
	
	public TouchpointTypeBuilder getTouchpointType()
	{
		return m_touchpointType;
	}

	public UpdateDescriptorBuilder getUpdateDescriptor()
	{
		return m_updateDescriptor;
	}

	public String getVersion()
	{
		return m_version;
	}

	public boolean isSingleton()
	{
		return m_singleton;
	}

	public void removeProperty(String key)
	{
		m_properties.remove(key);
		notifyChanged();
	}

	public void setArtifacts(ArtifactKeyBuilder[] artifacts)
	{
		if(m_artifacts != null)
			for(int i = 0; i < m_artifacts.length; i++)
				m_artifacts[i].setParent(null);
		m_artifacts = artifacts;
		for(int i = 0; i < m_artifacts.length; i++)
			m_artifacts[i].setParent(this);
		notifyChanged();
	}

	public void setCopyright(CopyrightBuilder copyright)
	{
		if(m_copyright != null)
			m_copyright.setParent(null);
		m_copyright = copyright;
		m_copyright.setParent(this);
		notifyChanged();
	}

	public void setFilter(String filter)
	{
		m_filter = filter;
		notifyChanged();
	}

	public void setId(String id)
	{
		m_id = id;
		notifyChanged();
	}

	public void setLicense(LicenseBuilder license)
	{
		if(m_license != null)
			m_license.setParent(null);
		m_license = license;
		m_license.setParent(this);
		notifyChanged();
	}

	public void setProperties(LinkedHashMap<String, String> properties)
	{
		m_properties = properties;
		notifyChanged();
	}

	public void setProperty(String key, String value)
	{
		m_properties.put(key, value);
		notifyChanged();
	}

	public void setProvidedCapabilities(ProvidedCapabilityBuilder[] providedCapabilities)
	{
		if(m_providedCapabilities != null)
			for(int i = 0; i < m_providedCapabilities.length; i++)
				m_providedCapabilities[i].setParent(null);

		m_providedCapabilities = providedCapabilities;
		for(int i = 0; i < m_providedCapabilities.length; i++)
			m_providedCapabilities[i].setParent(this);
		notifyChanged();
	}

	public void setRequiredCapabilities(RequiredCapabilityBuilder[] requiredCapabilities)
	{
		if(m_requiredCapabilities != null)
			for(int i = 0; i < m_requiredCapabilities.length; i++)
				m_requiredCapabilities[i].setParent(null);

		m_requiredCapabilities = requiredCapabilities;
		for(int i = 0; i < m_requiredCapabilities.length; i++)
			m_requiredCapabilities[i].setParent(this);
		notifyChanged();
	}

	public void setSingleton(boolean singleton)
	{
		m_singleton = singleton;
		notifyChanged();
	}

	public void setTouchpointData(TouchpointDataBuilder[] touchpointData)
	{
		// remove old parenthood
		if(m_touchpointData != null)
			for(int i = 0; i < m_touchpointData.length; i++)
				m_touchpointData[i].setParent(null);
		// set new and set parenthood
		m_touchpointData = touchpointData;
		for(int i = 0; i < touchpointData.length; i++)
			touchpointData[i].setParent(this);
		notifyChanged();
	}

	/**
	 * Sets the touchpoint type, and the passed touchpointType's parent is set to this installable unit builder. Any
	 * previously set touchpoint type's parent is set to null (to avoid change events from this instance to propagate
	 * into the model).
	 * 
	 * @param touchpointType
	 */
	public void setTouchpointType(TouchpointTypeBuilder touchpointType)
	{
		if(m_touchpointType != null)
			m_touchpointType.setParent(null);
		m_touchpointType = touchpointType;
		if(m_touchpointType != null)
			m_touchpointType.setParent(this);
		notifyChanged();
	}

	/**
	 * Sets the update descriptor, and the passed update descriptor's parent is set to this installable unit builder.
	 * Any previously set update descriptor's parent is set to null (to avoid change events from this instance to
	 * propagate into the model).
	 * 
	 * @param updateDescriptor
	 */
	public void setUpdateDescriptor(UpdateDescriptorBuilder updateDescriptor)
	{
		if(m_updateDescriptor != null)
			m_updateDescriptor.setParent(null);
		m_updateDescriptor = updateDescriptor;
		m_updateDescriptor.setParent(this);
		notifyChanged();
	}

	public void setVersion(String version)
	{
		m_version = version;
		notifyChanged();
	}
}
