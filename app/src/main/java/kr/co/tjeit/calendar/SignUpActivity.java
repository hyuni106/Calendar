package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.calendar.util.ServerUtil;

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
    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private EditText nickNameEdt;
    private EditText birthEdt;
    private android.widget.TextView passwordCheckTxt;
    private TextView checkDuplIdBtn;

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
                ServerUtil.sign_up(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(), nickNameEdt.getText().toString(), birthEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                Toast.makeText(mContext, "가입성공", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pwCheckEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordCheckTxt.setVisibility(View.VISIBLE);
                if (pwEdt.getText().toString().equals(charSequence.toString())) {
                    passwordCheckTxt.setText("입력하신 비밀번호가 일치합니다.");
                    passwordCheckTxt.setTextColor(getResources().getColor(R.color.honey_flower));
                } else {
                    passwordCheckTxt.setText("비밀번호가 일치하지 않습니다.");
                    passwordCheckTxt.setTextColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        checkDuplIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerUtil.check_dupl_id(mContext, idEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            if (json.getString("result").equals("FALSE")) {
                                Toast.makeText(mContext, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                            }
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

    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.birthEdt = (EditText) findViewById(R.id.birthEdt);
        this.nickNameEdt = (EditText) findViewById(R.id.nickNameEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.passwordCheckTxt = (TextView) findViewById(R.id.passwordCheckTxt);
        this.pwCheckEdt = (EditText) findViewById(R.id.pwCheckEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.checkDuplIdBtn = (TextView) findViewById(R.id.checkDuplIdBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
