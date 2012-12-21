package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class ManyClause
  implements Clause, NeedsFutureClause
{
  public static final String AND_OPERATION = "AND";
  public static final String OR_OPERATION = "OR";
  private final Clause first;
  private final String operation;
  private final Clause[] others;
  private Clause second;
  private final int startOthersAt;

  public ManyClause(Clause paramClause1, Clause paramClause2, Clause[] paramArrayOfClause, String paramString)
  {
    this.first = paramClause1;
    this.second = paramClause2;
    this.others = paramArrayOfClause;
    this.startOthersAt = 0;
    this.operation = paramString;
  }

  public ManyClause(Clause paramClause, String paramString)
  {
    this.first = paramClause;
    this.second = null;
    this.others = null;
    this.startOthersAt = 0;
    this.operation = paramString;
  }

  public ManyClause(Clause[] paramArrayOfClause, String paramString)
  {
    this.first = paramArrayOfClause[0];
    if (paramArrayOfClause.length < 2)
      this.second = null;
    for (this.startOthersAt = paramArrayOfClause.length; ; this.startOthersAt = 2)
    {
      this.others = paramArrayOfClause;
      this.operation = paramString;
      return;
      this.second = paramArrayOfClause[1];
    }
  }

  public void appendSql(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramStringBuilder.append("(");
    this.first.appendSql(paramDatabaseType, paramStringBuilder, paramList);
    if (this.second != null)
    {
      paramStringBuilder.append(this.operation);
      paramStringBuilder.append(' ');
      this.second.appendSql(paramDatabaseType, paramStringBuilder, paramList);
    }
    if (this.others != null)
      for (int i = this.startOthersAt; i < this.others.length; i++)
      {
        paramStringBuilder.append(this.operation);
        paramStringBuilder.append(' ');
        this.others[i].appendSql(paramDatabaseType, paramStringBuilder, paramList);
      }
    paramStringBuilder.append(") ");
  }

  public void setMissingClause(Clause paramClause)
  {
    this.second = paramClause;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.ManyClause
 * JD-Core Version:    0.6.0
 */