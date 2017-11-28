package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.co.tjeit.calendar.data.Schedule;

public class AddScheduleActivity extends BaseActivity {

    private android.widget.EditText inputEdt;
    private android.widget.Button okBtn;

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
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("schedule", new Schedule(inputEdt.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.inputEdt = (EditText) findViewById(R.id.inputEdt);
    }
}
