package com.j256.ormlite.logger;

import java.util.Arrays;

public class Logger
{
  private static final String ARG_STRING = "{}";
  private static final int ARG_STRING_LENGTH = "{}".length();
  private final Log log;

  public Logger(Log paramLog)
  {
    this.log = paramLog;
  }

  private String buildFullMessage(String paramString, Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    int i = 0;
    int j = 0;
    do
    {
      int k = paramString.indexOf("{}", i);
      if (k == -1)
      {
        localStringBuilder.append(paramString.substring(i));
        return localStringBuilder.toString();
      }
      localStringBuilder.append(paramString.substring(i, k));
      i = k + ARG_STRING_LENGTH;
    }
    while (j >= paramArrayOfObject.length);
    Object localObject = paramArrayOfObject[j];
    if ((localObject != null) && (localObject.getClass().isArray()))
      localStringBuilder.append(Arrays.toString((Object[])(Object[])localObject));
    while (true)
    {
      j++;
      break;
      localStringBuilder.append(localObject);
    }
  }

  private void log(Log.Level paramLevel, Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    String str;
    if (this.log.isLevelEnabled(paramLevel))
    {
      str = buildFullMessage(paramString, paramArrayOfObject);
      if (paramThrowable != null)
        break label39;
      this.log.log(paramLevel, str);
    }
    while (true)
    {
      return;
      label39: this.log.log(paramLevel, str, paramThrowable);
    }
  }

  public void debug(String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.DEBUG, null, paramString, paramArrayOfObject);
  }

  public void debug(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.DEBUG, paramThrowable, paramString, paramArrayOfObject);
  }

  public void error(String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.ERROR, null, paramString, paramArrayOfObject);
  }

  public void error(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.ERROR, paramThrowable, paramString, paramArrayOfObject);
  }

  public void fatal(String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.FATAL, null, paramString, paramArrayOfObject);
  }

  public void fatal(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.FATAL, paramThrowable, paramString, paramArrayOfObject);
  }

  public void info(String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.INFO, null, paramString, paramArrayOfObject);
  }

  public void info(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.INFO, paramThrowable, paramString, paramArrayOfObject);
  }

  public boolean isLevelEnabled(Log.Level paramLevel)
  {
    return this.log.isLevelEnabled(paramLevel);
  }

  public void trace(String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.TRACE, null, paramString, paramArrayOfObject);
  }

  public void trace(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.TRACE, paramThrowable, paramString, paramArrayOfObject);
  }

  public void warn(String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.WARNING, null, paramString, paramArrayOfObject);
  }

  public void warn(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(Log.Level.WARNING, paramThrowable, paramString, paramArrayOfObject);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.logger.Logger
 * JD-Core Version:    0.6.0
 */