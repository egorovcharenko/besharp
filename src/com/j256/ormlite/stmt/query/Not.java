package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class Not
  implements Clause, NeedsFutureClause
{
  private Comparison comparison = null;
  private Exists exists = null;

  public Not()
  {
  }

  public Not(Clause paramClause)
  {
    setMissingClause(paramClause);
  }

  public void appendSql(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    if ((this.comparison == null) && (this.exists == null))
      throw new IllegalStateException("Clause has not been set in NOT operation");
    if (this.comparison == null)
    {
      paramStringBuilder.append("(NOT ");
      this.exists.appendSql(paramDatabaseType, paramStringBuilder, paramList);
    }
    while (true)
    {
      paramStringBuilder.append(") ");
      return;
      paramStringBuilder.append("(NOT ");
      paramDatabaseType.appendEscapedEntityName(paramStringBuilder, this.comparison.getColumnName());
      paramStringBuilder.append(' ');
      this.comparison.appendOperation(paramStringBuilder);
      this.comparison.appendValue(paramDatabaseType, paramStringBuilder, paramList);
    }
  }

  public void setMissingClause(Clause paramClause)
  {
    if (this.comparison != null)
      throw new IllegalArgumentException("NOT operation already has a comparison set");
    if ((paramClause instanceof Comparison))
      this.comparison = ((Comparison)paramClause);
    while (true)
    {
      return;
      if (!(paramClause instanceof Exists))
        break;
      this.exists = ((Exists)paramClause);
    }
    throw new IllegalArgumentException("NOT operation can only work with comparison SQL clauses, not " + paramClause);
  }

  public String toString()
  {
    if (this.comparison == null);
    for (String str = "NOT without comparison"; ; str = "NOT comparison " + this.comparison)
      return str;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.Not
 * JD-Core Version:    0.6.0
 */