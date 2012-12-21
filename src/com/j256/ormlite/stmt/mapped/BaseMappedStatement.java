package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseMappedStatement<T, ID>
{
  protected static Logger logger = LoggerFactory.getLogger(BaseMappedStatement.class);
  protected final FieldType[] argFieldTypes;
  protected final FieldType idField;
  protected final String statement;
  protected final TableInfo<T, ID> tableInfo;

  protected BaseMappedStatement(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType)
  {
    this.tableInfo = paramTableInfo;
    this.idField = paramTableInfo.getIdField();
    this.statement = paramString;
    this.argFieldTypes = paramArrayOfFieldType;
  }

  static void appendFieldColumnName(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, FieldType paramFieldType, List<FieldType> paramList)
  {
    paramDatabaseType.appendEscapedEntityName(paramStringBuilder, paramFieldType.getDbColumnName());
    if (paramList != null)
      paramList.add(paramFieldType);
    paramStringBuilder.append(' ');
  }

  static void appendTableName(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    if (paramString1 != null)
      paramStringBuilder.append(paramString1);
    paramDatabaseType.appendEscapedEntityName(paramStringBuilder, paramString2);
    paramStringBuilder.append(' ');
  }

  static void appendWhereId(DatabaseType paramDatabaseType, FieldType paramFieldType, StringBuilder paramStringBuilder, List<FieldType> paramList)
  {
    paramStringBuilder.append("WHERE ");
    appendFieldColumnName(paramDatabaseType, paramStringBuilder, paramFieldType, paramList);
    paramStringBuilder.append("= ?");
  }

  protected Object convertIdToFieldObject(ID paramID)
    throws SQLException
  {
    return this.idField.convertJavaFieldToSqlArgValue(paramID);
  }

  public int delete(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    try
    {
      Object[] arrayOfObject1 = getFieldObjects(paramT);
      int i = paramDatabaseConnection.delete(this.statement, arrayOfObject1, this.argFieldTypes);
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.statement;
      arrayOfObject2[1] = Integer.valueOf(arrayOfObject1.length);
      arrayOfObject2[2] = Integer.valueOf(i);
      localLogger1.debug("delete data with statement '{}' and {} args, changed {} rows", arrayOfObject2);
      if (arrayOfObject1.length > 0)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = arrayOfObject1;
        localLogger2.trace("delete arguments: {}", arrayOfObject3);
      }
      return i;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Unable to run delete stmt on object " + paramT + ": " + this.statement, localSQLException);
  }

  protected Object extractIdToFieldObject(T paramT)
    throws SQLException
  {
    return this.idField.extractJavaFieldToSqlArgValue(paramT);
  }

  protected Object[] getFieldObjects(Object paramObject)
    throws SQLException
  {
    Object[] arrayOfObject = new Object[this.argFieldTypes.length];
    for (int i = 0; i < this.argFieldTypes.length; i++)
    {
      FieldType localFieldType = this.argFieldTypes[i];
      arrayOfObject[i] = localFieldType.extractJavaFieldToSqlArgValue(paramObject);
      if ((arrayOfObject[i] != null) || (localFieldType.getDefaultValue() == null))
        continue;
      arrayOfObject[i] = localFieldType.getDefaultValue();
    }
    return arrayOfObject;
  }

  public String getStatement()
  {
    return this.statement;
  }

  public String toString()
  {
    return "MappedStatement: " + this.statement;
  }

  public int update(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    try
    {
      Object[] arrayOfObject1 = getFieldObjects(paramT);
      int i = paramDatabaseConnection.update(this.statement, arrayOfObject1, this.argFieldTypes);
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.statement;
      arrayOfObject2[1] = Integer.valueOf(arrayOfObject1.length);
      arrayOfObject2[2] = Integer.valueOf(i);
      localLogger1.debug("update data with statement '{}' and {} args, changed {} rows", arrayOfObject2);
      if (arrayOfObject1.length > 0)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = arrayOfObject1;
        localLogger2.trace("update arguments: {}", arrayOfObject3);
      }
      return i;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Unable to run update stmt on object " + paramT + ": " + this.statement, localSQLException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.BaseMappedStatement
 * JD-Core Version:    0.6.0
 */