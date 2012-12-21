package com.j256.ormlite.db;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldConverter;
import com.j256.ormlite.field.FieldType;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public abstract interface DatabaseType
{
  public abstract void addPrimaryKeySql(FieldType[] paramArrayOfFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3, List<String> paramList4)
    throws SQLException;

  public abstract void addUniqueComboSql(FieldType[] paramArrayOfFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3, List<String> paramList4)
    throws SQLException;

  public abstract void appendColumnArg(StringBuilder paramStringBuilder, FieldType paramFieldType, List<String> paramList1, List<String> paramList2, List<String> paramList3, List<String> paramList4)
    throws SQLException;

  public abstract void appendCreateTableSuffix(StringBuilder paramStringBuilder);

  public abstract void appendEscapedEntityName(StringBuilder paramStringBuilder, String paramString);

  public abstract void appendEscapedWord(StringBuilder paramStringBuilder, String paramString);

  public abstract void appendLimitValue(StringBuilder paramStringBuilder, int paramInt, Integer paramInteger);

  public abstract void appendOffsetValue(StringBuilder paramStringBuilder, int paramInt);

  public abstract void appendSelectNextValFromSequence(StringBuilder paramStringBuilder, String paramString);

  public abstract void dropColumnArg(FieldType paramFieldType, List<String> paramList1, List<String> paramList2);

  public abstract String generateIdSequenceName(String paramString, FieldType paramFieldType);

  public abstract String getCommentLinePrefix();

  public abstract FieldConverter getFieldConverter(DataPersister paramDataPersister);

  public abstract String getPingStatement();

  public abstract boolean isBatchUseTransaction();

  public abstract boolean isCreateIfNotExistsSupported();

  public abstract boolean isCreateTableReturnsZero();

  public abstract boolean isDatabaseUrlThisType(String paramString1, String paramString2);

  public abstract boolean isEntityNamesMustBeUpCase();

  public abstract boolean isIdSequenceNeeded();

  public abstract boolean isLimitAfterSelect();

  public abstract boolean isLimitSqlSupported();

  public abstract boolean isNestedSavePointsSupported();

  public abstract boolean isOffsetLimitArgument();

  public abstract boolean isOffsetSqlSupported();

  public abstract boolean isSelectSequenceBeforeInsert();

  public abstract boolean isTruncateSupported();

  public abstract boolean isVarcharFieldWidthSupported();

  public abstract void loadDriver()
    throws SQLException;

  public abstract void setDriver(Driver paramDriver);
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.db.DatabaseType
 * JD-Core Version:    0.6.0
 */