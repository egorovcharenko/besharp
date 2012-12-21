package com.j256.ormlite.dao;

import java.sql.SQLException;

public abstract interface RawRowMapper<T>
{
  public abstract T mapRow(String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.RawRowMapper
 * JD-Core Version:    0.6.0
 */