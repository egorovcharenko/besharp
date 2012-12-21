package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.lang.reflect.Field;
import java.sql.SQLException;

public abstract class BaseEnumType extends BaseDataType
{
  protected BaseEnumType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }

  protected static Enum<?> enumVal(FieldType paramFieldType, Object paramObject, Enum<?> paramEnum1, Enum<?> paramEnum2)
    throws SQLException
  {
    if (paramEnum1 != null);
    for (Enum<?> localEnum = paramEnum1; ; localEnum = paramEnum2)
    {
      return localEnum;
      if (paramEnum2 != null)
        continue;
      throw new SQLException("Cannot get enum value of '" + paramObject + "' for field " + paramFieldType);
    }
  }

  public boolean isValidForField(Field paramField)
  {
    return paramField.getType().isEnum();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.types.BaseEnumType
 * JD-Core Version:    0.6.0
 */