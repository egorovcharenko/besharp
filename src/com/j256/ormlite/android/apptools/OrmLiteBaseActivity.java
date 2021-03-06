package com.j256.ormlite.android.apptools;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.support.ConnectionSource;

public abstract class OrmLiteBaseActivity<H extends OrmLiteSqliteOpenHelper> extends Activity
{
  private volatile boolean created = false;
  private volatile boolean destroyed = false;
  private volatile H helper;

  public ConnectionSource getConnectionSource()
  {
    return getHelper().getConnectionSource();
  }

  public H getHelper()
  {
    if (this.helper == null)
    {
      if (!this.created)
        throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
      if (this.destroyed)
        throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
      throw new IllegalStateException("Helper is null for some unknown reason");
    }
    return this.helper;
  }

  protected H getHelperInternal(Context paramContext)
  {
    return OpenHelperManager.getHelper(paramContext);
  }

  protected void onCreate(Bundle paramBundle)
  {
    if (this.helper == null)
    {
      this.helper = getHelperInternal(this);
      this.created = true;
    }
    super.onCreate(paramBundle);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    releaseHelper(this.helper);
    this.destroyed = true;
  }

  protected void releaseHelper(H paramH)
  {
    OpenHelperManager.releaseHelper();
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.android.apptools.OrmLiteBaseActivity
 * JD-Core Version:    0.6.0
 */