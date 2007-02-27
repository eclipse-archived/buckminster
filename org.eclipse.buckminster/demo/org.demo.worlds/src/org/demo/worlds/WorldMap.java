package org.demo.worlds;

import java.util.HashMap;

public class WorldMap
{
	private final HashMap m_worldMap = new HashMap();
	
	public WorldMap()
	{
		m_worldMap.put("earth", "Earth World");
		m_worldMap.put("alien", "Alien World");
	}
	
	public String getWorld(String world)
	{
		return (String)m_worldMap.get(world);
	}
}
