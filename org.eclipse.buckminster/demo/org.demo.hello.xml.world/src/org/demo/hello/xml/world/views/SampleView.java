package org.demo.hello.xml.world.views;

import java.io.StringReader;

import org.demo.worlds.WorldMap;
import org.demo.xml.provider.TheReader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.xml.sax.SAXException;


public class SampleView extends ViewPart
{
	private static final String s_xmlText =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<text world=\"earth\">Hello XML</text>";

	private Text m_helloText;

	public void createPartControl(Composite parent)
	{
		m_helloText = new Text(parent, SWT.BORDER);
		m_helloText.setText(this.getTextFromXML());
	}

	public String getTextFromXML()
	{
		try
		{
			TheReader reader = new TheReader("text");
			WorldMap map = new WorldMap();
			String text = reader.parseInput(new StringReader(s_xmlText));
			String world = reader.getWorld();
			return text + ' ' + map.getWorld(world);

		}
		catch(SAXException e)
		{
			return "Unable to parse: " + e.getMessage();
		}
	}

	public void setFocus()
	{
		m_helloText.setFocus();
	}
}
