package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.sql.SQLException;

public class CharType extends CharacterObjectType
{
  private static final CharType singleTon = new CharType();

  private CharType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static CharType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    Character localCharacter1 = (Character)paramObject;
    if ((localCharacter1 == null) || (localCharacter1.charValue() == 0));
    for (Character localCharacter2 = null; ; localCharacter2 = localCharacter1)
      return localCharacter2;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.CharType
 * JD-Core Version:    0.6.0
 */