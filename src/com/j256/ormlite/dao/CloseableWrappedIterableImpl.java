package com.j256.ormlite.dao;

import java.sql.SQLException;

public class CloseableWrappedIterableImpl<T>
  implements CloseableWrappedIterable<T>
{
  private CloseableIterable<T> iterable;
  private CloseableIterator<T> iterator;

  public CloseableWrappedIterableImpl(CloseableIterable<T> paramCloseableIterable)
  {
    this.iterable = paramCloseableIterable;
  }

  public void close()
    throws SQLException
  {
    if (this.iterator != null)
    {
      this.iterator.close();
      this.iterator = null;
    }
  }

  public CloseableIterator<T> closeableIterator()
  {
    this.iterator = this.iterable.closeableIterator();
    return this.iterator;
  }

  public CloseableIterator<T> iterator()
  {
    return closeableIterator();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.CloseableWrappedIterableImpl
 * JD-Core Version:    0.6.0
 */