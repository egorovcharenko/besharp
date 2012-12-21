package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class VoidType extends BaseDataType
{
  private VoidType()
  {
    super(null, new Class[0]);
  }

  public boolean isValidForField(Field paramField)
  {
    return false;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
  {
    return null;
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    return null;
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return null;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.VoidType
 * JD-Core Version:    0.6.0
 */