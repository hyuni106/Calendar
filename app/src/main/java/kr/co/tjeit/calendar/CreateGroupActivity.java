package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.adapter.MemberAdapter;
import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.data.User;
import kr.co.tjeit.calendar.util.GlobalData;

public class CreateGroupActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private com.balysv.materialmenu.MaterialMenuView checkBtn;
    private android.widget.EditText groupNameEdt;
    private android.widget.EditText groupInfoEdt;
    private android.widget.ListView memberListView;
    private android.widget.LinearLayout inviteMemberLayout;

    List<User> inviteUserList = new ArrayList<>();
    MemberAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
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

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalData.usersGroup.add(new Group(GlobalData.usersGroup.size() + 1, groupNameEdt.getText().toString(), groupInfoEdt.getText().toString()));
                finish();
            }
        });

        inviteMemberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, InviteMemberActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void setValues() {
        mAdapter = new MemberAdapter(mContext, inviteUserList);
        memberListView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(mContext, "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == 1) {
            User addUser = (User) data.getSerializableExtra("user");
            inviteUserList.add(addUser);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(mContext, "REQUEST_ACT가 아님", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void bindViews() {
        this.memberListView = (ListView) findViewById(R.id.memberListView);
        this.inviteMemberLayout = (LinearLayout) findViewById(R.id.inviteMemberLayout);
        this.groupInfoEdt = (EditText) findViewById(R.id.groupInfoEdt);
        this.groupNameEdt = (EditText) findViewById(R.id.groupNameEdt);
        this.checkBtn = (MaterialMenuView) findViewById(R.id.checkBtn);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
