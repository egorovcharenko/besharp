package com.j256.ormlite.support;

import com.j256.ormlite.db.DatabaseType;
import java.sql.SQLException;

public abstract interface ConnectionSource
{
  public abstract void clearSpecialConnection(DatabaseConnection paramDatabaseConnection);

  public abstract void close()
    throws SQLException;

  public abstract DatabaseType getDatabaseType();

  public abstract DatabaseConnection getReadOnlyConnection()
    throws SQLException;

  public abstract DatabaseConnection getReadWriteConnection()
    throws SQLException;

  public abstract DatabaseConnection getSpecialConnection();

  public abstract boolean isOpen();

  public abstract void releaseConnection(DatabaseConnection paramDatabaseConnection)
    throws SQLException;

  public abstract boolean saveSpecialConnection(DatabaseConnection paramDatabaseConnection)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.support.ConnectionSource
 * JD-Core Version:    0.6.0
 */