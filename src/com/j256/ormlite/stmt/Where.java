package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.Between;
import com.j256.ormlite.stmt.query.Clause;
import com.j256.ormlite.stmt.query.Exists;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.stmt.query.InSubQuery;
import com.j256.ormlite.stmt.query.IsNotNull;
import com.j256.ormlite.stmt.query.IsNull;
import com.j256.ormlite.stmt.query.ManyClause;
import com.j256.ormlite.stmt.query.NeedsFutureClause;
import com.j256.ormlite.stmt.query.Not;
import com.j256.ormlite.stmt.query.Raw;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.List;

public class Where<T, ID>
{
  private static final int START_CLAUSE_SIZE = 4;
  private Clause[] clauseStack = new Clause[4];
  private int clauseStackLevel = 0;
  private final String idColumnName;
  private final FieldType idFieldType;
  private NeedsFutureClause needsFuture = null;
  private final StatementBuilder<T, ID> statementBuilder;
  private final TableInfo<T, ID> tableInfo;

  Where(TableInfo<T, ID> paramTableInfo, StatementBuilder<T, ID> paramStatementBuilder)
  {
    this.tableInfo = paramTableInfo;
    this.statementBuilder = paramStatementBuilder;
    this.idFieldType = paramTableInfo.getIdField();
    if (this.idFieldType == null);
    for (this.idColumnName = null; ; this.idColumnName = this.idFieldType.getDbColumnName())
      return;
  }

  private void addClause(Clause paramClause)
  {
    if (this.needsFuture == null)
      push(paramClause);
    while (true)
    {
      return;
      this.needsFuture.setMissingClause(paramClause);
      this.needsFuture = null;
    }
  }

  private void addNeedsFuture(NeedsFutureClause paramNeedsFutureClause)
  {
    if (this.needsFuture != null)
      throw new IllegalStateException(this.needsFuture + " is already waiting for a future clause, can't add: " + paramNeedsFutureClause);
    this.needsFuture = paramNeedsFutureClause;
    push(paramNeedsFutureClause);
  }

  private Clause[] buildClauseArray(Where<T, ID>[] paramArrayOfWhere, String paramString)
  {
    Clause[] arrayOfClause;
    if (paramArrayOfWhere.length == 0)
      arrayOfClause = null;
    while (true)
    {
      return arrayOfClause;
      arrayOfClause = new Clause[paramArrayOfWhere.length];
      for (int i = paramArrayOfWhere.length - 1; i >= 0; i--)
        arrayOfClause[i] = pop("AND");
    }
  }

  private FieldType findColumnFieldType(String paramString)
    throws SQLException
  {
    return this.tableInfo.getFieldTypeByColumnName(paramString);
  }

  private Clause peek()
  {
    return this.clauseStack[(this.clauseStackLevel - 1)];
  }

  private Clause pop(String paramString)
  {
    if (this.clauseStackLevel == 0)
      throw new IllegalStateException("Expecting there to be a clause already defined for '" + paramString + "' operation");
    Clause[] arrayOfClause = this.clauseStack;
    int i = this.clauseStackLevel - 1;
    this.clauseStackLevel = i;
    Clause localClause = arrayOfClause[i];
    this.clauseStack[this.clauseStackLevel] = null;
    return localClause;
  }

  private void push(Clause paramClause)
  {
    if (this.clauseStackLevel == this.clauseStack.length)
    {
      Clause[] arrayOfClause2 = new Clause[2 * this.clauseStackLevel];
      for (int j = 0; j < this.clauseStackLevel; j++)
      {
        arrayOfClause2[j] = this.clauseStack[j];
        this.clauseStack[j] = null;
      }
      this.clauseStack = arrayOfClause2;
    }
    Clause[] arrayOfClause1 = this.clauseStack;
    int i = this.clauseStackLevel;
    this.clauseStackLevel = (i + 1);
    arrayOfClause1[i] = paramClause;
  }

  public Where<T, ID> and()
  {
    addNeedsFuture(new ManyClause(pop("AND"), "AND"));
    return this;
  }

