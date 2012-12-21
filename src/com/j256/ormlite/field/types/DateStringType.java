package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class DateStringType extends BaseDateType
{
  public static int DEFAULT_WIDTH = 50;
  private static final DateStringType singleTon = new DateStringType();

  private DateStringType()
  {
    super(SqlType.STRING, new Class[0]);
  }

  public static DateStringType getSingleton()
  {
    return singleTon;
  }

  public int getDefaultWidth()
  {
    return DEFAULT_WIDTH;
  }

  public boolean isValidForField(Field paramField)
  {
    return true;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
  {
    Date localDate = (Date)paramObject;
    return formatDate(convertDateStringConfig(paramFieldType), localDate);
  }

  public Object makeConfigObject(FieldType paramFieldType)
  {
    String str = paramFieldType.getFormat();
    if (str == null);
    for (BaseDateType.DateStringFormatConfig localDateStringFormatConfig = defaultDateFormatConfig; ; localDateStringFormatConfig = new BaseDateType.DateStringFormatConfig(str))
      return localDateStringFormatConfig;
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    BaseDateType.DateStringFormatConfig localDateStringFormatConfig = convertDateStringConfig(paramFieldType);
    try
    {
      String str = normalizeDateString(localDateStringFormatConfig, paramString);
      return str;
    }
    catch (ParseException localParseException)
    {
    }
    throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " parsing default date-string '" + paramString + "' using '" + localDateStringFormatConfig + "'", localParseException);
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    String str = paramDatabaseResults.getString(paramInt);
    Object localObject;
    if (str == null)
      localObject = null;
    BaseDateType.DateStringFormatConfig localDateStringFormatConfig;
    while (true)
    {
      return localObject;
      localDateStringFormatConfig = convertDateStringConfig(paramFieldType);
      try
      {
        Date localDate = parseDateString(localDateStringFormatConfig, str);
        localObject = localDate;
      }
      catch (ParseException localParseException)
      {
      }
    }
    throw SqlExceptionUtil.create("Problems with column " + paramInt + " parsing date-string '" + str + "' using '" + localDateStringFormatConfig + "'", localParseException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.DateStringType
 * JD-Core Version:    0.6.0
 */