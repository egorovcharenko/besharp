package com.airpush.android;

import android.app.NotificationManager;
import android.util.Log;

final class g
  implements Runnable
{
  g(PushService paramPushService)
  {
  }

  public final void run()
  {
    try
    {
      Log.i("AirpushSDK", "Notification Expired");
      PushService.a(this.a).cancel(999);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Airpush.a(this.a.getApplicationContext(), 1800000L);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.g
 * JD-Core Version:    0.6.0
 */