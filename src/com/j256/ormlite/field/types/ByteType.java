package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class ByteType extends ByteObjectType
{
  private static final ByteType singleTon = new ByteType();

  private ByteType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static ByteType getSingleton()
  {
    return singleTon;
  }

  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.ByteType
 * JD-Core Version:    0.6.0
 */