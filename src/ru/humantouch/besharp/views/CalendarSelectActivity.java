package ru.humantouch.besharp.views;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import ru.humantouch.besharp.entities.Event;

public class CalendarSelectActivity extends ListActivity
{
  private HashMap<Integer, String> mMap;

  private void returnCalendarId(int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("RETURN_ARGUMENT_CALENDAR_ID", paramInt);
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
      setContentView(2130903042);
      this.mMap = Event.getAllCalendars(getContentResolver());
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(this.mMap.values());
      ((Button)findViewById(2131099655)).setOnClickListener(new CalendarSelectActivity.1(this));
      setListAdapter(new ArrayAdapter(this, 17367043, localArrayList));
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle("Please select calendar for new events").setMessage("To create events in calendar, you need to choose, which calendar will hold your events. You can change this later in options").setCancelable(false).setNegativeButton("Ok", new CalendarSelectActivity.2(this));
      localBuilder.create().show();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    returnCalendarId(((Integer)this.mMap.keySet().toArray()[paramInt]).intValue());
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.CalendarSelectActivity
 * JD-Core Version:    0.6.0
 */