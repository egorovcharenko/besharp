package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseDateType extends BaseDataType
{
  public static final DateStringFormatConfig defaultDateFormatConfig = new DateStringFormatConfig("yyyy-MM-dd HH:mm:ss.SSSSSS");

  protected BaseDateType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  protected static DateStringFormatConfig convertDateStringConfig(FieldType paramFieldType)
  {
    Object localObject;
    if (paramFieldType == null)
      localObject = defaultDateFormatConfig;
    while (true)
    {
      return localObject;
      DateStringFormatConfig localDateStringFormatConfig = (DateStringFormatConfig)paramFieldType.getDataTypeConfigObj();
      if (localDateStringFormatConfig == null)
      {
        localObject = defaultDateFormatConfig;
        continue;
      }
      localObject = localDateStringFormatConfig;
    }
  }

  protected static String formatDate(DateStringFormatConfig paramDateStringFormatConfig, Date paramDate)
  {
    return paramDateStringFormatConfig.getDateFormat().format(paramDate);
  }

  protected static String normalizeDateString(DateStringFormatConfig paramDateStringFormatConfig, String paramString)
    throws ParseException
  {
    DateFormat localDateFormat = paramDateStringFormatConfig.getDateFormat();
    return localDateFormat.format(localDateFormat.parse(paramString));
  }

  protected static Date parseDateString(DateStringFormatConfig paramDateStringFormatConfig, String paramString)
    throws ParseException
  {
    return paramDateStringFormatConfig.getDateFormat().parse(paramString);
  }

  protected static class DateStringFormatConfig
  {
    final String dateFormatStr;
    private final ThreadLocal<DateFormat> threadLocal = new ThreadLocal();

    public DateStringFormatConfig(String paramString)
    {
      this.dateFormatStr = paramString;
    }

    public DateFormat getDateFormat()
    {
      Object localObject = (DateFormat)this.threadLocal.get();
      if (localObject == null)
      {
        localObject = new SimpleDateFormat(this.dateFormatStr);
        this.threadLocal.set(localObject);
      }
      return (DateFormat)localObject;
    }

    public String toString()
    {
      return this.dateFormatStr;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.BaseDateType
 * JD-Core Version:    0.6.0
 */