package kr.co.tjeit.calendar.data;

import java.io.Serializable;

/**
 * Created by the on 2017-11-27.
 */

public class Schedule implements Serializable {
    private int id;
    private String title;
    private String memo;
    private String start_date;
    private String end_date;
    private String start_time;
    private String end_time;
    private String location;

    User writer;
    Group includeGroup;

    public Schedule() {
    }

    public Schedule(int id, String title, String memo, String start_date, String end_date, String start_time, String end_time, String location) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.location = location;
    }

    public Schedule(String content) {
        this.memo = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Group getIncludeGroup() {
        return includeGroup;
    }

    public void setIncludeGroup(Group includeGroup) {
        this.includeGroup = includeGroup;
    }
}
