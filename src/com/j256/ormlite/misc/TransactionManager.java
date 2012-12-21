package com.j256.ormlite.misc;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManager
{
  private static final String SAVE_POINT_PREFIX = "ORMLITE";
  private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);
  private static AtomicInteger savePointCounter = new AtomicInteger();
  private ConnectionSource connectionSource;

  public TransactionManager()
  {
  }

  public TransactionManager(ConnectionSource paramConnectionSource)
  {
    this.connectionSource = paramConnectionSource;
    initialize();
  }

  public static <T> T callInTransaction(ConnectionSource paramConnectionSource, Callable<T> paramCallable)
    throws SQLException
  {
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadWriteConnection();
    try
    {
      Object localObject2 = callInTransaction(localDatabaseConnection, paramConnectionSource.saveSpecialConnection(localDatabaseConnection), paramConnectionSource.getDatabaseType(), paramCallable);
      return localObject2;
    }
    finally
    {
      paramConnectionSource.clearSpecialConnection(localDatabaseConnection);
      paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject1;
  }

  public static <T> T callInTransaction(DatabaseConnection paramDatabaseConnection, DatabaseType paramDatabaseType, Callable<T> paramCallable)
    throws SQLException
  {
    return callInTransaction(paramDatabaseConnection, false, paramDatabaseType, paramCallable);
  }

  // ERROR //
  public static <T> T callInTransaction(DatabaseConnection paramDatabaseConnection, boolean paramBoolean, DatabaseType paramDatabaseType, Callable<T> paramCallable)
    throws SQLException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore 5
    //   6: aconst_null
    //   7: astore 6
    //   9: iload_1
    //   10: ifne +12 -> 22
    //   13: aload_2
    //   14: invokeinterface 75 1 0
    //   19: ifeq +96 -> 115
    //   22: aload_0
    //   23: invokeinterface 80 1 0
    //   28: ifeq +35 -> 63
    //   31: aload_0
    //   32: invokeinterface 83 1 0
    //   37: istore 4
    //   39: iload 4
    //   41: ifeq +22 -> 63
    //   44: aload_0
    //   45: iconst_0
    //   46: invokeinterface 87 2 0
    //   51: getstatic 24	com/j256/ormlite/misc/TransactionManager:logger	Lcom/j256/ormlite/logger/Logger;
    //   54: ldc 89
    //   56: iconst_0
    //   57: anewarray 4	java/lang/Object
    //   60: invokevirtual 95	com/j256/ormlite/logger/Logger:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   63: aload_0
    //   64: new 97	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 98	java/lang/StringBuilder:<init>	()V
    //   71: ldc 8
    //   73: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: getstatic 31	com/j256/ormlite/misc/TransactionManager:savePointCounter	Ljava/util/concurrent/atomic/AtomicInteger;
    //   79: invokevirtual 106	java/util/concurrent/atomic/AtomicInteger:incrementAndGet	()I
    //   82: invokevirtual 109	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   85: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokeinterface 117 2 0
    //   93: astore 6
    //   95: aload 6
    //   97: ifnonnull +64 -> 161
    //   100: getstatic 24	com/j256/ormlite/misc/TransactionManager:logger	Lcom/j256/ormlite/logger/Logger;
    //   103: ldc 119
    //   105: iconst_0
    //   106: anewarray 4	java/lang/Object
    //   109: invokevirtual 95	com/j256/ormlite/logger/Logger:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   112: iconst_1
    //   113: istore 5
    //   115: aload_3
    //   116: invokeinterface 125 1 0
    //   121: astore 12
    //   123: iload 5
    //   125: ifeq +9 -> 134
    //   128: aload_0
    //   129: aload 6
    //   131: invokestatic 129	com/j256/ormlite/misc/TransactionManager:commit	(Lcom/j256/ormlite/support/DatabaseConnection;Ljava/sql/Savepoint;)V
    //   134: iload 4
    //   136: ifeq +22 -> 158
    //   139: aload_0
    //   140: iconst_1
    //   141: invokeinterface 87 2 0
    //   146: getstatic 24	com/j256/ormlite/misc/TransactionManager:logger	Lcom/j256/ormlite/logger/Logger;
    //   149: ldc 131
    //   151: iconst_0
    //   152: anewarray 4	java/lang/Object
    //   155: invokevirtual 95	com/j256/ormlite/logger/Logger:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   158: aload 12
    //   160: areturn
    //   161: getstatic 24	com/j256/ormlite/misc/TransactionManager:logger	Lcom/j256/ormlite/logger/Logger;
    //   164: astore 8
    //   166: iconst_1
    //   167: anewarray 4	java/lang/Object
    //   170: astore 9
    //   172: aload 9
    //   174: iconst_0
    //   175: aload 6
    //   177: invokeinterface 136 1 0
    //   182: aastore
    //   183: aload 8
    //   185: ldc 138
    //   187: aload 9
    //   189: invokevirtual 95	com/j256/ormlite/logger/Logger:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   192: goto -80 -> 112
    //   195: astore 7
    //   197: iload 4
    //   199: ifeq +22 -> 221
    //   202: aload_0
    //   203: iconst_1
    //   204: invokeinterface 87 2 0
    //   209: getstatic 24	com/j256/ormlite/misc/TransactionManager:logger	Lcom/j256/ormlite/logger/Logger;
    //   212: ldc 131
    //   214: iconst_0
    //   215: anewarray 4	java/lang/Object
    //   218: invokevirtual 95	com/j256/ormlite/logger/Logger:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   221: aload 7
    //   223: athrow
    //   224: astore 11
    //   226: iload 5
    //   228: ifeq +9 -> 237
    //   231: aload_0
    //   232: aload 6
    //   234: invokestatic 141	com/j256/ormlite/misc/TransactionManager:rollBack	(Lcom/j256/ormlite/support/DatabaseConnection;Ljava/sql/Savepoint;)V
    //   237: aload 11
    //   239: athrow
    //   240: astore 10
    //   242: iload 5
    //   244: ifeq +9 -> 253
    //   247: aload_0
    //   248: aload 6
    //   250: invokestatic 141	com/j256/ormlite/misc/TransactionManager:rollBack	(Lcom/j256/ormlite/support/DatabaseConnection;Ljava/sql/Savepoint;)V
    //   253: ldc 143
    //   255: aload 10
    //   257: invokestatic 149	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   260: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   13	112	195	finally
    //   115	134	195	finally
    //   161	192	195	finally
    //   231	261	195	finally
    //   115	134	224	java/sql/SQLException
    //   115	134	240	java/lang/Exception
  }

  private static void commit(DatabaseConnection paramDatabaseConnection, Savepoint paramSavepoint)
    throws SQLException
  {
    String str;
    if (paramSavepoint == null)
    {
      str = null;
      paramDatabaseConnection.commit(paramSavepoint);
      if (str != null)
        break label40;
      logger.debug("committed savePoint transaction", new Object[0]);
    }
    while (true)
    {
      return;
      str = paramSavepoint.getSavepointName();
      break;
      label40: Logger localLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str;
      localLogger.debug("committed savePoint transaction {}", arrayOfObject);
    }
  }

  private static void rollBack(DatabaseConnection paramDatabaseConnection, Savepoint paramSavepoint)
    throws SQLException
  {
    String str;
    if (paramSavepoint == null)
    {
      str = null;
      paramDatabaseConnection.rollback(paramSavepoint);
      if (str != null)
        break label40;
      logger.debug("rolled back savePoint transaction", new Object[0]);
    }
    while (true)
    {
      return;
      str = paramSavepoint.getSavepointName();
      break;
      label40: Logger localLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str;
      localLogger.debug("rolled back savePoint transaction {}", arrayOfObject);
    }
  }

  public <T> T callInTransaction(Callable<T> paramCallable)
    throws SQLException
  {
    return callInTransaction(this.connectionSource, paramCallable);
  }

  public void initialize()
  {
    if (this.connectionSource == null)
      throw new IllegalStateException("dataSource was not set on " + getClass().getSimpleName());
  }

  public void setConnectionSource(ConnectionSource paramConnectionSource)
  {
    this.connectionSource = paramConnectionSource;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.misc.TransactionManager
 * JD-Core Version:    0.6.0
 */