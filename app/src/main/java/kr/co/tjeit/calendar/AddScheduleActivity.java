package kr.co.tjeit.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.ServerUtil;

import static java.util.Calendar.MINUTE;

public class AddScheduleActivity extends BaseActivity {

    private android.widget.EditText inputEdt;
    private android.widget.Button okBtn;
    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView materialmenubutton;
    private android.widget.LinearLayout startScheduleLayout;
    private android.widget.LinearLayout endScheduleLayout;
    private EditText locationEdt;
    private EditText memoEdt;
    private android.widget.TextView startDateTxt;
    private android.widget.TextView startTimeTxt;
    private android.widget.TextView endDateTxt;
    private android.widget.TextView endTimeTxt;

    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();
    boolean isStartPicker = false;

    String startDateString = "";
    String endDateString = "";
    String startTimeString = "";
    String endTimeString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        startDate = (Calendar) getIntent().getSerializableExtra("calendar");
//        endDate = (Calendar) getIntent().getSerializableExtra("calendar");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        materialmenubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.createSchedule(mContext, inputEdt.getText().toString(), memoEdt.getText().toString(), startDateString + startTimeString, endDateString + endTimeString, locationEdt.getText().toString(),
                        ContextUtil.getRecentGroupId(mContext), ContextUtil.getUserData(mContext).getId(), 1, new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    Intent intent = new Intent();
                                    intent.putExtra("schedule", Schedule.getScheduleFromJson(json.getJSONObject("schedule")));
                                    setResult(RESULT_OK, intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
//                Log.d("날짜", startDateString + startTimeString);
//                Log.d("날짜", endDateString + endTimeString);
            }
        });

        View.OnClickListener dateTimePicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.startScheduleLayout) {
                    startDateString = "";
                    isStartPicker = true;
//                    Log.d("날짜", startDateString);
                    new TimePickerDialog(mContext, timeSetListener, startDate.get(Calendar.HOUR_OF_DAY), startDate.get(MINUTE), false).show();
                    new DatePickerDialog(mContext, dateSetListener, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH)).show();
                } else if (view.getId() == R.id.endScheduleLayout) {
                    endDateString = "";
                    isStartPicker = false;
                    new TimePickerDialog(mContext, timeSetListener, endDate.get(Calendar.HOUR_OF_DAY), endDate.get(MINUTE), false).show();
                    new DatePickerDialog(mContext, dateSetListener, endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH)).show();
                }
//                new TimePickerDialog(mContext, timeSetListener, hour, minute, false).show();
//                new DatePickerDialog(mContext, dateSetListener, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        };

        startScheduleLayout.setOnClickListener(dateTimePicker);
        endScheduleLayout.setOnClickListener(dateTimePicker);
    }

//    private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            // TODO Auto-generated method stub
//            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
//            startDate.set(Calendar.YEAR, year);
//            startDate.set(Calendar.MONTH, monthOfYear);
//            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            startDateTxt.setText(myDateFormat.format(startDate.getTime()));
//            endDate.set(Calendar.YEAR, year);
//            endDate.set(Calendar.MONTH, monthOfYear);
//            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            endDateTxt.setText(myDateFormat.format(endDate.getTime()));
//        }
//    };

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
            SimpleDateFormat myDateServerFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (isStartPicker) {
                startDate.set(Calendar.YEAR, year);
                startDate.set(Calendar.MONTH, monthOfYear);
                startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                startDateTxt.setText(myDateFormat.format(startDate.getTime()));
//                Log.d("날짜", startDateString);
                endDate.set(Calendar.YEAR, year);
                endDate.set(Calendar.MONTH, monthOfYear);
                endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                endDateTxt.setText(myDateFormat.format(startDate.getTime()));
                startDateString = myDateServerFormat.format(startDate.getTime());
//                Log.d("날짜", startDateString);
            }
            else {
                endDate.set(Calendar.YEAR, year);
                endDate.set(Calendar.MONTH, monthOfYear);
                endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                endDateTxt.setText(myDateFormat.format(endDate.getTime()));
//                Log.d("날짜", endDateString);
                endDateString = myDateServerFormat.format(endDate.getTime());
//                Log.d("날짜", endDateString);
            }
//            endDate.set(Calendar.YEAR, year);
//            endDate.set(Calendar.MONTH, monthOfYear);
//            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            endDateTxt.setText(myDateFormat.format(endDate.getTime()));
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            SimpleDateFormat myDateFormat = new SimpleDateFormat("a hh시 mm분");
            SimpleDateFormat myTimeServerFormat = new SimpleDateFormat("HH:mm:ss");
            if (isStartPicker) {
                startDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                startDate.set(MINUTE, minute);
                startTimeTxt.setText(myDateFormat.format(startDate.getTime()));
//                Log.d("날짜", startTimeString);
                endDate.set(Calendar.HOUR_OF_DAY, hourOfDay + 1);
                endDate.set(MINUTE, minute);
                endTimeTxt.setText(myDateFormat.format(endDate.getTime()));
                startTimeString = (" " + myTimeServerFormat.format(startDate.getTime()));
//                Log.d("날짜", startTimeString);
            }
            else {
                endDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                endDate.set(MINUTE, minute);
                endTimeTxt.setText(myDateFormat.format(endDate.getTime()));
//                Log.d("날짜", endTimeString);
                endTimeString =  (" " + myTimeServerFormat.format(endDate.getTime()));
//                Log.d("날짜", endTimeString);
            }
//            endDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            endDate.set(MINUTE, minute);
//            endTimeTxt.setText(myDateFormat.format(endDate.getTime()));
        }
    };

    @Override
    public void setValues() {
        SimpleDateFormat myDateFormatDate = new SimpleDateFormat("yyyy년 MM월 dd일");
        startDateTxt.setText(myDateFormatDate.format(startDate.getTime()));
        endDateTxt.setText(myDateFormatDate.format(startDate.getTime()));
        startTimeTxt.setText("오전 07시 00분");
        endTimeTxt.setText("오전 08시 00분");

        startDate.set(Calendar.HOUR_OF_DAY, 7);
        startDate.set(MINUTE, 0);
        endDate.set(Calendar.HOUR_OF_DAY, 8);
        endDate.set(MINUTE, 0);
        SimpleDateFormat myStartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startDateString = myStartDateFormat.format(startDate.getTime());
        SimpleDateFormat myEndDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        endDateString = myEndDateFormat.format(endDate.getTime());
    }

    public String parseDateFormat(Date date) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myDateFormat.format(date);
    }

    @Override
    public void bindViews() {
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.memoEdt = (EditText) findViewById(R.id.memoEdt);
        this.locationEdt = (EditText) findViewById(R.id.locationEdt);
        this.inputEdt = (EditText) findViewById(R.id.inputEdt);
        this.endScheduleLayout = (LinearLayout) findViewById(R.id.endScheduleLayout);
        this.endTimeTxt = (TextView) findViewById(R.id.endTimeTxt);
        this.endDateTxt = (TextView) findViewById(R.id.endDateTxt);
        this.startScheduleLayout = (LinearLayout) findViewById(R.id.startScheduleLayout);
        this.startTimeTxt = (TextView) findViewById(R.id.startTimeTxt);
        this.startDateTxt = (TextView) findViewById(R.id.startDateTxt);
        this.materialmenubutton = (MaterialMenuView) findViewById(R.id.material_menu_button);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
