package ru.humantouch.besharp.DAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ru.humantouch.besharp.entities.Line;
import ru.humantouch.besharp.entities.LineTagIntersection;
import ru.humantouch.besharp.entities.Tag;

public class LineTagInterDaoImpl extends BaseDaoImpl<LineTagIntersection, Integer>
{
  private DatabaseHelper mDbHelper;

  public LineTagInterDaoImpl(ConnectionSource paramConnectionSource, DatabaseHelper paramDatabaseHelper)
    throws android.database.SQLException, java.sql.SQLException
  {
    super(paramConnectionSource, LineTagIntersection.class);
    this.mDbHelper = paramDatabaseHelper;
  }

  public void addTagToLine(Integer paramInteger1, Integer paramInteger2)
    throws Exception
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    if (paramInteger1 == null)
      throw new Exception("Line must be saved to database first. (Line.id is null)");
    localQueryBuilder.where().eq("line_id", paramInteger1).and().eq("tag_id", paramInteger2);
    if (query(localQueryBuilder.prepare()).size() == 0)
      create(new LineTagIntersection(paramInteger1.intValue(), paramInteger2.intValue()));
  }

  public void addTagToLine(Line paramLine, Tag paramTag)
    throws Exception
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    if (paramLine.id == null)
      throw new Exception("Line must be saved to database first. (Line.id is null)");
    localQueryBuilder.where().eq("line_id", paramLine.id).and().eq("tag_id", Integer.valueOf(paramTag.id));
    if (query(localQueryBuilder.prepare()).size() == 0)
    {
      LineTagIntersection localLineTagIntersection = new LineTagIntersection();
      localLineTagIntersection.line = paramLine;
      localLineTagIntersection.tag = paramTag;
      create(localLineTagIntersection);
    }
  }

  public ArrayList<Tag> getTagsFromLine(Line paramLine)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    QueryBuilder localQueryBuilder = queryBuilder();
    localQueryBuilder.where().eq("line_id", paramLine.id);
    Iterator localIterator = query(localQueryBuilder.prepare()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      LineTagIntersection localLineTagIntersection = (LineTagIntersection)localIterator.next();
      localArrayList.add((Tag)this.mDbHelper.getTagDataDao().queryForId(Integer.valueOf(localLineTagIntersection.tag.id)));
    }
  }

  public Boolean hasLineTag(Line paramLine, Tag paramTag)
    throws Exception
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    localQueryBuilder.where().eq("line_id", paramLine.id).and().eq("tag_id", Integer.valueOf(paramTag.id));
    if (query(localQueryBuilder.prepare()).size() != 0);
    for (Boolean localBoolean = Boolean.valueOf(true); ; localBoolean = Boolean.valueOf(false))
      return localBoolean;
  }

  public void removeAllTagsFromLine(Line paramLine)
    throws Exception
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    localQueryBuilder.where().eq("line_id", paramLine.id);
    List localList = query(localQueryBuilder.prepare());
    if (localList.size() != 0)
      delete(localList);
  }

  public void removeTagFromLine(Line paramLine, Tag paramTag)
    throws Exception
  {
    QueryBuilder localQueryBuilder = queryBuilder();
    localQueryBuilder.where().eq("line_id", paramLine.id).and().eq("tag_id", Integer.valueOf(paramTag.id));
    List localList = query(localQueryBuilder.prepare());
    if (localList.size() == 0)
      throw new Exception("No such tag added to this line");
    delete(localList);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.DAO.LineTagInterDaoImpl
 * JD-Core Version:    0.6.0
 */