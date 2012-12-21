package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedUpdate<T, ID> extends BaseMappedStatement<T, ID>
{
  private MappedUpdate(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType);
  }

  public static <T, ID> MappedUpdate<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    FieldType localFieldType1 = paramTableInfo.getIdField();
    if (localFieldType1 == null)
      throw new SQLException("Cannot update " + paramTableInfo.getDataClass() + " because it doesn't have an id field");
    if (paramTableInfo.getFieldTypes().length == 1)
      throw new SQLException("Cannot update " + paramTableInfo.getDataClass() + " with only the id field.  You should use updateId().");
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendTableName(paramDatabaseType, localStringBuilder, "UPDATE ", paramTableInfo.getTableName());
    int i = 1;
    int j = 0;
    FieldType[] arrayOfFieldType1 = paramTableInfo.getFieldTypes();
    int k = arrayOfFieldType1.length;
    for (int m = 0; m < k; m++)
    {
      if (!isFieldUpdatable(arrayOfFieldType1[m], localFieldType1))
        continue;
      j++;
    }
    FieldType[] arrayOfFieldType2 = new FieldType[j + 1];
    FieldType[] arrayOfFieldType3 = paramTableInfo.getFieldTypes();
    int n = arrayOfFieldType3.length;
    int i1 = 0;
    int i2 = 0;
    while (i1 < n)
    {
      FieldType localFieldType2 = arrayOfFieldType3[i1];
      int i3;
      if (!isFieldUpdatable(localFieldType2, localFieldType1))
      {
        i3 = i2;
        i1++;
        i2 = i3;
        continue;
      }
      if (i != 0)
      {
        localStringBuilder.append("SET ");
        i = 0;
      }
      while (true)
      {
        appendFieldColumnName(paramDatabaseType, localStringBuilder, localFieldType2, null);
        i3 = i2 + 1;
        arrayOfFieldType2[i2] = localFieldType2;
        localStringBuilder.append("= ?");
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append(' ');
    appendWhereId(paramDatabaseType, localFieldType1, localStringBuilder, null);
    (i2 + 1);
    arrayOfFieldType2[i2] = localFieldType1;
    return new MappedUpdate(paramTableInfo, localStringBuilder.toString(), arrayOfFieldType2);
  }

  private static boolean isFieldUpdatable(FieldType paramFieldType1, FieldType paramFieldType2)
  {
    if ((paramFieldType1 == paramFieldType2) || (paramFieldType1.isForeignCollection()));
    for (int i = 0; ; i = 1)
      return i;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedUpdate
 * JD-Core Version:    0.6.0
 */