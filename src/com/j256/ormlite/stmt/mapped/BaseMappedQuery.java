package com.j256.ormlite.stmt.mapped;

import TT;;
import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseMappedQuery<T, ID> extends BaseMappedStatement<T, ID>
  implements GenericRowMapper<T>
{
  private Map<String, Integer> columnPositions = null;
  protected final FieldType[] resultsFieldTypes;

  protected BaseMappedQuery(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType1, FieldType[] paramArrayOfFieldType2)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType1);
    this.resultsFieldTypes = paramArrayOfFieldType2;
  }

  public FieldType[] getResultsFieldTypes()
  {
    return this.resultsFieldTypes;
  }

  public T mapRow(DatabaseResults paramDatabaseResults)
    throws SQLException
  {
    Object localObject1;
    Object localObject2;
    int i;
    int k;
    Object localObject3;
    label43: FieldType localFieldType2;
    if (this.columnPositions == null)
    {
      localObject1 = new HashMap();
      localObject2 = this.tableInfo.createObject();
      i = 0;
      FieldType[] arrayOfFieldType1 = this.resultsFieldTypes;
      int j = arrayOfFieldType1.length;
      k = 0;
      localObject3 = null;
      if (k >= j)
        break label115;
      localFieldType2 = arrayOfFieldType1[k];
      if (!localFieldType2.isForeignCollection())
        break label82;
      i = 1;
    }
    while (true)
    {
      k++;
      break label43;
      localObject1 = this.columnPositions;
      break;
      label82: Object localObject4 = localFieldType2.resultToJava(paramDatabaseResults, (Map)localObject1);
      localFieldType2.assignField(localObject2, localObject4);
      if (localFieldType2 != this.idField)
        continue;
      localObject3 = localObject4;
    }
    label115: if (i != 0)
      for (FieldType localFieldType1 : this.resultsFieldTypes)
      {
        if (!localFieldType1.isForeignCollection())
          continue;
        BaseForeignCollection localBaseForeignCollection = localFieldType1.buildForeignCollection(localObject3, false);
        if (localBaseForeignCollection == null)
          continue;
        localFieldType1.assignField(localObject2, localBaseForeignCollection);
      }
    if (this.columnPositions == null)
      this.columnPositions = ((Map)localObject1);
    return (TT)localObject2;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.mapped.BaseMappedQuery
 * JD-Core Version:    0.6.0
 */