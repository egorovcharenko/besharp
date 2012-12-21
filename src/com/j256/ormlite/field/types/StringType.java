package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class StringType extends BaseDataType
{
  public static int DEFAULT_WIDTH = 255;
  private static final StringType singleTon = new StringType();

  private StringType()
  {
    super(localSqlType, arrayOfClass);
  }

  protected StringType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  public static StringType getSingleton()
  {
    return singleTon;
  }

  public int getDefaultWidth()
  {
    return DEFAULT_WIDTH;
  }

  public boolean isValidForField(Field paramField)
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
    return paramString;
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return paramDatabaseResults.getString(paramInt);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.StringType
 * JD-Core Version:    0.6.0
 */