  public Where<T, ID> and(int paramInt)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException("Must have at least one clause in and(numClauses)");
    Clause[] arrayOfClause = new Clause[paramInt];
    for (int i = paramInt - 1; i >= 0; i--)
      arrayOfClause[i] = pop("AND");
    addClause(new ManyClause(arrayOfClause, "AND"));
    return this;
  }

  public Where<T, ID> and(Where<T, ID> paramWhere1, Where<T, ID> paramWhere2, Where<T, ID>[] paramArrayOfWhere)
  {
    Clause[] arrayOfClause = buildClauseArray(paramArrayOfWhere, "AND");
    Clause localClause = pop("AND");
    addClause(new ManyClause(pop("AND"), localClause, arrayOfClause, "AND"));
    return this;
  }

  void appendSql(DatabaseType paramDatabaseType, StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    if (this.clauseStackLevel == 0)
      throw new IllegalStateException("No where clauses defined.  Did you miss a where operation?");
    if (this.clauseStackLevel != 1)
      throw new IllegalStateException("Both the \"left-hand\" and \"right-hand\" clauses have been defined.  Did you miss an AND or OR?");
    peek().appendSql(paramDatabaseType, paramStringBuilder, paramList);
  }

  public Where<T, ID> between(String paramString, Object paramObject1, Object paramObject2)
    throws SQLException
  {
    addClause(new Between(paramString, findColumnFieldType(paramString), paramObject1, paramObject2));
    return this;
  }

  public void clear()
  {
    for (int i = 0; i < this.clauseStackLevel; i++)
      this.clauseStack[i] = null;
    this.clauseStackLevel = 0;
  }

  public Where<T, ID> eq(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, "="));
    return this;
  }

  public Where<T, ID> exists(QueryBuilder<?, ?> paramQueryBuilder)
    throws SQLException
  {
    paramQueryBuilder.enableInnerQuery();
    addClause(new Exists(new QueryBuilder.InternalQueryBuilderWrapper(paramQueryBuilder)));
    return this;
  }

  public Where<T, ID> ge(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, ">="));
    return this;
  }

  public Where<T, ID> gt(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, ">"));
    return this;
  }

  public <OD> Where<T, ID> idEq(Dao<OD, ?> paramDao, OD paramOD)
    throws SQLException
  {
    if (this.idColumnName == null)
      throw new SQLException("Object has no id column specified");
    addClause(new SimpleComparison(this.idColumnName, this.idFieldType, paramDao.extractId(paramOD), "="));
    return this;
  }

  public Where<T, ID> idEq(ID paramID)
    throws SQLException
  {
    if (this.idColumnName == null)
      throw new SQLException("Object has no id column specified");
    addClause(new SimpleComparison(this.idColumnName, this.idFieldType, paramID, "="));
    return this;
  }

  public Where<T, ID> in(String paramString, QueryBuilder<?, ?> paramQueryBuilder)
    throws SQLException
  {
    if (paramQueryBuilder.getSelectColumnCount() != 1)
      throw new SQLException("Inner query must have only 1 select column specified instead of " + paramQueryBuilder.getSelectColumnCount());
    paramQueryBuilder.enableInnerQuery();
    addClause(new InSubQuery(paramString, findColumnFieldType(paramString), new QueryBuilder.InternalQueryBuilderWrapper(paramQueryBuilder)));
    return this;
  }

  public Where<T, ID> in(String paramString, Iterable<?> paramIterable)
    throws SQLException
  {
    addClause(new In(paramString, findColumnFieldType(paramString), paramIterable));
    return this;
  }

  public Where<T, ID> in(String paramString, Object[] paramArrayOfObject)
    throws SQLException
  {
    if ((paramArrayOfObject.length == 1) && (paramArrayOfObject[0].getClass().isArray()))
      throw new IllegalArgumentException("in(Object... objects) seems to be an array within an array");
    addClause(new In(paramString, findColumnFieldType(paramString), paramArrayOfObject));
    return this;
  }

  public Where<T, ID> isNotNull(String paramString)
    throws SQLException
  {
    addClause(new IsNotNull(paramString, findColumnFieldType(paramString)));
    return this;
  }

  public Where<T, ID> isNull(String paramString)
    throws SQLException
  {
    addClause(new IsNull(paramString, findColumnFieldType(paramString)));
    return this;
  }

  public CloseableIterator<T> iterator()
    throws SQLException
  {
    if ((this.statementBuilder instanceof QueryBuilder))
      return ((QueryBuilder)this.statementBuilder).iterator();
    throw new SQLException("Cannot call iterator on a statement of type " + this.statementBuilder.getType());
  }

  public Where<T, ID> le(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, "<="));
    return this;
  }

  public Where<T, ID> like(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, "LIKE"));
    return this;
  }

  public Where<T, ID> lt(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, "<"));
    return this;
  }

  public Where<T, ID> ne(String paramString, Object paramObject)
    throws SQLException
  {
    addClause(new SimpleComparison(paramString, findColumnFieldType(paramString), paramObject, "<>"));
    return this;
  }

  public Where<T, ID> not()
  {
    addNeedsFuture(new Not());
    return this;
  }

  public Where<T, ID> not(Where<T, ID> paramWhere)
  {
    addClause(new Not(pop("NOT")));
    return this;
  }

  public Where<T, ID> or()
  {
    addNeedsFuture(new ManyClause(pop("OR"), "OR"));
    return this;
  }

  public Where<T, ID> or(int paramInt)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException("Must have at least one clause in or(numClauses)");
    Clause[] arrayOfClause = new Clause[paramInt];
    for (int i = paramInt - 1; i >= 0; i--)
      arrayOfClause[i] = pop("OR");
    addClause(new ManyClause(arrayOfClause, "OR"));
    return this;
  }

  public Where<T, ID> or(Where<T, ID> paramWhere1, Where<T, ID> paramWhere2, Where<T, ID>[] paramArrayOfWhere)
  {
    Clause[] arrayOfClause = buildClauseArray(paramArrayOfWhere, "OR");
    Clause localClause = pop("OR");
    addClause(new ManyClause(pop("OR"), localClause, arrayOfClause, "OR"));
    return this;
  }

  public PreparedQuery<T> prepare()
    throws SQLException
  {
    return this.statementBuilder.prepareStatement();
  }

  public List<T> query()
    throws SQLException
  {
    if ((this.statementBuilder instanceof QueryBuilder))
      return ((QueryBuilder)this.statementBuilder).query();
    throw new SQLException("Cannot call query on a statement of type " + this.statementBuilder.getType());
  }

  public Where<T, ID> raw(String paramString)
  {
    addClause(new Raw(paramString));
    return this;
  }

  public String toString()
  {
    if (this.clauseStackLevel == 0);
    Clause localClause;
    for (String str = "empty where clause"; ; str = "where clause: " + localClause)
    {
      return str;
      localClause = peek();
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.Where
 * JD-Core Version:    0.6.0
 */