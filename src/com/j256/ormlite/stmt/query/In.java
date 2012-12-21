package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class In extends BaseComparison
{
  private Iterable<?> objects;

  public In(String paramString, FieldType paramFieldType, Iterable<?> paramIterable)
    throws SQLException
  {
    super(paramString, paramFieldType, null);
    this.objects = paramIterable;
  }

  public In(String paramString, FieldType paramFieldType, Object[] paramArrayOfObject)
    throws SQLException
  {
    super(paramString, paramFieldType, null);
    this.objects = Arrays.asList(paramArrayOfObject);
  }

  public void appendOperation(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("IN ");
  }

  public void appendValue(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramStringBuilder.append('(');
    int i = 1;
    Iterator localIterator = this.objects.iterator();
    if (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject == null)
        throw new IllegalArgumentException("one of the IN values for '" + this.columnName + "' is null");
      if (i != 0)
        i = 0;
      while (true)
      {
        super.appendArgOrValue(paramDatabaseType, this.fieldType, paramStringBuilder, paramList, localObject);
        break;
        paramStringBuilder.append(',');
      }
    }
    paramStringBuilder.append(") ");
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.query.In
 * JD-Core Version:    0.6.0
 */