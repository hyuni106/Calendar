package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.balysv.materialmenu.MaterialMenuView;

import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.util.GlobalData;

public class CreateGroupActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private com.balysv.materialmenu.MaterialMenuView checkBtn;
    private android.widget.EditText groupNameEdt;
    private android.widget.EditText groupInfoEdt;
    private android.widget.ListView memberListView;

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
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.memberListView = (ListView) findViewById(R.id.memberListView);
        this.groupInfoEdt = (EditText) findViewById(R.id.groupInfoEdt);
        this.groupNameEdt = (EditText) findViewById(R.id.groupNameEdt);
        this.checkBtn = (MaterialMenuView) findViewById(R.id.checkBtn);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
