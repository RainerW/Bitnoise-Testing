package de.bitnoise.testing.config.impl;

import de.bitnoise.testing.config.ConfigStorage;
import de.bitnoise.testing.config.SyntaxParser;
import de.bitnoise.testing.config.ObjectTranslator;

public class PropertyToObjectTranslator implements ObjectTranslator {

	protected SyntaxParser _syntax;
	protected ConfigStorage _storage;

	public PropertyToObjectTranslator(SyntaxParser syntax, ConfigStorage storage) {
		_syntax = syntax;
		_storage = storage;
	}

	@Override
	public Object getValueFor(String dsl, Class<?> type) {
		String newKey = _syntax.resolveToKey(dsl, _storage);
		if (newKey == null) {
			newKey = dsl;
		}
		Object value = _storage.resolveKey(newKey);
		return transform(value, type);
	}

	protected Object transform(Object value, Class<?> type) {
		if (value == null) {
			return null;
		}
		Object into = null;
		if (type.isAssignableFrom(String.class)) {
			into = makeString(value);
		} else if (type.isAssignableFrom(Integer.class)) {
			into = makeInteger(value);
		} else if (type.isAssignableFrom(Boolean.class)) {
			into = makeBoolean(value);
		}
		if (into == null) {
			throw new IllegalArgumentException("I don't know how to caste the given type '"
			        + value.getClass() + "' into the target type '" + type + "'");
		}
		return into;
	}

	protected Boolean makeBoolean(Object value) {
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		if (value instanceof String) {
			return Boolean.valueOf((String) value);
		}
		return null;
	}

	protected Integer makeInteger(Object value) {
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof String) {
			return Integer.valueOf((String) value);
		}
		return null;
	}

	protected String makeString(Object value) {
		if (value instanceof String) {
			return (String) value;
		}
		return null;
	}

}
