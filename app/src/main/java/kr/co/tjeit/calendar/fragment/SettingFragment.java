package kr.co.tjeit.calendar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.AppSettingActivity;
import kr.co.tjeit.calendar.CalendarSettingActivity;
import kr.co.tjeit.calendar.InviteMemberActivity;
import kr.co.tjeit.calendar.MainActivity;
import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.adapter.MemberAdapter;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.data.User;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

import static android.app.Activity.RESULT_OK;

/**
 * Created by the on 2017-11-27.
 */

public class SettingFragment extends Fragment {
    private ListView memberListView;
    private android.widget.LinearLayout appSettingLayout;
    private android.widget.LinearLayout addMemberLayout;
    private LinearLayout calendarSettingLayout;

    MemberAdapter mAdapter;
    private android.widget.TextView groupNameTxt;
    private android.widget.TextView groupCommentTxt;

    public static SettingFragment settingFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        this.groupCommentTxt = (TextView) v.findViewById(R.id.groupCommentTxt);
        this.groupNameTxt = (TextView) v.findViewById(R.id.groupNameTxt);
        this.calendarSettingLayout = (LinearLayout) v.findViewById(R.id.calendarSettingLayout);
        this.addMemberLayout = (LinearLayout) v.findViewById(R.id.addMemberLayout);
        this.appSettingLayout = (LinearLayout) v.findViewById(R.id.appSettingLayout);
        this.memberListView = (ListView) v.findViewById(R.id.memberListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingFragment = this;
        setupEvents();
        setValues();
    }

    private void setValues() {
        getAllMember();
    }

    public void getAllMember() {
        GlobalData.allParticipantAlert.clear();
        ServerUtil.getParticipantUser(getContext(), ContextUtil.getRecentGroupId(getContext()), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray participant = json.getJSONArray("participant");
                    for (int i=0; i<participant.length(); i++) {
                        Participant p = Participant.getParticipantFromJson(participant.getJSONObject(i));
                        GlobalData.allParticipantAlert.add(p);
                    }
                    JSONArray invite = json.getJSONArray("invite");
                    for (int i=0; i<invite.length(); i++) {
                        Participant p = Participant.getParticipantFromJson(invite.getJSONObject(i));
                        GlobalData.allParticipantAlert.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter = new MemberAdapter(getContext(), GlobalData.allParticipantAlert);
                memberListView.setAdapter(mAdapter);
            }
        });
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
                intent.putExtra("intent", "2");
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
            Participant add = new Participant(GlobalData.allParticipantAlert.size()+1, 0);
            User addUser = (User) data.getSerializableExtra("user");
            add.setMember(addUser);
            GlobalData.allParticipantAlert.add(add);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "REQUEST_ACT가 아님", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        groupNameTxt.setText(MainActivity.mainActivity.returnMainGroup().getName());
        groupCommentTxt.setText(MainActivity.mainActivity.returnMainGroup().getComment());
    }
}
