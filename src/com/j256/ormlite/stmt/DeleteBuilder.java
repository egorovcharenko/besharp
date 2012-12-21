package com.j256.ormlite.stmt;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.List;

public class DeleteBuilder<T, ID> extends StatementBuilder<T, ID>
{
  public DeleteBuilder(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
  {
    super(paramDatabaseType, paramTableInfo, StatementBuilder.StatementType.DELETE);
  }

  protected void appendStatementEnd(StringBuilder paramStringBuilder)
  {
  }

  protected void appendStatementStart(StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
  {
    paramStringBuilder.append("DELETE FROM ");
    this.databaseType.appendEscapedEntityName(paramStringBuilder, this.tableInfo.getTableName());
    paramStringBuilder.append(' ');
  }

  public PreparedDelete<T> prepare()
    throws SQLException
  {
    return super.prepareStatement();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.DeleteBuilder
 * JD-Core Version:    0.6.0
 */