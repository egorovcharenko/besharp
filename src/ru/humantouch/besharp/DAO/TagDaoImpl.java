package ru.humantouch.besharp.DAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.util.Iterator;
import java.util.List;
import ru.humantouch.besharp.entities.Tag;

public class TagDaoImpl extends BaseDaoImpl<Tag, Integer>
{
  public TagDaoImpl(ConnectionSource paramConnectionSource)
    throws android.database.SQLException, java.sql.SQLException
  {
    super(paramConnectionSource, Tag.class);
  }

  public int createTagOrReturnExisting(String paramString)
    throws Exception
  {
    Iterator localIterator = queryForAll().iterator();
    Tag localTag1;
    if (!localIterator.hasNext())
    {
      localTag1 = new Tag();
      localTag1.name = paramString;
      create(localTag1);
    }
    Tag localTag2;
    for (int i = localTag1.id; ; i = localTag2.id)
    {
      return i;
      localTag2 = (Tag)localIterator.next();
      if (!localTag2.name.toLowerCase().equals(paramString.toLowerCase()))
        break;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.DAO.TagDaoImpl
 * JD-Core Version:    0.6.0
 */