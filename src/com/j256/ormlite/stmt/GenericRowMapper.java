package com.j256.ormlite.stmt;

import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

public abstract interface GenericRowMapper<T>
{
  public abstract T mapRow(DatabaseResults paramDatabaseResults)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.GenericRowMapper
 * JD-Core Version:    0.6.0
 */