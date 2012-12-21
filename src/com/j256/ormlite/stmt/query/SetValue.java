package com.j256.ormlite.stmt.query;

import com.j256.ormlite.field.FieldType;
import java.sql.SQLException;

public class SetValue extends BaseComparison
{
  public SetValue(String paramString, FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    super(paramString, paramFieldType, paramObject);
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("= ");
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.SetValue
 * JD-Core Version:    0.6.0
 */