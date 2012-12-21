package com.j256.ormlite.table;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TableUtils
{
  private static Logger logger = LoggerFactory.getLogger(TableUtils.class);
  private static final FieldType[] noFieldTypes = new FieldType[0];

  private static <T, ID> void addCreateIndexStatements(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, List<String> paramList, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    FieldType[] arrayOfFieldType = paramTableInfo.getFieldTypes();
    int i = arrayOfFieldType.length;
    int j = 0;
    if (j < i)
    {
      FieldType localFieldType = arrayOfFieldType[j];
      String str2;
      if (paramBoolean)
      {
        str2 = localFieldType.getUniqueIndexName();
        label48: if (str2 != null)
          break label69;
      }
      while (true)
      {
        j++;
        break;
        str2 = localFieldType.getIndexName();
        break label48;
        label69: Object localObject = (List)localHashMap.get(str2);
        if (localObject == null)
        {
          localObject = new ArrayList();
          localHashMap.put(str2, localObject);
        }
        ((List)localObject).add(localFieldType.getDbColumnName());
      }
    }
    StringBuilder localStringBuilder = new StringBuilder(128);
    Iterator localIterator1 = localHashMap.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      Logger localLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localEntry.getKey();
      arrayOfObject[1] = paramTableInfo.getTableName();
      localLogger.info("creating index '{}' for table '{}", arrayOfObject);
      localStringBuilder.append("CREATE ");
      if (paramBoolean)
        localStringBuilder.append("UNIQUE ");
      localStringBuilder.append("INDEX ");
      paramDatabaseType.appendEscapedEntityName(localStringBuilder, (String)localEntry.getKey());
      localStringBuilder.append(" ON ");
      paramDatabaseType.appendEscapedEntityName(localStringBuilder, paramTableInfo.getTableName());
      localStringBuilder.append(" ( ");
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      int k = 1;
      if (localIterator2.hasNext())
      {
        String str1 = (String)localIterator2.next();
        if (k != 0)
          k = 0;
        while (true)
        {
          paramDatabaseType.appendEscapedEntityName(localStringBuilder, str1);
          break;
          localStringBuilder.append(", ");
        }
      }
      localStringBuilder.append(" )");
      paramList.add(localStringBuilder.toString());
      localStringBuilder.setLength(0);
    }
  }

  private static <T, ID> List<String> addCreateTableStatements(ConnectionSource paramConnectionSource, TableInfo<T, ID> paramTableInfo, boolean paramBoolean)
    throws SQLException
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    addCreateTableStatements(paramConnectionSource.getDatabaseType(), paramTableInfo, localArrayList1, localArrayList2, paramBoolean);
    return localArrayList1;
  }

  private static <T, ID> void addCreateTableStatements(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, List<String> paramList1, List<String> paramList2, boolean paramBoolean)
    throws SQLException
  {
    StringBuilder localStringBuilder = new StringBuilder(256);
    localStringBuilder.append("CREATE TABLE ");
    if ((paramBoolean) && (paramDatabaseType.isCreateIfNotExistsSupported()))
      localStringBuilder.append("IF NOT EXISTS ");
    paramDatabaseType.appendEscapedEntityName(localStringBuilder, paramTableInfo.getTableName());
    localStringBuilder.append(" (");
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    int i = 1;
    FieldType[] arrayOfFieldType = paramTableInfo.getFieldTypes();
    int j = arrayOfFieldType.length;
    int k = 0;
    while (k < j)
    {
      FieldType localFieldType = arrayOfFieldType[k];
      if (localFieldType.isForeignCollection())
      {
        k++;
        continue;
      }
      if (i != 0);
      for (int m = 0; ; m = i)
      {
        paramDatabaseType.appendColumnArg(localStringBuilder, localFieldType, localArrayList1, localArrayList2, localArrayList3, paramList2);
        i = m;
        break;
        localStringBuilder.append(", ");
      }
    }
    paramDatabaseType.addPrimaryKeySql(paramTableInfo.getFieldTypes(), localArrayList1, localArrayList2, localArrayList3, paramList2);
    paramDatabaseType.addUniqueComboSql(paramTableInfo.getFieldTypes(), localArrayList1, localArrayList2, localArrayList3, paramList2);
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localStringBuilder.append(", ").append(str);
    }
    localStringBuilder.append(") ");
    paramDatabaseType.appendCreateTableSuffix(localStringBuilder);
    paramList1.addAll(localArrayList2);
    paramList1.add(localStringBuilder.toString());
    paramList1.addAll(localArrayList3);
    addCreateIndexStatements(paramDatabaseType, paramTableInfo, paramList1, false);
    addCreateIndexStatements(paramDatabaseType, paramTableInfo, paramList1, true);
  }

  private static <T, ID> void addDropIndexStatements(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, List<String> paramList)
  {
    HashSet localHashSet = new HashSet();
    for (FieldType localFieldType : paramTableInfo.getFieldTypes())
    {
      String str2 = localFieldType.getIndexName();
      if (str2 != null)
        localHashSet.add(str2);
      String str3 = localFieldType.getUniqueIndexName();
      if (str3 == null)
        continue;
      localHashSet.add(str3);
    }
    StringBuilder localStringBuilder = new StringBuilder(48);
    Iterator localIterator = localHashSet.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      Logger localLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = paramTableInfo.getTableName();
      localLogger.info("dropping index '{}' for table '{}", arrayOfObject);
      localStringBuilder.append("DROP INDEX ");
      paramDatabaseType.appendEscapedEntityName(localStringBuilder, str1);
      paramList.add(localStringBuilder.toString());
      localStringBuilder.setLength(0);
    }
  }

  private static <T, ID> void addDropTableStatements(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, List<String> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    FieldType[] arrayOfFieldType = paramTableInfo.getFieldTypes();
    int i = arrayOfFieldType.length;
    for (int j = 0; j < i; j++)
      paramDatabaseType.dropColumnArg(arrayOfFieldType[j], localArrayList1, localArrayList2);
    StringBuilder localStringBuilder = new StringBuilder(64);
    localStringBuilder.append("DROP TABLE ");
    paramDatabaseType.appendEscapedEntityName(localStringBuilder, paramTableInfo.getTableName());
    localStringBuilder.append(' ');
    paramList.addAll(localArrayList1);
    paramList.add(localStringBuilder.toString());
    paramList.addAll(localArrayList2);
  }

  public static <T> int clearTable(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    return clearTable(paramConnectionSource, paramDatabaseTableConfig.getTableName());
  }

  public static <T> int clearTable(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    String str = DatabaseTableConfig.extractTableName(paramClass);
    if (paramConnectionSource.getDatabaseType().isEntityNamesMustBeUpCase())
      str = str.toUpperCase();
    return clearTable(paramConnectionSource, str);
  }

  private static <T> int clearTable(ConnectionSource paramConnectionSource, String paramString)
    throws SQLException
  {
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadWriteConnection();
    StringBuilder localStringBuilder = new StringBuilder(48);
    if (localDatabaseType.isTruncateSupported())
      localStringBuilder.append("TRUNCATE TABLE ");
    while (true)
    {
      localDatabaseType.appendEscapedEntityName(localStringBuilder, paramString);
      String str = localStringBuilder.toString();
      Logger localLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = str;
      localLogger.info("clearing table '{}' with '{}", arrayOfObject);
      CompiledStatement localCompiledStatement = null;
      try
      {
        localCompiledStatement = localDatabaseConnection.compileStatement(str, StatementBuilder.StatementType.EXECUTE, noFieldTypes);
        int i = localCompiledStatement.runExecute();
        return i;
        localStringBuilder.append("DELETE FROM ");
      }
      finally
      {
        if (localCompiledStatement != null)
          localCompiledStatement.close();
        paramConnectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public static <T> int createTable(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    return createTable(paramConnectionSource, paramDatabaseTableConfig, false);
  }

  private static <T, ID> int createTable(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig, boolean paramBoolean)
    throws SQLException
  {
    Dao localDao = DaoManager.createDao(paramConnectionSource, paramDatabaseTableConfig);
    if (!(localDao instanceof BaseDaoImpl))
      paramDatabaseTableConfig.extractFieldTypes(paramConnectionSource);
    for (int i = doCreateTable(paramConnectionSource, new TableInfo(paramConnectionSource.getDatabaseType(), null, paramDatabaseTableConfig), paramBoolean); ; i = doCreateTable(paramConnectionSource, ((BaseDaoImpl)localDao).getTableInfo(), paramBoolean))
      return i;
  }

  public static <T> int createTable(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    return createTable(paramConnectionSource, paramClass, false);
  }

  private static <T, ID> int createTable(ConnectionSource paramConnectionSource, Class<T> paramClass, boolean paramBoolean)
    throws SQLException
  {
    Dao localDao = DaoManager.createDao(paramConnectionSource, paramClass);
    if (!(localDao instanceof BaseDaoImpl));
    for (int i = doCreateTable(paramConnectionSource, new TableInfo(paramConnectionSource, null, paramClass), paramBoolean); ; i = doCreateTable(paramConnectionSource, ((BaseDaoImpl)localDao).getTableInfo(), paramBoolean))
      return i;
  }

  public static <T> int createTableIfNotExists(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    return createTable(paramConnectionSource, paramDatabaseTableConfig, true);
  }

  public static <T> int createTableIfNotExists(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    return createTable(paramConnectionSource, paramClass, true);
  }

  private static <T, ID> int doCreateTable(ConnectionSource paramConnectionSource, TableInfo<T, ID> paramTableInfo, boolean paramBoolean)
    throws SQLException
  {
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramTableInfo.getTableName();
    localLogger.info("creating table '{}'", arrayOfObject);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    addCreateTableStatements(localDatabaseType, paramTableInfo, localArrayList1, localArrayList2, paramBoolean);
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadWriteConnection();
    try
    {
      int i = doStatements(localDatabaseConnection, "create", localArrayList1, false, localDatabaseType.isCreateTableReturnsZero());
      int j = doCreateTestQueries(localDatabaseConnection, localDatabaseType, localArrayList2);
      int k = i + j;
      return k;
    }
    finally
    {
      paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  private static int doCreateTestQueries(DatabaseConnection paramDatabaseConnection, DatabaseType paramDatabaseType, List<String> paramList)
    throws SQLException
  {
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      CompiledStatement localCompiledStatement = null;
      try
      {
        localCompiledStatement = paramDatabaseConnection.compileStatement(str, StatementBuilder.StatementType.EXECUTE, noFieldTypes);
        DatabaseResults localDatabaseResults = localCompiledStatement.runQuery();
        for (int j = 0; localDatabaseResults.next(); j++);
        Logger localLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(j);
        arrayOfObject[1] = str;
        localLogger.info("executing create table after-query got {} results: {}", arrayOfObject);
        if (localCompiledStatement != null)
          localCompiledStatement.close();
        i++;
        continue;
      }
      catch (SQLException localSQLException)
      {
        throw SqlExceptionUtil.create("executing create table after-query failed: " + str, localSQLException);
      }
      finally
      {
        if (localCompiledStatement != null)
          localCompiledStatement.close();
      }
    }
    return i;
  }

  private static <T, ID> int doDropTable(DatabaseType paramDatabaseType, ConnectionSource paramConnectionSource, TableInfo<T, ID> paramTableInfo, boolean paramBoolean)
    throws SQLException
  {
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramTableInfo.getTableName();
    localLogger.info("dropping table '{}'", arrayOfObject);
    ArrayList localArrayList = new ArrayList();
    addDropIndexStatements(paramDatabaseType, paramTableInfo, localArrayList);
    addDropTableStatements(paramDatabaseType, paramTableInfo, localArrayList);
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadWriteConnection();
    try
    {
      int i = doStatements(localDatabaseConnection, "drop", localArrayList, paramBoolean, false);
      return i;
    }
    finally
    {
      paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  private static int doStatements(DatabaseConnection paramDatabaseConnection, String paramString, Collection<String> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
    throws SQLException
  {
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int j = 0;
      CompiledStatement localCompiledStatement = null;
      try
      {
        localCompiledStatement = paramDatabaseConnection.compileStatement(str, StatementBuilder.StatementType.EXECUTE, noFieldTypes);
        j = localCompiledStatement.runUpdate();
        Logger localLogger2 = logger;
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = paramString;
        arrayOfObject2[1] = Integer.valueOf(j);
        arrayOfObject2[2] = str;
        localLogger2.info("executed {} table statement changed {} rows: {}", arrayOfObject2);
        if (localCompiledStatement != null)
          localCompiledStatement.close();
        if (j < 0)
          throw new SQLException("SQL statement " + str + " updated " + j + " rows, we were expecting >= 0");
      }
      catch (SQLException localSQLException)
      {
        while (paramBoolean1)
        {
          Logger localLogger1 = logger;
          Object[] arrayOfObject1 = new Object[3];
          arrayOfObject1[0] = paramString;
          arrayOfObject1[1] = localSQLException.getMessage();
          arrayOfObject1[2] = str;
          localLogger1.info("ignoring {} error '{}' for statement: {}", arrayOfObject1);
          if (localCompiledStatement == null)
            continue;
          localCompiledStatement.close();
        }
        throw SqlExceptionUtil.create("SQL statement failed: " + str, localSQLException);
      }
      finally
      {
        if (localCompiledStatement != null)
          localCompiledStatement.close();
      }
      if ((j > 0) && (paramBoolean2))
        throw new SQLException("SQL statement updated " + j + " rows, we were expecting == 0: " + str);
      i++;
    }
    return i;
  }

  public static <T, ID> int dropTable(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig, boolean paramBoolean)
    throws SQLException
  {
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    Dao localDao = DaoManager.createDao(paramConnectionSource, paramDatabaseTableConfig);
    if (!(localDao instanceof BaseDaoImpl))
      paramDatabaseTableConfig.extractFieldTypes(paramConnectionSource);
    for (int i = doDropTable(localDatabaseType, paramConnectionSource, new TableInfo(localDatabaseType, null, paramDatabaseTableConfig), paramBoolean); ; i = doDropTable(localDatabaseType, paramConnectionSource, ((BaseDaoImpl)localDao).getTableInfo(), paramBoolean))
      return i;
  }

  public static <T, ID> int dropTable(ConnectionSource paramConnectionSource, Class<T> paramClass, boolean paramBoolean)
    throws SQLException
  {
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    Dao localDao = DaoManager.createDao(paramConnectionSource, paramClass);
    if (!(localDao instanceof BaseDaoImpl));
    for (int i = doDropTable(localDatabaseType, paramConnectionSource, new TableInfo(paramConnectionSource, null, paramClass), paramBoolean); ; i = doDropTable(localDatabaseType, paramConnectionSource, ((BaseDaoImpl)localDao).getTableInfo(), paramBoolean))
      return i;
  }

  public static <T, ID> List<String> getCreateTableStatements(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    Dao localDao = DaoManager.createDao(paramConnectionSource, paramDatabaseTableConfig);
    if (!(localDao instanceof BaseDaoImpl))
      paramDatabaseTableConfig.extractFieldTypes(paramConnectionSource);
    for (List localList = addCreateTableStatements(paramConnectionSource, new TableInfo(paramConnectionSource.getDatabaseType(), null, paramDatabaseTableConfig), false); ; localList = addCreateTableStatements(paramConnectionSource, ((BaseDaoImpl)localDao).getTableInfo(), false))
      return localList;
  }

  public static <T, ID> List<String> getCreateTableStatements(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    Dao localDao = DaoManager.createDao(paramConnectionSource, paramClass);
    if (!(localDao instanceof BaseDaoImpl));
    for (List localList = addCreateTableStatements(paramConnectionSource, new TableInfo(paramConnectionSource, null, paramClass), false); ; localList = addCreateTableStatements(paramConnectionSource, ((BaseDaoImpl)localDao).getTableInfo(), false))
      return localList;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.table.TableUtils
 * JD-Core Version:    0.6.0
 */