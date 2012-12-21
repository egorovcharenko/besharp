package ru.humantouch.besharp.views;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import ru.humantouch.besharp.DAO.TagDaoImpl;

class TagSearchActivity$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    try
    {
      TagSearchActivity.access$2(this.this$0, TagSearchActivity.access$0(this.this$0).createTagOrReturnExisting(TagSearchActivity.access$1(this.this$0).getText().toString()));
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.TagSearchActivity.1
 * JD-Core Version:    0.6.0
 */