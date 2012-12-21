package ru.humantouch.besharp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class PredicateLayout extends ViewGroup
{
  private static final int horizontal_spacing = 1;
  private static final int vertical_spacing = 1;
  private int line_height;

  public PredicateLayout(Context paramContext)
  {
    super(paramContext);
  }

  public PredicateLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    int j = paramInt3 - paramInt1;
    int k = getPaddingLeft();
    int m = getPaddingTop();
    for (int n = 0; ; n++)
    {
      if (n >= i)
        return;
      View localView = getChildAt(n);
      if (localView.getVisibility() == 8)
        continue;
      int i1 = localView.getMeasuredWidth();
      int i2 = localView.getMeasuredHeight();
      if (k + i1 > j)
      {
        k = getPaddingLeft();
        m += this.line_height;
      }
      localView.layout(k, m, k + i1, m + i2);
      k += i1 + 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1) - getPaddingLeft() - getPaddingRight();
    int j = View.MeasureSpec.getSize(paramInt2) - getPaddingTop() - getPaddingBottom();
    int k = getChildCount();
    int m = 0;
    int n = getPaddingLeft();
    int i1 = getPaddingTop();
    int i2 = 0;
    if (i2 >= k)
    {
      this.line_height = m;
      if (View.MeasureSpec.getMode(paramInt2) == 0)
        j = i1 + m;
      setMeasuredDimension(i, j);
      return;
    }
    View localView = getChildAt(i2);
    if (localView.getVisibility() != 8)
      if (View.MeasureSpec.getMode(paramInt2) != -2147483648)
        break label197;
    label197: for (int i3 = View.MeasureSpec.makeMeasureSpec(j, -2147483648); ; i3 = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      localView.measure(View.MeasureSpec.makeMeasureSpec(i, -2147483648), i3);
      int i4 = localView.getMeasuredWidth();
      m = Math.max(m, 1 + localView.getMeasuredHeight());
      if (n + i4 > i)
      {
        n = getPaddingLeft();
        i1 += m;
      }
      n += i4 + 1;
      i2++;
      break;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.views.PredicateLayout
 * JD-Core Version:    0.6.0
 */