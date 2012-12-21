package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

public class SetExpression extends BaseComparison
{
  public SetExpression(String paramString1, FieldType paramFieldType, String paramString2)
    throws SQLException
  {
    super(paramString1, paramFieldType, paramString2);
  }

  protected void appendArgOrValue(DatabaseType paramDatabaseType, FieldType paramFieldType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList, Object paramObject)
  {
    paramStringBuilder.append(paramObject).append(' ');
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("= ");
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.SetExpression
 * JD-Core Version:    0.6.0
 */