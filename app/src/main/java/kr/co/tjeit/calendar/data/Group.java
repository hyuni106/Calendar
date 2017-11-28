package kr.co.tjeit.calendar.data;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-11-28.
 */

public class Group implements Serializable {
    private int id;
    private String name;
    private String comment;

    public Group() {
    }

    public Group(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
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
}
