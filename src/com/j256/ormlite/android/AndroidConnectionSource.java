package com.j256.ormlite.android;

import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.BaseConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;

public class AndroidConnectionSource extends BaseConnectionSource
  implements ConnectionSource
{
  private static final Logger logger = LoggerFactory.getLogger(AndroidConnectionSource.class);
  private DatabaseConnection connection = null;
  private final DatabaseType databaseType = new SqliteAndroidDatabaseType();
  private final SQLiteOpenHelper helper;
  private volatile boolean isOpen = true;

  public AndroidConnectionSource(SQLiteOpenHelper paramSQLiteOpenHelper)
  {
    this.helper = paramSQLiteOpenHelper;
  }

  public void clearSpecialConnection(DatabaseConnection paramDatabaseConnection)
  {
    clearSpecial(paramDatabaseConnection, logger);
  }

  public void close()
  {
    this.isOpen = false;
  }

  public DatabaseType getDatabaseType()
  {
    return this.databaseType;
  }

  public DatabaseConnection getReadOnlyConnection()
    throws SQLException
  {
    return getReadWriteConnection();
  }

  public DatabaseConnection getReadWriteConnection()
    throws SQLException
  {
    DatabaseConnection localDatabaseConnection1 = getSavedConnection();
    if (localDatabaseConnection1 != null);
    for (DatabaseConnection localDatabaseConnection2 = localDatabaseConnection1; ; localDatabaseConnection2 = this.connection)
    {
      return localDatabaseConnection2;
      if (this.connection != null)
        continue;
      this.connection = new AndroidDatabaseConnection(this.helper.getWritableDatabase(), true);
    }
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public void releaseConnection(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
  }

  public boolean saveSpecialConnection(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
    return saveSpecial(paramDatabaseConnection);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.AndroidConnectionSource
 * JD-Core Version:    0.6.0
 */