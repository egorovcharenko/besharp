package com.j256.ormlite.dao;

class LazyForeignCollection$1
  implements CloseableIterable<T>
{
  public CloseableIterator<T> closeableIterator()
  {
    try
    {
      CloseableIterator localCloseableIterator = this.this$0.seperateIteratorThrow();
      return localCloseableIterator;
    }
    catch (Exception localException)
    {
    }
    throw new IllegalStateException("Could not build lazy iterator for " + this.this$0.dao.getDataClass(), localException);
  }

  public CloseableIterator<T> iterator()
  {
    return closeableIterator();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.LazyForeignCollection.1
 * JD-Core Version:    0.6.0
 */