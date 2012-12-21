package ru.humantouch.besharp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.airpush.android.Airpush;

public class BootReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    new Airpush(paramContext, "12699", "1308685180197191986", false);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.BootReceiver
 * JD-Core Version:    0.6.0
 */