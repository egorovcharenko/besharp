package com.j256.ormlite.logger;

import java.lang.reflect.Constructor;

public class LoggerFactory
{
  private static LogType logType;

  private static LogType findLogType()
  {
    LogType[] arrayOfLogType = LogType.values();
    int i = arrayOfLogType.length;
    int j = 0;
    LogType localLogType2;
    if (j < i)
    {
      localLogType2 = arrayOfLogType[j];
      if (!localLogType2.isAvailable());
    }
    for (LogType localLogType1 = localLogType2; ; localLogType1 = LogType.LOCAL)
    {
      return localLogType1;
      j++;
      break;
    }
  }

  public static Logger getLogger(Class<?> paramClass)
  {
    return getLogger(paramClass.getName());
  }

  public static Logger getLogger(String paramString)
  {
    if (logType == null)
      logType = findLogType();
    return new Logger(logType.createLog(paramString));
  }

  public static String getSimpleClassName(String paramString)
  {
    String[] arrayOfString = paramString.split("\\.");
    if (arrayOfString.length == 0);
    for (String str = paramString; ; str = arrayOfString[(arrayOfString.length - 1)])
      return str;
  }

  private static enum LogType
  {
    private final String detectClassName;
    private final String logClassName;

    static
    {
      LOCAL = new LoggerFactory.LogType.1("LOCAL", 3, "com.j256.ormlite.logger.LocalLog", "com.j256.ormlite.logger.LocalLog");
      LogType[] arrayOfLogType = new LogType[4];
      arrayOfLogType[0] = ANDROID;
      arrayOfLogType[1] = COMMONS_LOGGING;
      arrayOfLogType[2] = LOG4J;
      arrayOfLogType[3] = LOCAL;
      $VALUES = arrayOfLogType;
    }

    private LogType(String paramString1, String paramString2)
    {
      this.detectClassName = paramString1;
      this.logClassName = paramString2;
    }

    public Log createLog(String paramString)
    {
      try
      {
        Class localClass = Class.forName(this.logClassName);
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = String.class;
        Constructor localConstructor = localClass.getConstructor(arrayOfClass);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramString;
        Log localLog = (Log)localConstructor.newInstance(arrayOfObject);
        localObject = localLog;
        return localObject;
      }
      catch (Exception localException)
      {
        while (true)
        {
          LocalLog localLocalLog = new LocalLog(paramString);
          localLocalLog.log(Log.Level.WARNING, "Unable to call constructor for class " + this.logClassName + ", so had to use local log", localException);
          Object localObject = localLocalLog;
        }
      }
    }

    public boolean isAvailable()
    {
      try
      {
        Class.forName(this.detectClassName);
        i = 1;
        return i;
      }
      catch (Exception localException)
      {
        while (true)
          int i = 0;
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.logger.LoggerFactory
 * JD-Core Version:    0.6.0
 */