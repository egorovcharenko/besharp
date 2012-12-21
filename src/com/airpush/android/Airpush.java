package com.airpush.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class Airpush
{
  protected static Context a;
  private static String b = null;
  private static String c = null;
  private static String d;
  private static boolean e;
  private static int g;
  private static List l;
  private String f;
  private boolean h;
  private long i = 0L;
  private long j = 0L;
  private long k = 0L;
  private Runnable m = new a(this);
  private Runnable n = new b(this);

  static
  {
    a = null;
    d = null;
    e = false;
    g = 17301620;
  }

  public Airpush()
  {
  }

  public Airpush(Context paramContext, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      a = paramContext;
      new SetPreferences().a(a, paramString1, paramString2, e, false, g, true);
      a();
      a(paramContext, paramString1, paramString2, e, false, g, true);
      return;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2Airpush");
      arrayOfStackTraceElement = localException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        List localList = SetPreferences.a(a);
        l = localList;
        localList.add(new BasicNameValuePair("model", "log"));
        l.add(new BasicNameValuePair("action", "sdkerror"));
        l.add(new BasicNameValuePair("APIKEY", d));
        l.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
        HttpPostData.a(l, a);
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  public Airpush(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      e = paramBoolean;
      a = paramContext;
      new SetPreferences().a(a, paramString1, paramString2, paramBoolean, false, g, true);
      a();
      a(paramContext, paramString1, paramString2, e, false, g, true);
      return;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2Airpush");
      arrayOfStackTraceElement = localException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        List localList = SetPreferences.a(a);
        l = localList;
        localList.add(new BasicNameValuePair("model", "log"));
        l.add(new BasicNameValuePair("action", "sdkerror"));
        l.add(new BasicNameValuePair("APIKEY", d));
        l.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
        HttpPostData.a(l, a);
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private static void a()
  {
    int i1 = 0;
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i2;
    try
    {
      if (!a.getSharedPreferences("dataPrefs", 1).equals(null))
      {
        SharedPreferences localSharedPreferences = a.getSharedPreferences("dataPrefs", 1);
        b = localSharedPreferences.getString("appId", "invalid");
        d = localSharedPreferences.getString("apikey", "airpush");
        c = localSharedPreferences.getString("imei", "invalid");
        e = localSharedPreferences.getBoolean("testMode", false);
        g = localSharedPreferences.getInt("icon", 17301620);
      }
      return;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2getDataSharedprefrences");
      arrayOfStackTraceElement = localException.getStackTrace();
      i2 = arrayOfStackTraceElement.length;
    }
    while (true)
    {
      if (i1 >= i2)
      {
        new StringBuilder("web").append(localException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        List localList = SetPreferences.a(a);
        l = localList;
        localList.add(new BasicNameValuePair("model", "log"));
        l.add(new BasicNameValuePair("action", "sdkerror"));
        l.add(new BasicNameValuePair("APIKEY", d));
        l.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
        HttpPostData.a(l, a);
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i1].toString());
      localStringBuilder.append("V3.2\n");
      i1++;
    }
  }

  protected static void a(Context paramContext, long paramLong)
  {
    Log.i("AirpushSDK", "SDK will restart in " + paramLong + " ms.");
    a = paramContext;
    a();
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      Intent localIntent1 = new Intent(paramContext, UserDetailsReceiver.class);
      localIntent1.setAction("SetUserInfo");
      localIntent1.putExtra("appId", b);
      localIntent1.putExtra("imei", c);
      localIntent1.putExtra("apikey", d);
      PendingIntent localPendingIntent1 = PendingIntent.getBroadcast(paramContext, 0, localIntent1, 0);
      ((AlarmManager)paramContext.getSystemService("alarm")).set(0, System.currentTimeMillis() + 60L * (1000L * paramLong), localPendingIntent1);
      Intent localIntent2 = new Intent(paramContext, MessageReceiver.class);
      localIntent2.setAction("SetMessageReceiver");
      localIntent2.putExtra("appId", b);
      localIntent2.putExtra("imei", c);
      localIntent2.putExtra("apikey", d);
      localIntent2.putExtra("testMode", e);
      localIntent2.putExtra("icon", g);
      PendingIntent localPendingIntent2 = PendingIntent.getBroadcast(paramContext, 0, localIntent2, 0);
      ((AlarmManager)paramContext.getSystemService("alarm")).setInexactRepeating(0, paramLong + System.currentTimeMillis() + c.b.intValue(), c.a, localPendingIntent2);
      return;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2RestartSDK");
      arrayOfStackTraceElement = localException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        List localList = SetPreferences.a(a);
        l = localList;
        localList.add(new BasicNameValuePair("model", "log"));
        l.add(new BasicNameValuePair("action", "sdkerror"));
        l.add(new BasicNameValuePair("APIKEY", d));
        l.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
        HttpPostData.a(l, a);
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  protected final void a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, int paramInt, boolean paramBoolean3)
  {
    int i1 = 0;
    try
    {
      this.h = paramBoolean3;
      SharedPreferences.Editor localEditor = a.getSharedPreferences("dialogPref", 2).edit();
      localEditor.putBoolean("ShowDialog", paramBoolean2);
      localEditor.putBoolean("ShowAd", this.h);
      localEditor.commit();
      if (this.h)
      {
        Log.i("AirpushSDK", "Initialising.....");
        e = paramBoolean1;
        b = paramString1;
        d = paramString2;
        g = paramInt;
        c.a();
        this.f = ((TelephonyManager)a.getSystemService("phone")).getDeviceId();
        try
        {
          MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
          localMessageDigest.update(this.f.getBytes(), 0, this.f.length());
          c = new BigInteger(1, localMessageDigest.digest()).toString(16);
          new Handler().postDelayed(this.m, 20000L);
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
          while (true)
            localNoSuchAlgorithmException.printStackTrace();
        }
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2StartAirpush");
      StackTraceElement[] arrayOfStackTraceElement = localException.getStackTrace();
      int i2 = arrayOfStackTraceElement.length;
      while (true)
      {
        if (i1 >= i2)
        {
          new StringBuilder("web").append(localException.toString()).toString();
          c.a();
          localStringBuilder.toString();
          c.a();
          List localList = SetPreferences.a(a);
          l = localList;
          localList.add(new BasicNameValuePair("model", "log"));
          l.add(new BasicNameValuePair("action", "sdkerror"));
          l.add(new BasicNameValuePair("APIKEY", d));
          l.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(l, a);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[i1].toString());
        localStringBuilder.append("V3.2\n");
        i1++;
      }
    }
  }

  public void disableSdk(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("sdkPrefs", 2).edit();
    localEditor.putBoolean("SDKEnabled", false);
    localEditor.commit();
  }

  public void enableSdk(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("sdkPrefs", 2).edit();
    localEditor.putBoolean("SDKEnabled", true);
    localEditor.commit();
  }

  public boolean isEnabled(Context paramContext)
  {
    boolean bool;
    if (!paramContext.getSharedPreferences("sdkPrefs", 1).equals(null))
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("sdkPrefs", 1);
      if (localSharedPreferences.contains("SDKEnabled"))
        bool = localSharedPreferences.getBoolean("SDKEnabled", false);
    }
    while (true)
    {
      return bool;
      bool = true;
      continue;
      bool = true;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.Airpush
 * JD-Core Version:    0.6.0
 */