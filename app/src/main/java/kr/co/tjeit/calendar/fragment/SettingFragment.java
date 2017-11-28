package kr.co.tjeit.calendar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import kr.co.tjeit.calendar.AppSettingActivity;
import kr.co.tjeit.calendar.CalendarSettingActivity;
import kr.co.tjeit.calendar.R;

/**
 * Created by the on 2017-11-27.
 */

public class SettingFragment extends Fragment {
    private ListView memberListView;
    private android.widget.LinearLayout appSettingLayout;
    private android.widget.LinearLayout addMemberLayout;
    private LinearLayout calendarSettingLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        this.calendarSettingLayout = (LinearLayout) v.findViewById(R.id.calendarSettingLayout);
        this.addMemberLayout = (LinearLayout) v.findViewById(R.id.addMemberLayout);
        this.appSettingLayout = (LinearLayout) v.findViewById(R.id.appSettingLayout);
        this.memberListView = (ListView) v.findViewById(R.id.memberListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {

    }

    private void setupEvents() {
        View.OnClickListener settingIntent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (v.getId() == R.id.calendarSettingLayout) {
                    intent = new Intent(getContext(), CalendarSettingActivity.class);
                } else if (v.getId() == R.id.appSettingLayout) {
                    intent = new Intent(getContext(), AppSettingActivity.class);
                }
                startActivity(intent);
            }
        };
        calendarSettingLayout.setOnClickListener(settingIntent);
        appSettingLayout.setOnClickListener(settingIntent);
    }
}
