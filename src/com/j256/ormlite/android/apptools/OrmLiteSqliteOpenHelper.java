package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

public abstract class OrmLiteSqliteOpenHelper extends SQLiteOpenHelper
{
  private AndroidConnectionSource connectionSource = new AndroidConnectionSource(this);
  private volatile boolean isOpen = true;
  private Logger logger = LoggerFactory.getLogger(getClass());

  public OrmLiteSqliteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  public void close()
  {
    super.close();
    this.connectionSource.close();
    this.isOpen = false;
  }

  public ConnectionSource getConnectionSource()
  {
    if (!this.isOpen)
      this.logger.error(new IllegalStateException(), "Getting connectionSource called after closed", new Object[0]);
    return this.connectionSource;
  }

  public <D extends Dao<T, ?>, T> D getDao(Class<T> paramClass)
    throws SQLException
  {
    return DaoManager.createDao(getConnectionSource(), paramClass);
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  // ERROR //
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 62	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:getConnectionSource	()Lcom/j256/ormlite/support/ConnectionSource;
    //   4: astore_2
    //   5: aload_2
    //   6: invokeinterface 77 1 0
    //   11: astore_3
    //   12: iconst_0
    //   13: istore 4
    //   15: aload_3
    //   16: ifnonnull +24 -> 40
    //   19: new 79	com/j256/ormlite/android/AndroidDatabaseConnection
    //   22: dup
    //   23: aload_1
    //   24: iconst_1
    //   25: invokespecial 82	com/j256/ormlite/android/AndroidDatabaseConnection:<init>	(Landroid/database/sqlite/SQLiteDatabase;Z)V
    //   28: astore_3
    //   29: aload_2
    //   30: aload_3
    //   31: invokeinterface 86 2 0
    //   36: pop
    //   37: iconst_1
    //   38: istore 4
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: invokevirtual 89	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:onCreate	(Landroid/database/sqlite/SQLiteDatabase;Lcom/j256/ormlite/support/ConnectionSource;)V
    //   46: iload 4
    //   48: ifeq +10 -> 58
    //   51: aload_2
    //   52: aload_3
    //   53: invokeinterface 93 2 0
    //   58: return
    //   59: astore 5
    //   61: new 46	java/lang/IllegalStateException
    //   64: dup
    //   65: ldc 95
    //   67: aload 5
    //   69: invokespecial 98	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   72: athrow
    //   73: astore 7
    //   75: iload 4
    //   77: ifeq +10 -> 87
    //   80: aload_2
    //   81: aload_3
    //   82: invokeinterface 93 2 0
    //   87: aload 7
    //   89: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   29	37	59	java/sql/SQLException
    //   40	46	73	finally
  }

  public abstract void onCreate(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource);

  // ERROR //
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 62	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:getConnectionSource	()Lcom/j256/ormlite/support/ConnectionSource;
    //   4: astore 4
    //   6: aload 4
    //   8: invokeinterface 77 1 0
    //   13: astore 5
    //   15: iconst_0
    //   16: istore 6
    //   18: aload 5
    //   20: ifnonnull +27 -> 47
    //   23: new 79	com/j256/ormlite/android/AndroidDatabaseConnection
    //   26: dup
    //   27: aload_1
    //   28: iconst_1
    //   29: invokespecial 82	com/j256/ormlite/android/AndroidDatabaseConnection:<init>	(Landroid/database/sqlite/SQLiteDatabase;Z)V
    //   32: astore 5
    //   34: aload 4
    //   36: aload 5
    //   38: invokeinterface 86 2 0
    //   43: pop
    //   44: iconst_1
    //   45: istore 6
    //   47: aload_0
    //   48: aload_1
    //   49: aload 4
    //   51: iload_2
    //   52: iload_3
    //   53: invokevirtual 103	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:onUpgrade	(Landroid/database/sqlite/SQLiteDatabase;Lcom/j256/ormlite/support/ConnectionSource;II)V
    //   56: iload 6
    //   58: ifeq +12 -> 70
    //   61: aload 4
    //   63: aload 5
    //   65: invokeinterface 93 2 0
    //   70: return
    //   71: astore 7
    //   73: new 46	java/lang/IllegalStateException
    //   76: dup
    //   77: ldc 95
    //   79: aload 7
    //   81: invokespecial 98	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   84: athrow
    //   85: astore 9
    //   87: iload 6
    //   89: ifeq +12 -> 101
    //   92: aload 4
    //   94: aload 5
    //   96: invokeinterface 93 2 0
    //   101: aload 9
    //   103: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   34	44	71	java/sql/SQLException
    //   47	56	85	finally
  }

  public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
 * JD-Core Version:    0.6.0
 */