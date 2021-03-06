package com.bigkoo.pickerview.view;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.ArrayWheelAdapter;
import com.bigkoo.pickerview.NumericWheelAdapter;
import com.bigkoo.pickerview.OnItemSelectedListener;
import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.TimePickerView.Type;
import com.bigkoo.pickerview.lib.WheelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class WheelTime {
    public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    private static final String[] SOLAR_MONTH_ARRAY = {"1", "3", "5", "7", "8", "10", "12"};
    private static final String[] MONTH_ARRAY = {"4", "6", "9", "11"};

    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView moring_and_after;
    private WheelView wv_hours;
    private WheelView wv_mins;

    private Type type;
    private int startYear = 0;
    private int endYear = 0;

    private List<String> mSolarMonthList = Arrays.asList(SOLAR_MONTH_ARRAY);
    private List<String> mMonthList = Arrays.asList(MONTH_ARRAY);

    public WheelTime(View view) {
        this(view, Type.ALL);
    }

    public WheelTime(View view, Type type) {
        super();
        this.view = view;
        this.type = type;
        setView(view);
    }

    public void setPicker(int year, int month, int day) {
        this.setPicker(year, month, day, 0, 0);
    }

    /**
     * 弹出日期时间选择器
     */
    public void setPicker(int year, int month, int day, int h, int m) {
        Context context = view.getContext();
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));
        wv_year.setCurrentItem(year - startYear);

        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new NumericWheelAdapter(1, 12));
        wv_month.setCurrentItem(month);

        wv_day = (WheelView) view.findViewById(R.id.day);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (mSolarMonthList.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (mMonthList.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        } else {// 二月
            // 判断是否为闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            } else {
                wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
        }
        wv_day.setCurrentItem(day - 1);

        wv_hours = (WheelView) view.findViewById(R.id.hour);
        wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
        wv_hours.setLabel(context.getString(R.string.pickerview_hours));
        wv_hours.setCurrentItem(h);

        wv_mins = (WheelView) view.findViewById(R.id.min);
        wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
        wv_mins.setLabel(context.getString(R.string.pickerview_minutes));
        wv_mins.setCurrentItem(m);

        moring_and_after = (WheelView) view.findViewById(R.id.moring_and_after);
        ArrayList<String> list = new ArrayList<>();
        list.add("上午");
        list.add("下午");
        moring_and_after.setAdapter(new ArrayWheelAdapter(list, 2));// 设置显示数据
        moring_and_after.setCurrentItem(0);

        // 添加"年"监听
        OnItemSelectedListener wheelListener_year = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int year_num = index + startYear;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int maxItem = 30;
                if (mSolarMonthList.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                } else if (mMonthList.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0) {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    } else {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1) {
                    wv_day.setCurrentItem(maxItem - 1);
                }
            }
        };
        // 添加"月"监听
        OnItemSelectedListener wheelListener_month = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int month_num = index + 1;
                int maxItem = 30;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (mSolarMonthList.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                } else if (mMonthList.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                } else {
                    if (((wv_year.getCurrentItem() + startYear) % 4 == 0 && (wv_year.getCurrentItem() + startYear) % 100 != 0)
                            || (wv_year.getCurrentItem() + startYear) % 400 == 0) {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    } else {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1) {
                    wv_day.setCurrentItem(maxItem - 1);
                }
            }
        };
        wv_year.setOnItemSelectedListener(wheelListener_year);
        wv_month.setOnItemSelectedListener(wheelListener_month);

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 6;
        switch (type) {
            case ALL:
                textSize = textSize * 3;
                moring_and_after.setVisibility(View.GONE);
                break;
            case YEAR_MONTH_DAY_MORNING:
                textSize = textSize * 3;
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                break;
            case YEAR_MONTH_DAY:
                textSize = textSize * 3;
                moring_and_after.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                break;
            case HOUR_MINUTE:
                textSize = textSize * 4;
                wv_year.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                moring_and_after.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MINUTE:
                textSize = textSize * 3;
                wv_year.setVisibility(View.GONE);
                moring_and_after.setVisibility(View.GONE);
                break;
            case YEAR_MONTH:
                textSize = textSize * 4;
                moring_and_after.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
        }
        wv_day.setTextSize(textSize);
        wv_month.setTextSize(textSize);
        wv_year.setTextSize(textSize);
        wv_hours.setTextSize(textSize);
        wv_mins.setTextSize(textSize);
        moring_and_after.setTextSize(textSize);
    }

    private void addDayListener(int year, int month) {
        if (mSolarMonthList.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (mMonthList.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            } else {
                wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
        }
    }

    /**
     * 设置是否循环滚动
     * @param cyclic true or false
     */
    public void setCyclic(boolean cyclic) {
        wv_year.setCyclic(cyclic);
        wv_month.setCyclic(cyclic);
        wv_day.setCyclic(cyclic);
        wv_hours.setCyclic(cyclic);
        wv_mins.setCyclic(cyclic);
        moring_and_after.setCyclic(false);
    }

    public String getTime() {
        StringBuffer buffer = new StringBuffer();
        buffer.append((wv_year.getCurrentItem() + startYear)).append("-")
                .append((wv_month.getCurrentItem() + 1)).append("-")
                .append((wv_day.getCurrentItem() + 1)).append(" ")
                .append(wv_hours.getCurrentItem()).append(":")
                .append(wv_mins.getCurrentItem());
        return buffer.toString();
    }

    public String getMorningOrAfter() {
        int index = moring_and_after.getCurrentItem();
        if (index == 0) {
            return "上午";
        } else {
            return "下午";
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
