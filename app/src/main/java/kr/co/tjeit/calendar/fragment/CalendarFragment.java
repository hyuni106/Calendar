package kr.co.tjeit.calendar.fragment;

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

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kr.co.tjeit.calendar.R;

/**
 * Created by the on 2017-11-23.
 */

public class CalendarFragment extends Fragment {
    private com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
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
                        ContextCompat.getColor(getContext(), R.color.firstColor)));
            }
        });
    }

    private void setupEvents() {

    }
}
