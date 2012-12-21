package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.OrderBy;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryBuilder<T, ID> extends StatementBuilder<T, ID>
{
  private final Dao<T, ID> dao;
  private boolean distinct = false;
  private List<String> groupByList = null;
  private String groupByRaw = null;
  private final FieldType idField;
  private boolean isInnerQuery = false;
  private List<OrderBy> orderByList = null;
  private String orderByRaw = null;
  private FieldType[] resultFieldTypes;
  private List<String> selectColumnList = null;
  private boolean selectIdColumn = true;

  public QueryBuilder(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, Dao<T, ID> paramDao)
  {
    super(paramDatabaseType, paramTableInfo, StatementBuilder.StatementType.SELECT);
    this.idField = paramTableInfo.getIdField();
    this.dao = paramDao;
  }

  private void addSelectColumnToList(String paramString)
  {
    if (verifyColumnName(paramString).isForeignCollection())
      throw new IllegalArgumentException("Can't select from foreign colletion field: " + paramString);
    this.selectColumnList.add(paramString);
  }

  private void appendColumns(StringBuilder paramStringBuilder)
    throws SQLException
  {
    if (this.selectColumnList == null)
      paramStringBuilder.append("* ");
    ArrayList localArrayList;
    for (this.resultFieldTypes = this.tableInfo.getFieldTypes(); ; this.resultFieldTypes = ((FieldType[])localArrayList.toArray(new FieldType[localArrayList.size()])))
    {
      return;
      int i = 1;
      int j;
      label68: String str;
      if (this.isInnerQuery)
      {
        j = 1;
        localArrayList = new ArrayList(1 + this.selectColumnList.size());
        Iterator localIterator = this.selectColumnList.iterator();
        if (!localIterator.hasNext())
          break label145;
        str = (String)localIterator.next();
        if (i == 0)
          break label135;
        i = 0;
      }
      while (true)
      {
        FieldType localFieldType = this.tableInfo.getFieldTypeByColumnName(str);
        appendFieldColumnName(paramStringBuilder, localFieldType, localArrayList);
        if (localFieldType != this.idField)
          break label68;
        j = 1;
        break label68;
        j = 0;
        break;
        label135: paramStringBuilder.append(',');
      }
      label145: if ((j == 0) && (this.selectIdColumn))
      {
        if (i == 0)
          paramStringBuilder.append(',');
        appendFieldColumnName(paramStringBuilder, this.idField, localArrayList);
      }
      paramStringBuilder.append(' ');
    }
  }

  private void appendFieldColumnName(StringBuilder paramStringBuilder, FieldType paramFieldType, List<FieldType> paramList)
  {
    this.databaseType.appendEscapedEntityName(paramStringBuilder, paramFieldType.getDbColumnName());
    if (paramList != null)
      paramList.add(paramFieldType);
  }

  private void appendGroupBys(StringBuilder paramStringBuilder)
  {
    if (((this.groupByList == null) || (this.groupByList.isEmpty())) && (this.groupByRaw == null));
    while (true)
    {
      return;
      paramStringBuilder.append("GROUP BY ");
      if (this.groupByRaw == null)
        break;
      paramStringBuilder.append(this.groupByRaw);
      paramStringBuilder.append(' ');
    }
    int i = 1;
    Iterator localIterator = this.groupByList.iterator();
    label73: String str;
    if (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      if (i == 0)
        break label116;
      i = 0;
    }
    while (true)
    {
      this.databaseType.appendEscapedEntityName(paramStringBuilder, str);
      break label73;
      break;
      label116: paramStringBuilder.append(',');
    }
  }

  private void appendLimit(StringBuilder paramStringBuilder)
  {
    if ((this.limit != null) && (this.databaseType.isLimitSqlSupported()))
      this.databaseType.appendLimitValue(paramStringBuilder, this.limit.intValue(), this.offset);
  }

  private void appendOffset(StringBuilder paramStringBuilder)
    throws SQLException
  {
    if (this.offset == null);
    while (true)
    {
      return;
      if (this.databaseType.isOffsetLimitArgument())
      {
        if (this.limit != null)
          continue;
        throw new SQLException("If the offset is specified, limit must also be specified with this database");
      }
      this.databaseType.appendOffsetValue(paramStringBuilder, this.offset.intValue());
    }
  }

  private void appendOrderBys(StringBuilder paramStringBuilder)
    throws SQLException
  {
    if (((this.orderByList == null) || (this.orderByList.isEmpty())) && (this.orderByRaw == null));
    while (true)
    {
      return;
      paramStringBuilder.append("ORDER BY ");
      if (this.orderByRaw == null)
        break;
      paramStringBuilder.append(this.orderByRaw);
      paramStringBuilder.append(' ');
    }
    int i = 1;
    Iterator localIterator = this.orderByList.iterator();
    label73: OrderBy localOrderBy;
    if (localIterator.hasNext())
    {
      localOrderBy = (OrderBy)localIterator.next();
      if (i == 0)
        break label138;
      i = 0;
    }
    while (true)
    {
      String str = localOrderBy.getColumnName();
      this.databaseType.appendEscapedEntityName(paramStringBuilder, str);
      if (localOrderBy.isAscending())
        break label73;
      paramStringBuilder.append(" DESC");
      break label73;
      break;
      label138: paramStringBuilder.append(',');
    }
  }

  protected void appendStatementEnd(StringBuilder paramStringBuilder)
    throws SQLException
  {
    appendGroupBys(paramStringBuilder);
    appendOrderBys(paramStringBuilder);
    if (!this.databaseType.isLimitAfterSelect())
      appendLimit(paramStringBuilder);
    appendOffset(paramStringBuilder);
  }

  protected void appendStatementStart(StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    paramStringBuilder.append("SELECT ");
    if (this.databaseType.isLimitAfterSelect())
      appendLimit(paramStringBuilder);
    if (this.distinct)
      paramStringBuilder.append("DISTINCT ");
    appendColumns(paramStringBuilder);
    paramStringBuilder.append("FROM ");
    this.databaseType.appendEscapedEntityName(paramStringBuilder, this.tableInfo.getTableName());
    paramStringBuilder.append(' ');
  }

  public QueryBuilder<T, ID> distinct()
  {
    this.distinct = true;
    this.selectIdColumn = false;
    return this;
  }

  void enableInnerQuery()
    throws SQLException
  {
    this.isInnerQuery = true;
  }

  protected FieldType[] getResultFieldTypes()
    throws SQLException
  {
    return this.resultFieldTypes;
  }

  int getSelectColumnCount()
  {
    if (this.selectColumnList == null);
    for (int i = 0; ; i = this.selectColumnList.size())
      return i;
  }

  public QueryBuilder<T, ID> groupBy(String paramString)
  {
    if (verifyColumnName(paramString).isForeignCollection())
      throw new IllegalArgumentException("Can't groupBy foreign colletion field: " + paramString);
    if (this.groupByList == null)
      this.groupByList = new ArrayList();
    this.groupByList.add(paramString);
    this.selectIdColumn = false;
    return this;
  }

  public QueryBuilder<T, ID> groupByRaw(String paramString)
  {
    this.groupByRaw = paramString;
    return this;
  }

  public CloseableIterator<T> iterator()
    throws SQLException
  {
    return this.dao.iterator(prepare());
  }

  public QueryBuilder<T, ID> limit(Integer paramInteger)
  {
    this.limit = paramInteger;
    return this;
  }

  public QueryBuilder<T, ID> offset(Integer paramInteger)
    throws SQLException
  {
    if (this.databaseType.isOffsetSqlSupported())
    {
      this.offset = paramInteger;
      return this;
    }
    throw new SQLException("Offset is not supported by this database");
  }

  public QueryBuilder<T, ID> orderBy(String paramString, boolean paramBoolean)
  {
    if (verifyColumnName(paramString).isForeignCollection())
      throw new IllegalArgumentException("Can't orderBy foreign colletion field: " + paramString);
    if (this.orderByList == null)
      this.orderByList = new ArrayList();
    this.orderByList.add(new OrderBy(paramString, paramBoolean));
    return this;
  }

  public QueryBuilder<T, ID> orderByRaw(String paramString)
  {
    this.orderByRaw = paramString;
    return this;
  }

  public PreparedQuery<T> prepare()
    throws SQLException
  {
    return super.prepareStatement();
  }

  public List<T> query()
    throws SQLException
  {
    return this.dao.query(prepare());
  }

  public QueryBuilder<T, ID> selectColumns(Iterable<String> paramIterable)
  {
    if (this.selectColumnList == null)
      this.selectColumnList = new ArrayList();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
      addSelectColumnToList((String)localIterator.next());
    return this;
  }

  public QueryBuilder<T, ID> selectColumns(String[] paramArrayOfString)
  {
    if (this.selectColumnList == null)
      this.selectColumnList = new ArrayList();
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      addSelectColumnToList(paramArrayOfString[j]);
    return this;
  }

  public static class InternalQueryBuilderWrapper
  {
    private final QueryBuilder<?, ?> queryBuilder;

    public InternalQueryBuilderWrapper(QueryBuilder<?, ?> paramQueryBuilder)
    {
      this.queryBuilder = paramQueryBuilder;
    }

    public void appendStatementString(StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
      throws SQLException
    {
      this.queryBuilder.appendStatementString(paramStringBuilder, paramList);
    }

    public FieldType[] getResultFieldTypes()
      throws SQLException
    {
      return this.queryBuilder.resultFieldTypes;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.QueryBuilder
 * JD-Core Version:    0.6.0
 */