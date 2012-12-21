package ru.humantouch.besharp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import ru.humantouch.besharp.entities.Line;
import ru.humantouch.besharp.entities.Tag;

public class TagView extends LinearLayout
{
  private Line mLine;
  TagRemoverListener mListener;
  public TextView mNameTextView;
  private ImageButton mRemoveTagButton;
  private Tag mTag;

  public TagView(Context paramContext, TagRemoverListener paramTagRemoverListener, Line paramLine, Tag paramTag)
  {
    super(paramContext);
    this.mLine = paramLine;
    this.mTag = paramTag;
    setOrientation(0);
    this.mListener = paramTagRemoverListener;
    ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2130903047, this, true);
    this.mNameTextView = ((TextView)findViewById(2131099699));
    this.mRemoveTagButton = ((ImageButton)findViewById(2131099700));
    this.mRemoveTagButton.setOnClickListener(new View.OnClickListener(paramContext)
    {
      public void onClick(View paramView)
      {
        try
        {
          TagView.this.mListener.removeTag(TagView.this.mLine, TagView.this.mTag);
          return;
        }
        catch (Exception localException)
        {
          while (true)
          {
            Toast.makeText(this.val$context, localException.toString(), 0).show();
            localException.printStackTrace();
          }
        }
      }
    });
  }

  public static abstract interface TagRemoverListener
  {
    public abstract void removeTag(Line paramLine, Tag paramTag)
      throws Exception;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.TagView
 * JD-Core Version:    0.6.0
 */