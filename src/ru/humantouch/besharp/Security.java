package ru.humantouch.besharp;

import android.util.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import ru.humantouch.besharp.util.Base64;
import ru.humantouch.besharp.util.Base64DecoderException;

public class Security
{
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String TAG = "Security";
  private static HashSet<Long> sKnownNonces = new HashSet();

  public static long generateNonce()
  {
    long l = RANDOM.nextLong();
    sKnownNonces.add(Long.valueOf(l));
    return l;
  }

  public static PublicKey generatePublicKey(String paramString)
  {
    try
    {
      byte[] arrayOfByte = Base64.decode(paramString);
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arrayOfByte));
      return localPublicKey;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      Log.e("Security", "Invalid key specification.");
      throw new IllegalArgumentException(localInvalidKeySpecException);
    }
    catch (Base64DecoderException localBase64DecoderException)
    {
      Log.e("Security", "Base64 decoding failed.");
    }
    throw new IllegalArgumentException(localBase64DecoderException);
  }

  public static boolean isNonceKnown(long paramLong)
  {
    return sKnownNonces.contains(Long.valueOf(paramLong));
  }

  public static void removeNonce(long paramLong)
  {
    sKnownNonces.remove(Long.valueOf(paramLong));
  }

  public static boolean verify(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!localSignature.verify(Base64.decode(paramString2)))
        Log.e("Security", "Signature verification failed.");
      for (i = 0; ; i = 1)
        return i;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        Log.e("Security", "NoSuchAlgorithmException.");
        int i = 0;
      }
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      while (true)
        Log.e("Security", "Invalid key specification.");
    }
    catch (SignatureException localSignatureException)
    {
      while (true)
        Log.e("Security", "Signature exception.");
    }
    catch (Base64DecoderException localBase64DecoderException)
    {
      while (true)
        Log.e("Security", "Base64 decoding failed.");
    }
  }

  // ERROR //
  public static java.util.ArrayList<VerifiedPurchase> verifyPurchase(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +17 -> 18
    //   4: ldc 19
    //   6: ldc 148
    //   8: invokestatic 94	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   11: pop
    //   12: aconst_null
    //   13: astore 5
    //   15: aload 5
    //   17: areturn
    //   18: aload_1
    //   19: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   22: ifne +326 -> 348
    //   25: ldc 156
    //   27: invokestatic 158	ru/humantouch/besharp/Security:generatePublicKey	(Ljava/lang/String;)Ljava/security/PublicKey;
    //   30: aload_0
    //   31: aload_1
    //   32: invokestatic 160	ru/humantouch/besharp/Security:verify	(Ljava/security/PublicKey;Ljava/lang/String;Ljava/lang/String;)Z
    //   35: istore 29
    //   37: iload 29
    //   39: ifne +17 -> 56
    //   42: ldc 19
    //   44: ldc 162
    //   46: invokestatic 165	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   49: pop
    //   50: aconst_null
    //   51: astore 5
    //   53: goto -38 -> 15
    //   56: iload 29
    //   58: istore_2
    //   59: new 167	org/json/JSONObject
    //   62: dup
    //   63: aload_0
    //   64: invokespecial 170	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   67: astore_3
    //   68: aload_3
    //   69: ldc 172
    //   71: invokevirtual 176	org/json/JSONObject:optLong	(Ljava/lang/String;)J
    //   74: lstore 6
    //   76: aload_3
    //   77: ldc 178
    //   79: invokevirtual 182	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   82: astore 9
    //   84: aload 9
    //   86: ifnull +256 -> 342
    //   89: aload 9
    //   91: invokevirtual 188	org/json/JSONArray:length	()I
    //   94: istore 28
    //   96: iload 28
    //   98: istore 10
    //   100: lload 6
    //   102: invokestatic 190	ru/humantouch/besharp/Security:isNonceKnown	(J)Z
    //   105: ifne +44 -> 149
    //   108: new 192	java/lang/StringBuilder
    //   111: dup
    //   112: ldc 194
    //   114: invokespecial 195	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   117: astore 11
    //   119: ldc 19
    //   121: aload 11
    //   123: lload 6
    //   125: invokevirtual 199	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   128: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokestatic 165	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aconst_null
    //   136: astore 5
    //   138: goto -123 -> 15
    //   141: astore 4
    //   143: aconst_null
    //   144: astore 5
    //   146: goto -131 -> 15
    //   149: new 205	java/util/ArrayList
    //   152: dup
    //   153: invokespecial 206	java/util/ArrayList:<init>	()V
    //   156: astore 13
    //   158: iconst_0
    //   159: istore 14
    //   161: iload 14
    //   163: iload 10
    //   165: if_icmplt +15 -> 180
    //   168: lload 6
    //   170: invokestatic 208	ru/humantouch/besharp/Security:removeNonce	(J)V
    //   173: aload 13
    //   175: astore 5
    //   177: goto -162 -> 15
    //   180: aload 9
    //   182: iload 14
    //   184: invokevirtual 212	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   187: astore 17
    //   189: aload 17
    //   191: ldc 214
    //   193: invokevirtual 218	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   196: invokestatic 223	ru/humantouch/besharp/Consts$PurchaseState:valueOf	(I)Lru/humantouch/besharp/Consts$PurchaseState;
    //   199: astore 18
    //   201: aload 17
    //   203: ldc 225
    //   205: invokevirtual 229	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   208: astore 19
    //   210: aload 17
    //   212: ldc 231
    //   214: invokevirtual 229	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   217: pop
    //   218: aload 17
    //   220: ldc 233
    //   222: invokevirtual 236	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   225: lstore 21
    //   227: aload 17
    //   229: ldc 238
    //   231: ldc 240
    //   233: invokevirtual 244	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   236: astore 23
    //   238: aconst_null
    //   239: astore 24
    //   241: aload 17
    //   243: ldc 246
    //   245: invokevirtual 250	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   248: ifeq +12 -> 260
    //   251: aload 17
    //   253: ldc 246
    //   255: invokevirtual 229	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   258: astore 24
    //   260: aload 17
    //   262: ldc 252
    //   264: aconst_null
    //   265: invokevirtual 244	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   268: astore 25
    //   270: aload 18
    //   272: getstatic 256	ru/humantouch/besharp/Consts$PurchaseState:PURCHASED	Lru/humantouch/besharp/Consts$PurchaseState;
    //   275: if_acmpne +10 -> 285
    //   278: iload_2
    //   279: ifne +6 -> 285
    //   282: goto +71 -> 353
    //   285: aload 13
    //   287: new 6	ru/humantouch/besharp/Security$VerifiedPurchase
    //   290: dup
    //   291: aload 18
    //   293: aload 24
    //   295: aload 19
    //   297: aload 23
    //   299: lload 21
    //   301: aload 25
    //   303: invokespecial 259	ru/humantouch/besharp/Security$VerifiedPurchase:<init>	(Lru/humantouch/besharp/Consts$PurchaseState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V
    //   306: invokevirtual 260	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   309: pop
    //   310: goto +43 -> 353
    //   313: astore 15
    //   315: ldc 19
    //   317: ldc_w 262
    //   320: aload 15
    //   322: invokestatic 265	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   325: pop
    //   326: aconst_null
    //   327: astore 5
    //   329: goto -314 -> 15
    //   332: astore 8
    //   334: goto -191 -> 143
    //   337: astore 27
    //   339: goto -196 -> 143
    //   342: iconst_0
    //   343: istore 10
    //   345: goto -245 -> 100
    //   348: iconst_0
    //   349: istore_2
    //   350: goto -291 -> 59
    //   353: iinc 14 1
    //   356: goto -195 -> 161
    //
    // Exception table:
    //   from	to	target	type
    //   59	76	141	org/json/JSONException
    //   180	310	313	org/json/JSONException
    //   76	84	332	org/json/JSONException
    //   89	96	337	org/json/JSONException
  }

  public static class VerifiedPurchase
  {
    public String developerPayload;
    public String notificationId;
    public String orderId;
    public String productId;
    public Consts.PurchaseState purchaseState;
    public long purchaseTime;

    public VerifiedPurchase(Consts.PurchaseState paramPurchaseState, String paramString1, String paramString2, String paramString3, long paramLong, String paramString4)
    {
      this.purchaseState = paramPurchaseState;
      this.notificationId = paramString1;
      this.productId = paramString2;
      this.orderId = paramString3;
      this.purchaseTime = paramLong;
      this.developerPayload = paramString4;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.Security
 * JD-Core Version:    0.6.0
 */