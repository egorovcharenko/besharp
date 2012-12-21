package ru.humantouch.besharp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PurchaseDatabase
{
  private static final String DATABASE_NAME = "purchase.db";
  private static final int DATABASE_VERSION = 1;
  private static final String[] HISTORY_COLUMNS;
  static final String HISTORY_DEVELOPER_PAYLOAD_COL = "developerPayload";
  static final String HISTORY_ORDER_ID_COL = "_id";
  static final String HISTORY_PRODUCT_ID_COL = "productId";
  static final String HISTORY_PURCHASE_TIME_COL = "purchaseTime";
  static final String HISTORY_STATE_COL = "state";
  private static final String[] PURCHASED_COLUMNS;
  private static final String PURCHASED_ITEMS_TABLE_NAME = "purchased";
  static final String PURCHASED_PRODUCT_ID_COL = "_id";
  static final String PURCHASED_QUANTITY_COL = "quantity";
  private static final String PURCHASE_HISTORY_TABLE_NAME = "history";
  private static final String TAG = "PurchaseDatabase";
  private DatabaseHelper mDatabaseHelper;
  private SQLiteDatabase mDb;

  static
  {
    String[] arrayOfString1 = new String[5];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "productId";
    arrayOfString1[2] = "state";
    arrayOfString1[3] = "purchaseTime";
    arrayOfString1[4] = "developerPayload";
    HISTORY_COLUMNS = arrayOfString1;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "_id";
    arrayOfString2[1] = "quantity";
    PURCHASED_COLUMNS = arrayOfString2;
  }

  public PurchaseDatabase(Context paramContext)
  {
    this.mDatabaseHelper = new DatabaseHelper(paramContext);
    this.mDb = this.mDatabaseHelper.getWritableDatabase();
  }

  private void insertOrder(String paramString1, String paramString2, Consts.PurchaseState paramPurchaseState, long paramLong, String paramString3)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_id", paramString1);
    localContentValues.put("productId", paramString2);
    localContentValues.put("state", Integer.valueOf(paramPurchaseState.ordinal()));
    localContentValues.put("purchaseTime", Long.valueOf(paramLong));
    localContentValues.put("developerPayload", paramString3);
    this.mDb.replace("history", null, localContentValues);
  }

  private void updatePurchasedItem(String paramString, int paramInt)
  {
    if (paramInt == 0)
    {
      SQLiteDatabase localSQLiteDatabase = this.mDb;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramString;
      localSQLiteDatabase.delete("purchased", "_id=?", arrayOfString);
    }
    while (true)
    {
      return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_id", paramString);
      localContentValues.put("quantity", Integer.valueOf(paramInt));
      this.mDb.replace("purchased", null, localContentValues);
    }
  }

  public void close()
  {
    this.mDatabaseHelper.close();
  }

  public Cursor queryAllPurchasedItems()
  {
    return this.mDb.query("purchased", PURCHASED_COLUMNS, null, null, null, null, null);
  }

  public int updatePurchase(String paramString1, String paramString2, Consts.PurchaseState paramPurchaseState, long paramLong, String paramString3)
  {
    monitorenter;
    try
    {
      insertOrder(paramString1, paramString2, paramPurchaseState, paramLong, paramString3);
      SQLiteDatabase localSQLiteDatabase = this.mDb;
      String[] arrayOfString1 = HISTORY_COLUMNS;
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = paramString2;
      Cursor localCursor = localSQLiteDatabase.query("history", arrayOfString1, "productId=?", arrayOfString2, null, null, null, null);
      int j;
      if (localCursor == null)
        j = 0;
      while (true)
      {
        return j;
        int i = 0;
        try
        {
          while (true)
          {
            if (!localCursor.moveToNext())
            {
              updatePurchasedItem(paramString2, i);
              if (localCursor != null)
                localCursor.close();
              j = i;
              break;
            }
            Consts.PurchaseState localPurchaseState1 = Consts.PurchaseState.valueOf(localCursor.getInt(2));
            if (localPurchaseState1 != Consts.PurchaseState.PURCHASED)
            {
              Consts.PurchaseState localPurchaseState2 = Consts.PurchaseState.REFUNDED;
              if (localPurchaseState1 != localPurchaseState2)
                continue;
            }
            i++;
          }
        }
        finally
        {
          if (localCursor != null)
            localCursor.close();
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  private class DatabaseHelper extends SQLiteOpenHelper
  {
    public DatabaseHelper(Context arg2)
    {
      super("purchase.db", null, 1);
    }

    private void createPurchaseTable(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE history(_id TEXT PRIMARY KEY, state INTEGER, productId TEXT, developerPayload TEXT, purchaseTime INTEGER)");
      paramSQLiteDatabase.execSQL("CREATE TABLE purchased(_id TEXT PRIMARY KEY, quantity INTEGER)");
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      createPurchaseTable(paramSQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt2 != 1)
      {
        Log.w("PurchaseDatabase", "Database upgrade from old: " + paramInt1 + " to: " + paramInt2);
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS purchased");
        createPurchaseTable(paramSQLiteDatabase);
      }
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.PurchaseDatabase
 * JD-Core Version:    0.6.0
 */