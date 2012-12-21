package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.content.res.Resources;
import java.lang.reflect.Constructor;

@Deprecated
public class ClassNameProvidedOpenHelperFactory
  implements OpenHelperManager.SqliteOpenHelperFactory
{
  public OrmLiteSqliteOpenHelper getHelper(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("open_helper_classname", "string", paramContext.getPackageName());
    if (i == 0)
      throw new IllegalStateException("string resrouce open_helper_classname required");
    String str = paramContext.getResources().getString(i);
    try
    {
      Class localClass = Class.forName(str);
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Context.class;
      Constructor localConstructor = localClass.getConstructor(arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramContext;
      OrmLiteSqliteOpenHelper localOrmLiteSqliteOpenHelper = (OrmLiteSqliteOpenHelper)localConstructor.newInstance(arrayOfObject);
      return localOrmLiteSqliteOpenHelper;
    }
    catch (Exception localException)
    {
    }
    throw new IllegalStateException("Count not create helper instance for class " + str, localException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.apptools.ClassNameProvidedOpenHelperFactory
 * JD-Core Version:    0.6.0
 */