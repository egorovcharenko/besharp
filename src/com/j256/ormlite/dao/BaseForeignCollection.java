package com.j256.ormlite.dao;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public abstract class BaseForeignCollection<T, ID>
  implements ForeignCollection<T>
{
  protected final Dao<T, ID> dao;
  private final String fieldName;
  private final Object fieldValue;
  private final String orderColumn;
  private PreparedQuery<T> preparedQuery;

  public BaseForeignCollection(Dao<T, ID> paramDao, String paramString1, Object paramObject, String paramString2)
  {
    this.dao = paramDao;
    this.fieldName = paramString1;
    this.fieldValue = paramObject;
    this.orderColumn = paramString2;
  }

  public boolean add(T paramT)
  {
    try
    {
      this.dao.create(paramT);
      return true;
    }
    catch (SQLException localSQLException)
    {
    }
    throw new IllegalStateException("Could not create data element in dao", localSQLException);
  }

  public boolean addAll(Collection<? extends T> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      try
      {
        this.dao.create(localObject);
      }
      catch (SQLException localSQLException)
      {
        throw new IllegalStateException("Could not create data elements in dao", localSQLException);
      }
    }
    return true;
  }

  // ERROR //
  public void clear()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 25	com/j256/ormlite/dao/BaseForeignCollection:dao	Lcom/j256/ormlite/dao/Dao;
    //   4: invokeinterface 72 1 0
    //   9: astore_1
    //   10: aload_1
    //   11: invokeinterface 75 1 0
    //   16: ifeq +28 -> 44
    //   19: aload_1
    //   20: invokeinterface 76 1 0
    //   25: pop
    //   26: aload_1
    //   27: invokeinterface 79 1 0
    //   32: goto -22 -> 10
    //   35: astore_2
    //   36: aload_1
    //   37: invokeinterface 82 1 0
    //   42: aload_2
    //   43: athrow
    //   44: aload_1
    //   45: invokeinterface 82 1 0
    //   50: return
    //   51: astore 4
    //   53: goto -3 -> 50
    //   56: astore_3
    //   57: goto -15 -> 42
    //
    // Exception table:
    //   from	to	target	type
    //   10	32	35	finally
    //   44	50	51	java/sql/SQLException
    //   36	42	56	java/sql/SQLException
  }

  protected PreparedQuery<T> getPreparedQuery()
    throws SQLException
  {
    if (this.preparedQuery == null)
    {
      SelectArg localSelectArg = new SelectArg();
      localSelectArg.setValue(this.fieldValue);
      QueryBuilder localQueryBuilder = this.dao.queryBuilder();
      if (this.orderColumn != null)
        localQueryBuilder.orderBy(this.orderColumn, true);
      this.preparedQuery = localQueryBuilder.where().eq(this.fieldName, localSelectArg).prepare();
    }
    return this.preparedQuery;
  }

  public boolean remove(Object paramObject)
  {
    try
    {
      int i = this.dao.delete(paramObject);
      if (i == 1);
      for (int j = 1; ; j = 0)
        return j;
    }
    catch (SQLException localSQLException)
    {
    }
    throw new IllegalStateException("Could not delete data element from dao", localSQLException);
  }

  public boolean removeAll(Collection<?> paramCollection)
  {
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      try
      {
        int j = this.dao.delete(localObject);
        if (j <= 0)
          continue;
        i = 1;
      }
      catch (SQLException localSQLException)
      {
        throw new IllegalStateException("Could not create data elements in dao", localSQLException);
      }
    }
    return i;
  }

  // ERROR //
  public boolean retainAll(Collection<?> paramCollection)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 25	com/j256/ormlite/dao/BaseForeignCollection:dao	Lcom/j256/ormlite/dao/Dao;
    //   6: invokeinterface 72 1 0
    //   11: astore_3
    //   12: aload_3
    //   13: invokeinterface 75 1 0
    //   18: ifeq +29 -> 47
    //   21: aload_1
    //   22: aload_3
    //   23: invokeinterface 76 1 0
    //   28: invokeinterface 126 2 0
    //   33: ifne -21 -> 12
    //   36: aload_3
    //   37: invokeinterface 79 1 0
    //   42: iconst_1
    //   43: istore_2
    //   44: goto -32 -> 12
    //   47: aload_3
    //   48: invokeinterface 82 1 0
    //   53: iload_2
    //   54: ireturn
    //   55: astore 4
    //   57: aload_3
    //   58: invokeinterface 82 1 0
    //   63: aload 4
    //   65: athrow
    //   66: astore 6
    //   68: goto -15 -> 53
    //   71: astore 5
    //   73: goto -10 -> 63
    //
    // Exception table:
    //   from	to	target	type
    //   12	42	55	finally
    //   47	53	66	java/sql/SQLException
    //   57	63	71	java/sql/SQLException
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.BaseForeignCollection
 * JD-Core Version:    0.6.0
 */