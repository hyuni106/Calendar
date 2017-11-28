package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends BaseActivity {

    private android.widget.TextView idTxt;
    private android.widget.EditText idEdt;
    private android.widget.TextView nameTxt;
    private android.widget.EditText nameEdt;
    private android.widget.TextView pwTxt;
    private android.widget.EditText pwEdt;
    private android.widget.TextView pwCheckTxt;
    private android.widget.EditText pwCheckEdt;
    private android.widget.Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.pwCheckEdt = (EditText) findViewById(R.id.pwCheckEdt);
        this.pwCheckTxt = (TextView) findViewById(R.id.pwCheckTxt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.pwTxt = (TextView) findViewById(R.id.pwTxt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
    }
}
