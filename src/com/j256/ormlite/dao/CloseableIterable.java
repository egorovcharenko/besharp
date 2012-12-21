package com.j256.ormlite.dao;

public abstract interface CloseableIterable<T> extends Iterable<T>
{
  public abstract CloseableIterator<T> closeableIterator();
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.CloseableIterable
 * JD-Core Version:    0.6.0
 */