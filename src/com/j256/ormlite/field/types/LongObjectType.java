package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class LongObjectType extends BaseDataType
{
  private static final LongObjectType singleTon = new LongObjectType();

  private LongObjectType()
  {
    super(localSqlType, arrayOfClass);
  }

  protected LongObjectType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  public static LongObjectType getSingleton()
  {
    return singleTon;
  }

  public Object convertIdNumber(Number paramNumber)
  {
    return Long.valueOf(paramNumber.longValue());
  }

  public boolean isEscapedValue()
  {
    return false;
  }

  public boolean isValidForField(Field paramField)
  {
    return true;
  }

  public boolean isValidGeneratedType()
  {
    return true;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    return paramObject;
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
  {
    return Long.valueOf(Long.parseLong(paramString));
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return Long.valueOf(paramDatabaseResults.getLong(paramInt));
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.LongObjectType
 * JD-Core Version:    0.6.0
 */