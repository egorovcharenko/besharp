package com.j256.ormlite.db;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.util.List;

public abstract class BaseSqliteDatabaseType extends BaseDatabaseType
  implements DatabaseType
{
  private static final FieldConverter booleanConverter = new BaseDatabaseType.BooleanNumberFieldConverter();

  protected void configureGeneratedId(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3)
  {
    if (paramFieldType.getSqlType() != SqlType.INTEGER)
      throw new IllegalArgumentException("Sqlite requires that auto-increment generated-id be integer types");
    paramStringBuilder.append("PRIMARY KEY AUTOINCREMENT ");
  }

  protected boolean generatedIdSqlAtEnd()
  {
    return false;
  }

  public FieldConverter getFieldConverter(DataPersister paramDataPersister)
  {
    switch (1.$SwitchMap$com$j256$ormlite$field$SqlType[paramDataPersister.getSqlType().ordinal()])
    {
    default:
    case 1:
    }
    for (FieldConverter localFieldConverter = super.getFieldConverter(paramDataPersister); ; localFieldConverter = booleanConverter)
      return localFieldConverter;
  }

  public boolean isCreateTableReturnsZero()
  {
    return false;
  }

  public boolean isVarcharFieldWidthSupported()
  {
    return false;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.db.BaseSqliteDatabaseType
 * JD-Core Version:    0.6.0
 */