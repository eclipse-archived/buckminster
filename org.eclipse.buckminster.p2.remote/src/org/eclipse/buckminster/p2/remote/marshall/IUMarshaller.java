package org.eclipse.buckminster.p2.remote.marshall;

import java.net.URL;

import org.eclipse.equinox.internal.p2.metadata.ArtifactKey;
import org.eclipse.equinox.internal.p2.metadata.UpdateDescriptor;
import org.eclipse.equinox.internal.provisional.p2.metadata.Copyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;
import org.eclipse.equinox.internal.provisional.p2.metadata.License;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.ProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.RequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.TouchpointType;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.AbstractSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.ObjectMatch;
import org.jabsorb.serializer.SerializerState;
import org.jabsorb.serializer.UnmarshallException;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.Version;

public class IUMarshaller extends AbstractSerializer
{
	private static final long serialVersionUID = 7858338218473381962L;

	private static final Class<?>[] jsonClasses = new Class[] { JSONObject.class };

	private static final Class<?>[] javaClasses = new Class[] { Version.class, VersionRange.class,
		TouchpointType.class, IArtifactKey.class, ArtifactKey.class, ProvidedCapability.class,
		RequiredCapability.class, IUpdateDescriptor.class, UpdateDescriptor.class, Copyright.class,
		License.class };

	private static final String PROP_VERSION = "version";

	private static final String PROP_ID = "id";

	private static final String PROP_STRING = "string";

	private static final String PROP_TYPE = "type";

	private static final String PROP_CLASSIFIER = "classifier";

	private static final String PROP_NAMESPACE = "namespace";

	private static final String PROP_OPTIONAL = "optional";

	private static final String PROP_NAME = "name";

	private static final String PROP_FILTER = "filter";

	private static final String PROP_MULTIPLE = "multiple";

	private static final String PROP_GREEDY = "greedy";

	private static final String PROP_RANGE = "range";

	private static final String PROP_MAXIMUM = "maximum";

	private static final String PROP_INCLUDE_MAXIMUM = "includeMaximum";

	private static final String PROP_INCLUDE_MINIMUM = "minimum";

	private static final String PROP_MINIMUM = "includeMinimum";

	private static final String PROP_DESCRIPTION = "description";

	private static final String PROP_SEVERITY = "severity";

	private static final String PROP_BODY = "body";

	private static final String PROP_URL = "url";

	public Class<?>[] getJSONClasses()
	{
		return jsonClasses;
	}

	public Class<?>[] getSerializableClasses()
	{
		return javaClasses;
	}

	public Object marshall(SerializerState state, Object parent, Object java) throws MarshallException
	{
		JSONException ex = null;
		try
		{
			if(java instanceof IArtifactKey)
				return marshallArtifactKey(state, (IArtifactKey)java);
			if(java instanceof Version)
				return marshallVersion(state, (Version)java);
			if(java instanceof VersionRange)
				return marshallVersionRange(state, (VersionRange)java);
			if(java instanceof ProvidedCapability)
				return marshallProvidedCapability(state, (ProvidedCapability)java);
			if(java instanceof RequiredCapability)
				return marshallRequiredCapability(state, (RequiredCapability)java);
			if(java instanceof TouchpointType)
				return marshallTouchpointType(state, (TouchpointType)java);
			if(java instanceof IUpdateDescriptor)
				return marshallUpdateDescriptor(state, (IUpdateDescriptor)java);
			if(java instanceof Copyright)
				return marshallCopyright(state, (Copyright)java);
			if(java instanceof License)
				return marshallLicense(state, (License)java);
		}
		catch(JSONException e)
		{
			ex = e;
		}
		throw new MarshallException("Unable to marshall instance of " + java.getClass().getName(), ex);
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object json)
	throws UnmarshallException
	{
		state.setSerialized(json, ObjectMatch.OKAY);
		return ObjectMatch.OKAY;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class javaClass, Object jsonObject)
	throws UnmarshallException
	{
		JSONException ex = null;
		if(jsonObject instanceof JSONObject)
		{
			try
			{
				JSONObject json = (JSONObject)jsonObject;
				if(IArtifactKey.class.isAssignableFrom(javaClass))
					return unmarshallArtifactKey(state, json);
				if(Version.class.isAssignableFrom(javaClass))
					return unmarshallVersion(state, json);
				if(VersionRange.class.isAssignableFrom(javaClass))
					return unmarshallVersionRange(state, json);
				if(ProvidedCapability.class.isAssignableFrom(javaClass))
					return unmarshallProvidedCapability(state, json);
				if(RequiredCapability.class.isAssignableFrom(javaClass))
					return unmarshallRequiredCapability(state, json);
				if(TouchpointType.class.isAssignableFrom(javaClass))
					return unmarshallTouchpointType(state, json);
				if(IUpdateDescriptor.class.isAssignableFrom(javaClass))
					return unmarshallUpateDescriptor(state, json);
				if(Copyright.class.isAssignableFrom(javaClass))
					return unmarshallCopyright(state, json);
				if(License.class.isAssignableFrom(javaClass))
					return unmarshallLicense(state, json);
			}
			catch(JSONException e)
			{
				ex = e;
			}
		}
		throw new UnmarshallException("Don't know how to unmarshall instance of " + javaClass.getName(), ex);
	}

