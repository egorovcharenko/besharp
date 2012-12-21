package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedQueryForId<T, ID> extends BaseMappedQuery<T, ID>
{
  private final String label;

  protected MappedQueryForId(TableInfo<T, ID> paramTableInfo, String paramString1, FieldType[] paramArrayOfFieldType1, FieldType[] paramArrayOfFieldType2, String paramString2)
  {
    super(paramTableInfo, paramString1, paramArrayOfFieldType1, paramArrayOfFieldType2);
    this.label = paramString2;
  }

  public static <T, ID> MappedQueryForId<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    String str = buildStatement(paramDatabaseType, paramTableInfo);
    FieldType[] arrayOfFieldType = new FieldType[1];
    arrayOfFieldType[0] = paramTableInfo.getIdField();
    return new MappedQueryForId(paramTableInfo, str, arrayOfFieldType, paramTableInfo.getFieldTypes(), "query-for-id");
  }

  protected static <T, ID> String buildStatement(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    FieldType localFieldType = paramTableInfo.getIdField();
    if (localFieldType == null)
      throw new SQLException("Cannot query-for-id with " + paramTableInfo.getDataClass() + " because it doesn't have an id field");
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendTableName(paramDatabaseType, localStringBuilder, "SELECT * FROM ", paramTableInfo.getTableName());
    appendWhereId(paramDatabaseType, localFieldType, localStringBuilder, null);
    return localStringBuilder.toString();
  }

  private void logArgs(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length > 0)
    {
      Logger localLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.label;
      arrayOfObject[1] = paramArrayOfObject;
      localLogger.trace("{} arguments: {}", arrayOfObject);
    }
  }

  public T execute(DatabaseConnection paramDatabaseConnection, ID paramID)
    throws SQLException
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = convertIdToFieldObject(paramID);
    String str = this.statement;
    FieldType[] arrayOfFieldType = new FieldType[1];
    arrayOfFieldType[0] = this.idField;
    Object localObject = paramDatabaseConnection.queryForOne(str, arrayOfObject1, arrayOfFieldType, this);
    if (localObject == null)
    {
      Logger localLogger3 = logger;
      Object[] arrayOfObject4 = new Object[3];
      arrayOfObject4[0] = this.label;
      arrayOfObject4[1] = this.statement;
      arrayOfObject4[2] = Integer.valueOf(arrayOfObject1.length);
      localLogger3.debug("{} using '{}' and {} args, got no results", arrayOfObject4);
    }
    while (true)
    {
      logArgs(arrayOfObject1);
      return localObject;
      if (localObject == DatabaseConnection.MORE_THAN_ONE)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[3];
        arrayOfObject3[0] = this.label;
        arrayOfObject3[1] = this.statement;
        arrayOfObject3[2] = Integer.valueOf(arrayOfObject1.length);
        localLogger2.error("{} using '{}' and {} args, got >1 results", arrayOfObject3);
        logArgs(arrayOfObject1);
        throw new SQLException(this.label + " got more than 1 result: " + this.statement);
      }
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.label;
      arrayOfObject2[1] = this.statement;
      arrayOfObject2[2] = Integer.valueOf(arrayOfObject1.length);
      localLogger1.debug("{} using '{}' and {} args, got 1 result", arrayOfObject2);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedQueryForId
 * JD-Core Version:    0.6.0
 */