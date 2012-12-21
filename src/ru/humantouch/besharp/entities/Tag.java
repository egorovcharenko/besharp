package ru.humantouch.besharp.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tags")
public class Tag
{
  public static final String NAME_FIELD_NAME = "name";

  @DatabaseField(generatedId=true)
  public int id;

  @DatabaseField(columnName="name")
  public String name;
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.entities.Tag
 * JD-Core Version:    0.6.0
 */