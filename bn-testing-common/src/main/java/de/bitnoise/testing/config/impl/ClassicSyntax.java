package de.bitnoise.testing.config.impl;

import de.bitnoise.testing.config.ConfigStorage;
import de.bitnoise.testing.config.SyntaxParser;

public class ClassicSyntax implements SyntaxParser {

	@Override
	public String resolveToKey(String dsl, ConfigStorage _storage) {
		return dsl;
	}

}
