package de.bitnoise.testing.db;

import static org.fest.assertions.Assertions.*;

import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DataType;
import org.junit.Test;

public class BaseDBTableTest
{
  @Test
  public void testAddRow() throws Exception
  {
    // preapre
    BaseDBTable sut = new BaseDBTable("Eine Tabelle_01");
    sut.addColumn("column 1 ", DataType.VARCHAR);

    // execute
    ITable table = sut.getTable();

    // verify
    assertThat(table.getRowCount()).isEqualTo(0);
    assertThat(table.getTableMetaData()).isNotNull();
    assertThat(table.getTableMetaData().getTableName()).isEqualTo("Eine Tabelle_01");
    assertThat(table.getTableMetaData().getColumns()).hasSize(1);
    assertThat(table.getTableMetaData().getColumnIndex("column 1 ")).isEqualTo(0);
  }

}
