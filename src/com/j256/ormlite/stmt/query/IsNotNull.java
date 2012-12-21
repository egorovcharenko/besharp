package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class IsNotNull extends BaseComparison
{
  public IsNotNull(String paramString, FieldType paramFieldType)
    throws SQLException
  {
    super(paramString, paramFieldType, null);
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("IS NOT NULL ");
  }

  public void appendValue(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
  {
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.IsNotNull
 * JD-Core Version:    0.6.0
 */