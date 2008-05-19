package org.eclipse.buckminster.opml;

import java.net.URI;
import java.util.Date;

public interface IHead
{
	Date getDateCreated();

	Date getDateModified();

	URI getDocs();

	int[] getExpansionState();

	String getOwnerEmail();

	URI getOwnerId();

	String getOwnerName();

	String getTitle();

	int getVertScrollState();

	int getWindowBottom();

	int getWindowLeft();

	int getWindowRight();

	int getWindowTop();

}
