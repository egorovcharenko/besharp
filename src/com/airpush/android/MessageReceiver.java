package com.airpush.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageReceiver extends BroadcastReceiver
{
  protected static Context a;
  private static String c = "Invalid";
  private List b = null;
  private String d = null;
  private boolean e;
  private int f;
  private JSONObject g;
  private String h;
  private String i;
  private Runnable j = new d(this);

  private String a(String paramString)
  {
    try
    {
      this.g = new JSONObject(paramString);
      String str2 = this.g.getString("appid");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "invalid Id";
    }
  }

  private void a()
  {
    try
    {
      if (!a.getSharedPreferences("dataPrefs", 1).equals(null))
      {
        SharedPreferences localSharedPreferences = a.getSharedPreferences("dataPrefs", 1);
        c = localSharedPreferences.getString("appId", "invalid");
        this.d = localSharedPreferences.getString("apikey", "airpush");
        localSharedPreferences.getString("imei", "invalid");
        this.e = localSharedPreferences.getBoolean("testMode", false);
        this.f = localSharedPreferences.getInt("icon", 17301620);
      }
      else
      {
        this.h = a.getPackageName();
        this.i = HttpPostData.a("http://api.airpush.com/model/user/getappinfo.php?packageName=" + this.h, a);
        c = a(this.i);
        this.d = b(this.i);
      }
    }
    catch (Exception localException)
    {
      this.h = a.getPackageName();
      this.i = HttpPostData.a("http://api.airpush.com/model/user/getappinfo.php?packageName=" + this.h, a);
      c = a(this.i);
      this.d = b(this.i);
      new Airpush(a, c, "airpush");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2 MessageReceiver getDataSharedprefrences ");
      StackTraceElement[] arrayOfStackTraceElement = localException.getStackTrace();
      int k = arrayOfStackTraceElement.length;
      for (int m = 0; ; m++)
      {
        if (m >= k)
        {
          this.b = SetPreferences.a(a);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", this.d));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, a);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[m].toString());
        localStringBuilder.append("V3.2\n");
      }
    }
  }

  private String b(String paramString)
  {
    try
    {
      this.g = new JSONObject(paramString);
      String str2 = this.g.getString("authkey");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "invalid key";
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a = paramContext;
    if (SetPreferences.isEnabled(paramContext))
    {
      StringBuilder localStringBuilder;
      StackTraceElement[] arrayOfStackTraceElement;
      int k;
      int m;
      try
      {
        if (!c.a(a))
          return;
        Log.i("AirpushSDK", "Receiving Message.....");
        if (paramIntent.getAction().equals("SetMessageReceiver"))
          a();
        Intent localIntent = new Intent();
        localIntent.setAction("com.airpush.android.PushServiceStart" + c);
        localIntent.putExtra("appId", c);
        localIntent.putExtra("type", "message");
        localIntent.putExtra("apikey", this.d);
        localIntent.putExtra("testMode", this.e);
        localIntent.putExtra("icon", this.f);
        if (!localIntent.equals(null))
        {
          paramContext.startService(localIntent);
          return;
        }
        a();
        if ((c.equals("invalid")) || (c.equals(null)))
          new Handler().postDelayed(this.j, 1800000L);
        new Airpush(a, c, "airpush");
      }
      catch (Exception localException)
      {
        a();
        new Airpush(a, c, "airpush");
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("V3.2 MessageReceiverError ");
        arrayOfStackTraceElement = localException.getStackTrace();
        k = arrayOfStackTraceElement.length;
        m = 0;
      }
      while (true)
      {
        if (m >= k)
        {
          this.b = SetPreferences.a(a);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", this.d));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, a);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[m].toString());
        localStringBuilder.append("V3.2\n");
        m++;
      }
    }
    else
    {
      Log.i("AirpushSDK", "SDK is disabled, please enable to receive Ads !");
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.MessageReceiver
 * JD-Core Version:    0.6.0
 */