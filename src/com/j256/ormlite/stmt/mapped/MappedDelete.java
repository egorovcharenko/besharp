package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedDelete<T, ID> extends BaseMappedStatement<T, ID>
{
  private MappedDelete(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType);
  }

  public static <T, ID> MappedDelete<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    FieldType localFieldType = paramTableInfo.getIdField();
    if (localFieldType == null)
      throw new SQLException("Cannot delete from " + paramTableInfo.getDataClass() + " because it doesn't have an id field");
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendTableName(paramDatabaseType, localStringBuilder, "DELETE FROM ", paramTableInfo.getTableName());
    appendWhereId(paramDatabaseType, localFieldType, localStringBuilder, null);
    String str = localStringBuilder.toString();
    FieldType[] arrayOfFieldType = new FieldType[1];
    arrayOfFieldType[0] = localFieldType;
    return new MappedDelete(paramTableInfo, str, arrayOfFieldType);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedDelete
 * JD-Core Version:    0.6.0
 */