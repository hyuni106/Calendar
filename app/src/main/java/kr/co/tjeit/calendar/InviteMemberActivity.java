package kr.co.tjeit.calendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.adapter.InviteAdapter;
import kr.co.tjeit.calendar.adapter.MemberAdapter;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.data.User;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class InviteMemberActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.ListView userListView;

    InviteAdapter mAdapter;

    String intentActivity = null;
    List<User> viewUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_member);
        intentActivity = getIntent().getStringExtra("intent");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (intentActivity.equals("1")) {
                    Intent intent = new Intent();
                    intent.putExtra("user", GlobalData.allUser.get(i));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    new AlertDialog.Builder(mContext)
                            .setTitle("초대")
                            .setMessage("이 사용자를 초대하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ServerUtil.inviteOneUser(mContext, ContextUtil.getUserData(mContext).getId(), viewUser.get(i).getId(), ContextUtil.getRecentGroupId(mContext), new ServerUtil.JsonResponseHandler() {
                                        @Override
                                        public void onResponse(JSONObject json) {
                                            try {
                                                Intent intent = new Intent();
                                                JSONObject invite = json.getJSONObject("invite");
                                                Participant p = Participant.getParticipantFromJson(invite);
                                                intent.putExtra("invite", p);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                }
            }
        });
    }

    @Override
    public void setValues() {
        viewUser.clear();

        if (intentActivity.equals("1")) {
            for (User u : GlobalData.allUser) {
                if (u.getId() != ContextUtil.getUserData(mContext).getId()) {
                    viewUser.add(u);
                }
            }
        } else {
            for (User u : GlobalData.allUser) {
                boolean isParticipant = true;
                for (Participant p : GlobalData.allParticipantAlert) {
                    if (u.getId() == p.getMember().getId()) {
                        isParticipant = false;
                    }
                }
                if (isParticipant) {
                    viewUser.add(u);
                }
            }
        }

        mAdapter = new InviteAdapter(mContext, viewUser);
        userListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.userListView = (ListView) findViewById(R.id.userListView);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
