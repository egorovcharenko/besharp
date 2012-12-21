package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class ShortType extends ShortObjectType
{
  private static final ShortType singleTon = new ShortType();

  private ShortType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static ShortType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.ShortType
 * JD-Core Version:    0.6.0
 */