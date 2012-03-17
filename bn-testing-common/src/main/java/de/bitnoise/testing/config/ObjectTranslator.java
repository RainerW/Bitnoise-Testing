package de.bitnoise.testing.config;

public interface ObjectTranslator {

	/**
	 * Get a value
	 * 
	 * @param key
	 *            the key from where to lookup, can be a dsl
	 * @param type
	 *            the type of the target object this value will be injected
	 * @return the object which can be injected into the given type
	 */
	Object getValueFor(String key, Class<?> type);

}
