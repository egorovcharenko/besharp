package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedPreparedStmt<T, ID> extends BaseMappedQuery<T, ID>
  implements PreparedQuery<T>, PreparedDelete<T>, PreparedUpdate<T>
{
  private final ArgumentHolder[] argHolders;
  private final Integer limit;
  private final StatementBuilder.StatementType type;

  public MappedPreparedStmt(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType1, FieldType[] paramArrayOfFieldType2, ArgumentHolder[] paramArrayOfArgumentHolder, Integer paramInteger, StatementBuilder.StatementType paramStatementType)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType1, paramArrayOfFieldType2);
    this.argHolders = paramArrayOfArgumentHolder;
    this.limit = paramInteger;
    this.type = paramStatementType;
  }

  public CompiledStatement compile(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
    CompiledStatement localCompiledStatement = paramDatabaseConnection.compileStatement(this.statement, this.type, this.argFieldTypes);
    while (true)
    {
      Object[] arrayOfObject1;
      try
      {
        if (this.limit == null)
          continue;
        localCompiledStatement.setMaxRows(this.limit.intValue());
        arrayOfObject1 = null;
        if ((!logger.isLevelEnabled(Log.Level.TRACE)) || (this.argHolders.length <= 0))
          break label256;
        arrayOfObject1 = new Object[this.argHolders.length];
        break label256;
        if (i < this.argHolders.length)
        {
          Object localObject2 = this.argHolders[i].getSqlArgValue();
          if (localObject2 != null)
            continue;
          localCompiledStatement.setNull(i, this.argFieldTypes[i].getSqlType());
          if (arrayOfObject1 == null)
            break label262;
          arrayOfObject1[i] = localObject2;
          break label262;
          localCompiledStatement.setObject(i, localObject2, this.argFieldTypes[i].getSqlType());
          continue;
        }
      }
      finally
      {
        if (0 != 0)
          continue;
        localCompiledStatement.close();
      }
      Logger localLogger1 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = this.statement;
      arrayOfObject2[1] = Integer.valueOf(this.argHolders.length);
      localLogger1.debug("prepared statement '{}' with {} args", arrayOfObject2);
      if (arrayOfObject1 != null)
      {
        Logger localLogger2 = logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = arrayOfObject1;
        localLogger2.trace("prepared statement arguments: {}", arrayOfObject3);
      }
      if (1 == 0)
        localCompiledStatement.close();
      return localCompiledStatement;
      label256: int i = 0;
      continue;
      label262: i++;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedPreparedStmt
 * JD-Core Version:    0.6.0
 */