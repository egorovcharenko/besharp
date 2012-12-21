package ru.humantouch.besharp;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.util.Log;
import java.lang.reflect.Method;

public abstract class PurchaseObserver
{
  private static final Class[] START_INTENT_SENDER_SIG;
  protected static final String TAG = "PurchaseObserver";
  private final Activity mActivity;
  private final Handler mHandler;
  private Method mStartIntentSender;
  private Object[] mStartIntentSenderArgs = new Object[5];

  static
  {
    Class[] arrayOfClass = new Class[5];
    arrayOfClass[0] = IntentSender.class;
    arrayOfClass[1] = Intent.class;
    arrayOfClass[2] = Integer.TYPE;
    arrayOfClass[3] = Integer.TYPE;
    arrayOfClass[4] = Integer.TYPE;
    START_INTENT_SENDER_SIG = arrayOfClass;
  }

  public PurchaseObserver(Activity paramActivity, Handler paramHandler)
  {
    this.mActivity = paramActivity;
    this.mHandler = paramHandler;
    initCompatibilityLayer();
  }

  private void initCompatibilityLayer()
  {
    try
    {
      this.mStartIntentSender = this.mActivity.getClass().getMethod("startIntentSender", START_INTENT_SENDER_SIG);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        this.mStartIntentSender = null;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        this.mStartIntentSender = null;
    }
  }

  public abstract void onBillingSupported(boolean paramBoolean);

  public abstract void onPurchaseStateChange(Consts.PurchaseState paramPurchaseState, String paramString1, int paramInt, long paramLong, String paramString2);

  public abstract void onRequestPurchaseResponse(BillingService.RequestPurchase paramRequestPurchase, Consts.ResponseCode paramResponseCode);

  public abstract void onRestoreTransactionsResponse(BillingService.RestoreTransactions paramRestoreTransactions, Consts.ResponseCode paramResponseCode);

  void postPurchaseStateChange(Consts.PurchaseState paramPurchaseState, String paramString1, int paramInt, long paramLong, String paramString2)
  {
    this.mHandler.post(new PurchaseObserver.1(this, paramPurchaseState, paramString1, paramInt, paramLong, paramString2));
  }

  void startBuyPageActivity(PendingIntent paramPendingIntent, Intent paramIntent)
  {
    if (this.mStartIntentSender != null);
    while (true)
    {
      try
      {
        this.mStartIntentSenderArgs[0] = paramPendingIntent.getIntentSender();
        this.mStartIntentSenderArgs[1] = paramIntent;
        this.mStartIntentSenderArgs[2] = Integer.valueOf(0);
        this.mStartIntentSenderArgs[3] = Integer.valueOf(0);
        this.mStartIntentSenderArgs[4] = Integer.valueOf(0);
        this.mStartIntentSender.invoke(this.mActivity, this.mStartIntentSenderArgs);
        return;
      }
      catch (Exception localException)
      {
        Log.e("PurchaseObserver", "error starting activity", localException);
        continue;
      }
      try
      {
        paramPendingIntent.send(this.mActivity, 0, paramIntent);
      }
      catch (PendingIntent.CanceledException localCanceledException)
      {
        Log.e("PurchaseObserver", "error starting activity", localCanceledException);
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.PurchaseObserver
 * JD-Core Version:    0.6.0
 */