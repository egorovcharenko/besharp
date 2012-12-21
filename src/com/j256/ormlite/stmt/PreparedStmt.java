package com.j256.ormlite.stmt;

import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;

public abstract interface PreparedStmt<T> extends GenericRowMapper<T>
{
  public abstract CompiledStatement compile(DatabaseConnection paramDatabaseConnection)
    throws SQLException;

  public abstract String getStatement()
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.PreparedStmt
 * JD-Core Version:    0.6.0
 */