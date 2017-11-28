package kr.co.tjeit.calendar.data;

import java.io.Serializable;

/**
 * Created by the on 2017-11-27.
 */

public class Schedule implements Serializable {
    int id;
    String content;

    public Schedule() {
    }

    public Schedule(String content) {
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
}
