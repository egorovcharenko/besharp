package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IMarketBillingService extends IInterface
{
  public abstract Bundle sendBillingRequest(Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMarketBillingService
  {
    private static final String DESCRIPTOR = "com.android.vending.billing.IMarketBillingService";
    static final int TRANSACTION_sendBillingRequest = 1;

    public Stub()
    {
      attachInterface(this, "com.android.vending.billing.IMarketBillingService");
    }

    public static IMarketBillingService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.billing.IMarketBillingService");
        if ((localIInterface != null) && ((localIInterface instanceof IMarketBillingService)))
        {
          localObject = (IMarketBillingService)localIInterface;
          continue;
        }
        localObject = new Proxy(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default:
      case 1598968902:
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); ; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.android.vending.billing.IMarketBillingService");
        }
      case 1:
      }
      paramParcel1.enforceInterface("com.android.vending.billing.IMarketBillingService");
      Bundle localBundle1;
      if (paramParcel1.readInt() != 0)
      {
        localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        label81: Bundle localBundle2 = sendBillingRequest(localBundle1);
        paramParcel2.writeNoException();
        if (localBundle2 == null)
          break label122;
        paramParcel2.writeInt(1);
        localBundle2.writeToParcel(paramParcel2, 1);
      }
      while (true)
      {
        bool = true;
        break;
        localBundle1 = null;
        break label81;
        label122: paramParcel2.writeInt(0);
      }
    }

    private static class Proxy
      implements IMarketBillingService
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "com.android.vending.billing.IMarketBillingService";
      }

      public Bundle sendBillingRequest(Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.android.vending.billing.IMarketBillingService");
            if (paramBundle == null)
              continue;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
              return localBundle;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          Bundle localBundle = null;
        }
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.vending.billing.IMarketBillingService
 * JD-Core Version:    0.6.0
 */