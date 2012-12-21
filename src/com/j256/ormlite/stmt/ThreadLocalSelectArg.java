package com.j256.ormlite.stmt;

public class ThreadLocalSelectArg extends BaseSelectArg
  implements ArgumentHolder
{
  private ThreadLocal<ValueWrapper> threadValue = new ThreadLocal();

  protected Object getValue()
  {
    ValueWrapper localValueWrapper = (ValueWrapper)this.threadValue.get();
    if (localValueWrapper == null);
    for (Object localObject = null; ; localObject = localValueWrapper.value)
      return localObject;
  }

  protected boolean isValueSet()
  {
    if (this.threadValue.get() != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void setValue(Object paramObject)
  {
    this.threadValue.set(new ValueWrapper(paramObject));
  }

  private static class ValueWrapper
  {
    Object value;

    public ValueWrapper(Object paramObject)
    {
      this.value = paramObject;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.stmt.ThreadLocalSelectArg
 * JD-Core Version:    0.6.0
 */