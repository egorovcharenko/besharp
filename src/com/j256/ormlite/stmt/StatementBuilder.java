package com.j256.ormlite.stmt;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class StatementBuilder<T, ID>
{
  private static Logger logger = LoggerFactory.getLogger(StatementBuilder.class);
  protected final DatabaseType databaseType;
  protected Integer limit = null;
  protected Integer offset = null;
  protected final TableInfo<T, ID> tableInfo;
  private final StatementType type;
  private Where<T, ID> where = null;

  public StatementBuilder(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, StatementType paramStatementType)
  {
    this.databaseType = paramDatabaseType;
    this.tableInfo = paramTableInfo;
    this.type = paramStatementType;
    if ((paramStatementType != StatementType.SELECT) && (paramStatementType != StatementType.UPDATE) && (paramStatementType != StatementType.DELETE))
      throw new IllegalStateException("Building a statement from a " + paramStatementType + " statement is not allowed");
  }

  private String buildStatementString(List<ArgumentHolder> paramList)
    throws SQLException
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    appendStatementString(localStringBuilder, paramList);
    String str = localStringBuilder.toString();
    Logger localLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = str;
    localLogger.debug("built statement {}", arrayOfObject);
    return str;
  }

  protected abstract void appendStatementEnd(StringBuilder paramStringBuilder)
    throws SQLException;

  protected abstract void appendStatementStart(StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException;

  protected void appendStatementString(StringBuilder paramStringBuilder, List<ArgumentHolder> paramList)
    throws SQLException
  {
    appendStatementStart(paramStringBuilder, paramList);
    if (this.where != null)
    {
      paramStringBuilder.append("WHERE ");
      this.where.appendSql(this.databaseType, paramStringBuilder, paramList);
    }
    appendStatementEnd(paramStringBuilder);
  }

  protected FieldType[] getResultFieldTypes()
    throws SQLException
  {
    return null;
  }

  StatementType getType()
  {
    return this.type;
  }

  protected MappedPreparedStmt<T, ID> prepareStatement()
    throws SQLException
  {
    ArrayList localArrayList = new ArrayList();
    String str = buildStatementString(localArrayList);
    ArgumentHolder[] arrayOfArgumentHolder = (ArgumentHolder[])localArrayList.toArray(new ArgumentHolder[localArrayList.size()]);
    FieldType[] arrayOfFieldType1 = getResultFieldTypes();
    FieldType[] arrayOfFieldType2 = new FieldType[localArrayList.size()];
    for (int i = 0; i < arrayOfArgumentHolder.length; i++)
      arrayOfFieldType2[i] = arrayOfArgumentHolder[i].getFieldType();
    TableInfo localTableInfo = this.tableInfo;
    if (this.databaseType.isLimitSqlSupported());
    for (Integer localInteger = null; ; localInteger = this.limit)
      return new MappedPreparedStmt(localTableInfo, str, arrayOfFieldType2, arrayOfFieldType1, arrayOfArgumentHolder, localInteger, this.type);
  }

  public String prepareStatementString()
    throws SQLException
  {
    return buildStatementString(new ArrayList());
  }

  public void setWhere(Where<T, ID> paramWhere)
  {
    this.where = paramWhere;
  }

  protected FieldType verifyColumnName(String paramString)
  {
    return this.tableInfo.getFieldTypeByColumnName(paramString);
  }

  public Where<T, ID> where()
  {
    this.where = new Where(this.tableInfo, this);
    return this.where;
  }

  public static enum StatementType
  {
    static
    {
      DELETE = new StatementType("DELETE", 3);
      EXECUTE = new StatementType("EXECUTE", 4);
      StatementType[] arrayOfStatementType = new StatementType[5];
      arrayOfStatementType[0] = INSERT;
      arrayOfStatementType[1] = SELECT;
      arrayOfStatementType[2] = UPDATE;
      arrayOfStatementType[3] = DELETE;
      arrayOfStatementType[4] = EXECUTE;
      $VALUES = arrayOfStatementType;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.StatementBuilder
 * JD-Core Version:    0.6.0
 */