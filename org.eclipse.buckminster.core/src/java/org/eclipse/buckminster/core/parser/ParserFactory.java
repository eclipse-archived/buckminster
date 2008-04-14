/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.parser;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.parser.CSpecParser;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.buckminster.core.cspecext.parser.AlterCSpecParser;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.metadata.parser.BillOfMaterialsParser;
import org.eclipse.buckminster.core.metadata.parser.DepNodeParser;
import org.eclipse.buckminster.core.metadata.parser.MaterializationParser;
import org.eclipse.buckminster.core.metadata.parser.ResolutionParser;
import org.eclipse.buckminster.core.metadata.parser.WorkspaceBindingParser;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.mspec.parser.MaterializationSpecParser;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.query.parser.ComponentQueryParser;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.rmap.parser.ProviderParser;
import org.eclipse.buckminster.core.rmap.parser.ResourceMapParser;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.parser.OPMLParser;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ParserFactory implements IParserFactory
{
	public static class ParserExtension
	{
		private final String m_namespace;
		private final URL m_resource;
		private final Map<String,Class<? extends ChildHandler>> m_handlers = new HashMap<String,Class<? extends ChildHandler>>();

		public ParserExtension(String namespace, URL resource)
		{
			m_namespace = namespace;
			m_resource = resource;
		}

		public final ChildHandler getHandler(AbstractHandler parent, String xsiType) throws CoreException
		{
			Class<? extends ChildHandler> handlerClass = m_handlers.get(xsiType);
			try
			{
				Constructor<? extends ChildHandler> ctor = handlerClass.getConstructor(new Class[] { AbstractHandler.class });
				return ctor.newInstance(new Object[] { parent });
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}

		public final String getNamespace()
		{
			return m_namespace;
		}

		public final URL getResource()
		{
			return m_resource;
		}

		void addHandler(String xsiType, Class<? extends ChildHandler> clazz)
		{
			m_handlers.put(xsiType, clazz);
		}
	}

	public static final String PARSER_EXTENSIONS_POINT = CorePlugin.CORE_NAMESPACE + ".parserExtensions";

	private static final ParserFactory s_instance = new ParserFactory();
	
	public static IParserFactory getDefault()
	{
		return s_instance;
	}

	private Map<String, List<ParserExtension>> m_parserExtensions;

	public IParser<CSpecExtension> getAlterCSpecParser(boolean validating)
	throws CoreException
	{
		return new AlterCSpecParser(getParserExtensions(CSpec.TAG, CSpecExtension.TAG), validating);
	}

	public IParser<BillOfMaterials> getBillOfMaterialsParser(boolean validating)
	throws CoreException
	{
		return new BillOfMaterialsParser(getParserExtensions(BillOfMaterials.TAG, ComponentQuery.TAG, Provider.TAG, CSpec.TAG, Resolution.TAG, DepNode.TAG), validating);
	}

	public IParser<ComponentQuery> getComponentQueryParser(boolean validating) throws CoreException
	{
		return new ComponentQueryParser(getParserExtensions(ComponentQuery.TAG), validating);
	}

	public IParser<CSpec> getCSpecParser(boolean validating)
	throws CoreException
	{
		return new CSpecParser(getParserExtensions(CSpec.TAG), validating);
	}

	public IParser<DepNode> getDepNodeParser()
	throws CoreException
	{
		return new DepNodeParser(getParserExtensions(Resolution.TAG, DepNode.TAG));
	}

	public IParser<Materialization> getMaterializationParser()
	throws CoreException
	{
		return new MaterializationParser(getParserExtensions(Materialization.TAG));
	}

	public IParser<MaterializationSpec> getMaterializationSpecParser(boolean validating)
	throws CoreException
	{
		return new MaterializationSpecParser(getParserExtensions(MaterializationSpec.TAG), validating);
	}

	public IParser<Provider> getProviderParser(boolean validating) throws CoreException
	{
		return new ProviderParser(getParserExtensions(Provider.TAG), validating);
	}

	public IParser<Resolution> getResolutionParser()
	throws CoreException
	{
		return new ResolutionParser(getParserExtensions(Resolution.TAG));
	}

	public IParser<ResourceMap> getResourceMapParser(boolean validating)
	throws CoreException
	{
		return new ResourceMapParser(getParserExtensions(ResourceMap.TAG, Provider.TAG), validating);
	}

	public IParser<WorkspaceBinding> getWorkspaceBindingParser(boolean validating)
	throws CoreException
	{
		return new WorkspaceBindingParser(getParserExtensions(Provider.TAG, CSpec.TAG, Resolution.TAG), validating);
	}

	private synchronized List<ParserExtension> getParserExtensions(String ...parserIds)
	{
		if(m_parserExtensions == null)
		{
			try
			{
				m_parserExtensions = loadParserExtensions();
			}
			catch(CoreException e)
			{
				CorePlugin.getLogger().warning(e, "Unable to load parser extensions");
				m_parserExtensions = Collections.emptyMap();
			}
		}

		List<ParserExtension> result = null;
		boolean mutable = false;
		for(String parserId : parserIds)
		{
			List<ParserExtension> pel = m_parserExtensions.get(parserId);
			if(pel == null)
				continue;

			if(result == null)
			{
				result = pel;
				continue;
			}

			if(!mutable)
			{
				ArrayList<ParserExtension> mutableList = new ArrayList<ParserExtension>();
				mutableList.addAll(result);
				result = mutableList;
				mutable = true;
			}
			result.addAll(pel);
		}
		return (result == null || result.size() == 0) ? Collections.<ParserExtension>emptyList() : result;
	}

	private Map<String, List<ParserExtension>> loadParserExtensions() throws CoreException
	{
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		HashMap<String,List<ParserExtension>> peMap = new HashMap<String,List<ParserExtension>>();
		for(IConfigurationElement namespace : exReg.getConfigurationElementsFor(PARSER_EXTENSIONS_POINT))
		{
			Bundle bundle = Platform.getBundle(namespace.getNamespaceIdentifier());
			URL resource = bundle.getResource(namespace.getAttribute("resource"));
			ParserExtension pe = new ParserExtension(namespace.getAttribute("namespace"), resource);
			for(IConfigurationElement handler : namespace.getChildren("handler"))
			{
				try
				{
					pe.addHandler(
						handler.getAttribute("type"),
						((Class<?>)bundle.loadClass(handler.getAttribute("class"))).asSubclass(ChildHandler.class));
				}
				catch(ClassNotFoundException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}

			for(String parserId : namespace.getAttribute("parserIds").split(","))
			{
				List<ParserExtension> peList = peMap.get(parserId);
				if(peList == null)
				{
					peList = new ArrayList<ParserExtension>();
					peMap.put(parserId, peList);
				}
				peList.add(pe);
			}
		}
		return peMap;
	}

	static class OPMLParserExt extends OPMLParser implements IParser<OPML>
	{
		OPMLParserExt(boolean validating) throws SAXException
		{
			super(validating);
		}

		public OPML parse(String systemId, InputStream input) throws CoreException
		{
			IFile[] files = AbstractParser.clearMarkers(systemId);
			try
			{
				return parseInput(systemId, input);
			}
			catch(SAXParseException e)
			{
				AbstractParser.setMarkers(files, e);
				throw BuckminsterException.wrap(e);
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				getXMLReader().setContentHandler(this);
			}
		}
	}

	public IParser<OPML> getOPMLParser(boolean validating) throws CoreException
	{
		try
		{
			return new OPMLParserExt(validating);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}