	private JSONObject marshallArtifactKey(SerializerState state, IArtifactKey artifactKey)
	throws JSONException,
		MarshallException
	{
		if(artifactKey == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_CLASSIFIER, artifactKey.getClassifier());
		json.put(PROP_ID, artifactKey.getId());
		putMarshalled(state, json, PROP_VERSION, artifactKey, artifactKey.getVersion());
		return json;
	}

	private Object marshallCopyright(SerializerState state, Copyright copyright) throws JSONException
	{
		if(copyright == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_BODY, copyright.getBody());
		URL url = copyright.getURL();
		json.put(PROP_URL, url == null ? null : url.toString());
		return json;
	}

	private Object marshallLicense(SerializerState state, License license) throws JSONException
	{
		if(license == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_BODY, license.getBody());
		URL url = license.getURL();
		json.put(PROP_URL, url == null ? null : url.toString());
		return json;
	}

	private Object marshallProvidedCapability(SerializerState state, ProvidedCapability capability)
	throws JSONException,
		MarshallException
	{
		if(capability == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_NAMESPACE, capability.getNamespace());
		json.put(PROP_NAME, capability.getName());
		putMarshalled(state, json, PROP_VERSION, capability, capability.getVersion());
		return json;
	}

	private JSONObject marshallRequiredCapability(SerializerState state, RequiredCapability capability)
	throws JSONException,
		MarshallException
	{
		if(capability == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_NAMESPACE, capability.getNamespace());
		json.put(PROP_NAME, capability.getName());
		json.put(PROP_FILTER, capability.getFilter());
		json.put(PROP_OPTIONAL, capability.isOptional());
		json.put(PROP_MULTIPLE, capability.isMultiple());
		json.put(PROP_GREEDY, capability.isGreedy());
		putMarshalled(state, json, PROP_RANGE, capability, capability.getRange());
		return json;
	}

	private JSONObject marshallTouchpointType(SerializerState state, TouchpointType touchpointType)
	throws JSONException,
		MarshallException
	{
		if(touchpointType == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_ID, touchpointType.getId());
		putMarshalled(state, json, PROP_VERSION, touchpointType, touchpointType.getVersion());
		return json;
	}

	private JSONObject marshallUpdateDescriptor(SerializerState state, IUpdateDescriptor updateDescriptor)
	throws JSONException,
		MarshallException
	{
		if(updateDescriptor == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_ID, updateDescriptor.getId());
		putMarshalled(state, json, PROP_RANGE, updateDescriptor, updateDescriptor.getRange());
		json.put(PROP_SEVERITY, updateDescriptor.getSeverity());
		json.put(PROP_DESCRIPTION, updateDescriptor.getDescription());
		return json;
	}

	private JSONObject marshallVersion(SerializerState state, Version version)
	throws JSONException,
		MarshallException
	{
		if(version == null)
			return null;

		JSONObject json = new JSONObject();
		json.put(PROP_STRING, version.toString());
		json.put(PROP_TYPE, "OSGi");
		return json;
	}

