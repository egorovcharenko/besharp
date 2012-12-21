package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class ShortObjectType extends BaseDataType
{
  private static final ShortObjectType singleTon = new ShortObjectType();

  private ShortObjectType()
  {
    super(localSqlType, arrayOfClass);
  }

  protected ShortObjectType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  public static ShortObjectType getSingleton()
  {
    return singleTon;
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
    return Short.valueOf(Short.parseShort(paramString));
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return Short.valueOf(paramDatabaseResults.getShort(paramInt));
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.ShortObjectType
 * JD-Core Version:    0.6.0
 */