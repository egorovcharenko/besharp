package ru.humantouch.besharp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import ru.humantouch.besharp.DAO.DatabaseHelper;
import ru.humantouch.besharp.DAO.LineDaoImpl;
import ru.humantouch.besharp.DAO.LineTagInterDaoImpl;
import ru.humantouch.besharp.entities.Event;
import ru.humantouch.besharp.entities.Line;
import ru.humantouch.besharp.entities.Tag;
import ru.humantouch.besharp.views.PredicateLayout;
import ru.humantouch.besharp.views.TagSearchActivity;
import ru.humantouch.besharp.views.TagView;
import ru.humantouch.besharp.views.TagView.TagRemoverListener;

public class LinesAdapter extends BaseAdapter
  implements View.OnKeyListener, TagView.TagRemoverListener
{
  private static final String LOG_TAG = "LinesAdapter";
  private static final int PADDING_SIZE = 20;
  private static final int SORT_ORDER_INCREMENT = 2;
  protected static final String TAGS_DAO = null;
  private static final int TYPE_COUNT = 2;
  private static final int TYPE_ITEM_EXPANDED = 1;
  private static final int TYPE_ITEM_NORMAL;
  private HashMap<Integer, Line> lines;
  boolean mAlreadySetFocus;
  private final BesharpMainActivity mBesharpMainActivity;
  private String mFilterString;
  private Tag mFilterTag;
  protected boolean mFirstTimeKeyboardKludge = true;
  private LayoutInflater mInflater;
  private LineTagInterDaoImpl mLineTagInterDao;
  private LineDaoImpl mLinesDao;
  ViewHolder mSelectedHolder;
  public Line mSpecialLineSelected;
  private ArrayList<Line> visibleLines;

  public LinesAdapter(BesharpMainActivity paramBesharpMainActivity, Context paramContext, HashMap<Integer, Line> paramHashMap, DatabaseHelper paramDatabaseHelper)
    throws Exception
  {
    this.mBesharpMainActivity = paramBesharpMainActivity;
    this.mInflater = LayoutInflater.from(paramContext);
    this.mLinesDao = paramDatabaseHelper.getLinesDataDao();
    this.mLineTagInterDao = paramDatabaseHelper.getLineTagInterDataDao();
    setLines(paramHashMap);
  }

  private int calcPaddingInMemory(Line paramLine)
    throws Exception
  {
    for (int i = 0; ; i++)
    {
      if (isParentLineNull(paramLine))
        return i * 20;
      paramLine = findParent(paramLine);
    }
  }

  private Line createDummyLine(Line paramLine)
  {
    Line localLine = new Line();
    localLine.setLineType(6);
    localLine.setDesc("---dummy---");
    localLine.id = Integer.valueOf(-1);
    return localLine;
  }

  private void displayTags(Line paramLine, PredicateLayout paramPredicateLayout, Context paramContext)
    throws Exception
  {
    ArrayList localArrayList = this.mLineTagInterDao.getTagsFromLine(paramLine);
    paramPredicateLayout.removeAllViews();
    Iterator localIterator = localArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Tag localTag = (Tag)localIterator.next();
      TagView localTagView = new TagView(paramContext, this, paramLine, localTag);
      SpannableString localSpannableString = new SpannableString(localTag.name);
      localSpannableString.setSpan(new UnderlineSpan(), 0, localSpannableString.length(), 0);
      localTagView.mNameTextView.setText(localSpannableString);
      paramPredicateLayout.addView(localTagView, new LinearLayout.LayoutParams(-2, -2));
    }
  }

  private Line findParent(Line paramLine)
  {
    Line localLine;
    if (paramLine.getParentLine() == null)
      localLine = null;
    while (true)
    {
      return localLine;
      if (paramLine.getParentLine().id == null)
      {
        localLine = null;
        continue;
      }
      localLine = (Line)this.lines.get(paramLine.getParentLine().id);
    }
  }

  private boolean isParentLineNull(Line paramLine)
  {
    Line localLine = findParent(paramLine);
    if ((localLine != null) && (localLine.id != null));
    for (int i = 0; ; i = 1)
      return i;
  }

  private void setAllParentsToNotFiltered(Line paramLine)
  {
    Line localLine = findParent(paramLine);
    if (localLine != null)
    {
      if (localLine.isFilteredOut != "FILTERED_OUT_YES")
        break label33;
      localLine.isFilteredOut = "FILTERED_OUT_PARENT";
    }
    while (true)
    {
      setAllParentsToNotFiltered(localLine);
      return;
      label33: if (localLine.isFilteredOut == "FILTERED_OUT_PARENT")
      {
        localLine.isFilteredOut = "FILTERED_OUT_PARENT";
        continue;
      }
      if (localLine.isFilteredOut != "FILTERED_OUT_NO")
        continue;
      localLine.isFilteredOut = "FILTERED_OUT_NO";
    }
  }

  private void setFilteredState()
    throws Exception
  {
    Iterator localIterator3;
    Iterator localIterator2;
    if (this.mFilterString != null)
    {
      localIterator3 = this.lines.values().iterator();
      if (localIterator3.hasNext());
    }
    else if (this.mFilterTag != null)
    {
      localIterator2 = this.lines.values().iterator();
    }
    Iterator localIterator1;
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        localIterator1 = this.lines.values().iterator();
        if (localIterator1.hasNext())
          break label123;
        return;
        ((Line)localIterator3.next()).isFilteredOut = "FILTERED_OUT_YES";
        break;
      }
      ((Line)localIterator2.next()).isFilteredOut = "FILTERED_OUT_YES";
    }
    label123: Line localLine = (Line)localIterator1.next();
    String str = localLine.isFilteredOut;
    if ((this.mFilterString != null) && (localLine.getDesc().toLowerCase().contains(this.mFilterString.toLowerCase())))
      str = "FILTERED_OUT_NO";
    if (this.mFilterTag != null)
      if (!this.mLineTagInterDao.hasLineTag(localLine, this.mFilterTag).booleanValue())
        break label218;
    label218: for (str = "FILTERED_OUT_NO"; ; str = localLine.isFilteredOut)
    {
      if (str != "FILTERED_OUT_YES")
        setAllParentsToNotFiltered(localLine);
      localLine.isFilteredOut = str;
      break;
    }
  }

  private void setPadding(View paramView, int paramInt)
  {
    paramView.setPadding(dip(paramView.getContext(), paramInt), 0, 0, 0);
  }

  private void showToast(String paramString)
  {
    Toast.makeText(this.mBesharpMainActivity.getApplicationContext(), paramString, 0).show();
  }

  private void sortLines()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    ArrayList localArrayList1 = new ArrayList(this.lines.keySet());
    ArrayList localArrayList2 = new ArrayList(this.lines.values());
    Object[] arrayOfObject = new TreeSet(localArrayList2).toArray();
    int i = arrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.lines = localLinkedHashMap;
        return;
      }
      localLinkedHashMap.put((Integer)localArrayList1.get(localArrayList2.indexOf(arrayOfObject[j])), (Line)arrayOfObject[j]);
    }
  }

  private void updateSortOrderInMemory()
  {
    if (this.mBesharpMainActivity.root != null)
      updateSortOrderInMemoryInternal(null, this.mBesharpMainActivity.root.getSortOrder());
    while (true)
    {
      return;
      updateSortOrderInMemoryInternal(null, 0);
    }
  }

  private int updateSortOrderInMemoryInternal(Line paramLine, int paramInt)
  {
    if (paramLine == null);
    while (true)
    {
      Iterator localIterator2;
      try
      {
        localArrayList = new ArrayList();
        Iterator localIterator3 = this.lines.values().iterator();
        if (localIterator3.hasNext())
          continue;
        Collections.sort(localArrayList);
        localIterator2 = localArrayList.iterator();
        if (localIterator2.hasNext())
          break label196;
        break;
        Line localLine3 = (Line)localIterator3.next();
        if (!isParentLineNull(localLine3))
          continue;
        localArrayList.add(localLine3);
        continue;
      }
      catch (Exception localException)
      {
        showToast(localException.toString());
        localException.printStackTrace();
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = this.lines.values().iterator();
      while (true)
      {
        if (!localIterator1.hasNext())
        {
          Collections.sort(localArrayList);
          break;
        }
        Line localLine1 = (Line)localIterator1.next();
        if ((localLine1.getParentLine() == null) || (!localLine1.getParentLine().id.equals(paramLine.id)))
          continue;
        localArrayList.add(localLine1);
      }
      label196: Line localLine2 = (Line)localIterator2.next();
      localLine2.setSortOrder(paramInt);
      paramInt += 2;
      int i = updateSortOrderInMemoryInternal(localLine2, paramInt);
      paramInt = i;
    }
    return paramInt;
  }

  public void deleteLineFromCacheAndDb(Line paramLine)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramLine);
    deleteLinesFromCacheAndDb(localArrayList);
  }

  public void deleteLinesFromCacheAndDb(ArrayList<Line> paramArrayList)
    throws Exception
  {
    Iterator localIterator = paramArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        updateSortOrderInMemory();
        sortLines();
        this.mBesharpMainActivity.selectedState = false;
        notifyDataSetChanged();
        return;
      }
      Line localLine = (Line)localIterator.next();
      this.mLineTagInterDao.removeAllTagsFromLine(localLine);
      this.mLinesDao.deleteLine(localLine);
      this.lines.remove(localLine.id);
    }
  }

  public int dip(Context paramContext, int paramInt)
  {
    DisplayMetrics localDisplayMetrics = this.mBesharpMainActivity.getResources().getDisplayMetrics();
    return (int)TypedValue.applyDimension(1, paramInt, localDisplayMetrics);
  }

  public void expandLine(int paramInt)
  {
    this.mBesharpMainActivity.setSelectedLineId(paramInt);
  }

  public Line findById(Integer paramInteger)
  {
    return (Line)this.lines.get(paramInteger);
  }

  public ArrayList<Line> getChildLines(Line paramLine)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.lines.values().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Line localLine = (Line)localIterator.next();
      if (!isChildLine(paramLine, localLine))
        continue;
      localArrayList.add(localLine);
    }
  }

  public int getCount()
  {
    return this.visibleLines.size();
  }

  public Object getItem(int paramInt)
  {
    return this.visibleLines.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return ((Line)getItem(paramInt)).id.intValue();
  }

  public int getItemViewType(int paramInt)
  {
    if ((((Line)getItem(paramInt)).id.intValue() == this.mBesharpMainActivity.getSelectedLineId()) && (this.mBesharpMainActivity.selectedState));
    for (int i = 1; ; i = 0)
      return i;
  }

  public HashMap<Integer, Line> getLines()
  {
    return this.lines;
  }

  public int getPositionById(int paramInt)
  {
    try
    {
      int j = this.visibleLines.indexOf(findById(Integer.valueOf(paramInt)));
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder;
    View localView1;
    while (true)
    {
      Line localLine1;
      Iterator localIterator;
      String str2;
      try
      {
        localLine1 = (Line)getItem(paramInt);
        if (paramView != null)
          continue;
        paramView = this.mInflater.inflate(2130903045, paramViewGroup, false);
        localViewHolder = new ViewHolder();
        TextView localTextView1 = (TextView)paramView.findViewById(2131099663);
        localViewHolder.padding = localTextView1;
        TextView localTextView2 = (TextView)paramView.findViewById(2131099674);
        localViewHolder.desc = localTextView2;
        View localView2 = paramView.findViewById(2131099677);
        localViewHolder.details_pane = localView2;
        View localView3 = paramView.findViewById(2131099662);
        localViewHolder.short_desc_pane = localView3;
        LinearLayout localLinearLayout1 = (LinearLayout)paramView.findViewById(2131099661);
        localViewHolder.line_view = localLinearLayout1;
        ImageButton localImageButton9 = (ImageButton)paramView.findViewById(2131099686);
        localViewHolder.button_create_new_below = localImageButton9;
        ImageButton localImageButton10 = (ImageButton)paramView.findViewById(2131099688);
        localViewHolder.button_create_new_above = localImageButton10;
        ImageButton localImageButton11 = (ImageButton)paramView.findViewById(2131099687);
        localViewHolder.button_create_new_child = localImageButton11;
        ImageButton localImageButton12 = (ImageButton)paramView.findViewById(2131099689);
        localViewHolder.button_delete_line = localImageButton12;
        EditText localEditText2 = (EditText)paramView.findViewById(2131099678);
        localViewHolder.edit_text_detailed_desc = localEditText2;
        PredicateLayout localPredicateLayout2 = (PredicateLayout)paramView.findViewById(2131099679);
        localViewHolder.layout_for_tags = localPredicateLayout2;
        ImageButton localImageButton13 = (ImageButton)paramView.findViewById(2131099667);
        localViewHolder.collapse_button = localImageButton13;
        Button localButton6 = (Button)paramView.findViewById(2131099690);
        localViewHolder.button_add_tag = localButton6;
        ImageView localImageView1 = (ImageView)paramView.findViewById(2131099668);
        localViewHolder.arrow_sign_before_element = localImageView1;
        TextView localTextView3 = (TextView)paramView.findViewById(2131099664);
        localViewHolder.drag_drop_start = localTextView3;
        TextView localTextView4 = (TextView)paramView.findViewById(2131099670);
        localViewHolder.drag_drop_before = localTextView4;
        TextView localTextView5 = (TextView)paramView.findViewById(2131099671);
        localViewHolder.drag_drop_after = localTextView5;
        TextView localTextView6 = (TextView)paramView.findViewById(2131099672);
        localViewHolder.drag_drop_right = localTextView6;
        ImageView localImageView2 = (ImageView)paramView.findViewById(2131099669);
        localViewHolder.drag_image = localImageView2;
        LinearLayout localLinearLayout2 = (LinearLayout)paramView.findViewById(2131099691);
        localViewHolder.line_dummy_pane = localLinearLayout2;
        TextView localTextView7 = (TextView)paramView.findViewById(2131099675);
        localViewHolder.task_due_date = localTextView7;
        ImageView localImageView3 = (ImageView)paramView.findViewById(2131099673);
        localViewHolder.line_icon = localImageView3;
        ImageButton localImageButton14 = (ImageButton)paramView.findViewById(2131099676);
        localViewHolder.button_new_child_special = localImageButton14;
        TextView localTextView8 = (TextView)paramView.findViewById(2131099681);
        localViewHolder.information_due_date = localTextView8;
        Button localButton7 = (Button)paramView.findViewById(2131099682);
        localViewHolder.information_change_due_date = localButton7;
        TextView localTextView9 = (TextView)paramView.findViewById(2131099684);
        localViewHolder.information_calendar_event_info = localTextView9;
        Button localButton8 = (Button)paramView.findViewById(2131099683);
        localViewHolder.information_calendar_button = localButton8;
        paramView.setTag(localViewHolder);
        if (localLine1.getLineType() != 6)
          continue;
        localViewHolder.line_dummy_pane.setVisibility(0);
        localViewHolder.details_pane.setVisibility(8);
        localViewHolder.short_desc_pane.setVisibility(8);
        localView1 = paramView;
        break label3061;
        localViewHolder = (ViewHolder)paramView.getTag();
        continue;
        localViewHolder.line_dummy_pane.setVisibility(8);
        switch (getItemViewType(paramInt))
        {
        default:
          localViewHolder.collapse_button.setVisibility(8);
          if (((this.mBesharpMainActivity.root == null) || (this.mBesharpMainActivity.root.id == localLine1.id)) && (this.mBesharpMainActivity.root != null))
            continue;
          localIterator = this.lines.values().iterator();
          if (localIterator.hasNext())
            break;
          int k = calcPaddingInMemory(localLine1);
          setPadding(localViewHolder.padding, k);
          str2 = localLine1.getDesc();
          if (localLine1.getLineType() != 7)
            break label2863;
          SpannableString localSpannableString1 = new SpannableString(str2);
          localSpannableString1.setSpan(new UnderlineSpan(), 0, localSpannableString1.length(), 0);
          localSpannableString1.setSpan(new StyleSpan(1), 0, localSpannableString1.length(), 0);
          localViewHolder.desc.setText(localSpannableString1, TextView.BufferType.SPANNABLE);
          localViewHolder.task_due_date.setVisibility(8);
          if (this.mBesharpMainActivity.mCalendarEnabled)
            continue;
          Date localDate1 = localLine1.getDueDate();
          if (localDate1 == null)
            continue;
          localViewHolder.task_due_date.setVisibility(0);
          SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
          localViewHolder.task_due_date.setText(localSimpleDateFormat1.format(localDate1));
          if (localLine1.getLineType() != 1)
            break label2955;
          Spannable localSpannable3 = (Spannable)localViewHolder.desc.getText();
          localSpannable3.setSpan(new StyleSpan(3), 0, localSpannable3.length(), 33);
          Integer localInteger1 = Integer.valueOf(paramInt);
          localViewHolder.position = localInteger1;
          Integer localInteger2 = localLine1.id;
          localViewHolder.lineId = localInteger2;
          if (localLine1.isMoveable.booleanValue())
            break label3047;
          localViewHolder.drag_drop_start.setTag(null);
          break;
        case 1:
          logInfo("case TYPE_ITEM_EXPANDED, position = " + paramInt);
          this.mSelectedHolder = localViewHolder;
          localViewHolder.details_pane.setVisibility(0);
          localViewHolder.short_desc_pane.setVisibility(8);
          ImageButton localImageButton5 = localViewHolder.button_create_new_below;
          OnCreateNewBelowListener localOnCreateNewBelowListener = new OnCreateNewBelowListener(null);
          localImageButton5.setOnClickListener(localOnCreateNewBelowListener);
          ImageButton localImageButton6 = localViewHolder.button_create_new_above;
          OnCreateNewAboveListener localOnCreateNewAboveListener = new OnCreateNewAboveListener(null);
          localImageButton6.setOnClickListener(localOnCreateNewAboveListener);
          ImageButton localImageButton7 = localViewHolder.button_create_new_child;
          OnCreateNewChildListener localOnCreateNewChildListener4 = new OnCreateNewChildListener(localLine1);
          localImageButton7.setOnClickListener(localOnCreateNewChildListener4);
          if (localLine1.isDeletable.booleanValue())
          {
            localViewHolder.button_delete_line.setVisibility(0);
            ImageButton localImageButton8 = localViewHolder.button_delete_line;
            OnDeleteListener localOnDeleteListener = new OnDeleteListener(null);
            localImageButton8.setOnClickListener(localOnDeleteListener);
            Button localButton1 = localViewHolder.button_add_tag;
            1 local1 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
                Intent localIntent = new Intent(LinesAdapter.this.mBesharpMainActivity, TagSearchActivity.class);
                ((Activity)paramView.getContext()).startActivityForResult(localIntent, 1);
              }
            };
            localButton1.setOnClickListener(local1);
            localViewHolder.edit_text_detailed_desc.setOnKeyListener(this);
            PredicateLayout localPredicateLayout1 = localViewHolder.layout_for_tags;
            Context localContext = paramView.getContext();
            logInfo("Setting EditText.text from " + localViewHolder.edit_text_detailed_desc.getText().toString() + " to " + localLine1.getDesc() + ", edittext = " + localViewHolder.edit_text_detailed_desc.toString());
            localViewHolder.edit_text_detailed_desc.setText(localLine1.getDesc());
            EditText localEditText1 = localViewHolder.edit_text_detailed_desc;
            2 local2 = new View.OnFocusChangeListener()
            {
              public void onFocusChange(View paramView, boolean paramBoolean)
              {
                if (!paramBoolean)
                  LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
              }
            };
            localEditText1.setOnFocusChangeListener(local2);
            displayTags(localLine1, localPredicateLayout1, localContext);
            if (this.mFirstTimeKeyboardKludge)
              break label1526;
            localViewHolder.edit_text_detailed_desc.requestFocus();
            if (this.mAlreadySetFocus)
              continue;
            ((InputMethodManager)this.mBesharpMainActivity.getSystemService("input_method")).showSoftInput(localViewHolder.edit_text_detailed_desc, 1);
            this.mAlreadySetFocus = true;
            Date localDate2 = localLine1.getDueDate();
            if (this.mBesharpMainActivity.mCalendarEnabled)
              break label1548;
            localViewHolder.information_change_due_date.setVisibility(0);
            localViewHolder.information_calendar_button.setVisibility(8);
            if (localDate2 == null)
              break label1534;
            SimpleDateFormat localSimpleDateFormat5 = new SimpleDateFormat("dd.MM.yyyy");
            localViewHolder.information_due_date.setText(localSimpleDateFormat5.format(localDate2));
            Button localButton4 = localViewHolder.information_change_due_date;
            3 local3 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                LinesAdapter.3.1 local1 = new LinesAdapter.3.1(this);
                LinesAdapter.this.mBesharpMainActivity.mSearchBox.post(local1);
              }
            };
            localButton4.setOnClickListener(local3);
            localViewHolder.information_calendar_button.setVisibility(0);
            localViewHolder.information_calendar_button.setText("Add calendar event");
            Button localButton5 = localViewHolder.information_calendar_button;
            4 local4 = new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(LinesAdapter.this.mBesharpMainActivity);
                localBuilder.setTitle("Calendar integration is not activated").setMessage("Calendar integration is not activated. When you activate it, you will be able to easily create calendar events from tasks in BeSharp - just one click away! And even more - event date and time changes will be seen in BeSharp!").setCancelable(false).setNegativeButton("Cancel", new LinesAdapter.4.1(this)).setPositiveButton("Activate calendar integration", new LinesAdapter.4.2(this));
                localBuilder.create().show();
              }
            };
            localButton5.setOnClickListener(local4);
            continue;
          }
        case 0:
        }
      }
      catch (Exception localException1)
      {
        showToast(localException1.toString());
        localException1.printStackTrace();
      }
      localViewHolder.button_delete_line.setVisibility(8);
      continue;
      label1526: this.mFirstTimeKeyboardKludge = false;
      continue;
      label1534: localViewHolder.information_due_date.setText(" date not set ");
      continue;
      label1548: localViewHolder.information_change_due_date.setVisibility(8);
      localViewHolder.information_calendar_button.setVisibility(0);
      localViewHolder.information_calendar_event_info.setText("");
      localViewHolder.information_due_date.setText("No event linked");
      localViewHolder.information_calendar_button.setText("Add event");
      Button localButton2 = localViewHolder.information_calendar_button;
      5 local5 = new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          try
          {
            LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
            Line localLine = LinesAdapter.this.findById(LinesAdapter.this.mSelectedHolder.lineId);
            LinesAdapter.this.mBesharpMainActivity.mLineForStyleSet = localLine;
            Event localEvent = new Event();
            localEvent.mTitle = localLine.getDesc();
            localEvent.mAllDay = 1;
            int i = LinesAdapter.this.mBesharpMainActivity.mCalendarId;
            if (i == -1)
            {
              AlertDialog.Builder localBuilder = new AlertDialog.Builder(LinesAdapter.this.mBesharpMainActivity);
              localBuilder.setTitle("Error").setMessage("You must select calendar in which your event will be saved. You want to do it now?").setCancelable(false).setNegativeButton("No", new LinesAdapter.5.1(this)).setPositiveButton("Yes", new LinesAdapter.5.2(this));
              localBuilder.create().show();
            }
            else
            {
              Uri localUri = Event.createNewEventInCalendar(LinesAdapter.this.mBesharpMainActivity.getContentResolver(), localEvent, i);
              localLine.setCalendarEventId(Long.valueOf(localEvent.id));
              localLine.setCalendarId(Integer.valueOf(i));
              Intent localIntent = new Intent("android.intent.action.EDIT");
              localIntent.setType("vnd.android.cursor.item/event");
              localIntent.setData(localUri);
              LinesAdapter.this.mBesharpMainActivity.startActivityForResult(localIntent, 4);
            }
          }
          catch (Exception localException)
          {
            LinesAdapter.this.showToast(localException.toString());
          }
        }
      };
      localButton2.setOnClickListener(local5);
      if (localLine1.getCalendarEventId() == null)
        continue;
      Integer localInteger3 = localLine1.getCalendarId();
      if (localInteger3 == null)
        continue;
      while (true)
      {
        Date localDate3;
        Date localDate4;
        SimpleDateFormat localSimpleDateFormat2;
        SimpleDateFormat localSimpleDateFormat3;
        SimpleDateFormat localSimpleDateFormat4;
        try
        {
          Event localEvent = Event.getEvent(this.mBesharpMainActivity.getContentResolver(), localLine1.getCalendarEventId(), localLine1.getCalendarId());
          localDate3 = new Date(Long.parseLong(localEvent.mStartDate));
          localDate4 = new Date(Long.parseLong(localEvent.mEndDate));
          localSimpleDateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
          localSimpleDateFormat3 = new SimpleDateFormat("dd.MM");
          localSimpleDateFormat4 = new SimpleDateFormat("HH:mm");
          if (localEvent.mAllDay != 1)
            break label1835;
          localObject = localSimpleDateFormat2.format(localDate3) + ", All day";
          localViewHolder.information_due_date.setText((CharSequence)localObject);
          localViewHolder.information_calendar_button.setText("Edit event");
          Button localButton3 = localViewHolder.information_calendar_button;
          6 local6 = new View.OnClickListener(localDate3, localDate4)
          {
            public void onClick(View paramView)
            {
              LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
              Line localLine = LinesAdapter.this.findById(LinesAdapter.this.mSelectedHolder.lineId);
              LinesAdapter.this.mBesharpMainActivity.mLineForStyleSet = localLine;
              Uri localUri = ContentUris.withAppendedId(Uri.parse(Event.getCalendarUriBase() + "/events"), localLine.getCalendarEventId().longValue());
              Intent localIntent = new Intent("android.intent.action.EDIT");
              localIntent.setType("vnd.android.cursor.item/event");
              localIntent.putExtra("beginTime", this.val$startDate.getTime());
              localIntent.putExtra("endTime", this.val$endDate.getTime());
              localIntent.setData(localUri);
              LinesAdapter.this.mBesharpMainActivity.startActivityForResult(localIntent, 5);
            }
          };
          localButton3.setOnClickListener(local6);
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
        break;
        label1835: if ((localDate3.getYear() == localDate4.getYear()) && (localDate3.getMonth() == localDate4.getMonth()) && (localDate3.getDate() == localDate4.getDate()))
        {
          localObject = localSimpleDateFormat2.format(localDate3) + ", " + localSimpleDateFormat4.format(localDate3) + " - " + localSimpleDateFormat4.format(localDate4);
          continue;
        }
        String str3 = localSimpleDateFormat3.format(localDate3) + ", " + localSimpleDateFormat4.format(localDate3) + " - " + localSimpleDateFormat3.format(localDate4) + ", " + localSimpleDateFormat4.format(localDate4);
        Object localObject = str3;
      }
      localViewHolder.details_pane.setVisibility(8);
      localViewHolder.short_desc_pane.setVisibility(0);
      int i = Color.argb(100, Color.red(localLine1.getTextColor().intValue()), Color.green(localLine1.getTextColor().intValue()), Color.blue(localLine1.getTextColor().intValue()));
      int j = Color.argb(100, Color.red(localLine1.getBackgroundColor().intValue()), Color.green(localLine1.getBackgroundColor().intValue()), Color.blue(localLine1.getBackgroundColor().intValue()));
      if (this.mBesharpMainActivity.selectedState)
      {
        localViewHolder.desc.setTextColor(i);
        localViewHolder.line_view.setBackgroundColor(j);
        label2137: if (this.mBesharpMainActivity.mDraggedLine != null)
        {
          if (!isChildOrSelfLine(this.mBesharpMainActivity.mDraggedLine, localLine1))
            break label2459;
          localViewHolder.desc.setTextColor(localLine1.getTextColor().intValue());
          localViewHolder.line_view.setBackgroundColor(localLine1.getBackgroundColor().intValue());
        }
        label2195: if (this.mBesharpMainActivity.mSearchMode)
        {
          if (localLine1.isFilteredOut != "FILTERED_OUT_NO")
            break label2482;
          localViewHolder.desc.setTextColor(localLine1.getTextColor().intValue());
          localViewHolder.line_view.setBackgroundColor(localLine1.getBackgroundColor().intValue());
        }
        label2248: if (findParent(localLine1) != null)
          break label2516;
        localViewHolder.arrow_sign_before_element.setVisibility(8);
        label2267: if (!this.mBesharpMainActivity.mReorderMode)
          break label2551;
        localViewHolder.drag_image.setVisibility(0);
      }
      while (true)
      {
        String str1 = localLine1.getLineIcon();
        localViewHolder.line_icon.setVisibility(8);
        if ((this.mBesharpMainActivity.mIcons != null) && (this.mBesharpMainActivity.mIcons.containsKey(str1)) && (str1 != null) && (str1.length() > 0))
        {
          localViewHolder.line_icon.setImageResource(((Integer)this.mBesharpMainActivity.mIcons.get(str1)).intValue());
          localViewHolder.line_icon.setVisibility(0);
        }
        if (localLine1.getLineType() != 7)
          break label2564;
        ImageButton localImageButton4 = localViewHolder.button_new_child_special;
        OnCreateNewChildListener localOnCreateNewChildListener3 = new OnCreateNewChildListener(localLine1);
        localImageButton4.setOnClickListener(localOnCreateNewChildListener3);
        localViewHolder.button_new_child_special.setVisibility(0);
        break;
        localViewHolder.desc.setTextColor(localLine1.getTextColor().intValue());
        localViewHolder.line_view.setBackgroundColor(localLine1.getBackgroundColor().intValue());
        break label2137;
        label2459: localViewHolder.desc.setTextColor(i);
        localViewHolder.line_view.setBackgroundColor(j);
        break label2195;
        label2482: if (localLine1.isFilteredOut != "FILTERED_OUT_PARENT")
          break label2248;
        localViewHolder.desc.setTextColor(i);
        localViewHolder.line_view.setBackgroundColor(j);
        break label2248;
        label2516: if (!this.mBesharpMainActivity.mReorderMode)
        {
          localViewHolder.arrow_sign_before_element.setVisibility(0);
          break label2267;
        }
        localViewHolder.arrow_sign_before_element.setVisibility(8);
        break label2267;
        label2551: localViewHolder.drag_image.setVisibility(8);
      }
      label2564: if (localLine1.getLineType() == 8)
      {
        ImageButton localImageButton3 = localViewHolder.button_new_child_special;
        OnCreateNewChildListener localOnCreateNewChildListener2 = new OnCreateNewChildListener(localLine1);
        localImageButton3.setOnClickListener(localOnCreateNewChildListener2);
        localViewHolder.button_new_child_special.setVisibility(0);
        continue;
      }
      if (localLine1.getLineType() == 1)
      {
        ImageButton localImageButton2 = localViewHolder.button_new_child_special;
        OnCreateNewChildListener localOnCreateNewChildListener1 = new OnCreateNewChildListener(localLine1);
        localImageButton2.setOnClickListener(localOnCreateNewChildListener1);
        localViewHolder.button_new_child_special.setVisibility(0);
        continue;
      }
      localViewHolder.button_new_child_special.setVisibility(8);
      continue;
      Line localLine2 = (Line)localIterator.next();
      if ((localLine2.getIsStrikedOut().booleanValue()) && (!this.mBesharpMainActivity.mShowStrikedOut))
        continue;
      Line localLine3 = findParent(localLine2);
      if ((localLine3 == null) || (!localLine3.id.equals(localLine1.id)))
        continue;
      if (!this.mBesharpMainActivity.mReorderMode)
        localViewHolder.collapse_button.setVisibility(0);
      localViewHolder.arrow_sign_before_element.setVisibility(8);
      localViewHolder.collapse_button.setTag(localLine1.id);
      ImageButton localImageButton1 = localViewHolder.collapse_button;
      7 local7 = new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Line localLine = LinesAdapter.this.findById((Integer)paramView.getTag());
          if (localLine.getIsCollapsed().booleanValue())
            localLine.setIsCollapsed(Boolean.valueOf(false));
          while (true)
          {
            LinesAdapter.this.notifyDataSetChanged();
            return;
            localLine.setIsCollapsed(Boolean.valueOf(true));
          }
        }
      };
      localImageButton1.setOnClickListener(local7);
      if (this.mBesharpMainActivity.mSearchMode)
      {
        localViewHolder.collapse_button.setImageResource(2130837575);
        continue;
      }
      if (localLine1.getIsCollapsed().booleanValue())
      {
        localViewHolder.collapse_button.setImageResource(2130837579);
        continue;
      }
      localViewHolder.collapse_button.setImageResource(2130837578);
      continue;
      label2863: if (localLine1.getLineType() == 8)
      {
        SpannableString localSpannableString2 = new SpannableString(str2);
        localSpannableString2.setSpan(new UnderlineSpan(), 0, localSpannableString2.length(), 0);
        localSpannableString2.setSpan(new StyleSpan(1), 0, localSpannableString2.length(), 0);
        localViewHolder.desc.setText(localSpannableString2, TextView.BufferType.SPANNABLE);
        continue;
      }
      localViewHolder.desc.setText(str2, TextView.BufferType.SPANNABLE);
      continue;
      label2955: if (localLine1.getIsStrikedOut().booleanValue())
      {
        Spannable localSpannable2 = (Spannable)localViewHolder.desc.getText();
        localSpannable2.setSpan(new StrikethroughSpan(), 0, localSpannable2.length(), 33);
        continue;
      }
      Spannable localSpannable1 = (Spannable)localViewHolder.desc.getText();
      localSpannable1.setSpan(new StyleSpan(0), 0, localSpannable1.length(), 33);
    }
    label3047: localViewHolder.drag_drop_start.setTag("start_drag");
    while (true)
    {
      label3061: return localView1;
      localView1 = paramView;
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public boolean hasChildren(Line paramLine)
  {
    Iterator localIterator = this.lines.values().iterator();
    if (!localIterator.hasNext());
    for (int i = 0; ; i = 1)
    {
      return i;
      Line localLine1 = (Line)localIterator.next();
      if ((localLine1.getIsStrikedOut().booleanValue()) && (!this.mBesharpMainActivity.mShowStrikedOut))
        break;
      Line localLine2 = findParent(localLine1);
      if ((localLine2 == null) || (!localLine2.id.equals(paramLine.id)))
        break;
    }
  }

  public boolean isChildLine(Line paramLine1, Line paramLine2)
  {
    for (Line localLine = findParent(paramLine2); ; localLine = findParent(localLine))
    {
      int i;
      if (localLine == null)
        i = 0;
      while (true)
      {
        return i;
        if (localLine.id == null)
        {
          i = 0;
          continue;
        }
        if (!localLine.id.equals(paramLine1.id))
          break;
        i = 1;
      }
    }
  }

  public boolean isChildOrSelfLine(Line paramLine1, Line paramLine2)
  {
    for (Line localLine = paramLine2; ; localLine = findParent(localLine))
    {
      int i;
      if (localLine == null)
        i = 0;
      while (true)
      {
        return i;
        if (localLine.id == null)
        {
          i = 0;
          continue;
        }
        if (!localLine.id.equals(paramLine1.id))
          break;
        i = 1;
      }
    }
  }

  public boolean isUnderCollapsedLine(Line paramLine)
  {
    Line localLine = paramLine;
    int i;
    if (localLine == null)
      i = 0;
    while (true)
    {
      return i;
      localLine = findParent(localLine);
      if (localLine == this.mBesharpMainActivity.root)
      {
        i = 0;
        continue;
      }
      if ((localLine == null) || (!localLine.getIsCollapsed().booleanValue()))
        break;
      i = 1;
    }
  }

  public boolean isUnderStrikedOutLine(Line paramLine)
  {
    Line localLine = paramLine;
    int i;
    if (localLine == null)
      i = 0;
    while (true)
    {
      return i;
      localLine = findParent(localLine);
      if (localLine == this.mBesharpMainActivity.root)
      {
        i = 0;
        continue;
      }
      if ((localLine == null) || (!localLine.getIsStrikedOut().booleanValue()))
        break;
      i = 1;
    }
  }

  public void logInfo(String paramString)
  {
    Log.i("LinesAdapter", paramString);
  }

  public void moveLineAfter(Line paramLine1, Line paramLine2)
  {
    try
    {
      if (((paramLine1.getSortOrder() != 2 + paramLine2.getSortOrder()) || (((paramLine1.getParentLine() != null) || (paramLine2.getParentLine() != null)) && ((paramLine1.getParentLine() == null) || (paramLine2.getParentLine() == null) || (!paramLine1.getParentLine().id.equals(paramLine2.getParentLine().id))))) && (paramLine1.getSortOrder() != paramLine2.getSortOrder()))
      {
        paramLine1.setParentLine(findParent(paramLine2));
        paramLine1.setSortOrder(1 + paramLine2.getSortOrder());
        updateSortOrderInMemory();
        sortLines();
        notifyDataSetChanged();
      }
    }
    catch (Exception localException)
    {
      showToast(localException.toString());
      localException.printStackTrace();
    }
  }

  public void moveLineBefore(Line paramLine1, Line paramLine2)
  {
    try
    {
      if (((paramLine1.getSortOrder() != paramLine2.getSortOrder() - 2) || (((paramLine1.getParentLine() != null) || (paramLine2.getParentLine() != null)) && ((paramLine1.getParentLine() == null) || (paramLine2.getParentLine() == null) || (paramLine1.getParentLine().id != paramLine2.getParentLine().id)))) && (paramLine1.getSortOrder() != paramLine2.getSortOrder()))
      {
        paramLine1.setParentLine(findParent(paramLine2));
        paramLine1.setSortOrder(paramLine2.getSortOrder() - 1);
        updateSortOrderInMemory();
        sortLines();
        notifyDataSetChanged();
      }
    }
    catch (Exception localException)
    {
      showToast(localException.toString());
      localException.printStackTrace();
    }
  }

  public void moveLineDecreaseIndent(Line paramLine1, Line paramLine2)
  {
    try
    {
      if (paramLine1.id == paramLine2.id)
      {
        Line localLine = findParent(paramLine1);
        if (localLine != null)
        {
          paramLine1.setParentLine(findParent(localLine));
          updateSortOrderInMemory();
          sortLines();
          notifyDataSetChanged();
        }
      }
    }
    catch (Exception localException)
    {
      showToast(localException.toString());
      localException.printStackTrace();
    }
  }

  public void moveLineFirstChild(Line paramLine1, Line paramLine2)
  {
    try
    {
      if (((paramLine1.getSortOrder() != 2 + paramLine2.getSortOrder()) || (paramLine1.getParentLine() == null) || (paramLine1.getParentLine().id != paramLine2.id)) && (paramLine1.getSortOrder() != paramLine2.getSortOrder()))
      {
        paramLine1.setParentLine(paramLine2);
        paramLine1.setSortOrder(1 + paramLine2.getSortOrder());
        updateSortOrderInMemory();
        sortLines();
        notifyDataSetChanged();
      }
    }
    catch (Exception localException)
    {
      showToast(localException.toString());
      localException.printStackTrace();
    }
  }

  public void notifyDataSetChanged()
  {
    recreateVisibleLines();
    super.notifyDataSetChanged();
  }

  public void onDrop(int paramInt1, int paramInt2)
  {
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramInt == 66));
    try
    {
      saveCurrentlyVisibleLineInMemory();
      this.mBesharpMainActivity.selectedState = false;
      notifyDataSetChanged();
      return false;
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

  public void recreateVisibleLines()
  {
    this.visibleLines = new ArrayList();
    Boolean localBoolean = Boolean.valueOf(false);
    Iterator localIterator;
    if (this.lines != null)
      localIterator = this.lines.values().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Line localLine = (Line)localIterator.next();
      if (((isUnderCollapsedLine(localLine)) && (!this.mBesharpMainActivity.mSearchMode)) || ((!this.mBesharpMainActivity.mShowStrikedOut) && ((isUnderStrikedOutLine(localLine)) || (localLine.getIsStrikedOut().booleanValue()))))
        continue;
      if (localLine.isFilteredOut == "FILTERED_OUT_YES")
      {
        if (localBoolean.booleanValue())
          continue;
        this.visibleLines.add(createDummyLine(localLine));
        localBoolean = Boolean.valueOf(true);
        continue;
      }
      this.visibleLines.add(localLine);
      localBoolean = Boolean.valueOf(false);
    }
  }

  public void refreshWholeTreeFromDb()
    throws Exception
  {
    Iterator localIterator = this.lines.values().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Line localLine = (Line)localIterator.next();
      this.mLinesDao.refresh(localLine);
    }
  }

  public void removeTag(Line paramLine, Tag paramTag)
    throws Exception
  {
    this.mLineTagInterDao.removeTagFromLine(paramLine, paramTag);
    displayTags(paramLine, this.mSelectedHolder.layout_for_tags, this.mBesharpMainActivity.getApplicationContext());
  }

  public void saveCurrentlyVisibleLineInMemory()
  {
    if (this.mSelectedHolder != null)
    {
      Line localLine = (Line)this.lines.get(this.mSelectedHolder.lineId);
      if (localLine != null)
        localLine.setDesc(this.mSelectedHolder.edit_text_detailed_desc.getText().toString());
    }
  }

  public void saveLines()
    throws Exception
  {
    this.mLinesDao.saveLines(this.lines);
  }

  public void setFilter(String paramString)
  {
    try
    {
      this.mFilterString = paramString;
      setFilteredState();
      notifyDataSetChanged();
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

  public void setFilteredTag(Tag paramTag)
  {
    try
    {
      this.mFilterTag = paramTag;
      setFilteredState();
      notifyDataSetChanged();
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

  public void setLines(HashMap<Integer, Line> paramHashMap)
    throws Exception
  {
    this.lines = paramHashMap;
    updateSortOrderInMemory();
    sortLines();
    notifyDataSetChanged();
  }

  private final class OnCreateNewAboveListener
    implements View.OnClickListener
  {
    private OnCreateNewAboveListener()
    {
    }

    public void onClick(View paramView)
    {
      try
      {
        LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
        Line localLine1 = LinesAdapter.this.findById(LinesAdapter.this.mSelectedHolder.lineId);
        Line localLine2 = new Line();
        localLine2.setDesc("");
        localLine2.setParentLine(LinesAdapter.this.findParent(localLine1));
        localLine2.setSortOrder(localLine1.getSortOrder() - 1);
        LinesAdapter.this.mLinesDao.create(localLine2);
        LinesAdapter.this.lines.put(localLine2.id, localLine2);
        LinesAdapter.this.updateSortOrderInMemory();
        LinesAdapter.this.sortLines();
        LinesAdapter.this.recreateVisibleLines();
        LinesAdapter.this.expandLine(localLine2.id.intValue());
        LinesAdapter.this.notifyDataSetChanged();
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          LinesAdapter.this.showToast(localException.toString());
          localException.printStackTrace();
        }
      }
    }
  }

  private final class OnCreateNewBelowListener
    implements View.OnClickListener
  {
    private OnCreateNewBelowListener()
    {
    }

    public void onClick(View paramView)
    {
      try
      {
        LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
        Line localLine1 = LinesAdapter.this.findById(LinesAdapter.this.mSelectedHolder.lineId);
        Line localLine2 = new Line();
        localLine2.setDesc("");
        localLine2.setParentLine(LinesAdapter.this.findParent(localLine1));
        localLine2.setSortOrder(1 + localLine1.getSortOrder());
        LinesAdapter.this.mLinesDao.create(localLine2);
        LinesAdapter.this.lines.put(localLine2.id, localLine2);
        LinesAdapter.this.updateSortOrderInMemory();
        LinesAdapter.this.sortLines();
        LinesAdapter.this.recreateVisibleLines();
        LinesAdapter.this.expandLine(localLine2.id.intValue());
        LinesAdapter.this.notifyDataSetChanged();
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          LinesAdapter.this.showToast(localException.toString());
          localException.printStackTrace();
        }
      }
    }
  }

  private final class OnCreateNewChildListener
    implements View.OnClickListener
  {
    Line mSpecialLineSelected;

    public OnCreateNewChildListener(Line arg2)
    {
      Object localObject;
      this.mSpecialLineSelected = localObject;
    }

    public void onClick(View paramView)
    {
      try
      {
        LinesAdapter.this.saveCurrentlyVisibleLineInMemory();
        if (this.mSpecialLineSelected != null);
        Line localLine1;
        for (Object localObject = this.mSpecialLineSelected; ; localObject = localLine1)
        {
          Line localLine2 = new Line();
          localLine2.setDesc("");
          localLine2.setParentLine((Line)localObject);
          localLine2.setSortOrder(1 + ((Line)localObject).getSortOrder());
          LinesAdapter.this.mLinesDao.create(localLine2);
          LinesAdapter.this.lines.put(localLine2.id, localLine2);
          LinesAdapter.this.updateSortOrderInMemory();
          LinesAdapter.this.sortLines();
          LinesAdapter.this.recreateVisibleLines();
          LinesAdapter.this.expandLine(localLine2.id.intValue());
          LinesAdapter.this.notifyDataSetChanged();
          break;
          localLine1 = LinesAdapter.this.findById(LinesAdapter.this.mSelectedHolder.lineId);
        }
      }
      catch (Exception localException)
      {
        LinesAdapter.this.showToast(localException.toString());
        localException.printStackTrace();
      }
    }
  }

  private final class OnDeleteListener
    implements View.OnClickListener
  {
    private OnDeleteListener()
    {
    }

    public void onClick(View paramView)
    {
      try
      {
        Line localLine = LinesAdapter.this.findById(LinesAdapter.this.mSelectedHolder.lineId);
        LinesAdapter.this.mBesharpMainActivity.deleteLine(localLine);
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          LinesAdapter.this.showToast(localException.toString());
          localException.printStackTrace();
        }
      }
    }
  }

  static class ViewHolder
  {
    ImageView arrow_sign_before_element;
    Button button_add_tag;
    ImageButton button_create_new_above;
    ImageButton button_create_new_below;
    ImageButton button_create_new_child;
    ImageButton button_delete_line;
    ImageButton button_new_child_special;
    ImageButton collapse_button;
    TextView desc;
    View details_pane;
    TextView drag_drop_after;
    TextView drag_drop_before;
    TextView drag_drop_right;
    TextView drag_drop_start;
    ImageView drag_image;
    EditText edit_text_detailed_desc;
    Button information_calendar_button;
    TextView information_calendar_event_info;
    Button information_change_due_date;
    TextView information_due_date;
    PredicateLayout layout_for_tags;
    Integer lineId;
    LinearLayout line_dummy_pane;
    ImageView line_icon;
    LinearLayout line_view;
    TextView padding;
    Integer position;
    View short_desc_pane;
    TextView task_due_date;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     ru.humantouch.besharp.LinesAdapter
 * JD-Core Version:    0.6.0
 */