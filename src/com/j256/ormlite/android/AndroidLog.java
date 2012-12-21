package com.j256.ormlite.android;

import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.LoggerFactory;

public class AndroidLog
  implements com.j256.ormlite.logger.Log
{
  private static final String ALL_LOGS_NAME = "ORMLite";
  private static final int MAX_TAG_LENGTH = 23;
  private static final int REFRESH_LEVEL_CACHE_EVERY = 200;
  private String className;
  private final boolean[] levelCache;
  private volatile int levelCacheC = 0;

  public AndroidLog(String paramString)
  {
    this.className = LoggerFactory.getSimpleClassName(paramString);
    int i = this.className.length();
    if (i > 23)
      this.className = this.className.substring(i - 23, i);
    int j = 0;
    Log.Level[] arrayOfLevel = Log.Level.values();
    int k = arrayOfLevel.length;
    for (int m = 0; m < k; m++)
    {
      int n = levelToAndroidLevel(arrayOfLevel[m]);
      if (n <= j)
        continue;
      j = n;
    }
    this.levelCache = new boolean[j + 1];
    refreshLevelCache();
  }

  private boolean isLevelEnabledInternal(int paramInt)
  {
    if ((android.util.Log.isLoggable(this.className, paramInt)) || (android.util.Log.isLoggable("ORMLite", paramInt)));
    for (int i = 1; ; i = 0)
      return i;
  }

  private int levelToAndroidLevel(Log.Level paramLevel)
  {
    int i;
    switch (1.$SwitchMap$com$j256$ormlite$logger$Log$Level[paramLevel.ordinal()])
    {
    default:
      i = 4;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
      continue;
      i = 5;
      continue;
      i = 6;
      continue;
      i = 6;
    }
  }

  private void refreshLevelCache()
  {
    Log.Level[] arrayOfLevel = Log.Level.values();
    int i = arrayOfLevel.length;
    for (int j = 0; j < i; j++)
    {
      int k = levelToAndroidLevel(arrayOfLevel[j]);
      if (k >= this.levelCache.length)
        continue;
      this.levelCache[k] = isLevelEnabledInternal(k);
    }
  }

  public boolean isLevelEnabled(Log.Level paramLevel)
  {
    int i = 1 + this.levelCacheC;
    this.levelCacheC = i;
    if (i >= 200)
    {
      refreshLevelCache();
      this.levelCacheC = 0;
    }
    int j = levelToAndroidLevel(paramLevel);
    if (j < this.levelCache.length);
    boolean bool;
    for (int k = this.levelCache[j]; ; bool = isLevelEnabledInternal(j))
      return k;
  }

  public void log(Log.Level paramLevel, String paramString)
  {
    switch (1.$SwitchMap$com$j256$ormlite$logger$Log$Level[paramLevel.ordinal()])
    {
    default:
      android.util.Log.i(this.className, paramString);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return;
      android.util.Log.v(this.className, paramString);
      continue;
      android.util.Log.d(this.className, paramString);
      continue;
      android.util.Log.i(this.className, paramString);
      continue;
      android.util.Log.w(this.className, paramString);
      continue;
      android.util.Log.e(this.className, paramString);
      continue;
      android.util.Log.e(this.className, paramString);
    }
  }

  public void log(Log.Level paramLevel, String paramString, Throwable paramThrowable)
  {
    switch (1.$SwitchMap$com$j256$ormlite$logger$Log$Level[paramLevel.ordinal()])
    {
    default:
      android.util.Log.i(this.className, paramString, paramThrowable);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return;
      android.util.Log.v(this.className, paramString, paramThrowable);
      continue;
      android.util.Log.d(this.className, paramString, paramThrowable);
      continue;
      android.util.Log.i(this.className, paramString, paramThrowable);
      continue;
      android.util.Log.w(this.className, paramString, paramThrowable);
      continue;
      android.util.Log.e(this.className, paramString, paramThrowable);
      continue;
      android.util.Log.e(this.className, paramString, paramThrowable);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.AndroidLog
 * JD-Core Version:    0.6.0
 */