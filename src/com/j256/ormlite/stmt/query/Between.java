package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class Between extends BaseComparison
{
  private Object high;
  private Object low;

  public Between(String paramString, FieldType paramFieldType, Object paramObject1, Object paramObject2)
    throws SQLException
  {
    super(paramString, paramFieldType, null);
    this.low = paramObject1;
    this.high = paramObject2;
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("BETWEEN ");
  }

  public void appendValue(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    if (this.low == null)
      throw new IllegalArgumentException("BETWEEN low value for '" + this.columnName + "' is null");
    if (this.high == null)
      throw new IllegalArgumentException("BETWEEN high value for '" + this.columnName + "' is null");
    appendArgOrValue(paramDatabaseType, this.fieldType, paramStringBuilder, paramList, this.low);
    paramStringBuilder.append("AND ");
    appendArgOrValue(paramDatabaseType, this.fieldType, paramStringBuilder, paramList, this.high);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.Between
 * JD-Core Version:    0.6.0
 */