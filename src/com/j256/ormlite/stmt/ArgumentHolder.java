package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import java.sql.SQLException;

public abstract interface ArgumentHolder
{
  public abstract String getColumnName();

  public abstract FieldType getFieldType();

  public abstract Object getSqlArgValue()
    throws SQLException;

  public abstract void setMetaInfo(String paramString, FieldType paramFieldType);

  public abstract void setValue(Object paramObject);
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.ArgumentHolder
 * JD-Core Version:    0.6.0
 */