package de.bitnoise.testing.mockito;

import org.mockito.MockitoAnnotations;

import de.bitnoise.testing.junit.rules.BeforeAfterRule;
import de.bitnoise.testing.junit.rules.RuleDescriptor;

/**
 * Rule to run process @Mock with Mockito
 */
public class MockitoRule extends BeforeAfterRule
{

  @Override
  protected void onBefore(RuleDescriptor descriptor) throws Throwable
  {
    MockitoAnnotations.initMocks(descriptor.getTarget());
  }

}
