package com.j256.ormlite.table;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.JavaxPersistence;
import com.j256.ormlite.support.ConnectionSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseTableConfig<T>
{
  private Constructor<T> constructor;
  private Class<T> dataClass;
  private List<DatabaseFieldConfig> fieldConfigs;
  private FieldType[] fieldTypes;
  private String tableName;

  public DatabaseTableConfig()
  {
  }

  public DatabaseTableConfig(Class<T> paramClass, String paramString, List<DatabaseFieldConfig> paramList)
  {
    this.dataClass = paramClass;
    this.tableName = paramString;
    this.fieldConfigs = paramList;
  }

  private DatabaseTableConfig(Class<T> paramClass, String paramString, FieldType[] paramArrayOfFieldType)
  {
    this.dataClass = paramClass;
    this.tableName = paramString;
    this.fieldTypes = paramArrayOfFieldType;
  }

  public DatabaseTableConfig(Class<T> paramClass, List<DatabaseFieldConfig> paramList)
  {
    this(paramClass, extractTableName(paramClass), paramList);
  }

  private FieldType[] convertFieldConfigs(ConnectionSource paramConnectionSource, String paramString, List<DatabaseFieldConfig> paramList)
    throws SQLException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DatabaseFieldConfig localDatabaseFieldConfig = (DatabaseFieldConfig)localIterator.next();
      FieldType localFieldType = null;
      Class localClass = this.dataClass;
      while (true)
      {
        if (localClass != null);
        try
        {
          Field localField = localClass.getDeclaredField(localDatabaseFieldConfig.getFieldName());
          if (localField != null)
          {
            localFieldType = new FieldType(paramConnectionSource, paramString, localField, localDatabaseFieldConfig, this.dataClass);
            if (localFieldType != null)
              break;
            throw new SQLException("Could not find declared field with name '" + localDatabaseFieldConfig.getFieldName() + "' for " + this.dataClass);
          }
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          localClass = localClass.getSuperclass();
        }
      }
      localArrayList.add(localFieldType);
    }
    if (localArrayList.isEmpty())
      throw new SQLException("No fields were configured for class " + this.dataClass);
    return (FieldType[])localArrayList.toArray(new FieldType[localArrayList.size()]);
  }

  private static <T> FieldType[] extractFieldTypes(ConnectionSource paramConnectionSource, Class<T> paramClass, String paramString)
    throws SQLException
  {
    ArrayList localArrayList = new ArrayList();
    for (Object localObject = paramClass; localObject != null; localObject = ((Class)localObject).getSuperclass())
    {
      Field[] arrayOfField = ((Class)localObject).getDeclaredFields();
      int i = arrayOfField.length;
      for (int j = 0; j < i; j++)
      {
        FieldType localFieldType = FieldType.createFieldType(paramConnectionSource, paramString, arrayOfField[j], paramClass);
        if (localFieldType == null)
          continue;
        localArrayList.add(localFieldType);
      }
    }
    if (localArrayList.isEmpty())
      throw new IllegalArgumentException("No fields have a " + DatabaseField.class.getSimpleName() + " annotation in " + paramClass);
    return (FieldType)(FieldType[])localArrayList.toArray(new FieldType[localArrayList.size()]);
  }

  public static <T> FieldType extractIdFieldType(ConnectionSource paramConnectionSource, Class<T> paramClass, String paramString)
    throws SQLException
  {
    Object localObject = paramClass;
    int j;
    label20: FieldType localFieldType2;
    if (localObject != null)
    {
      Field[] arrayOfField = ((Class)localObject).getDeclaredFields();
      int i = arrayOfField.length;
      j = 0;
      if (j < i)
      {
        localFieldType2 = FieldType.createFieldType(paramConnectionSource, paramString, arrayOfField[j], paramClass);
        if ((localFieldType2 == null) || ((!localFieldType2.isId()) && (!localFieldType2.isGeneratedId()) && (!localFieldType2.isGeneratedIdSequence())));
      }
    }
    for (FieldType localFieldType1 = localFieldType2; ; localFieldType1 = null)
    {
      return localFieldType1;
      j++;
      break label20;
      localObject = ((Class)localObject).getSuperclass();
      break;
    }
  }

  public static <T> String extractTableName(Class<T> paramClass)
  {
    DatabaseTable localDatabaseTable = (DatabaseTable)paramClass.getAnnotation(DatabaseTable.class);
    String str;
    if ((localDatabaseTable != null) && (localDatabaseTable.tableName() != null) && (localDatabaseTable.tableName().length() > 0))
      str = localDatabaseTable.tableName();
    while (true)
    {
      return str;
      str = JavaxPersistence.getEntityName(paramClass);
      if (str != null)
        continue;
      str = paramClass.getSimpleName().toLowerCase();
    }
  }

  public static <T> Constructor<T> findNoArgConstructor(Class<T> paramClass)
  {
    while (true)
    {
      int j;
      try
      {
        Constructor[] arrayOfConstructor = (Constructor[])paramClass.getDeclaredConstructors();
        int i = arrayOfConstructor.length;
        j = 0;
        if (j >= i)
          break;
        Constructor localConstructor = arrayOfConstructor[j];
        if (localConstructor.getParameterTypes().length == 0)
          return localConstructor;
      }
      catch (Exception localException)
      {
        throw new IllegalArgumentException("Can't lookup declared constructors for " + paramClass, localException);
      }
      j++;
    }
    if (paramClass.getEnclosingClass() == null)
      throw new IllegalArgumentException("Can't find a no-arg constructor for " + paramClass);
    throw new IllegalArgumentException("Can't find a no-arg constructor for " + paramClass + ".  Missing static on inner class?");
  }

  public static <T> DatabaseTableConfig<T> fromClass(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    String str = extractTableName(paramClass);
    if (paramConnectionSource.getDatabaseType().isEntityNamesMustBeUpCase())
      str = str.toUpperCase();
    return new DatabaseTableConfig(paramClass, str, extractFieldTypes(paramConnectionSource, paramClass, str));
  }

  public void extractFieldTypes(ConnectionSource paramConnectionSource)
    throws SQLException
  {
    if (this.fieldTypes == null)
      if (this.fieldConfigs != null)
        break label31;
    label31: for (this.fieldTypes = extractFieldTypes(paramConnectionSource, this.dataClass, this.tableName); ; this.fieldTypes = convertFieldConfigs(paramConnectionSource, this.tableName, this.fieldConfigs))
      return;
  }

  public Constructor<T> getConstructor()
  {
    if (this.constructor == null)
      this.constructor = findNoArgConstructor(this.dataClass);
    return this.constructor;
  }

  public Class<T> getDataClass()
  {
    return this.dataClass;
  }

  public List<DatabaseFieldConfig> getFieldConfigs()
  {
    return this.fieldConfigs;
  }

  public FieldType[] getFieldTypes(DatabaseType paramDatabaseType)
    throws SQLException
  {
    if (this.fieldTypes == null)
      throw new SQLException("Field types have not been extracted in table config");
    return this.fieldTypes;
  }

  public String getTableName()
  {
    return this.tableName;
  }

  public void initialize()
  {
    if (this.dataClass == null)
      throw new IllegalStateException("dataClass was never set on " + getClass().getSimpleName());
    if (this.tableName == null)
      this.tableName = extractTableName(this.dataClass);
  }

  public void setConstructor(Constructor<T> paramConstructor)
  {
    this.constructor = paramConstructor;
  }

  public void setDataClass(Class<T> paramClass)
  {
    this.dataClass = paramClass;
  }

  public void setFieldConfigs(List<DatabaseFieldConfig> paramList)
  {
    this.fieldConfigs = paramList;
  }

  public void setTableName(String paramString)
  {
    this.tableName = paramString;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.table.DatabaseTableConfig
 * JD-Core Version:    0.6.0
 */