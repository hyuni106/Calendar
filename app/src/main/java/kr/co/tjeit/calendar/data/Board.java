package kr.co.tjeit.calendar.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by the on 2017-11-27.
 */

public class Board implements Serializable {
    private int id;
    private String content;
    private String createdAt;

    User writer;
    Group includeGroup;
    List<User> likeUser = new ArrayList<>();

    public Board() {
    }

    public Board(int id, String content, String createdAt) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }
}
