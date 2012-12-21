package com.j256.ormlite.stmt.query;

import com.j256.ormlite.field.FieldType;
import java.sql.SQLException;

public class SimpleComparison extends BaseComparison
{
  public static final String EQUAL_TO_OPERATION = "=";
  public static final String GREATER_THAN_EQUAL_TO_OPERATION = ">=";
  public static final String GREATER_THAN_OPERATION = ">";
  public static final String LESS_THAN_EQUAL_TO_OPERATION = "<=";
  public static final String LESS_THAN_OPERATION = "<";
  public static final String LIKE_OPERATION = "LIKE";
  public static final String NOT_EQUAL_TO_OPERATION = "<>";
  private final String operation;

  public SimpleComparison(String paramString1, FieldType paramFieldType, Object paramObject, String paramString2)
    throws SQLException
  {
    super(paramString1, paramFieldType, paramObject);
    this.operation = paramString2;
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(this.operation);
    paramStringBuilder.append(' ');
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.SimpleComparison
 * JD-Core Version:    0.6.0
 */