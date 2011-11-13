package de.bitnoise.testing.db.simple;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class RowBuilderTest
{
  @Test
  public void defaultConstrcutor_oneColumn() throws Exception
  {
    // execute
    RowBuilder<String> sut = new RowBuilder<String>(1);
    // verify
    assertThat(sut.getValues()).hasSize(1);
    assertThat(sut.getValues()[0]).isNull();
  }

  @Test
  public void defaultConstrcutor_ThreeColumns() throws Exception
  {
    // execute
    RowBuilder<String> sut = new RowBuilder<String>(3);
    // verify
    assertThat(sut.getValues()).hasSize(3);
    assertThat(sut.getValues()[0]).isNull();
    assertThat(sut.getValues()[1]).isNull();
    assertThat(sut.getValues()[2]).isNull();
  }

  @Test
  public void threeColumns_setAndGet() throws Exception
  {
    // prepare
    RowBuilder<String> sut = new RowBuilder<String>(3);
    sut.setValue(0, "Wert 0");
    sut.setValue(1, "Wert 1");
    sut.setValue(2, "Wert 2");

    // execute
    Object wert0 = sut.getValue(0);
    Object wert1 = sut.getValue(1);
    Object wert2 = sut.getValue(2);

    // verify
    assertThat(wert0).isEqualTo("Wert 0");
    assertThat(wert1).isEqualTo("Wert 1");
    assertThat(wert2).isEqualTo("Wert 2");
  }

}
