package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class LazyForeignCollection<T, ID> extends BaseForeignCollection<T, ID>
  implements ForeignCollection<T>
{
  private CloseableIterator<T> lastIterator;

  public LazyForeignCollection(Dao<T, ID> paramDao, String paramString1, Object paramObject, String paramString2)
  {
    super(paramDao, paramString1, paramObject, paramString2);
  }

  public void closeLastIterator()
    throws SQLException
  {
    if (this.lastIterator != null)
    {
      this.lastIterator.close();
      this.lastIterator = null;
    }
  }

  public CloseableIterator<T> closeableIterator()
  {
    try
    {
      CloseableIterator localCloseableIterator = iteratorThrow();
      return localCloseableIterator;
    }
    catch (SQLException localSQLException)
    {
    }
    throw new IllegalStateException("Could not build lazy iterator for " + this.dao.getDataClass(), localSQLException);
  }

  // ERROR //
  public boolean contains(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 67	com/j256/ormlite/dao/LazyForeignCollection:iterator	()Lcom/j256/ormlite/dao/CloseableIterator;
    //   4: astore_2
    //   5: aload_2
    //   6: invokeinterface 71 1 0
    //   11: ifeq +32 -> 43
    //   14: aload_2
    //   15: invokeinterface 75 1 0
    //   20: aload_1
    //   21: invokevirtual 80	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   24: istore 7
    //   26: iload 7
    //   28: ifeq -23 -> 5
    //   31: iconst_1
    //   32: istore 5
    //   34: aload_2
    //   35: invokeinterface 25 1 0
    //   40: iload 5
    //   42: ireturn
    //   43: iconst_0
    //   44: istore 5
    //   46: aload_2
    //   47: invokeinterface 25 1 0
    //   52: goto -12 -> 40
    //   55: astore 6
    //   57: goto -17 -> 40
    //   60: astore_3
    //   61: aload_2
    //   62: invokeinterface 25 1 0
    //   67: aload_3
    //   68: athrow
    //   69: astore 8
    //   71: goto -31 -> 40
    //   74: astore 4
    //   76: goto -9 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   46	52	55	java/sql/SQLException
    //   5	26	60	finally
    //   34	40	69	java/sql/SQLException
    //   61	67	74	java/sql/SQLException
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (contains(localIterator.next()));
    for (int i = 0; ; i = 1)
      return i;
  }

  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  public CloseableWrappedIterable<T> getWrappedIterable()
  {
    return new CloseableWrappedIterableImpl(new LazyForeignCollection.1(this));
  }

  public int hashCode()
  {
    return super.hashCode();
  }

  public boolean isEager()
  {
    return false;
  }

  // ERROR //
  public boolean isEmpty()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 67	com/j256/ormlite/dao/LazyForeignCollection:iterator	()Lcom/j256/ormlite/dao/CloseableIterator;
    //   4: astore_1
    //   5: aload_1
    //   6: invokeinterface 71 1 0
    //   11: istore 4
    //   13: iload 4
    //   15: ifne +15 -> 30
    //   18: iconst_1
    //   19: istore 5
    //   21: aload_1
    //   22: invokeinterface 25 1 0
    //   27: iload 5
    //   29: ireturn
    //   30: iconst_0
    //   31: istore 5
    //   33: goto -12 -> 21
    //   36: astore_2
    //   37: aload_1
    //   38: invokeinterface 25 1 0
    //   43: aload_2
    //   44: athrow
    //   45: astore 6
    //   47: goto -20 -> 27
    //   50: astore_3
    //   51: goto -8 -> 43
    //
    // Exception table:
    //   from	to	target	type
    //   5	13	36	finally
    //   21	27	45	java/sql/SQLException
    //   37	43	50	java/sql/SQLException
  }

  public CloseableIterator<T> iterator()
  {
    return closeableIterator();
  }

  public CloseableIterator<T> iteratorThrow()
    throws SQLException
  {
    this.lastIterator = seperateIteratorThrow();
    return this.lastIterator;
  }

  public CloseableIterator<T> seperateIteratorThrow()
    throws SQLException
  {
    return this.dao.iterator(getPreparedQuery());
  }

  // ERROR //
  public int size()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: invokevirtual 67	com/j256/ormlite/dao/LazyForeignCollection:iterator	()Lcom/j256/ormlite/dao/CloseableIterator;
    //   6: astore_2
    //   7: aload_2
    //   8: invokeinterface 71 1 0
    //   13: istore 5
    //   15: iload 5
    //   17: ifeq +9 -> 26
    //   20: iinc 1 1
    //   23: goto -16 -> 7
    //   26: aload_2
    //   27: invokeinterface 25 1 0
    //   32: iload_1
    //   33: ireturn
    //   34: astore_3
    //   35: aload_2
    //   36: invokeinterface 25 1 0
    //   41: aload_3
    //   42: athrow
    //   43: astore 6
    //   45: goto -13 -> 32
    //   48: astore 4
    //   50: goto -9 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   7	15	34	finally
    //   26	32	43	java/sql/SQLException
    //   35	41	48	java/sql/SQLException
  }

  // ERROR //
  public Object[] toArray()
  {
    // Byte code:
    //   0: new 128	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 129	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokevirtual 67	com/j256/ormlite/dao/LazyForeignCollection:iterator	()Lcom/j256/ormlite/dao/CloseableIterator;
    //   12: astore_2
    //   13: aload_2
    //   14: invokeinterface 71 1 0
    //   19: ifeq +28 -> 47
    //   22: aload_1
    //   23: aload_2
    //   24: invokeinterface 75 1 0
    //   29: invokeinterface 134 2 0
    //   34: pop
    //   35: goto -22 -> 13
    //   38: astore_3
    //   39: aload_2
    //   40: invokeinterface 25 1 0
    //   45: aload_3
    //   46: athrow
    //   47: aload_2
    //   48: invokeinterface 25 1 0
    //   53: aload_1
    //   54: invokeinterface 136 1 0
    //   59: areturn
    //   60: astore 5
    //   62: goto -9 -> 53
    //   65: astore 4
    //   67: goto -22 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   13	35	38	finally
    //   47	53	60	java/sql/SQLException
    //   39	45	65	java/sql/SQLException
  }

  // ERROR //
  public <E> E[] toArray(E[] paramArrayOfE)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: invokevirtual 67	com/j256/ormlite/dao/LazyForeignCollection:iterator	()Lcom/j256/ormlite/dao/CloseableIterator;
    //   6: astore_3
    //   7: aconst_null
    //   8: astore 4
    //   10: aload_3
    //   11: invokeinterface 71 1 0
    //   16: ifeq +95 -> 111
    //   19: aload_3
    //   20: invokeinterface 75 1 0
    //   25: astore 10
    //   27: iload_2
    //   28: aload_1
    //   29: arraylength
    //   30: if_icmplt +69 -> 99
    //   33: aload 4
    //   35: ifnonnull +147 -> 182
    //   38: new 128	java/util/ArrayList
    //   41: dup
    //   42: invokespecial 129	java/util/ArrayList:<init>	()V
    //   45: astore 11
    //   47: aload_1
    //   48: arraylength
    //   49: istore 12
    //   51: iconst_0
    //   52: istore 13
    //   54: iload 13
    //   56: iload 12
    //   58: if_icmpge +21 -> 79
    //   61: aload 11
    //   63: aload_1
    //   64: iload 13
    //   66: aaload
    //   67: invokeinterface 134 2 0
    //   72: pop
    //   73: iinc 13 1
    //   76: goto -22 -> 54
    //   79: aload 11
    //   81: aload 10
    //   83: invokeinterface 134 2 0
    //   88: pop
    //   89: iinc 2 1
    //   92: aload 11
    //   94: astore 4
    //   96: goto -86 -> 10
    //   99: aload_1
    //   100: iload_2
    //   101: aload 10
    //   103: aastore
    //   104: aload 4
    //   106: astore 11
    //   108: goto -19 -> 89
    //   111: aload_3
    //   112: invokeinterface 25 1 0
    //   117: aload 4
    //   119: ifnonnull +35 -> 154
    //   122: iload_2
    //   123: aload_1
    //   124: arraylength
    //   125: iconst_1
    //   126: isub
    //   127: if_icmpge +7 -> 134
    //   130: aload_1
    //   131: iload_2
    //   132: aconst_null
    //   133: aastore
    //   134: aload_1
    //   135: astore 9
    //   137: aload 9
    //   139: areturn
    //   140: astore 5
    //   142: aload 4
    //   144: pop
    //   145: aload_3
    //   146: invokeinterface 25 1 0
    //   151: aload 5
    //   153: athrow
    //   154: aload 4
    //   156: aload_1
    //   157: invokeinterface 139 2 0
    //   162: astore 9
    //   164: goto -27 -> 137
    //   167: astore 8
    //   169: goto -52 -> 117
    //   172: astore 7
    //   174: goto -23 -> 151
    //   177: astore 5
    //   179: goto -34 -> 145
    //   182: aload 4
    //   184: astore 11
    //   186: goto -107 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   10	47	140	finally
    //   99	104	140	finally
    //   111	117	167	java/sql/SQLException
    //   145	151	172	java/sql/SQLException
    //   47	89	177	finally
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.LazyForeignCollection
 * JD-Core Version:    0.6.0
 */