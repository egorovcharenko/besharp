package com.j256.ormlite.field;

import com.j256.ormlite.field.types.EnumStringType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataPersisterManager
{
  private static final DataPersister DEFAULT_ENUM_PERSISTER = EnumStringType.getSingleton();
  private static Map<Class<?>, DataPersister> builtInMap = null;
  private static List<DataPersister> registeredPersisters = null;

  public static void clear()
  {
    registeredPersisters = null;
  }

  public static DataPersister lookupForField(Field paramField)
  {
    Object localObject;
    if (registeredPersisters != null)
    {
      Iterator localIterator = registeredPersisters.iterator();
      while (localIterator.hasNext())
      {
        DataPersister localDataPersister3 = (DataPersister)localIterator.next();
        if (!localDataPersister3.isValidForField(paramField))
          continue;
        localObject = localDataPersister3;
      }
    }
    while (true)
    {
      return localObject;
      if (builtInMap == null)
      {
        HashMap localHashMap = new HashMap();
        DataType[] arrayOfDataType = DataType.values();
        int i = arrayOfDataType.length;
        for (int j = 0; j < i; j++)
        {
          DataPersister localDataPersister2 = arrayOfDataType[j].getDataPersister();
          if (localDataPersister2 == null)
            continue;
          Class[] arrayOfClass = localDataPersister2.getAssociatedClasses();
          int k = arrayOfClass.length;
          for (int m = 0; m < k; m++)
            localHashMap.put(arrayOfClass[m], localDataPersister2);
        }
        builtInMap = localHashMap;
      }
      DataPersister localDataPersister1 = (DataPersister)builtInMap.get(paramField.getType());
      if (localDataPersister1 != null)
      {
        localObject = localDataPersister1;
        continue;
      }
      if (paramField.getType().isEnum())
      {
        localObject = DEFAULT_ENUM_PERSISTER;
        continue;
      }
      localObject = null;
    }
  }

  public static void registerDataPersisters(DataPersister[] paramArrayOfDataPersister)
  {
    ArrayList localArrayList = new ArrayList();
    if (registeredPersisters != null)
      localArrayList.addAll(registeredPersisters);
    int i = paramArrayOfDataPersister.length;
    for (int j = 0; j < i; j++)
      localArrayList.add(paramArrayOfDataPersister[j]);
    registeredPersisters = localArrayList;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.DataPersisterManager
 * JD-Core Version:    0.6.0
 */