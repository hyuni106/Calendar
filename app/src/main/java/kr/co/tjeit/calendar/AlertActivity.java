package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.calendar.adapter.AlertAdapter;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class AlertActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.ListView alertListView;
    private android.widget.TextView noAlertTxt;

    AlertAdapter mAdapter;

    public static AlertActivity alertActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        alertActivity = this;
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
//        getUserAlert();
        mAdapter = new AlertAdapter(mContext, GlobalData.allParticipantAlert);
        alertListView.setAdapter(mAdapter);

        showAlert();

    }

    void showAlert() {
        if (GlobalData.allParticipantAlert.size() == 0) {
            noAlertTxt.setVisibility(View.VISIBLE);
            alertListView.setVisibility(View.GONE);
        } else {
            noAlertTxt.setVisibility(View.GONE);
            alertListView.setVisibility(View.VISIBLE);
        }
    }

    public void getUserAlert() {
        GlobalData.allParticipantAlert.clear();
        ServerUtil.getAllAlert(mContext, ContextUtil.getUserData(mContext).getId(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray alert = json.getJSONArray("alert");
                    for (int i=0; i<alert.length(); i++) {
                        Participant p = Participant.getParticipantFromJson(alert.getJSONObject(i));
                        GlobalData.allParticipantAlert.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showAlert();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserAlert();
    }

    @Override
    public void bindViews() {
        this.noAlertTxt = (TextView) findViewById(R.id.noAlertTxt);
        this.alertListView = (ListView) findViewById(R.id.alertListView);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
