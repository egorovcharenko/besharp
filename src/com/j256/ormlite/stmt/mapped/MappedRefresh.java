package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedRefresh<T, ID> extends MappedQueryForId<T, ID>
{
  private MappedRefresh(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType1, FieldType[] paramArrayOfFieldType2)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType1, paramArrayOfFieldType2, "refresh");
  }

  public static <T, ID> MappedRefresh<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    String str = buildStatement(paramDatabaseType, paramTableInfo);
    FieldType[] arrayOfFieldType = new FieldType[1];
    arrayOfFieldType[0] = paramTableInfo.getIdField();
    return new MappedRefresh(paramTableInfo, str, arrayOfFieldType, paramTableInfo.getFieldTypes());
  }

  public int executeRefresh(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    Object localObject = super.execute(paramDatabaseConnection, this.idField.extractJavaFieldValue(paramT));
    if (localObject == null);
    for (int k = 0; ; k = 1)
    {
      return k;
      for (FieldType localFieldType : this.resultsFieldTypes)
      {
        if (localFieldType == this.idField)
          continue;
        localFieldType.assignField(paramT, localFieldType.extractJavaFieldValue(localObject));
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedRefresh
 * JD-Core Version:    0.6.0
 */