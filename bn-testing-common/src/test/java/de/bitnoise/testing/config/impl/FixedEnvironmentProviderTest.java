package de.bitnoise.testing.config.impl;

import org.junit.Test;
import static de.bitnoise.testing.fest.Assertions.*;

public class FixedEnvironmentProviderTest {

	@Test
	public void empty() {
		FixedEnvironmentProvider sut = new FixedEnvironmentProvider();
		assertThat(sut.getDetectedEnvironments()).isEmpty();
	}

	@Test
	public void fixed() {
		FixedEnvironmentProvider sut = new FixedEnvironmentProvider("test01");
		assertThat(sut.getDetectedEnvironments()).containsExactly(new SimpleEnvironment("test01"));
	}

	@Test
	public void twoEnvironments() {
		FixedEnvironmentProvider sut = new FixedEnvironmentProvider("test01", "test02");
		assertThat(sut.getDetectedEnvironments()).containsExactly(new SimpleEnvironment("test01"), new SimpleEnvironment("test02"));
	}
}
