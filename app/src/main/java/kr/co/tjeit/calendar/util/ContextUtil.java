package kr.co.tjeit.calendar.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.calendar.data.User;

/**
 * Created by suhyu on 2017-12-01.
 */

public class ContextUtil {
    private static User loginUser = null;

    private final static String prefName = "CalendarPref";
    //    자동로그인 여부를 저장할때 사용할 태그
    private final static String USER_LOGIN = "USER_LOGIN";
    private final static String USER_ID = "USER_ID";
    private final static String USER_LOGIN_ID = "USER_LOGIN_ID";
    private final static String USER_NAME = "USER_NAME";
    private final static String USER_NICKNAME = "USER_NICKNAME";
    private final static String USER_BIRTH = "USER_BIRTH";
    private final static String RECENT_GROUP = "RECENT_GROUP";

    public static void setLoginUser(Context context, User loginUser) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putInt(USER_ID, loginUser.getId()).commit();
        pref.edit().putString(USER_LOGIN_ID, loginUser.getLogin_id()).commit();
        pref.edit().putString(USER_NAME, loginUser.getName()).commit();
        pref.edit().putString(USER_NICKNAME, loginUser.getNickName()).commit();
        pref.edit().putString(USER_BIRTH, loginUser.getBirth()).commit();
    }

    public static User getUserData(Context context) {

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (pref.getInt(USER_ID, -1) != -1) {

            if (loginUser == null) {
                loginUser = new User();
            }

            loginUser.setId(pref.getInt(USER_ID, -1));
            loginUser.setLogin_id(pref.getString(USER_LOGIN_ID, ""));
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setNickName(pref.getString(USER_NICKNAME, ""));
            loginUser.setBirth(pref.getString(USER_BIRTH, ""));

        }
        else {
            loginUser = null;
        }

        return loginUser;
    }

    public static void setLoginUserNick(Context context, String nickname) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_NICKNAME, nickname).commit();
    }

    public static String isAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_LOGIN, "");
    }

    public static void setAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_LOGIN, "1").commit();
    }

    public static void setRecentGroupId(Context context, int group_id) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(RECENT_GROUP, group_id).commit();
    }

    public static int getRecentGroupId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getInt(RECENT_GROUP, -1);
    }
    public static void logout(Context context) {
        loginUser = null;
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(USER_ID, -1).commit();
        pref.edit().putString(USER_LOGIN, "").commit();
        pref.edit().putString(USER_LOGIN_ID, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_NICKNAME, "").commit();
        pref.edit().putString(USER_BIRTH, "").commit();
        pref.edit().putInt(RECENT_GROUP, -1).commit();
    }
}