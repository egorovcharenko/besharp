package com.j256.ormlite.db;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDatabaseType
  implements DatabaseType
{
  protected static String DEFAULT_SEQUENCE_SUFFIX = "_id_seq";
  protected Driver driver;

  public void addPrimaryKeySql(FieldType[] paramArrayOfFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3, List<String> paramList4)
    throws SQLException
  {
    StringBuilder localStringBuilder = null;
    int i = paramArrayOfFieldType.length;
    int j = 0;
    if (j < i)
    {
      FieldType localFieldType = paramArrayOfFieldType[j];
      if ((localFieldType.isGeneratedId()) && (!generatedIdSqlAtEnd()));
      do
      {
        j++;
        break;
      }
      while (!localFieldType.isId());
      if (localStringBuilder == null)
      {
        localStringBuilder = new StringBuilder(48);
        localStringBuilder.append("PRIMARY KEY (");
      }
      while (true)
      {
        appendEscapedEntityName(localStringBuilder, localFieldType.getDbColumnName());
        break;
        localStringBuilder.append(',');
      }
    }
    if (localStringBuilder != null)
    {
      localStringBuilder.append(") ");
      paramList1.add(localStringBuilder.toString());
    }
  }

  protected void addSingleUnique(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" UNIQUE (");
    appendEscapedEntityName(localStringBuilder, paramFieldType.getDbColumnName());
    localStringBuilder.append(")");
    paramList1.add(localStringBuilder.toString());
  }

  public void addUniqueComboSql(FieldType[] paramArrayOfFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3, List<String> paramList4)
    throws SQLException
  {
    StringBuilder localStringBuilder = null;
    int i = paramArrayOfFieldType.length;
    int j = 0;
    if (j < i)
    {
      FieldType localFieldType = paramArrayOfFieldType[j];
      if (localFieldType.isUniqueCombo())
      {
        if (localStringBuilder != null)
          break label72;
        localStringBuilder = new StringBuilder(48);
        localStringBuilder.append("UNIQUE (");
      }
      while (true)
      {
        appendEscapedEntityName(localStringBuilder, localFieldType.getDbColumnName());
        j++;
        break;
        label72: localStringBuilder.append(',');
      }
    }
    if (localStringBuilder != null)
    {
      localStringBuilder.append(") ");
      paramList1.add(localStringBuilder.toString());
    }
  }

  protected void appendBooleanType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("BOOLEAN");
  }

  protected void appendByteArrayType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("BLOB");
  }

  protected void appendByteType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("TINYINT");
  }

  protected void appendCanBeNull(StringBuilder paramStringBuilder, FieldType paramFieldType)
  {
  }

  protected void appendCharType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("CHAR");
  }

  public void appendColumnArg(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3, List<String> paramList4)
    throws SQLException
  {
    appendEscapedEntityName(paramStringBuilder, paramFieldType.getDbColumnName());
    paramStringBuilder.append(' ');
    DataPersister localDataPersister = paramFieldType.getDataPersister();
    int i = paramFieldType.getWidth();
    if (i == 0)
      i = localDataPersister.getDefaultWidth();
    switch (1.$SwitchMap$com$j256$ormlite$field$SqlType[localDataPersister.getSqlType().ordinal()])
    {
    default:
      throw new IllegalArgumentException("Unknown SQL-type " + localDataPersister.getSqlType());
    case 1:
      appendStringType(paramStringBuilder, i);
      paramStringBuilder.append(' ');
      if ((paramFieldType.isGeneratedIdSequence()) && (!paramFieldType.isSelfGeneratedId()))
      {
        configureGeneratedIdSequence(paramStringBuilder, paramFieldType, paramList2, paramList1, paramList4);
        label196: if (paramFieldType.isGeneratedId())
          break;
        Object localObject = paramFieldType.getDefaultValue();
        if (localObject != null)
        {
          paramStringBuilder.append("DEFAULT ");
          appendDefaultValue(paramStringBuilder, paramFieldType, localObject);
          paramStringBuilder.append(' ');
        }
        if (!paramFieldType.isCanBeNull())
          break label435;
        appendCanBeNull(paramStringBuilder, paramFieldType);
      }
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    }
    while (true)
    {
      if (paramFieldType.isUnique())
        addSingleUnique(paramStringBuilder, paramFieldType, paramList1, paramList3);
      return;
      appendLongStringType(paramStringBuilder, i);
      break;
      appendBooleanType(paramStringBuilder, i);
      break;
      appendDateType(paramStringBuilder, i);
      break;
      appendCharType(paramStringBuilder, i);
      break;
      appendByteType(paramStringBuilder, i);
      break;
      appendByteArrayType(paramStringBuilder, i);
      break;
      appendShortType(paramStringBuilder, i);
      break;
      appendIntegerType(paramStringBuilder, i);
      break;
      appendLongType(paramStringBuilder, i);
      break;
      appendFloatType(paramStringBuilder, i);
      break;
      appendDoubleType(paramStringBuilder, i);
      break;
      appendSerializableType(paramStringBuilder, i);
      break;
      if ((paramFieldType.isGeneratedId()) && (!paramFieldType.isSelfGeneratedId()))
      {
        configureGeneratedId(paramStringBuilder, paramFieldType, paramList2, paramList1, paramList4);
        break label196;
      }
      if (!paramFieldType.isId())
        break label196;
      configureId(paramStringBuilder, paramFieldType, paramList2, paramList1, paramList4);
      break label196;
      label435: paramStringBuilder.append("NOT NULL ");
    }
  }

  public void appendCreateTableSuffix(StringBuilder paramStringBuilder)
  {
  }

  protected void appendDateType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("TIMESTAMP");
  }

  protected void appendDefaultValue(StringBuilder paramStringBuilder, FieldType paramFieldType, Object paramObject)
  {
    if (paramFieldType.isEscapedDefaultValue())
      appendEscapedWord(paramStringBuilder, paramObject.toString());
    while (true)
    {
      return;
      paramStringBuilder.append(paramObject);
    }
  }

  protected void appendDoubleType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("DOUBLE PRECISION");
  }

  public void appendEscapedEntityName(StringBuilder paramStringBuilder, String paramString)
  {
    paramStringBuilder.append('`').append(paramString).append('`');
  }

  public void appendEscapedWord(StringBuilder paramStringBuilder, String paramString)
  {
    paramStringBuilder.append('\'').append(paramString).append('\'');
  }

  protected void appendFloatType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("FLOAT");
  }

  protected void appendIntegerType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("INTEGER");
  }

  public void appendLimitValue(StringBuilder paramStringBuilder, int paramInt, Integer paramInteger)
  {
    paramStringBuilder.append("LIMIT ").append(paramInt).append(' ');
  }

  protected void appendLongStringType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("TEXT");
  }

  protected void appendLongType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("BIGINT");
  }

  public void appendOffsetValue(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("OFFSET ").append(paramInt).append(' ');
  }

  public void appendSelectNextValFromSequence(StringBuilder paramStringBuilder, String paramString)
  {
  }

  protected void appendSerializableType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("BLOB");
  }

  protected void appendShortType(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append("SMALLINT");
  }

  protected void appendStringType(StringBuilder paramStringBuilder, int paramInt)
  {
    if (isVarcharFieldWidthSupported())
      paramStringBuilder.append("VARCHAR(").append(paramInt).append(")");
    while (true)
    {
      return;
      paramStringBuilder.append("VARCHAR");
    }
  }

  protected void configureGeneratedId(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3)
  {
    throw new IllegalStateException("GeneratedId is not supported by database " + getDatabaseName() + " for field " + paramFieldType);
  }

  protected void configureGeneratedIdSequence(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3)
    throws SQLException
  {
    throw new SQLException("GeneratedIdSequence is not supported by database " + getDatabaseName() + " for field " + paramFieldType);
  }

  protected void configureId(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3)
  {
  }

  public void dropColumnArg(FieldType paramFieldType, List<String> paramList1, List<String> paramList2)
  {
  }

  public String generateIdSequenceName(String paramString, FieldType paramFieldType)
  {
    String str1 = paramString + DEFAULT_SEQUENCE_SUFFIX;
    if (isEntityNamesMustBeUpCase());
    for (String str2 = str1.toUpperCase(); ; str2 = str1)
      return str2;
  }

  protected boolean generatedIdSqlAtEnd()
  {
    return true;
  }

  public String getCommentLinePrefix()
  {
    return "-- ";
  }

  protected abstract String getDatabaseName();

  protected abstract String getDriverClassName();

  public FieldConverter getFieldConverter(DataPersister paramDataPersister)
  {
    return paramDataPersister;
  }

  public String getPingStatement()
  {
    return "SELECT 1";
  }

  public boolean isBatchUseTransaction()
  {
    return false;
  }

  public boolean isCreateIfNotExistsSupported()
  {
    return false;
  }

  public boolean isCreateTableReturnsZero()
  {
    return true;
  }

  public boolean isEntityNamesMustBeUpCase()
  {
    return false;
  }

  public boolean isIdSequenceNeeded()
  {
    return false;
  }

  public boolean isLimitAfterSelect()
  {
    return false;
  }

  public boolean isLimitSqlSupported()
  {
    return true;
  }

  public boolean isNestedSavePointsSupported()
  {
    return true;
  }

  public boolean isOffsetLimitArgument()
  {
    return false;
  }

  public boolean isOffsetSqlSupported()
  {
    return true;
  }

  public boolean isSelectSequenceBeforeInsert()
  {
    return false;
  }

  public boolean isTruncateSupported()
  {
    return false;
  }

  public boolean isVarcharFieldWidthSupported()
  {
    return true;
  }

  public void loadDriver()
    throws SQLException
  {
    String str = getDriverClassName();
    if (str != null);
    try
    {
      Class.forName(str);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw SqlExceptionUtil.create("Driver class was not found for " + getDatabaseName() + " database.  Missing jar with class " + str + ".", localClassNotFoundException);
  }

  public void setDriver(Driver paramDriver)
  {
    this.driver = paramDriver;
  }

  protected static class BooleanNumberFieldConverter
    implements FieldConverter
  {
    public SqlType getSqlType()
    {
      return SqlType.BOOLEAN;
    }

    public boolean isStreamType()
    {
      return false;
    }

    public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
      throws SQLException
    {
      if (((Boolean)paramObject).booleanValue());
      for (Byte localByte = Byte.valueOf(1); ; localByte = Byte.valueOf(0))
        return localByte;
    }

    public Object parseDefaultString(FieldType paramFieldType, String paramString)
      throws SQLException
    {
      if (Boolean.parseBoolean(paramString));
      for (Byte localByte = Byte.valueOf(1); ; localByte = Byte.valueOf(0))
        return localByte;
    }

    public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
      throws SQLException
    {
      if (paramDatabaseResults.getByte(paramInt) == 1);
      for (Boolean localBoolean = Boolean.valueOf(true); ; localBoolean = Boolean.valueOf(false))
        return localBoolean;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.db.BaseDatabaseType
 * JD-Core Version:    0.6.0
 */