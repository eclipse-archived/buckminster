package org.eclipse.buckminster.ui.editor.rmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager
{
	private final Map<RGB,Color> m_colorTable = new HashMap<RGB,Color>(10);

	public void dispose()
	{
		Iterator<Color> e = m_colorTable.values().iterator();
		while(e.hasNext())
			e.next().dispose();
	}

	public Color getColor(RGB rgb)
	{
		Color color = m_colorTable.get(rgb);
		if(color == null)
		{
			color = new Color(Display.getCurrent(), rgb);
			m_colorTable.put(rgb, color);
		}
		return color;
	}
}
