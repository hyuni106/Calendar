package kr.co.tjeit.calendar.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private int tag;

    User writer;
    Group includeGroup;
    List<User> attendUser = new ArrayList<>();

    public static Schedule getScheduleFromJson(JSONObject json) {
        Schedule s = new Schedule();

        try {
            s.setId(json.getInt("id"));
            s.setTitle(json.getString("title"));
            s.setMemo(json.getString("memo"));

            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar start_date = Calendar.getInstance();
            start_date.setTime(myDateFormat.parse(json.getString("start_date")));
            s.setStart_date(start_date);
            Calendar end_date = Calendar.getInstance();
            end_date.setTime(myDateFormat.parse(json.getString("end_date")));
            s.setEnd_date(end_date);

            s.setLocation(json.getString("location"));
            s.setTag(json.getInt("tag"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return s;
    }

    public Schedule() {
    }

    public Schedule(int id, String title, String memo, Calendar start_date, Calendar end_date, String location, int tag) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.start_date = start_date;
        this.end_date = end_date;
        this.location = location;
        this.tag = tag;
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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
