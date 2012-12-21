package com.j256.ormlite.dao;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract interface Dao<T, ID> extends CloseableIterable<T>
{
  public abstract <CT> CT callBatchTasks(Callable<CT> paramCallable)
    throws Exception;

  public abstract void closeLastIterator()
    throws SQLException;

  public abstract long countOf()
    throws SQLException;

  public abstract int create(T paramT)
    throws SQLException;

  public abstract T createIfNotExists(T paramT)
    throws SQLException;

  public abstract CreateOrUpdateStatus createOrUpdate(T paramT)
    throws SQLException;

  public abstract int delete(PreparedDelete<T> paramPreparedDelete)
    throws SQLException;

  public abstract int delete(T paramT)
    throws SQLException;

  public abstract int delete(Collection<T> paramCollection)
    throws SQLException;

  public abstract DeleteBuilder<T, ID> deleteBuilder();

  public abstract int deleteIds(Collection<ID> paramCollection)
    throws SQLException;

  public abstract int executeRaw(String paramString, String[] paramArrayOfString)
    throws SQLException;

  public abstract ID extractId(T paramT)
    throws SQLException;

  public abstract FieldType findForeignFieldType(Class<?> paramClass);

  public abstract Class<T> getDataClass();

  public abstract <FT> ForeignCollection<FT> getEmptyForeignCollection(String paramString)
    throws SQLException;

  public abstract CloseableWrappedIterable<T> getWrappedIterable();

  public abstract CloseableWrappedIterable<T> getWrappedIterable(PreparedQuery<T> paramPreparedQuery);

  public abstract boolean isTableExists()
    throws SQLException;

  public abstract boolean isUpdatable();

  public abstract CloseableIterator<T> iterator();

  public abstract CloseableIterator<T> iterator(PreparedQuery<T> paramPreparedQuery)
    throws SQLException;

  @Deprecated
  public abstract RawResults iteratorRaw(String paramString)
    throws SQLException;

  public abstract String objectToString(T paramT);

  public abstract boolean objectsEqual(T paramT1, T paramT2)
    throws SQLException;

  public abstract List<T> query(PreparedQuery<T> paramPreparedQuery)
    throws SQLException;

  public abstract QueryBuilder<T, ID> queryBuilder();

  public abstract List<T> queryForAll()
    throws SQLException;

  @Deprecated
  public abstract RawResults queryForAllRaw(String paramString)
    throws SQLException;

  public abstract List<T> queryForEq(String paramString, Object paramObject)
    throws SQLException;

  public abstract List<T> queryForFieldValues(Map<String, Object> paramMap)
    throws SQLException;

  public abstract T queryForFirst(PreparedQuery<T> paramPreparedQuery)
    throws SQLException;

  public abstract T queryForId(ID paramID)
    throws SQLException;

  public abstract List<T> queryForMatching(T paramT)
    throws SQLException;

  public abstract T queryForSameId(T paramT)
    throws SQLException;

  public abstract <UO> GenericRawResults<UO> queryRaw(String paramString, RawRowMapper<UO> paramRawRowMapper, String[] paramArrayOfString)
    throws SQLException;

  public abstract GenericRawResults<Object[]> queryRaw(String paramString, DataType[] paramArrayOfDataType, String[] paramArrayOfString)
    throws SQLException;

  public abstract GenericRawResults<String[]> queryRaw(String paramString, String[] paramArrayOfString)
    throws SQLException;

  public abstract int refresh(T paramT)
    throws SQLException;

  public abstract int update(PreparedUpdate<T> paramPreparedUpdate)
    throws SQLException;

  public abstract int update(T paramT)
    throws SQLException;

  public abstract UpdateBuilder<T, ID> updateBuilder();

  public abstract int updateId(T paramT, ID paramID)
    throws SQLException;

  public abstract int updateRaw(String paramString, String[] paramArrayOfString)
    throws SQLException;

  public static class CreateOrUpdateStatus
  {
    boolean created;
    int numLinesChanged;
    boolean updated;

    public CreateOrUpdateStatus(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    {
      this.created = paramBoolean1;
      this.updated = paramBoolean2;
      this.numLinesChanged = paramInt;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.dao.Dao
 * JD-Core Version:    0.6.0
 */