package ru.humantouch.besharp;

import android.view.View;
import android.widget.ListView;

public abstract interface DragListener
{
  public abstract void onDrag(int paramInt1, int paramInt2, ListView paramListView);

  public abstract void onStartDrag(View paramView);

  public abstract void onStopDrag(View paramView);
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.DragListener
 * JD-Core Version:    0.6.0
 */