package com.j256.ormlite.field;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.misc.JavaxPersistence;
import com.j256.ormlite.table.DatabaseTableConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class DatabaseFieldConfig
{
  private boolean canBeNull;
  private String columnName;
  private DataPersister dataPersister;
  private String defaultValue;
  private String fieldName;
  private boolean foreign;
  private boolean foreignAutoRefresh;
  private boolean foreignCollection;
  private boolean foreignCollectionEager;
  private String foreignCollectionOrderColumn;
  private DatabaseTableConfig<?> foreignTableConfig;
  private String format;
  private boolean generatedId;
  private String generatedIdSequence;
  private boolean id;
  private String indexName;
  private int maxEagerForeignCollectionLevel = 1;
  private int maxForeignAutoRefreshLevel = 2;
  private Class<? extends DataPersister> persisterClass;
  private boolean throwIfNull;
  private boolean unique;
  private boolean uniqueCombo;
  private String uniqueIndexName;
  private Enum<?> unknownEnumvalue;
  private boolean useGetSet;
  private int width;

  public DatabaseFieldConfig()
  {
  }

  public DatabaseFieldConfig(String paramString)
  {
    this.fieldName = paramString;
  }

  public DatabaseFieldConfig(String paramString1, String paramString2, DataPersister paramDataPersister, String paramString3, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString4, boolean paramBoolean4, DatabaseTableConfig<?> paramDatabaseTableConfig, boolean paramBoolean5, Enum<?> paramEnum, boolean paramBoolean6, String paramString5, boolean paramBoolean7, String paramString6, String paramString7, boolean paramBoolean8, int paramInt2, int paramInt3)
  {
    this.fieldName = paramString1;
    this.columnName = paramString2;
    this.dataPersister = paramDataPersister;
    this.defaultValue = paramString3;
    this.width = paramInt1;
    this.canBeNull = paramBoolean1;
    this.id = paramBoolean2;
    this.generatedId = paramBoolean3;
    this.generatedIdSequence = paramString4;
    this.foreign = paramBoolean4;
    this.foreignTableConfig = paramDatabaseTableConfig;
    this.useGetSet = paramBoolean5;
    this.unknownEnumvalue = paramEnum;
    this.throwIfNull = paramBoolean6;
    this.format = paramString5;
    this.unique = paramBoolean7;
    this.indexName = paramString6;
    this.uniqueIndexName = paramString7;
    this.foreignAutoRefresh = paramBoolean8;
    this.maxForeignAutoRefreshLevel = paramInt2;
    this.maxEagerForeignCollectionLevel = paramInt3;
  }

  public DatabaseFieldConfig(String paramString1, String paramString2, DataType paramDataType, String paramString3, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString4, boolean paramBoolean4, DatabaseTableConfig<?> paramDatabaseTableConfig, boolean paramBoolean5, Enum<?> paramEnum, boolean paramBoolean6, String paramString5, boolean paramBoolean7, String paramString6, String paramString7, boolean paramBoolean8, int paramInt2, int paramInt3)
  {
  }

  public static Method findGetMethod(Field paramField, boolean paramBoolean)
  {
    String str = methodFromField(paramField, "get");
    Method localMethod2;
    Method localMethod1;
    try
    {
      localMethod2 = paramField.getDeclaringClass().getMethod(str, new Class[0]);
      if (localMethod2.getReturnType() != paramField.getType())
        if (paramBoolean)
          throw new IllegalArgumentException("Return type of get method " + str + " does not return " + paramField.getType());
    }
    catch (Exception localException)
    {
      if (paramBoolean)
        throw new IllegalArgumentException("Could not find appropriate get method for " + paramField);
      localMethod1 = null;
    }
    while (true)
    {
      return localMethod1;
      localMethod1 = null;
      continue;
      localMethod1 = localMethod2;
    }
  }

  private static String findIndexName(String paramString1, String paramString2, boolean paramBoolean, DatabaseFieldConfig paramDatabaseFieldConfig)
  {
    String str;
    if (paramString2.length() > 0)
      str = paramString2;
    while (true)
    {
      return str;
      if (paramBoolean)
      {
        if (paramDatabaseFieldConfig.columnName == null)
        {
          str = paramString1 + "_" + paramDatabaseFieldConfig.fieldName + "_idx";
          continue;
        }
        str = paramString1 + "_" + paramDatabaseFieldConfig.columnName + "_idx";
        continue;
      }
      str = null;
    }
  }

  private static Enum<?> findMatchingEnumVal(Field paramField, String paramString)
  {
    for (Enum localEnum : (Enum[])(Enum[])paramField.getType().getEnumConstants())
      if (localEnum.name().equals(paramString))
        return localEnum;
    throw new IllegalArgumentException("Unknwown enum unknown name " + paramString + " for field " + paramField);
  }

  public static Method findSetMethod(Field paramField, boolean paramBoolean)
  {
    String str = methodFromField(paramField, "set");
    Method localMethod2;
    Method localMethod1;
    try
    {
      Class localClass = paramField.getDeclaringClass();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = paramField.getType();
      localMethod2 = localClass.getMethod(str, arrayOfClass);
      if (localMethod2.getReturnType() != Void.TYPE)
        if (paramBoolean)
          throw new IllegalArgumentException("Return type of set method " + str + " returns " + localMethod2.getReturnType() + " instead of void");
    }
    catch (Exception localException)
    {
      if (paramBoolean)
        throw new IllegalArgumentException("Could not find appropriate set method for " + paramField);
      localMethod1 = null;
    }
    while (true)
    {
      return localMethod1;
      localMethod1 = null;
      continue;
      localMethod1 = localMethod2;
    }
  }

  private static DatabaseFieldConfig fromDatabaseField(DatabaseType paramDatabaseType, String paramString, Field paramField, DatabaseField paramDatabaseField)
  {
    DatabaseFieldConfig localDatabaseFieldConfig = new DatabaseFieldConfig();
    localDatabaseFieldConfig.fieldName = paramField.getName();
    if (paramDatabaseType.isEntityNamesMustBeUpCase())
      localDatabaseFieldConfig.fieldName = localDatabaseFieldConfig.fieldName.toUpperCase();
    if (paramDatabaseField.columnName().length() > 0)
    {
      localDatabaseFieldConfig.columnName = paramDatabaseField.columnName();
      if (paramDatabaseField.dataType() != null)
        break label371;
      localDatabaseFieldConfig.dataPersister = null;
      label78: if (!paramDatabaseField.defaultValue().equals("__ormlite__ no default value string was specified"))
        break label388;
      localDatabaseFieldConfig.defaultValue = null;
      label98: localDatabaseFieldConfig.width = paramDatabaseField.width();
      localDatabaseFieldConfig.canBeNull = paramDatabaseField.canBeNull();
      localDatabaseFieldConfig.id = paramDatabaseField.id();
      localDatabaseFieldConfig.generatedId = paramDatabaseField.generatedId();
      if (paramDatabaseField.generatedIdSequence().length() <= 0)
        break label402;
      localDatabaseFieldConfig.generatedIdSequence = paramDatabaseField.generatedIdSequence();
      label165: localDatabaseFieldConfig.foreign = paramDatabaseField.foreign();
      localDatabaseFieldConfig.useGetSet = paramDatabaseField.useGetSet();
      if (paramDatabaseField.unknownEnumName().length() <= 0)
        break label411;
      localDatabaseFieldConfig.unknownEnumvalue = findMatchingEnumVal(paramField, paramDatabaseField.unknownEnumName());
      label214: localDatabaseFieldConfig.throwIfNull = paramDatabaseField.throwIfNull();
      if (paramDatabaseField.format().length() <= 0)
        break label420;
      localDatabaseFieldConfig.format = paramDatabaseField.format();
      label248: localDatabaseFieldConfig.unique = paramDatabaseField.unique();
      localDatabaseFieldConfig.uniqueCombo = paramDatabaseField.uniqueCombo();
      localDatabaseFieldConfig.indexName = findIndexName(paramString, paramDatabaseField.indexName(), paramDatabaseField.index(), localDatabaseFieldConfig);
      localDatabaseFieldConfig.uniqueIndexName = findIndexName(paramString, paramDatabaseField.uniqueIndexName(), paramDatabaseField.uniqueIndex(), localDatabaseFieldConfig);
      localDatabaseFieldConfig.foreignAutoRefresh = paramDatabaseField.foreignAutoRefresh();
      if (paramDatabaseField.maxForeignLevel() == 2)
        break label429;
    }
    label388: label402: label411: label420: label429: for (localDatabaseFieldConfig.maxForeignAutoRefreshLevel = paramDatabaseField.maxForeignLevel(); ; localDatabaseFieldConfig.maxForeignAutoRefreshLevel = paramDatabaseField.maxForeignAutoRefreshLevel())
    {
      localDatabaseFieldConfig.persisterClass = paramDatabaseField.persisterClass();
      return localDatabaseFieldConfig;
      localDatabaseFieldConfig.columnName = null;
      break;
      label371: localDatabaseFieldConfig.dataPersister = paramDatabaseField.dataType().getDataPersister();
      break label78;
      localDatabaseFieldConfig.defaultValue = paramDatabaseField.defaultValue();
      break label98;
      localDatabaseFieldConfig.generatedIdSequence = null;
      break label165;
      localDatabaseFieldConfig.unknownEnumvalue = null;
      break label214;
      localDatabaseFieldConfig.format = null;
      break label248;
    }
  }

  public static DatabaseFieldConfig fromField(DatabaseType paramDatabaseType, String paramString, Field paramField)
    throws SQLException
  {
    DatabaseField localDatabaseField = (DatabaseField)paramField.getAnnotation(DatabaseField.class);
    Object localObject;
    if (localDatabaseField != null)
      if (localDatabaseField.persisted())
        localObject = fromDatabaseField(paramDatabaseType, paramString, paramField, localDatabaseField);
    while (true)
    {
      return localObject;
      localObject = null;
      continue;
      ForeignCollectionField localForeignCollectionField = (ForeignCollectionField)paramField.getAnnotation(ForeignCollectionField.class);
      if (localForeignCollectionField != null)
      {
        localObject = fromForeignCollection(paramDatabaseType, paramString, paramField, localForeignCollectionField);
        continue;
      }
      DatabaseFieldConfig localDatabaseFieldConfig = JavaxPersistence.createFieldConfig(paramDatabaseType, paramField);
      if (localDatabaseFieldConfig != null)
      {
        localObject = localDatabaseFieldConfig;
        continue;
      }
      localObject = null;
    }
  }

  private static DatabaseFieldConfig fromForeignCollection(DatabaseType paramDatabaseType, String paramString, Field paramField, ForeignCollectionField paramForeignCollectionField)
  {
    DatabaseFieldConfig localDatabaseFieldConfig = new DatabaseFieldConfig();
    localDatabaseFieldConfig.fieldName = paramField.getName();
    if (paramForeignCollectionField.columnName().length() > 0)
    {
      localDatabaseFieldConfig.columnName = paramForeignCollectionField.columnName();
      localDatabaseFieldConfig.foreignCollection = true;
      localDatabaseFieldConfig.foreignCollectionEager = paramForeignCollectionField.eager();
      localDatabaseFieldConfig.maxEagerForeignCollectionLevel = paramForeignCollectionField.maxEagerForeignCollectionLevel();
      if (paramForeignCollectionField.orderColumnName().length() <= 0)
        break label107;
    }
    label107: for (localDatabaseFieldConfig.foreignCollectionOrderColumn = paramForeignCollectionField.orderColumnName(); ; localDatabaseFieldConfig.foreignCollectionOrderColumn = null)
    {
      return localDatabaseFieldConfig;
      localDatabaseFieldConfig.columnName = paramField.getName();
      break;
    }
  }

  private static String methodFromField(Field paramField, String paramString)
  {
    return paramString + paramField.getName().substring(0, 1).toUpperCase() + paramField.getName().substring(1);
  }

  public String getColumnName()
  {
    return this.columnName;
  }

  public DataPersister getDataPersister()
  {
    return this.dataPersister;
  }

  public String getDefaultValue()
  {
    return this.defaultValue;
  }

  public String getFieldName()
  {
    return this.fieldName;
  }

  public String getForeignCollectionOrderColumn()
  {
    return this.foreignCollectionOrderColumn;
  }

  public DatabaseTableConfig<?> getForeignTableConfig()
  {
    return this.foreignTableConfig;
  }

  public String getFormat()
  {
    return this.format;
  }

  public String getGeneratedIdSequence()
  {
    return this.generatedIdSequence;
  }

  public String getIndexName()
  {
    return this.indexName;
  }

  public int getMaxEagerForeignCollectionLevel()
  {
    return this.maxEagerForeignCollectionLevel;
  }

  public int getMaxForeignAutoRefreshLevel()
  {
    return this.maxForeignAutoRefreshLevel;
  }

  public Class<? extends DataPersister> getPersisterClass()
  {
    return this.persisterClass;
  }

  public String getUniqueIndexName()
  {
    return this.uniqueIndexName;
  }

  public Enum<?> getUnknownEnumvalue()
  {
    return this.unknownEnumvalue;
  }

  public int getWidth()
  {
    return this.width;
  }

  public boolean isCanBeNull()
  {
    return this.canBeNull;
  }

  public boolean isForeign()
  {
    return this.foreign;
  }

  public boolean isForeignAutoRefresh()
  {
    return this.foreignAutoRefresh;
  }

  public boolean isForeignCollection()
  {
    return this.foreignCollection;
  }

  public boolean isForeignCollectionEager()
  {
    return this.foreignCollectionEager;
  }

  public boolean isGeneratedId()
  {
    return this.generatedId;
  }

  public boolean isId()
  {
    return this.id;
  }

  public boolean isThrowIfNull()
  {
    return this.throwIfNull;
  }

  public boolean isUnique()
  {
    return this.unique;
  }

  public boolean isUniqueCombo()
  {
    return this.uniqueCombo;
  }

  public boolean isUseGetSet()
  {
    return this.useGetSet;
  }

  public void setCanBeNull(boolean paramBoolean)
  {
    this.canBeNull = paramBoolean;
  }

  public void setColumnName(String paramString)
  {
    this.columnName = paramString;
  }

  public void setDataPersister(DataPersister paramDataPersister)
  {
    this.dataPersister = paramDataPersister;
  }

  public void setDataType(DataType paramDataType)
  {
    this.dataPersister = paramDataType.getDataPersister();
  }

  public void setDefaultValue(String paramString)
  {
    this.defaultValue = paramString;
  }

  public void setFieldName(String paramString)
  {
    this.fieldName = paramString;
  }

  public void setForeign(boolean paramBoolean)
  {
    this.foreign = paramBoolean;
  }

  public void setForeignAutoRefresh(boolean paramBoolean)
  {
    this.foreignAutoRefresh = paramBoolean;
  }

  public void setForeignCollection(boolean paramBoolean)
  {
    this.foreignCollection = paramBoolean;
  }

  public void setForeignCollectionEager(boolean paramBoolean)
  {
    this.foreignCollectionEager = paramBoolean;
  }

  public void setForeignCollectionOrderColumn(String paramString)
  {
    this.foreignCollectionOrderColumn = paramString;
  }

  public void setForeignTableConfig(DatabaseTableConfig<?> paramDatabaseTableConfig)
  {
    this.foreignTableConfig = paramDatabaseTableConfig;
  }

  public void setFormat(String paramString)
  {
    this.format = paramString;
  }

  public void setGeneratedId(boolean paramBoolean)
  {
    this.generatedId = paramBoolean;
  }

  public void setGeneratedIdSequence(String paramString)
  {
    this.generatedIdSequence = paramString;
  }

  public void setId(boolean paramBoolean)
  {
    this.id = paramBoolean;
  }

  public void setIndexName(String paramString)
  {
    this.indexName = paramString;
  }

  public void setMaxEagerForeignCollectionLevel(int paramInt)
  {
    this.maxEagerForeignCollectionLevel = paramInt;
  }

  public void setMaxForeignAutoRefreshLevel(int paramInt)
  {
    this.maxForeignAutoRefreshLevel = paramInt;
  }

  public void setThrowIfNull(boolean paramBoolean)
  {
    this.throwIfNull = paramBoolean;
  }

  public void setUnique(boolean paramBoolean)
  {
    this.unique = paramBoolean;
  }

  public void setUniqueIndexName(String paramString)
  {
    this.uniqueIndexName = paramString;
  }

  public void setUnknownEnumvalue(Enum<?> paramEnum)
  {
    this.unknownEnumvalue = paramEnum;
  }

  public void setUseGetSet(boolean paramBoolean)
  {
    this.useGetSet = paramBoolean;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.DatabaseFieldConfig
 * JD-Core Version:    0.6.0
 */