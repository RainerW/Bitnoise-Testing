package de.bitnoise.testing.config.impl;

import org.junit.Test;
import static de.bitnoise.testing.fest.Assertions.*;

public class SimpleEnvironmentTest {
	@Test
	public void testEquals() {
		SimpleEnvironment s1 = new SimpleEnvironment("rainer");
		SimpleEnvironment s2 = new SimpleEnvironment("rainer");
		assertThat(s1).isEqualTo(s2);
	}
}
