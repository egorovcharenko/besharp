package com.j256.ormlite.field;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ForeignCollectionField
{
  public static final int MAX_EAGER_FOREIGN_COLLECTION_LEVEL = 1;

  public abstract String columnName();

  public abstract boolean eager();

  public abstract int maxEagerForeignCollectionLevel();

  public abstract String orderColumnName();
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.ForeignCollectionField
 * JD-Core Version:    0.6.0
 */