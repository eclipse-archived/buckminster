/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.parser.ICSpecBuilderSupport;
import org.eclipse.buckminster.core.cspecext.builder.AlterCSpecBuilder;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;

/**
 * @author Thomas Hallgren
 * 
 */
abstract class AlterHandler extends ExtensionAwareHandler implements ChildPoppedListener, ICSpecBuilderSupport
{
	AlterHandler(AbstractHandler parent)
	{
		super(parent);
	}

	AlterCSpecBuilder getAlterCSpecBuilder()
	{
		return ((AlterHandler)this.getParentHandler()).getAlterCSpecBuilder();
	}

	public CSpecBuilder getCSpecBuilder()
	{
		return ((ICSpecBuilderSupport)this.getParentHandler()).getCSpecBuilder();
	}

	String getCSpecExtensionName()
	{
		return ((AlterHandler)this.getParentHandler()).getCSpecExtensionName();
	}
}
