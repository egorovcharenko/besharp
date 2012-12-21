package com.airpush.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDetailsReceiver extends BroadcastReceiver
{
  protected static Context a = null;
  private String b = "Invalid";
  private List c = null;
  private String d = "airpush";
  private String e;
  private String f;
  private JSONObject g;

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
        this.b = localSharedPreferences.getString("appId", "invalid");
        this.d = localSharedPreferences.getString("apikey", "airpush");
        localSharedPreferences.getString("imei", "invalid");
        localSharedPreferences.getBoolean("testMode", false);
        localSharedPreferences.getInt("icon", 17301620);
      }
      else
      {
        this.f = a.getPackageName();
        this.e = HttpPostData.a("http://api.airpush.com/model/user/getappinfo.php?packageName=" + this.f, a);
        this.b = a(this.e);
        this.d = b(this.e);
      }
    }
    catch (Exception localException)
    {
      this.f = a.getPackageName();
      this.e = HttpPostData.a("http://api.airpush.com/model/user/getappinfo.php?packageName=" + this.f, a);
      this.b = a(this.e);
      this.d = b(this.e);
      new Airpush(a, this.b, "airpush");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2 UserDetailsReceiver getDataSharedprefrences ");
      StackTraceElement[] arrayOfStackTraceElement = localException.getStackTrace();
      int i = arrayOfStackTraceElement.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          this.c = SetPreferences.a(a);
          this.c.add(new BasicNameValuePair("model", "log"));
          this.c.add(new BasicNameValuePair("action", "sdkerror"));
          this.c.add(new BasicNameValuePair("APIKEY", this.d));
          this.c.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.c, a);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[j].toString());
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
      int i;
      int j;
      try
      {
        if (c.a(a))
        {
          if (paramIntent.getAction().equals("SetUserInfo"))
            a();
          Log.i("AirpushSDK", "Sending User Info....");
          new StringBuilder("airpushAppid ").append(this.b).toString();
          c.a();
          Intent localIntent = new Intent();
          localIntent.setAction("com.airpush.android.PushServiceStart" + this.b);
          localIntent.putExtra("appId", this.b);
          localIntent.putExtra("type", "userInfo");
          localIntent.putExtra("apikey", this.d);
          if (!localIntent.equals(null))
          {
            a.startService(localIntent);
            return;
          }
          a();
          new Airpush(a, this.b, "airpush");
        }
      }
      catch (Exception localException)
      {
        a();
        new Airpush(a, this.b, "airpush");
        Log.i("AirpushSDK", "Sending User Info failed");
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("V3.2 UserDetailsReceiver ");
        arrayOfStackTraceElement = localException.getStackTrace();
        i = arrayOfStackTraceElement.length;
        j = 0;
      }
      while (true)
      {
        if (j >= i)
        {
          this.c = SetPreferences.a(a);
          this.c.add(new BasicNameValuePair("model", "log"));
          this.c.add(new BasicNameValuePair("action", "sdkerror"));
          this.c.add(new BasicNameValuePair("APIKEY", this.d));
          this.c.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.c, a);
          break;
          Airpush.a(a, 1800000L);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[j].toString());
        localStringBuilder.append("V3.2\n");
        j++;
      }
    }
    Log.i("AirpushSDK", "SDK is disabled, please enable to receive Ads !");
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.UserDetailsReceiver
 * JD-Core Version:    0.6.0
 */