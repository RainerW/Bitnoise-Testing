package de.bitnoise.testing.config;

import de.bitnoise.testing.config.impl.AnnotationInjector;
import de.bitnoise.testing.config.impl.ClassicSyntax;
import de.bitnoise.testing.config.impl.PropertyToObjectTranslator;

public class TestConfigReader {

	public static TestConfigReader defaultReader() {
		return build()
		        // TODO : implement this
		        // .addEnvironmentProvider(1, new LocalUsername())
		        // .addEnvironmentProvider(2, new ComputerName())
//		        .setStorageSystem(new PropertyFiles())
		        .setPropertySyntax(new ClassicSyntax())
		        .setObjectInjector(new AnnotationInjector())
		        .createReader();
	}

	protected ObjectInjector _injector;
	protected SyntaxParser _syntax;
	protected ConfigStorage _storage;

	protected TestConfigReader() {
	}

	public TestConfigReader(TestConfigReader from) {
		_injector = from._injector;
		_syntax = from._syntax;
		_storage = from._storage;
	}

	/**
	 * Parses the given Object and injects the TestConfiguration
	 */
	// this method will not be used by Java, but code completion will list this
	public void load(Class<?> staticClassToLoad) {
		doLoad(staticClassToLoad);
	}

	/**
	 * Parses the given Object and injects the TestConfiguration
	 */
	public void load(Object target) {
		doLoad(target);
	}

	protected void doLoad(Object target) {
		if (_syntax == null) {
			throw new IllegalArgumentException("Syntax not known");
		}
		if (_storage == null) {
			throw new IllegalArgumentException("Storage not known");
		}
		if (_injector == null) {
			throw new IllegalArgumentException("Injector not known");
		}
		PropertyToObjectTranslator source = new PropertyToObjectTranslator(_syntax,_storage);
		_injector.load(source, target);
	}

	public static TestConfigReaderBuilder build() {
		return new TestConfigReaderBuilder();
	}

	public static class TestConfigReaderBuilder {

		protected TestConfigReader _reader;

		public TestConfigReaderBuilder() {
			_reader = new TestConfigReader();
		}

		protected TestConfigReaderBuilder(TestConfigReaderBuilder cloneFrom) {
			_reader = new TestConfigReader(cloneFrom._reader);
		}

		public TestConfigReaderBuilder addEnvironmentProvider(Integer priority,
		        EnvironmentProvider... poviders) {
			TestConfigReaderBuilder builder = new TestConfigReaderBuilder(this);
			return builder;
		}

		public TestConfigReaderBuilder setObjectInjector(
		        ObjectInjector injector) {
			TestConfigReaderBuilder builder = new TestConfigReaderBuilder(this);
			builder._reader._injector = injector;
			return builder;
		}

		public TestConfigReaderBuilder setPropertySyntax(SyntaxParser syntax) {
			TestConfigReaderBuilder builder = new TestConfigReaderBuilder(this);
			builder._reader._syntax = syntax;
			return builder;
		}

		public TestConfigReaderBuilder setStorageSystem(ConfigStorage storage) {
			TestConfigReaderBuilder builder = new TestConfigReaderBuilder(this);
			builder._reader._storage = storage;
			return builder;
		}

		public TestConfigReader createReader() {
			return _reader;
		}
	}

}
