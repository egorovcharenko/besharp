package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedCreate<T, ID> extends BaseMappedStatement<T, ID>
{
  private String dataClassName;
  private final String queryNextSequenceStmt;

  private MappedCreate(TableInfo<T, ID> paramTableInfo, String paramString1, FieldType[] paramArrayOfFieldType, String paramString2)
  {
    super(paramTableInfo, paramString1, paramArrayOfFieldType);
    this.dataClassName = paramTableInfo.getDataClass().getSimpleName();
    this.queryNextSequenceStmt = paramString2;
  }

  private void assignIdValue(T paramT, Number paramNumber, String paramString)
    throws SQLException
  {
    this.idField.assignIdValue(paramT, paramNumber);
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramNumber;
    arrayOfObject[1] = paramString;
    arrayOfObject[2] = this.idField.getFieldName();
    arrayOfObject[3] = this.dataClassName;
    localLogger.debug("assigned id '{}' from {} to '{}' in {} object", arrayOfObject);
  }

  private void assignSequenceId(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    long l = paramDatabaseConnection.queryForLong(this.queryNextSequenceStmt);
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(l);
    arrayOfObject[1] = this.queryNextSequenceStmt;
    localLogger.debug("queried for sequence {} using stmt: {}", arrayOfObject);
    if (l == 0L)
      throw new SQLException("Should not have returned 0 for stmt: " + this.queryNextSequenceStmt);
    assignIdValue(paramT, Long.valueOf(l), "sequence");
  }

  public static <T, ID> MappedCreate<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    appendTableName(paramDatabaseType, localStringBuilder, "INSERT INTO ", paramTableInfo.getTableName());
    localStringBuilder.append('(');
    int i = 0;
    FieldType[] arrayOfFieldType1 = paramTableInfo.getFieldTypes();
    int j = arrayOfFieldType1.length;
    for (int k = 0; k < j; k++)
    {
      if (!isFieldCreatable(paramDatabaseType, arrayOfFieldType1[k]))
        continue;
      i++;
    }
    FieldType[] arrayOfFieldType2 = new FieldType[i];
    int m = 1;
    FieldType[] arrayOfFieldType3 = paramTableInfo.getFieldTypes();
    int n = arrayOfFieldType3.length;
    int i1 = 0;
    int i2 = 0;
    while (i1 < n)
    {
      FieldType localFieldType = arrayOfFieldType3[i1];
      int i6;
      if (!isFieldCreatable(paramDatabaseType, localFieldType))
      {
        i6 = i2;
        i1++;
        i2 = i6;
        continue;
      }
      if (m != 0)
        m = 0;
      while (true)
      {
        appendFieldColumnName(paramDatabaseType, localStringBuilder, localFieldType, null);
        i6 = i2 + 1;
        arrayOfFieldType2[i2] = localFieldType;
        break;
        localStringBuilder.append(",");
      }
    }
    localStringBuilder.append(") VALUES (");
    int i3 = 1;
    FieldType[] arrayOfFieldType4 = paramTableInfo.getFieldTypes();
    int i4 = arrayOfFieldType4.length;
    int i5 = 0;
    while (i5 < i4)
    {
      if (!isFieldCreatable(paramDatabaseType, arrayOfFieldType4[i5]))
      {
        i5++;
        continue;
      }
      if (i3 != 0)
        i3 = 0;
      while (true)
      {
        localStringBuilder.append("?");
        break;
        localStringBuilder.append(",");
      }
    }
    localStringBuilder.append(")");
    String str = buildQueryNextSequence(paramDatabaseType, paramTableInfo.getIdField());
    return new MappedCreate(paramTableInfo, localStringBuilder.toString(), arrayOfFieldType2, str);
  }

  private static String buildQueryNextSequence(DatabaseType paramDatabaseType, FieldType paramFieldType)
  {
    String str1 = null;
    if (paramFieldType == null);
    while (true)
    {
      return str1;
      String str2 = paramFieldType.getGeneratedIdSequence();
      if (str2 == null)
        continue;
      StringBuilder localStringBuilder = new StringBuilder(64);
      paramDatabaseType.appendSelectNextValFromSequence(localStringBuilder, str2);
      str1 = localStringBuilder.toString();
    }
  }

  private int createWithGeneratedId(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    Object[] arrayOfObject1 = getFieldObjects(paramT);
    int i;
    Number localNumber;
    try
    {
      KeyHolder localKeyHolder = new KeyHolder(null);
      i = paramDatabaseConnection.insert(this.statement, arrayOfObject1, this.argFieldTypes, localKeyHolder);
      Logger localLogger3 = logger;
      Object[] arrayOfObject4 = new Object[3];
      arrayOfObject4[0] = this.statement;
      arrayOfObject4[1] = Integer.valueOf(arrayOfObject1.length);
      arrayOfObject4[2] = Integer.valueOf(i);
      localLogger3.debug("create object using '{}' and {} args, changed {} rows", arrayOfObject4);
      if (arrayOfObject1.length > 0)
      {
        Logger localLogger4 = logger;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = arrayOfObject1;
        localLogger4.trace("create arguments: {}", arrayOfObject5);
      }
      if (i != 1)
        break label280;
      localNumber = localKeyHolder.getKey();
      if (localNumber == null)
        throw new SQLException("generated-id key was not set by the update call");
    }
    catch (SQLException localSQLException)
    {
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.statement;
      arrayOfObject2[1] = Integer.valueOf(arrayOfObject1.length);
      arrayOfObject2[2] = localSQLException;
      localLogger1.error("create object using '{}' and {} args, threw exception: {}", arrayOfObject2);
      if (arrayOfObject1.length > 0)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = arrayOfObject1;
        localLogger2.trace("create arguments: {}", arrayOfObject3);
      }
      throw SqlExceptionUtil.create("Unable to run stmt on object " + paramT + ": " + this.statement, localSQLException);
    }
    if (localNumber.longValue() == 0L)
      throw new SQLException("generated-id key must not be 0 value");
    assignIdValue(paramT, localNumber, "keyholder");
    label280: return i;
  }

  private static boolean isFieldCreatable(DatabaseType paramDatabaseType, FieldType paramFieldType)
  {
    int i;
    if (paramFieldType.isForeignCollection())
      i = 0;
    while (true)
    {
      return i;
      if ((paramDatabaseType.isIdSequenceNeeded()) && (paramDatabaseType.isSelectSequenceBeforeInsert()))
      {
        i = 1;
        continue;
      }
      if ((paramFieldType.isGeneratedId()) && (!paramFieldType.isSelfGeneratedId()))
      {
        i = 0;
        continue;
      }
      i = 1;
    }
  }

  public int insert(DatabaseType paramDatabaseType, DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    if (this.idField != null)
    {
      if (!this.idField.isSelfGeneratedId())
        break label143;
      this.idField.assignField(paramT, this.idField.generatedId());
    }
    try
    {
      Object[] arrayOfObject1 = getFieldObjects(paramT);
      int i = paramDatabaseConnection.insert(this.statement, arrayOfObject1, this.argFieldTypes);
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.statement;
      arrayOfObject2[1] = Integer.valueOf(arrayOfObject1.length);
      arrayOfObject2[2] = Integer.valueOf(i);
      localLogger1.debug("insert data with statement '{}' and {} args, changed {} rows", arrayOfObject2);
      if (arrayOfObject1.length > 0)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = arrayOfObject1;
        localLogger2.trace("insert arguments: {}", arrayOfObject3);
      }
      for (int j = i; ; j = createWithGeneratedId(paramDatabaseConnection, paramT))
      {
        return j;
        label143: if ((this.idField.isGeneratedIdSequence()) && (paramDatabaseType.isSelectSequenceBeforeInsert()))
        {
          assignSequenceId(paramDatabaseConnection, paramT);
          break;
        }
        if (!this.idField.isGeneratedId())
          break;
      }
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Unable to run insert stmt on object " + paramT + ": " + this.statement, localSQLException);
  }

  private static class KeyHolder
    implements GeneratedKeyHolder
  {
    Number key;

    public void addKey(Number paramNumber)
      throws SQLException
    {
      if (this.key == null)
      {
        this.key = paramNumber;
        return;
      }
      throw new SQLException("generated key has already been set to " + this.key + ", now set to " + paramNumber);
    }

    public Number getKey()
    {
      return this.key;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedCreate
 * JD-Core Version:    0.6.0
 */