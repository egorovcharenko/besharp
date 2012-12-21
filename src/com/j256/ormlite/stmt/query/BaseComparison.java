package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.SelectArg;
import java.sql.SQLException;
import java.util.List;

abstract class BaseComparison
  implements Comparison
{
  protected final String columnName;
  protected final FieldType fieldType;
  private final Object value;

  protected BaseComparison(String paramString, FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    if ((paramFieldType != null) && (!paramFieldType.isComparable()))
      throw new SQLException("Field '" + paramString + "' is of data type " + paramFieldType.getDataPersister() + " which can be compared");
    this.columnName = paramString;
    this.fieldType = paramFieldType;
    this.value = paramObject;
  }

  protected void appendArgOrValue(DatabaseType paramDatabaseType, FieldType paramFieldType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList, Object paramObject)
    throws SQLException
  {
    int i = 1;
    if (paramObject == null)
      throw new SQLException("argument to comparison of '" + paramFieldType.getFieldName() + "' is null");
    if ((paramObject instanceof ArgumentHolder))
    {
      paramStringBuilder.append('?');
      ArgumentHolder localArgumentHolder = (ArgumentHolder)paramObject;
      localArgumentHolder.setMetaInfo(this.columnName, paramFieldType);
      paramList.add(localArgumentHolder);
    }
    while (true)
    {
      if (i != 0)
        paramStringBuilder.append(' ');
      return;
      if (paramFieldType.isSelectArgRequired())
      {
        paramStringBuilder.append('?');
        SelectArg localSelectArg = new SelectArg();
        localSelectArg.setMetaInfo(this.columnName, paramFieldType);
        localSelectArg.setValue(paramObject);
        paramList.add(localSelectArg);
        continue;
      }
      if ((paramFieldType.isForeign()) && (paramFieldType.getFieldType() == paramObject.getClass()))
      {
        FieldType localFieldType = paramFieldType.getForeignIdField();
        appendArgOrValue(paramDatabaseType, localFieldType, paramStringBuilder, paramList, localFieldType.extractJavaFieldValue(paramObject));
        i = 0;
        continue;
      }
      if (paramFieldType.isEscapedValue())
      {
        paramDatabaseType.appendEscapedWord(paramStringBuilder, paramFieldType.convertJavaFieldToSqlArgValue(paramObject).toString());
        continue;
      }
      paramStringBuilder.append(paramFieldType.convertJavaFieldToSqlArgValue(paramObject));
    }
  }

  public abstract void appendOperation(StringBuilder paramStringBuilder);

  public void appendSql(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramDatabaseType.appendEscapedEntityName(paramStringBuilder, this.columnName);
    paramStringBuilder.append(' ');
    appendOperation(paramStringBuilder);
    appendValue(paramDatabaseType, paramStringBuilder, paramList);
  }

  public void appendValue(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    appendArgOrValue(paramDatabaseType, this.fieldType, paramStringBuilder, paramList, this.value);
  }

  public String getColumnName()
  {
    return this.columnName;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.columnName).append(' ');
    appendOperation(localStringBuilder);
    localStringBuilder.append(' ');
    localStringBuilder.append(this.value);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.BaseComparison
 * JD-Core Version:    0.6.0
 */