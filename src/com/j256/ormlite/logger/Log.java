package com.j256.ormlite.logger;

public abstract interface Log
{
  public abstract boolean isLevelEnabled(Level paramLevel);

  public abstract void log(Level paramLevel, String paramString);

  public abstract void log(Level paramLevel, String paramString, Throwable paramThrowable);

  public static enum Level
  {
    private int level;

    static
    {
      DEBUG = new Level("DEBUG", 1, 2);
      INFO = new Level("INFO", 2, 3);
      WARNING = new Level("WARNING", 3, 4);
      ERROR = new Level("ERROR", 4, 5);
      FATAL = new Level("FATAL", 5, 6);
      Level[] arrayOfLevel = new Level[6];
      arrayOfLevel[0] = TRACE;
      arrayOfLevel[1] = DEBUG;
      arrayOfLevel[2] = INFO;
      arrayOfLevel[3] = WARNING;
      arrayOfLevel[4] = ERROR;
      arrayOfLevel[5] = FATAL;
      $VALUES = arrayOfLevel;
    }

    private Level(int paramInt)
    {
      this.level = paramInt;
    }

    public boolean isEnabled(Level paramLevel)
    {
      if (this.level <= paramLevel.level);
      for (int i = 1; ; i = 0)
        return i;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.logger.Log
 * JD-Core Version:    0.6.0
 */