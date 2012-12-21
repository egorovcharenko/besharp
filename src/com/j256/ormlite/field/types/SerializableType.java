package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;

public class SerializableType extends BaseDataType
{
  private static final SerializableType singleTon = new SerializableType();

  private SerializableType()
  {
    super(SqlType.SERIALIZABLE, new Class[0]);
  }

  public static SerializableType getSingleton()
  {
    return singleTon;
  }

  public boolean isAppropriateId()
  {
    return false;
  }

  public boolean isComparable()
  {
    return false;
  }

  public boolean isSelectArgRequired()
  {
    return true;
  }

  public boolean isStreamType()
  {
    return true;
  }

  public boolean isValidForField(Field paramField)
  {
    return Serializable.class.isAssignableFrom(paramField.getType());
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(paramObject);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    throw SqlExceptionUtil.create("Could not write serialized object to byte array: " + paramObject, localException);
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    throw new SQLException("Default values for serializable types are not supported");
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    byte[] arrayOfByte = paramDatabaseResults.getBytes(paramInt);
    Object localObject2;
    if (arrayOfByte == null)
      localObject2 = null;
    while (true)
    {
      return localObject2;
      try
      {
        Object localObject1 = new ObjectInputStream(new ByteArrayInputStream(arrayOfByte)).readObject();
        localObject2 = localObject1;
      }
      catch (Exception localException)
      {
      }
    }
    throw SqlExceptionUtil.create("Could not read serialized object from byte array: " + Arrays.toString(arrayOfByte) + "(len " + arrayOfByte.length + ")", localException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.SerializableType
 * JD-Core Version:    0.6.0
 */