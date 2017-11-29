package kr.co.tjeit.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.util.GlobalData;

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

    int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
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
                Intent intent = new Intent();
                intent.putExtra("schedule", new Schedule(GlobalData.allSchedule.size() + 1, inputEdt.getText().toString(), memoEdt.getText().toString(), "", "", "", "", locationEdt.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        startScheduleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(mContext, timeSetListener, hour, minute, false).show();
                new DatePickerDialog(mContext, dateSetListener, year, month, day).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            String msg = String.format(Locale.KOREA, "%d년 %d월 %d일", year,monthOfYear+1, dayOfMonth);
            startDateTxt.setText(msg);
//            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            String msg = String.format(Locale.KOREA, "%d시 %d분", hourOfDay, minute);
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void setValues() {
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day= calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
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
