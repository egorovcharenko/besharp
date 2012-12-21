package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.UUID;

public class UuidType extends BaseDataType
{
  public static int DEFAULT_WIDTH = 48;
  private static final UuidType singleTon = new UuidType();

  private UuidType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static UuidType getSingleton()
  {
    return singleTon;
  }

  public Object generatedId()
  {
    return UUID.randomUUID();
  }

  public int getDefaultWidth()
  {
    return DEFAULT_WIDTH;
  }

  public boolean isSelfGeneratedId()
  {
    return true;
  }

  public boolean isValidForField(Field paramField)
  {
    return true;
  }

  public boolean isValidGeneratedType()
  {
    return true;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
  {
    return ((UUID)paramObject).toString();
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    try
    {
      UUID localUUID = UUID.fromString(paramString);
      return localUUID;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " parsing default UUID-string '" + paramString + "'", localIllegalArgumentException);
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    String str = paramDatabaseResults.getString(paramInt);
    Object localObject;
    if (str == null)
      localObject = null;
    while (true)
    {
      return localObject;
      try
      {
        UUID localUUID = UUID.fromString(str);
        localObject = localUUID;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
      }
    }
    throw SqlExceptionUtil.create("Problems with column " + paramInt + " parsing UUID-string '" + str + "'", localIllegalArgumentException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.UuidType
 * JD-Core Version:    0.6.0
 */