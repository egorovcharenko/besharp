package ru.humantouch.besharp.entities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.text.format.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Event
{
  public static final String ALL_DAY = "allDay";
  public static final String CALENDAR_ID = "calendar_id";
  public static final String CAN_INVITE_OTHERS = "canInviteOthers";
  public static final String COMMENTS_URI = "commentsUri";
  public static final String DELETED = "deleted";
  public static final String DESCRIPTION = "description";
  public static final String DTEND = "dtend";
  public static final String DTSTART = "dtstart";
  public static final String DURATION = "duration";
  public static final String EVENT_LOCATION = "eventLocation";
  public static final String EVENT_TIMEZONE = "eventTimezone";
  public static final String EXDATE = "exdate";
  public static final String EXRULE = "exrule";
  public static final String GUESTS_CAN_INVITE_OTHERS = "guestsCanInviteOthers";
  public static final String GUESTS_CAN_MODIFY = "guestsCanModify";
  public static final String GUESTS_CAN_SEE_GUESTS = "guestsCanSeeGuests";
  public static final String HAS_ALARM = "hasAlarm";
  public static final String HAS_ATTENDEE_DATA = "hasAttendeeData";
  public static final String HAS_EXTENDED_PROPERTIES = "hasExtendedProperties";
  public static final String HTML_URI = "htmlUri";
  public static final String LAST_DATE = "lastDate";
  public static final String ORGANIZER = "organizer";
  public static final String ORIGINAL_ALL_DAY = "originalAllDay";
  public static final String ORIGINAL_EVENT = "originalEvent";
  public static final String ORIGINAL_INSTANCE_TIME = "originalInstanceTime";
  public static final String OWNER_ACCOUNT = "ownerAccount";
  public static final String RDATE = "rdate";
  public static final String RRULE = "rrule";
  public static final String SELF_ATTENDEE_STATUS = "selfAttendeeStatus";
  public static final String STATUS = "eventStatus";
  public static final int STATUS_CANCELED = 2;
  public static final int STATUS_CONFIRMED = 1;
  public static final int STATUS_TENTATIVE = 0;
  public static final String SYNC_ADAPTER_DATA = "syncAdapterData";
  public static final String TITLE = "title";
  public static final String TRANSPARENCY = "transparency";
  public static final int TRANSPARENCY_OPAQUE = 0;
  public static final int TRANSPARENCY_TRANSPARENT = 1;
  public static final String VISIBILITY = "visibility";
  public static final int VISIBILITY_CONFIDENTIAL = 1;
  public static final int VISIBILITY_DEFAULT = 0;
  public static final int VISIBILITY_PRIVATE = 2;
  public static final int VISIBILITY_PUBLIC = 3;
  public long id;
  public int mAllDay;
  public String mDescription;
  public String mEndDate;
  private String mHasAlarm;
  public String mLocation;
  public String mStartDate;
  public Time mStartTime;
  private String mStatus;
  public Time mStopTime;
  public String mTitle;
  private String mTransparency;
  public String mVenueName;
  private String mVisibility;

  public static Uri createNewEventInCalendar(ContentResolver paramContentResolver, Event paramEvent, int paramInt)
  {
    Uri localUri1 = Uri.parse(getCalendarUriBase() + "/calendars");
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "displayName";
    Cursor localCursor = paramContentResolver.query(localUri1, arrayOfString1, "selected=1", null, null);
    String[] arrayOfString2;
    int[] arrayOfInt;
    int i;
    Uri localUri2;
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      arrayOfString2 = new String[localCursor.getCount()];
      arrayOfInt = new int[localCursor.getCount()];
      i = 0;
      if (i >= arrayOfString2.length)
      {
        localCursor.close();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("calendar_id", Integer.valueOf(paramInt));
        localContentValues.put("title", paramEvent.mTitle);
        localContentValues.put("allDay", Integer.valueOf(paramEvent.mAllDay));
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.roll(5, true);
        Date localDate = localCalendar.getTime();
        localContentValues.put("dtstart", Long.valueOf(new Date(localDate.getYear(), localDate.getMonth(), localDate.getDate(), 14, 0).getTime()));
        localContentValues.put("dtend", Long.valueOf(new Date(localDate.getYear(), localDate.getMonth(), localDate.getDate(), 15, 0).getTime()));
        localUri2 = paramContentResolver.insert(Uri.parse(getCalendarUriBase() + "/events"), localContentValues);
        if (localUri2 == null)
          break label345;
        paramEvent.id = Long.parseLong(localUri2.getLastPathSegment());
      }
    }
    while (true)
    {
      return localUri2;
      arrayOfInt[i] = localCursor.getInt(0);
      arrayOfString2[i] = localCursor.getString(1);
      localCursor.moveToNext();
      i++;
      break;
      label345: localUri2 = null;
    }
  }

  public static void deleteEvent(ContentResolver paramContentResolver, Long paramLong)
  {
    paramContentResolver.delete(ContentUris.withAppendedId(Uri.parse(getCalendarUriBase() + "/events"), paramLong.longValue()), null, null);
  }

  public static HashMap<Integer, String> getAllCalendars(ContentResolver paramContentResolver)
  {
    HashMap localHashMap = new HashMap();
    Uri localUri = Uri.parse(getCalendarUriBase() + "/calendars");
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "displayname";
    Cursor localCursor = paramContentResolver.query(localUri, arrayOfString, null, null, null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        return localHashMap;
      }
      localHashMap.put(Integer.valueOf(localCursor.getInt(0)), localCursor.getString(1));
    }
  }

  public static String getCalendarUriBase()
  {
    if (Integer.parseInt(Build.VERSION.SDK) >= 8);
    for (String str = "content://com.android.calendar"; ; str = "content://calendar")
      return str;
  }

  public static Event getEvent(ContentResolver paramContentResolver, Long paramLong, Integer paramInteger)
  {
    Uri localUri = Uri.parse(getCalendarUriBase() + "/events").buildUpon().build();
    String[] arrayOfString = new String[7];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "title";
    arrayOfString[2] = "allDay";
    arrayOfString[3] = "dtstart";
    arrayOfString[4] = "description";
    arrayOfString[5] = "dtend";
    arrayOfString[6] = "eventLocation";
    Cursor localCursor = paramContentResolver.query(localUri, arrayOfString, "calendar_id = " + paramInteger.toString() + " AND " + "_id = " + paramLong.toString(), null, "");
    if (!localCursor.moveToNext());
    Event localEvent;
    for (Object localObject = null; ; localObject = localEvent)
    {
      return localObject;
      if (localCursor.getLong(0) != paramLong.longValue())
        break;
      localEvent = new Event();
      localEvent.id = paramLong.longValue();
      localEvent.mTitle = localCursor.getString(1);
      localEvent.mAllDay = localCursor.getInt(2);
      localEvent.mStartDate = localCursor.getString(3);
      localEvent.mDescription = localCursor.getString(4);
      localEvent.mEndDate = localCursor.getString(5);
      localEvent.mLocation = localCursor.getString(6);
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.entities.Event
 * JD-Core Version:    0.6.0
 */