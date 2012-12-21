package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class DateType extends BaseDateType
{
  public static final BaseDateType.DateStringFormatConfig defaultDateFormatConfig = new BaseDateType.DateStringFormatConfig("yyyy-MM-dd HH:mm:ss.SSSSSS");
  private static final DateType singleTon = new DateType();

  private DateType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static DateType getSingleton()
  {
    return singleTon;
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
  {
    return new Timestamp(((Date)paramObject).getTime());
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    BaseDateType.DateStringFormatConfig localDateStringFormatConfig = convertDateStringConfig(paramFieldType);
    try
    {
      Timestamp localTimestamp = new Timestamp(parseDateString(localDateStringFormatConfig, paramString).getTime());
      return localTimestamp;
    }
    catch (ParseException localParseException)
    {
    }
    throw SqlExceptionUtil.create("Problems parsing default date string '" + paramString + "' using '" + localDateStringFormatConfig + '\'', localParseException);
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    Timestamp localTimestamp = paramDatabaseResults.getTimestamp(paramInt);
    if (localTimestamp == null);
    for (Date localDate = null; ; localDate = new Date(localTimestamp.getTime()))
      return localDate;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.DateType
 * JD-Core Version:    0.6.0
 */