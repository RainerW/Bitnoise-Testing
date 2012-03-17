package de.bitnoise.testing.config.impl;

import org.junit.Test;
import static de.bitnoise.testing.fest.Assertions.*;

public class MapStorageTest {
	@Test
	public void test() {
		// prepare
		MapStorage sut = new MapStorage();
		// verfiy 1
		assertThat(sut.resolveKey("key01")).isNull();
		// execute
		sut.add("key-01", "value-01");
		// verify 2
		assertThat(sut.resolveKey("key-01")).isEqualTo("value-01");
	}
}
