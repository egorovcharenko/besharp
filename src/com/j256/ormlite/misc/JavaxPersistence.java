package com.j256.ormlite.misc;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.field.DatabaseFieldConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class JavaxPersistence
{
  public static DatabaseFieldConfig createFieldConfig(DatabaseType paramDatabaseType, Field paramField)
    throws SQLException
  {
    Annotation[] arrayOfAnnotation = paramField.getAnnotations();
    int i = arrayOfAnnotation.length;
    Object localObject1 = null;
    Object localObject2 = null;
    int j = 0;
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject5 = null;
    Annotation localAnnotation;
    Class localClass;
    Object localObject8;
    Object localObject7;
    Object localObject9;
    Object localObject10;
    Object localObject11;
    if (j < i)
    {
      localAnnotation = arrayOfAnnotation[j];
      localClass = localAnnotation.annotationType();
      if (localClass.getName().equals("javax.persistence.Column"))
      {
        localObject8 = localAnnotation;
        localObject7 = localObject4;
        localObject9 = localObject3;
        localObject10 = localObject1;
        localObject11 = localObject2;
      }
    }
    while (true)
    {
      j++;
      localObject2 = localObject11;
      localObject1 = localObject10;
      localObject5 = localObject8;
      localObject3 = localObject9;
      localObject4 = localObject7;
      break;
      if (localClass.getName().equals("javax.persistence.Id"))
      {
        localObject10 = localObject1;
        localObject7 = localObject4;
        localObject11 = localObject2;
        localObject9 = localAnnotation;
        localObject8 = localObject5;
        continue;
      }
      if (localClass.getName().equals("javax.persistence.GeneratedValue"))
      {
        localObject7 = localAnnotation;
        localObject9 = localObject3;
        localObject10 = localObject1;
        localObject8 = localObject5;
        localObject11 = localObject2;
        continue;
      }
      if (localClass.getName().equals("javax.persistence.OneToOne"))
      {
        localObject7 = localObject4;
        localObject9 = localObject3;
        localObject10 = localObject1;
        localObject11 = localAnnotation;
        localObject8 = localObject5;
        continue;
      }
      if (localClass.getName().equals("javax.persistence.ManyToOne"))
      {
        localObject11 = localObject2;
        localObject7 = localObject4;
        localObject9 = localObject3;
        localObject10 = localAnnotation;
        localObject8 = localObject5;
        continue;
        Object localObject6;
        if ((localObject5 == null) && (localObject3 == null) && (localObject2 == null) && (localObject1 == null))
        {
          localObject6 = null;
          return localObject6;
        }
        DatabaseFieldConfig localDatabaseFieldConfig = new DatabaseFieldConfig();
        String str1 = paramField.getName();
        if (paramDatabaseType.isEntityNamesMustBeUpCase());
        for (String str2 = str1.toUpperCase(); ; str2 = str1)
        {
          localDatabaseFieldConfig.setFieldName(str2);
          if (localObject5 != null);
          while (true)
          {
            try
            {
              String str3 = (String)localObject5.getClass().getMethod("name", new Class[0]).invoke(localObject5, new Object[0]);
              if ((str3 == null) || (str3.length() <= 0))
                continue;
              localDatabaseFieldConfig.setColumnName(str3);
              localDatabaseFieldConfig.setWidth(((Integer)localObject5.getClass().getMethod("length", new Class[0]).invoke(localObject5, new Object[0])).intValue());
              localDatabaseFieldConfig.setCanBeNull(((Boolean)localObject5.getClass().getMethod("nullable", new Class[0]).invoke(localObject5, new Object[0])).booleanValue());
              localDatabaseFieldConfig.setUnique(((Boolean)localObject5.getClass().getMethod("unique", new Class[0]).invoke(localObject5, new Object[0])).booleanValue());
              if (localObject3 == null)
                continue;
              if (localObject4 == null)
              {
                localDatabaseFieldConfig.setId(true);
                if ((localObject2 == null) && (localObject1 == null))
                  break label583;
                bool1 = true;
                localDatabaseFieldConfig.setForeign(bool1);
                localDatabaseFieldConfig.setDataPersister(DataPersisterManager.lookupForField(paramField));
                if ((DatabaseFieldConfig.findGetMethod(paramField, false) == null) || (DatabaseFieldConfig.findSetMethod(paramField, false) == null))
                  break label589;
                bool2 = true;
                localDatabaseFieldConfig.setUseGetSet(bool2);
                localObject6 = localDatabaseFieldConfig;
              }
            }
            catch (Exception localException)
            {
              throw SqlExceptionUtil.create("Problem accessing fields from the Column annotation for field " + paramField, localException);
            }
            localDatabaseFieldConfig.setGeneratedId(true);
            continue;
            label583: boolean bool1 = false;
            continue;
            label589: boolean bool2 = false;
          }
        }
      }
      localObject7 = localObject4;
      localObject8 = localObject5;
      localObject9 = localObject3;
      localObject10 = localObject1;
      localObject11 = localObject2;
    }
  }

  public static String getEntityName(Class<?> paramClass)
  {
    Object localObject1 = null;
    for (Annotation localAnnotation : paramClass.getAnnotations())
    {
      if (!localAnnotation.annotationType().getName().equals("javax.persistence.Entity"))
        continue;
      localObject1 = localAnnotation;
    }
    Object localObject2;
    if (localObject1 == null)
      localObject2 = null;
    while (true)
    {
      return localObject2;
      try
      {
        String str = (String)localObject1.getClass().getMethod("name", new Class[0]).invoke(localObject1, new Object[0]);
        if (str != null)
        {
          int k = str.length();
          if (k > 0)
          {
            localObject2 = str;
            continue;
          }
        }
        localObject2 = null;
      }
      catch (Exception localException)
      {
      }
    }
    throw new IllegalStateException("Could not get entity name from class " + paramClass, localException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.misc.JavaxPersistence
 * JD-Core Version:    0.6.0
 */