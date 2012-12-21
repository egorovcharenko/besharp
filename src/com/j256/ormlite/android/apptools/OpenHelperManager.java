package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.content.res.Resources;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OpenHelperManager
{
  private static final String HELPER_CLASS_RESOURCE_NAME = "open_helper_classname";
  private static SqliteOpenHelperFactory factory;
  private static volatile OrmLiteSqliteOpenHelper helper;
  private static Class<? extends OrmLiteSqliteOpenHelper> helperClass;
  private static int instanceCount;
  private static Logger logger = LoggerFactory.getLogger(OpenHelperManager.class);
  private static boolean wasClosed;

  static
  {
    factory = null;
    helperClass = null;
    helper = null;
    wasClosed = false;
    instanceCount = 0;
  }

  // ERROR //
  private static OrmLiteSqliteOpenHelper constructHelper(Class<? extends OrmLiteSqliteOpenHelper> paramClass, Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray 53	java/lang/Class
    //   4: astore_3
    //   5: aload_3
    //   6: iconst_0
    //   7: ldc 55
    //   9: aastore
    //   10: aload_0
    //   11: aload_3
    //   12: invokevirtual 59	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   15: astore 4
    //   17: iconst_1
    //   18: anewarray 4	java/lang/Object
    //   21: astore 6
    //   23: aload 6
    //   25: iconst_0
    //   26: aload_1
    //   27: aastore
    //   28: aload 4
    //   30: aload 6
    //   32: invokevirtual 65	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   35: checkcast 67	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper
    //   38: astore 7
    //   40: aload 7
    //   42: areturn
    //   43: astore_2
    //   44: new 69	java/lang/IllegalStateException
    //   47: dup
    //   48: new 71	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 72	java/lang/StringBuilder:<init>	()V
    //   55: ldc 74
    //   57: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: aload_0
    //   61: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: aload_2
    //   68: invokespecial 88	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   71: athrow
    //   72: astore 5
    //   74: new 69	java/lang/IllegalStateException
    //   77: dup
    //   78: new 71	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 72	java/lang/StringBuilder:<init>	()V
    //   85: ldc 90
    //   87: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: aload_0
    //   91: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: aload 5
    //   99: invokespecial 88	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   102: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	17	43	java/lang/Exception
    //   17	40	72	java/lang/Exception
  }

  public static OrmLiteSqliteOpenHelper getHelper(Context paramContext)
  {
    monitorenter;
    try
    {
      if (helper != null)
        break label89;
      if (paramContext == null)
        throw new IllegalArgumentException("context argument is null");
    }
    finally
    {
      monitorexit;
    }
    if (wasClosed)
      logger.info("helper has already been closed and is being re-opened.", new Object[0]);
    Context localContext = paramContext.getApplicationContext();
    if (factory == null)
    {
      if (helperClass == null)
        innerSetHelperClass(lookupHelperClass(localContext, paramContext.getClass()));
      helper = constructHelper(helperClass, localContext);
    }
    while (true)
    {
      instanceCount = 0;
      label89: instanceCount = 1 + instanceCount;
      OrmLiteSqliteOpenHelper localOrmLiteSqliteOpenHelper = helper;
      monitorexit;
      return localOrmLiteSqliteOpenHelper;
      helper = factory.getHelper(localContext);
    }
  }

  public static OrmLiteSqliteOpenHelper getHelper(Context paramContext, Class<? extends OrmLiteSqliteOpenHelper> paramClass)
  {
    if (helper == null)
      innerSetHelperClass(paramClass);
    return getHelper(paramContext);
  }

  private static void innerSetHelperClass(Class<? extends OrmLiteSqliteOpenHelper> paramClass)
  {
    if (helperClass == null)
      helperClass = paramClass;
    do
      return;
    while (helperClass == paramClass);
    throw new IllegalStateException("Helper class was " + helperClass + " but is trying to be reset to " + paramClass);
  }

  private static Class<? extends OrmLiteSqliteOpenHelper> lookupHelperClass(Context paramContext, Class<?> paramClass)
  {
    int i = paramContext.getResources().getIdentifier("open_helper_classname", "string", paramContext.getPackageName());
    String str;
    if (i != 0)
      str = paramContext.getResources().getString(i);
    while (true)
    {
      try
      {
        Class localClass2 = Class.forName(str);
        localObject = localClass2;
        return localObject;
      }
      catch (Exception localException)
      {
        throw new IllegalStateException("Could not create helper instance for class " + str, localException);
      }
      if (paramClass == null)
        break;
      Type localType1 = paramClass.getGenericSuperclass();
      if ((localType1 == null) || (!(localType1 instanceof ParameterizedType)));
      Type[] arrayOfType;
      do
      {
        paramClass = paramClass.getSuperclass();
        break;
        arrayOfType = ((ParameterizedType)localType1).getActualTypeArguments();
      }
      while ((arrayOfType == null) || (arrayOfType.length == 0));
      int j = arrayOfType.length;
      int k = 0;
      label134: Type localType2;
      if (k < j)
      {
        localType2 = arrayOfType[k];
        if ((localType2 instanceof Class))
          break label162;
      }
      label162: Class localClass1;
      do
      {
        k++;
        break label134;
        break;
        localClass1 = (Class)localType2;
      }
      while (!OrmLiteSqliteOpenHelper.class.isAssignableFrom(localClass1));
      Object localObject = localClass1;
    }
    throw new IllegalStateException("Could not find OpenHelperClass because none of its generic parameters extends OrmLiteSqliteOpenHelper: " + paramClass);
  }

  @Deprecated
  public static void release()
  {
    releaseHelper();
  }

  public static void releaseHelper()
  {
    monitorenter;
    try
    {
      instanceCount -= 1;
      if (instanceCount == 0)
        if (helper != null)
        {
          helper.close();
          helper = null;
          wasClosed = true;
        }
      do
        return;
      while (instanceCount >= 0);
      throw new IllegalStateException("Too many calls to release helper.  Instance count = " + instanceCount);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void setOpenHelperClass(Class<? extends OrmLiteSqliteOpenHelper> paramClass)
  {
    innerSetHelperClass(paramClass);
  }

  @Deprecated
  public static void setOpenHelperFactory(SqliteOpenHelperFactory paramSqliteOpenHelperFactory)
  {
    factory = paramSqliteOpenHelperFactory;
  }

  @Deprecated
  public static abstract interface SqliteOpenHelperFactory
  {
    public abstract OrmLiteSqliteOpenHelper getHelper(Context paramContext);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.apptools.OpenHelperManager
 * JD-Core Version:    0.6.0
 */