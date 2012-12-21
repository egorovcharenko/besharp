package ru.humantouch.besharp.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tag_line_intersecions")
public class LineTagIntersection
{
  public static final String LINE_ID_FIELD_NAME = "line_id";
  public static final String TAG_ID_FIELD_NAME = "tag_id";

  @DatabaseField(generatedId=true)
  public Integer id;

  @DatabaseField(columnName="line_id", foreign=true)
  public Line line;

  @DatabaseField(columnName="tag_id", foreign=true)
  public Tag tag;

  public LineTagIntersection()
  {
  }

  public LineTagIntersection(int paramInt1, int paramInt2)
  {
    this.tag = new Tag();
    this.tag.id = paramInt2;
    this.line = new Line();
    this.line.id = Integer.valueOf(paramInt1);
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.entities.LineTagIntersection
 * JD-Core Version:    0.6.0
 */