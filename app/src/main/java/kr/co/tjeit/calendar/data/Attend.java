package kr.co.tjeit.calendar.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-12-09.
 */

public class Attend implements Serializable {
    int id;

    User attendUser;

    public static Attend getAttendFromJson(JSONObject json) {
        Attend a = new Attend();

        try {
            a.setId(json.getInt("id"));
            a.attendUser = User.getUserFromJson(json.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return a;
    }

    public Attend() {
    }

    public Attend(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAttendUser() {
        return attendUser;
    }

    public void setAttendUser(User attendUser) {
        this.attendUser = attendUser;
    }
}
