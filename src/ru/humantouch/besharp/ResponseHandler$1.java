package ru.humantouch.besharp;

import android.content.Context;

class ResponseHandler$1
  implements Runnable
{
  public void run()
  {
    PurchaseDatabase localPurchaseDatabase = new PurchaseDatabase(this.val$context);
    int i = localPurchaseDatabase.updatePurchase(this.val$orderId, this.val$productId, this.val$purchaseState, this.val$purchaseTime, this.val$developerPayload);
    localPurchaseDatabase.close();
    monitorenter;
    try
    {
      if (ResponseHandler.access$0() != null)
        ResponseHandler.access$0().postPurchaseStateChange(this.val$purchaseState, this.val$productId, i, this.val$purchaseTime, this.val$developerPayload);
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
 * Qualified Name:     ru.humantouch.besharp.ResponseHandler.1
 * JD-Core Version:    0.6.0
 */