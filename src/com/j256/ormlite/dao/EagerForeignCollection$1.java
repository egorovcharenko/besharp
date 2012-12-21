package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

class EagerForeignCollection$1
  implements CloseableIterator<T>
{
  private Iterator<T> iterator = EagerForeignCollection.access$000(this.this$0).iterator();
  private T last = null;

  public void close()
  {
  }

  public boolean hasNext()
  {
    return this.iterator.hasNext();
  }

  public T next()
  {
    this.last = this.iterator.next();
    return this.last;
  }

  public void remove()
  {
    this.iterator.remove();
    try
    {
      this.this$0.dao.delete(this.last);
      return;
    }
    catch (SQLException localSQLException)
    {
    }
    throw new RuntimeException(localSQLException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.EagerForeignCollection.1
 * JD-Core Version:    0.6.0
 */