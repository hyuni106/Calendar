package kr.co.tjeit.calendar.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by the on 2017-11-27.
 */

public class Board implements Serializable {
    private int id;
    private String content;
    private Calendar createdAt;

    User writer = new User();
    List<User> likeUser = new ArrayList<>();

    public static Board getBoardFromJson(JSONObject json) {
        Board b = new Board();

        try {
            b.setId(json.getInt("id"));
            b.setContent(json.getString("content"));

            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar createdAt = Calendar.getInstance();
            createdAt.setTime(myDateFormat.parse(json.getString("createdAt")));
            b.setCreatedAt(createdAt);

            b.writer = User.getUserFromJson(json.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }

    public Board() {
    }

    public Board(int id, String content, Calendar createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }
}
