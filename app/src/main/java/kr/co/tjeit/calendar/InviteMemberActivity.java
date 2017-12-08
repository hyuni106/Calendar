package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("user", GlobalData.allUser.get(i));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {
        if (intentActivity.equals("1")) {
            for (User u : GlobalData.allUser) {
                if (u.getId() != ContextUtil.getUserData(mContext).getId()) {
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
