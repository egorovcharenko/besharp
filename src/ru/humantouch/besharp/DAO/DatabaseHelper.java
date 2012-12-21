package ru.humantouch.besharp.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import ru.humantouch.besharp.entities.Line;
import ru.humantouch.besharp.entities.LineTagIntersection;
import ru.humantouch.besharp.entities.Tag;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
  private static final String DATABASE_NAME = "besharp.db";
  private static final int DATABASE_VERSION = 4;
  private LineDaoImpl linesDao = null;
  private LineTagInterDaoImpl linesTagInterDao = null;
  private TagDaoImpl tagsDao = null;

  public DatabaseHelper(Context paramContext)
  {
    super(paramContext, "besharp.db", null, 4);
  }

  public void close()
  {
    super.close();
    this.linesDao = null;
    this.tagsDao = null;
    this.linesTagInterDao = null;
  }

  public LineTagInterDaoImpl getLineTagInterDataDao()
    throws SQLException
  {
    if (this.linesTagInterDao == null)
      this.linesTagInterDao = new LineTagInterDaoImpl(getConnectionSource(), this);
    return this.linesTagInterDao;
  }

  public LineDaoImpl getLinesDataDao()
    throws SQLException
  {
    if (this.linesDao == null)
      this.linesDao = new LineDaoImpl(getConnectionSource());
    return this.linesDao;
  }

  public TagDaoImpl getTagDataDao()
    throws SQLException
  {
    if (this.tagsDao == null)
      this.tagsDao = new TagDaoImpl(getConnectionSource());
    return this.tagsDao;
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource)
  {
    try
    {
      Log.i(DatabaseHelper.class.getName(), "onCreate");
      TableUtils.dropTable(paramConnectionSource, Line.class, true);
      TableUtils.createTable(paramConnectionSource, Line.class);
      TableUtils.dropTable(paramConnectionSource, Tag.class, true);
      TableUtils.createTable(paramConnectionSource, Tag.class);
      TableUtils.dropTable(paramConnectionSource, LineTagIntersection.class, true);
      TableUtils.createTable(paramConnectionSource, LineTagIntersection.class);
      return;
    }
    catch (SQLException localSQLException)
    {
      Log.e(DatabaseHelper.class.getName(), "Can't create database", localSQLException);
    }
    throw new RuntimeException(localSQLException);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource, int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 1) && (paramInt2 >= 2));
    try
    {
      paramSQLiteDatabase.execSQL("ALTER TABLE lines ADD COLUMN isStrikedOut BOOLEAN;");
      if ((paramInt1 <= 2) && (paramInt2 >= 3))
      {
        paramSQLiteDatabase.execSQL("ALTER TABLE lines ADD COLUMN textColor INTEGER;");
        paramSQLiteDatabase.execSQL("ALTER TABLE lines ADD COLUMN backgroundColor INTEGER;");
        paramSQLiteDatabase.execSQL("ALTER TABLE lines ADD COLUMN lineIcon TEXT;");
        paramSQLiteDatabase.execSQL("UPDATE lines SET textColor = -1;");
        paramSQLiteDatabase.execSQL("UPDATE lines SET backgroundColor = -16777216;");
      }
      if ((paramInt1 <= 3) && (paramInt2 >= 4))
      {
        paramSQLiteDatabase.execSQL("ALTER TABLE lines ADD COLUMN calendarEventId INTEGER;");
        paramSQLiteDatabase.execSQL("ALTER TABLE lines ADD COLUMN calendarId INTEGER;");
      }
      Log.i(DatabaseHelper.class.getName(), "onUpgrade");
      return;
    }
    catch (Exception localException)
    {
    }
    throw new RuntimeException(localException);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.DAO.DatabaseHelper
 * JD-Core Version:    0.6.0
 */