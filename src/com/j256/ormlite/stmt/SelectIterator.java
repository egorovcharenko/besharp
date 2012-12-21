package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

public class SelectIterator<T, ID>
  implements CloseableIterator<T>
{
  private static final Logger logger = LoggerFactory.getLogger(SelectIterator.class);
  private final Dao<T, ID> classDao;
  private boolean closed = false;
  private final CompiledStatement compiledStmt;
  private final DatabaseConnection connection;
  private final ConnectionSource connectionSource;
  private final Class<?> dataClass;
  private T last = null;
  private final DatabaseResults results;
  private int rowC = 0;
  private final GenericRowMapper<T> rowMapper;
  private final String statement;

  public SelectIterator(Class<?> paramClass, Dao<T, ID> paramDao, GenericRowMapper<T> paramGenericRowMapper, ConnectionSource paramConnectionSource, DatabaseConnection paramDatabaseConnection, CompiledStatement paramCompiledStatement, String paramString)
    throws SQLException
  {
    this.dataClass = paramClass;
    this.classDao = paramDao;
    this.rowMapper = paramGenericRowMapper;
    this.connectionSource = paramConnectionSource;
    this.connection = paramDatabaseConnection;
    this.compiledStmt = paramCompiledStatement;
    this.results = paramCompiledStatement.runQuery();
    this.statement = paramString;
    if (paramString != null)
    {
      Logger localLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(hashCode());
      arrayOfObject[1] = paramString;
      localLogger.debug("starting iterator @{} for '{}'", arrayOfObject);
    }
  }

  public void close()
    throws SQLException
  {
    if (!this.closed)
    {
      this.compiledStmt.close();
      this.closed = true;
      this.last = null;
      if (this.statement != null)
      {
        Logger localLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(hashCode());
        arrayOfObject[1] = Integer.valueOf(this.rowC);
        localLogger.debug("closed iterator @{} after {} rows", arrayOfObject);
      }
      this.connectionSource.releaseConnection(this.connection);
    }
  }

  public DatabaseResults getRawResults()
  {
    return this.results;
  }

  public boolean hasNext()
  {
    try
    {
      boolean bool = hasNextThrow();
      return bool;
    }
    catch (SQLException localSQLException1)
    {
      this.last = null;
    }
    try
    {
      close();
      label17: throw new IllegalStateException("Errors getting more results of " + this.dataClass, localSQLException1);
    }
    catch (SQLException localSQLException2)
    {
      break label17;
    }
  }

  public boolean hasNextThrow()
    throws SQLException
  {
    int i;
    if (this.closed)
      i = 0;
    while (true)
    {
      return i;
      if (this.results.next())
      {
        i = 1;
        continue;
      }
      close();
      i = 0;
    }
  }

  public T next()
  {
    try
    {
      Object localObject = nextThrow();
      return localObject;
    }
    catch (SQLException localSQLException1)
    {
      this.last = null;
    }
    try
    {
      close();
      label17: throw new IllegalStateException("Errors getting more results of " + this.dataClass, localSQLException1);
    }
    catch (SQLException localSQLException2)
    {
      break label17;
    }
  }

  public T nextThrow()
    throws SQLException
  {
    if (this.closed);
    for (Object localObject = null; ; localObject = this.last)
    {
      return localObject;
      this.last = this.rowMapper.mapRow(this.results);
      this.rowC = (1 + this.rowC);
    }
  }

  public void remove()
  {
    try
    {
      removeThrow();
      return;
    }
    catch (SQLException localSQLException1)
    {
    }
    try
    {
      close();
      label10: throw new IllegalStateException("Errors trying to delete " + this.dataClass + " object " + this.last, localSQLException1);
    }
    catch (SQLException localSQLException2)
    {
      break label10;
    }
  }

  public void removeThrow()
    throws SQLException
  {
    if (this.last == null)
      throw new IllegalStateException("No last " + this.dataClass + " object to remove. Must be called after a call to next.");
    if (this.classDao == null)
      throw new IllegalStateException("Cannot remove " + this.dataClass + " object because classDao not initialized");
    try
    {
      this.classDao.delete(this.last);
      return;
    }
    finally
    {
      this.last = null;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.SelectIterator
 * JD-Core Version:    0.6.0
 */