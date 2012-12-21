package ru.humantouch.besharp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OpenHelperManager.SqliteOpenHelperFactory;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.humantouch.besharp.DAO.DatabaseHelper;
import ru.humantouch.besharp.DAO.LineDaoImpl;
import ru.humantouch.besharp.DAO.LineTagInterDaoImpl;
import ru.humantouch.besharp.DAO.TagDaoImpl;
import ru.humantouch.besharp.entities.Event;
import ru.humantouch.besharp.entities.Line;
import ru.humantouch.besharp.entities.Tag;
import ru.humantouch.besharp.views.CalendarSelectActivity;
import ru.humantouch.besharp.views.IconSelectActivity;
import ru.humantouch.besharp.views.LinesListView;
import ru.humantouch.besharp.views.TagSearchActivity;
import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;

public class BesharpMainActivity extends ListActivity
  implements IsOverZoneInterface
{
  public static final String ACTIVITY_PARAMETER_ROOT_LINE_ID = "ACTIVITY_PARAMETER_ROOT_LINE_ID";
  public static final int ACTIVITY_RESULT_CREATE_EVENT = 4;
  public static final int ACTIVITY_RESULT_EDIT_EVENT = 5;
  public static final int ACTIVITY_RESULT_PICK_ICON_FOR_LINE = 3;
  public static final int ACTIVITY_RESULT_PICK_TAG = 1;
  public static final int ACTIVITY_RESULT_PICK_TAG_FOR_FILTER = 2;
  public static final int ACTIVITY_RESULT_SELECT_CALENDAR = 6;
  private static final String BUNDLE_SCROLL_INDEX = "BUNDLE_SCROLL_INDEX";
  private static final String BUNDLE_SCROLL_TOP = "BUNDLE_SCROLL_TOP";
  private static final String BUNDLE_SEARCH_MODE = "BUNDLE_SEARCH_MODE";
  private static final String BUNDLE_SEARCH_STRING = "BUNDLE_SEARCH_STRING";
  private static final String BUNDLE_SEARCH_TAG = "BUNDLE_SEARCH_TAG";
  private static final int CONTEXT_MENU_CHOOSE_BACKGROUND_COLOR = 6;
  private static final int CONTEXT_MENU_CHOOSE_ICON = 7;
  private static final int CONTEXT_MENU_CHOOSE_TEXT_COLOR = 5;
  private static final int CONTEXT_MENU_DELETE = 4;
  private static final int CONTEXT_MENU_EDIT = 8;
  private static final int CONTEXT_MENU_FOCUS = 3;
  private static final int CONTEXT_MENU_MAKE_LIST = 1;
  private static final int CONTEXT_MENU_MAKE_TASK = 2;
  private static final String DB_INITIALIZED = "db_initialized";
  public static final int DIALOG_BILLING_NOT_SUPPORTED_ID = 3;
  public static final int DIALOG_CANNOT_CONNECT_ID = 2;
  public static final int DIALOG_CONTEXT_MENU_ID = 4;
  public static final int DIALOG_DATE = 1;
  public static int DRAG_OFFSET_X = 0;
  public static int DRAG_OFFSET_Y = 0;
  private static final int MENU_CHANGE_DEFAULT_CALENDAR = 6;
  private static final int MENU_REORDER = 1;
  private static final int MENU_SEARCH = 2;
  private static final int MENU_SEARCH_BY_TAG = 3;
  private static final int MENU_SHOW_DONE = 4;
  public static final int MILLIS_PER_DAY = 86400000;
  public static final String PREFERENCE_CALENDAR_ENABLED = "PREFERENCE_CALENDAR_ENABLED";
  public static final String PREFERENCE_CALENDAR_ID = "PREFERENCE_CALENDAR_ID";
  public static final String PREFERENCE_EASY_COLLAPSING = "PREFERENCE_EASY_COLLAPSING";
  public static final String PRODUCT_CALENDAR_INTEGRATION = "calendar_integration";
  public static final int PURCHASE_NOTIFICATION_ID = 1;
  public static final String RETURN_ARGUMENT_CALENDAR_ID = "RETURN_ARGUMENT_CALENDAR_ID";
  public static final String RETURN_ARGUMENT_ICON_NAME = "RETURN_ARGUMENT_ICON_NAME";
  public static final String RETURN_ARGUMENT_TAG_ID = "RETURN_ARGUMENT_TAG_ID";
  public static final String SHARED_PREFERENCES_NAME = "ru.humantouch.besharp";
  private static final int SWIPE_MAX_OFF_PATH = 20;
  static LineDaoImpl mLinesDao;
  private final String LOG_TAG = getClass().getSimpleName();
  private int REL_SWIPE_MAX_OFF_PATH;
  private int REL_SWIPE_MIN_DISTANCE;
  private int REL_SWIPE_THRESHOLD_VELOCITY;
  private DatabaseHelper dbHelper;
  private GestureDetector gestureDetector;
  View.OnTouchListener gestureListener;
  private AdapterView.OnItemLongClickListener itemLongClickHandler = new AdapterView.OnItemLongClickListener()
  {
    public boolean onItemLongClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      BesharpMainActivity.this.showDialog(4);
      return true;
    }
  };
  LinesListView linesListView;
  BillingService mBillingService;
  public boolean mCalendarEnabled;
  int mCalendarId;
  public DatePickerDialog mDateSelectDialog;
  private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
  {
    public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
    {
      BesharpMainActivity.this.mLinesAdapter.findById(BesharpMainActivity.this.mLinesAdapter.mSelectedHolder.lineId).setDueDate(new Date(paramInt1 - 1900, paramInt2, paramInt3));
      BesharpMainActivity.this.mLinesAdapter.saveCurrentlyVisibleLineInMemory();
      BesharpMainActivity.this.mLinesAdapter.notifyDataSetChanged();
      Date localDate = new Date();
      int i = 1900 + localDate.getYear();
      int j = localDate.getMonth();
      int k = localDate.getDate();
      if (BesharpMainActivity.this.mDateSelectDialog != null)
        BesharpMainActivity.this.mDateSelectDialog.updateDate(i, j, k);
    }
  };
  private DragListener mDragListener = new DragListener()
  {
    public void onDrag(int paramInt1, int paramInt2, ListView paramListView)
    {
      View localView;
      String str;
      try
      {
        BesharpMainActivity.this.logInfo("onDrag, start finding view, x=" + paramInt1 + ",y=" + paramInt2);
        localView = BesharpMainActivity.this.findViewBy(paramInt1, paramInt2, BesharpMainActivity.this.linesListView, "drag_drop", "- ");
        BesharpMainActivity.this.logInfo("onDrag, finish finding view, x=" + paramInt1 + ",y=" + paramInt2);
        if (localView == null)
          return;
        str = localView.getTag().toString();
        if (str.equals("drag_drop_after"))
        {
          Line localLine4 = BesharpMainActivity.this.findLineInHolderUpInHierarchy(localView);
          if (!BesharpMainActivity.this.mLinesAdapter.isChildOrSelfLine(BesharpMainActivity.this.mDraggedLine, localLine4))
            BesharpMainActivity.this.mLinesAdapter.moveLineAfter(BesharpMainActivity.this.mDraggedLine, localLine4);
          BesharpMainActivity.this.mDraggedLine = BesharpMainActivity.this.mLinesAdapter.findById(BesharpMainActivity.this.mDraggedLine.id);
          return;
        }
        if (str.equals("drag_drop_before"))
        {
          Line localLine3 = BesharpMainActivity.this.findLineInHolderUpInHierarchy(localView);
          if (!BesharpMainActivity.this.mLinesAdapter.isChildOrSelfLine(BesharpMainActivity.this.mDraggedLine, localLine3))
            BesharpMainActivity.this.mLinesAdapter.moveLineBefore(BesharpMainActivity.this.mDraggedLine, localLine3);
          BesharpMainActivity.this.mDraggedLine = BesharpMainActivity.this.mLinesAdapter.findById(BesharpMainActivity.this.mDraggedLine.id);
        }
      }
      catch (Exception localException)
      {
        BesharpMainActivity.this.showToast(localException.toString());
        localException.printStackTrace();
      }
      if (str.equals("drag_drop_first_child"))
      {
        Line localLine2 = BesharpMainActivity.this.findLineInHolderUpInHierarchy(localView);
        if (!BesharpMainActivity.this.mLinesAdapter.isChildOrSelfLine(BesharpMainActivity.this.mDraggedLine, localLine2))
          BesharpMainActivity.this.mLinesAdapter.moveLineFirstChild(BesharpMainActivity.this.mDraggedLine, localLine2);
        BesharpMainActivity.this.mDraggedLine = BesharpMainActivity.this.mLinesAdapter.findById(BesharpMainActivity.this.mDraggedLine.id);
      }
      else if (str.equals("drag_drop_left"))
      {
        Line localLine1 = BesharpMainActivity.this.findLineInHolderUpInHierarchy(localView);
        if (!BesharpMainActivity.this.mLinesAdapter.isChildLine(BesharpMainActivity.this.mDraggedLine, localLine1))
          BesharpMainActivity.this.mLinesAdapter.moveLineDecreaseIndent(BesharpMainActivity.this.mDraggedLine, localLine1);
        BesharpMainActivity.this.mDraggedLine = BesharpMainActivity.this.mLinesAdapter.findById(BesharpMainActivity.this.mDraggedLine.id);
      }
    }

    public void onStartDrag(View paramView)
    {
      try
      {
        BesharpMainActivity.this.mDraggedLine = BesharpMainActivity.this.findLineInHolderUpInHierarchy(paramView);
        BesharpMainActivity.this.selectedState = false;
        BesharpMainActivity.this.mLinesAdapter.notifyDataSetChanged();
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          BesharpMainActivity.this.showToast(localException.toString());
          localException.printStackTrace();
        }
      }
    }

    public void onStopDrag(View paramView)
    {
      BesharpMainActivity.this.mDraggedLine = null;
      BesharpMainActivity.this.mLinesAdapter.notifyDataSetChanged();
    }
  };
  public Line mDraggedLine;
  private DropListener mDropListener = new DropListener()
  {
    public void onDrop(int paramInt1, int paramInt2)
    {
      ListAdapter localListAdapter = BesharpMainActivity.this.getListAdapter();
      if ((localListAdapter instanceof LinesAdapter))
        ((LinesAdapter)localListAdapter).onDrop(paramInt1, paramInt2);
    }
  };
  private boolean mEasyCollapsing;
  private TextView mFilteredTagText;
  private boolean mFirstTimeKludge = true;
  public boolean mFlingState = false;
  private Handler mHandler;
  HashMap<String, Integer> mIcons;
  public Line mLineForStyleSet;
  private Integer mLineForStyleSetId;
  private HashMap<Integer, Line> mLines;
  private LinesAdapter mLinesAdapter;
  private PurchaseDatabase mPurchaseDatabase;
  private Notification mPurchaseNotification;
  private BesharpPurchaseObserver mPurchaseObserver;
  public boolean mReorderMode;
  public EditText mSearchBox;
  private LinearLayout mSearchBoxLayout;
  private Button mSearchCancel;
  private View mSearchDividerLine;
  boolean mSearchMode;
  private String mSearchString;
  private Tag mSearchTag;
  public boolean mShowStrikedOut;
  private LinearLayout mTagSearchLayout;
  Line root;
  private int scrollIndex;
  private int scrollTop;
  private int selectedLineId;
  boolean selectedState;

  static
  {
    OpenHelperManager.setOpenHelperFactory(new OpenHelperManager.SqliteOpenHelperFactory()
    {
      public OrmLiteSqliteOpenHelper getHelper(Context paramContext)
      {
        return new DatabaseHelper(paramContext);
      }
    });
  }

  private Dialog createDialog(String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramString1).setIcon(17301642).setMessage(paramString2).setCancelable(false).setPositiveButton(17039370, null);
    return localBuilder.create();
  }

  private void doInitializeOwnedItems()
  {
    Cursor localCursor = this.mPurchaseDatabase.queryAllPurchasedItems();
    if (localCursor == null);
    while (true)
    {
      return;
      HashSet localHashSet = new HashSet();
      try
      {
        int i = localCursor.getColumnIndexOrThrow("_id");
        while (true)
        {
          boolean bool = localCursor.moveToNext();
          if (!bool)
          {
            localCursor.close();
            this.mHandler.post(new Runnable(localHashSet)
            {
              public void run()
              {
                if (this.val$ownedItems.contains("calendar_integration"))
                  BesharpMainActivity.this.setCalendarIntegrationEnabled(Boolean.valueOf(true));
              }
            });
            this.mCalendarEnabled = true;
            break;
          }
          localHashSet.add(localCursor.getString(i));
        }
      }
      finally
      {
        localCursor.close();
      }
    }
    throw localObject;
  }

  private void enterSearchMode()
  {
    this.mSearchBoxLayout.setVisibility(0);
    this.mSearchDividerLine.setVisibility(0);
    this.mTagSearchLayout.setVisibility(0);
    updateListViewHeight();
    this.mLinesAdapter.setFilteredTag(this.mSearchTag);
    this.mSearchBox.setText(this.mSearchString);
    if (this.mSearchTag != null)
      this.mFilteredTagText.setText(this.mSearchTag.name);
    while (true)
    {
      ((InputMethodManager)getSystemService("input_method")).showSoftInput(this.mSearchBox, 1);
      return;
      this.mFilteredTagText.setText(" - ");
    }
  }

  private void exitSearchMode()
  {
    this.mSearchMode = false;
    this.mSearchBoxLayout.setVisibility(8);
    this.mSearchDividerLine.setVisibility(8);
    this.mTagSearchLayout.setVisibility(8);
    updateListViewHeight();
    this.mSearchString = "";
    this.mSearchTag = null;
    this.mLinesAdapter.setFilter("");
    this.mLinesAdapter.setFilteredTag(null);
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.mSearchBoxLayout.getWindowToken(), 0);
  }

  private void fillLinesWithTempData()
    throws Exception
  {
    Line localLine1 = new Line();
    localLine1.setLineType(7);
    localLine1.setDesc("Inbox");
    localLine1.setLineIcon("ic_menu_white_letter_box");
    localLine1.isMoveable = Boolean.valueOf(false);
    localLine1.isDeletable = Boolean.valueOf(false);
    mLinesDao.create(localLine1);
    Line localLine2 = new Line();
    localLine2.setLineType(8);
    localLine2.setDesc("Projects");
    localLine2.setLineIcon("ic_menu_white_gear");
    localLine2.isMoveable = Boolean.valueOf(false);
    localLine2.isDeletable = Boolean.valueOf(false);
    mLinesDao.create(localLine2);
    Line localLine3 = new Line();
    localLine3.setLineType(0);
    localLine3.setDesc("Tutorial");
    localLine3.isMoveable = Boolean.valueOf(true);
    localLine3.isDeletable = Boolean.valueOf(true);
    localLine3.setParentLine(localLine2);
    mLinesDao.create(localLine3);
    String[] arrayOfString1 = new String[4];
    arrayOfString1[0] = "Inbox";
    arrayOfString1[1] = "*'Inbox' is a place where you stack all your incoming tasks";
    arrayOfString1[2] = "*Create task in 'Inbox' using down-right arrow on the right side";
    arrayOfString1[3] = "*Later, you can move tasks to 'Projects' folder";
    String[] arrayOfString2 = new String[34];
    arrayOfString2[0] = "Lines (Tasks)";
    arrayOfString2[1] = "*Each line is a task";
    arrayOfString2[2] = "*But you can actually store any information within lines";
    arrayOfString2[3] = "*Use hierarchy to represent strucutre of your data";
    arrayOfString2[4] = "Operations";
    arrayOfString2[5] = "*Touch line to open details";
    arrayOfString2[6] = "*Swipe line horizontaly to complete it";
    arrayOfString2[7] = "*Create new line below by touching down arrow below";
    arrayOfString2[8] = "*Create first child of line by touching down-right arrow";
    arrayOfString2[9] = "*Create new line above by touching up arrow";
    arrayOfString2[10] = "*Delete task by touching 'Х' below";
    arrayOfString2[11] = "*Return by pressing 'Back' button";
    arrayOfString2[12] = "Tags";
    arrayOfString2[13] = "*Each line can have many tags linked to it";
    arrayOfString2[14] = "*Exemples of tags include:";
    arrayOfString2[15] = "*Place where you need to accomplish task";
    arrayOfString2[16] = "*Person to whom to speak";
    arrayOfString2[17] = "*Specific condition in which to act";
    arrayOfString2[18] = "*Or anythins else, it's up to you";
    arrayOfString2[19] = "*Add tag by touching 'Add Tag' button";
    arrayOfString2[20] = "*In opened window you can select existing tag or enter new one";
    arrayOfString2[21] = "*Remove tag by pressing 'X' next to it";
    arrayOfString2[22] = "Reorder";
    arrayOfString2[23] = "*You can reorder lines by selecting 'Reorder' from menu";
    arrayOfString2[24] = "*Then drag lines anyway you like, holding by marker to the left";
    arrayOfString2[25] = "*Exit reorder mode by pressing 'Back' button";
    arrayOfString2[26] = "Search";
    arrayOfString2[27] = "*You can search lines by any text";
    arrayOfString2[28] = "*Just select 'Search' from options menu";
    arrayOfString2[29] = "*Also, you can see any task with specific tag";
    arrayOfString2[30] = "*To do it, select 'Search By Tag' in options menu";
    arrayOfString2[31] = "*Exit search mode by pressing 'Back' button";
    arrayOfString2[32] = "Now, if you like, you can delete this tutorial";
    arrayOfString2[33] = "*Do it by long-press on 'Tutorial' line and select 'Delete'";
    Object localObject = null;
    List localList = this.dbHelper.getTagDataDao().queryForAll();
    int i = localList.size();
    int j = arrayOfString1.length;
    int k = 0;
    int n;
    if (k >= j)
    {
      int m = arrayOfString2.length;
      n = 0;
      if (n >= m)
        return;
    }
    else
    {
      String str1 = arrayOfString1[k];
      Line localLine4 = new Line();
      localLine4.setDesc(str1);
      if (localLine4.getDesc().startsWith("*"))
      {
        localLine4.setDesc(localLine4.getDesc().replace("*", ""));
        localLine4.setParentLine((Line)localObject);
      }
      while (true)
      {
        mLinesDao.create(localLine4);
        k++;
        break;
        localObject = localLine4;
        localLine4.setParentLine(localLine1);
      }
    }
    String str2 = arrayOfString2[n];
    Line localLine5 = new Line();
    localLine5.setDesc(str2);
    if (localLine5.getDesc().startsWith("*"))
    {
      localLine5.setDesc(localLine5.getDesc().replace("*", ""));
      localLine5.setParentLine((Line)localObject);
      label676: mLinesDao.create(localLine5);
    }
    while (true)
    {
      if (Math.random() <= 0.2D)
      {
        n++;
        break;
        localObject = localLine5;
        localLine5.setParentLine(localLine3);
        break label676;
      }
      Tag localTag = (Tag)localList.get((int)(Math.random() * i % (i - 1)));
      this.dbHelper.getLineTagInterDataDao().addTagToLine(localLine5, localTag);
    }
  }

  private void fillTagsWithTempData()
    throws Exception
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "Work";
    arrayOfString[1] = "Home";
    arrayOfString[2] = "Shop";
    arrayOfString[3] = "Call";
    arrayOfString[4] = "Concentration";
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString[j];
      Tag localTag = new Tag();
      localTag.name = str;
      this.dbHelper.getTagDataDao().create(localTag);
    }
  }

  private Line findLineInHolderUpInHierarchy(View paramView)
    throws SQLException
  {
    View localView = paramView;
    Integer localInteger = null;
    Line localLine = null;
    while (true)
    {
      if (localView == null);
      while (true)
      {
        if (localInteger != null)
          localLine = this.mLinesAdapter.findById(localInteger);
        return localLine;
        Object localObject = localView.getTag();
        if (!(localObject instanceof LinesAdapter.ViewHolder))
          break;
        localInteger = ((LinesAdapter.ViewHolder)localObject).lineId;
      }
      localView = (View)localView.getParent();
    }
  }

  private View findViewBy(float paramFloat1, float paramFloat2, View paramView, String paramString1, String paramString2)
  {
    Rect localRect = new Rect();
    logInfo(paramString2 + "findViewBy, x=" + paramFloat1 + ", y=" + paramFloat2 + ", viewTagPrefix=" + paramString1);
    int j;
    label122: Object localObject2;
    if ((paramView instanceof ViewGroup))
    {
      int i = ((ViewGroup)paramView).getChildCount();
      logInfo(paramString2 + "ViewGroup, " + i + " children");
      j = i - 1;
      if (j < 0)
        localObject2 = null;
    }
    while (true)
    {
      return localObject2;
      View localView1 = ((ViewGroup)paramView).getChildAt(j);
      localView1.getHitRect(localRect);
      logInfo(paramString2 + j + " child (" + localView1.toString() + "), rect = " + localRect.toShortString());
      if (localRect.contains((int)paramFloat1, (int)paramFloat2))
      {
        float f1 = paramFloat1 - localView1.getLeft();
        float f2 = paramFloat2 - localView1.getTop();
        logInfo(paramString2 + "hit, child.getLeft()=" + localView1.getLeft() + ", child.getTop()=" + localView1.getTop() + ", view.getLeft()=" + paramView.getLeft() + ", view.getTop()=" + paramView.getTop());
        View localView2 = findViewBy(f1, f2, localView1, paramString1, "-" + paramString2);
        if (localView2 != null)
        {
          logInfo(paramString2 + "found!!!");
          localObject2 = localView2;
          continue;
        }
        logInfo(paramString2 + " not found, try the next view");
      }
      j--;
      break;
      paramView.getHitRect(localRect);
      logInfo(paramString2 + "ordinary view, " + ", rect = " + localRect.toShortString());
      Object localObject1 = paramView.getTag();
      if (localObject1 == null)
        break label122;
      logInfo(paramString2 + "tag found, " + ", tag= " + localObject1.toString());
      if ((!localObject1.toString().startsWith(paramString1)) || (!localRect.contains((int)paramFloat1 + paramView.getLeft(), (int)paramFloat2 + paramView.getTop())))
        break label122;
      logInfo(paramString2 + "hit!");
      localObject2 = paramView;
    }
  }

  private void initializeOwnedItems()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        BesharpMainActivity.this.doInitializeOwnedItems();
      }
    }).start();
  }

  private void restoreDatabase()
  {
    if (!getPreferences(0).getBoolean("db_initialized", false))
    {
      this.mBillingService.restoreTransactions();
      showPurchaseNotification("BeSharp purchase", "Restoring your purchases, please wait");
    }
  }

  private void saveLines()
  {
    try
    {
      this.mLinesAdapter.saveLines();
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        showToast("Unable to save data: " + localException.toString());
        localException.printStackTrace();
      }
    }
  }

  private void saveScrollState()
  {
    this.scrollIndex = getListView().getFirstVisiblePosition();
    View localView = getListView().getChildAt(0);
    if (localView == null);
    for (int i = 0; ; i = localView.getTop())
    {
      this.scrollTop = i;
      return;
    }
  }

  private void selectLine(int paramInt)
  {
    while (true)
    {
      Line localLine;
      try
      {
        if (!this.mFlingState)
          continue;
        this.mFlingState = false;
        break;
        this.mLinesAdapter.saveCurrentlyVisibleLineInMemory();
        localLine = this.mLinesAdapter.findById(Integer.valueOf(paramInt));
        if (localLine.getLineType() == 1)
        {
          if ((this.root != null) && (localLine.id == this.root.id))
            break;
          Intent localIntent = new Intent(this, BesharpMainActivity.class);
          localIntent.putExtra("ACTIVITY_PARAMETER_ROOT_LINE_ID", localLine.id);
          startActivity(localIntent);
          if (!this.mFirstTimeKludge)
            break;
          this.mSearchBox.postDelayed(new Runnable()
          {
            public void run()
            {
              if (BesharpMainActivity.this.mLinesAdapter.mSelectedHolder != null)
              {
                BesharpMainActivity.this.mLinesAdapter.mSelectedHolder.edit_text_detailed_desc.requestFocus();
                ((InputMethodManager)BesharpMainActivity.this.getSystemService("input_method")).showSoftInput(BesharpMainActivity.this.mLinesAdapter.mSelectedHolder.edit_text_detailed_desc, 1);
              }
            }
          }
          , 200L);
          this.mFirstTimeKludge = false;
        }
      }
      catch (Exception localException)
      {
        showToast(localException.toString());
        localException.printStackTrace();
      }
      if (localLine.getLineType() == 0)
      {
        setSelectedLineId(localLine.id.intValue());
        saveScrollState();
        this.mLinesAdapter.notifyDataSetChanged();
        this.mLinesAdapter.mAlreadySetFocus = false;
        getListView().setSelectionFromTop(this.mLinesAdapter.getPositionById(paramInt), 50);
        continue;
      }
      localLine.getLineType();
    }
  }

  private void setCalendarIntegrationEnabled(Boolean paramBoolean)
  {
    this.mCalendarEnabled = paramBoolean.booleanValue();
    if (this.mCalendarEnabled)
    {
      if (this.mCalendarId == -1)
        selectAndSaveCalendar();
      this.mLinesAdapter.notifyDataSetChanged();
    }
  }

  private void showToast(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }

  private void ttt(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    Integer localInteger = ((LinesAdapter.ViewHolder)((AdapterView.AdapterContextMenuInfo)paramContextMenuInfo).targetView.getTag()).lineId;
    Line localLine = this.mLinesAdapter.findById(localInteger);
    paramContextMenu.setHeaderTitle(localLine.getDesc());
    if (localLine.getLineType() == 0)
    {
      paramContextMenu.add(0, 1, 0, "Mark as List");
      if (localLine.isDeletable.booleanValue())
        paramContextMenu.add(0, 4, 0, "Delete");
      if (this.mEasyCollapsing)
        paramContextMenu.add(0, 8, 0, "Edit");
      paramContextMenu.add(0, 5, 0, "Set text color");
      paramContextMenu.add(0, 6, 0, "Set background color");
      paramContextMenu.add(0, 7, 0, "Set icon");
    }
    while (true)
    {
      return;
      if (localLine.getLineType() == 1)
      {
        paramContextMenu.add(0, 2, 0, "Mark as Task");
        paramContextMenu.add(0, 8, 0, "Edit");
        paramContextMenu.add(0, 5, 0, "Set text color");
        paramContextMenu.add(0, 6, 0, "Set background color");
        paramContextMenu.add(0, 7, 0, "Set icon");
        continue;
      }
      localLine.getLineType();
    }
  }

  private void updateListViewHeight()
  {
  }

  public void deleteEventWithConfirmation(Line paramLine)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("This line has event attached. You want to delete it?").setCancelable(false).setPositiveButton("Delete event", new DialogInterface.OnClickListener(paramLine)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        Event.deleteEvent(BesharpMainActivity.this.getContentResolver(), this.val$line.getCalendarEventId());
        this.val$line.setCalendarEventId(null);
        this.val$line.setCalendarId(null);
      }
    }).setNegativeButton("Keep event", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }

  public void deleteLine(Line paramLine)
  {
    try
    {
      ArrayList localArrayList = this.mLinesAdapter.getChildLines(paramLine);
      if (paramLine.getCalendarEventId() != null)
        deleteEventWithConfirmation(paramLine);
      localArrayList.add(paramLine);
      if (localArrayList.size() > 1)
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle("Warning").setMessage("Line \"" + paramLine.getDesc() + "\" has " + localArrayList.size() + " sub-lines. They will be also deleted. Are you sure?").setCancelable(false).setPositiveButton("Delete all", new DialogInterface.OnClickListener(localArrayList)
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            try
            {
              BesharpMainActivity.this.mLinesAdapter.deleteLinesFromCacheAndDb(this.val$mChildLinesToDelete);
              BesharpMainActivity.this.showToast(this.val$mChildLinesToDelete.size() + " lines deleted");
              return;
            }
            catch (Exception localException)
            {
              while (true)
              {
                BesharpMainActivity.this.showToast(localException.toString());
                localException.printStackTrace();
              }
            }
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            paramDialogInterface.cancel();
          }
        });
        localBuilder.create().show();
      }
      else
      {
        this.mLinesAdapter.deleteLinesFromCacheAndDb(localArrayList);
        showToast("Line \"" + paramLine.getDesc() + "\" deleted");
      }
    }
    catch (Exception localException)
    {
      showToast("Error: " + localException.toString());
      localException.printStackTrace();
    }
  }

  public int getSelectedLineId()
  {
    return this.selectedLineId;
  }

  public void initialUpdateDisplay()
  {
    this.mLines = null;
    try
    {
      this.mLines = mLinesDao.getLinesFromRoot(this.root, null);
      if (this.root != null)
        this.mLines.put(this.root.id, this.root);
      this.mLinesAdapter = new LinesAdapter(this, this, this.mLines, this.dbHelper);
      setListAdapter(this.mLinesAdapter);
      getListView().setSelectionFromTop(this.scrollIndex, this.scrollTop);
      if (this.mSearchMode)
        enterSearchMode();
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        showToast(localException.toString());
        localException.printStackTrace();
      }
    }
  }

  public Boolean isOverZone(int paramInt1, int paramInt2, String paramString)
  {
    logInfo("isOverZone, start finding view, x=" + paramInt1 + ",y=" + paramInt2);
    View localView = findViewBy(paramInt1, paramInt2, this.linesListView, paramString, "-");
    logInfo("isOverZone, finish finding view, x=" + paramInt1 + ",y=" + paramInt2);
    if ((localView != null) && (localView.getTag().toString().equals(paramString)));
    for (Boolean localBoolean = Boolean.valueOf(true); ; localBoolean = Boolean.valueOf(false))
      return localBoolean;
  }

  public void logInfo(String paramString)
  {
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 != 1) || (paramInt2 == -1))
    {
      try
      {
        int k = paramIntent.getExtras().getInt("RETURN_ARGUMENT_TAG_ID");
        this.dbHelper.getLineTagInterDataDao().addTagToLine(this.mLinesAdapter.mSelectedHolder.lineId, Integer.valueOf(k));
        this.mLinesAdapter.notifyDataSetChanged();
        return;
        if (paramInt1 == 2)
        {
          if (paramInt2 != -1)
            return;
          int j = paramIntent.getExtras().getInt("RETURN_ARGUMENT_TAG_ID");
          this.mSearchTag = ((Tag)this.dbHelper.getTagDataDao().queryForId(Integer.valueOf(j)));
          this.mSearchMode = true;
          enterSearchMode();
        }
      }
      catch (Exception localException)
      {
        showToast(localException.toString());
        localException.printStackTrace();
      }
      if (paramInt1 == 3)
      {
        if (paramInt2 == -1)
        {
          String str = paramIntent.getExtras().getString("RETURN_ARGUMENT_ICON_NAME");
          this.mLinesAdapter.findById(this.mLineForStyleSetId).setLineIcon(str);
          saveLines();
          this.mLinesAdapter.notifyDataSetChanged();
        }
      }
      else if (paramInt1 == 4)
      {
        if (paramInt2 == -1)
        {
          this.mLinesAdapter.notifyDataSetChanged();
        }
        else
        {
          Event.deleteEvent(getContentResolver(), this.mLineForStyleSet.getCalendarEventId());
          this.mLineForStyleSet.setCalendarEventId(null);
          this.mLineForStyleSet.setCalendarId(null);
        }
      }
      else if (paramInt1 == 5)
      {
        if (paramInt2 == -1)
          this.mLinesAdapter.notifyDataSetChanged();
      }
      else if ((paramInt1 == 6) && (paramInt2 == -1))
      {
        int i = paramIntent.getExtras().getInt("RETURN_ARGUMENT_CALENDAR_ID");
        SharedPreferences localSharedPreferences = getSharedPreferences("ru.humantouch.besharp", 0);
        this.mCalendarId = i;
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        localEditor.putInt("PREFERENCE_CALENDAR_ID", this.mCalendarId);
        localEditor.commit();
        this.mLinesAdapter.notifyDataSetChanged();
      }
    }
  }

  public void onBackPressed()
  {
    if (this.selectedState)
    {
      this.selectedState = false;
      this.mLinesAdapter.notifyDataSetChanged();
    }
    while (true)
    {
      updateListViewHeight();
      return;
      if (this.mReorderMode)
      {
        this.mReorderMode = false;
        this.mLinesAdapter.notifyDataSetChanged();
        continue;
      }
      if (this.mSearchMode)
      {
        exitSearchMode();
        continue;
      }
      super.onBackPressed();
    }
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    Integer localInteger = ((LinesAdapter.ViewHolder)((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).targetView.getTag()).lineId;
    Line localLine = this.mLinesAdapter.findById(localInteger);
    boolean bool;
    switch (paramMenuItem.getItemId())
    {
    default:
      bool = super.onContextItemSelected(paramMenuItem);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    while (true)
    {
      return bool;
      localLine.setLineType(1);
      this.mLinesAdapter.notifyDataSetChanged();
      bool = true;
      continue;
      localLine.setLineType(0);
      this.mLinesAdapter.notifyDataSetChanged();
      bool = true;
      continue;
      Intent localIntent2 = new Intent(this, BesharpMainActivity.class);
      localIntent2.putExtra("ACTIVITY_PARAMETER_ROOT_LINE_ID", localLine.id);
      startActivity(localIntent2);
      this.mLinesAdapter.notifyDataSetChanged();
      bool = true;
      continue;
      deleteLine(localLine);
      bool = true;
      continue;
      this.mLineForStyleSet = localLine;
      new AmbilWarnaDialog(this, localLine.getTextColor().intValue(), new AmbilWarnaDialog.OnAmbilWarnaListener()
      {
        public void onCancel(AmbilWarnaDialog paramAmbilWarnaDialog)
        {
        }

        public void onOk(AmbilWarnaDialog paramAmbilWarnaDialog, int paramInt)
        {
          BesharpMainActivity.this.mLineForStyleSet.setTextColor(Integer.valueOf(paramInt));
          BesharpMainActivity.this.mLinesAdapter.notifyDataSetChanged();
        }
      }).show();
      bool = true;
      continue;
      this.mLineForStyleSet = localLine;
      new AmbilWarnaDialog(this, localLine.getBackgroundColor().intValue(), new AmbilWarnaDialog.OnAmbilWarnaListener()
      {
        public void onCancel(AmbilWarnaDialog paramAmbilWarnaDialog)
        {
        }

        public void onOk(AmbilWarnaDialog paramAmbilWarnaDialog, int paramInt)
        {
          BesharpMainActivity.this.mLineForStyleSet.setBackgroundColor(Integer.valueOf(paramInt));
          BesharpMainActivity.this.mLinesAdapter.notifyDataSetChanged();
        }
      }).show();
      bool = true;
      continue;
      this.mLineForStyleSet = localLine;
      this.mLineForStyleSetId = localLine.id;
      Intent localIntent1 = new Intent(this, IconSelectActivity.class);
      ((GlobalState)getApplication()).mIcons = this.mIcons;
      startActivityForResult(localIntent1, 3);
      this.mLinesAdapter.notifyDataSetChanged();
      bool = true;
      continue;
      selectLine(localLine.id.intValue());
      bool = true;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    try
    {
      super.onCreate(paramBundle);
      this.mIcons = new HashMap();
      arrayOfField = R.drawable.class.getFields();
      int i = arrayOfField.length;
      j = 0;
      if (j >= i)
      {
        this.mReorderMode = false;
        this.mSearchMode = false;
        this.mShowStrikedOut = false;
        this.dbHelper = ((DatabaseHelper)OpenHelperManager.getHelper(this));
        ((GlobalState)getApplication()).setDbHelper(this.dbHelper);
        mLinesDao = this.dbHelper.getLinesDataDao();
        Bundle localBundle = getIntent().getExtras();
        if (localBundle != null)
        {
          Integer localInteger2 = Integer.valueOf(localBundle.getInt("ACTIVITY_PARAMETER_ROOT_LINE_ID"));
          if (localInteger2 != null)
            this.root = ((Line)mLinesDao.queryForId(localInteger2));
        }
        Log.i(this.LOG_TAG, "creating " + getClass());
        setContentView(2130903046);
        this.linesListView = ((LinesListView)getListView());
        this.linesListView.mBesharpMainActivity = this;
        this.selectedState = false;
        if (!(this.linesListView instanceof LinesListView))
          break label976;
        this.linesListView.setDropListener(this.mDropListener);
        this.linesListView.setDragListener(this.mDragListener);
        this.linesListView.setIsOverZoneChecker(this);
        break label976;
        this.scrollIndex = ((Integer)localSerializable1).intValue();
        if (paramBundle != null)
          break label914;
        localObject1 = null;
        this.scrollTop = ((Integer)localObject1).intValue();
        getListView().setSelectionFromTop(this.scrollIndex, this.scrollTop);
        this.mSearchString = "";
        this.mSearchTag = null;
        if (paramBundle != null)
          break label929;
        localSerializable3 = null;
      }
    }
    catch (Exception localException1)
    {
      while (true)
      {
        try
        {
          Field[] arrayOfField;
          int j;
          Boolean localBoolean = (Boolean)localSerializable3;
          if (localBoolean == null)
            continue;
          this.mSearchMode = localBoolean.booleanValue();
          if (paramBundle != null)
            continue;
          Serializable localSerializable4 = null;
          this.mSearchString = ((String)localSerializable4);
          if (paramBundle != null)
            continue;
          Object localObject2 = null;
          Integer localInteger1 = (Integer)localObject2;
          if (localInteger1 == null)
            continue;
          this.mSearchTag = ((Tag)this.dbHelper.getTagDataDao().queryForId(localInteger1));
          if (!this.mSearchMode)
            continue;
          enterSearchMode();
          registerForContextMenu(getListView());
          this.mSearchBoxLayout = ((LinearLayout)findViewById(2131099695));
          this.mSearchDividerLine = findViewById(2131099698);
          this.mSearchBox = ((EditText)findViewById(2131099696));
          this.mSearchCancel = ((Button)findViewById(2131099697));
          this.mFilteredTagText = ((TextView)findViewById(2131099694));
          this.mTagSearchLayout = ((LinearLayout)findViewById(2131099693));
          this.mSearchBoxLayout.setVisibility(8);
          this.mSearchDividerLine.setVisibility(8);
          this.mTagSearchLayout.setVisibility(8);
          EditText localEditText1 = this.mSearchBox;
          6 local6 = new TextWatcher()
          {
            public void afterTextChanged(Editable paramEditable)
            {
              BesharpMainActivity.this.mLinesAdapter.setFilter(paramEditable.toString());
            }

            public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
            {
            }

            public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
            {
            }
          };
          localEditText1.addTextChangedListener(local6);
          EditText localEditText2 = this.mSearchBox;
          7 local7 = new View.OnKeyListener()
          {
            public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
            {
              if (paramInt == 66);
              for (int i = 1; ; i = 0)
                return i;
            }
          };
          localEditText2.setOnKeyListener(local7);
          Button localButton = this.mSearchCancel;
          8 local8 = new View.OnClickListener()
          {
            public void onClick(View paramView)
            {
              BesharpMainActivity.this.exitSearchMode();
            }
          };
          localButton.setOnClickListener(local8);
          if (this.dbHelper.getTagDataDao().queryForAll().size() != 0)
            continue;
          fillTagsWithTempData();
          if (mLinesDao.getAllLines().size() != 0)
            continue;
          fillLinesWithTempData();
          MyGestureDetector localMyGestureDetector = new MyGestureDetector(this);
          this.gestureDetector = new GestureDetector(localMyGestureDetector);
          9 local9 = new View.OnTouchListener()
          {
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
            {
              if (BesharpMainActivity.this.gestureDetector.onTouchEvent(paramMotionEvent));
              for (int i = 1; ; i = 0)
                return i;
            }
          };
          this.gestureListener = local9;
          DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
          ViewConfiguration localViewConfiguration = ViewConfiguration.get(this);
          this.REL_SWIPE_MIN_DISTANCE = localViewConfiguration.getScaledTouchSlop();
          this.REL_SWIPE_MAX_OFF_PATH = (int)(20 * localDisplayMetrics.densityDpi / 160.0F);
          this.REL_SWIPE_THRESHOLD_VELOCITY = localViewConfiguration.getScaledMinimumFlingVelocity();
          getListView().setOnTouchListener(this.gestureListener);
          initialUpdateDisplay();
          SharedPreferences localSharedPreferences = getSharedPreferences("ru.humantouch.besharp", 0);
          this.mCalendarId = localSharedPreferences.getInt("PREFERENCE_CALENDAR_ID", -1);
          this.mEasyCollapsing = localSharedPreferences.getBoolean("PREFERENCE_EASY_COLLAPSING", true);
          this.mHandler = new Handler();
          BesharpPurchaseObserver localBesharpPurchaseObserver = new BesharpPurchaseObserver(this.mHandler);
          this.mPurchaseObserver = localBesharpPurchaseObserver;
          this.mBillingService = new BillingService();
          this.mBillingService.setContext(this);
          PurchaseDatabase localPurchaseDatabase = new PurchaseDatabase(this);
          this.mPurchaseDatabase = localPurchaseDatabase;
          ResponseHandler.register(this.mPurchaseObserver);
          break;
          Field localField = arrayOfField[j];
          try
          {
            if (!localField.getName().startsWith("ic_menu"))
              continue;
            this.mIcons.put(localField.getName(), Integer.valueOf(localField.getInt(null)));
            j++;
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
            continue;
          }
          localException1 = localException1;
          showToast(localException1.toString());
          localException1.printStackTrace();
          break;
          localSerializable1 = paramBundle.getSerializable("BUNDLE_SCROLL_INDEX");
          continue;
          label914: Serializable localSerializable2 = paramBundle.getSerializable("BUNDLE_SCROLL_TOP");
          Object localObject1 = localSerializable2;
          continue;
          label929: Serializable localSerializable3 = paramBundle.getSerializable("BUNDLE_SEARCH_MODE");
          continue;
          localSerializable4 = paramBundle.getSerializable("BUNDLE_SEARCH_STRING");
          continue;
          Serializable localSerializable5 = paramBundle.getSerializable("BUNDLE_SEARCH_TAG");
          localObject2 = localSerializable5;
          continue;
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          continue;
        }
        label976: if (paramBundle == null)
          continue;
        if (paramBundle != null)
          continue;
        Serializable localSerializable1 = null;
      }
    }
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    Dialog localDialog = new Dialog(this);
    localDialog.setContentView(2130903044);
    Integer localInteger = ((LinesAdapter.ViewHolder)((AdapterView.AdapterContextMenuInfo)paramContextMenuInfo).targetView.getTag()).lineId;
    Line localLine = this.mLinesAdapter.findById(localInteger);
    localDialog.setTitle("");
    ((TextView)localDialog.findViewById(2131099660)).setText(localLine.getDesc());
    if (localLine.getLineType() == 0)
    {
      paramContextMenu.add(0, 1, 0, "Mark as List");
      if (localLine.isDeletable.booleanValue())
        paramContextMenu.add(0, 4, 0, "Delete");
      if (this.mEasyCollapsing)
        paramContextMenu.add(0, 8, 0, "Edit");
      paramContextMenu.add(0, 5, 0, "Set text color");
      paramContextMenu.add(0, 6, 0, "Set background color");
      paramContextMenu.add(0, 7, 0, "Set icon");
    }
    while (true)
    {
      localDialog.show();
      return;
      if (localLine.getLineType() == 1)
      {
        paramContextMenu.add(0, 2, 0, "Mark as Task");
        paramContextMenu.add(0, 8, 0, "Edit");
        paramContextMenu.add(0, 5, 0, "Set text color");
        paramContextMenu.add(0, 6, 0, "Set background color");
        paramContextMenu.add(0, 7, 0, "Set icon");
        continue;
      }
      localLine.getLineType();
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    Object localObject;
    switch (paramInt)
    {
    default:
      localObject = null;
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return localObject;
      Date localDate = new Date();
      int i = 1900 + localDate.getYear();
      int j = localDate.getMonth();
      int k = localDate.getDate();
      this.mDateSelectDialog = new DatePickerDialog(this, this.mDateSetListener, i, j, k);
      localObject = this.mDateSelectDialog;
      continue;
      localObject = createDialog("In-app billing unsupported", "Looks like in-app billing is not supported in your version of Android Market.");
      continue;
      localObject = createDialog("Error", "In-application billing is not supported in your version of Android Market");
      continue;
      Dialog localDialog = new Dialog(this);
      localDialog.setContentView(2130903044);
      localDialog.setTitle("Custom Dialog");
      localObject = localDialog;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    paramMenu.add(0, 1, 0, "Reorder");
    paramMenu.add(0, 2, 0, "Search");
    paramMenu.add(0, 3, 0, "Search by tag");
    paramMenu.add(0, 4, 0, "Show done tasks");
    paramMenu.add(0, 6, 0, "Change default calendar");
    return true;
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mPurchaseDatabase.close();
    this.mBillingService.unbind();
  }

  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    Line localLine;
    if (this.mEasyCollapsing)
    {
      localLine = this.mLinesAdapter.findById(Integer.valueOf((int)paramLong));
      if ((this.mLinesAdapter.hasChildren(localLine)) && (localLine.getLineType() != 1))
        if (localLine.getIsCollapsed().booleanValue())
        {
          localLine.setIsCollapsed(Boolean.valueOf(false));
          this.mLinesAdapter.notifyDataSetChanged();
        }
    }
    while (true)
    {
      return;
      localLine.setIsCollapsed(Boolean.valueOf(true));
      break;
      selectLine((int)paramLong);
      continue;
      selectLine((int)paramLong);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool1;
    switch (paramMenuItem.getItemId())
    {
    case 5:
    default:
      bool1 = super.onOptionsItemSelected(paramMenuItem);
    case 1:
    case 2:
    case 3:
    case 4:
    case 6:
    }
    while (true)
    {
      return bool1;
      if (this.mReorderMode);
      for (boolean bool3 = false; ; bool3 = true)
      {
        this.mReorderMode = bool3;
        this.mLinesAdapter.notifyDataSetChanged();
        bool1 = true;
        break;
      }
      this.mSearchMode = true;
      enterSearchMode();
      this.mSearchBox.requestFocus();
      bool1 = true;
      continue;
      try
      {
        startActivityForResult(new Intent(this, TagSearchActivity.class), 2);
        bool1 = true;
      }
      catch (Exception localException2)
      {
        while (true)
        {
          showToast(localException2.toString());
          localException2.printStackTrace();
        }
      }
      try
      {
        if (this.mShowStrikedOut);
        for (boolean bool2 = false; ; bool2 = true)
        {
          this.mShowStrikedOut = bool2;
          this.mLinesAdapter.notifyDataSetChanged();
          bool1 = true;
          break;
        }
      }
      catch (Exception localException1)
      {
        while (true)
        {
          showToast(localException1.toString());
          localException1.printStackTrace();
        }
      }
      selectAndSaveCalendar();
      bool1 = true;
    }
  }

  protected void onPause()
  {
    super.onPause();
    if (this.mLinesAdapter.mSelectedHolder != null)
      this.mLinesAdapter.saveCurrentlyVisibleLineInMemory();
    saveLines();
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.mReorderMode)
    {
      paramMenu.findItem(1).setTitle("End reorder");
      if (!this.mShowStrikedOut)
        break label71;
      paramMenu.findItem(4).setTitle("Hide done tasks");
    }
    while (true)
    {
      return super.onPrepareOptionsMenu(paramMenu);
      paramMenu.findItem(1).setTitle("Reorder");
      break;
      label71: paramMenu.findItem(4).setTitle("Show done tasks");
    }
  }

  protected void onResume()
  {
    super.onResume();
    initialUpdateDisplay();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    saveLines();
    saveScrollState();
    paramBundle.putSerializable("BUNDLE_SCROLL_TOP", Integer.valueOf(this.scrollTop));
    paramBundle.putSerializable("BUNDLE_SCROLL_INDEX", Integer.valueOf(this.scrollIndex));
    paramBundle.putSerializable("BUNDLE_SEARCH_MODE", Boolean.valueOf(this.mSearchMode));
    paramBundle.putSerializable("BUNDLE_SEARCH_STRING", this.mSearchString);
    if (this.mSearchTag != null)
      paramBundle.putSerializable("BUNDLE_SEARCH_TAG", Integer.valueOf(this.mSearchTag.id));
  }

  protected void onStart()
  {
    super.onStart();
    ResponseHandler.register(this.mPurchaseObserver);
    initializeOwnedItems();
  }

  protected void onStop()
  {
    super.onStop();
    ResponseHandler.unregister(this.mPurchaseObserver);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent);
  }

  void selectAndSaveCalendar()
  {
    startActivityForResult(new Intent(this, CalendarSelectActivity.class), 6);
    this.mLinesAdapter.notifyDataSetChanged();
  }

  public void setSelectedLineId(int paramInt)
  {
    this.selectedLineId = paramInt;
    this.selectedState = true;
  }

  public void showPurchaseNotification(String paramString1, String paramString2)
  {
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    this.mPurchaseNotification = new Notification(2130837569, paramString2, System.currentTimeMillis());
    Notification localNotification = this.mPurchaseNotification;
    localNotification.flags = (0x10 | localNotification.flags);
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, null, 0);
    this.mPurchaseNotification.setLatestEventInfo(this, paramString1, paramString2, localPendingIntent);
    localNotificationManager.notify(1, this.mPurchaseNotification);
  }

  private class BesharpPurchaseObserver extends PurchaseObserver
  {
    public BesharpPurchaseObserver(Handler arg2)
    {
      super(localHandler);
    }

    public void onBillingSupported(boolean paramBoolean)
    {
      if (paramBoolean)
        BesharpMainActivity.this.restoreDatabase();
      while (true)
      {
        return;
        BesharpMainActivity.this.showDialog(3);
      }
    }

    public void onPurchaseStateChange(Consts.PurchaseState paramPurchaseState, String paramString1, int paramInt, long paramLong, String paramString2)
    {
      if (paramPurchaseState == Consts.PurchaseState.PURCHASED)
      {
        BesharpMainActivity.this.showPurchaseNotification("BeSharp purchase", "Your purchase was successful!");
        BesharpMainActivity.this.initializeOwnedItems();
      }
      BesharpMainActivity.this.mLinesAdapter.notifyDataSetChanged();
    }

    public void onRequestPurchaseResponse(BillingService.RequestPurchase paramRequestPurchase, Consts.ResponseCode paramResponseCode)
    {
      if (paramResponseCode == Consts.ResponseCode.RESULT_OK)
        BesharpMainActivity.this.showPurchaseNotification("BeSharp purchase", "Your purchase is being processed, please wait");
      while (true)
      {
        return;
        if (paramResponseCode == Consts.ResponseCode.RESULT_USER_CANCELED)
          continue;
      }
    }

    public void onRestoreTransactionsResponse(BillingService.RestoreTransactions paramRestoreTransactions, Consts.ResponseCode paramResponseCode)
    {
      if (paramResponseCode == Consts.ResponseCode.RESULT_OK)
      {
        SharedPreferences.Editor localEditor2 = BesharpMainActivity.this.getPreferences(0).edit();
        localEditor2.putBoolean("db_initialized", true);
        localEditor2.commit();
        BesharpMainActivity.this.showPurchaseNotification("BeSharp purchase", "Your purchases were restored");
      }
      while (true)
      {
        return;
        if (paramResponseCode == Consts.ResponseCode.RESULT_DEVELOPER_ERROR)
        {
          BesharpMainActivity.this.showPurchaseNotification("BeSharp purchase", "Permanent error during restoring of transactions");
          SharedPreferences.Editor localEditor1 = BesharpMainActivity.this.getPreferences(0).edit();
          localEditor1.putBoolean("db_initialized", true);
          localEditor1.commit();
          continue;
        }
        BesharpMainActivity.this.showPurchaseNotification("BeSharp purchase", "Error during restoring of transactions");
      }
    }
  }

  class MyGestureDetector extends GestureDetector.SimpleOnGestureListener
  {
    private BesharpMainActivity mBesharpMainActivity;
    private Line mLineUnderCursor;

    public MyGestureDetector(BesharpMainActivity arg2)
    {
      Object localObject;
      this.mBesharpMainActivity = localObject;
    }

    private void markLineAsStrikedOut(MotionEvent paramMotionEvent)
      throws SQLException
    {
      BesharpMainActivity.MyGestureDetector.1 local1 = new BesharpMainActivity.MyGestureDetector.1(this, paramMotionEvent);
      this.mBesharpMainActivity.runOnUiThread(local1);
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      try
      {
        if (Math.abs(paramMotionEvent1.getY() - paramMotionEvent2.getY()) <= BesharpMainActivity.this.REL_SWIPE_MAX_OFF_PATH)
        {
          if ((paramMotionEvent1.getX() - paramMotionEvent2.getX() > BesharpMainActivity.this.REL_SWIPE_MIN_DISTANCE) && (Math.abs(paramFloat1) > BesharpMainActivity.this.REL_SWIPE_THRESHOLD_VELOCITY))
          {
            markLineAsStrikedOut(paramMotionEvent1);
            break label124;
          }
          if ((paramMotionEvent2.getX() - paramMotionEvent1.getX() <= BesharpMainActivity.this.REL_SWIPE_MIN_DISTANCE) || (Math.abs(paramFloat1) <= BesharpMainActivity.this.REL_SWIPE_THRESHOLD_VELOCITY))
            break label124;
          markLineAsStrikedOut(paramMotionEvent1);
        }
      }
      catch (Exception localException)
      {
      }
      label124: 
      while (true)
        return false;
    }
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.BesharpMainActivity
 * JD-Core Version:    0.6.0
 */