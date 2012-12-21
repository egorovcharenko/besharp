package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.QueryBuilder.InternalQueryBuilderWrapper;
import java.sql.SQLException;
import java.util.List;

public class Exists
  implements Clause
{
  private final QueryBuilder.InternalQueryBuilderWrapper subQueryBuilder;

  public Exists(QueryBuilder.InternalQueryBuilderWrapper paramInternalQueryBuilderWrapper)
  {
    this.subQueryBuilder = paramInternalQueryBuilderWrapper;
  }

  public void appendSql(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramStringBuilder.append("EXISTS (");
    this.subQueryBuilder.appendStatementString(paramStringBuilder, paramList);
    paramStringBuilder.append(") ");
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.Exists
 * JD-Core Version:    0.6.0
 */