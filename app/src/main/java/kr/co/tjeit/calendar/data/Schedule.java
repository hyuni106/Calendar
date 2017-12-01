package kr.co.tjeit.calendar.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by the on 2017-11-27.
 */

public class Schedule implements Serializable {
    private int id;
    private String title;
    private String memo;
    private Calendar start_date;
    private Calendar end_date;
    private String location;

    User writer;
    Group includeGroup;
    List<User> attendUser = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(int id, String title, String memo, Calendar start_date, Calendar end_date, String location) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public Calendar getStart_date() {
        return start_date;
    }

    public void setStart_date(Calendar start_date) {
        this.start_date = start_date;
    }

    public Calendar getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Calendar end_date) {
        this.end_date = end_date;
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

    public List<User> getAttendUser() {
        return attendUser;
    }

    public void setAttendUser(List<User> attendUser) {
        this.attendUser = attendUser;
    }
}
