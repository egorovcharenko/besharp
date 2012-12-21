package com.j256.ormlite.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalLog
  implements Log
{
  private static final Log.Level DEFAULT_LEVEL = Log.Level.DEBUG;
  public static final String LOCAL_LOG_FILE_PROPERTY = "com.j256.ormlite.logger.file";
  public static final String LOCAL_LOG_LEVEL_PROPERTY = "com.j256.ormlite.logger.level";
  private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal();
  private final String className;
  private final Log.Level level;
  private final PrintStream printStream;

  public LocalLog(String paramString)
  {
    this.className = LoggerFactory.getSimpleClassName(paramString);
    String str1 = System.getProperty("com.j256.ormlite.logger.level");
    String str2;
    if (str1 == null)
    {
      this.level = DEFAULT_LEVEL;
      str2 = System.getProperty("com.j256.ormlite.logger.file");
      if (str2 != null)
        break label101;
      this.printStream = System.out;
    }
    while (true)
    {
      while (true)
      {
        return;
        try
        {
          Log.Level localLevel = Log.Level.valueOf(str1.toUpperCase());
          this.level = localLevel;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw new IllegalArgumentException("Level '" + str1 + "' was not found", localIllegalArgumentException);
        }
      }
      try
      {
        label101: this.printStream = new PrintStream(new File(str2));
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
      }
    }
    throw new IllegalArgumentException("Log file " + str2 + " was not found", localFileNotFoundException);
  }

  private void printMessage(Log.Level paramLevel, String paramString, Throwable paramThrowable)
  {
    if (!isLevelEnabled(paramLevel));
    while (true)
    {
      return;
      StringBuilder localStringBuilder = new StringBuilder(128);
      Object localObject = (DateFormat)dateFormatThreadLocal.get();
      if (localObject == null)
      {
        localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        dateFormatThreadLocal.set(localObject);
      }
      localStringBuilder.append(((DateFormat)localObject).format(new Date()));
      localStringBuilder.append(" [").append(paramLevel.name()).append("] ");
      localStringBuilder.append(this.className).append(' ');
      localStringBuilder.append(paramString);
      this.printStream.println(localStringBuilder.toString());
      if (paramThrowable == null)
        continue;
      paramThrowable.printStackTrace(this.printStream);
    }
  }

  void flush()
  {
    this.printStream.flush();
  }

  public boolean isLevelEnabled(Log.Level paramLevel)
  {
    return this.level.isEnabled(paramLevel);
  }

  public void log(Log.Level paramLevel, String paramString)
  {
    printMessage(paramLevel, paramString, null);
  }

  public void log(Log.Level paramLevel, String paramString, Throwable paramThrowable)
  {
    printMessage(paramLevel, paramString, paramThrowable);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.logger.LocalLog
 * JD-Core Version:    0.6.0
 */