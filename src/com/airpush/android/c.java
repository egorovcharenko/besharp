package com.airpush.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.util.Log;

public final class c
{
  protected static long a;
  protected static final Integer b;
  protected static final Integer c;

  static
  {
    Integer.valueOf(20000);
    a = 14400000L;
    b = Integer.valueOf(20000);
    SystemClock.elapsedRealtime();
    c = Integer.valueOf(240);
  }

  protected static void a()
  {
  }

  protected static boolean a(Context paramContext)
  {
    int i;
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isAvailable()) && (localConnectivityManager.getActiveNetworkInfo().isConnected()))
      {
        i = 1;
      }
      else
      {
        Log.i("AirpushSDK", "Internet Connection Not Found");
        Log.i("AirpushSDK", "Internet Error: SDK will retry after " + HttpPostData.a + " ms");
        i = 0;
      }
    }
    catch (Exception localException)
    {
      i = 0;
    }
    return i;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.c
 * JD-Core Version:    0.6.0
 */