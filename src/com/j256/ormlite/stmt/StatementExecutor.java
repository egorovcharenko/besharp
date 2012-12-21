package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.mapped.MappedCreate;
import com.j256.ormlite.stmt.mapped.MappedDelete;
import com.j256.ormlite.stmt.mapped.MappedDeleteCollection;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.stmt.mapped.MappedRefresh;
import com.j256.ormlite.stmt.mapped.MappedUpdate;
import com.j256.ormlite.stmt.mapped.MappedUpdateId;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class StatementExecutor<T, ID>
  implements GenericRowMapper<String[]>
{
  private static Logger logger = LoggerFactory.getLogger(StatementExecutor.class);
  private static final FieldType[] noFieldTypes = new FieldType[0];
  private final Dao<T, ID> dao;
  private final DatabaseType databaseType;
  private MappedDelete<T, ID> mappedDelete;
  private MappedCreate<T, ID> mappedInsert;
  private MappedQueryForId<T, ID> mappedQueryForId;
  private MappedRefresh<T, ID> mappedRefresh;
  private MappedUpdate<T, ID> mappedUpdate;
  private MappedUpdateId<T, ID> mappedUpdateId;
  private PreparedQuery<T> preparedQueryForAll;
  private final TableInfo<T, ID> tableInfo;

  public StatementExecutor(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, Dao<T, ID> paramDao)
    throws SQLException
  {
    this.databaseType = paramDatabaseType;
    this.tableInfo = paramTableInfo;
    this.dao = paramDao;
  }

  private void assignStatementArguments(CompiledStatement paramCompiledStatement, String[] paramArrayOfString)
    throws SQLException
  {
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (paramArrayOfString[i] == null)
        paramCompiledStatement.setNull(i, SqlType.STRING);
      while (true)
      {
        i++;
        break;
        paramCompiledStatement.setObject(i, paramArrayOfString[i], SqlType.STRING);
      }
    }
  }

  private String[] extractColumnNames(CompiledStatement paramCompiledStatement)
    throws SQLException
  {
    int i = paramCompiledStatement.getColumnCount();
    String[] arrayOfString = new String[i];
    for (int j = 0; j < i; j++)
      arrayOfString[j] = paramCompiledStatement.getColumnName(j);
    return arrayOfString;
  }

  private void prepareQueryForAll()
    throws SQLException
  {
    if (this.preparedQueryForAll == null)
      this.preparedQueryForAll = new QueryBuilder(this.databaseType, this.tableInfo, this.dao).prepare();
  }

  public SelectIterator<T, ID> buildIterator(BaseDaoImpl<T, ID> paramBaseDaoImpl, ConnectionSource paramConnectionSource)
    throws SQLException
  {
    prepareQueryForAll();
    return buildIterator(paramBaseDaoImpl, paramConnectionSource, this.preparedQueryForAll);
  }

  public SelectIterator<T, ID> buildIterator(BaseDaoImpl<T, ID> paramBaseDaoImpl, ConnectionSource paramConnectionSource, PreparedStmt<T> paramPreparedStmt)
    throws SQLException
  {
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadOnlyConnection();
    CompiledStatement localCompiledStatement = null;
    try
    {
      localCompiledStatement = paramPreparedStmt.compile(localDatabaseConnection);
      SelectIterator localSelectIterator = new SelectIterator(this.tableInfo.getDataClass(), paramBaseDaoImpl, paramPreparedStmt, paramConnectionSource, localDatabaseConnection, localCompiledStatement, paramPreparedStmt.getStatement());
      if (0 != 0)
        null.close();
      if (0 != 0)
        paramConnectionSource.releaseConnection(null);
      return localSelectIterator;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
      if (localDatabaseConnection != null)
        paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public RawResults buildOldIterator(ConnectionSource paramConnectionSource, String paramString)
    throws SQLException
  {
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    localLogger.debug("executing raw results iterator for: {}", arrayOfObject);
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadOnlyConnection();
    CompiledStatement localCompiledStatement = null;
    try
    {
      localCompiledStatement = localDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.SELECT, noFieldTypes);
      RawResultsWrapper localRawResultsWrapper = new RawResultsWrapper(paramConnectionSource, localDatabaseConnection, paramString, localCompiledStatement, extractColumnNames(localCompiledStatement), this);
      if (0 != 0)
        null.close();
      if (0 != 0)
        paramConnectionSource.releaseConnection(null);
      return localRawResultsWrapper;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
      if (localDatabaseConnection != null)
        paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public <CT> CT callBatchTasks(DatabaseConnection paramDatabaseConnection, boolean paramBoolean, Callable<CT> paramCallable)
    throws Exception
  {
    Object localObject3;
    if (this.databaseType.isBatchUseTransaction())
      localObject3 = TransactionManager.callInTransaction(paramDatabaseConnection, paramBoolean, this.databaseType, paramCallable);
    while (true)
    {
      return localObject3;
      boolean bool = false;
      try
      {
        if (paramDatabaseConnection.isAutoCommitSupported())
        {
          bool = paramDatabaseConnection.getAutoCommit();
          if (bool)
          {
            paramDatabaseConnection.setAutoCommit(false);
            Logger localLogger3 = logger;
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = this.tableInfo.getTableName();
            localLogger3.debug("disabled auto-commit on table {} before batch tasks", arrayOfObject3);
          }
        }
        Object localObject2 = paramCallable.call();
        localObject3 = localObject2;
        if (!bool)
          continue;
        paramDatabaseConnection.setAutoCommit(true);
        Logger localLogger2 = logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.tableInfo.getTableName();
        localLogger2.debug("re-enabled auto-commit on table {} after batch tasks", arrayOfObject2);
      }
      finally
      {
        if (bool)
        {
          paramDatabaseConnection.setAutoCommit(true);
          Logger localLogger1 = logger;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = this.tableInfo.getTableName();
          localLogger1.debug("re-enabled auto-commit on table {} after batch tasks", arrayOfObject1);
        }
      }
    }
  }

  public int create(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    if (this.mappedInsert == null)
      this.mappedInsert = MappedCreate.build(this.databaseType, this.tableInfo);
    return this.mappedInsert.insert(this.databaseType, paramDatabaseConnection, paramT);
  }

  public int delete(DatabaseConnection paramDatabaseConnection, PreparedDelete<T> paramPreparedDelete)
    throws SQLException
  {
    CompiledStatement localCompiledStatement = paramPreparedDelete.compile(paramDatabaseConnection);
    try
    {
      int i = localCompiledStatement.runUpdate();
      return i;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
    }
    throw localObject;
  }

  public int delete(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    if (this.mappedDelete == null)
      this.mappedDelete = MappedDelete.build(this.databaseType, this.tableInfo);
    return this.mappedDelete.delete(paramDatabaseConnection, paramT);
  }

  public int deleteIds(DatabaseConnection paramDatabaseConnection, Collection<ID> paramCollection)
    throws SQLException
  {
    return MappedDeleteCollection.deleteIds(this.databaseType, this.tableInfo, paramDatabaseConnection, paramCollection);
  }

  public int deleteObjects(DatabaseConnection paramDatabaseConnection, Collection<T> paramCollection)
    throws SQLException
  {
    return MappedDeleteCollection.deleteObjects(this.databaseType, this.tableInfo, paramDatabaseConnection, paramCollection);
  }

  public int executeRaw(DatabaseConnection paramDatabaseConnection, String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    Logger localLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramString;
    localLogger1.debug("running raw execute statement: {}", arrayOfObject1);
    if (paramArrayOfString.length > 0)
    {
      Logger localLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramArrayOfString;
      localLogger2.trace("execute arguments: {}", arrayOfObject2);
    }
    CompiledStatement localCompiledStatement = paramDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.EXECUTE, noFieldTypes);
    try
    {
      assignStatementArguments(localCompiledStatement, paramArrayOfString);
      int i = localCompiledStatement.runExecute();
      return i;
    }
    finally
    {
      localCompiledStatement.close();
    }
    throw localObject;
  }

  public String[] mapRow(DatabaseResults paramDatabaseResults)
    throws SQLException
  {
    int i = paramDatabaseResults.getColumnCount();
    String[] arrayOfString = new String[i];
    for (int j = 0; j < i; j++)
      arrayOfString[j] = paramDatabaseResults.getString(j);
    return arrayOfString;
  }

  public List<T> query(ConnectionSource paramConnectionSource, PreparedStmt<T> paramPreparedStmt)
    throws SQLException
  {
    SelectIterator localSelectIterator = null;
    ArrayList localArrayList;
    try
    {
      localSelectIterator = buildIterator(null, paramConnectionSource, paramPreparedStmt);
      localArrayList = new ArrayList();
      while (localSelectIterator.hasNextThrow())
        localArrayList.add(localSelectIterator.nextThrow());
    }
    finally
    {
      if (localSelectIterator != null)
        localSelectIterator.close();
    }
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramPreparedStmt.getStatement();
    arrayOfObject[1] = Integer.valueOf(localArrayList.size());
    localLogger.debug("query of '{}' returned {} results", arrayOfObject);
    if (localSelectIterator != null)
      localSelectIterator.close();
    return localArrayList;
  }

  public List<T> queryForAll(ConnectionSource paramConnectionSource)
    throws SQLException
  {
    prepareQueryForAll();
    return query(paramConnectionSource, this.preparedQueryForAll);
  }

  public RawResults queryForAllRawOld(ConnectionSource paramConnectionSource, String paramString)
    throws SQLException
  {
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadOnlyConnection();
    CompiledStatement localCompiledStatement = null;
    try
    {
      localCompiledStatement = localDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.SELECT, noFieldTypes);
      RawResultsWrapper localRawResultsWrapper = new RawResultsWrapper(paramConnectionSource, localDatabaseConnection, paramString, localCompiledStatement, extractColumnNames(localCompiledStatement), this);
      if (0 != 0)
        null.close();
      if (0 != 0)
        paramConnectionSource.releaseConnection(null);
      return localRawResultsWrapper;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
      if (localDatabaseConnection != null)
        paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public long queryForCountStar(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    localStringBuilder.append("SELECT COUNT(*) FROM ");
    this.databaseType.appendEscapedEntityName(localStringBuilder, this.tableInfo.getTableName());
    String str = localStringBuilder.toString();
    long l = paramDatabaseConnection.queryForLong(str);
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = str;
    arrayOfObject[1] = Long.valueOf(l);
    localLogger.debug("query of '{}' returned {}", arrayOfObject);
    return l;
  }

  public T queryForFirst(DatabaseConnection paramDatabaseConnection, PreparedStmt<T> paramPreparedStmt)
    throws SQLException
  {
    CompiledStatement localCompiledStatement = paramPreparedStmt.compile(paramDatabaseConnection);
    try
    {
      DatabaseResults localDatabaseResults = localCompiledStatement.runQuery();
      Object localObject2;
      if (localDatabaseResults.next())
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramPreparedStmt.getStatement();
        localLogger2.debug("query-for-first of '{}' returned at least 1 result", arrayOfObject2);
        Object localObject3 = paramPreparedStmt.mapRow(localDatabaseResults);
        localObject2 = localObject3;
      }
      while (true)
      {
        return localObject2;
        Logger localLogger1 = logger;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramPreparedStmt.getStatement();
        localLogger1.debug("query-for-first of '{}' returned at 0 results", arrayOfObject1);
        localObject2 = null;
        if (localCompiledStatement == null)
          continue;
        localCompiledStatement.close();
      }
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
    }
    throw localObject1;
  }

  public T queryForId(DatabaseConnection paramDatabaseConnection, ID paramID)
    throws SQLException
  {
    if (this.mappedQueryForId == null)
      this.mappedQueryForId = MappedQueryForId.build(this.databaseType, this.tableInfo);
    return this.mappedQueryForId.execute(paramDatabaseConnection, paramID);
  }

  public <UO> GenericRawResults<UO> queryRaw(ConnectionSource paramConnectionSource, String paramString, RawRowMapper<UO> paramRawRowMapper, String[] paramArrayOfString)
    throws SQLException
  {
    Logger localLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramString;
    localLogger1.debug("executing raw query for: {}", arrayOfObject1);
    if (paramArrayOfString.length > 0)
    {
      Logger localLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramArrayOfString;
      localLogger2.trace("query arguments: {}", arrayOfObject2);
    }
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadOnlyConnection();
    CompiledStatement localCompiledStatement = null;
    try
    {
      localCompiledStatement = localDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.SELECT, noFieldTypes);
      assignStatementArguments(localCompiledStatement, paramArrayOfString);
      String[] arrayOfString = extractColumnNames(localCompiledStatement);
      RawResultsImpl localRawResultsImpl = new RawResultsImpl(paramConnectionSource, localDatabaseConnection, paramString, [Ljava.lang.String.class, localCompiledStatement, arrayOfString, new UserObjectRowMapper(paramRawRowMapper, arrayOfString, this));
      if (0 != 0)
        null.close();
      if (0 != 0)
        paramConnectionSource.releaseConnection(null);
      return localRawResultsImpl;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
      if (localDatabaseConnection != null)
        paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public GenericRawResults<Object[]> queryRaw(ConnectionSource paramConnectionSource, String paramString, DataType[] paramArrayOfDataType, String[] paramArrayOfString)
    throws SQLException
  {
    Logger localLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramString;
    localLogger1.debug("executing raw query for: {}", arrayOfObject1);
    if (paramArrayOfString.length > 0)
    {
      Logger localLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramArrayOfString;
      localLogger2.trace("query arguments: {}", arrayOfObject2);
    }
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadOnlyConnection();
    CompiledStatement localCompiledStatement = null;
    try
    {
      localCompiledStatement = localDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.SELECT, noFieldTypes);
      assignStatementArguments(localCompiledStatement, paramArrayOfString);
      RawResultsImpl localRawResultsImpl = new RawResultsImpl(paramConnectionSource, localDatabaseConnection, paramString, [Ljava.lang.Object.class, localCompiledStatement, extractColumnNames(localCompiledStatement), new ObjectArrayRowMapper(paramArrayOfDataType));
      if (0 != 0)
        null.close();
      if (0 != 0)
        paramConnectionSource.releaseConnection(null);
      return localRawResultsImpl;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
      if (localDatabaseConnection != null)
        paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public GenericRawResults<String[]> queryRaw(ConnectionSource paramConnectionSource, String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    Logger localLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramString;
    localLogger1.debug("executing raw query for: {}", arrayOfObject1);
    if (paramArrayOfString.length > 0)
    {
      Logger localLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramArrayOfString;
      localLogger2.trace("query arguments: {}", arrayOfObject2);
    }
    DatabaseConnection localDatabaseConnection = paramConnectionSource.getReadOnlyConnection();
    CompiledStatement localCompiledStatement = null;
    try
    {
      localCompiledStatement = localDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.SELECT, noFieldTypes);
      assignStatementArguments(localCompiledStatement, paramArrayOfString);
      RawResultsImpl localRawResultsImpl = new RawResultsImpl(paramConnectionSource, localDatabaseConnection, paramString, [Ljava.lang.String.class, localCompiledStatement, extractColumnNames(localCompiledStatement), this);
      if (0 != 0)
        null.close();
      if (0 != 0)
        paramConnectionSource.releaseConnection(null);
      return localRawResultsImpl;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
      if (localDatabaseConnection != null)
        paramConnectionSource.releaseConnection(localDatabaseConnection);
    }
    throw localObject;
  }

  public int refresh(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    if (this.mappedRefresh == null)
      this.mappedRefresh = MappedRefresh.build(this.databaseType, this.tableInfo);
    return this.mappedRefresh.executeRefresh(paramDatabaseConnection, paramT);
  }

  public int update(DatabaseConnection paramDatabaseConnection, PreparedUpdate<T> paramPreparedUpdate)
    throws SQLException
  {
    CompiledStatement localCompiledStatement = paramPreparedUpdate.compile(paramDatabaseConnection);
    try
    {
      int i = localCompiledStatement.runUpdate();
      return i;
    }
    finally
    {
      if (localCompiledStatement != null)
        localCompiledStatement.close();
    }
    throw localObject;
  }

  public int update(DatabaseConnection paramDatabaseConnection, T paramT)
    throws SQLException
  {
    if (this.mappedUpdate == null)
      this.mappedUpdate = MappedUpdate.build(this.databaseType, this.tableInfo);
    return this.mappedUpdate.update(paramDatabaseConnection, paramT);
  }

  public int updateId(DatabaseConnection paramDatabaseConnection, T paramT, ID paramID)
    throws SQLException
  {
    if (this.mappedUpdateId == null)
      this.mappedUpdateId = MappedUpdateId.build(this.databaseType, this.tableInfo);
    return this.mappedUpdateId.execute(paramDatabaseConnection, paramT, paramID);
  }

  public int updateRaw(DatabaseConnection paramDatabaseConnection, String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    Logger localLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramString;
    localLogger1.debug("running raw update statement: {}", arrayOfObject1);
    if (paramArrayOfString.length > 0)
    {
      Logger localLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramArrayOfString;
      localLogger2.trace("update arguments: {}", arrayOfObject2);
    }
    CompiledStatement localCompiledStatement = paramDatabaseConnection.compileStatement(paramString, StatementBuilder.StatementType.UPDATE, noFieldTypes);
    try
    {
      assignStatementArguments(localCompiledStatement, paramArrayOfString);
      int i = localCompiledStatement.runUpdate();
      return i;
    }
    finally
    {
      localCompiledStatement.close();
    }
    throw localObject;
  }

  private static class ObjectArrayRowMapper
    implements GenericRowMapper<Object[]>
  {
    private final DataType[] columnTypes;

    public ObjectArrayRowMapper(DataType[] paramArrayOfDataType)
    {
      this.columnTypes = paramArrayOfDataType;
    }

    public Object[] mapRow(DatabaseResults paramDatabaseResults)
      throws SQLException
    {
      int i = paramDatabaseResults.getColumnCount();
      Object[] arrayOfObject = new Object[i];
      int j = 0;
      if (j < i)
      {
        if (j >= this.columnTypes.length);
        for (DataType localDataType = DataType.STRING; ; localDataType = this.columnTypes[j])
        {
          arrayOfObject[j] = localDataType.getDataPersister().resultToJava(null, paramDatabaseResults, j);
          j++;
          break;
        }
      }
      return arrayOfObject;
    }
  }

  private static class RawResultsWrapper
    implements RawResults
  {
    private final String[] columnNames;
    private final CompiledStatement compiledStatement;
    private final DatabaseConnection connection;
    private final ConnectionSource connectionSource;
    private final String query;
    private final GenericRawResults<String[]> rawResults;
    private final GenericRowMapper<String[]> stringMapper;

    public RawResultsWrapper(ConnectionSource paramConnectionSource, DatabaseConnection paramDatabaseConnection, String paramString, CompiledStatement paramCompiledStatement, String[] paramArrayOfString, GenericRowMapper<String[]> paramGenericRowMapper)
      throws SQLException
    {
      this.connectionSource = paramConnectionSource;
      this.connection = paramDatabaseConnection;
      this.query = paramString;
      this.compiledStatement = paramCompiledStatement;
      this.columnNames = paramArrayOfString;
      this.stringMapper = paramGenericRowMapper;
      this.rawResults = new RawResultsImpl(paramConnectionSource, paramDatabaseConnection, paramString, [Ljava.lang.String.class, paramCompiledStatement, paramArrayOfString, paramGenericRowMapper);
    }

    public void close()
      throws SQLException
    {
      this.rawResults.close();
    }

    public CloseableIterator<String[]> closeableIterator()
    {
      return this.rawResults.closeableIterator();
    }

    public String[] getColumnNames()
    {
      return this.rawResults.getColumnNames();
    }

    public <UO> List<UO> getMappedResults(RawRowMapper<UO> paramRawRowMapper)
      throws SQLException
    {
      ArrayList localArrayList = new ArrayList();
      String[] arrayOfString = this.rawResults.getColumnNames();
      Iterator localIterator = iterator();
      while (localIterator.hasNext())
      {
        Object localObject = paramRawRowMapper.mapRow(arrayOfString, (String[])localIterator.next());
        if (localObject == null)
          continue;
        localArrayList.add(localObject);
      }
      return localArrayList;
    }

    public int getNumberColumns()
    {
      return this.rawResults.getNumberColumns();
    }

    public List<String[]> getResults()
      throws SQLException
    {
      return this.rawResults.getResults();
    }

    public CloseableIterator<String[]> iterator()
    {
      return closeableIterator();
    }

    public <UO> CloseableIterator<UO> iterator(RawRowMapper<UO> paramRawRowMapper)
      throws SQLException
    {
      return new RawResultsImpl(this.connectionSource, this.connection, this.query, [Ljava.lang.String.class, this.compiledStatement, this.columnNames, new StatementExecutor.UserObjectRowMapper(paramRawRowMapper, this.columnNames, this.stringMapper)).iterator();
    }
  }

  private static class UserObjectRowMapper<UO>
    implements GenericRowMapper<UO>
  {
    private final String[] columnNames;
    private final RawRowMapper<UO> mapper;
    private final GenericRowMapper<String[]> stringRowMapper;

    public UserObjectRowMapper(RawRowMapper<UO> paramRawRowMapper, String[] paramArrayOfString, GenericRowMapper<String[]> paramGenericRowMapper)
    {
      this.mapper = paramRawRowMapper;
      this.columnNames = paramArrayOfString;
      this.stringRowMapper = paramGenericRowMapper;
    }

    public UO mapRow(DatabaseResults paramDatabaseResults)
      throws SQLException
    {
      String[] arrayOfString = (String[])this.stringRowMapper.mapRow(paramDatabaseResults);
      return this.mapper.mapRow(this.columnNames, arrayOfString);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.StatementExecutor
 * JD-Core Version:    0.6.0
 */