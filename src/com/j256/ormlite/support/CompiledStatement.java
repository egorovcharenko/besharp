package com.j256.ormlite.support;

import com.j256.ormlite.field.SqlType;
import java.sql.SQLException;

public abstract interface CompiledStatement
{
  public abstract void close()
    throws SQLException;

  public abstract int getColumnCount()
    throws SQLException;

  public abstract String getColumnName(int paramInt)
    throws SQLException;

  public abstract DatabaseResults getGeneratedKeys()
    throws SQLException;

  public abstract int runExecute()
    throws SQLException;

  public abstract DatabaseResults runQuery()
    throws SQLException;

  public abstract int runUpdate()
    throws SQLException;

  public abstract void setMaxRows(int paramInt)
    throws SQLException;

  public abstract void setNull(int paramInt, SqlType paramSqlType)
    throws SQLException;

  public abstract void setObject(int paramInt, Object paramObject, SqlType paramSqlType)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.support.CompiledStatement
 * JD-Core Version:    0.6.0
 */