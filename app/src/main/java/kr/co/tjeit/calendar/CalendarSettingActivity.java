package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class CalendarSettingActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.EditText groupNameEdt;
    private android.widget.EditText groupCommentEdt;
    private Button changeBtn;

    Group userGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_setting);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerUtil.updateGroupInfo(mContext, groupNameEdt.getText().toString(), groupCommentEdt.getText().toString(), MainActivity.mainActivity.returnMainGroup().getId(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    JSONObject group = json.getJSONObject("group");
                                    userGroup.setName(group.getString("name"));
                                    userGroup.setComment(group.getString("comment"));
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void setValues() {
        userGroup = MainActivity.mainActivity.returnMainGroup();
        groupNameEdt.setText(MainActivity.mainActivity.returnMainGroup().getName());
        groupCommentEdt.setText(MainActivity.mainActivity.returnMainGroup().getComment());
    }

    @Override
    public void bindViews() {
        this.changeBtn = (Button) findViewById(R.id.changeBtn);
        this.groupCommentEdt = (EditText) findViewById(R.id.groupCommentEdt);
        this.groupNameEdt = (EditText) findViewById(R.id.groupNameEdt);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
