package ru.humantouch.besharp.DAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import ru.humantouch.besharp.entities.Line;

public class LineDaoImpl extends BaseDaoImpl<Line, Integer>
{
  public LineDaoImpl(ConnectionSource paramConnectionSource)
    throws android.database.SQLException, java.sql.SQLException
  {
    super(paramConnectionSource, Line.class);
  }

  private void makeSpaceForSortOrderInternal(Line paramLine, int paramInt)
    throws java.sql.SQLException
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    localQueryBuilder.orderBy("sortOrder", true).where().gt("sortOrder", Integer.valueOf(paramInt + paramLine.getSortOrder()));
    Iterator localIterator = query(localQueryBuilder.prepare()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Line localLine = (Line)localIterator.next();
      localLine.setSortOrder(1 + localLine.getSortOrder());
      update(localLine);
    }
  }

  private int updateSortOrderInternal(Line paramLine, int paramInt)
    throws Exception
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    int i = paramInt;
    localQueryBuilder.orderBy("sortOrder", true);
    Iterator localIterator;
    if (paramLine == null)
    {
      localQueryBuilder.where().isNull("parentLine_id").or().eq("parentLine_id", Integer.valueOf(0));
      localIterator = query(localQueryBuilder.prepare()).iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return i;
        localQueryBuilder.where().eq("parentLine_id", paramLine.id);
        break;
      }
      Line localLine = (Line)localIterator.next();
      localLine.setSortOrder(i);
      int j = i + 1;
      update(localLine);
      i = updateSortOrderInternal(localLine, j);
    }
  }

  public void deleteLine(Line paramLine)
    throws Exception
  {
    delete(paramLine);
  }

  public ArrayList<Line> getAllLines()
    throws java.sql.SQLException
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    localQueryBuilder.orderBy("sortOrder", true);
    return (ArrayList)query(localQueryBuilder.prepare());
  }

  public HashMap<Integer, Line> getLinesFromRoot(Line paramLine, HashMap<Integer, Line> paramHashMap)
    throws java.sql.SQLException
  {
    Iterator localIterator2;
    if (paramHashMap == null)
    {
      List localList = queryForAll();
      paramHashMap = new HashMap();
      localIterator2 = localList.iterator();
    }
    Object localObject;
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        if (paramLine != null)
          break;
        localObject = paramHashMap;
        return localObject;
      }
      Line localLine2 = (Line)localIterator2.next();
      paramHashMap.put(localLine2.id, localLine2);
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator1 = paramHashMap.values().iterator();
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        localObject = localHashMap;
        break;
      }
      Line localLine1 = (Line)localIterator1.next();
      if ((localLine1.getParentLine() == null) || (localLine1.getParentLine().id != paramLine.id))
        continue;
      localHashMap.put(localLine1.id, localLine1);
      localHashMap.putAll(getLinesFromRoot(localLine1, paramHashMap));
    }
  }

  public void makeSpaceForSortOrder(Line paramLine)
    throws java.sql.SQLException
  {
    makeSpaceForSortOrderInternal(paramLine, 0);
  }

  public void makeSpaceForSortOrderBefore(Line paramLine)
    throws java.sql.SQLException
  {
    makeSpaceForSortOrderInternal(paramLine, -1);
  }

  public void saveLines(HashMap<Integer, Line> paramHashMap)
    throws Exception
  {
    Iterator localIterator = paramHashMap.values().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Line localLine = (Line)localIterator.next();
      if ((localLine.id != null) && (localLine.id.intValue() != 0))
      {
        if (!localLine.isChanged.booleanValue())
          continue;
        update(localLine);
        continue;
      }
      create(localLine);
    }
  }

  public void updateSortOrder()
    throws Exception
  {
    updateSortOrderInternal(null, 0);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.DAO.LineDaoImpl
 * JD-Core Version:    0.6.0
 */