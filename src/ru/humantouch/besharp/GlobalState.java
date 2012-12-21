package ru.humantouch.besharp;

import android.app.Application;
import java.util.HashMap;
import ru.humantouch.besharp.DAO.DatabaseHelper;

public class GlobalState extends Application
{
  private DatabaseHelper dbHelper;
  public HashMap<String, Integer> mIcons;

  public DatabaseHelper getDbHelper()
  {
    return this.dbHelper;
  }

  public void setDbHelper(DatabaseHelper paramDatabaseHelper)
  {
    this.dbHelper = paramDatabaseHelper;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.GlobalState
 * JD-Core Version:    0.6.0
 */