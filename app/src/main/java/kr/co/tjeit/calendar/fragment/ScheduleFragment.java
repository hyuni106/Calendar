package kr.co.tjeit.calendar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.ViewScheduleActivity;
import kr.co.tjeit.calendar.adapter.ScheduleAdapter;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.util.GlobalData;

/**
 * Created by the on 2017-11-23.
 */

public class ScheduleFragment extends Fragment {
    private android.widget.ListView scheduleListView;
    List<Schedule> mList = new ArrayList<>();
    ScheduleAdapter mAdapter;
    private android.widget.TextView noCalendarAlertTxt;

    public static ScheduleFragment scheduleFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        this.noCalendarAlertTxt = (TextView) v.findViewById(R.id.noCalendarAlertTxt);
        this.scheduleListView = (ListView) v.findViewById(R.id.scheduleListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scheduleFragment = this;
        setupEvents();
        setValues();
    }

    private void setValues() {
        if (GlobalData.allSchedule.size() != 0) {
            noCalendarAlertTxt.setVisibility(View.GONE);
            scheduleListView.setVisibility(View.VISIBLE);
            mAdapter = new ScheduleAdapter(getContext(), GlobalData.allSchedule);
            scheduleListView.setAdapter(mAdapter);
        } else {
            noCalendarAlertTxt.setVisibility(View.VISIBLE);
            scheduleListView.setVisibility(View.GONE);
        }
    }

    private void setupEvents() {
        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ViewScheduleActivity.class);
                intent.putExtra("schedule_item", GlobalData.allSchedule.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (GlobalData.allSchedule.size() != 0) {
            noCalendarAlertTxt.setVisibility(View.GONE);
            scheduleListView.setVisibility(View.VISIBLE);
            mAdapter = new ScheduleAdapter(getContext(), GlobalData.allSchedule);
            scheduleListView.setAdapter(mAdapter);
        }
    }
}
