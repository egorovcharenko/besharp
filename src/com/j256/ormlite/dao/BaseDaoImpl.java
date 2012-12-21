package com.j256.ormlite.dao;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectIterator;
import com.j256.ormlite.stmt.StatementExecutor;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

public abstract class BaseDaoImpl<T, ID>
  implements Dao<T, ID>
{
  private static final ThreadLocal<DaoConfigLevel> daoConfigLevelLocal = new ThreadLocal();
  protected ConnectionSource connectionSource;
  protected final Class<T> dataClass;
  protected DatabaseType databaseType;
  private boolean initialized = false;
  protected CloseableIterator<T> lastIterator;
  protected StatementExecutor<T, ID> statementExecutor;
  protected DatabaseTableConfig<T> tableConfig;
  protected TableInfo<T, ID> tableInfo;

  protected BaseDaoImpl(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    this(paramConnectionSource, paramDatabaseTableConfig.getDataClass(), paramDatabaseTableConfig);
  }

  protected BaseDaoImpl(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    this(paramConnectionSource, paramClass, null);
  }

  private BaseDaoImpl(ConnectionSource paramConnectionSource, Class<T> paramClass, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    this.dataClass = paramClass;
    this.tableConfig = paramDatabaseTableConfig;
    if (paramConnectionSource != null)
    {
      this.connectionSource = paramConnectionSource;
      initialize();
    }
  }

  protected BaseDaoImpl(Class<T> paramClass)
    throws SQLException
  {
    this(null, paramClass, null);
  }

  @Deprecated
  public static <T, ID> Dao<T, ID> createDao(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    return new BaseDaoImpl(paramConnectionSource, paramDatabaseTableConfig)
    {
    };
  }

  @Deprecated
  public static <T, ID> Dao<T, ID> createDao(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    return new BaseDaoImpl(paramConnectionSource, paramClass)
    {
    };
  }

  private DaoConfigLevel getDaoConfigLevel()
  {
    DaoConfigLevel localDaoConfigLevel = (DaoConfigLevel)daoConfigLevelLocal.get();
    if (localDaoConfigLevel == null)
    {
      localDaoConfigLevel = new DaoConfigLevel(null);
      daoConfigLevelLocal.set(localDaoConfigLevel);
    }
    return localDaoConfigLevel;
  }

  public <CT> CT callBatchTasks(Callable<CT> paramCallable)
    throws Exception
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
    try
    {
      boolean bool = this.connectionSource.saveSpecialConnection(localDatabaseConnection);
      Object localObject2 = this.statementExecutor.callBatchTasks(localDatabaseConnection, bool, paramCallable);
      return localObject2;
    }
    finally
    {
      this.connectionSource.clearSpecialConnection(localDatabaseConnection);
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject1;
  }

  protected void checkForInitialized()
  {
    if (!this.initialized)
      throw new IllegalStateException("you must call initialize() before you can use the dao");
  }

  public void closeLastIterator()
    throws SQLException
  {
    if (this.lastIterator != null)
    {
      this.lastIterator.close();
      this.lastIterator = null;
    }
  }

  public CloseableIterator<T> closeableIterator()
  {
    this.lastIterator = seperateIterator();
    return this.lastIterator;
  }

  public long countOf()
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadOnlyConnection();
    try
    {
      long l = this.statementExecutor.queryForCountStar(localDatabaseConnection);
      return l;
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public int create(T paramT)
    throws SQLException
  {
    checkForInitialized();
    int j;
    if (paramT == null)
      j = 0;
    while (true)
    {
      return j;
      if ((paramT instanceof BaseDaoEnabled))
        ((BaseDaoEnabled)paramT).setDao(this);
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
      try
      {
        int i = this.statementExecutor.create(localDatabaseConnection, paramT);
        j = i;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public T createIfNotExists(T paramT)
    throws SQLException
  {
    Object localObject2;
    if (paramT == null)
      localObject2 = null;
    while (true)
    {
      return localObject2;
      Object localObject1 = queryForSameId(paramT);
      if (localObject1 == null)
      {
        create(paramT);
        localObject2 = paramT;
        continue;
      }
      localObject2 = localObject1;
    }
  }

  public Dao.CreateOrUpdateStatus createOrUpdate(T paramT)
    throws SQLException
  {
    Dao.CreateOrUpdateStatus localCreateOrUpdateStatus;
    if (paramT == null)
      localCreateOrUpdateStatus = new Dao.CreateOrUpdateStatus(false, false, 0);
    while (true)
    {
      return localCreateOrUpdateStatus;
      if (queryForSameId(paramT) == null)
      {
        localCreateOrUpdateStatus = new Dao.CreateOrUpdateStatus(true, false, create(paramT));
        continue;
      }
      localCreateOrUpdateStatus = new Dao.CreateOrUpdateStatus(false, true, update(paramT));
    }
  }

  public int delete(PreparedDelete<T> paramPreparedDelete)
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
    try
    {
      int i = this.statementExecutor.delete(localDatabaseConnection, paramPreparedDelete);
      return i;
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public int delete(T paramT)
    throws SQLException
  {
    checkForInitialized();
    int j;
    if (paramT == null)
      j = 0;
    while (true)
    {
      return j;
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
      try
      {
        int i = this.statementExecutor.delete(localDatabaseConnection, paramT);
        j = i;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public int delete(Collection<T> paramCollection)
    throws SQLException
  {
    checkForInitialized();
    int i;
    if ((paramCollection == null) || (paramCollection.isEmpty()))
      i = 0;
    while (true)
    {
      return i;
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
      try
      {
        int j = this.statementExecutor.deleteObjects(localDatabaseConnection, paramCollection);
        i = j;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public DeleteBuilder<T, ID> deleteBuilder()
  {
    checkForInitialized();
    return new DeleteBuilder(this.databaseType, this.tableInfo);
  }

  public int deleteIds(Collection<ID> paramCollection)
    throws SQLException
  {
    checkForInitialized();
    int i;
    if ((paramCollection == null) || (paramCollection.isEmpty()))
      i = 0;
    while (true)
    {
      return i;
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
      try
      {
        int j = this.statementExecutor.deleteIds(localDatabaseConnection, paramCollection);
        i = j;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public int executeRaw(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
    try
    {
      int i = this.statementExecutor.executeRaw(localDatabaseConnection, paramString, paramArrayOfString);
      return i;
    }
    catch (SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("Could not run raw execute statement " + paramString, localSQLException);
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public ID extractId(T paramT)
    throws SQLException
  {
    checkForInitialized();
    return this.tableInfo.getIdField().extractJavaFieldValue(paramT);
  }

  public FieldType findForeignFieldType(Class<?> paramClass)
  {
    checkForInitialized();
    FieldType[] arrayOfFieldType = this.tableInfo.getFieldTypes();
    int i = arrayOfFieldType.length;
    int j = 0;
    FieldType localFieldType2;
    if (j < i)
    {
      localFieldType2 = arrayOfFieldType[j];
      if (localFieldType2.getFieldType() != paramClass);
    }
    for (FieldType localFieldType1 = localFieldType2; ; localFieldType1 = null)
    {
      return localFieldType1;
      j++;
      break;
    }
  }

  public Class<T> getDataClass()
  {
    return this.dataClass;
  }

  public <FT> ForeignCollection<FT> getEmptyForeignCollection(String paramString)
    throws SQLException
  {
    for (FieldType localFieldType : this.tableInfo.getFieldTypes())
      if (localFieldType.getDbColumnName().equals(paramString))
        return localFieldType.buildForeignCollection(null, true);
    throw new IllegalArgumentException("Could not find a field named " + paramString);
  }

  public DatabaseTableConfig<T> getTableConfig()
  {
    return this.tableConfig;
  }

  public TableInfo<T, ID> getTableInfo()
  {
    return this.tableInfo;
  }

  public CloseableWrappedIterable<T> getWrappedIterable()
  {
    checkForInitialized();
    return new CloseableWrappedIterableImpl(new CloseableIterable()
    {
      public CloseableIterator<T> closeableIterator()
      {
        try
        {
          CloseableIterator localCloseableIterator = BaseDaoImpl.this.seperateIterator();
          return localCloseableIterator;
        }
        catch (Exception localException)
        {
        }
        throw new IllegalStateException("Could not build iterator for " + BaseDaoImpl.this.dataClass, localException);
      }

      public Iterator<T> iterator()
      {
        return closeableIterator();
      }
    });
  }

  public CloseableWrappedIterable<T> getWrappedIterable(PreparedQuery<T> paramPreparedQuery)
  {
    checkForInitialized();
    return new CloseableWrappedIterableImpl(new CloseableIterable(paramPreparedQuery)
    {
      public CloseableIterator<T> closeableIterator()
      {
        try
        {
          CloseableIterator localCloseableIterator = BaseDaoImpl.this.seperateIterator(this.val$preparedQuery);
          return localCloseableIterator;
        }
        catch (Exception localException)
        {
        }
        throw new IllegalStateException("Could not build prepared-query iterator for " + BaseDaoImpl.this.dataClass, localException);
      }

      public Iterator<T> iterator()
      {
        return closeableIterator();
      }
    });
  }

  public void initialize()
    throws SQLException
  {
    if (this.initialized)
      return;
    if (this.connectionSource == null)
      throw new IllegalStateException("connectionSource was never set on " + getClass().getSimpleName());
    this.databaseType = this.connectionSource.getDatabaseType();
    if (this.databaseType == null)
      throw new IllegalStateException("connectionSource is getting a null DatabaseType in " + getClass().getSimpleName());
    label130: DaoConfigLevel localDaoConfigLevel;
    if (this.tableConfig == null)
    {
      this.tableInfo = new TableInfo(this.connectionSource, this, this.dataClass);
      this.statementExecutor = new StatementExecutor(this.databaseType, this.tableInfo, this);
      localDaoConfigLevel = getDaoConfigLevel();
      localDaoConfigLevel.level = (1 + localDaoConfigLevel.level);
    }
    while (true)
    {
      int k;
      try
      {
        if (localDaoConfigLevel.level <= 1)
          continue;
        localDaoConfigLevel.addDao(this);
        localDaoConfigLevel.level -= 1;
        this.initialized = true;
        break;
        this.tableConfig.extractFieldTypes(this.connectionSource);
        this.tableInfo = new TableInfo(this.databaseType, this, this.tableConfig);
        break label130;
        FieldType[] arrayOfFieldType1 = this.tableInfo.getFieldTypes();
        int i = arrayOfFieldType1.length;
        int j = 0;
        if (j >= i)
          continue;
        arrayOfFieldType1[j].configDaoInformation(this.connectionSource, this.dataClass);
        j++;
        continue;
        List localList = localDaoConfigLevel.daoList;
        if (localList == null)
          continue;
        k = 0;
        if (k >= localList.size())
          continue;
        BaseDaoImpl localBaseDaoImpl = (BaseDaoImpl)localList.get(k);
        FieldType[] arrayOfFieldType2 = localBaseDaoImpl.getTableInfo().getFieldTypes();
        int m = arrayOfFieldType2.length;
        int n = 0;
        if (n < m)
        {
          arrayOfFieldType2[n].configDaoInformation(this.connectionSource, localBaseDaoImpl.dataClass);
          n++;
          continue;
          localList.clear();
          continue;
        }
      }
      finally
      {
        localDaoConfigLevel.level -= 1;
      }
      k++;
    }
  }

  public boolean isTableExists()
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadOnlyConnection();
    try
    {
      boolean bool = localDatabaseConnection.isTableExists(this.tableInfo.getTableName());
      return bool;
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public boolean isUpdatable()
  {
    return this.tableInfo.isUpdatable();
  }

  public CloseableIterator<T> iterator()
  {
    return closeableIterator();
  }

  public CloseableIterator<T> iterator(PreparedQuery<T> paramPreparedQuery)
    throws SQLException
  {
    this.lastIterator = seperateIterator(paramPreparedQuery);
    return this.lastIterator;
  }

  public RawResults iteratorRaw(String paramString)
    throws SQLException
  {
    checkForInitialized();
    try
    {
      RawResults localRawResults = this.statementExecutor.buildOldIterator(this.connectionSource, paramString);
      return localRawResults;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Could not build iterator for " + paramString, localSQLException);
  }

  public String objectToString(T paramT)
  {
    checkForInitialized();
    return this.tableInfo.objectToString(paramT);
  }

  public boolean objectsEqual(T paramT1, T paramT2)
    throws SQLException
  {
    checkForInitialized();
    FieldType[] arrayOfFieldType = this.tableInfo.getFieldTypes();
    int i = arrayOfFieldType.length;
    int j = 0;
    Object localObject1;
    Object localObject2;
    int k;
    if (j < i)
    {
      FieldType localFieldType = arrayOfFieldType[j];
      localObject1 = localFieldType.extractJavaFieldValue(paramT1);
      localObject2 = localFieldType.extractJavaFieldValue(paramT2);
      if (localObject1 == null)
      {
        if (localObject2 == null)
          break label91;
        k = 0;
      }
    }
    while (true)
    {
      return k;
      if (localObject2 == null)
      {
        k = 0;
        continue;
      }
      if (!localObject1.equals(localObject2))
      {
        k = 0;
        continue;
      }
      label91: j++;
      break;
      k = 1;
    }
  }

  public List<T> query(PreparedQuery<T> paramPreparedQuery)
    throws SQLException
  {
    checkForInitialized();
    return this.statementExecutor.query(this.connectionSource, paramPreparedQuery);
  }

  public QueryBuilder<T, ID> queryBuilder()
  {
    checkForInitialized();
    return new QueryBuilder(this.databaseType, this.tableInfo, this);
  }

  public List<T> queryForAll()
    throws SQLException
  {
    checkForInitialized();
    return this.statementExecutor.queryForAll(this.connectionSource);
  }

  public RawResults queryForAllRaw(String paramString)
    throws SQLException
  {
    checkForInitialized();
    return this.statementExecutor.queryForAllRawOld(this.connectionSource, paramString);
  }

  public List<T> queryForEq(String paramString, Object paramObject)
    throws SQLException
  {
    return queryBuilder().where().eq(paramString, paramObject).query();
  }

  public List<T> queryForFieldValues(Map<String, Object> paramMap)
    throws SQLException
  {
    checkForInitialized();
    QueryBuilder localQueryBuilder = queryBuilder();
    Where localWhere = localQueryBuilder.where();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localWhere.eq((String)localEntry.getKey(), localEntry.getValue());
    }
    if (paramMap.size() == 0);
    for (List localList = Collections.emptyList(); ; localList = localQueryBuilder.query())
    {
      return localList;
      localWhere.and(paramMap.size());
    }
  }

  public T queryForFirst(PreparedQuery<T> paramPreparedQuery)
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadOnlyConnection();
    try
    {
      Object localObject2 = this.statementExecutor.queryForFirst(localDatabaseConnection, paramPreparedQuery);
      return localObject2;
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject1;
  }

  public T queryForId(ID paramID)
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadOnlyConnection();
    try
    {
      Object localObject2 = this.statementExecutor.queryForId(localDatabaseConnection, paramID);
      return localObject2;
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject1;
  }

  public List<T> queryForMatching(T paramT)
    throws SQLException
  {
    checkForInitialized();
    QueryBuilder localQueryBuilder = queryBuilder();
    Where localWhere = localQueryBuilder.where();
    int i = 0;
    for (FieldType localFieldType : this.tableInfo.getFieldTypes())
    {
      Object localObject = localFieldType.getFieldValueIfNotDefault(paramT);
      if (localObject == null)
        continue;
      localWhere.eq(localFieldType.getDbColumnName(), localObject);
      i++;
    }
    if (i == 0);
    for (List localList = Collections.emptyList(); ; localList = localQueryBuilder.query())
    {
      return localList;
      localWhere.and(i);
    }
  }

  public T queryForSameId(T paramT)
    throws SQLException
  {
    checkForInitialized();
    if (paramT == null);
    for (Object localObject = null; ; localObject = queryForId(extractId(paramT)))
      return localObject;
  }

  public <GR> GenericRawResults<GR> queryRaw(String paramString, RawRowMapper<GR> paramRawRowMapper, String[] paramArrayOfString)
    throws SQLException
  {
    checkForInitialized();
    try
    {
      GenericRawResults localGenericRawResults = this.statementExecutor.queryRaw(this.connectionSource, paramString, paramRawRowMapper, paramArrayOfString);
      return localGenericRawResults;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Could not build iterator for " + paramString, localSQLException);
  }

  public GenericRawResults<Object[]> queryRaw(String paramString, DataType[] paramArrayOfDataType, String[] paramArrayOfString)
    throws SQLException
  {
    checkForInitialized();
    try
    {
      GenericRawResults localGenericRawResults = this.statementExecutor.queryRaw(this.connectionSource, paramString, paramArrayOfDataType, paramArrayOfString);
      return localGenericRawResults;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Could not build iterator for " + paramString, localSQLException);
  }

  public GenericRawResults<String[]> queryRaw(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    checkForInitialized();
    try
    {
      GenericRawResults localGenericRawResults = this.statementExecutor.queryRaw(this.connectionSource, paramString, paramArrayOfString);
      return localGenericRawResults;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Could not build iterator for " + paramString, localSQLException);
  }

  public int refresh(T paramT)
    throws SQLException
  {
    checkForInitialized();
    int j;
    if (paramT == null)
      j = 0;
    while (true)
    {
      return j;
      if ((paramT instanceof BaseDaoEnabled))
        ((BaseDaoEnabled)paramT).setDao(this);
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadOnlyConnection();
      try
      {
        int i = this.statementExecutor.refresh(localDatabaseConnection, paramT);
        j = i;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public CloseableIterator<T> seperateIterator()
  {
    checkForInitialized();
    try
    {
      SelectIterator localSelectIterator = this.statementExecutor.buildIterator(this, this.connectionSource);
      return localSelectIterator;
    }
    catch (Exception localException)
    {
    }
    throw new IllegalStateException("Could not build iterator for " + this.dataClass, localException);
  }

  public CloseableIterator<T> seperateIterator(PreparedQuery<T> paramPreparedQuery)
    throws SQLException
  {
    checkForInitialized();
    try
    {
      SelectIterator localSelectIterator = this.statementExecutor.buildIterator(this, this.connectionSource, paramPreparedQuery);
      this.lastIterator = localSelectIterator;
      return localSelectIterator;
    }
    catch (SQLException localSQLException)
    {
    }
    throw SqlExceptionUtil.create("Could not build prepared-query iterator for " + this.dataClass, localSQLException);
  }

  public void setConnectionSource(ConnectionSource paramConnectionSource)
  {
    this.connectionSource = paramConnectionSource;
  }

  public void setTableConfig(DatabaseTableConfig<T> paramDatabaseTableConfig)
  {
    this.tableConfig = paramDatabaseTableConfig;
  }

  public int update(PreparedUpdate<T> paramPreparedUpdate)
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
    try
    {
      int i = this.statementExecutor.update(localDatabaseConnection, paramPreparedUpdate);
      return i;
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public int update(T paramT)
    throws SQLException
  {
    checkForInitialized();
    int j;
    if (paramT == null)
      j = 0;
    while (true)
    {
      return j;
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
      try
      {
        int i = this.statementExecutor.update(localDatabaseConnection, paramT);
        j = i;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public UpdateBuilder<T, ID> updateBuilder()
  {
    checkForInitialized();
    return new UpdateBuilder(this.databaseType, this.tableInfo);
  }

  public int updateId(T paramT, ID paramID)
    throws SQLException
  {
    checkForInitialized();
    int j;
    if (paramT == null)
      j = 0;
    while (true)
    {
      return j;
      DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
      try
      {
        int i = this.statementExecutor.updateId(localDatabaseConnection, paramT, paramID);
        j = i;
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
      finally
      {
        this.connectionSource.releaseConnection(localDatabaseConnection);
      }
    }
  }

  public int updateRaw(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    checkForInitialized();
    DatabaseConnection localDatabaseConnection = this.connectionSource.getReadWriteConnection();
    try
    {
      int i = this.statementExecutor.updateRaw(localDatabaseConnection, paramString, paramArrayOfString);
      return i;
    }
    catch (SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("Could not run raw update statement " + paramString, localSQLException);
    }
    finally
    {
      this.connectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  private static class DaoConfigLevel
  {
    List<BaseDaoImpl<?, ?>> daoList;
    int level;

    public void addDao(BaseDaoImpl<?, ?> paramBaseDaoImpl)
    {
      if (this.daoList == null)
        this.daoList = new ArrayList();
      this.daoList.add(paramBaseDaoImpl);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.BaseDaoImpl
 * JD-Core Version:    0.6.0
 */