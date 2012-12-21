package com.j256.ormlite.android;

import android.database.Cursor;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.support.DatabaseResults;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AndroidDatabaseResults
  implements DatabaseResults
{
  private static final DatabaseType databaseType = new SqliteAndroidDatabaseType();
  private final Cursor cursor;
  private boolean firstCall;

  public AndroidDatabaseResults(Cursor paramCursor)
  {
    this.cursor = paramCursor;
    this.firstCall = true;
  }

  public int findColumn(String paramString)
    throws SQLException
  {
    int i = this.cursor.getColumnIndex(paramString);
    if (i < 0)
    {
      StringBuilder localStringBuilder = new StringBuilder(4 + paramString.length());
      databaseType.appendEscapedEntityName(localStringBuilder, paramString);
      i = this.cursor.getColumnIndex(localStringBuilder.toString());
      if (i < 0)
        throw new SQLException("Unknown field '" + paramString + "' from the Android sqlite cursor");
    }
    return i;
  }

  public InputStream getBlobStream(int paramInt)
    throws SQLException
  {
    return new ByteArrayInputStream(this.cursor.getBlob(paramInt));
  }

  public boolean getBoolean(int paramInt)
    throws SQLException
  {
    if ((this.cursor.isNull(paramInt)) || (this.cursor.getShort(paramInt) == 0));
    for (int i = 0; ; i = 1)
      return i;
  }

  public byte getByte(int paramInt)
    throws SQLException
  {
    return (byte)getShort(paramInt);
  }

  public byte[] getBytes(int paramInt)
    throws SQLException
  {
    return this.cursor.getBlob(paramInt);
  }

  public char getChar(int paramInt)
    throws SQLException
  {
    String str = this.cursor.getString(paramInt);
    if ((str == null) || (str.length() == 0));
    for (int i = 0; ; i = str.charAt(0))
    {
      return i;
      if (str.length() != 1)
        break;
    }
    throw new SQLException("More than 1 character stored in database column: " + paramInt);
  }

  public int getColumnCount()
    throws SQLException
  {
    return this.cursor.getColumnCount();
  }

  public double getDouble(int paramInt)
    throws SQLException
  {
    return this.cursor.getDouble(paramInt);
  }

  public float getFloat(int paramInt)
    throws SQLException
  {
    return this.cursor.getFloat(paramInt);
  }

  public int getInt(int paramInt)
    throws SQLException
  {
    return this.cursor.getInt(paramInt);
  }

  public long getLong(int paramInt)
    throws SQLException
  {
    return this.cursor.getLong(paramInt);
  }

  public Cursor getRawCursor()
  {
    return this.cursor;
  }

  public short getShort(int paramInt)
    throws SQLException
  {
    return this.cursor.getShort(paramInt);
  }

  public String getString(int paramInt)
    throws SQLException
  {
    return this.cursor.getString(paramInt);
  }

  public Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
    throw new SQLException("Android does not support timestamp.  Use JAVA_DATE_LONG or JAVA_DATE_STRING types");
  }

  public boolean next()
    throws SQLException
  {
    boolean bool;
    if (this.firstCall)
    {
      bool = this.cursor.moveToFirst();
      this.firstCall = false;
    }
    while (true)
    {
      return bool;
      bool = this.cursor.moveToNext();
    }
  }

  public boolean wasNull(int paramInt)
    throws SQLException
  {
    return this.cursor.isNull(paramInt);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.AndroidDatabaseResults
 * JD-Core Version:    0.6.0
 */