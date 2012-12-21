package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class DoubleType extends DoubleObjectType
{
  private static final DoubleType singleTon = new DoubleType();

  private DoubleType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static DoubleType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.DoubleType
 * JD-Core Version:    0.6.0
 */