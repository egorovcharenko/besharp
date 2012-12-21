package com.j256.ormlite.dao;

import java.sql.SQLException;

public abstract interface CloseableWrappedIterable<T> extends CloseableIterable<T>
{
  public abstract void close()
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.CloseableWrappedIterable
 * JD-Core Version:    0.6.0
 */