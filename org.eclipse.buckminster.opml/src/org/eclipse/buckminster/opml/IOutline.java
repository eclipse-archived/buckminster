package org.eclipse.buckminster.opml;

import java.net.URI;
import java.util.Date;

public interface IOutline extends IBody
{

	String getCategory();

	Date getCreated();

	String getDescription();

	URI getHtmlUrl();

	String getLanguage();

	String getText();

	String getTitle();

	OutlineType getType();

	String getTypeString();

	URI getUrl();

	String getVersion();

	URI getXmlUrl();

	boolean isBreakpoint();

	boolean isComment();

}
