package kr.co.tjeit.calendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONObject;

import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.ServerUtil;

public class AppSettingActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.LinearLayout changeNickLayout;
    private LinearLayout logoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);
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

        changeNickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText txtUrl = new EditText(mContext);
                txtUrl.setText(ContextUtil.getUserData(mContext).getNickName());

                new AlertDialog.Builder(mContext)
                        .setTitle("닉네임 변경")
                        .setMessage("변경할 닉네임을 작성해주세요")
                        .setView(txtUrl)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ServerUtil.updateNickname(mContext, txtUrl.getText().toString(), ContextUtil.getUserData(mContext).getId(), new ServerUtil.JsonResponseHandler() {
                                    @Override
                                    public void onResponse(JSONObject json) {
                                        ContextUtil.setLoginUserNick(mContext, txtUrl.getText().toString());
                                        Log.d("확인닉변경", txtUrl.getText().toString());
                                        Log.d("확인닉변경", ContextUtil.getUserData(mContext).getNickName());
                                        MainActivity.mainActivity.onResume();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("로그아웃")
                        .setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity.mainActivity.finish();
                                ContextUtil.logout(mContext);
                                Intent intent = new Intent(mContext, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.logoutLayout = (LinearLayout) findViewById(R.id.logoutLayout);
        this.changeNickLayout = (LinearLayout) findViewById(R.id.changeNickLayout);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
