package ru.humantouch.besharp.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import ru.humantouch.besharp.BesharpMainActivity;
import ru.humantouch.besharp.DragListener;
import ru.humantouch.besharp.DropListener;
import ru.humantouch.besharp.IsOverZoneInterface;

public class LinesListView extends ListView
{
  private IsOverZoneInterface isOverZoneChecker;
  public BesharpMainActivity mBesharpMainActivity;
  private DragListener mDragListener;
  private boolean mDragMode;
  int mDragPointOffset;
  ImageView mDragView;
  private DropListener mDropListener;
  int mEndPosition;
  GestureDetector mGestureDetector;
  int mStartPosition;

  public LinesListView(Context paramContext)
  {
    super(paramContext);
  }

  public LinesListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public LinesListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private Bitmap adjustOpacity(Bitmap paramBitmap, int paramInt)
  {
    if (paramBitmap.isMutable());
    for (Bitmap localBitmap = paramBitmap; ; localBitmap = paramBitmap.copy(Bitmap.Config.ARGB_8888, true))
    {
      new Canvas(localBitmap).drawColor((paramInt & 0xFF) << 24, PorterDuff.Mode.DST_IN);
      return localBitmap;
    }
  }

  private void drag(int paramInt1, int paramInt2)
  {
    WindowManager.LayoutParams localLayoutParams = (WindowManager.LayoutParams)this.mDragView.getLayoutParams();
    localLayoutParams.x = (paramInt1 + BesharpMainActivity.DRAG_OFFSET_X);
    localLayoutParams.y = (paramInt2 - this.mDragPointOffset + BesharpMainActivity.DRAG_OFFSET_Y);
    ((WindowManager)getContext().getSystemService("window")).updateViewLayout(this.mDragView, localLayoutParams);
    if (this.mDragListener != null)
      this.mDragListener.onDrag(paramInt1, paramInt2, null);
  }

  private void startDrag(int paramInt1, int paramInt2)
  {
    stopDrag(paramInt1);
    View localView = getChildAt(paramInt1);
    if (localView == null);
    while (true)
    {
      return;
      localView.setDrawingCacheEnabled(true);
      if (this.mDragListener != null)
        this.mDragListener.onStartDrag(localView);
      Bitmap localBitmap = Bitmap.createBitmap(localView.getDrawingCache());
      WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
      localLayoutParams.gravity = 48;
      localLayoutParams.x = 0;
      localLayoutParams.y = (paramInt2 - this.mDragPointOffset);
      localLayoutParams.height = -2;
      localLayoutParams.width = -2;
      localLayoutParams.flags = 920;
      localLayoutParams.format = -3;
      localLayoutParams.windowAnimations = 0;
      Context localContext = getContext();
      ImageView localImageView = new ImageView(localContext);
      localImageView.setImageBitmap(localBitmap);
      localImageView.setAlpha(125);
      ((WindowManager)localContext.getSystemService("window")).addView(localImageView, localLayoutParams);
      this.mDragView = localImageView;
    }
  }

  private void stopDrag(int paramInt)
  {
    if (this.mDragView != null)
    {
      if (this.mDragListener != null)
        this.mDragListener.onStopDrag(getChildAt(paramInt));
      this.mDragView.setVisibility(8);
      ((WindowManager)getContext().getSystemService("window")).removeView(this.mDragView);
      this.mDragView.setImageDrawable(null);
      this.mDragView = null;
    }
  }

  public IsOverZoneInterface getIsOverZoneChecker()
  {
    return this.isOverZoneChecker;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    int j = (int)paramMotionEvent.getX();
    int k = (int)paramMotionEvent.getY();
    if ((this.mBesharpMainActivity != null) && (this.mBesharpMainActivity.mReorderMode) && (i == 0) && (this.isOverZoneChecker.isOverZone(j, k, "start_drag").booleanValue()))
      this.mDragMode = true;
    boolean bool;
    if (!this.mDragMode)
    {
      bool = super.onTouchEvent(paramMotionEvent);
      return bool;
    }
    switch (i)
    {
    case 1:
    default:
      this.mDragMode = false;
      this.mEndPosition = pointToPosition(j, k);
      stopDrag(this.mStartPosition - getFirstVisiblePosition());
      if ((this.mDropListener == null) || (this.mStartPosition == -1) || (this.mEndPosition == -1))
        break;
      this.mDropListener.onDrop(this.mStartPosition, this.mEndPosition);
    case 0:
    case 2:
    }
    while (true)
    {
      bool = true;
      break;
      this.mStartPosition = pointToPosition(j, k);
      if (this.mStartPosition == -1)
        continue;
      int m = this.mStartPosition - getFirstVisiblePosition();
      this.mDragPointOffset = (k - getChildAt(m).getTop());
      this.mDragPointOffset -= (int)paramMotionEvent.getRawY() - k;
      startDrag(m, k);
      drag(j, k);
      continue;
      drag(j, k);
    }
  }

  public void setDragListener(DragListener paramDragListener)
  {
    this.mDragListener = paramDragListener;
  }

  public void setDropListener(DropListener paramDropListener)
  {
    this.mDropListener = paramDropListener;
  }

  public void setIsOverZoneChecker(IsOverZoneInterface paramIsOverZoneInterface)
  {
    this.isOverZoneChecker = paramIsOverZoneInterface;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.LinesListView
 * JD-Core Version:    0.6.0
 */