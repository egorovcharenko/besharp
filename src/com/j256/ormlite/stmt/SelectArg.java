package com.j256.ormlite.stmt;

public class SelectArg extends BaseSelectArg
  implements ArgumentHolder
{
  private boolean hasBeenSet = false;
  private Object value = null;

  protected Object getValue()
  {
    return this.value;
  }

  protected boolean isValueSet()
  {
    return this.hasBeenSet;
  }

  public void setValue(Object paramObject)
  {
    this.hasBeenSet = true;
    this.value = paramObject;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.SelectArg
 * JD-Core Version:    0.6.0
 */