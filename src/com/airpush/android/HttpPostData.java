package com.airpush.android;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public final class HttpPostData
{
  protected static long a = 1800000L;
  private static String b;
  private static Context c;

  protected static String a(String paramString, Context paramContext)
  {
    String str1;
    if (c.a(paramContext))
    {
      c = paramContext;
      try
      {
        if (c.a(paramContext))
        {
          HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
          localHttpURLConnection.setRequestMethod("GET");
          localHttpURLConnection.setDoOutput(true);
          localHttpURLConnection.setDoInput(true);
          localHttpURLConnection.setConnectTimeout(3000);
          localHttpURLConnection.connect();
          if (localHttpURLConnection.getResponseCode() == 200)
          {
            StringBuffer localStringBuffer = new StringBuffer();
            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream()));
            while (true)
            {
              String str2 = localBufferedReader.readLine();
              if (str2 == null)
              {
                str1 = localStringBuffer.toString();
                break;
              }
              localStringBuffer.append(str2);
            }
          }
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        Airpush.a(c, 1800000L);
        str1 = "";
      }
      catch (IOException localIOException)
      {
        while (true)
          Airpush.a(c, 1800000L);
      }
      catch (Exception localException)
      {
        while (true)
          Airpush.a(c, 1800000L);
      }
    }
    else
    {
      Airpush.a(paramContext, a);
      str1 = "";
    }
    return str1;
  }

  protected static HttpEntity a(List paramList, Context paramContext)
  {
    if (c.a(paramContext))
      c = paramContext;
    while (true)
    {
      StringBuilder localStringBuilder;
      StackTraceElement[] arrayOfStackTraceElement;
      int i;
      int j;
      try
      {
        HttpPost localHttpPost = new HttpPost("http://api.airpush.com/v2/api.php");
        localHttpPost.setEntity(new UrlEncodedFormEntity(paramList));
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 7000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 7000);
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
        if (!c.a(paramContext))
          break label311;
        localBasicHttpResponse = (BasicHttpResponse)localDefaultHttpClient.execute(localHttpPost);
        HttpEntity localHttpEntity2 = localBasicHttpResponse.getEntity();
        localHttpEntity1 = localHttpEntity2;
        return localHttpEntity1;
      }
      catch (Exception localException)
      {
        Airpush.a(c, 1800000L);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("V3.2postData");
        arrayOfStackTraceElement = localException.getStackTrace();
        i = arrayOfStackTraceElement.length;
        j = 0;
      }
      while (true)
      {
        if (j >= i)
        {
          List localList = SetPreferences.a(c);
          localList.add(new BasicNameValuePair("model", "log"));
          localList.add(new BasicNameValuePair("action", "sdkerror"));
          localList.add(new BasicNameValuePair("APIKEY", "airpush"));
          localList.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          a(localList, c);
          localHttpEntity1 = null;
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[j].toString());
        localStringBuilder.append("V3.2\n");
        j++;
      }
      Airpush.a(paramContext, a);
      HttpEntity localHttpEntity1 = null;
      continue;
      label311: BasicHttpResponse localBasicHttpResponse = null;
    }
  }

  protected static HttpEntity a(List paramList, boolean paramBoolean, Context paramContext)
  {
    if (c.a(paramContext))
    {
      c = paramContext;
      if (!paramBoolean);
    }
    HttpEntity localHttpEntity;
    while (true)
    {
      StringBuilder localStringBuilder;
      StackTraceElement[] arrayOfStackTraceElement;
      int i;
      int j;
      try
      {
        b = "http://api.airpush.com/testmsg2.php";
        HttpPost localHttpPost = new HttpPost(b);
        localHttpPost.setEntity(new UrlEncodedFormEntity(paramList));
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 3000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 3000);
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
        if (!c.a(paramContext))
          break label355;
        localBasicHttpResponse = (BasicHttpResponse)localDefaultHttpClient.execute(localHttpPost);
        localHttpEntity = localBasicHttpResponse.getEntity();
        break;
        b = "http://api.airpush.com/v2/api.php";
        continue;
      }
      catch (Exception localException)
      {
        Airpush.a(c, 1800000L);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("V3.2postData3");
        arrayOfStackTraceElement = localException.getStackTrace();
        i = arrayOfStackTraceElement.length;
        j = 0;
      }
      while (true)
      {
        if (j >= i)
        {
          List localList = SetPreferences.a(c);
          localList.add(new BasicNameValuePair("model", "log"));
          localList.add(new BasicNameValuePair("action", "sdkerror"));
          localList.add(new BasicNameValuePair("APIKEY", "airpush"));
          localList.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          a(localList, c);
          Log.i("AirpushSDK", "Message Fetching Error : " + localException.toString());
          localHttpEntity = null;
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[j].toString());
        localStringBuilder.append("V3.2\n");
        j++;
      }
      Airpush.a(paramContext, a);
      localHttpEntity = null;
      break;
      label355: BasicHttpResponse localBasicHttpResponse = null;
    }
    return localHttpEntity;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.HttpPostData
 * JD-Core Version:    0.6.0
 */