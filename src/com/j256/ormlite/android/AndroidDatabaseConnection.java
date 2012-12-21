package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import java.sql.Savepoint;

public class AndroidDatabaseConnection
  implements DatabaseConnection
{
  private final SQLiteDatabase db;
  private final boolean readWrite;

  public AndroidDatabaseConnection(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    this.db = paramSQLiteDatabase;
    this.readWrite = paramBoolean;
  }

  private void bindArgs(SQLiteStatement paramSQLiteStatement, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws java.sql.SQLException
  {
    if (paramArrayOfObject == null)
      return;
    int i = 0;
    label8: Object localObject;
    if (i < paramArrayOfObject.length)
    {
      localObject = paramArrayOfObject[i];
      if (localObject != null)
        break label40;
      paramSQLiteStatement.bindNull(i + 1);
    }
    while (true)
    {
      i++;
      break label8;
      break;
      label40: switch (1.$SwitchMap$com$j256$ormlite$field$SqlType[paramArrayOfFieldType[i].getSqlType().ordinal()])
      {
      default:
        throw new java.sql.SQLException("Unknown sql argument type " + paramArrayOfFieldType[i].getSqlType());
      case 1:
      case 2:
      case 3:
        paramSQLiteStatement.bindString(i + 1, localObject.toString());
        break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
        paramSQLiteStatement.bindLong(i + 1, ((Number)localObject).longValue());
        break;
      case 9:
      case 10:
        paramSQLiteStatement.bindDouble(i + 1, ((Number)localObject).doubleValue());
        break;
      case 11:
      case 12:
        paramSQLiteStatement.bindBlob(i + 1, (byte[])(byte[])localObject);
      }
    }
  }

  private String[] toStrings(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null);
    String[] arrayOfString;
    for (Object localObject1 = null; ; localObject1 = arrayOfString)
    {
      return localObject1;
      arrayOfString = new String[paramArrayOfObject.length];
      int i = 0;
      if (i >= paramArrayOfObject.length)
        continue;
      Object localObject2 = paramArrayOfObject[i];
      if (localObject2 == null)
        arrayOfString[i] = null;
      while (true)
      {
        i++;
        break;
        arrayOfString[i] = localObject2.toString();
      }
    }
  }

  public void close()
    throws java.sql.SQLException
  {
    try
    {
      this.db.close();
      return;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("problems closing the database connection", localSQLException);
  }

  public void commit(Savepoint paramSavepoint)
    throws java.sql.SQLException
  {
    try
    {
      this.db.setTransactionSuccessful();
      this.db.endTransaction();
      return;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("problems commiting transaction", localSQLException);
  }

  public CompiledStatement compileStatement(String paramString, StatementBuilder.StatementType paramStatementType, FieldType[] paramArrayOfFieldType)
  {
    return new AndroidCompiledStatement(paramString, this.db, paramStatementType);
  }

  public int delete(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws java.sql.SQLException
  {
    return update(paramString, paramArrayOfObject, paramArrayOfFieldType);
  }

  public boolean getAutoCommit()
    throws java.sql.SQLException
  {
    try
    {
      boolean bool = this.db.inTransaction();
      if (!bool);
      for (int i = 1; ; i = 0)
        return i;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("problems getting auto-commit from database", localSQLException);
  }

  public int insert(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws java.sql.SQLException
  {
    return insert(paramString, paramArrayOfObject, paramArrayOfFieldType, null);
  }

  public int insert(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType, GeneratedKeyHolder paramGeneratedKeyHolder)
    throws java.sql.SQLException
  {
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = this.db.compileStatement(paramString);
      bindArgs(localSQLiteStatement, paramArrayOfObject, paramArrayOfFieldType);
      long l = localSQLiteStatement.executeInsert();
      if (paramGeneratedKeyHolder != null)
        paramGeneratedKeyHolder.addKey(Long.valueOf(l));
      return 1;
    }
    catch (android.database.SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("inserting to database failed: " + paramString, localSQLException);
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
    throw localObject;
  }

  public boolean isAutoCommitSupported()
  {
    return false;
  }

  public boolean isClosed()
    throws java.sql.SQLException
  {
    try
    {
      boolean bool = this.db.isOpen();
      if (!bool);
      for (int i = 1; ; i = 0)
        return i;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("problems detecting if the database is closed", localSQLException);
  }

  public boolean isReadWrite()
  {
    return this.readWrite;
  }

  public boolean isTableExists(String paramString)
    throws java.sql.SQLException
  {
    return true;
  }

  public long queryForLong(String paramString)
    throws java.sql.SQLException
  {
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = this.db.compileStatement(paramString);
      long l = localSQLiteStatement.simpleQueryForLong();
      return l;
    }
    catch (android.database.SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("queryForLong from database failed: " + paramString, localSQLException);
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
    throw localObject;
  }

  public <T> Object queryForOne(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType, GenericRowMapper<T> paramGenericRowMapper)
    throws java.sql.SQLException
  {
    Cursor localCursor = null;
    try
    {
      localCursor = this.db.rawQuery(paramString, toStrings(paramArrayOfObject));
      AndroidDatabaseResults localAndroidDatabaseResults = new AndroidDatabaseResults(localCursor);
      boolean bool = localAndroidDatabaseResults.next();
      Object localObject3;
      if (!bool)
        localObject3 = null;
      while (true)
      {
        return localObject3;
        Object localObject2 = paramGenericRowMapper.mapRow(localAndroidDatabaseResults);
        if (localAndroidDatabaseResults.next())
        {
          localObject3 = MORE_THAN_ONE;
          if (localCursor == null)
            continue;
          localCursor.close();
          continue;
        }
        if (localCursor != null)
          localCursor.close();
        localObject3 = localObject2;
      }
    }
    catch (android.database.SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("queryForOne from database failed: " + paramString, localSQLException);
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject1;
  }

  public void rollback(Savepoint paramSavepoint)
    throws java.sql.SQLException
  {
    try
    {
      this.db.endTransaction();
      return;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("problems rolling back transaction", localSQLException);
  }

  public void setAutoCommit(boolean paramBoolean)
  {
  }

  public Savepoint setSavePoint(String paramString)
    throws java.sql.SQLException
  {
    try
    {
      this.db.beginTransaction();
      return null;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("problems beginning transaction", localSQLException);
  }

  public int update(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws java.sql.SQLException
  {
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = this.db.compileStatement(paramString);
      bindArgs(localSQLiteStatement, paramArrayOfObject, paramArrayOfFieldType);
      localSQLiteStatement.execute();
      return 1;
    }
    catch (android.database.SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("updating database failed: " + paramString, localSQLException);
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.AndroidDatabaseConnection
 * JD-Core Version:    0.6.0
 */