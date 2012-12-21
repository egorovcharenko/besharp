package com.j256.ormlite.table;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.ConnectionSource;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TableInfo<T, ID>
{
  private final Constructor<T> constructor;
  private final Dao<T, ID> dao;
  private final Class<T> dataClass;
  private Map<String, FieldType> fieldNameMap;
  private final FieldType[] fieldTypes;
  private final FieldType idField;
  private final String tableName;

  public TableInfo(DatabaseType paramDatabaseType, Dao<T, ID> paramDao, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    this.dao = paramDao;
    this.dataClass = paramDatabaseTableConfig.getDataClass();
    this.tableName = paramDatabaseTableConfig.getTableName();
    this.fieldTypes = paramDatabaseTableConfig.getFieldTypes(paramDatabaseType);
    Object localObject = null;
    for (FieldType localFieldType : this.fieldTypes)
    {
      if ((!localFieldType.isId()) && (!localFieldType.isGeneratedId()) && (!localFieldType.isGeneratedIdSequence()))
        continue;
      if (localObject != null)
        throw new SQLException("More than 1 idField configured for class " + this.dataClass + " (" + localObject + "," + localFieldType + ")");
      localObject = localFieldType;
    }
    if ((this.fieldTypes.length == 1) && (localObject != null) && (localObject.isGeneratedId()))
      throw new SQLException("Must have more than a single field which is a generated-id for class " + this.dataClass);
    this.idField = localObject;
    this.constructor = paramDatabaseTableConfig.getConstructor();
  }

  public TableInfo(ConnectionSource paramConnectionSource, Dao<T, ID> paramDao, Class<T> paramClass)
    throws SQLException
  {
    this(paramConnectionSource.getDatabaseType(), paramDao, DatabaseTableConfig.fromClass(paramConnectionSource, paramClass));
  }

  public static <T, ID> T createObject(Constructor<?> paramConstructor, Dao<T, ID> paramDao)
    throws SQLException
  {
    boolean bool = paramConstructor.isAccessible();
    if (!bool);
    try
    {
      paramConstructor.setAccessible(true);
      Object localObject2 = paramConstructor.newInstance(new Object[0]);
      if ((localObject2 instanceof BaseDaoEnabled))
        ((BaseDaoEnabled)localObject2).setDao(paramDao);
      return localObject2;
    }
    catch (Exception localException)
    {
      throw SqlExceptionUtil.create("Could not create object for " + paramConstructor.getDeclaringClass(), localException);
    }
    finally
    {
      if (!bool)
        paramConstructor.setAccessible(false);
    }
    throw localObject1;
  }

  public T createObject()
    throws SQLException
  {
    return createObject(this.constructor, this.dao);
  }

  public Constructor<T> getConstructor()
  {
    return this.constructor;
  }

  public Class<T> getDataClass()
  {
    return this.dataClass;
  }

  public FieldType getFieldTypeByColumnName(String paramString)
  {
    if (this.fieldNameMap == null)
    {
      HashMap localHashMap = new HashMap();
      for (FieldType localFieldType3 : this.fieldTypes)
        localHashMap.put(localFieldType3.getDbColumnName(), localFieldType3);
      this.fieldNameMap = localHashMap;
    }
    FieldType localFieldType1 = (FieldType)this.fieldNameMap.get(paramString);
    if (localFieldType1 == null)
    {
      for (FieldType localFieldType2 : this.fieldTypes)
      {
        if (!localFieldType2.getFieldName().equals(paramString))
          continue;
        throw new IllegalArgumentException("You should use columnName '" + localFieldType2.getDbColumnName() + "' for table " + this.tableName + " instead of fieldName '" + paramString + "'");
      }
      throw new IllegalArgumentException("Unknown column name '" + paramString + "' in table " + this.tableName);
    }
    return localFieldType1;
  }

  public FieldType[] getFieldTypes()
  {
    return this.fieldTypes;
  }

  public FieldType getIdField()
  {
    return this.idField;
  }

  public String getTableName()
  {
    return this.tableName;
  }

  public boolean isUpdatable()
  {
    if ((this.idField != null) && (this.fieldTypes.length > 1));
    for (int i = 1; ; i = 0)
      return i;
  }

  public String objectToString(T paramT)
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    localStringBuilder.append(paramT.getClass().getSimpleName());
    FieldType[] arrayOfFieldType = this.fieldTypes;
    int i = arrayOfFieldType.length;
    int j = 0;
    while (j < i)
    {
      FieldType localFieldType = arrayOfFieldType[j];
      localStringBuilder.append(' ').append(localFieldType.getDbColumnName()).append("=");
      try
      {
        localStringBuilder.append(localFieldType.extractJavaFieldValue(paramT));
        j++;
      }
      catch (Exception localException)
      {
        throw new IllegalStateException("Could not generate toString of field " + localFieldType, localException);
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.table.TableInfo
 * JD-Core Version:    0.6.0
 */