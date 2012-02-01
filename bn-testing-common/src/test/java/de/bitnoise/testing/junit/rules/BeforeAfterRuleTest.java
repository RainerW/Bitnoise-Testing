package de.bitnoise.testing.junit.rules;

import org.fest.assertions.Fail;
import org.junit.Rule;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.mockito.Mock;

import de.bitnoise.testing.mockito.MockitoRule;

public class BeforeAfterRuleTest
{
  int _onBefore;

  int _onError;

  int _onFinally;

  int _onSuccess;
  
  boolean _errorReturn = true;

  BeforeAfterRule sut = new BeforeAfterRule()
  {

    protected void onBefore(RuleDescriptor descriptor) throws Throwable
    {
      _onBefore++;
      super.onBefore(descriptor);
    };

    protected boolean onError(RuleDescriptor descriptor, Throwable t)
        throws Throwable
    {
      _onError++;
      super.onError(descriptor, t);
      return _errorReturn;
    };

    protected void onFinally(RuleDescriptor descriptor) throws Throwable
    {
      _onFinally++;
      super.onFinally(descriptor);
    };

    protected void onSuccess(RuleDescriptor descriptor) throws Throwable
    {
      _onSuccess++;
      super.onSuccess(descriptor);
    };
  };

  @Rule
  public MockitoRule mockito = new MockitoRule();

  @Mock
  Statement statement;

  @Mock
  FrameworkMethod method;

  Object target = new Object();

  @Test
  public void noError() throws Throwable
  {
    // execute
    sut.apply(statement, method, target).evaluate();

    // verify
    verify(statement).evaluate();
    assertThat(_onBefore).isEqualTo(1);
    assertThat(_onError).isEqualTo(0);
    assertThat(_onFinally).isEqualTo(1);
    assertThat(_onSuccess).isEqualTo(1);
  }

  @Test
  public void hasError() throws Throwable
  {
    // prepare / verify
    Throwable error = new IllegalArgumentException();
    doThrow(error).when(statement).evaluate();

    // execute
    try
    {
      sut.apply(statement, method, target).evaluate();
      Fail.fail();
    }
    catch (IllegalArgumentException actual)
    {
      assertThat(actual).isSameAs(error);
    }

    // verify
    assertThat(_onBefore).isEqualTo(1);
    assertThat(_onError).isEqualTo(1);
    assertThat(_onFinally).isEqualTo(1);
    assertThat(_onSuccess).isEqualTo(0);
  }

  @Test
  public void hasSupressError() throws Throwable
  {
    // prepare / verify
    _errorReturn=false;
    doThrow(new IllegalArgumentException()).when(statement).evaluate();

    // execute
    sut.apply(statement, method, target).evaluate();
    
    // verify
    assertThat(_onBefore).isEqualTo(1);
    assertThat(_onError).isEqualTo(1);
    assertThat(_onFinally).isEqualTo(1);
    assertThat(_onSuccess).isEqualTo(0);
  }

}
