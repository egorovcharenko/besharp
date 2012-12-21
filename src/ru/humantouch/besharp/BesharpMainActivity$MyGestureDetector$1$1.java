package ru.humantouch.besharp;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import ru.humantouch.besharp.entities.Line;

class BesharpMainActivity$MyGestureDetector$1$1
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Line localLine = BesharpMainActivity.MyGestureDetector.access$2(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2));
    if (BesharpMainActivity.MyGestureDetector.access$2(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2)).getIsStrikedOut().booleanValue());
    for (boolean bool = false; ; bool = true)
    {
      localLine.setIsStrikedOut(Boolean.valueOf(bool));
      if (BesharpMainActivity.MyGestureDetector.access$2(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2)).getCalendarEventId() != null)
        BesharpMainActivity.MyGestureDetector.access$3(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2)).deleteEventWithConfirmation(BesharpMainActivity.MyGestureDetector.access$2(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2)));
      BesharpMainActivity.access$2(BesharpMainActivity.MyGestureDetector.access$3(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2))).notifyDataSetChanged();
      Toast.makeText(BesharpMainActivity.MyGestureDetector.access$3(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2)), "Task \"" + BesharpMainActivity.MyGestureDetector.access$2(BesharpMainActivity.MyGestureDetector.1.access$0(this.this$2)).getDesc() + "\" " + this.val$doneUndoneString + "!", 0).show();
      return;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.BesharpMainActivity.MyGestureDetector.1.1
 * JD-Core Version:    0.6.0
 */