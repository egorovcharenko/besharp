package com.airpush.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class DeliveryReceiver extends BroadcastReceiver
{
  protected static Context a = null;
  private List b = null;
  private String c = null;
  private String d = null;
  private String e = null;
  private String f = null;
  private String g = null;
  private String h = null;
  private String i;
  private Long j;
  private String k;
  private String l;
  private String m;
  private String n;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a = paramContext;
    try
    {
      localIntent = new Intent();
      if (c.a(a))
      {
        Log.i("AirpushSDK", "Delivering Message");
        if (paramIntent.getAction().equals("setDeliveryReceiverPhone"))
        {
          this.h = paramIntent.getStringExtra("apikey");
          this.c = new String(paramIntent.getStringExtra("appId"));
          paramIntent.getStringExtra("imei");
          this.l = new String(paramIntent.getStringExtra("number"));
          this.k = new String(paramIntent.getStringExtra("title"));
          this.d = new String(paramIntent.getStringExtra("text"));
          this.i = new String(paramIntent.getStringExtra("imageurl"));
          this.j = Long.valueOf(paramIntent.getLongExtra("expiry_time", 60L));
          this.f = paramIntent.getStringExtra("campId");
          this.g = paramIntent.getStringExtra("creativeId");
          localIntent.setAction("com.airpush.android.PushServiceStart" + this.c);
          localIntent.putExtra("adType", "CC");
          localIntent.putExtra("appId", this.c);
          localIntent.putExtra("type", "delivery");
          localIntent.putExtra("number", this.l);
          localIntent.putExtra("title", this.k);
          localIntent.putExtra("text", this.d);
          localIntent.putExtra("apikey", this.h);
          localIntent.putExtra("imageurl", this.i);
          localIntent.putExtra("expiry_time", this.j);
        }
        while (true)
        {
          paramContext.startService(localIntent);
          return;
          if (!paramIntent.getAction().equals("setDeliveryReceiverSMS"))
            break;
          this.h = paramIntent.getStringExtra("apikey");
          this.c = new String(paramIntent.getStringExtra("appId"));
          paramIntent.getStringExtra("imei");
          this.l = new String(paramIntent.getStringExtra("number"));
          this.m = new String(paramIntent.getStringExtra("sms"));
          this.k = new String(paramIntent.getStringExtra("title"));
          this.d = new String(paramIntent.getStringExtra("text"));
          this.i = new String(paramIntent.getStringExtra("imageurl"));
          this.j = Long.valueOf(paramIntent.getLongExtra("expiry_time", 60L));
          this.f = paramIntent.getStringExtra("campId");
          this.g = paramIntent.getStringExtra("creativeId");
          localIntent.setAction("com.airpush.android.PushServiceStart" + this.c);
          localIntent.putExtra("adType", "CM");
          localIntent.putExtra("appId", this.c);
          localIntent.putExtra("type", "delivery");
          localIntent.putExtra("number", this.l);
          localIntent.putExtra("title", this.k);
          localIntent.putExtra("text", this.d);
          localIntent.putExtra("sms", this.m);
          localIntent.putExtra("apikey", this.h);
          localIntent.putExtra("imageurl", this.i);
          localIntent.putExtra("expiry_time", this.j);
          localIntent.putExtra("campId", this.f);
          localIntent.putExtra("creativeId", this.g);
        }
      }
    }
    catch (Exception localException)
    {
      Intent localIntent;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2DeliveryReceiver");
      StackTraceElement[] arrayOfStackTraceElement = localException.getStackTrace();
      int i1 = arrayOfStackTraceElement.length;
      for (int i2 = 0; ; i2++)
      {
        if (i2 >= i1)
        {
          new StringBuilder("web").append(localException.toString()).toString();
          c.a();
          localStringBuilder.toString();
          c.a();
          this.b = SetPreferences.a(a);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", this.h));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, a);
          Log.e("AirpushSDK", "Delivering Message Failed");
          return;
          if (paramIntent.getAction().equals("setDeliveryReceiverWEB"))
          {
            this.h = paramIntent.getStringExtra("apikey");
            this.c = new String(paramIntent.getStringExtra("appId"));
            paramIntent.getStringExtra("imei");
            this.e = new String(paramIntent.getStringExtra("url"));
            this.k = new String(paramIntent.getStringExtra("title"));
            this.d = new String(paramIntent.getStringExtra("text"));
            this.i = new String(paramIntent.getStringExtra("imageurl"));
            this.n = new String(paramIntent.getStringExtra("header"));
            this.j = Long.valueOf(paramIntent.getLongExtra("expiry_time", 60L));
            this.f = paramIntent.getStringExtra("campId");
            this.g = paramIntent.getStringExtra("creativeId");
            localIntent.setAction("com.airpush.android.PushServiceStart" + this.c);
            localIntent.putExtra("adType", "W");
            localIntent.putExtra("appId", this.c);
            localIntent.putExtra("type", "delivery");
            localIntent.putExtra("link", this.e);
            localIntent.putExtra("header", this.n);
            localIntent.putExtra("title", this.k);
            localIntent.putExtra("text", this.d);
            localIntent.putExtra("apikey", this.h);
            localIntent.putExtra("imageurl", this.i);
            localIntent.putExtra("expiry_time", this.j);
            localIntent.putExtra("campId", this.f);
            localIntent.putExtra("creativeId", this.g);
            break;
          }
          if (!paramIntent.getAction().equals("setDeliveryReceiverMARKET"))
            break;
          this.h = paramIntent.getStringExtra("apikey");
          this.c = new String(paramIntent.getStringExtra("appId"));
          paramIntent.getStringExtra("imei");
          this.e = new String(paramIntent.getStringExtra("url"));
          this.k = new String(paramIntent.getStringExtra("title"));
          this.d = new String(paramIntent.getStringExtra("text"));
          this.i = new String(paramIntent.getStringExtra("imageurl"));
          this.j = Long.valueOf(paramIntent.getLongExtra("expiry_time", 60L));
          this.f = paramIntent.getStringExtra("campId");
          this.g = paramIntent.getStringExtra("creativeId");
          localIntent.setAction("com.airpush.android.PushServiceStart" + this.c);
          localIntent.putExtra("adType", "A");
          localIntent.putExtra("appId", this.c);
          localIntent.putExtra("type", "delivery");
          localIntent.putExtra("link", this.e);
          localIntent.putExtra("title", this.k);
          localIntent.putExtra("text", this.d);
          localIntent.putExtra("apikey", this.h);
          localIntent.putExtra("imageurl", this.i);
          localIntent.putExtra("expiry_time", this.j);
          localIntent.putExtra("campId", this.f);
          localIntent.putExtra("creativeId", this.g);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
        localStringBuilder.append("V3.2\n");
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.DeliveryReceiver
 * JD-Core Version:    0.6.0
 */