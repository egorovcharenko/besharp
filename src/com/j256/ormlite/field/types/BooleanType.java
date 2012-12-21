package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class BooleanType extends BooleanObjectType
{
  private static final BooleanType singleTon = new BooleanType();

  private BooleanType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static BooleanType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.BooleanType
 * JD-Core Version:    0.6.0
 */