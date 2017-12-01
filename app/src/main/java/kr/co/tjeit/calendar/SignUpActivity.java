package kr.co.tjeit.calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kr.co.tjeit.calendar.util.ServerUtil;

public class SignUpActivity extends BaseActivity {
    private android.widget.EditText idEdt;
    private android.widget.EditText nameEdt;
    private android.widget.EditText pwEdt;
    private android.widget.EditText pwCheckEdt;
    private android.widget.Button signUpBtn;
    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private EditText nickNameEdt;
    private android.widget.TextView passwordCheckTxt;
    private TextView checkDuplIdBtn;
    private TextView birthTxt;
    private TextView pickBirthBtn;

    Boolean isCheckDuplID = false;
    Boolean isEqualPwCheck = false;
    Calendar birth = Calendar.getInstance();

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
                if (idEdt.getText().toString().equals("")) {
                    Toast.makeText(mContext, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isCheckDuplID) {
                    Toast.makeText(mContext, "아이디 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwEdt.getText().toString().equals("")) {
                    Toast.makeText(mContext, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEqualPwCheck) {
                    Toast.makeText(mContext, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nameEdt.getText().toString().equals("")) {
                    Toast.makeText(mContext, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nickNameEdt.getText().toString().equals("")) {
                    Toast.makeText(mContext, "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (birthTxt.getText().toString().equals("생일")) {
                    Toast.makeText(mContext, "생일을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                ServerUtil.sign_up(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(), nickNameEdt.getText().toString(), myDateFormat.format(birth.getTime()),
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
                    isEqualPwCheck = true;
                } else {
                    passwordCheckTxt.setText("비밀번호가 일치하지 않습니다.");
                    passwordCheckTxt.setTextColor(getResources().getColor(R.color.red));
                    isEqualPwCheck = false;
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
                                isCheckDuplID = false;
                            } else {
                                Toast.makeText(mContext, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                                isCheckDuplID = true;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        pickBirthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mContext, dateSetListener, 1990, 0, 1).show();
            }
        });
    }

    @Override
    public void setValues() {

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
            birth.set(Calendar.YEAR, year);
            birth.set(Calendar.MONTH, monthOfYear);
            birth.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            birthTxt.setText(myDateFormat.format(birth.getTime()));
        }
    };

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.pickBirthBtn = (TextView) findViewById(R.id.pickBirthBtn);
        this.birthTxt = (TextView) findViewById(R.id.birthTxt);
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
