package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.List;

public abstract interface GenericRawResults<T> extends CloseableWrappedIterable<T>
{
  public abstract void close()
    throws SQLException;

  public abstract String[] getColumnNames();

  public abstract int getNumberColumns();

  public abstract List<T> getResults()
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.GenericRawResults
 * JD-Core Version:    0.6.0
 */