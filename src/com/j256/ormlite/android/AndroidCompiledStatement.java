package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseResults;
import java.util.ArrayList;
import java.util.List;

public class AndroidCompiledStatement
  implements CompiledStatement
{
  private final List<Object> args = new ArrayList();
  private Cursor cursor;
  private final SQLiteDatabase db;
  private Integer max;
  private final String sql;
  private final StatementBuilder.StatementType type;

  public AndroidCompiledStatement(String paramString, SQLiteDatabase paramSQLiteDatabase, StatementBuilder.StatementType paramStatementType)
  {
    this.sql = paramString;
    this.db = paramSQLiteDatabase;
    this.type = paramStatementType;
  }

  private void isInPrep()
    throws java.sql.SQLException
  {
    if (this.cursor != null)
      throw new java.sql.SQLException("Query already run. Cannot add argument values.");
  }

  public void close()
    throws java.sql.SQLException
  {
    if (this.cursor != null);
    try
    {
      this.cursor.close();
      return;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Problems closing Android cursor", localSQLException);
  }

  public int getColumnCount()
    throws java.sql.SQLException
  {
    return getCursor().getColumnCount();
  }

  public String getColumnName(int paramInt)
    throws java.sql.SQLException
  {
    return getCursor().getColumnName(paramInt);
  }

  public Cursor getCursor()
    throws java.sql.SQLException
  {
    Object localObject;
    if (this.cursor == null)
      localObject = null;
    try
    {
      if (this.max == null);
      String str;
      for (localObject = this.sql; ; localObject = str)
      {
        this.cursor = this.db.rawQuery((String)localObject, (String[])this.args.toArray(new String[this.args.size()]));
        this.cursor.moveToFirst();
        return this.cursor;
        str = this.sql + " " + this.max;
      }
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Problems executing Android query: " + (String)localObject, localSQLException);
  }

  public DatabaseResults getGeneratedKeys()
    throws java.sql.SQLException
  {
    throw new UnsupportedOperationException("Unsupported operation to getGeneratedKeys");
  }

  public int runExecute()
    throws java.sql.SQLException
  {
    if (this.type != StatementBuilder.StatementType.EXECUTE)
      throw new IllegalArgumentException("Cannot call execute on a " + this.type + " statement");
    try
    {
      this.db.execSQL(this.sql, new Object[0]);
      return 0;
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Problems executing Android statement: " + this.sql, localSQLException);
  }

  public DatabaseResults runQuery()
    throws java.sql.SQLException
  {
    if (this.type != StatementBuilder.StatementType.SELECT)
      throw new IllegalArgumentException("Cannot call query on a " + this.type + " statement");
    return new AndroidDatabaseResults(getCursor());
  }

  public int runUpdate()
    throws java.sql.SQLException
  {
    if (this.type == StatementBuilder.StatementType.SELECT)
      throw new IllegalArgumentException("Cannot call update on a " + this.type + " statement");
    Object localObject = null;
    try
    {
      if (this.max == null);
      String str;
      for (localObject = this.sql; ; localObject = str)
      {
        this.db.execSQL((String)localObject, this.args.toArray(new Object[this.args.size()]));
        return 1;
        str = this.sql + " " + this.max;
      }
    }
    catch (android.database.SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Problems executing Android statement: " + (String)localObject, localSQLException);
  }

  public void setMaxRows(int paramInt)
    throws java.sql.SQLException
  {
    isInPrep();
    this.max = Integer.valueOf(paramInt);
  }

  public void setNull(int paramInt, SqlType paramSqlType)
    throws java.sql.SQLException
  {
    isInPrep();
    this.args.add(paramInt, null);
  }

  public void setObject(int paramInt, Object paramObject, SqlType paramSqlType)
    throws java.sql.SQLException
  {
    isInPrep();
    this.args.add(paramInt, paramObject.toString());
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.AndroidCompiledStatement
 * JD-Core Version:    0.6.0
 */