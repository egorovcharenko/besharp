package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class StringBytesType extends BaseDataType
{
  private static final String DEFAULT_STRING_BYTES_CHARSET_NAME = "Unicode";
  private static final StringBytesType singleTon = new StringBytesType();

  private StringBytesType()
  {
    super(SqlType.BYTE_ARRAY, new Class[0]);
  }

  private String getCharsetName(FieldType paramFieldType)
  {
    if ((paramFieldType == null) || (paramFieldType.getFormat() == null));
    for (String str = "Unicode"; ; str = paramFieldType.getFormat())
      return str;
  }

  public static StringBytesType getSingleton()
  {
    return singleTon;
  }

  public boolean isAppropriateId()
  {
    return false;
  }

  public boolean isSelectArgRequired()
  {
    return true;
  }

  public boolean isValidForField(Field paramField)
  {
    return true;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    String str1 = (String)paramObject;
    String str2 = getCharsetName(paramFieldType);
    try
    {
      byte[] arrayOfByte = str1.getBytes(str2);
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw SqlExceptionUtil.create("Could not convert string with charset name: " + str2, localUnsupportedEncodingException);
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    throw new SQLException("String bytes type cannot have default values");
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    byte[] arrayOfByte = paramDatabaseResults.getBytes(paramInt);
    String str2;
    if (arrayOfByte == null)
      str2 = null;
    String str1;
    while (true)
    {
      return str2;
      str1 = getCharsetName(paramFieldType);
      try
      {
        str2 = new String(arrayOfByte, str1);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
    }
    throw SqlExceptionUtil.create("Could not convert string with charset name: " + str1, localUnsupportedEncodingException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.StringBytesType
 * JD-Core Version:    0.6.0
 */