package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EnumStringType extends BaseEnumType
{
  public static int DEFAULT_WIDTH = 100;
  private static final EnumStringType singleTon = new EnumStringType();

  private EnumStringType()
  {
    super(localSqlType, arrayOfClass);
  }

  public static EnumStringType getSingleton()
  {
    return singleTon;
  }

  public int getDefaultWidth()
  {
    return DEFAULT_WIDTH;
  }

  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
  {
    return ((Enum)paramObject).name();
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
      localHashMap.put(localEnum.name(), localEnum);
    }
    return localHashMap;
  }

  public Object parseDefaultString(FieldType paramFieldType, String paramString)
  {
    return paramString;
  }

  public Object resultToJava(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    String str = paramDatabaseResults.getString(paramInt);
    Object localObject;
    if (paramFieldType == null)
      localObject = str;
    while (true)
    {
      return localObject;
      if (str == null)
      {
        localObject = null;
        continue;
      }
      Map localMap = (Map)paramFieldType.getDataTypeConfigObj();
      if (localMap == null)
      {
        localObject = enumVal(paramFieldType, str, null, paramFieldType.getUnknownEnumVal());
        continue;
      }
      localObject = enumVal(paramFieldType, str, (Enum)localMap.get(str), paramFieldType.getUnknownEnumVal());
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.EnumStringType
 * JD-Core Version:    0.6.0
 */