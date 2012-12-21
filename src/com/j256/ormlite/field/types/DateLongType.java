package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;

public class DateLongType extends BaseDataType
{
  private static final DateLongType singleTon = new DateLongType();

  private DateLongType()
  {
    super(SqlType.LONG, new Class[0]);
  }

  public static DateLongType getSingleton()
  {
    return singleTon;
  }

  public boolean isEscapedValue()
  {
    return false;
  }

  public boolean isValidForField(Field paramField)
  {
    return true;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
  {
    return Long.valueOf(((Date)paramObject).getTime());
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    try
    {
      Long localLong = Long.valueOf(Long.parseLong(paramString));
      return localLong;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " parsing default date-long value: " + paramString, localNumberFormatException);
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return new Date(paramDatabaseResults.getLong(paramInt));
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.DateLongType
 * JD-Core Version:    0.6.0
 */