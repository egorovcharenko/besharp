package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class MappedDeleteCollection<T, ID> extends BaseMappedStatement<T, ID>
{
  private MappedDeleteCollection(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType);
  }

  private static void appendWhereIds(DatabaseType paramDatabaseType, FieldType paramFieldType, StringBuilder paramStringBuilder, int paramInt, FieldType[] paramArrayOfFieldType)
  {
    paramStringBuilder.append("WHERE ");
    paramDatabaseType.appendEscapedEntityName(paramStringBuilder, paramFieldType.getDbColumnName());
    paramStringBuilder.append(" IN (");
    int i = 1;
    int j = 0;
    if (j < paramInt)
    {
      if (i != 0)
        i = 0;
      while (true)
      {
        paramStringBuilder.append('?');
        if (paramArrayOfFieldType != null)
          paramArrayOfFieldType[j] = paramFieldType;
        j++;
        break;
        paramStringBuilder.append(',');
      }
    }
    paramStringBuilder.append(") ");
  }

  private static <T, ID> MappedDeleteCollection<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, int paramInt)
    throws SQLException
  {
    FieldType localFieldType = paramTableInfo.getIdField();
    if (localFieldType == null)
      throw new SQLException("Cannot delete " + paramTableInfo.getDataClass() + " because it doesn't have an id field defined");
    StringBuilder localStringBuilder = new StringBuilder(128);
    appendTableName(paramDatabaseType, localStringBuilder, "DELETE FROM ", paramTableInfo.getTableName());
    FieldType[] arrayOfFieldType = new FieldType[paramInt];
    appendWhereIds(paramDatabaseType, localFieldType, localStringBuilder, paramInt, arrayOfFieldType);
    return new MappedDeleteCollection(paramTableInfo, localStringBuilder.toString(), arrayOfFieldType);
  }

  public static <T, ID> int deleteIds(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, DatabaseConnection paramDatabaseConnection, Collection<ID> paramCollection)
    throws SQLException
  {
    MappedDeleteCollection localMappedDeleteCollection = build(paramDatabaseType, paramTableInfo, paramCollection.size());
    Object[] arrayOfObject = new Object[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      arrayOfObject[i] = paramTableInfo.getIdField().convertJavaFieldToSqlArgValue(localObject);
      i++;
    }
    return updateRows(paramDatabaseConnection, localMappedDeleteCollection, arrayOfObject);
  }

  public static <T, ID> int deleteObjects(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, DatabaseConnection paramDatabaseConnection, Collection<T> paramCollection)
    throws SQLException
  {
    MappedDeleteCollection localMappedDeleteCollection = build(paramDatabaseType, paramTableInfo, paramCollection.size());
    Object[] arrayOfObject = new Object[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      arrayOfObject[i] = paramTableInfo.getIdField().extractJavaFieldToSqlArgValue(localObject);
      i++;
    }
    return updateRows(paramDatabaseConnection, localMappedDeleteCollection, arrayOfObject);
  }

  private static <T, ID> int updateRows(DatabaseConnection paramDatabaseConnection, MappedDeleteCollection<T, ID> paramMappedDeleteCollection, Object[] paramArrayOfObject)
    throws SQLException
  {
    try
    {
      int i = paramDatabaseConnection.delete(paramMappedDeleteCollection.statement, paramArrayOfObject, paramMappedDeleteCollection.argFieldTypes);
      Logger localLogger1 = logger;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramMappedDeleteCollection.statement;
      arrayOfObject1[1] = Integer.valueOf(paramArrayOfObject.length);
      arrayOfObject1[2] = Integer.valueOf(i);
      localLogger1.debug("delete-collection with statement '{}' and {} args, changed {} rows", arrayOfObject1);
      if (paramArrayOfObject.length > 0)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramArrayOfObject;
        localLogger2.trace("delete-collection arguments: {}", arrayOfObject2);
      }
      return i;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Unable to run delete collection stmt: " + paramMappedDeleteCollection.statement, localSQLException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedDeleteCollection
 * JD-Core Version:    0.6.0
 */