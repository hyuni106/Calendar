package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    private android.widget.TextView idTxt;
    private android.widget.EditText idEdt;
    private android.widget.TextView pwTxt;
    private android.widget.EditText pwEdt;
    private android.widget.CheckBox autoLoginCheck;
    private android.widget.Button loginBtn;
    private android.widget.TextView signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoLoginCheck = (CheckBox) findViewById(R.id.autoLoginCheck);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.pwTxt = (TextView) findViewById(R.id.pwTxt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
    }
}