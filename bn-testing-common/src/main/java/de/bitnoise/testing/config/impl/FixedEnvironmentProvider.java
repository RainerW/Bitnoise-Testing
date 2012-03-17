package de.bitnoise.testing.config.impl;

import java.util.ArrayList;
import java.util.List;

import de.bitnoise.testing.config.Environment;
import de.bitnoise.testing.config.EnvironmentProvider;

/**
 * Simple Environment Provider with just a fixed list.
 */
public class FixedEnvironmentProvider implements EnvironmentProvider {

	ArrayList<Environment> result = new ArrayList<Environment>();

	public FixedEnvironmentProvider(String... names) {
		for (String name : names) {
			result.add(new SimpleEnvironment(name));
		}
	}

	@Override
	public List<Environment> getDetectedEnvironments() {
		return result;
	}

}
