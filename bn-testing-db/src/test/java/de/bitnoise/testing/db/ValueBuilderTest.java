package de.bitnoise.testing.db;

import static org.fest.assertions.Assertions.*;

public class ValueBuilderTest
{
  Object _lastSetValue;
  int _lastSetIndex;

  BaseDBTable table = new BaseDBTable("")
  {
    public void setValue(int index, Object value)
    {

    }

    ;
  };

}
