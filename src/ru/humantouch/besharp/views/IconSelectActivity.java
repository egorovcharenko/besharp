package ru.humantouch.besharp.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.HashMap;
import ru.humantouch.besharp.GlobalState;

public class IconSelectActivity extends Activity
{
  HashMap<String, Integer> mIcons;

  private void returnIconName(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("RETURN_ARGUMENT_ICON_NAME", paramString);
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
      setContentView(2130903043);
      this.mIcons = ((GlobalState)getApplication()).mIcons;
      GridView localGridView = (GridView)findViewById(2131099657);
      localGridView.setAdapter(new ImageAdapter(this));
      localGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          IconSelectActivity.this.returnIconName((String)IconSelectActivity.this.mIcons.keySet().toArray()[paramInt]);
        }
      });
      ((Button)findViewById(2131099659)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent();
          IconSelectActivity.this.setResult(0, localIntent);
          IconSelectActivity.this.finish();
        }
      });
      ((Button)findViewById(2131099658)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          IconSelectActivity.this.returnIconName(null);
        }
      });
      label100: return;
    }
    catch (Exception localException)
    {
      break label100;
    }
  }

  public class ImageAdapter extends BaseAdapter
  {
    private Context mContext;

    public ImageAdapter(Context arg2)
    {
      Object localObject;
      this.mContext = localObject;
    }

    public int getCount()
    {
      return IconSelectActivity.this.mIcons.size();
    }

    public Object getItem(int paramInt)
    {
      return (String)IconSelectActivity.this.mIcons.keySet().toArray()[paramInt];
    }

    public long getItemId(int paramInt)
    {
      return ((Integer)IconSelectActivity.this.mIcons.values().toArray()[paramInt]).intValue();
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ImageView localImageView;
      if (paramView == null)
      {
        localImageView = new ImageView(this.mContext);
        localImageView.setLayoutParams(new AbsListView.LayoutParams(85, 85));
        localImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        localImageView.setPadding(2, 2, 2, 2);
      }
      while (true)
      {
        localImageView.setImageResource(((Integer)IconSelectActivity.this.mIcons.values().toArray()[paramInt]).intValue());
        return localImageView;
        localImageView = (ImageView)paramView;
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.IconSelectActivity
 * JD-Core Version:    0.6.0
 */