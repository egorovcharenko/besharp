package com.j256.ormlite.field.types;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public abstract class BaseDataType
  implements DataPersister
{
  private final Class<?>[] classes;
  private final SqlType sqlType;

  public BaseDataType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    this.sqlType = paramSqlType;
    this.classes = paramArrayOfClass;
  }

  public Object convertIdNumber(Number paramNumber)
  {
    return null;
  }

  public Object generatedId()
  {
    return null;
  }

  public Class<?>[] getAssociatedClasses()
  {
    return this.classes;
  }

  public int getDefaultWidth()
  {
    return 0;
  }

  public SqlType getSqlType()
  {
    return this.sqlType;
  }

  public boolean isAppropriateId()
  {
    return true;
  }

  public boolean isComparable()
  {
    return true;
  }

  public boolean isEscapedDefaultValue()
  {
    return isEscapedValue();
  }

  public boolean isEscapedValue()
  {
    return true;
  }

  public boolean isPrimitive()
  {
    return false;
  }

  public boolean isSelectArgRequired()
  {
    return false;
  }

  public boolean isSelfGeneratedId()
  {
    return false;
  }

  public boolean isStreamType()
  {
    return false;
  }

  public abstract boolean isValidForField(Field paramField);

  public boolean isValidGeneratedType()
  {
    return false;
  }

  public abstract Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException;

  public Object makeConfigObject(FieldType paramFieldType)
    throws SQLException
  {
    return null;
  }

  public abstract Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException;

  public abstract Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.BaseDataType
 * JD-Core Version:    0.6.0
 */