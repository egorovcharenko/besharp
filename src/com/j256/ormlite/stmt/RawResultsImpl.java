package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawResultsImpl<T>
  implements GenericRawResults<T>
{
  protected final String[] columnNames;
  protected SelectIterator<T, Void> iterator;
  protected final GenericRowMapper<T> rowMapper;

  public RawResultsImpl(ConnectionSource paramConnectionSource, DatabaseConnection paramDatabaseConnection, String paramString, Class<?> paramClass, CompiledStatement paramCompiledStatement, String[] paramArrayOfString, GenericRowMapper<T> paramGenericRowMapper)
    throws SQLException
  {
    this.rowMapper = paramGenericRowMapper;
    this.iterator = new SelectIterator(paramClass, null, paramGenericRowMapper, paramConnectionSource, paramDatabaseConnection, paramCompiledStatement, paramString);
    this.columnNames = paramArrayOfString;
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
    return this.iterator;
  }

  public String[] getColumnNames()
  {
    return this.columnNames;
  }

  public int getNumberColumns()
  {
    return this.columnNames.length;
  }

  public List<T> getResults()
    throws SQLException
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      if (this.iterator.hasNext())
        localArrayList.add(this.iterator.next());
    }
    finally
    {
      this.iterator.close();
    }
    return localArrayList;
  }

  public CloseableIterator<T> iterator()
  {
    return this.iterator;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.RawResultsImpl
 * JD-Core Version:    0.6.0
 */