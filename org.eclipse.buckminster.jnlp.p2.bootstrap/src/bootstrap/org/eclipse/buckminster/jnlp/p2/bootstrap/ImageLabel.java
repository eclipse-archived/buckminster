/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.net.URL;

/**
 * @author kaja
 * 
 */
class ImageLabel extends Label
{
	private static final long serialVersionUID = 7551220238421271846L;

	private Image m_image;

	public ImageLabel(String imageName)
	{
		this.m_image = getImage(imageName);
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(m_image, 0, 0, this);
	}

	private Image getImage(String imageName)
	{
		Class<?> myClass = this.getClass();
		String imageResource = "/icons/" + imageName; //$NON-NLS-1$
		URL imageUrl = myClass.getResource(imageResource);
		return Toolkit.getDefaultToolkit().createImage(imageUrl);
	}
}
