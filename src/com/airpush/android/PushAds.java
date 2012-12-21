package com.airpush.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class PushAds extends Activity
{
  private String a = null;
  private String b = null;
  private String c = null;
  private String d = null;
  private List e = null;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j = null;
  private String k;
  private Context l;
  private boolean m = true;
  private boolean n = false;
  private int o = 17301620;
  private boolean p = true;
  private Intent q;
  private String r;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.l = getApplicationContext();
    this.q = getIntent();
    this.r = this.q.getAction();
    this.f = this.q.getStringExtra("adType");
    if (this.f.equals("ShoWDialog"))
    {
      this.d = this.q.getStringExtra("appId");
      this.j = this.q.getStringExtra("apikey");
      this.n = this.q.getBooleanExtra("test", false);
      this.o = this.q.getIntExtra("icon", 17301620);
    }
    try
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setCancelable(true);
      localBuilder.setMessage("Support the App developer by enabling ads in the notification tray, limited to 1 per day.");
      localBuilder.setPositiveButton("I Agree", new e(this));
      localBuilder.setNegativeButton("No", new f(this));
      localBuilder.create();
      localBuilder.show();
      if (this.r.equals("CC"))
      {
        if (this.f.equals("CC"))
        {
          Log.i("AirpushSDK", "Pushing Ads.....");
          if (this.l.getSharedPreferences("airpushNotificationPref", 1) == null)
            break label585;
          SharedPreferences localSharedPreferences3 = this.l.getSharedPreferences("airpushNotificationPref", 1);
          this.d = localSharedPreferences3.getString("appId", this.q.getStringExtra("appId"));
          this.j = localSharedPreferences3.getString("apikey", this.q.getStringExtra("apikey"));
          this.i = localSharedPreferences3.getString("number", this.q.getStringExtra("number"));
          this.b = localSharedPreferences3.getString("campId", this.q.getStringExtra("campId"));
          this.c = localSharedPreferences3.getString("creativeId", this.q.getStringExtra("creativeId"));
          Log.i("AirpushSDK", "Pushing CC Ads.....");
          startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.i)));
          this.e = SetPreferences.a(this.l);
          this.e.add(new BasicNameValuePair("model", "log"));
          this.e.add(new BasicNameValuePair("action", "settexttracking"));
          this.e.add(new BasicNameValuePair("APIKEY", this.j));
          this.e.add(new BasicNameValuePair("event", "TrayClicked"));
          this.e.add(new BasicNameValuePair("campId", this.b));
          this.e.add(new BasicNameValuePair("creativeId", this.c));
          if (!PushService.a)
            HttpPostData.a(this.e, getApplicationContext());
        }
        return;
      }
    }
    catch (Exception localException)
    {
      label585: 
      do
      {
        do
        {
          while (true)
          {
            Log.i("AirpushSDK", "Error : " + localException.toString());
            continue;
            this.d = this.q.getStringExtra("appId");
            this.j = this.q.getStringExtra("apikey");
            this.b = this.q.getStringExtra("campId");
            this.c = this.q.getStringExtra("creativeId");
            this.i = this.q.getStringExtra("number");
          }
          if (!this.r.equals("CM"))
            break;
        }
        while (!this.f.equals("CM"));
        Log.i("AirpushSDK", "Pushing Ads.....");
        SharedPreferences localSharedPreferences2;
        if (this.l.getSharedPreferences("airpushNotificationPref", 1) != null)
        {
          localSharedPreferences2 = this.l.getSharedPreferences("airpushNotificationPref", 1);
          this.d = localSharedPreferences2.getString("appId", this.q.getStringExtra("appId"));
          this.j = localSharedPreferences2.getString("apikey", this.q.getStringExtra("apikey"));
          this.g = localSharedPreferences2.getString("sms", this.q.getStringExtra("sms"));
          this.b = localSharedPreferences2.getString("campId", this.q.getStringExtra("campId"));
          this.c = localSharedPreferences2.getString("creativeId", this.q.getStringExtra("creativeId"));
        }
        for (this.h = localSharedPreferences2.getString("number", this.q.getStringExtra("number")); ; this.h = this.q.getStringExtra("number"))
        {
          Log.i("AirpushSDK", "Pushing CM Ads.....");
          Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + this.h));
          localIntent.putExtra("sms_body", this.g);
          startActivity(localIntent);
          this.e = SetPreferences.a(this.l);
          this.e.add(new BasicNameValuePair("model", "log"));
          this.e.add(new BasicNameValuePair("action", "settexttracking"));
          this.e.add(new BasicNameValuePair("APIKEY", this.j));
          this.e.add(new BasicNameValuePair("event", "TrayClicked"));
          this.e.add(new BasicNameValuePair("campId", this.b));
          this.e.add(new BasicNameValuePair("creativeId", this.c));
          if (PushService.a)
            break;
          HttpPostData.a(this.e, getApplicationContext());
          break;
          this.d = this.q.getStringExtra("appId");
          this.j = this.q.getStringExtra("apikey");
          this.b = this.q.getStringExtra("campId");
          this.c = this.q.getStringExtra("creativeId");
          this.g = this.q.getStringExtra("sms");
        }
      }
      while ((!this.r.equals("Web And App")) || ((!this.f.equals("W")) && (!this.f.equals("A"))));
      Log.i("AirpushSDK", "Pushing Ads.....");
      if (this.l.getSharedPreferences("airpushNotificationPref", 1) == null)
        break label1571;
    }
    SharedPreferences localSharedPreferences1 = this.l.getSharedPreferences("airpushNotificationPref", 1);
    this.d = localSharedPreferences1.getString("appId", this.q.getStringExtra("appId"));
    this.j = localSharedPreferences1.getString("apikey", this.q.getStringExtra("apikey"));
    this.a = localSharedPreferences1.getString("url", this.q.getStringExtra("url"));
    this.b = localSharedPreferences1.getString("campId", this.q.getStringExtra("campId"));
    this.c = localSharedPreferences1.getString("creativeId", this.q.getStringExtra("creativeId"));
    for (this.k = localSharedPreferences1.getString("header", this.q.getStringExtra("header")); ; this.k = this.q.getStringExtra("header"))
    {
      setTitle(this.k);
      String str = this.a;
      Log.i("AirpushSDK", "Pushing Web and App Ads.....");
      CustomWebView localCustomWebView = new CustomWebView(this);
      localCustomWebView.loadUrl(str);
      setContentView(localCustomWebView);
      this.e = SetPreferences.a(this.l);
      this.e.add(new BasicNameValuePair("model", "log"));
      this.e.add(new BasicNameValuePair("action", "settexttracking"));
      this.e.add(new BasicNameValuePair("APIKEY", this.j));
      this.e.add(new BasicNameValuePair("event", "TrayClicked"));
      this.e.add(new BasicNameValuePair("campId", this.b));
      this.e.add(new BasicNameValuePair("creativeId", this.c));
      if (PushService.a)
        break;
      HttpPostData.a(this.e, getApplicationContext());
      break;
      label1571: this.d = this.q.getStringExtra("appId");
      this.j = this.q.getStringExtra("apikey");
      this.b = this.q.getStringExtra("campId");
      this.c = this.q.getStringExtra("creativeId");
      this.a = this.q.getStringExtra("url");
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      finish();
    for (boolean bool = true; ; bool = super.onKeyDown(paramInt, paramKeyEvent))
      return bool;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.PushAds
 * JD-Core Version:    0.6.0
 */