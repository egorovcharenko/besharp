package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.Collection;

public abstract interface ForeignCollection<T> extends Collection<T>, CloseableIterable<T>
{
  public abstract void closeLastIterator()
    throws SQLException;

  public abstract CloseableWrappedIterable<T> getWrappedIterable();

  public abstract boolean isEager();

  public abstract CloseableIterator<T> iteratorThrow()
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.ForeignCollection
 * JD-Core Version:    0.6.0
 */