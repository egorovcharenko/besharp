package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EagerForeignCollection<T, ID> extends BaseForeignCollection<T, ID>
  implements ForeignCollection<T>, CloseableWrappedIterable<T>
{
  private final List<T> results;

  public EagerForeignCollection(Dao<T, ID> paramDao, String paramString1, Object paramObject, String paramString2)
    throws SQLException
  {
    super(paramDao, paramString1, paramObject, paramString2);
    if (paramObject == null);
    for (this.results = new ArrayList(); ; this.results = paramDao.query(getPreparedQuery()))
      return;
  }

  public boolean add(T paramT)
  {
    this.results.add(paramT);
    return super.add(paramT);
  }

  public boolean addAll(Collection<? extends T> paramCollection)
  {
    this.results.addAll(paramCollection);
    return super.addAll(paramCollection);
  }

  public void clear()
  {
    this.results.clear();
    super.clear();
  }

  public void close()
  {
  }

  public void closeLastIterator()
  {
  }

  public CloseableIterator<T> closeableIterator()
  {
    return iteratorThrow();
  }

  public boolean contains(Object paramObject)
  {
    return this.results.contains(paramObject);
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    return this.results.containsAll(paramCollection);
  }

  public boolean equals(Object paramObject)
  {
    return this.results.equals(paramObject);
  }

  public CloseableWrappedIterable<T> getWrappedIterable()
  {
    return this;
  }

  public int hashCode()
  {
    return this.results.hashCode();
  }

  public boolean isEager()
  {
    return true;
  }

  public boolean isEmpty()
  {
    return this.results.isEmpty();
  }

  public CloseableIterator<T> iterator()
  {
    return iteratorThrow();
  }

  public CloseableIterator<T> iteratorThrow()
  {
    return new EagerForeignCollection.1(this);
  }

  public boolean remove(Object paramObject)
  {
    this.results.remove(paramObject);
    return super.remove(paramObject);
  }

  public boolean removeAll(Collection<?> paramCollection)
  {
    this.results.removeAll(paramCollection);
    return super.removeAll(paramCollection);
  }

  public boolean retainAll(Collection<?> paramCollection)
  {
    this.results.retainAll(paramCollection);
    return super.retainAll(paramCollection);
  }

  public int size()
  {
    return this.results.size();
  }

  public Object[] toArray()
  {
    return this.results.toArray();
  }

  public <E> E[] toArray(E[] paramArrayOfE)
  {
    return this.results.toArray(paramArrayOfE);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.EagerForeignCollection
 * JD-Core Version:    0.6.0
 */