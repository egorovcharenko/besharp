package com.j256.ormlite.field;

import java.lang.reflect.Field;
import java.sql.SQLException;

public abstract interface DataPersister extends FieldConverter
{
  public abstract Object convertIdNumber(Number paramNumber);

  public abstract Object generatedId();

  public abstract Class<?>[] getAssociatedClasses();

  public abstract int getDefaultWidth();

  public abstract boolean isAppropriateId();

  public abstract boolean isComparable();

  public abstract boolean isEscapedDefaultValue();

  public abstract boolean isEscapedValue();

  public abstract boolean isPrimitive();

  public abstract boolean isSelectArgRequired();

  public abstract boolean isSelfGeneratedId();

  public abstract boolean isValidForField(Field paramField);

  public abstract boolean isValidGeneratedType();

  public abstract Object makeConfigObject(FieldType paramFieldType)
    throws SQLException;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.DataPersister
 * JD-Core Version:    0.6.0
 */