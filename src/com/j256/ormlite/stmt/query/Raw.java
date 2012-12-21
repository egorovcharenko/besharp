package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class Raw
  implements Clause
{
  private final String statement;

  public Raw(String paramString)
  {
    this.statement = paramString;
  }

  public void appendSql(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramStringBuilder.append(this.statement);
    paramStringBuilder.append(' ');
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.Raw
 * JD-Core Version:    0.6.0
 */