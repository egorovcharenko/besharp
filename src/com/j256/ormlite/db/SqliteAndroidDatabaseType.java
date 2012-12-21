package com.j256.ormlite.db;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.types.DateStringType;

public class SqliteAndroidDatabaseType extends BaseSqliteDatabaseType
  implements DatabaseType
{
  protected void appendBooleanType(StringBuilder paramStringBuilder, int paramInt)
  {
    appendShortType(paramStringBuilder, paramInt);
  }

  protected void appendDateType(StringBuilder paramStringBuilder, int paramInt)
  {
    appendStringType(paramStringBuilder, paramInt);
  }

  public String getDatabaseName()
  {
    return "Android SQLite";
  }

  protected String getDriverClassName()
  {
    return null;
  }

  public FieldConverter getFieldConverter(DataPersister paramDataPersister)
  {
    switch (1.$SwitchMap$com$j256$ormlite$field$SqlType[paramDataPersister.getSqlType().ordinal()])
    {
    default:
    case 1:
    }
    for (Object localObject = super.getFieldConverter(paramDataPersister); ; localObject = DateStringType.getSingleton())
      return localObject;
  }

  public boolean isBatchUseTransaction()
  {
    return true;
  }

  public boolean isDatabaseUrlThisType(String paramString1, String paramString2)
  {
    return true;
  }

  public boolean isNestedSavePointsSupported()
  {
    return false;
  }

  public void loadDriver()
  {
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.db.SqliteAndroidDatabaseType
 * JD-Core Version:    0.6.0
 */