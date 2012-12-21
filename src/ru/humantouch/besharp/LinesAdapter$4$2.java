package ru.humantouch.besharp;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class LinesAdapter$4$2
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (!LinesAdapter.access$0(LinesAdapter.4.access$0(this.this$1)).mBillingService.checkBillingSupported())
      LinesAdapter.access$0(LinesAdapter.4.access$0(this.this$1)).showDialog(2);
    while (true)
    {
      return;
      if (!LinesAdapter.access$0(LinesAdapter.4.access$0(this.this$1)).mBillingService.requestPurchase("calendar_integration", null))
      {
        LinesAdapter.access$0(LinesAdapter.4.access$0(this.this$1)).showDialog(3);
        continue;
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.LinesAdapter.4.2
 * JD-Core Version:    0.6.0
 */