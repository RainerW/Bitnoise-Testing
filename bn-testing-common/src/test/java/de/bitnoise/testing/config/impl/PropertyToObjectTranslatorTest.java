package de.bitnoise.testing.config.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.bitnoise.testing.config.ConfigStorage;
import de.bitnoise.testing.config.SyntaxParser;

public class PropertyToObjectTranslatorTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testOuterMethod() {
		// prepare
		SyntaxParser syntax = mock(SyntaxParser.class);
		ConfigStorage storage = mock(ConfigStorage.class);
		// Create SUT, but spy on it!
		PropertyToObjectTranslator sut = spy(new PropertyToObjectTranslator(syntax, storage));
		when(syntax.resolveToKey("keyName", storage)).thenReturn("keyValue");
		when(storage.resolveKey("keyValue")).thenReturn("storeValue");
		when(sut.transform("storeValue", String.class)).thenReturn("result");

		// execute
		Object result = sut.getValueFor("keyName", String.class);
		assertThat(result).isEqualTo("result");

		// verify
		verify(syntax).resolveToKey("keyName", storage);
		verify(sut, times(2)).transform("storeValue", String.class); // one additional time for the spy setup
	}
	
	@Test
	public void testOuterMethodWithNotParsableSyntax() {
		// prepare
		SyntaxParser syntax = mock(SyntaxParser.class);
		ConfigStorage storage = mock(ConfigStorage.class);
		// Create SUT, but spy on it!
		PropertyToObjectTranslator sut = spy(new PropertyToObjectTranslator(syntax, storage));
		when(syntax.resolveToKey("keyName", storage)).thenReturn(null);
		when(storage.resolveKey("keyName")).thenReturn("storeValue");
		when(sut.transform("storeValue", String.class)).thenReturn("result");
		
		// execute
		Object result = sut.getValueFor("keyName", String.class);
		assertThat(result).isEqualTo("result");
		
		// verify
		verify(syntax).resolveToKey("keyName", storage);
		verify(sut, times(2)).transform("storeValue", String.class); // one additional time for the spy setup
	}

	PropertyToObjectTranslator sut = new PropertyToObjectTranslator(null, null);

	@Test
	public void transform_String_to_String() {
		assertThat(sut.transform(null, String.class)).isNull();
		assertThat(sut.transform("", String.class)).isInstanceOf(String.class).isEqualTo("");
		assertThat(sut.transform(" ", String.class)).isInstanceOf(String.class).isEqualTo(" ");
		assertThat(sut.transform("1253", String.class)).isInstanceOf(String.class).isEqualTo("1253");
		assertThat(sut.transform("String", String.class)).isInstanceOf(String.class).isEqualTo("String");
	}

	@Test
	public void transform_String_to_Integer() {
		assertThat(sut.transform(null, Integer.class)).isNull();
		assertThat(sut.transform("1", Integer.class)).isInstanceOf(Integer.class).isEqualTo(1);
		assertThat(sut.transform("-654", Integer.class)).isInstanceOf(Integer.class).isEqualTo(-654);
	}

	@Test
	public void transformNot_String_to_Integer() {
		exception.expect(IllegalArgumentException.class);
		sut.transform("  1", Integer.class);
	}

	@Test
	public void transform_String_to_Boolean() {
		assertThat(sut.transform(null, Boolean.class)).isNull();
		assertThat(sut.transform("true", Boolean.class)).isInstanceOf(Boolean.class).isEqualTo(true);
		assertThat(sut.transform("false", Boolean.class)).isInstanceOf(Boolean.class).isEqualTo(false);
		assertThat(sut.transform(" ", Boolean.class)).isInstanceOf(Boolean.class).isEqualTo(false);
	}

}
