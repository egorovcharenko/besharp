package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class BooleanObjectType extends BaseDataType
{
  private static final BooleanObjectType singleTon = new BooleanObjectType();

  private BooleanObjectType()
  {
    super(localSqlType, arrayOfClass);
  }

  protected BooleanObjectType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  public static BooleanObjectType getSingleton()
  {
    return singleTon;
  }

  public boolean isAppropriateId()
  {
    return false;
  }

  public boolean isEscapedValue()
  {
    return false;
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
    return Boolean.valueOf(Boolean.parseBoolean(paramString));
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return Boolean.valueOf(paramDatabaseResults.getBoolean(paramInt));
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.BooleanObjectType
 * JD-Core Version:    0.6.0
 */