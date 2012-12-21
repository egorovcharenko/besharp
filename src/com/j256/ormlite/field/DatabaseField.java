package com.j256.ormlite.field;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface DatabaseField
{
  public static final int MAX_FOREIGN_AUTO_REFRESH_LEVEL = 2;
  public static final String NO_DEFAULT = "__ormlite__ no default value string was specified";

  public abstract boolean canBeNull();

  public abstract String columnName();

  public abstract DataType dataType();

  public abstract String defaultValue();

  public abstract boolean foreign();

  public abstract boolean foreignAutoRefresh();

  public abstract String format();

  public abstract boolean generatedId();

  public abstract String generatedIdSequence();

  public abstract boolean id();

  public abstract boolean index();

  public abstract String indexName();

  public abstract int maxForeignAutoRefreshLevel();

  @Deprecated
  public abstract int maxForeignLevel();

  public abstract boolean persisted();

  public abstract Class<? extends DataPersister> persisterClass();

  public abstract boolean throwIfNull();

  public abstract boolean unique();

  public abstract boolean uniqueCombo();

  public abstract boolean uniqueIndex();

  public abstract String uniqueIndexName();

  public abstract String unknownEnumName();

  public abstract boolean useGetSet();

  public abstract int width();
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.DatabaseField
 * JD-Core Version:    0.6.0
 */