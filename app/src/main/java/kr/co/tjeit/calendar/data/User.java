package kr.co.tjeit.calendar.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-11-28.
 */

public class User implements Serializable {
    private int id;
    private String login_id;
    private String birth;
    private String name;
    private String nickName;

    public static User getUserFromJson(JSONObject json) {
        User u = new User();

        try {
            u.setId(json.getInt("id"));
            u.setLogin_id(json.getString("login_id"));
            u.setName(json.getString("name"));
            u.setNickName(json.getString("nickname"));
            u.setBirth(json.getString("birth"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }

    public User() {
    }

    public User(int id, String login_id, String birth, String name, String nickName) {
        this.id = id;
        this.login_id = login_id;
        this.birth = birth;
        this.name = name;
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
