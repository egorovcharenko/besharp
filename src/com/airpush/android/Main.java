package com.airpush.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Main extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    new Airpush(this, "10241", "airpush");
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      moveTaskToBack(true);
    for (boolean bool = true; ; bool = super.onKeyDown(paramInt, paramKeyEvent))
      return bool;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.Main
 * JD-Core Version:    0.6.0
 */