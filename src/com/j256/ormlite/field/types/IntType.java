package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class IntType extends IntegerObjectType
{
  private static final IntType singleTon = new IntType();

  private IntType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static IntType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.IntType
 * JD-Core Version:    0.6.0
 */