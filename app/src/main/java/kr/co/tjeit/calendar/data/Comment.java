package kr.co.tjeit.calendar.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-11-28.
 */

public class Comment implements Serializable {
    private int id;
    private String content;

    User writer;
    Board includeBoard;

    public static Comment getCommentFromJson (JSONObject json) {
        Comment c = new Comment();

        try {
            c.setId(json.getInt("id"));
            c.setContent(json.getString("content"));

            User u = User.getUserFromJson(json.getJSONObject("write_user"));
            c.writer = u;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return c;
    }

    public Comment() {
    }

    public Comment(int id, String content) {
        this.id = id;
        this.content = content;
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Board getIncludeBoard() {
        return includeBoard;
    }

    public void setIncludeBoard(Board includeBoard) {
        this.includeBoard = includeBoard;
    }
}
