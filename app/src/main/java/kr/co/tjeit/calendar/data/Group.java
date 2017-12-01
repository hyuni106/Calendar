package kr.co.tjeit.calendar.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-11-28.
 */

public class Group implements Serializable {
    private int id;
    private String name;
    private String comment;
    private String image_path;

    public static Group getGroupFromJson(JSONObject json) {
        Group g = new Group();

        try {
            g.setId(json.getInt("id"));
            g.setName(json.getString("name"));
            g.setComment(json.getString("comment"));
            g.setImage_path(json.getString("image_path"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return g;
    }

    public Group() {
    }

    public Group(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }

    public Group(int id, String name, String comment, String image_path) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
