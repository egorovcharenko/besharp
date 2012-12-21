package ru.humantouch.besharp;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.billing.IMarketBillingService;
import com.android.vending.billing.IMarketBillingService.Stub;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BillingService extends Service
  implements ServiceConnection
{
  private static final String TAG = "BillingService";
  private static LinkedList<BillingRequest> mPendingRequests = new LinkedList();
  private static HashMap<Long, BillingRequest> mSentRequests = new HashMap();
  private static IMarketBillingService mService;

  private boolean bindToMarketBillingService()
  {
    int i;
    try
    {
      if (bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), this, 1))
      {
        i = 1;
      }
      else
      {
        Log.e("BillingService", "Could not bind to service.");
        i = 0;
      }
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        Log.e("BillingService", "Security exception: " + localSecurityException);
    }
    return i;
  }

  private void checkResponseCode(long paramLong, Consts.ResponseCode paramResponseCode)
  {
    BillingRequest localBillingRequest = (BillingRequest)mSentRequests.get(Long.valueOf(paramLong));
    if (localBillingRequest != null)
      localBillingRequest.responseCodeReceived(paramResponseCode);
    mSentRequests.remove(Long.valueOf(paramLong));
  }

  private boolean confirmNotifications(int paramInt, String[] paramArrayOfString)
  {
    return new ConfirmNotifications(paramInt, paramArrayOfString).runRequest();
  }

  private boolean getPurchaseInformation(int paramInt, String[] paramArrayOfString)
  {
    return new GetPurchaseInformation(paramInt, paramArrayOfString).runRequest();
  }

  private void purchaseStateChanged(int paramInt, String paramString1, String paramString2)
  {
    ArrayList localArrayList1 = Security.verifyPurchase(paramString1, paramString2);
    if (localArrayList1 == null)
      return;
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localArrayList1.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localArrayList2.isEmpty())
          break;
        confirmNotifications(paramInt, (String[])localArrayList2.toArray(new String[localArrayList2.size()]));
        break;
      }
      Security.VerifiedPurchase localVerifiedPurchase = (Security.VerifiedPurchase)localIterator.next();
      if (localVerifiedPurchase.notificationId != null)
        localArrayList2.add(localVerifiedPurchase.notificationId);
      ResponseHandler.purchaseResponse(this, localVerifiedPurchase.purchaseState, localVerifiedPurchase.productId, localVerifiedPurchase.orderId, localVerifiedPurchase.purchaseTime, localVerifiedPurchase.developerPayload);
    }
  }

  private void runPendingRequests()
  {
    int i = -1;
    BillingRequest localBillingRequest = (BillingRequest)mPendingRequests.peek();
    if (localBillingRequest == null)
      if (i >= 0)
        stopSelf(i);
    while (true)
    {
      return;
      if (localBillingRequest.runIfConnected())
      {
        mPendingRequests.remove();
        if (i >= localBillingRequest.getStartId())
          break;
        i = localBillingRequest.getStartId();
        break;
      }
      bindToMarketBillingService();
    }
  }

  public boolean checkBillingSupported()
  {
    return new CheckBillingSupported().runRequest();
  }

  public void handleCommand(Intent paramIntent, int paramInt)
  {
    String str1 = paramIntent.getAction();
    if ("com.example.dungeons.CONFIRM_NOTIFICATION".equals(str1))
      confirmNotifications(paramInt, paramIntent.getStringArrayExtra("notification_id"));
    while (true)
    {
      return;
      if ("com.example.dungeons.GET_PURCHASE_INFORMATION".equals(str1))
      {
        String str2 = paramIntent.getStringExtra("notification_id");
        String[] arrayOfString = new String[1];
        arrayOfString[0] = str2;
        getPurchaseInformation(paramInt, arrayOfString);
        continue;
      }
      if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(str1))
      {
        purchaseStateChanged(paramInt, paramIntent.getStringExtra("inapp_signed_data"), paramIntent.getStringExtra("inapp_signature"));
        continue;
      }
      if (!"com.android.vending.billing.RESPONSE_CODE".equals(str1))
        continue;
      checkResponseCode(paramIntent.getLongExtra("request_id", -1L), Consts.ResponseCode.valueOf(paramIntent.getIntExtra("response_code", Consts.ResponseCode.RESULT_ERROR.ordinal())));
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    mService = IMarketBillingService.Stub.asInterface(paramIBinder);
    runPendingRequests();
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.w("BillingService", "Billing service disconnected");
    mService = null;
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    handleCommand(paramIntent, paramInt);
  }

  public boolean requestPurchase(String paramString1, String paramString2)
  {
    return new RequestPurchase(paramString1, paramString2).runRequest();
  }

  public boolean restoreTransactions()
  {
    return new RestoreTransactions().runRequest();
  }

  public void setContext(Context paramContext)
  {
    attachBaseContext(paramContext);
  }

  public void unbind()
  {
    try
    {
      unbindService(this);
      label5: return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label5;
    }
  }

  abstract class BillingRequest
  {
    protected long mRequestId;
    private final int mStartId;

    public BillingRequest(int arg2)
    {
      int i;
      this.mStartId = i;
    }

    public int getStartId()
    {
      return this.mStartId;
    }

    protected void logResponseCode(String paramString, Bundle paramBundle)
    {
      Consts.ResponseCode.valueOf(paramBundle.getInt("RESPONSE_CODE"));
    }

    protected Bundle makeRequestBundle(String paramString)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("BILLING_REQUEST", paramString);
      localBundle.putInt("API_VERSION", 1);
      localBundle.putString("PACKAGE_NAME", BillingService.this.getPackageName());
      return localBundle;
    }

    protected void onRemoteException(RemoteException paramRemoteException)
    {
      Log.w("BillingService", "remote billing service crashed");
      BillingService.mService = null;
    }

    protected void responseCodeReceived(Consts.ResponseCode paramResponseCode)
    {
    }

    protected abstract long run()
      throws RemoteException;

    public boolean runIfConnected()
    {
      if (BillingService.mService != null);
      while (true)
      {
        try
        {
          this.mRequestId = run();
          if (this.mRequestId < 0L)
            continue;
          BillingService.mSentRequests.put(Long.valueOf(this.mRequestId), this);
          i = 1;
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          onRemoteException(localRemoteException);
        }
        int i = 0;
      }
    }

    public boolean runRequest()
    {
      int i;
      if (runIfConnected())
        i = 1;
      while (true)
      {
        return i;
        if (BillingService.this.bindToMarketBillingService())
        {
          BillingService.mPendingRequests.add(this);
          i = 1;
          continue;
        }
        i = 0;
      }
    }
  }

  class CheckBillingSupported extends BillingService.BillingRequest
  {
    public CheckBillingSupported()
    {
      super(-1);
    }

    protected long run()
      throws RemoteException
    {
      Bundle localBundle = makeRequestBundle("CHECK_BILLING_SUPPORTED");
      if (BillingService.mService.sendBillingRequest(localBundle).getInt("RESPONSE_CODE") == Consts.ResponseCode.RESULT_OK.ordinal());
      for (boolean bool = true; ; bool = false)
      {
        ResponseHandler.checkBillingSupportedResponse(bool);
        return Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
      }
    }
  }

  class ConfirmNotifications extends BillingService.BillingRequest
  {
    final String[] mNotifyIds;

    public ConfirmNotifications(int paramArrayOfString, String[] arg3)
    {
      super(paramArrayOfString);
      Object localObject;
      this.mNotifyIds = localObject;
    }

    protected long run()
      throws RemoteException
    {
      Bundle localBundle1 = makeRequestBundle("CONFIRM_NOTIFICATIONS");
      localBundle1.putStringArray("NOTIFY_IDS", this.mNotifyIds);
      Bundle localBundle2 = BillingService.mService.sendBillingRequest(localBundle1);
      logResponseCode("confirmNotifications", localBundle2);
      return localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
    }
  }

  class GetPurchaseInformation extends BillingService.BillingRequest
  {
    long mNonce;
    final String[] mNotifyIds;

    public GetPurchaseInformation(int paramArrayOfString, String[] arg3)
    {
      super(paramArrayOfString);
      Object localObject;
      this.mNotifyIds = localObject;
    }

    protected void onRemoteException(RemoteException paramRemoteException)
    {
      super.onRemoteException(paramRemoteException);
      Security.removeNonce(this.mNonce);
    }

    protected long run()
      throws RemoteException
    {
      this.mNonce = Security.generateNonce();
      Bundle localBundle1 = makeRequestBundle("GET_PURCHASE_INFORMATION");
      localBundle1.putLong("NONCE", this.mNonce);
      localBundle1.putStringArray("NOTIFY_IDS", this.mNotifyIds);
      Bundle localBundle2 = BillingService.mService.sendBillingRequest(localBundle1);
      logResponseCode("getPurchaseInformation", localBundle2);
      return localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
    }
  }

  class RequestPurchase extends BillingService.BillingRequest
  {
    public final String mDeveloperPayload;
    public final String mProductId;

    public RequestPurchase(String arg2)
    {
      this(str, null);
    }

    public RequestPurchase(String paramString1, String arg3)
    {
      super(-1);
      this.mProductId = paramString1;
      Object localObject;
      this.mDeveloperPayload = localObject;
    }

    protected void responseCodeReceived(Consts.ResponseCode paramResponseCode)
    {
      ResponseHandler.responseCodeReceived(BillingService.this, this, paramResponseCode);
    }

    protected long run()
      throws RemoteException
    {
      Bundle localBundle1 = makeRequestBundle("REQUEST_PURCHASE");
      localBundle1.putString("ITEM_ID", this.mProductId);
      if (this.mDeveloperPayload != null)
        localBundle1.putString("DEVELOPER_PAYLOAD", this.mDeveloperPayload);
      Bundle localBundle2 = BillingService.mService.sendBillingRequest(localBundle1);
      PendingIntent localPendingIntent = (PendingIntent)localBundle2.getParcelable("PURCHASE_INTENT");
      long l;
      if (localPendingIntent == null)
      {
        Log.e("BillingService", "Error with requestPurchase");
        l = Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
      }
      while (true)
      {
        return l;
        ResponseHandler.buyPageIntentResponse(localPendingIntent, new Intent());
        l = localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
      }
    }
  }

  class RestoreTransactions extends BillingService.BillingRequest
  {
    long mNonce;

    public RestoreTransactions()
    {
      super(-1);
    }

    protected void onRemoteException(RemoteException paramRemoteException)
    {
      super.onRemoteException(paramRemoteException);
      Security.removeNonce(this.mNonce);
    }

    protected void responseCodeReceived(Consts.ResponseCode paramResponseCode)
    {
      ResponseHandler.responseCodeReceived(BillingService.this, this, paramResponseCode);
    }

    protected long run()
      throws RemoteException
    {
      this.mNonce = Security.generateNonce();
      Bundle localBundle1 = makeRequestBundle("RESTORE_TRANSACTIONS");
      localBundle1.putLong("NONCE", this.mNonce);
      Bundle localBundle2 = BillingService.mService.sendBillingRequest(localBundle1);
      logResponseCode("restoreTransactions", localBundle2);
      return localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.BillingService
 * JD-Core Version:    0.6.0
 */