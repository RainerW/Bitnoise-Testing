package de.bitnoise.testing.junit.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class BeforeAfterRule implements MethodRule
{

  @Override
  public Statement apply(final Statement base, final FrameworkMethod method,
      final Object target)
  {
    return new Statement()
    {
      @Override
      public void evaluate() throws Throwable
      {
        RuleDescriptor descriptor = new RuleDescriptorImpl(method, target);
        onBefore(descriptor);
        try
        {
          base.evaluate();
          onSuccess(descriptor);
        }
        catch (Throwable t)
        {
          if (onError(descriptor, t))
          {
            throw t;
          }
        }
        finally
        {
          onFinally(descriptor);
        }
      }
    };
  }

  protected void onFinally(RuleDescriptor descriptor) throws Throwable
  {
  }

  protected boolean onError(RuleDescriptor descriptor, Throwable t)
      throws Throwable
  {
    return true;
  }

  protected void onSuccess(RuleDescriptor descriptor) throws Throwable
  {
  }

  protected void onBefore(RuleDescriptor descriptor) throws Throwable
  {
  }

}
