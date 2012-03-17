package de.bitnoise.testing.config;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.bitnoise.testing.config.TestConfigReader.TestConfigReaderBuilder;
import de.bitnoise.testing.config.impl.AnnotationInjector;
import de.bitnoise.testing.config.impl.ClassicSyntax;
import de.bitnoise.testing.config.impl.MapStorage;
import static de.bitnoise.testing.fest.Assertions.*;

public class TestConfigReaderTest {

	TestConfigReaderBuilder builder = TestConfigReader.build();
	Target01 target01 = new Target01();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void noSyntax() {
		// prepare
		TestConfigReader sut = builder.createReader();

		// verify
		exception.expectMessage("Syntax not known");
		// execute
		sut.load(target01);
	}

	@Test
	public void noStorage() {
		// prepare
		TestConfigReader sut = builder
		        .setPropertySyntax(new ClassicSyntax())
		        .createReader();
		// verify
		exception.expectMessage("Storage not known");
		// execute
		sut.load(target01);
	}

	@Test
	public void noInjector() {
		// prepare
		TestConfigReader sut = builder
		        .setPropertySyntax(new ClassicSyntax())
		        .setStorageSystem(new MapStorage())
		        .createReader();
		// verify
		exception.expectMessage("Injector not known");
		// execute
		sut.load(target01);
	}

	@Test
	public void testSimpleEnvironment() {
		// prepare
		MapStorage store = new MapStorage();
		store.add("valueString", "wert01");
		TestConfigReader sut = builder
		        .setStorageSystem(store)
		        .setPropertySyntax(new ClassicSyntax())
		        .setObjectInjector(new AnnotationInjector())
		        .createReader();

		// execute
		sut.load(target01);

		// verify
		assertThat(target01.valueString).isEqualTo("wert01");
	}

	class Target01 {
		@TestConfigProperty("valueString")
		String valueString;
	}
}
