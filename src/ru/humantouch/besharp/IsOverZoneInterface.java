package ru.humantouch.besharp;

public abstract interface IsOverZoneInterface
{
  public static final String ZONE_DROP_AFTER = "drag_drop_after";
  public static final String ZONE_DROP_BEFORE = "drag_drop_before";
  public static final String ZONE_DROP_DECREASE_INDENT = "drag_drop_left";
  public static final String ZONE_DROP_FIRST_CHILD = "drag_drop_first_child";
  public static final String ZONE_START_DRAG = "start_drag";

  public abstract Boolean isOverZone(int paramInt1, int paramInt2, String paramString);
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.IsOverZoneInterface
 * JD-Core Version:    0.6.0
 */