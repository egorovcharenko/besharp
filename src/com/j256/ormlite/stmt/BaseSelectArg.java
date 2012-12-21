package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import java.sql.SQLException;

public abstract class BaseSelectArg
  implements ArgumentHolder
{
  private String columnName = null;
  private FieldType fieldType = null;

  public String getColumnName()
  {
    if (this.columnName == null)
      throw new IllegalArgumentException("Column name has not been set");
    return this.columnName;
  }

  public FieldType getFieldType()
  {
    return this.fieldType;
  }

  public Object getSqlArgValue()
    throws SQLException
  {
    if (!isValueSet())
      throw new SQLException("Column value has not been set for " + this.columnName);
    Object localObject1 = getValue();
    Object localObject2;
    if (localObject1 == null)
      localObject2 = null;
    while (true)
    {
      return localObject2;
      if (this.fieldType == null)
      {
        localObject2 = localObject1;
        continue;
      }
      if ((this.fieldType.isForeign()) && (this.fieldType.getFieldType() == localObject1.getClass()))
      {
        localObject2 = this.fieldType.getForeignIdField().extractJavaFieldValue(localObject1);
        continue;
      }
      localObject2 = this.fieldType.convertJavaFieldToSqlArgValue(localObject1);
    }
  }

  protected abstract Object getValue();

  protected abstract boolean isValueSet();

  public void setMetaInfo(String paramString, FieldType paramFieldType)
  {
    if (this.columnName == null)
      if (this.fieldType != null)
        break label75;
    label75: 
    do
    {
      this.columnName = paramString;
      this.fieldType = paramFieldType;
      return;
      if (this.columnName.equals(paramString))
        break;
      throw new IllegalArgumentException("Column name cannot be set twice from " + this.columnName + " to " + paramString);
    }
    while (this.fieldType == paramFieldType);
    throw new IllegalArgumentException("FieldType name cannot be set twice from " + this.fieldType + " to " + paramFieldType);
  }

  public abstract void setValue(Object paramObject);

  public String toString()
  {
    Object localObject1;
    if (!isValueSet())
      localObject1 = "[unset]";
    while (true)
    {
      return localObject1;
      try
      {
        Object localObject2 = getSqlArgValue();
        if (localObject2 == null)
        {
          localObject1 = "[null]";
          continue;
        }
        String str = localObject2.toString();
        localObject1 = str;
      }
      catch (SQLException localSQLException)
      {
        localObject1 = "[could not get value: " + localSQLException.getMessage() + "]";
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.BaseSelectArg
 * JD-Core Version:    0.6.0
 */