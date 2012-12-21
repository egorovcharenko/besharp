package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class CharacterObjectType extends BaseDataType
{
  private static final CharacterObjectType singleTon = new CharacterObjectType();

  private CharacterObjectType()
  {
    super(localSqlType, arrayOfClass);
  }

  protected CharacterObjectType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  public static CharacterObjectType getSingleton()
  {
    return singleTon;
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
    throws SQLException
  {
    if (paramString.length() != 1)
      throw new SQLException("Problems with field " + paramFieldType + ", default string to long for Character: '" + paramString + "'");
    return Character.valueOf(paramString.charAt(0));
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return Character.valueOf(paramDatabaseResults.getChar(paramInt));
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.CharacterObjectType
 * JD-Core Version:    0.6.0
 */