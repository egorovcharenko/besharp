package com.airpush.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class e
  implements DialogInterface.OnClickListener
{
  e(PushAds paramPushAds)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    PushAds.a(this.a, false);
    PushAds.b(this.a, true);
    PushAds.a(this.a);
    this.a.finish();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.e
 * JD-Core Version:    0.6.0
 */