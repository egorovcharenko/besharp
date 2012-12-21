package com.airpush.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CustomWebView extends WebView
{
  private ProgressDialog a = null;

  public CustomWebView(Context paramContext)
  {
    super(paramContext);
    clearCache(true);
    clearFormData();
    clearHistory();
    getSettings().setJavaScriptEnabled(true);
    getSettings().setUserAgentString(null);
    requestFocus(130);
    this.a = new ProgressDialog(paramContext);
    this.a.setMessage("Loading...");
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (canGoBack()))
      goBack();
    for (boolean bool = true; ; bool = super.onKeyDown(paramInt, paramKeyEvent))
      return bool;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.airpush.android.CustomWebView
 * JD-Core Version:    0.6.0
 */