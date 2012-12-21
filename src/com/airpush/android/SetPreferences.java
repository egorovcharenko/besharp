package com.airpush.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class SetPreferences
{
  protected static List a;
  private static JSONObject b = null;
  private static int e;
  private static String g;
  private static boolean h;
  private static String i;
  private static String j = "0";
  private static String k = "00";
  private static String l = "invalid";
  private static String m = "0";
  private static String n = "0";
  private static String o = "0";
  private static String p = "0";
  private static String q = "0";
  private static String r = "0";
  private static String s = "0";
  private static String t = "0";
  private static String u = "0";
  private static String v = "0";
  private static Context w;
  private static String x;
  private String c = "0";
  private boolean d;
  private boolean f;

  // ERROR //
  protected static List a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 81
    //   3: iconst_1
    //   4: invokevirtual 87	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: aconst_null
    //   8: invokevirtual 91	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   11: ifne +580 -> 591
    //   14: aload_0
    //   15: ldc 81
    //   17: iconst_1
    //   18: invokevirtual 87	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   21: astore 48
    //   23: aload 48
    //   25: ldc 93
    //   27: ldc 48
    //   29: invokeinterface 99 3 0
    //   34: putstatic 62	com/airpush/android/SetPreferences:r	Ljava/lang/String;
    //   37: aload 48
    //   39: ldc 101
    //   41: ldc 103
    //   43: invokeinterface 99 3 0
    //   48: putstatic 64	com/airpush/android/SetPreferences:s	Ljava/lang/String;
    //   51: aload 48
    //   53: ldc 105
    //   55: ldc 48
    //   57: invokeinterface 99 3 0
    //   62: putstatic 42	com/airpush/android/SetPreferences:j	Ljava/lang/String;
    //   65: aload 48
    //   67: ldc 107
    //   69: ldc 48
    //   71: invokeinterface 99 3 0
    //   76: putstatic 66	com/airpush/android/SetPreferences:t	Ljava/lang/String;
    //   79: new 109	java/util/Date
    //   82: dup
    //   83: invokespecial 110	java/util/Date:<init>	()V
    //   86: invokevirtual 113	java/util/Date:toString	()Ljava/lang/String;
    //   89: putstatic 46	com/airpush/android/SetPreferences:k	Ljava/lang/String;
    //   92: aload 48
    //   94: ldc 115
    //   96: ldc 48
    //   98: invokeinterface 99 3 0
    //   103: putstatic 50	com/airpush/android/SetPreferences:l	Ljava/lang/String;
    //   106: aload 48
    //   108: ldc 117
    //   110: ldc 48
    //   112: invokeinterface 99 3 0
    //   117: putstatic 52	com/airpush/android/SetPreferences:m	Ljava/lang/String;
    //   120: aload 48
    //   122: ldc 119
    //   124: ldc 48
    //   126: invokeinterface 99 3 0
    //   131: putstatic 54	com/airpush/android/SetPreferences:n	Ljava/lang/String;
    //   134: aload 48
    //   136: ldc 121
    //   138: ldc 48
    //   140: invokeinterface 99 3 0
    //   145: putstatic 56	com/airpush/android/SetPreferences:o	Ljava/lang/String;
    //   148: aload 48
    //   150: ldc 123
    //   152: ldc 48
    //   154: invokeinterface 99 3 0
    //   159: putstatic 58	com/airpush/android/SetPreferences:p	Ljava/lang/String;
    //   162: aload 48
    //   164: ldc 125
    //   166: ldc 48
    //   168: invokeinterface 99 3 0
    //   173: putstatic 60	com/airpush/android/SetPreferences:q	Ljava/lang/String;
    //   176: aload 48
    //   178: ldc 127
    //   180: ldc 48
    //   182: invokeinterface 99 3 0
    //   187: putstatic 68	com/airpush/android/SetPreferences:u	Ljava/lang/String;
    //   190: aload 48
    //   192: ldc 129
    //   194: ldc 48
    //   196: invokeinterface 99 3 0
    //   201: putstatic 70	com/airpush/android/SetPreferences:v	Ljava/lang/String;
    //   204: aload 48
    //   206: ldc 131
    //   208: ldc 133
    //   210: invokeinterface 99 3 0
    //   215: putstatic 135	com/airpush/android/SetPreferences:i	Ljava/lang/String;
    //   218: aload 48
    //   220: ldc 137
    //   222: ldc 40
    //   224: invokeinterface 99 3 0
    //   229: putstatic 139	com/airpush/android/SetPreferences:g	Ljava/lang/String;
    //   232: aload 48
    //   234: ldc 141
    //   236: iconst_0
    //   237: invokeinterface 145 3 0
    //   242: putstatic 147	com/airpush/android/SetPreferences:h	Z
    //   245: aload 48
    //   247: ldc 149
    //   249: ldc 150
    //   251: invokeinterface 154 3 0
    //   256: putstatic 156	com/airpush/android/SetPreferences:e	I
    //   259: new 158	java/util/ArrayList
    //   262: dup
    //   263: invokespecial 159	java/util/ArrayList:<init>	()V
    //   266: astore 29
    //   268: aload 29
    //   270: putstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   273: aload 29
    //   275: new 163	org/apache/http/message/BasicNameValuePair
    //   278: dup
    //   279: ldc 101
    //   281: getstatic 64	com/airpush/android/SetPreferences:s	Ljava/lang/String;
    //   284: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   287: invokeinterface 171 2 0
    //   292: pop
    //   293: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   296: new 163	org/apache/http/message/BasicNameValuePair
    //   299: dup
    //   300: ldc 93
    //   302: getstatic 62	com/airpush/android/SetPreferences:r	Ljava/lang/String;
    //   305: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   308: invokeinterface 171 2 0
    //   313: pop
    //   314: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   317: new 163	org/apache/http/message/BasicNameValuePair
    //   320: dup
    //   321: ldc 105
    //   323: getstatic 42	com/airpush/android/SetPreferences:j	Ljava/lang/String;
    //   326: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   329: invokeinterface 171 2 0
    //   334: pop
    //   335: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   338: new 163	org/apache/http/message/BasicNameValuePair
    //   341: dup
    //   342: ldc 107
    //   344: getstatic 66	com/airpush/android/SetPreferences:t	Ljava/lang/String;
    //   347: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   350: invokeinterface 171 2 0
    //   355: pop
    //   356: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   359: new 163	org/apache/http/message/BasicNameValuePair
    //   362: dup
    //   363: ldc 173
    //   365: getstatic 46	com/airpush/android/SetPreferences:k	Ljava/lang/String;
    //   368: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   371: invokeinterface 171 2 0
    //   376: pop
    //   377: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   380: new 163	org/apache/http/message/BasicNameValuePair
    //   383: dup
    //   384: ldc 115
    //   386: getstatic 50	com/airpush/android/SetPreferences:l	Ljava/lang/String;
    //   389: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   392: invokeinterface 171 2 0
    //   397: pop
    //   398: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   401: new 163	org/apache/http/message/BasicNameValuePair
    //   404: dup
    //   405: ldc 117
    //   407: getstatic 52	com/airpush/android/SetPreferences:m	Ljava/lang/String;
    //   410: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   413: invokeinterface 171 2 0
    //   418: pop
    //   419: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   422: new 163	org/apache/http/message/BasicNameValuePair
    //   425: dup
    //   426: ldc 119
    //   428: getstatic 54	com/airpush/android/SetPreferences:n	Ljava/lang/String;
    //   431: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   434: invokeinterface 171 2 0
    //   439: pop
    //   440: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   443: new 163	org/apache/http/message/BasicNameValuePair
    //   446: dup
    //   447: ldc 121
    //   449: getstatic 56	com/airpush/android/SetPreferences:o	Ljava/lang/String;
    //   452: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   455: invokeinterface 171 2 0
    //   460: pop
    //   461: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   464: new 163	org/apache/http/message/BasicNameValuePair
    //   467: dup
    //   468: ldc 123
    //   470: getstatic 58	com/airpush/android/SetPreferences:p	Ljava/lang/String;
    //   473: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   476: invokeinterface 171 2 0
    //   481: pop
    //   482: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   485: new 163	org/apache/http/message/BasicNameValuePair
    //   488: dup
    //   489: ldc 125
    //   491: getstatic 60	com/airpush/android/SetPreferences:q	Ljava/lang/String;
    //   494: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   497: invokeinterface 171 2 0
    //   502: pop
    //   503: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   506: new 163	org/apache/http/message/BasicNameValuePair
    //   509: dup
    //   510: ldc 127
    //   512: getstatic 68	com/airpush/android/SetPreferences:u	Ljava/lang/String;
    //   515: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   518: invokeinterface 171 2 0
    //   523: pop
    //   524: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   527: new 163	org/apache/http/message/BasicNameValuePair
    //   530: dup
    //   531: ldc 129
    //   533: getstatic 70	com/airpush/android/SetPreferences:v	Ljava/lang/String;
    //   536: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   539: invokeinterface 171 2 0
    //   544: pop
    //   545: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   548: new 163	org/apache/http/message/BasicNameValuePair
    //   551: dup
    //   552: ldc 131
    //   554: getstatic 135	com/airpush/android/SetPreferences:i	Ljava/lang/String;
    //   557: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   560: invokeinterface 171 2 0
    //   565: pop
    //   566: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   569: new 163	org/apache/http/message/BasicNameValuePair
    //   572: dup
    //   573: ldc 175
    //   575: getstatic 139	com/airpush/android/SetPreferences:g	Ljava/lang/String;
    //   578: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   581: invokeinterface 171 2 0
    //   586: pop
    //   587: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   590: areturn
    //   591: getstatic 177	com/airpush/android/SetPreferences:w	Landroid/content/Context;
    //   594: invokevirtual 180	android/content/Context:getPackageName	()Ljava/lang/String;
    //   597: putstatic 50	com/airpush/android/SetPreferences:l	Ljava/lang/String;
    //   600: new 182	java/lang/StringBuilder
    //   603: dup
    //   604: ldc 184
    //   606: invokespecial 187	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   609: getstatic 50	com/airpush/android/SetPreferences:l	Ljava/lang/String;
    //   612: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   618: getstatic 177	com/airpush/android/SetPreferences:w	Landroid/content/Context;
    //   621: invokestatic 197	com/airpush/android/HttpPostData:a	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   624: astore 47
    //   626: aload 47
    //   628: putstatic 199	com/airpush/android/SetPreferences:x	Ljava/lang/String;
    //   631: aload 47
    //   633: invokestatic 202	com/airpush/android/SetPreferences:c	(Ljava/lang/String;)Ljava/lang/String;
    //   636: putstatic 62	com/airpush/android/SetPreferences:r	Ljava/lang/String;
    //   639: getstatic 199	com/airpush/android/SetPreferences:x	Ljava/lang/String;
    //   642: invokestatic 204	com/airpush/android/SetPreferences:d	(Ljava/lang/String;)Ljava/lang/String;
    //   645: putstatic 64	com/airpush/android/SetPreferences:s	Ljava/lang/String;
    //   648: goto -389 -> 259
    //   651: astore_1
    //   652: new 182	java/lang/StringBuilder
    //   655: dup
    //   656: invokespecial 205	java/lang/StringBuilder:<init>	()V
    //   659: astore_2
    //   660: aload_2
    //   661: ldc 207
    //   663: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   666: pop
    //   667: aload_1
    //   668: invokevirtual 211	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   671: astore 19
    //   673: aload 19
    //   675: arraylength
    //   676: istore 20
    //   678: iconst_0
    //   679: istore 21
    //   681: iload 21
    //   683: iload 20
    //   685: if_icmplt +341 -> 1026
    //   688: new 158	java/util/ArrayList
    //   691: dup
    //   692: invokespecial 159	java/util/ArrayList:<init>	()V
    //   695: astore 22
    //   697: aload 22
    //   699: putstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   702: aload 22
    //   704: new 163	org/apache/http/message/BasicNameValuePair
    //   707: dup
    //   708: ldc 213
    //   710: ldc 215
    //   712: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   715: invokeinterface 171 2 0
    //   720: pop
    //   721: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   724: new 163	org/apache/http/message/BasicNameValuePair
    //   727: dup
    //   728: ldc 93
    //   730: getstatic 62	com/airpush/android/SetPreferences:r	Ljava/lang/String;
    //   733: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   736: invokeinterface 171 2 0
    //   741: pop
    //   742: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   745: new 163	org/apache/http/message/BasicNameValuePair
    //   748: dup
    //   749: ldc 217
    //   751: ldc 219
    //   753: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   756: invokeinterface 171 2 0
    //   761: pop
    //   762: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   765: new 163	org/apache/http/message/BasicNameValuePair
    //   768: dup
    //   769: ldc 221
    //   771: ldc 103
    //   773: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   776: invokeinterface 171 2 0
    //   781: pop
    //   782: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   785: new 163	org/apache/http/message/BasicNameValuePair
    //   788: dup
    //   789: ldc 223
    //   791: new 182	java/lang/StringBuilder
    //   794: dup
    //   795: aload_1
    //   796: invokevirtual 224	java/lang/Exception:toString	()Ljava/lang/String;
    //   799: invokestatic 230	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   802: invokespecial 187	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   805: aload_2
    //   806: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   809: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   815: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   818: invokeinterface 171 2 0
    //   823: pop
    //   824: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   827: getstatic 177	com/airpush/android/SetPreferences:w	Landroid/content/Context;
    //   830: invokestatic 233	com/airpush/android/HttpPostData:a	(Ljava/util/List;Landroid/content/Context;)Lorg/apache/http/HttpEntity;
    //   833: pop
    //   834: goto -575 -> 259
    //   837: astore_3
    //   838: new 182	java/lang/StringBuilder
    //   841: dup
    //   842: invokespecial 205	java/lang/StringBuilder:<init>	()V
    //   845: astore 4
    //   847: aload 4
    //   849: ldc 235
    //   851: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   854: pop
    //   855: aload_3
    //   856: invokevirtual 211	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   859: astore 6
    //   861: aload 6
    //   863: arraylength
    //   864: istore 7
    //   866: iconst_0
    //   867: istore 8
    //   869: iload 8
    //   871: iload 7
    //   873: if_icmplt +179 -> 1052
    //   876: new 158	java/util/ArrayList
    //   879: dup
    //   880: invokespecial 159	java/util/ArrayList:<init>	()V
    //   883: astore 9
    //   885: aload 9
    //   887: putstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   890: aload 9
    //   892: new 163	org/apache/http/message/BasicNameValuePair
    //   895: dup
    //   896: ldc 213
    //   898: ldc 215
    //   900: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   903: invokeinterface 171 2 0
    //   908: pop
    //   909: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   912: new 163	org/apache/http/message/BasicNameValuePair
    //   915: dup
    //   916: ldc 93
    //   918: getstatic 62	com/airpush/android/SetPreferences:r	Ljava/lang/String;
    //   921: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   924: invokeinterface 171 2 0
    //   929: pop
    //   930: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   933: new 163	org/apache/http/message/BasicNameValuePair
    //   936: dup
    //   937: ldc 217
    //   939: ldc 219
    //   941: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   944: invokeinterface 171 2 0
    //   949: pop
    //   950: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   953: new 163	org/apache/http/message/BasicNameValuePair
    //   956: dup
    //   957: ldc 221
    //   959: ldc 103
    //   961: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   964: invokeinterface 171 2 0
    //   969: pop
    //   970: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   973: new 163	org/apache/http/message/BasicNameValuePair
    //   976: dup
    //   977: ldc 223
    //   979: new 182	java/lang/StringBuilder
    //   982: dup
    //   983: aload_3
    //   984: invokevirtual 224	java/lang/Exception:toString	()Ljava/lang/String;
    //   987: invokestatic 230	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   990: invokespecial 187	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   993: aload 4
    //   995: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   998: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1001: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1004: invokespecial 166	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   1007: invokeinterface 171 2 0
    //   1012: pop
    //   1013: getstatic 161	com/airpush/android/SetPreferences:a	Ljava/util/List;
    //   1016: getstatic 177	com/airpush/android/SetPreferences:w	Landroid/content/Context;
    //   1019: invokestatic 233	com/airpush/android/HttpPostData:a	(Ljava/util/List;Landroid/content/Context;)Lorg/apache/http/HttpEntity;
    //   1022: pop
    //   1023: goto -436 -> 587
    //   1026: aload_2
    //   1027: aload 19
    //   1029: iload 21
    //   1031: aaload
    //   1032: invokevirtual 238	java/lang/StackTraceElement:toString	()Ljava/lang/String;
    //   1035: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1038: pop
    //   1039: aload_2
    //   1040: ldc 240
    //   1042: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: pop
    //   1046: iinc 21 1
    //   1049: goto -368 -> 681
    //   1052: aload 4
    //   1054: aload 6
    //   1056: iload 8
    //   1058: aaload
    //   1059: invokevirtual 238	java/lang/StackTraceElement:toString	()Ljava/lang/String;
    //   1062: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1065: pop
    //   1066: aload 4
    //   1068: ldc 240
    //   1070: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1073: pop
    //   1074: iinc 8 1
    //   1077: goto -208 -> 869
    //
    // Exception table:
    //   from	to	target	type
    //   0	259	651	java/lang/Exception
    //   591	648	651	java/lang/Exception
    //   259	587	837	java/lang/Exception
    //   652	834	837	java/lang/Exception
    //   1026	1046	837	java/lang/Exception
  }

  private static String c(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      b = localJSONObject;
      String str2 = localJSONObject.getString("appid");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "invalid Id";
    }
  }

  private void c()
  {
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    try
    {
      k = new Date().toString();
      SharedPreferences.Editor localEditor = w.getSharedPreferences("dataPrefs", 2).edit();
      localEditor.putString("apikey", s);
      localEditor.putString("appId", r);
      localEditor.putString("imei", j);
      localEditor.putString("connectionType", g);
      localEditor.putString("token", t);
      localEditor.putString("request_timestamp", k);
      localEditor.putString("packageName", l);
      localEditor.putString("version", m);
      localEditor.putString("carrier", n);
      localEditor.putString("networkOperator", o);
      localEditor.putString("phoneModel", p);
      localEditor.putString("manufacturer", q);
      localEditor.putString("longitude", u);
      localEditor.putString("latitude", v);
      localEditor.putString("sdkversion", "3.2");
      localEditor.putBoolean("showDialog", this.d);
      localEditor.putBoolean("showAd", this.f);
      localEditor.putBoolean("testMode", h);
      localEditor.putInt("icon", e);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("V3.2setPreferences setSharedPreferences");
      arrayOfStackTraceElement = localException.getStackTrace();
      i1 = arrayOfStackTraceElement.length;
      i2 = 0;
    }
    while (true)
    {
      if (i2 >= i1)
      {
        ArrayList localArrayList = new ArrayList();
        a = localArrayList;
        localArrayList.add(new BasicNameValuePair("model", "log"));
        a.add(new BasicNameValuePair("appId", r));
        a.add(new BasicNameValuePair("action", "sdkerror"));
        a.add(new BasicNameValuePair("APIKEY", "airpush"));
        a.add(new BasicNameValuePair("message", localException.toString() + localStringBuilder.toString()));
        HttpPostData.a(a, w);
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }

  private static String d(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      b = localJSONObject;
      String str2 = localJSONObject.getString("authkey");
      str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str1 = "invalid key";
    }
  }

  public static boolean isEnabled(Context paramContext)
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

  protected final void a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, int paramInt, boolean paramBoolean3)
  {
    w = paramContext;
    r = paramString1;
    s = paramString2;
    this.d = false;
    e = paramInt;
    this.f = true;
    h = paramBoolean1;
    String str;
    if (((ConnectivityManager)w.getSystemService("connectivity")).getActiveNetworkInfo().getTypeName().equals("WIFI"))
      str = "1";
    StringBuilder localStringBuilder;
    StackTraceElement[] arrayOfStackTraceElement;
    int i1;
    int i2;
    while (true)
    {
      g = str;
      Context localContext = w;
      Location localLocation;
      TelephonyManager localTelephonyManager;
      if ((localContext.getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", localContext.getPackageName()) == 0) && (localContext.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", localContext.getPackageName()) == 0))
      {
        LocationManager localLocationManager = (LocationManager)localContext.getSystemService("location");
        localLocation = localLocationManager.getLastKnownLocation("network");
        if (localLocation == null)
          localLocationManager.requestLocationUpdates("network", 0L, 0.0F, new h(this));
      }
      else
      {
        localTelephonyManager = (TelephonyManager)w.getSystemService("phone");
        this.c = localTelephonyManager.getDeviceId();
      }
      try
      {
        MessageDigest localMessageDigest1 = MessageDigest.getInstance("MD5");
        localMessageDigest1.update(this.c.getBytes(), 0, this.c.length());
        j = new BigInteger(1, localMessageDigest1.digest()).toString(16);
        k = new Date().toString();
        p = Build.MODEL;
        q = Build.MANUFACTURER;
        o = localTelephonyManager.getNetworkOperatorName();
        n = localTelephonyManager.getSimOperatorName();
        m = Build.VERSION.SDK;
        l = w.getPackageName();
        t = j + r + k;
        MessageDigest localMessageDigest2 = MessageDigest.getInstance("MD5");
        localMessageDigest2.update(t.getBytes(), 0, t.length());
        t = new BigInteger(1, localMessageDigest2.digest()).toString(16);
        c();
        return;
        str = "0";
        continue;
        u = String.valueOf(localLocation.getLongitude());
        v = String.valueOf(localLocation.getLatitude());
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("V3.2setPreferences");
        arrayOfStackTraceElement = localNoSuchAlgorithmException.getStackTrace();
        i1 = arrayOfStackTraceElement.length;
        i2 = 0;
      }
    }
    while (true)
    {
      if (i2 >= i1)
      {
        ArrayList localArrayList = new ArrayList();
        a = localArrayList;
        localArrayList.add(new BasicNameValuePair("model", "log"));
        a.add(new BasicNameValuePair("appId", r));
        a.add(new BasicNameValuePair("action", "sdkerror"));
        a.add(new BasicNameValuePair("APIKEY", "airpush"));
        a.add(new BasicNameValuePair("message", localNoSuchAlgorithmException.toString() + localStringBuilder.toString()));
        HttpPostData.a(a, w);
        Log.i("AirpushSDK", "IMEI conversion Error ");
        break;
      }
      localStringBuilder.append(arrayOfStackTraceElement[i2].toString());
      localStringBuilder.append("V3.2\n");
      i2++;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.SetPreferences
 * JD-Core Version:    0.6.0
 */