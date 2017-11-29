package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import kr.co.tjeit.calendar.adapter.AlertAdapter;
import kr.co.tjeit.calendar.util.GlobalData;

public class AlertActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.ListView alertListView;
    private android.widget.TextView noAlertTxt;

    AlertAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
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
    }

    @Override
    public void setValues() {
        if (GlobalData.allParticipantAlert.size() == 0) {
            noAlertTxt.setVisibility(View.VISIBLE);
            alertListView.setVisibility(View.GONE);
        } else {
            mAdapter = new AlertAdapter(mContext, GlobalData.allParticipantAlert);
            alertListView.setAdapter(mAdapter);
            noAlertTxt.setVisibility(View.GONE);
            alertListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void bindViews() {
        this.noAlertTxt = (TextView) findViewById(R.id.noAlertTxt);
        this.alertListView = (ListView) findViewById(R.id.alertListView);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
