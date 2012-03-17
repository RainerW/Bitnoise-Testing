package de.bitnoise.testing.config;

public interface SyntaxParser {

	/**
	 * Resolve the given DSL to a key.
	 * @param dsl
	 * @param _storage
	 * @return
	 */
	public String resolveToKey(String dsl, ConfigStorage _storage);

}