	private JSONObject marshallVersionRange(SerializerState state, VersionRange versionRange)
	throws JSONException,
		MarshallException
	{
		if(versionRange == null)
			return null;

		JSONObject json = new JSONObject();
		putMarshalled(state, json, PROP_MINIMUM, versionRange, versionRange.getMinimum());
		json.put(PROP_INCLUDE_MINIMUM, versionRange.getIncludeMinimum());
		putMarshalled(state, json, PROP_MAXIMUM, versionRange, versionRange.getMaximum());
		json.put(PROP_INCLUDE_MAXIMUM, versionRange.getIncludeMaximum());
		return json;
	}

	private void putMarshalled(SerializerState state, JSONObject json, String key, Object parent, Object child)
	throws JSONException,
		MarshallException
	{
		Object obj = ser.marshall(state, parent, child, key);

		// Omit the object entirely if it's a circular reference or duplicate.
		// It will be regenerated in the fixups phase
		//
		if(JSONSerializer.CIRC_REF_OR_DUPLICATE != obj)
			json.put(key, obj);
	}

	private IArtifactKey unmarshallArtifactKey(SerializerState state, JSONObject json)
	throws JSONException,
		UnmarshallException
	{
		return new ArtifactKey(json.optString(PROP_CLASSIFIER, null), json.getString(PROP_ID),
			(Version)ser.unmarshall(state, Version.class, json.opt(PROP_VERSION)));
	}

	private Copyright unmarshallCopyright(SerializerState state, JSONObject json)
	{
		return MetadataFactory.createCopyright(json.optString(PROP_URL, null), json.optString(PROP_BODY));
	}

	private License unmarshallLicense(SerializerState state, JSONObject json)
	{
		return MetadataFactory.createLicense(json.optString(PROP_URL, null), json.optString(PROP_BODY));
	}

	private ProvidedCapability unmarshallProvidedCapability(SerializerState state, JSONObject json)
	throws JSONException,
		UnmarshallException
	{
		return MetadataFactory.createProvidedCapability(json.optString(PROP_NAMESPACE, null),
			json.getString(PROP_NAME), (Version)ser.unmarshall(state, Version.class, json.opt(PROP_VERSION)));
	}

	private RequiredCapability unmarshallRequiredCapability(SerializerState state, JSONObject json)
	throws JSONException,
		UnmarshallException
	{
		return MetadataFactory.createRequiredCapability(json.optString(PROP_NAMESPACE, null),
			json.getString(PROP_NAME), (VersionRange)ser.unmarshall(state, VersionRange.class,
				json.opt(PROP_RANGE)), json.optString(PROP_FILTER, null), json.getBoolean(PROP_OPTIONAL),
			json.getBoolean(PROP_MULTIPLE), json.getBoolean(PROP_GREEDY));
	}

	private TouchpointType unmarshallTouchpointType(SerializerState state, JSONObject json)
	throws JSONException,
		UnmarshallException
	{
		return MetadataFactory.createTouchpointType(json.getString(PROP_ID), (Version)ser.unmarshall(state,
			Version.class, json.opt(PROP_VERSION)));
	}

	private IUpdateDescriptor unmarshallUpateDescriptor(SerializerState state, JSONObject json)
	throws JSONException,
		UnmarshallException
	{
		return new UpdateDescriptor(json.getString(PROP_ID), (VersionRange)ser.unmarshall(state,
			VersionRange.class, json.opt(PROP_RANGE)), json.getInt(PROP_SEVERITY), json.optString(
			PROP_DESCRIPTION, null));
	}

	private Version unmarshallVersion(SerializerState state, JSONObject json) throws UnmarshallException
	{
		String vstr = json.optString(PROP_STRING, null);
		return vstr == null ? null : new Version(vstr);
	}

	private VersionRange unmarshallVersionRange(SerializerState state, JSONObject json)
	throws UnmarshallException,
		JSONException
	{
		return new VersionRange((Version)ser.unmarshall(state, Version.class, json.opt(PROP_MINIMUM)),
			json.getBoolean(PROP_INCLUDE_MINIMUM), (Version)ser.unmarshall(state, Version.class,
				json.opt(PROP_MAXIMUM)), json.getBoolean(PROP_INCLUDE_MAXIMUM));
	}
}
