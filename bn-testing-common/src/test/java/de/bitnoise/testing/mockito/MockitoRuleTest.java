package de.bitnoise.testing.mockito;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.exceptions.verification.NoInteractionsWanted;

public class MockitoRuleTest
{

  @Rule
  public MockitoRule sut = new MockitoRule();

  @Mock
  List<String> mock;

  @Test
  public void verifyAtMockWorked()
  {
    assertThat(mock).isNotNull();
  }

  @Test(expected = NoInteractionsWanted.class)
  public void verifyNoMoreIntaractions()
  {
    when(mock.add("value")).thenReturn(true);
    mock.add("value");
    mock.add("value2");
    verifyNoMoreInteractions(mock);
  }
}
