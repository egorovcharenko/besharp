package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.Iterator;

public abstract interface CloseableIterator<T> extends Iterator<T>
{
  public abstract void close()
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.CloseableIterator
 * JD-Core Version:    0.6.0
 */