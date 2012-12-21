package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class FloatType extends FloatObjectType
{
  private static final FloatType singleTon = new FloatType();

  private FloatType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static FloatType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.FloatType
 * JD-Core Version:    0.6.0
 */