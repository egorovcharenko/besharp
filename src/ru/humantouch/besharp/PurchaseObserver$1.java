package ru.humantouch.besharp;

class PurchaseObserver$1
  implements Runnable
{
  public void run()
  {
    this.this$0.onPurchaseStateChange(this.val$purchaseState, this.val$itemId, this.val$quantity, this.val$purchaseTime, this.val$developerPayload);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.PurchaseObserver.1
 * JD-Core Version:    0.6.0
 */