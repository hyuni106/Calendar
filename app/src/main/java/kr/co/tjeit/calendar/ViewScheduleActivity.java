package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.adapter.AttendAdapter;
import kr.co.tjeit.calendar.data.Attend;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.ServerUtil;

public class ViewScheduleActivity extends BaseActivity {
    public static ViewScheduleActivity viewScheduleActivity;

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.LinearLayout startScheduleLayout;
    private android.widget.LinearLayout endScheduleLayout;
    private android.widget.TextView shareBtn;
    private TextView categoryTxt;
    private TextView titleTxt;
    private TextView locationTxt;
    private TextView startDateTxt;
    private TextView startTimeTxt;
    private TextView endDateTxt;
    private TextView endTimeTxt;
    private TextView memoTxt;

    Schedule schedule;
    private TextView middleTxt;
    private android.widget.ImageView locationImageView;
    private android.widget.ImageView memoImageView;
    private TextView attendBtn;

    List<Attend> mList = new ArrayList<>();
    AttendAdapter mAdapter;
    private android.widget.ListView attendListView;

    boolean isLoginUserAttend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        viewScheduleActivity = this;
        schedule = (Schedule) getIntent().getSerializableExtra("schedule_item");
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

        attendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerUtil.insertNewAttend(mContext, ContextUtil.getUserData(mContext).getId(), schedule.getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            JSONObject attend = json.getJSONObject("attend");
                            mList.add(Attend.getAttendFromJson(attend));
                            mAdapter.notifyDataSetChanged();
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
        getAllAttends();

        if (schedule.getTag() == 1) {
            toolBar.setBackgroundColor(getResources().getColor(R.color.firstColor));
            middleTxt.setTextColor(getResources().getColor(R.color.firstColor));
            locationImageView.setImageResource(R.drawable.location_first);
            memoImageView.setImageResource(R.drawable.memo_first);
            attendBtn.setBackgroundResource(R.drawable.first_color_btn);
        } else if (schedule.getTag() == 2) {
            toolBar.setBackgroundColor(getResources().getColor(R.color.secondColor));
            middleTxt.setTextColor(getResources().getColor(R.color.secondColor));
            locationImageView.setImageResource(R.drawable.location_second);
            memoImageView.setImageResource(R.drawable.memo_second);
            attendBtn.setBackgroundResource(R.drawable.second_color_btn);
        } else if (schedule.getTag() == 3) {
            toolBar.setBackgroundColor(getResources().getColor(R.color.thirdColor));
            middleTxt.setTextColor(getResources().getColor(R.color.thirdColor));
            locationImageView.setImageResource(R.drawable.location_thrid);
            memoImageView.setImageResource(R.drawable.memo_thrid);
            attendBtn.setBackgroundResource(R.drawable.third_color_btn);
        } else {
            toolBar.setBackgroundColor(getResources().getColor(R.color.fourthColor));
            middleTxt.setTextColor(getResources().getColor(R.color.fourthColor));
            locationImageView.setImageResource(R.drawable.location_fourth);
            memoImageView.setImageResource(R.drawable.memo_fourth);
            attendBtn.setBackgroundResource(R.drawable.foruth_color_btn);
        }

        titleTxt.setText(schedule.getTitle());
        memoTxt.setText(schedule.getMemo());
        locationTxt.setText(schedule.getLocation());
        SimpleDateFormat myDateFormatDate = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat myDateFormatTime = new SimpleDateFormat("a hh시 mm분");
        startDateTxt.setText(myDateFormatDate.format(schedule.getStart_date().getTime()));
        startTimeTxt.setText(myDateFormatTime.format(schedule.getStart_date().getTime()));
        endDateTxt.setText(myDateFormatDate.format(schedule.getEnd_date().getTime()));
        endTimeTxt.setText(myDateFormatTime.format(schedule.getEnd_date().getTime()));
    }

    private void getAllAttends() {
        mList.clear();
        isLoginUserAttend = false;
        ServerUtil.getAttendList(mContext, schedule.getId(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray attend = json.getJSONArray("attend");
                    for (int i = 0; i < attend.length(); i++) {
                        Attend a = Attend.getAttendFromJson(attend.getJSONObject(i));
                        if (a.getAttendUser().getId() == ContextUtil.getUserData(mContext).getId()) {
                            isLoginUserAttend = true;
                        }
                        mList.add(a);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter = new AttendAdapter(mContext, mList);
                attendListView.setAdapter(mAdapter);
                if (isLoginUserAttend) {
                    attendBtn.setText("참 여 취 소");
                } else {
                    attendBtn.setText("참 여");
                }
            }
        });
    }

    @Override
    public void bindViews() {
        this.attendBtn = (TextView) findViewById(R.id.attendBtn);
        this.attendListView = (ListView) findViewById(R.id.attendListView);
        this.memoTxt = (TextView) findViewById(R.id.memoTxt);
        this.memoImageView = (ImageView) findViewById(R.id.memoImageView);
        this.locationTxt = (TextView) findViewById(R.id.locationTxt);
        this.locationImageView = (ImageView) findViewById(R.id.locationImageView);
        this.endScheduleLayout = (LinearLayout) findViewById(R.id.endScheduleLayout);
        this.endTimeTxt = (TextView) findViewById(R.id.endTimeTxt);
        this.endDateTxt = (TextView) findViewById(R.id.endDateTxt);
        this.middleTxt = (TextView) findViewById(R.id.middleTxt);
        this.startScheduleLayout = (LinearLayout) findViewById(R.id.startScheduleLayout);
        this.startTimeTxt = (TextView) findViewById(R.id.startTimeTxt);
        this.startDateTxt = (TextView) findViewById(R.id.startDateTxt);
        this.categoryTxt = (TextView) findViewById(R.id.categoryTxt);
        this.titleTxt = (TextView) findViewById(R.id.titleTxt);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
