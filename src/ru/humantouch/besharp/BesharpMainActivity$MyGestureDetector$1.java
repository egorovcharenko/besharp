package ru.humantouch.besharp;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.MotionEvent;
import android.view.View;
import ru.humantouch.besharp.entities.Line;

class BesharpMainActivity$MyGestureDetector$1
  implements Runnable
{
  public void run()
  {
    try
    {
      View localView = BesharpMainActivity.access$0(BesharpMainActivity.MyGestureDetector.access$3(this.this$1), this.val$e1.getX(), this.val$e1.getY(), BesharpMainActivity.MyGestureDetector.access$3(this.this$1).linesListView, "", "");
      BesharpMainActivity.MyGestureDetector.access$0(this.this$1, BesharpMainActivity.access$1(BesharpMainActivity.MyGestureDetector.access$3(this.this$1), localView));
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(BesharpMainActivity.MyGestureDetector.access$1(this.this$1));
      if (!BesharpMainActivity.MyGestureDetector.access$2(this.this$1).getIsStrikedOut().booleanValue());
      for (String str = "done"; ; str = "undone")
      {
        localBuilder.setMessage("Mark \"" + BesharpMainActivity.MyGestureDetector.access$2(this.this$1).getDesc() + "\" as " + str + "?").setCancelable(false).setPositiveButton("Mark as " + str, new BesharpMainActivity.MyGestureDetector.1.1(this, str)).setNegativeButton("Cancel", new BesharpMainActivity.MyGestureDetector.1.2(this));
        localBuilder.create().show();
        break;
      }
    }
    catch (Exception localException)
    {
      BesharpMainActivity.access$3(BesharpMainActivity.MyGestureDetector.access$3(this.this$1), localException.toString());
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.BesharpMainActivity.MyGestureDetector.1
 * JD-Core Version:    0.6.0
 */