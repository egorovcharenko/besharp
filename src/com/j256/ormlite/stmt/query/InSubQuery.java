package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.QueryBuilder.InternalQueryBuilderWrapper;
import java.sql.SQLException;
import java.util.List;

public class InSubQuery extends BaseComparison
{
  private final QueryBuilder.InternalQueryBuilderWrapper subQueryBuilder;

  public InSubQuery(String paramString, FieldType paramFieldType, QueryBuilder.InternalQueryBuilderWrapper paramInternalQueryBuilderWrapper)
    throws SQLException
  {
    super(paramString, paramFieldType, null);
    this.subQueryBuilder = paramInternalQueryBuilderWrapper;
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("IN ");
  }

  public void appendValue(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramStringBuilder.append('(');
    this.subQueryBuilder.appendStatementString(paramStringBuilder, paramList);
    FieldType[] arrayOfFieldType = this.subQueryBuilder.getResultFieldTypes();
    if (arrayOfFieldType.length != 1)
      throw new SQLException("There must be only 1 result column in sub-query but we found " + arrayOfFieldType.length);
    if (this.fieldType.getSqlType() != arrayOfFieldType[0].getSqlType())
      throw new SQLException("Outer column " + this.fieldType + " is not the same type as inner column " + arrayOfFieldType[0]);
    paramStringBuilder.append(") ");
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.InSubQuery
 * JD-Core Version:    0.6.0
 */