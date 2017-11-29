package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import kr.co.tjeit.calendar.data.Schedule;

public class ViewScheduleActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.LinearLayout startScheduleLayout;
    private android.widget.LinearLayout endScheduleLayout;
    private android.widget.TextView likeBtn;
    private android.widget.TextView shareBtn;
    private TextView categoryTxt;
    private TextView titleTxt;
    private TextView locationTxt;
    private TextView memoTxt;

    Schedule view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        view = (Schedule) getIntent().getSerializableExtra("schedule_item");
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
        titleTxt.setText(view.getTitle());
        memoTxt.setText(view.getMemo());
        locationTxt.setText(view.getLocation());
    }

    @Override
    public void bindViews() {
        this.shareBtn = (TextView) findViewById(R.id.shareBtn);
        this.likeBtn = (TextView) findViewById(R.id.likeBtn);
        this.memoTxt = (TextView) findViewById(R.id.memoTxt);
        this.locationTxt = (TextView) findViewById(R.id.locationTxt);
        this.endScheduleLayout = (LinearLayout) findViewById(R.id.endScheduleLayout);
        this.startScheduleLayout = (LinearLayout) findViewById(R.id.startScheduleLayout);
        this.titleTxt = (TextView) findViewById(R.id.titleTxt);
        this.categoryTxt = (TextView) findViewById(R.id.categoryTxt);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
