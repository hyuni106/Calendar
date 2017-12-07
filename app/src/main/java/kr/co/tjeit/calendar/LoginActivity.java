package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.data.User;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdt;
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
        View.OnClickListener intent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent);
            }
        };
        signUpBtn.setOnClickListener(intent);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoLoginCheck.isChecked()) {
                    ContextUtil.setAutoLogin(mContext);
                    Log.d("확인", ContextUtil.isAutoLogin(mContext));
                }

                ServerUtil.sign_in(mContext, idEdt.getText().toString(), pwEdt.getText().toString(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    if (json.getString("result").equals("OK")) {
                                        GlobalData.usersGroup.clear();
                                        JSONObject user = json.getJSONObject("user");
                                        ContextUtil.setLoginUser(mContext, User.getUserFromJson(user));
                                        JSONArray group = json.getJSONArray("group");
                                        Intent intent = null;
                                        if (group.length() > 0) {
                                         intent = new Intent(mContext, MainActivity.class);
                                            for (int i=0; i<group.length(); i++) {
                                                Group g = Group.getGroupFromJson(group.getJSONObject(i));
                                                GlobalData.usersGroup.add(g);
                                            }
                                        } else {
                                            intent = new Intent(mContext, CreateGroupActivity.class);
                                            intent.putExtra("activity", "login");
                                        }
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show();
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
        this.signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoLoginCheck = (CheckBox) findViewById(R.id.autoLoginCheck);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }
}
