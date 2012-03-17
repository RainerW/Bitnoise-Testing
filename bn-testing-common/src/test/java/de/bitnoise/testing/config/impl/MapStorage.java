package de.bitnoise.testing.config.impl;

import java.util.HashMap;
import java.util.Map;

import de.bitnoise.testing.config.ConfigStorage;

public class MapStorage implements ConfigStorage {

	private Map<String, String> _map = new HashMap<String, String>();

	public void add(String key, String value) {
		_map.put(key, value);
	}

	@Override
    public Object resolveKey(String key) {
	    return _map.get(key);
    }

	 
}
