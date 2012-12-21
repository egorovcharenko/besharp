package com.j256.ormlite.support;

import com.j256.ormlite.logger.Logger;
import java.sql.SQLException;

public abstract class BaseConnectionSource
  implements ConnectionSource
{
  private ThreadLocal<NestedConnection> specialConnection = new ThreadLocal();
  protected boolean usedSpecialConnection = false;

  protected boolean clearSpecial(DatabaseConnection paramDatabaseConnection, Logger paramLogger)
  {
    NestedConnection localNestedConnection = (NestedConnection)this.specialConnection.get();
    int i = 0;
    if (localNestedConnection == null)
      paramLogger.error("no connection has been saved when clear() called", new Object[0]);
    while (true)
    {
      return i;
      if (localNestedConnection.connection == paramDatabaseConnection)
      {
        if (localNestedConnection.decrementAndGet() == 0)
          this.specialConnection.set(null);
        i = 1;
        continue;
      }
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localNestedConnection.connection;
      arrayOfObject[1] = paramDatabaseConnection;
      paramLogger.error("connection saved {} is not the one being cleared {}", arrayOfObject);
    }
  }

  protected DatabaseConnection getSavedConnection()
    throws SQLException
  {
    DatabaseConnection localDatabaseConnection;
    if (!this.usedSpecialConnection)
      localDatabaseConnection = null;
    while (true)
    {
      return localDatabaseConnection;
      NestedConnection localNestedConnection = (NestedConnection)this.specialConnection.get();
      if (localNestedConnection == null)
      {
        localDatabaseConnection = null;
        continue;
      }
      localDatabaseConnection = localNestedConnection.connection;
    }
  }

  protected DatabaseConnection getSpecial()
  {
    NestedConnection localNestedConnection = (NestedConnection)this.specialConnection.get();
    if (localNestedConnection == null);
    for (DatabaseConnection localDatabaseConnection = null; ; localDatabaseConnection = localNestedConnection.connection)
      return localDatabaseConnection;
  }

  public DatabaseConnection getSpecialConnection()
  {
    return getSpecial();
  }

  protected boolean isSavedConnection(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
    int i;
    if (!this.usedSpecialConnection)
      i = 0;
    while (true)
    {
      return i;
      NestedConnection localNestedConnection = (NestedConnection)this.specialConnection.get();
      if (localNestedConnection == null)
      {
        i = 0;
        continue;
      }
      if (localNestedConnection.connection == paramDatabaseConnection)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  protected boolean saveSpecial(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
    NestedConnection localNestedConnection = (NestedConnection)this.specialConnection.get();
    if (localNestedConnection == null)
    {
      this.usedSpecialConnection = true;
      this.specialConnection.set(new NestedConnection(paramDatabaseConnection));
    }
    for (int i = 1; ; i = 0)
    {
      return i;
      if (localNestedConnection.connection != paramDatabaseConnection)
        throw new SQLException("trying to save connection " + paramDatabaseConnection + " but already have saved connection " + localNestedConnection.connection);
      localNestedConnection.increment();
    }
  }

  private static class NestedConnection
  {
    public final DatabaseConnection connection;
    private int nestedC;

    public NestedConnection(DatabaseConnection paramDatabaseConnection)
    {
      this.connection = paramDatabaseConnection;
      this.nestedC = 1;
    }

    public int decrementAndGet()
    {
      this.nestedC -= 1;
      return this.nestedC;
    }

    public void increment()
    {
      this.nestedC = (1 + this.nestedC);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.support.BaseConnectionSource
 * JD-Core Version:    0.6.0
 */