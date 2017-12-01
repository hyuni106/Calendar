package kr.co.tjeit.calendar.data;

import java.io.Serializable;

/**
 * Created by suhyu on 2017-12-01.
 */

public class Like implements Serializable {
    int id;

    User likeUser = null;
    Board likeBoard = null;

    public Like() {
    }

    public Like(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(User likeUser) {
        this.likeUser = likeUser;
    }

    public Board getLikeBoard() {
        return likeBoard;
    }

    public void setLikeBoard(Board likeBoard) {
        this.likeBoard = likeBoard;
    }
}
