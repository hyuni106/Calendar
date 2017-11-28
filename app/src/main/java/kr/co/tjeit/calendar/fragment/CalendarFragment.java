package kr.co.tjeit.calendar.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import kr.co.tjeit.calendar.AddScheduleActivity;
import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.adapter.CalendarAdapter;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.util.GlobalData;

import static android.app.Activity.RESULT_OK;

/**
 * Created by the on 2017-11-23.
 */

public class CalendarFragment extends Fragment {
    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;
    private com.melnykov.fab.FloatingActionButton fab;
    List<Schedule> mList = new ArrayList<>();
    CalendarAdapter mAdapter;
    private android.widget.ListView calendarListView;
    private android.widget.TextView noCalendarAlertTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        this.noCalendarAlertTxt = (TextView) v.findViewById(R.id.noCalendarAlertTxt);
        this.calendarListView = (ListView) v.findViewById(R.id.calendarListView);
        this.fab = (FloatingActionButton) v.findViewById(R.id.fab);
        this.calendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {
        settingCalendar();
        if (mList.size() == 0) {
            noCalendarAlertTxt.setVisibility(View.VISIBLE);

        } else {
            mAdapter = new CalendarAdapter(getContext(), mList);
            calendarListView.setAdapter(mAdapter);
        }
    }

    private void settingCalendar() {
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 MM월");
        DateFormatTitleFormatter formatter = new DateFormatTitleFormatter(myDateFormat);
        calendarView.setTitleFormatter(formatter);
        calendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                // check if weekday is sunday
                return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                // add red foreground span
                view.addSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(getContext(), R.color.red)));
            }
        });

        calendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                // check if weekday is sunday
                return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                // add red foreground span
                view.addSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(getContext(), R.color.blue)));
            }
        });

        final CalendarDay date = CalendarDay.today();

        calendarView.setSelectedDate(date);
        calendarView.addDecorator(new DayViewDecorator() {

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return date != null && day.equals(date);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new StyleSpan(Typeface.BOLD));
                view.addSpan(new RelativeSizeSpan(1.2f));
                view.addSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(getContext(), R.color.honey_flower)));
            }
        });
    }

    private void setupEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddScheduleActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void addDecorator() {
        CalendarDay date = calendarView.getSelectedDate();
//        CalendarDay date = CalendarDay.today();
        ArrayList<CalendarDay> dates = new ArrayList<>();
        dates.add(date);
        calendarView.addDecorator(new EventDecorator(Color.RED, dates));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mList.size() == 0) {
            noCalendarAlertTxt.setVisibility(View.VISIBLE);
            calendarListView.setVisibility(View.GONE);
        } else {
            mAdapter = new CalendarAdapter(getContext(), mList);
            calendarListView.setAdapter(mAdapter);
            noCalendarAlertTxt.setVisibility(View.GONE);
            calendarListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(getContext(), "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == 1) {
            Schedule add = (Schedule) data.getSerializableExtra("schedule");
            mList.add(add);
            addDecorator();
        } else {
            Toast.makeText(getContext(), "REQUEST_ACT가 아님", Toast.LENGTH_SHORT).show();
        }
    }

    private class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        private EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));
        }
    }

}
