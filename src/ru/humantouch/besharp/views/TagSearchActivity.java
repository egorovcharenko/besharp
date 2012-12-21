package ru.humantouch.besharp.views;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import ru.humantouch.besharp.DAO.DatabaseHelper;
import ru.humantouch.besharp.DAO.TagDaoImpl;
import ru.humantouch.besharp.GlobalState;
import ru.humantouch.besharp.entities.Tag;

public class TagSearchActivity extends ListActivity
{
  private List<Tag> allTags;
  private HashMap<Integer, Integer> idsMap;
  private EditText mEditText;
  private TagDaoImpl mTagDao;

  private void returnTagId(int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("RETURN_ARGUMENT_TAG_ID", paramInt);
    Intent localIntent = new Intent();
    localIntent.putExtras(localBundle);
    setResult(-1, localIntent);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    try
    {
      super.onCreate(paramBundle);
      setContentView(2130903048);
      this.mTagDao = ((GlobalState)getApplication()).getDbHelper().getTagDataDao();
      this.allTags = this.mTagDao.query(this.mTagDao.queryBuilder().orderBy("name", true).prepare());
      setFilter("");
      this.mEditText = ((EditText)findViewById(2131099703));
      ((Button)findViewById(2131099704)).setOnClickListener(new TagSearchActivity.1(this));
      this.mEditText.addTextChangedListener(new TagSearchActivity.2(this));
      this.mEditText.setOnKeyListener(new TagSearchActivity.3(this));
      this.mEditText.requestFocus();
      return;
    }
    catch (SQLException localSQLException)
    {
      while (true)
        localSQLException.printStackTrace();
    }
  }

  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    returnTagId(((Integer)this.idsMap.get(Integer.valueOf(paramInt))).intValue());
  }

  protected void setFilter(String paramString)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "tag_name";
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 2131099705;
    this.idsMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.allTags.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        setListAdapter(new SimpleAdapter(this, localArrayList, 2130903049, arrayOfString, arrayOfInt));
        return;
      }
      Tag localTag = (Tag)localIterator.next();
      if ((!localTag.name.toLowerCase().contains(paramString.toLowerCase())) && (paramString.length() != 0))
        continue;
      HashMap localHashMap = new HashMap();
      localHashMap.put("tag_name", localTag.name);
      this.idsMap.put(Integer.valueOf(localArrayList.size()), Integer.valueOf(localTag.id));
      localArrayList.add(localHashMap);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.TagSearchActivity
 * JD-Core Version:    0.6.0
 */