package com.airpush.android;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class PushService extends Service
{
  private static int A;
  protected static boolean a;
  private static String c = null;
  private static String d = null;
  private static String g = null;
  private static Context n = null;
  private static String q = null;
  private Runnable B = new g(this);
  private List b = null;
  private String e = null;
  private String f = null;
  private String h = null;
  private String i = null;
  private String j = null;
  private String k = null;
  private String l = null;
  private String m = "http://api.airpush.com/redirect.php?market=";
  private String o;
  private long p;
  private NotificationManager r;
  private String s = null;
  private String t = null;
  private String u;
  private Long v;
  private String w;
  private long x;
  private String y;
  private String z;

  static
  {
    a = false;
    A = 17301620;
  }

  private static long a(String paramString1, String paramString2)
  {
    long l1 = 0L;
    try
    {
      Date localDate1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(paramString1);
      Date localDate2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(paramString2);
      long l2 = localDate1.getTime();
      long l3 = localDate2.getTime();
      l1 = l2 - l3;
      return l1;
    }
    catch (ParseException localParseException)
    {
      while (true)
      {
        Airpush.a(n, 1800000L);
        Log.e("AirpushSDK", "Date Diff .....Failed");
      }
    }
  }

  private void a()
  {
    Log.i("AirpushSDK", "Receiving.......");
    try
    {
      this.b = SetPreferences.a(n);
      this.b.add(new BasicNameValuePair("model", "message"));
      this.b.add(new BasicNameValuePair("action", "getmessage"));
      this.b.add(new BasicNameValuePair("APIKEY", q));
      c.a();
      this.t = null;
      InputStream localInputStream = HttpPostData.a(this.b, a, n).getContent();
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        int i3 = localInputStream.read();
        if (i3 == -1)
        {
          this.t = localStringBuffer.toString();
          a(this.t);
          break;
        }
        localStringBuffer.append((char)i3);
      }
    }
    catch (Exception localException)
    {
      new StringBuilder("json").append(localException.toString()).toString();
      c.a();
      new StringBuilder("Message ").append(this.t).toString();
      c.a();
      Airpush.a(n, 1800000L);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2startreciever");
      StackTraceElement[] arrayOfStackTraceElement = localException.getStackTrace();
      int i1 = arrayOfStackTraceElement.length;
      for (int i2 = 0; ; i2++)
      {
        if (i2 >= i1)
        {
          this.b = SetPreferences.a(n);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", q));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, n);
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
        localStringBuilder.append("V3.2\n");
      }
    }
  }

  private static void a(long paramLong)
  {
    try
    {
      if (!n.getSharedPreferences("dataPrefs", 1).equals(null))
      {
        SharedPreferences localSharedPreferences = n.getSharedPreferences("dataPrefs", 1);
        d = localSharedPreferences.getString("appId", "invalid");
        q = localSharedPreferences.getString("apikey", "airpush");
        c = localSharedPreferences.getString("imei", "invalid");
        a = localSharedPreferences.getBoolean("testMode", false);
        A = localSharedPreferences.getInt("icon", 17301620);
      }
      try
      {
        label101: Intent localIntent = new Intent(n, MessageReceiver.class);
        localIntent.setAction("SetMessageReceiver");
        localIntent.putExtra("appId", d);
        localIntent.putExtra("apikey", q);
        localIntent.putExtra("imei", c);
        localIntent.putExtra("testMode", a);
        PendingIntent localPendingIntent = PendingIntent.getBroadcast(n, 0, localIntent, 0);
        ((AlarmManager)n.getSystemService("alarm")).setInexactRepeating(0, paramLong + System.currentTimeMillis(), c.a, localPendingIntent);
        return;
      }
      catch (Exception localException2)
      {
        while (true)
          Airpush.a(n, paramLong);
      }
    }
    catch (Exception localException1)
    {
      break label101;
    }
  }

  // ERROR //
  private void a(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   7: pop
    //   8: invokestatic 167	com/airpush/android/c:a	()V
    //   11: aload_0
    //   12: getstatic 317	com/airpush/android/c:a	J
    //   15: invokestatic 328	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   18: putfield 330	com/airpush/android/PushService:v	Ljava/lang/Long;
    //   21: aload_1
    //   22: ldc_w 332
    //   25: invokevirtual 336	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   28: istore 5
    //   30: iload 5
    //   32: ifeq +121 -> 153
    //   35: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   38: pop
    //   39: invokestatic 167	com/airpush/android/c:a	()V
    //   42: new 338	org/json/JSONObject
    //   45: dup
    //   46: aload_1
    //   47: invokespecial 339	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   50: astore 40
    //   52: aload_0
    //   53: aload 40
    //   55: invokestatic 342	com/airpush/android/PushService:d	(Lorg/json/JSONObject;)Ljava/lang/String;
    //   58: putfield 344	com/airpush/android/PushService:o	Ljava/lang/String;
    //   61: aload_0
    //   62: getfield 344	com/airpush/android/PushService:o	Ljava/lang/String;
    //   65: ldc 252
    //   67: invokevirtual 345	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   70: ifne +73 -> 143
    //   73: aload_0
    //   74: getfield 344	com/airpush/android/PushService:o	Ljava/lang/String;
    //   77: ldc_w 347
    //   80: invokevirtual 345	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   83: ifne +16 -> 99
    //   86: aload_0
    //   87: getfield 344	com/airpush/android/PushService:o	Ljava/lang/String;
    //   90: ldc_w 348
    //   93: invokevirtual 345	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   96: ifeq +9 -> 105
    //   99: aload_0
    //   100: aload 40
    //   102: invokespecial 351	com/airpush/android/PushService:a	(Lorg/json/JSONObject;)V
    //   105: aload_0
    //   106: getfield 344	com/airpush/android/PushService:o	Ljava/lang/String;
    //   109: ldc_w 353
    //   112: invokevirtual 345	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   115: ifeq +9 -> 124
    //   118: aload_0
    //   119: aload 40
    //   121: invokespecial 355	com/airpush/android/PushService:b	(Lorg/json/JSONObject;)V
    //   124: aload_0
    //   125: getfield 344	com/airpush/android/PushService:o	Ljava/lang/String;
    //   128: ldc_w 357
    //   131: invokevirtual 345	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   134: ifeq +9 -> 143
    //   137: aload_0
    //   138: aload 40
    //   140: invokespecial 359	com/airpush/android/PushService:c	(Lorg/json/JSONObject;)V
    //   143: aload_0
    //   144: getfield 330	com/airpush/android/PushService:v	Ljava/lang/Long;
    //   147: invokevirtual 362	java/lang/Long:longValue	()J
    //   150: invokestatic 364	com/airpush/android/PushService:a	(J)V
    //   153: aload_0
    //   154: monitorexit
    //   155: return
    //   156: astore 24
    //   158: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   161: pop
    //   162: new 199	java/lang/StringBuilder
    //   165: dup
    //   166: ldc 201
    //   168: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: aload 24
    //   173: invokevirtual 365	org/json/JSONException:toString	()Ljava/lang/String;
    //   176: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: pop
    //   183: invokestatic 167	com/airpush/android/c:a	()V
    //   186: new 199	java/lang/StringBuilder
    //   189: dup
    //   190: invokespecial 210	java/lang/StringBuilder:<init>	()V
    //   193: astore 27
    //   195: aload 27
    //   197: ldc 212
    //   199: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: pop
    //   203: aload 24
    //   205: invokevirtual 366	org/json/JSONException:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   208: astore 29
    //   210: aload 29
    //   212: arraylength
    //   213: istore 30
    //   215: iload_2
    //   216: iload 30
    //   218: if_icmplt +178 -> 396
    //   221: aload_0
    //   222: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   225: invokestatic 142	com/airpush/android/SetPreferences:a	(Landroid/content/Context;)Ljava/util/List;
    //   228: putfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   231: aload_0
    //   232: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   235: new 144	org/apache/http/message/BasicNameValuePair
    //   238: dup
    //   239: ldc 146
    //   241: ldc 218
    //   243: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   246: invokeinterface 157 2 0
    //   251: pop
    //   252: aload_0
    //   253: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   256: new 144	org/apache/http/message/BasicNameValuePair
    //   259: dup
    //   260: ldc 159
    //   262: ldc 220
    //   264: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   267: invokeinterface 157 2 0
    //   272: pop
    //   273: aload_0
    //   274: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   277: new 144	org/apache/http/message/BasicNameValuePair
    //   280: dup
    //   281: ldc 163
    //   283: getstatic 53	com/airpush/android/PushService:q	Ljava/lang/String;
    //   286: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   289: invokeinterface 157 2 0
    //   294: pop
    //   295: aload_0
    //   296: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   299: new 144	org/apache/http/message/BasicNameValuePair
    //   302: dup
    //   303: ldc 148
    //   305: new 199	java/lang/StringBuilder
    //   308: dup
    //   309: aload 24
    //   311: invokevirtual 365	org/json/JSONException:toString	()Ljava/lang/String;
    //   314: invokestatic 226	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   317: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   320: aload 27
    //   322: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   331: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   334: invokeinterface 157 2 0
    //   339: pop
    //   340: aload_0
    //   341: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   344: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   347: invokestatic 229	com/airpush/android/HttpPostData:a	(Ljava/util/List;Landroid/content/Context;)Lorg/apache/http/HttpEntity;
    //   350: pop
    //   351: ldc 121
    //   353: new 199	java/lang/StringBuilder
    //   356: dup
    //   357: ldc_w 368
    //   360: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   363: aload 24
    //   365: invokevirtual 365	org/json/JSONException:toString	()Ljava/lang/String;
    //   368: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   374: invokestatic 128	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   377: pop
    //   378: aload_0
    //   379: getfield 330	com/airpush/android/PushService:v	Ljava/lang/Long;
    //   382: invokevirtual 362	java/lang/Long:longValue	()J
    //   385: invokestatic 364	com/airpush/android/PushService:a	(J)V
    //   388: goto -235 -> 153
    //   391: astore_3
    //   392: aload_0
    //   393: monitorexit
    //   394: aload_3
    //   395: athrow
    //   396: aload 27
    //   398: aload 29
    //   400: iload_2
    //   401: aaload
    //   402: invokevirtual 232	java/lang/StackTraceElement:toString	()Ljava/lang/String;
    //   405: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: pop
    //   409: aload 27
    //   411: ldc 234
    //   413: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: pop
    //   417: iinc 2 1
    //   420: goto -205 -> 215
    //   423: astore 7
    //   425: new 199	java/lang/StringBuilder
    //   428: dup
    //   429: invokespecial 210	java/lang/StringBuilder:<init>	()V
    //   432: astore 8
    //   434: aload 8
    //   436: ldc_w 370
    //   439: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: pop
    //   443: aload 7
    //   445: invokevirtual 216	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   448: astore 10
    //   450: aload 10
    //   452: arraylength
    //   453: istore 11
    //   455: iload_2
    //   456: iload 11
    //   458: if_icmplt +190 -> 648
    //   461: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   464: pop
    //   465: new 199	java/lang/StringBuilder
    //   468: dup
    //   469: ldc_w 372
    //   472: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   475: aload 7
    //   477: invokevirtual 203	java/lang/Exception:toString	()Ljava/lang/String;
    //   480: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   486: pop
    //   487: invokestatic 167	com/airpush/android/c:a	()V
    //   490: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   493: pop
    //   494: aload 8
    //   496: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   499: pop
    //   500: invokestatic 167	com/airpush/android/c:a	()V
    //   503: aload_0
    //   504: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   507: invokestatic 142	com/airpush/android/SetPreferences:a	(Landroid/content/Context;)Ljava/util/List;
    //   510: putfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   513: aload_0
    //   514: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   517: new 144	org/apache/http/message/BasicNameValuePair
    //   520: dup
    //   521: ldc 146
    //   523: ldc 218
    //   525: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   528: invokeinterface 157 2 0
    //   533: pop
    //   534: aload_0
    //   535: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   538: new 144	org/apache/http/message/BasicNameValuePair
    //   541: dup
    //   542: ldc 159
    //   544: ldc 220
    //   546: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   549: invokeinterface 157 2 0
    //   554: pop
    //   555: aload_0
    //   556: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   559: new 144	org/apache/http/message/BasicNameValuePair
    //   562: dup
    //   563: ldc 163
    //   565: getstatic 53	com/airpush/android/PushService:q	Ljava/lang/String;
    //   568: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   571: invokeinterface 157 2 0
    //   576: pop
    //   577: aload_0
    //   578: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   581: new 144	org/apache/http/message/BasicNameValuePair
    //   584: dup
    //   585: ldc 148
    //   587: aload_1
    //   588: invokespecial 151	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   591: invokeinterface 157 2 0
    //   596: pop
    //   597: aload_0
    //   598: getfield 63	com/airpush/android/PushService:b	Ljava/util/List;
    //   601: getstatic 51	com/airpush/android/PushService:n	Landroid/content/Context;
    //   604: invokestatic 229	com/airpush/android/HttpPostData:a	(Ljava/util/List;Landroid/content/Context;)Lorg/apache/http/HttpEntity;
    //   607: pop
    //   608: ldc 121
    //   610: new 199	java/lang/StringBuilder
    //   613: dup
    //   614: ldc_w 374
    //   617: invokespecial 202	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   620: aload 7
    //   622: invokevirtual 203	java/lang/Exception:toString	()Ljava/lang/String;
    //   625: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   631: invokestatic 128	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   634: pop
    //   635: aload_0
    //   636: getfield 330	com/airpush/android/PushService:v	Ljava/lang/Long;
    //   639: invokevirtual 362	java/lang/Long:longValue	()J
    //   642: invokestatic 364	com/airpush/android/PushService:a	(J)V
    //   645: goto -492 -> 153
    //   648: aload 8
    //   650: aload 10
    //   652: iload_2
    //   653: aaload
    //   654: invokevirtual 232	java/lang/StackTraceElement:toString	()Ljava/lang/String;
    //   657: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   660: pop
    //   661: aload 8
    //   663: ldc 234
    //   665: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: pop
    //   669: iinc 2 1
    //   672: goto -217 -> 455
    //   675: astore 6
    //   677: aload_0
    //   678: getfield 330	com/airpush/android/PushService:v	Ljava/lang/Long;
    //   681: invokevirtual 362	java/lang/Long:longValue	()J
    //   684: invokestatic 364	com/airpush/android/PushService:a	(J)V
    //   687: aload 6
    //   689: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   35	143	156	org/json/JSONException
    //   4	30	391	finally
    //   143	153	391	finally
    //   378	388	391	finally
    //   635	645	391	finally
    //   677	690	391	finally
    //   35	143	423	java/lang/Exception
    //   35	143	675	finally
    //   158	378	675	finally
    //   396	635	675	finally
    //   648	669	675	finally
  }

  private void a(JSONObject paramJSONObject)
  {
    try
    {
      this.s = e(paramJSONObject);
      this.e = f(paramJSONObject);
      this.f = g(paramJSONObject);
      this.h = k(paramJSONObject);
      this.z = p(paramJSONObject);
      this.i = j(paramJSONObject);
      if ((!this.h.equals(null)) && (!this.h.equals("")) && (!this.i.equals(null)) && (!this.i.equals("")) && (!this.f.equals(null)) && (!this.f.equals("nothing")))
      {
        this.v = Long.valueOf(l(paramJSONObject));
        if (this.v.longValue() == 0L)
          this.v = Long.valueOf(c.a);
        this.w = m(paramJSONObject);
        this.p = n(paramJSONObject).longValue();
        o(paramJSONObject);
        if ((this.w.equals(null)) || (this.w.equals("0")))
          break label313;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = localSimpleDateFormat.format(new Date());
        this.w.toString();
        c.a();
        c.a();
        this.x = a(this.w.toString(), str);
        this.v = Long.valueOf(this.v.longValue() + this.x);
      }
      while (true)
      {
        b();
        return;
        label313: if (!this.w.equals("0"))
          continue;
        this.x = 0L;
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2WEB");
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
          this.b = SetPreferences.a(n);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", q));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, n);
          Log.e("AirpushSDK", "Web and App Message Parsing.....Failed ");
          a(this.v.longValue());
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
        localStringBuilder.append("V3.2\n");
      }
    }
    finally
    {
      a(this.v.longValue());
    }
    throw localObject;
  }

  private void b()
  {
    while (true)
    {
      StringBuilder localStringBuilder;
      StackTraceElement[] arrayOfStackTraceElement;
      int i2;
      try
      {
        if ((!this.o.equals("W")) && (!this.o.equals("A")))
          continue;
        if (!this.o.equals("A"))
          continue;
        this.f = (this.m + this.f);
        this.j = "settexttracking";
        this.k = "trayDelivered";
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", this.j));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("event", this.k));
        this.b.add(new BasicNameValuePair("campId", this.h));
        this.b.add(new BasicNameValuePair("creativeId", this.i));
        if (a)
          continue;
        HttpPostData.a(this.b, n);
        this.r = ((NotificationManager)n.getSystemService("notification"));
        String str1 = this.e;
        String str2 = this.s;
        String str3 = this.e;
        long l1 = System.currentTimeMillis();
        Notification localNotification1 = new Notification(A, str1, l1);
        localNotification1.ledARGB = -65536;
        localNotification1.ledOffMS = 300;
        localNotification1.ledOnMS = 300;
        Intent localIntent1 = new Intent(n, PushAds.class);
        localIntent1.addFlags(268435456);
        localIntent1.setAction("Web And App");
        SharedPreferences.Editor localEditor1 = n.getSharedPreferences("airpushNotificationPref", 2).edit();
        localEditor1.putString("appId", d);
        localEditor1.putString("apikey", q);
        localEditor1.putString("url", this.f);
        localEditor1.putString("adType", this.o);
        localEditor1.putString("tray", "trayClicked");
        localEditor1.putString("campId", this.h);
        localEditor1.putString("creativeId", this.i);
        localEditor1.putString("header", this.z);
        localEditor1.commit();
        localIntent1.putExtra("appId", d);
        localIntent1.putExtra("apikey", q);
        localIntent1.putExtra("adType", this.o);
        localIntent1.putExtra("url", this.f);
        localIntent1.putExtra("campId", this.h);
        localIntent1.putExtra("creativeId", this.i);
        localIntent1.putExtra("tray", "trayClicked");
        localIntent1.putExtra("header", this.z);
        PendingIntent localPendingIntent1 = PendingIntent.getActivity(n.getApplicationContext(), 0, localIntent1, 268435456);
        localNotification1.defaults = (0x4 | localNotification1.defaults);
        localNotification1.flags = (0x10 | localNotification1.flags);
        localNotification1.setLatestEventInfo(n, str2, str3, localPendingIntent1);
        localNotification1.contentIntent = localPendingIntent1;
        this.r.notify(999, localNotification1);
        new Handler().postDelayed(this.B, 1000L * this.p);
        Log.i("AirpushSDK", "Message Delivered");
        if (!this.o.equals("CM"))
          continue;
        this.j = "settexttracking";
        this.k = "trayDelivered";
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", this.j));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("event", this.k));
        this.b.add(new BasicNameValuePair("campId", this.h));
        this.b.add(new BasicNameValuePair("creativeId", this.i));
        if (a)
          continue;
        HttpPostData.a(this.b, n);
        this.r = ((NotificationManager)n.getSystemService("notification"));
        String str7 = this.e;
        String str8 = this.s;
        String str9 = this.e;
        long l3 = System.currentTimeMillis();
        Notification localNotification3 = new Notification(A, str7, l3);
        localNotification3.defaults = -1;
        localNotification3.ledARGB = -65536;
        localNotification3.ledOffMS = 300;
        localNotification3.ledOnMS = 300;
        Intent localIntent3 = new Intent(n, PushAds.class);
        localIntent3.addFlags(268435456);
        localIntent3.setAction("CM");
        SharedPreferences.Editor localEditor3 = n.getSharedPreferences("airpushNotificationPref", 2).edit();
        localEditor3.putString("appId", d);
        localEditor3.putString("apikey", q);
        localEditor3.putString("sms", this.y);
        localEditor3.putString("number", this.u);
        localEditor3.putString("adType", this.o);
        localEditor3.putString("tray", "trayClicked");
        localEditor3.putString("campId", this.h);
        localEditor3.putString("creativeId", this.i);
        localEditor3.commit();
        localIntent3.putExtra("appId", d);
        localIntent3.putExtra("apikey", q);
        localIntent3.putExtra("sms", this.y);
        localIntent3.putExtra("number", this.u);
        localIntent3.putExtra("adType", this.o);
        localIntent3.putExtra("tray", "trayClicked");
        localIntent3.putExtra("campId", this.h);
        localIntent3.putExtra("creativeId", this.i);
        PendingIntent localPendingIntent3 = PendingIntent.getActivity(n.getApplicationContext(), 0, localIntent3, 268435456);
        localNotification3.defaults = (0x4 | localNotification3.defaults);
        localNotification3.flags = (0x10 | localNotification3.flags);
        localNotification3.setLatestEventInfo(n, str8, str9, localPendingIntent3);
        localNotification3.contentIntent = localPendingIntent3;
        this.r.notify(999, localNotification3);
        new Handler().postDelayed(this.B, 1000L * this.p);
        Log.i("AirpushSDK", "Notification Delivered");
        if (!this.o.equals("CC"))
          continue;
        this.j = "settexttracking";
        this.k = "trayDelivered";
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", this.j));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("event", this.k));
        this.b.add(new BasicNameValuePair("campId", this.h));
        this.b.add(new BasicNameValuePair("creativeId", this.i));
        if (a)
          continue;
        HttpPostData.a(this.b, n);
        this.r = ((NotificationManager)n.getSystemService("notification"));
        String str4 = this.e;
        String str5 = this.s;
        String str6 = this.e;
        long l2 = System.currentTimeMillis();
        Notification localNotification2 = new Notification(A, str4, l2);
        localNotification2.defaults = -1;
        localNotification2.ledARGB = -65536;
        localNotification2.ledOffMS = 300;
        localNotification2.ledOnMS = 300;
        Intent localIntent2 = new Intent(n, PushAds.class);
        localIntent2.addFlags(268435456);
        localIntent2.setAction("CC");
        SharedPreferences.Editor localEditor2 = n.getSharedPreferences("airpushNotificationPref", 2).edit();
        localEditor2.putString("appId", d);
        localEditor2.putString("apikey", q);
        localEditor2.putString("number", this.u);
        localEditor2.putString("adType", this.o);
        localEditor2.putString("tray", "trayClicked");
        localEditor2.putString("campId", this.h);
        localEditor2.putString("creativeId", this.i);
        localEditor2.commit();
        localIntent2.putExtra("appId", d);
        localIntent2.putExtra("apikey", q);
        localIntent2.putExtra("number", this.u);
        localIntent2.putExtra("adType", this.o);
        localIntent2.putExtra("tray", "trayClicked");
        localIntent2.putExtra("campId", this.h);
        localIntent2.putExtra("creativeId", this.i);
        PendingIntent localPendingIntent2 = PendingIntent.getActivity(n.getApplicationContext(), 0, localIntent2, 268435456);
        localNotification2.defaults = (0x4 | localNotification2.defaults);
        localNotification2.flags = (0x10 | localNotification2.flags);
        localNotification2.setLatestEventInfo(n, str5, str6, localPendingIntent2);
        localNotification2.contentIntent = localPendingIntent2;
        this.r.notify(999, localNotification2);
        new Handler().postDelayed(this.B, 1000L * this.p);
        Log.i("AirpushSDK", "Notification Delivered");
        return;
        if ((!this.o.equals("W")) || (!this.f.contains("?")))
          continue;
        this.f = (this.m + this.f + "&" + d);
        continue;
      }
      catch (Exception localException)
      {
        Airpush.a(n, 1800000L);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("V3.2DeliverNotification");
        arrayOfStackTraceElement = localException.getStackTrace();
        int i1 = arrayOfStackTraceElement.length;
        i2 = 0;
        if (i2 >= i1)
        {
          new StringBuilder("web").append(localException.toString()).toString();
          c.a();
          localStringBuilder.toString();
          c.a();
          this.b = SetPreferences.a(n);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", q));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, n);
          Log.i("AirpushSDK", "Message Delivered");
          new Handler().postDelayed(this.B, 1000L * this.p);
          continue;
          if ((!this.o.equals("W")) || (this.f.contains("?")))
            continue;
          this.f = (this.m + this.f + "?" + d);
          continue;
        }
      }
      finally
      {
        new Handler().postDelayed(this.B, 1000L * this.p);
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private void b(JSONObject paramJSONObject)
  {
    try
    {
      this.s = e(paramJSONObject);
      this.e = f(paramJSONObject);
      this.u = h(paramJSONObject);
      this.h = k(paramJSONObject);
      this.i = j(paramJSONObject);
      if ((!this.h.equals(null)) && (!this.h.equals("")) && (!this.i.equals(null)) && (!this.i.equals("")))
      {
        this.v = Long.valueOf(l(paramJSONObject));
        if (this.v.longValue() == 0L)
          this.v = Long.valueOf(c.a);
        this.w = m(paramJSONObject);
        this.p = n(paramJSONObject).longValue();
        o(paramJSONObject);
        if ((this.w.equals(null)) || (this.w.equals("0")))
          break label305;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = localSimpleDateFormat.format(new Date());
        this.w.toString();
        c.a();
        c.a();
        this.x = a(this.w.toString(), str);
        this.v = Long.valueOf(this.v.longValue() + this.x);
      }
      while (true)
      {
        if ((!this.u.equals(null)) && (!this.u.equals("0")))
          b();
        return;
        label305: if (!this.w.equals("0"))
          continue;
        this.x = 0L;
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2C2Call");
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
          this.b = SetPreferences.a(n);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", q));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, n);
          Log.e("AirpushSDK", "Click to Call Message Parsing.....Failed ");
          a(this.v.longValue());
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
        localStringBuilder.append("V3.2\n");
      }
    }
    finally
    {
      a(this.v.longValue());
    }
    throw localObject;
  }

  private void c(JSONObject paramJSONObject)
  {
    try
    {
      this.s = e(paramJSONObject);
      this.e = f(paramJSONObject);
      this.u = h(paramJSONObject);
      this.y = i(paramJSONObject);
      this.h = k(paramJSONObject);
      this.i = j(paramJSONObject);
      if ((!this.h.equals(null)) && (!this.h.equals("")) && (!this.i.equals(null)) && (!this.i.equals("")))
      {
        this.v = Long.valueOf(l(paramJSONObject));
        if (this.v.longValue() == 0L)
          this.v = Long.valueOf(c.a);
        this.w = m(paramJSONObject);
        this.p = n(paramJSONObject).longValue();
        o(paramJSONObject);
        if ((this.w.equals(null)) || (this.w.equals("0")))
          break label314;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = localSimpleDateFormat.format(new Date());
        this.w.toString();
        c.a();
        c.a();
        this.x = a(this.w.toString(), str);
        this.v = Long.valueOf(this.v.longValue() + this.x);
      }
      while (true)
      {
        if ((!this.u.equals(null)) && (!this.u.equals("0")))
          b();
        return;
        label314: if (!this.w.equals("0"))
          continue;
        this.x = 0L;
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2C2SMS");
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
          this.b = SetPreferences.a(n);
          this.b.add(new BasicNameValuePair("model", "log"));
          this.b.add(new BasicNameValuePair("action", "sdkerror"));
          this.b.add(new BasicNameValuePair("APIKEY", q));
          this.b.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
          HttpPostData.a(this.b, n);
          Log.e("AirpushSDK", "Click to SMS Message Parsing.....Failed ");
          a(this.v.longValue());
          break;
        }
        localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
        localStringBuilder.append("V3.2\n");
      }
    }
    finally
    {
      a(this.v.longValue());
    }
    throw localObject;
  }

  private static String d(JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("adtype");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "invalid";
    }
  }

  private static String e(JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("title");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "New Message";
    }
  }

  private static String f(JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("text");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "Click here for details!";
    }
  }

  private String g(JSONObject paramJSONObject)
  {
    String str1;
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      String str2 = paramJSONObject.getString("url");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2getUrl");
      arrayOfStackTraceElement = localJSONException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localJSONException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("message", localJSONException.toString() + localStringBuilder.toString()));
        HttpPostData.a(this.b, n);
        str1 = "nothing";
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private String h(JSONObject paramJSONObject)
  {
    String str1;
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      String str2 = paramJSONObject.getString("number");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2getNumber");
      arrayOfStackTraceElement = localJSONException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localJSONException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("message", localJSONException.toString() + localStringBuilder.toString()));
        HttpPostData.a(this.b, n);
        str1 = "0";
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private String i(JSONObject paramJSONObject)
  {
    String str1;
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      String str2 = paramJSONObject.getString("sms");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2getSms");
      arrayOfStackTraceElement = localJSONException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localJSONException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("message", localJSONException.toString() + localStringBuilder.toString()));
        HttpPostData.a(this.b, n);
        str1 = "";
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private String j(JSONObject paramJSONObject)
  {
    String str1;
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      String str2 = paramJSONObject.getString("creativeid");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2getCreativeid");
      arrayOfStackTraceElement = localJSONException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localJSONException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("message", localJSONException.toString() + localStringBuilder.toString()));
        HttpPostData.a(this.b, n);
        str1 = "";
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private String k(JSONObject paramJSONObject)
  {
    String str1;
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      String str2 = paramJSONObject.getString("campaignid");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2getCampaignid");
      arrayOfStackTraceElement = localJSONException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        new StringBuilder("web").append(localJSONException.toString()).toString();
        c.a();
        localStringBuilder.toString();
        c.a();
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("message", localJSONException.toString() + localStringBuilder.toString()));
        HttpPostData.a(this.b, n);
        str1 = "";
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private static long l(JSONObject paramJSONObject)
  {
    try
    {
      Long localLong = Long.valueOf(1000L * Long.parseLong(paramJSONObject.get("nextmessagecheck").toString()));
      l1 = localLong.longValue();
      return l1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        long l1 = c.a;
    }
  }

  private static String m(JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("delivery_time");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "0";
    }
  }

  private static Long n(JSONObject paramJSONObject)
  {
    try
    {
      Long localLong2 = Long.valueOf(paramJSONObject.getLong("expirytime"));
      localLong1 = localLong2;
      return localLong1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        Long localLong1 = Long.valueOf(Long.parseLong("86400000"));
    }
  }

  private static String o(JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("adimage");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "http://beta.airpush.com/images/adsthumbnail/48.png";
    }
  }

  private static String p(JSONObject paramJSONObject)
  {
    try
    {
      String str2 = paramJSONObject.getString("header");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "Advertisment";
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onDestroy()
  {
    super.onDestroy();
    Log.i("AirpushSDK", "Service Finished");
  }

  public void onLowMemory()
  {
    super.onLowMemory();
    Log.i("AirpushSDK", "Low On Memory");
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    Integer localInteger = Integer.valueOf(paramInt);
    while (true)
    {
      StringBuilder localStringBuilder1;
      StackTraceElement[] arrayOfStackTraceElement1;
      int i2;
      try
      {
        d = paramIntent.getStringExtra("appId");
        g = paramIntent.getStringExtra("type");
        q = paramIntent.getStringExtra("apikey");
        if (!g.equals("userInfo"))
          continue;
        Context localContext2 = UserDetailsReceiver.a;
        n = localContext2;
        if (localContext2.getSharedPreferences("dataPrefs", 1).equals(null))
          continue;
        c = n.getSharedPreferences("dataPrefs", 1).getString("imei", "invalid");
        String str = q;
        int i3;
        try
        {
          this.b = SetPreferences.a(n);
          this.b.add(new BasicNameValuePair("model", "user"));
          this.b.add(new BasicNameValuePair("action", "setuserinfo"));
          this.b.add(new BasicNameValuePair("APIKEY", str));
          this.b.add(new BasicNameValuePair("type", "app"));
          InputStream localInputStream = HttpPostData.a(this.b, n).getContent();
          StringBuffer localStringBuffer = new StringBuffer();
          int i5 = localInputStream.read();
          if (i5 != -1)
            continue;
          localStringBuffer.toString();
          Log.i("AirpushSDK", "User Info Sent.");
          if (localInteger == null)
            continue;
          stopSelf(paramInt);
          return;
          char c1 = (char)i5;
          localStringBuffer.append(c1);
          continue;
        }
        catch (Exception localException2)
        {
          Log.e("AirpushSDK", "User Info Sending Failed.....");
          Airpush.a(n, 1800000L);
          localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("V3.2userinfo");
          arrayOfStackTraceElement2 = localException2.getStackTrace();
          i3 = arrayOfStackTraceElement2.length;
          i4 = 0;
        }
        if (i4 < i3)
          continue;
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", str));
        this.b.add(new BasicNameValuePair("message", localException2.toString() + localStringBuilder2.toString()));
        HttpPostData.a(this.b, n);
        continue;
      }
      catch (Exception localException1)
      {
        StringBuilder localStringBuilder2;
        StackTraceElement[] arrayOfStackTraceElement2;
        int i4;
        new Airpush(getApplicationContext(), d, "airpush");
        Log.e("AirpushSDK", "Service Error");
        localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("V3.2ServiceError");
        arrayOfStackTraceElement1 = localException1.getStackTrace();
        int i1 = arrayOfStackTraceElement1.length;
        i2 = 0;
        if (i2 < i1)
          break label1354;
        this.b = SetPreferences.a(n);
        this.b.add(new BasicNameValuePair("model", "log"));
        this.b.add(new BasicNameValuePair("action", "sdkerror"));
        this.b.add(new BasicNameValuePair("APIKEY", q));
        this.b.add(new BasicNameValuePair("message", localException1.toString() + localStringBuilder1.toString()));
        HttpPostData.a(this.b, n);
        new StringBuilder("web").append(localException1.toString()).toString();
        c.a();
        localStringBuilder1.toString();
        c.a();
        if (localInteger == null)
          continue;
        stopSelf(paramInt);
        continue;
        localStringBuilder2.append(arrayOfStackTraceElement2[i4].toString());
        localStringBuilder2.append("V3.2\n");
        i4++;
        continue;
        if (g.equals("message"))
        {
          Context localContext1 = MessageReceiver.a;
          n = localContext1;
          if (localContext1.getSharedPreferences("dataPrefs", 1).equals(null))
            continue;
          c = n.getSharedPreferences("dataPrefs", 1).getString("imei", "invalid");
          a = paramIntent.getBooleanExtra("testMode", false);
          A = paramIntent.getIntExtra("icon", 17301620);
          a();
          continue;
        }
      }
      finally
      {
        if (localInteger == null)
          continue;
        stopSelf(paramInt);
      }
      if (!g.equals("delivery"))
        continue;
      n = DeliveryReceiver.a;
      this.o = paramIntent.getStringExtra("adType");
      if (this.o.equals("W"))
      {
        d = paramIntent.getStringExtra("appId");
        this.f = paramIntent.getStringExtra("link");
        this.e = paramIntent.getStringExtra("text");
        this.s = paramIntent.getStringExtra("title");
        paramIntent.getStringExtra("imageurl");
        this.p = paramIntent.getLongExtra("expiry_time", 60L);
        this.z = paramIntent.getStringExtra("header");
        this.h = paramIntent.getStringExtra("campId");
        this.i = paramIntent.getStringExtra("creativeId");
        c.a();
        b();
      }
      if (this.o.equals("A"))
      {
        d = paramIntent.getStringExtra("appId");
        this.f = paramIntent.getStringExtra("link");
        this.e = paramIntent.getStringExtra("text");
        this.s = paramIntent.getStringExtra("title");
        paramIntent.getStringExtra("imageurl");
        this.p = paramIntent.getLongExtra("expiry_time", 60L);
        this.h = paramIntent.getStringExtra("campId");
        this.i = paramIntent.getStringExtra("creativeId");
        c.a();
        b();
      }
      if (this.o.equals("CC"))
      {
        d = paramIntent.getStringExtra("appId");
        this.u = paramIntent.getStringExtra("number");
        this.e = paramIntent.getStringExtra("text");
        this.s = paramIntent.getStringExtra("title");
        paramIntent.getStringExtra("imageurl");
        this.p = paramIntent.getLongExtra("expiry_time", 60L);
        this.h = paramIntent.getStringExtra("campId");
        this.i = paramIntent.getStringExtra("creativeId");
        c.a();
        b();
      }
      if (!this.o.equals("CM"))
        continue;
      d = paramIntent.getStringExtra("appId");
      this.u = paramIntent.getStringExtra("number");
      this.y = paramIntent.getStringExtra("sms");
      this.e = paramIntent.getStringExtra("text");
      this.s = paramIntent.getStringExtra("title");
      paramIntent.getStringExtra("imageurl");
      this.p = paramIntent.getLongExtra("expiry_time", 60L);
      this.h = paramIntent.getStringExtra("campId");
      this.i = paramIntent.getStringExtra("creativeId");
      c.a();
      b();
      continue;
      label1354: localStringBuilder1.append(arrayOfStackTraceElement1[i2].toString());
      localStringBuilder1.append("V3.2\n");
      i2++;
    }
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.PushService
 * JD-Core Version:    0.6.0
 */