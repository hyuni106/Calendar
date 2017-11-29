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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.AppSettingActivity;
import kr.co.tjeit.calendar.CalendarSettingActivity;
import kr.co.tjeit.calendar.InviteMemberActivity;
import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.adapter.MemberAdapter;
import kr.co.tjeit.calendar.data.User;

import static android.app.Activity.RESULT_OK;

/**
 * Created by the on 2017-11-27.
 */

public class SettingFragment extends Fragment {
    private ListView memberListView;
    private android.widget.LinearLayout appSettingLayout;
    private android.widget.LinearLayout addMemberLayout;
    private LinearLayout calendarSettingLayout;

    List<User> memberList = new ArrayList<>();
    MemberAdapter mAdapter;

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
        mAdapter = new MemberAdapter(getContext(), memberList);
        memberListView.setAdapter(mAdapter);
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

        addMemberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InviteMemberActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(getContext(), "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == 1) {
            User addUser = (User) data.getSerializableExtra("user");
            memberList.add(addUser);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "REQUEST_ACT가 아님", Toast.LENGTH_SHORT).show();
        }
    }
}
