package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class IntegerObjectType extends BaseDataType
{
  private static final IntegerObjectType singleTon = new IntegerObjectType();

  private IntegerObjectType()
  {
    super(localSqlType, arrayOfClass);
  }

  protected IntegerObjectType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  public static IntegerObjectType getSingleton()
  {
    return singleTon;
  }

  public Object convertIdNumber(Number paramNumber)
  {
    return Integer.valueOf(paramNumber.intValue());
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
    return Integer.valueOf(Integer.parseInt(paramString));
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return Integer.valueOf(paramDatabaseResults.getInt(paramInt));
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.IntegerObjectType
 * JD-Core Version:    0.6.0
 */