package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.List;

@Deprecated
public abstract interface RawResults extends CloseableIterable<String[]>
{
  public abstract void close()
    throws SQLException;

  public abstract String[] getColumnNames();

  public abstract <T> List<T> getMappedResults(RawRowMapper<T> paramRawRowMapper)
    throws SQLException;

  public abstract int getNumberColumns();

  public abstract List<String[]> getResults()
    throws SQLException;

  public abstract <T> CloseableIterator<T> iterator(RawRowMapper<T> paramRawRowMapper)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.RawResults
 * JD-Core Version:    0.6.0
 */