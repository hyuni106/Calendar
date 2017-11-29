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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.util.GlobalData;

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
                intent.putExtra("schedule", new Schedule(GlobalData.allSchedule.size() + 1, inputEdt.getText().toString(), memoEdt.getText().toString(),
                        startDate, endDate, locationEdt.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        View.OnClickListener dateTimePicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = 0, minute = 0;
                if (view.getId() == R.id.startScheduleLayout) {
                    isStartPicker = true;
                    hour = 0;
                    minute = 0;
                } else if (view.getId() == R.id.endScheduleLayout) {
                    isStartPicker = false;
                    hour = 23;
                    minute = 59;
                }
                new TimePickerDialog(mContext, timeSetListener, hour, minute, false).show();
                new DatePickerDialog(mContext, dateSetListener, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        };

        startScheduleLayout.setOnClickListener(dateTimePicker);
        endScheduleLayout.setOnClickListener(dateTimePicker);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
            if (isStartPicker) {
                startDate.set(Calendar.YEAR, year);
                startDate.set(Calendar.MONTH, monthOfYear);
                startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                startDateTxt.setText(myDateFormat.format(startDate.getTime()));
                endDateTxt.setText(myDateFormat.format(startDate.getTime()));
            } else {
                endDate.set(Calendar.YEAR, year);
                endDate.set(Calendar.MONTH, monthOfYear);
                endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                endDateTxt.setText(myDateFormat.format(endDate.getTime()));
            }
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            SimpleDateFormat myDateFormat = new SimpleDateFormat("a hh시 mm분");
            if (isStartPicker) {
                startDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                startDate.set(MINUTE, minute);
                startTimeTxt.setText(myDateFormat.format(startDate.getTime()));
            } else {
                endDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                endDate.set(MINUTE, minute);
                endTimeTxt.setText(myDateFormat.format(endDate.getTime()));
            }
        }
    };

    @Override
    public void setValues() {
        SimpleDateFormat myDateFormatDate = new SimpleDateFormat("yyyy년 MM월 dd일");
        startDateTxt.setText(myDateFormatDate.format(startDate.getTime()));
        endDateTxt.setText(myDateFormatDate.format(startDate.getTime()));
        startTimeTxt.setText("오전 12시 00분");
        endTimeTxt.setText("오후 11시 59분");
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
