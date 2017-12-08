package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.data.User;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        GlobalData.initUserData();
        GlobalData.usersGroup.clear();
        getAllUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("확인", ContextUtil.isAutoLogin(SplashActivity.this));
                if (ContextUtil.isAutoLogin(SplashActivity.this).equals("1")) {
                    ServerUtil.participantGroup(SplashActivity.this, ContextUtil.getUserData(SplashActivity.this).getId(), new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {
                            try {
                                JSONArray group = json.getJSONArray("group");
                                for (int i = 0; i < group.length(); i++) {
                                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                    Group g = Group.getGroupFromJson(group.getJSONObject(i));
                                    GlobalData.usersGroup.add(g);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);
    }

    public void getAllUser() {
        GlobalData.allUser.clear();
        ServerUtil.getAllUsers(SplashActivity.this, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray users = json.getJSONArray("users");
                    for (int i=0; i<users.length(); i++) {
                        User u = User.getUserFromJson(users.getJSONObject(i));
                        GlobalData.allUser.add(u);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
