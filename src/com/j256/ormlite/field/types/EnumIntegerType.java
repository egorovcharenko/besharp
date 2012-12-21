package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EnumIntegerType extends BaseEnumType
{
  private static final EnumIntegerType singleTon = new EnumIntegerType();

  private EnumIntegerType()
  {
    super(SqlType.INTEGER, new Class[0]);
  }

  public static EnumIntegerType getSingleton()
  {
    return singleTon;
  }

  public boolean isEscapedValue()
  {
    return false;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
  {
    return Integer.valueOf(((Enum)paramObject).ordinal());
  }

  public Object makeConfigObject(FieldType paramFieldType)
    throws SQLException
  {
    HashMap localHashMap = new HashMap();
    Enum[] arrayOfEnum = (Enum[])(Enum[])paramFieldType.getFieldType().getEnumConstants();
    if (arrayOfEnum == null)
      throw new SQLException("Field " + paramFieldType + " improperly configured as type " + this);
    int i = arrayOfEnum.length;
    for (int j = 0; j < i; j++)
    {
      Enum localEnum = arrayOfEnum[j];
      localHashMap.put(Integer.valueOf(localEnum.ordinal()), localEnum);
    }
    return localHashMap;
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
  {
    return Integer.valueOf(Integer.parseInt(paramString));
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    int i = paramDatabaseResults.getInt(paramInt);
    Object localObject;
    if (paramFieldType == null)
      localObject = Integer.valueOf(i);
    while (true)
    {
      return localObject;
      Integer localInteger = Integer.valueOf(i);
      Map localMap = (Map)paramFieldType.getDataTypeConfigObj();
      if (localMap == null)
      {
        localObject = enumVal(paramFieldType, localInteger, null, paramFieldType.getUnknownEnumVal());
        continue;
      }
      localObject = enumVal(paramFieldType, localInteger, (Enum)localMap.get(localInteger), paramFieldType.getUnknownEnumVal());
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.EnumIntegerType
 * JD-Core Version:    0.6.0
 */