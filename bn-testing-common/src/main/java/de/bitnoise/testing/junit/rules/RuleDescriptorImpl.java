package de.bitnoise.testing.junit.rules;

import org.junit.runners.model.FrameworkMethod;

public class RuleDescriptorImpl implements RuleDescriptor
{
  FrameworkMethod _method;

  Object _target;

  public RuleDescriptorImpl(FrameworkMethod method, Object target)
  {
    _method = method;
    _target = target;
  }

  @Override
  public Object getTarget()
  {
    return _target;
  }

}
