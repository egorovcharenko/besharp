package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedUpdateId<T, ID> extends BaseMappedStatement<T, ID>
{
  private MappedUpdateId(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType);
  }

  public static <T, ID> MappedUpdateId<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    FieldType localFieldType = paramTableInfo.getIdField();
    if (localFieldType == null)
      throw new SQLException("Cannot update-id in " + paramTableInfo.getDataClass() + " because it doesn't have an id field");
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendTableName(paramDatabaseType, localStringBuilder, "UPDATE ", paramTableInfo.getTableName());
    localStringBuilder.append("SET ");
    appendFieldColumnName(paramDatabaseType, localStringBuilder, localFieldType, null);
    localStringBuilder.append("= ? ");
    appendWhereId(paramDatabaseType, localFieldType, localStringBuilder, null);
    String str = localStringBuilder.toString();
    FieldType[] arrayOfFieldType = new FieldType[2];
    arrayOfFieldType[0] = localFieldType;
    arrayOfFieldType[1] = localFieldType;
    return new MappedUpdateId(paramTableInfo, str, arrayOfFieldType);
  }

  public int execute(DatabaseConnection paramDatabaseConnection, T paramT, ID paramID)
    throws SQLException
  {
    try
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = convertIdToFieldObject(paramID);
      arrayOfObject1[1] = extractIdToFieldObject(paramT);
      int i = paramDatabaseConnection.update(this.statement, arrayOfObject1, this.argFieldTypes);
      if (i == 1)
        this.idField.assignField(paramT, paramID);
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.statement;
      arrayOfObject2[1] = Integer.valueOf(arrayOfObject1.length);
      arrayOfObject2[2] = Integer.valueOf(i);
      localLogger1.debug("updating-id with statement '{}' and {} args, changed {} rows", arrayOfObject2);
      if (arrayOfObject1.length > 0)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = arrayOfObject1;
        localLogger2.trace("updating-id arguments: {}", arrayOfObject3);
      }
      return i;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Unable to run update-id stmt on object " + paramT + ": " + this.statement, localSQLException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedUpdateId
 * JD-Core Version:    0.6.0
 */