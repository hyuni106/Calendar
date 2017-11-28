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

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.ViewScheduleActivity;
import kr.co.tjeit.calendar.adapter.ScheduleAdapter;
import kr.co.tjeit.calendar.data.Schedule;

/**
 * Created by the on 2017-11-23.
 */

public class ScheduleFragment extends Fragment {
    private android.widget.ListView scheduleListView;
    List<Schedule> mList = new ArrayList<>();
    ScheduleAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        this.scheduleListView = (ListView) v.findViewById(R.id.scheduleListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {
        mAdapter = new ScheduleAdapter(getContext(), mList);
        scheduleListView.setAdapter(mAdapter);
    }

    private void setupEvents() {
        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ViewScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}
