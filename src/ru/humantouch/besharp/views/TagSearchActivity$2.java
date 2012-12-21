package ru.humantouch.besharp.views;

import android.text.Editable;
import android.text.TextWatcher;

class TagSearchActivity$2
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    this.this$0.setFilter(paramEditable.toString());
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.TagSearchActivity.2
 * JD-Core Version:    0.6.0
 */