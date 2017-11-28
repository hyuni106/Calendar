package kr.co.tjeit.calendar.data;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-11-28.
 */

public class Comment implements Serializable {
    private int id;
    private String content;

    User writer;
    Board includeBoard;

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
