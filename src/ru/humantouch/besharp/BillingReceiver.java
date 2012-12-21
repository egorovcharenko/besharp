package ru.humantouch.besharp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BillingReceiver extends BroadcastReceiver
{
  private static final String TAG = "BillingReceiver";

  private void checkResponseCode(Context paramContext, long paramLong, int paramInt)
  {
    Intent localIntent = new Intent("com.android.vending.billing.RESPONSE_CODE");
    localIntent.setClass(paramContext, BillingService.class);
    localIntent.putExtra("request_id", paramLong);
    localIntent.putExtra("response_code", paramInt);
    paramContext.startService(localIntent);
  }

  private void notify(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("com.example.dungeons.GET_PURCHASE_INFORMATION");
    localIntent.setClass(paramContext, BillingService.class);
    localIntent.putExtra("notification_id", paramString);
    paramContext.startService(localIntent);
  }

  private void purchaseStateChanged(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("com.android.vending.billing.PURCHASE_STATE_CHANGED");
    localIntent.setClass(paramContext, BillingService.class);
    localIntent.putExtra("inapp_signed_data", paramString1);
    localIntent.putExtra("inapp_signature", paramString2);
    paramContext.startService(localIntent);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(str))
      purchaseStateChanged(paramContext, paramIntent.getStringExtra("inapp_signed_data"), paramIntent.getStringExtra("inapp_signature"));
    while (true)
    {
      return;
      if ("com.android.vending.billing.IN_APP_NOTIFY".equals(str))
      {
        notify(paramContext, paramIntent.getStringExtra("notification_id"));
        continue;
      }
      if ("com.android.vending.billing.RESPONSE_CODE".equals(str))
      {
        checkResponseCode(paramContext, paramIntent.getLongExtra("request_id", -1L), paramIntent.getIntExtra("response_code", Consts.ResponseCode.RESULT_ERROR.ordinal()));
        continue;
      }
      Log.w("BillingReceiver", "unexpected action: " + str);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.BillingReceiver
 * JD-Core Version:    0.6.0
 */