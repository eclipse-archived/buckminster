package org.eclipse.buckminster.opml.builder;

import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IHead;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class OPMLBuilder implements IOPML
{
	private BodyBuilder m_body = new BodyBuilder();

	private HeadBuilder m_head = new HeadBuilder();

	private String m_version;

	public void clear()
	{
		m_body.clear();
		m_head.clear();
		m_version = null;
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(adapter.isInstance(this))
			return this;
		if(adapter.isAssignableFrom(OPML.class))
			return new OPML(this);
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public IBody getBody()
	{
		return getBodyBuilder();
	}

	public BodyBuilder getBodyBuilder()
	{
		return m_body;
	}

	public IHead getHead()
	{
		return getHeadBuilder();
	}

	public HeadBuilder getHeadBuilder()
	{
		return m_head;
	}

	public String getVersion()
	{
		return m_version;
	}

	public void initFrom(IOPML opml)
	{
		m_body.initFrom(opml.getBody());
		m_head.initFrom(opml.getHead());
		m_version = opml.getVersion();
	}

	public void setBody(BodyBuilder body)
	{
		m_body = body;
	}

	public void setHead(HeadBuilder head)
	{
		m_head = head;
	}

	public void setVersion(String version)
	{
		m_version = version;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		OPML opml = new OPML(this);
		opml.toSax(receiver);
	}
}
