package ru.humantouch.besharp.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName="lines")
public class Line
  implements Comparable<Line>
{
  public static final String FILTERED_OUT_NO = "FILTERED_OUT_NO";
  public static final String FILTERED_OUT_PARENT = "FILTERED_OUT_PARENT";
  public static final String FILTERED_OUT_YES = "FILTERED_OUT_YES";
  public static final int LINE_TYPE_AGREGATOR = 2;
  public static final int LINE_TYPE_DUMMY = 6;
  public static final int LINE_TYPE_END_OF_LIST_DUMMY = 10;
  public static final int LINE_TYPE_GOAL = 3;
  public static final int LINE_TYPE_INBOX = 7;
  public static final int LINE_TYPE_LIST = 1;
  public static final int LINE_TYPE_PROJECTS = 8;
  public static final int LINE_TYPE_RISK = 4;
  public static final int LINE_TYPE_TAG = 5;
  public static final int LINE_TYPE_TAGS_LIST = 9;
  public static final int LINE_TYPE_TASK = 0;
  public static final String PARENT_LINE_ID_FIELD_NAME = "parentLine_id";
  public static final String SORT_ORDER_FIELD_NAME = "sortOrder";

  @DatabaseField
  private Integer backgroundColor;

  @DatabaseField
  private Long calendarEventId;

  @DatabaseField
  private Integer calendarId;

  @DatabaseField
  private float cost;

  @DatabaseField
  private Date createdDate;

  @DatabaseField
  private String desc;

  @DatabaseField
  private Date dueDate;
  private Line goal = null;

  @DatabaseField(generatedId=true)
  public Integer id;
  public Boolean isChanged;

  @DatabaseField
  private Boolean isCollapsed;

  @DatabaseField
  public Boolean isDeletable;
  public String isFilteredOut;

  @DatabaseField
  public Boolean isMoveable;

  @DatabaseField
  private Boolean isStrikedOut;

  @DatabaseField
  private String lineIcon;

  @DatabaseField
  private int lineType;

  @DatabaseField(columnName="parentLine_id", foreign=true)
  private Line parentLine = null;

  @DatabaseField(columnName="sortOrder")
  private int sortOrder;

  @DatabaseField
  private Integer textColor;

  @DatabaseField
  private String type;

  public Line()
  {
    setIsCollapsed(Boolean.valueOf(false));
    this.createdDate = new Date();
    this.isMoveable = Boolean.valueOf(true);
    this.isDeletable = Boolean.valueOf(true);
    this.isFilteredOut = "FILTERED_OUT_NO";
    this.isChanged = Boolean.valueOf(false);
    this.isStrikedOut = Boolean.valueOf(false);
    this.textColor = Integer.valueOf(-1);
    this.backgroundColor = Integer.valueOf(-16777216);
    this.lineIcon = null;
    this.calendarEventId = null;
    this.calendarId = null;
    this.lineType = 0;
  }

  public int compareTo(Line paramLine)
  {
    return this.sortOrder - paramLine.sortOrder;
  }

  public Integer getBackgroundColor()
  {
    return this.backgroundColor;
  }

  public Long getCalendarEventId()
  {
    return this.calendarEventId;
  }

  public Integer getCalendarId()
  {
    return this.calendarId;
  }

  public float getCost()
  {
    return this.cost;
  }

  public String getDesc()
  {
    return this.desc;
  }

  public Date getDueDate()
  {
    return this.dueDate;
  }

  public Line getGoal()
  {
    return this.goal;
  }

  public Boolean getIsCollapsed()
  {
    return this.isCollapsed;
  }

  public Boolean getIsStrikedOut()
  {
    if (this.isStrikedOut == null);
    for (Boolean localBoolean = Boolean.valueOf(false); ; localBoolean = this.isStrikedOut)
      return localBoolean;
  }

  public String getLineIcon()
  {
    return this.lineIcon;
  }

  public int getLineType()
  {
    return this.lineType;
  }

  public Line getParentLine()
  {
    return this.parentLine;
  }

  public int getSortOrder()
  {
    return this.sortOrder;
  }

  public Integer getTextColor()
  {
    return this.textColor;
  }

  public void setBackgroundColor(Integer paramInteger)
  {
    this.backgroundColor = paramInteger;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setCalendarEventId(Long paramLong)
  {
    this.calendarEventId = paramLong;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setCalendarId(Integer paramInteger)
  {
    this.calendarId = paramInteger;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setCost(float paramFloat)
  {
    this.cost = paramFloat;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setDesc(String paramString)
  {
    if (this.desc != paramString)
    {
      this.desc = paramString;
      this.isChanged = Boolean.valueOf(true);
    }
  }

  public void setDueDate(Date paramDate)
  {
    this.dueDate = paramDate;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setGoal(Line paramLine)
  {
    this.goal = paramLine;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setIsCollapsed(Boolean paramBoolean)
  {
    this.isCollapsed = paramBoolean;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setIsStrikedOut(Boolean paramBoolean)
  {
    this.isStrikedOut = paramBoolean;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setLineIcon(String paramString)
  {
    this.lineIcon = paramString;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setLineType(int paramInt)
  {
    if (this.lineType != paramInt)
    {
      this.lineType = paramInt;
      this.isChanged = Boolean.valueOf(true);
    }
  }

  public void setParentLine(Line paramLine)
  {
    this.parentLine = paramLine;
    this.isChanged = Boolean.valueOf(true);
  }

  public void setSortOrder(int paramInt)
  {
    if (this.sortOrder != paramInt)
    {
      this.sortOrder = paramInt;
      this.isChanged = Boolean.valueOf(true);
    }
  }

  public void setTextColor(Integer paramInteger)
  {
    this.textColor = paramInteger;
    this.isChanged = Boolean.valueOf(true);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.entities.Line
 * JD-Core Version:    0.6.0
 */