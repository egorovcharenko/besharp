package com.j256.ormlite.field;

import com.j256.ormlite.field.types.BooleanObjectType;
import com.j256.ormlite.field.types.BooleanType;
import com.j256.ormlite.field.types.ByteArrayType;
import com.j256.ormlite.field.types.ByteObjectType;
import com.j256.ormlite.field.types.ByteType;
import com.j256.ormlite.field.types.CharType;
import com.j256.ormlite.field.types.CharacterObjectType;
import com.j256.ormlite.field.types.DateLongType;
import com.j256.ormlite.field.types.DateStringType;
import com.j256.ormlite.field.types.DateType;
import com.j256.ormlite.field.types.DoubleObjectType;
import com.j256.ormlite.field.types.DoubleType;
import com.j256.ormlite.field.types.EnumIntegerType;
import com.j256.ormlite.field.types.EnumStringType;
import com.j256.ormlite.field.types.FloatObjectType;
import com.j256.ormlite.field.types.FloatType;
import com.j256.ormlite.field.types.IntType;
import com.j256.ormlite.field.types.IntegerObjectType;
import com.j256.ormlite.field.types.LongObjectType;
import com.j256.ormlite.field.types.LongStringType;
import com.j256.ormlite.field.types.LongType;
import com.j256.ormlite.field.types.SerializableType;
import com.j256.ormlite.field.types.ShortObjectType;
import com.j256.ormlite.field.types.ShortType;
import com.j256.ormlite.field.types.StringBytesType;
import com.j256.ormlite.field.types.StringType;
import com.j256.ormlite.field.types.UuidType;

public enum DataType
{
  private final DataPersister dataPersister;

  static
  {
    LONG_STRING = new DataType("LONG_STRING", 1, LongStringType.getSingleton());
    STRING_BYTES = new DataType("STRING_BYTES", 2, StringBytesType.getSingleton());
    BOOLEAN = new DataType("BOOLEAN", 3, BooleanType.getSingleton());
    BOOLEAN_OBJ = new DataType("BOOLEAN_OBJ", 4, BooleanObjectType.getSingleton());
    DATE = new DataType("DATE", 5, DateType.getSingleton());
    JAVA_DATE = new DataType("JAVA_DATE", 6, DATE.dataPersister);
    DATE_LONG = new DataType("DATE_LONG", 7, DateLongType.getSingleton());
    JAVA_DATE_LONG = new DataType("JAVA_DATE_LONG", 8, DATE_LONG.dataPersister);
    DATE_STRING = new DataType("DATE_STRING", 9, DateStringType.getSingleton());
    JAVA_DATE_STRING = new DataType("JAVA_DATE_STRING", 10, DATE_STRING.dataPersister);
    CHAR = new DataType("CHAR", 11, CharType.getSingleton());
    CHAR_OBJ = new DataType("CHAR_OBJ", 12, CharacterObjectType.getSingleton());
    BYTE = new DataType("BYTE", 13, ByteType.getSingleton());
    BYTE_ARRAY = new DataType("BYTE_ARRAY", 14, ByteArrayType.getSingleton());
    BYTE_OBJ = new DataType("BYTE_OBJ", 15, ByteObjectType.getSingleton());
    SHORT = new DataType("SHORT", 16, ShortType.getSingleton());
    SHORT_OBJ = new DataType("SHORT_OBJ", 17, ShortObjectType.getSingleton());
    INTEGER = new DataType("INTEGER", 18, IntType.getSingleton());
    INTEGER_OBJ = new DataType("INTEGER_OBJ", 19, IntegerObjectType.getSingleton());
    LONG = new DataType("LONG", 20, LongType.getSingleton());
    LONG_OBJ = new DataType("LONG_OBJ", 21, LongObjectType.getSingleton());
    FLOAT = new DataType("FLOAT", 22, FloatType.getSingleton());
    FLOAT_OBJ = new DataType("FLOAT_OBJ", 23, FloatObjectType.getSingleton());
    DOUBLE = new DataType("DOUBLE", 24, DoubleType.getSingleton());
    DOUBLE_OBJ = new DataType("DOUBLE_OBJ", 25, DoubleObjectType.getSingleton());
    SERIALIZABLE = new DataType("SERIALIZABLE", 26, SerializableType.getSingleton());
    ENUM_STRING = new DataType("ENUM_STRING", 27, EnumStringType.getSingleton());
    ENUM_INTEGER = new DataType("ENUM_INTEGER", 28, EnumIntegerType.getSingleton());
    UUID = new DataType("UUID", 29, UuidType.getSingleton());
    UNKNOWN = new DataType("UNKNOWN", 30, null);
    DataType[] arrayOfDataType = new DataType[31];
    arrayOfDataType[0] = STRING;
    arrayOfDataType[1] = LONG_STRING;
    arrayOfDataType[2] = STRING_BYTES;
    arrayOfDataType[3] = BOOLEAN;
    arrayOfDataType[4] = BOOLEAN_OBJ;
    arrayOfDataType[5] = DATE;
    arrayOfDataType[6] = JAVA_DATE;
    arrayOfDataType[7] = DATE_LONG;
    arrayOfDataType[8] = JAVA_DATE_LONG;
    arrayOfDataType[9] = DATE_STRING;
    arrayOfDataType[10] = JAVA_DATE_STRING;
    arrayOfDataType[11] = CHAR;
    arrayOfDataType[12] = CHAR_OBJ;
    arrayOfDataType[13] = BYTE;
    arrayOfDataType[14] = BYTE_ARRAY;
    arrayOfDataType[15] = BYTE_OBJ;
    arrayOfDataType[16] = SHORT;
    arrayOfDataType[17] = SHORT_OBJ;
    arrayOfDataType[18] = INTEGER;
    arrayOfDataType[19] = INTEGER_OBJ;
    arrayOfDataType[20] = LONG;
    arrayOfDataType[21] = LONG_OBJ;
    arrayOfDataType[22] = FLOAT;
    arrayOfDataType[23] = FLOAT_OBJ;
    arrayOfDataType[24] = DOUBLE;
    arrayOfDataType[25] = DOUBLE_OBJ;
    arrayOfDataType[26] = SERIALIZABLE;
    arrayOfDataType[27] = ENUM_STRING;
    arrayOfDataType[28] = ENUM_INTEGER;
    arrayOfDataType[29] = UUID;
    arrayOfDataType[30] = UNKNOWN;
    $VALUES = arrayOfDataType;
  }

  private DataType(DataPersister paramDataPersister)
  {
    this.dataPersister = paramDataPersister;
  }

  public DataPersister getDataPersister()
  {
    return this.dataPersister;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.DataType
 * JD-Core Version:    0.6.0
 */