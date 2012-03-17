package de.bitnoise.testing.config.impl;

import de.bitnoise.testing.config.Environment;

/**
 * Simple Environment where the environments are not detected, they are passed via the constructor.
 */
public class SimpleEnvironment implements Environment {

	protected String _name;

	/**
	 * Just creates a Environment with the given Name
	 * 
	 * @param name
	 *            The name of the environemnt
	 */
	public SimpleEnvironment(String name) {
		_name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleEnvironment other = (SimpleEnvironment) obj;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		return true;
	}

}
