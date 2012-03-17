package de.bitnoise.testing.config.impl;

import org.junit.Test;

import de.bitnoise.testing.config.TestConfigProperty;
import de.bitnoise.testing.config.ObjectTranslator;
import static de.bitnoise.testing.fest.Assertions.*;

public class AnnotationInjectorTest {
	AnnotationInjector sut = new AnnotationInjector();
	ObjectTranslator source = new ObjectTranslator() {
		@Override
		public Object getValueFor(String key, Class<?> type) {
			if (type.isAssignableFrom(String.class))
				return key;
			if (type.isAssignableFrom(Integer.class))
				return 42;
			return null;
		}
	};

	@Test
	public void injectString() {
		// prepare
		TargetString target = new TargetString();

		// execute
		sut.load(source, target);

		// verify
		assertThat(target.valueString).isEqualTo("value");
	}

	@Test
	public void injectInteger() {
		// prepare
		TargetInteger target = new TargetInteger();

		// execute
		sut.load(source, target);

		// verify
		assertThat(target.valueInteger).isEqualTo(42);
	}

	@Test
	public void injectInherited() {
		// prepare
		TargetStringInherited target = new TargetStringInherited();

		// execute
		sut.load(source, target);

		// verify
		assertThat(target.otherString).isEqualTo("valueInherited");
		assertThat(target.valueString).isEqualTo("value");
	}

	@Test
	public void injectInheritedOverwrite() {
		// prepare
		TargetStringInheritedOverwrite target = new TargetStringInheritedOverwrite();

		// execute
		sut.load(source, target);

		// verify
		assertThat(target.valueString).isEqualTo("valueInherited");
	}

	class TargetString {
		@TestConfigProperty("value")
		String valueString;
	}

	class TargetInteger {
		@TestConfigProperty("intValue")
		Integer valueInteger;
	}

	class TargetStringInheritedOverwrite extends TargetString {
		@TestConfigProperty("valueInherited")
		String valueString;
	}

	class TargetStringInherited extends TargetString {
		@TestConfigProperty("valueInherited")
		String otherString;
	}
}
