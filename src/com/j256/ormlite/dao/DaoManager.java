package com.j256.ormlite.dao;

import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoManager
{
  private static Map<ClazzConnectionSource, Dao<?, ?>> classMap = null;
  private static Map<TableConfigConnectionSource, Dao<?, ?>> tableMap = null;

  public static void clearCache()
  {
    monitorenter;
    try
    {
      if (classMap != null)
      {
        classMap.clear();
        classMap = null;
      }
      if (tableMap != null)
      {
        tableMap.clear();
        tableMap = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public static <D extends Dao<T, ?>, T> D createDao(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnonnull +21 -> 25
    //   7: new 38	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc 40
    //   13: invokespecial 43	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: astore 8
    //   19: ldc 2
    //   21: monitorexit
    //   22: aload 8
    //   24: athrow
    //   25: new 9	com/j256/ormlite/dao/DaoManager$TableConfigConnectionSource
    //   28: dup
    //   29: aload_0
    //   30: aload_1
    //   31: invokespecial 46	com/j256/ormlite/dao/DaoManager$TableConfigConnectionSource:<init>	(Lcom/j256/ormlite/support/ConnectionSource;Lcom/j256/ormlite/table/DatabaseTableConfig;)V
    //   34: astore_2
    //   35: aload_2
    //   36: invokestatic 50	com/j256/ormlite/dao/DaoManager:lookupDao	(Lcom/j256/ormlite/dao/DaoManager$TableConfigConnectionSource;)Lcom/j256/ormlite/dao/Dao;
    //   39: astore_3
    //   40: aload_3
    //   41: ifnull +12 -> 53
    //   44: aload_3
    //   45: astore 7
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload 7
    //   52: areturn
    //   53: aload_1
    //   54: invokevirtual 56	com/j256/ormlite/table/DatabaseTableConfig:getDataClass	()Ljava/lang/Class;
    //   57: ldc 58
    //   59: invokevirtual 64	java/lang/Class:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   62: checkcast 58	com/j256/ormlite/table/DatabaseTable
    //   65: astore 4
    //   67: aload 4
    //   69: ifnull +27 -> 96
    //   72: aload 4
    //   74: invokeinterface 67 1 0
    //   79: ldc 69
    //   81: if_acmpeq +15 -> 96
    //   84: aload 4
    //   86: invokeinterface 67 1 0
    //   91: ldc 71
    //   93: if_acmpne +29 -> 122
    //   96: aload_0
    //   97: aload_1
    //   98: invokestatic 73	com/j256/ormlite/dao/BaseDaoImpl:createDao	(Lcom/j256/ormlite/support/ConnectionSource;Lcom/j256/ormlite/table/DatabaseTableConfig;)Lcom/j256/ormlite/dao/Dao;
    //   101: astore 5
    //   103: getstatic 21	com/j256/ormlite/dao/DaoManager:tableMap	Ljava/util/Map;
    //   106: aload_2
    //   107: aload 5
    //   109: invokeinterface 77 3 0
    //   114: pop
    //   115: aload 5
    //   117: astore 7
    //   119: goto -72 -> 47
    //   122: aload 4
    //   124: invokeinterface 67 1 0
    //   129: astore 9
    //   131: iconst_2
    //   132: anewarray 60	java/lang/Class
    //   135: astore 11
    //   137: aload 11
    //   139: iconst_0
    //   140: ldc 79
    //   142: aastore
    //   143: aload 11
    //   145: iconst_1
    //   146: ldc 52
    //   148: aastore
    //   149: aload 9
    //   151: aload 11
    //   153: invokevirtual 83	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   156: astore 12
    //   158: iconst_2
    //   159: anewarray 4	java/lang/Object
    //   162: astore 14
    //   164: aload 14
    //   166: iconst_0
    //   167: aload_0
    //   168: aastore
    //   169: aload 14
    //   171: iconst_1
    //   172: aload_1
    //   173: aastore
    //   174: aload 12
    //   176: aload 14
    //   178: invokevirtual 89	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   181: checkcast 91	com/j256/ormlite/dao/Dao
    //   184: astore 5
    //   186: goto -83 -> 103
    //   189: astore 10
    //   191: new 93	java/lang/StringBuilder
    //   194: dup
    //   195: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   198: ldc 96
    //   200: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload 9
    //   205: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: aload 10
    //   213: invokestatic 113	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   216: athrow
    //   217: astore 13
    //   219: new 93	java/lang/StringBuilder
    //   222: dup
    //   223: invokespecial 94	java/lang/StringBuilder:<init>	()V
    //   226: ldc 115
    //   228: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: aload 9
    //   233: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   236: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: aload 13
    //   241: invokestatic 113	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   244: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	17	17	finally
    //   25	40	17	finally
    //   53	131	17	finally
    //   131	158	17	finally
    //   158	186	17	finally
    //   191	245	17	finally
    //   131	158	189	java/lang/Exception
    //   158	186	217	java/lang/Exception
  }

  public static <D extends Dao<T, ?>, T> D createDao(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    monitorenter;
    if (paramConnectionSource == null)
      try
      {
        throw new IllegalArgumentException("connectionSource argument cannot be null");
      }
      finally
      {
        monitorexit;
      }
    ClazzConnectionSource localClazzConnectionSource = new ClazzConnectionSource(paramConnectionSource, paramClass);
    Dao localDao1 = lookupDao(localClazzConnectionSource);
    if (localDao1 != null);
    DatabaseTable localDatabaseTable;
    Object localObject1;
    for (Object localObject2 = localDao1; ; localObject2 = localObject1)
    {
      monitorexit;
      return localObject2;
      localDatabaseTable = (DatabaseTable)paramClass.getAnnotation(DatabaseTable.class);
      if ((localDatabaseTable != null) && (localDatabaseTable.daoClass() != Void.class) && (localDatabaseTable.daoClass() != BaseDaoImpl.class))
        break;
      localObject1 = BaseDaoImpl.createDao(paramConnectionSource, paramClass);
      classMap.put(localClazzConnectionSource, localObject1);
    }
    Class localClass = localDatabaseTable.daoClass();
    Constructor[] arrayOfConstructor = localClass.getConstructors();
    int i = arrayOfConstructor.length;
    int j = 0;
    Constructor localConstructor1;
    Object[] arrayOfObject1;
    if (j < i)
    {
      Constructor localConstructor4 = arrayOfConstructor[j];
      Class[] arrayOfClass2 = localConstructor4.getParameterTypes();
      if ((arrayOfClass2.length == 2) && (arrayOfClass2[0] == ConnectionSource.class) && (arrayOfClass2[1] == Class.class))
      {
        localConstructor1 = localConstructor4;
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = paramConnectionSource;
        arrayOfObject4[1] = paramClass;
        arrayOfObject1 = arrayOfObject4;
      }
    }
    while (true)
    {
      int m;
      label226: Object[] arrayOfObject2;
      Constructor localConstructor2;
      if (localConstructor1 == null)
      {
        int k = arrayOfConstructor.length;
        m = 0;
        if (m < k)
        {
          Constructor localConstructor3 = arrayOfConstructor[m];
          Class[] arrayOfClass1 = localConstructor3.getParameterTypes();
          if ((arrayOfClass1.length == 1) && (arrayOfClass1[0] == ConnectionSource.class))
          {
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = paramConnectionSource;
            arrayOfObject2 = arrayOfObject3;
            localConstructor2 = localConstructor3;
          }
        }
      }
      while (true)
      {
        while (true)
        {
          if (localConstructor2 == null)
          {
            throw new SQLException("Could not find public constructor with ConnectionSource parameter in class " + localClass);
            j++;
            break;
            m++;
            break label226;
          }
          try
          {
            Dao localDao2 = (Dao)localConstructor2.newInstance(arrayOfObject2);
            localObject1 = localDao2;
          }
          catch (Exception localException)
          {
            throw SqlExceptionUtil.create("Could not call the constructor in class " + localClass, localException);
          }
        }
        arrayOfObject2 = arrayOfObject1;
        localConstructor2 = localConstructor1;
        continue;
        arrayOfObject2 = arrayOfObject1;
        localConstructor2 = localConstructor1;
      }
      arrayOfObject1 = null;
      localConstructor1 = null;
    }
  }

  private static <T> Dao<?, ?> lookupDao(ClazzConnectionSource paramClazzConnectionSource)
  {
    if (classMap == null)
      classMap = new HashMap();
    Dao localDao1 = (Dao)classMap.get(paramClazzConnectionSource);
    if (localDao1 == null);
    for (Dao localDao2 = null; ; localDao2 = localDao1)
      return localDao2;
  }

  private static <T> Dao<?, ?> lookupDao(TableConfigConnectionSource paramTableConfigConnectionSource)
  {
    if (tableMap == null)
      tableMap = new HashMap();
    Dao localDao1 = (Dao)tableMap.get(paramTableConfigConnectionSource);
    if (localDao1 == null);
    for (Dao localDao2 = null; ; localDao2 = localDao1)
      return localDao2;
  }

  public static <D extends Dao<T, ?>, T> D lookupDao(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    monitorenter;
    if (paramConnectionSource == null)
      try
      {
        throw new IllegalArgumentException("connectionSource argument cannot be null");
      }
      finally
      {
        monitorexit;
      }
    Dao localDao1 = lookupDao(new TableConfigConnectionSource(paramConnectionSource, paramDatabaseTableConfig));
    if (localDao1 == null);
    for (Dao localDao2 = null; ; localDao2 = localDao1)
    {
      monitorexit;
      return localDao2;
    }
  }

  public static <D extends Dao<T, ?>, T> D lookupDao(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    monitorenter;
    if (paramConnectionSource == null)
      try
      {
        throw new IllegalArgumentException("connectionSource argument cannot be null");
      }
      finally
      {
        monitorexit;
      }
    Dao localDao1 = lookupDao(new ClazzConnectionSource(paramConnectionSource, paramClass));
    if (localDao1 == null);
    for (Dao localDao2 = null; ; localDao2 = localDao1)
    {
      monitorexit;
      return localDao2;
    }
  }

  public static void registerDao(ConnectionSource paramConnectionSource, Dao<?, ?> paramDao)
  {
    monitorenter;
    if (paramConnectionSource == null)
      try
      {
        throw new IllegalArgumentException("connectionSource argument cannot be null");
      }
      finally
      {
        monitorexit;
      }
    if ((paramDao instanceof BaseDaoImpl))
    {
      DatabaseTableConfig localDatabaseTableConfig = ((BaseDaoImpl)paramDao).getTableConfig();
      if (localDatabaseTableConfig != null)
        tableMap.put(new TableConfigConnectionSource(paramConnectionSource, localDatabaseTableConfig), paramDao);
    }
    while (true)
    {
      monitorexit;
      return;
      classMap.put(new ClazzConnectionSource(paramConnectionSource, paramDao.getDataClass()), paramDao);
    }
  }

  private static class ClazzConnectionSource
  {
    Class<?> clazz;
    ConnectionSource connectionSource;

    public ClazzConnectionSource(ConnectionSource paramConnectionSource, Class<?> paramClass)
    {
      this.connectionSource = paramConnectionSource;
      this.clazz = paramClass;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
        i = 0;
      while (true)
      {
        return i;
        ClazzConnectionSource localClazzConnectionSource = (ClazzConnectionSource)paramObject;
        if (!this.clazz.equals(localClazzConnectionSource.clazz))
        {
          i = 0;
          continue;
        }
        if (!this.connectionSource.equals(localClazzConnectionSource.connectionSource))
        {
          i = 0;
          continue;
        }
        i = 1;
      }
    }

    public int hashCode()
    {
      return 31 * (31 + this.clazz.hashCode()) + this.connectionSource.hashCode();
    }
  }

  private static class TableConfigConnectionSource
  {
    ConnectionSource connectionSource;
    DatabaseTableConfig<?> tableConfig;

    public TableConfigConnectionSource(ConnectionSource paramConnectionSource, DatabaseTableConfig<?> paramDatabaseTableConfig)
    {
      this.connectionSource = paramConnectionSource;
      this.tableConfig = paramDatabaseTableConfig;
    }

    public boolean equals(Object paramObject)
    {
      int i;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
        i = 0;
      while (true)
      {
        return i;
        TableConfigConnectionSource localTableConfigConnectionSource = (TableConfigConnectionSource)paramObject;
        if (!this.tableConfig.equals(localTableConfigConnectionSource.tableConfig))
        {
          i = 0;
          continue;
        }
        if (!this.connectionSource.equals(localTableConfigConnectionSource.connectionSource))
        {
          i = 0;
          continue;
        }
        i = 1;
      }
    }

    public int hashCode()
    {
      return 31 * (31 + this.tableConfig.hashCode()) + this.connectionSource.hashCode();
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.DaoManager
 * JD-Core Version:    0.6.0
 */