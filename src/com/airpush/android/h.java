package com.airpush.android;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public final class h
  implements LocationListener
{
  public h(SetPreferences paramSetPreferences)
  {
  }

  public final void onLocationChanged(Location paramLocation)
  {
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i;
    int j;
    try
    {
      SetPreferences.a(String.valueOf(paramLocation.getLongitude()));
      SetPreferences.b(String.valueOf(paramLocation.getLatitude()));
      return;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2 MyLocationListener");
      arrayOfStackTraceElement = localException.getStackTrace();
      i = arrayOfStackTraceElement.length;
      j = 0;
    }
    while (true)
    {
      if (j >= i)
      {
        ArrayList localArrayList = new ArrayList();
        SetPreferences.a = localArrayList;
        localArrayList.add(new BasicNameValuePair("model", "log"));
        SetPreferences.a.add(new BasicNameValuePair("appId", SetPreferences.a()));
        SetPreferences.a.add(new BasicNameValuePair("action", "sdkerror"));
        SetPreferences.a.add(new BasicNameValuePair("APIKEY", "airpush"));
        SetPreferences.a.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
        HttpPostData.a(SetPreferences.a, SetPreferences.b());
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[j].toString());
      localStringBuilder.append("V3.2\n");
      j++;
    }
  }

  public final void onProviderDisabled(String paramString)
  {
  }

  public final void onProviderEnabled(String paramString)
  {
  }

  public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.h
 * JD-Core Version:    0.6.0
 */