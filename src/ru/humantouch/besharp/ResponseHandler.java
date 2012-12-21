package ru.humantouch.besharp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ResponseHandler
{
  private static final String TAG = "ResponseHandler";
  private static PurchaseObserver sPurchaseObserver;

  public static void buyPageIntentResponse(PendingIntent paramPendingIntent, Intent paramIntent)
  {
    if (sPurchaseObserver == null);
    while (true)
    {
      return;
      sPurchaseObserver.startBuyPageActivity(paramPendingIntent, paramIntent);
    }
  }

  public static void checkBillingSupportedResponse(boolean paramBoolean)
  {
    if (sPurchaseObserver != null)
      sPurchaseObserver.onBillingSupported(paramBoolean);
  }

  public static void purchaseResponse(Context paramContext, Consts.PurchaseState paramPurchaseState, String paramString1, String paramString2, long paramLong, String paramString3)
  {
    new Thread(new ResponseHandler.1(paramContext, paramString2, paramString1, paramPurchaseState, paramLong, paramString3)).start();
  }

  public static void register(PurchaseObserver paramPurchaseObserver)
  {
    monitorenter;
    try
    {
      sPurchaseObserver = paramPurchaseObserver;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void responseCodeReceived(Context paramContext, BillingService.RequestPurchase paramRequestPurchase, Consts.ResponseCode paramResponseCode)
  {
    if (sPurchaseObserver != null)
      sPurchaseObserver.onRequestPurchaseResponse(paramRequestPurchase, paramResponseCode);
  }

  public static void responseCodeReceived(Context paramContext, BillingService.RestoreTransactions paramRestoreTransactions, Consts.ResponseCode paramResponseCode)
  {
    if (sPurchaseObserver != null)
      sPurchaseObserver.onRestoreTransactionsResponse(paramRestoreTransactions, paramResponseCode);
  }

  public static void unregister(PurchaseObserver paramPurchaseObserver)
  {
    monitorenter;
    try
    {
      sPurchaseObserver = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.ResponseHandler
 * JD-Core Version:    0.6.0
 */