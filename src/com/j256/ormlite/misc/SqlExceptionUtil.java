package com.j256.ormlite.misc;

import java.sql.SQLException;

public class SqlExceptionUtil
{
  public static SQLException create(String paramString, Throwable paramThrowable)
  {
    SQLException localSQLException = new SQLException(paramString);
    localSQLException.initCause(paramThrowable);
    return localSQLException;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.misc.SqlExceptionUtil
 * JD-Core Version:    0.6.0
 */