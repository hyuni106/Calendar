package kr.co.tjeit.calendar.data;

import java.io.Serializable;

/**
 * Created by the on 2017-11-28.
 */

public class Participant implements Serializable {
    private int id;
    private int status; // 0 : 미수락 1 : 수락 2 : 거절

    User member;
    Group participant_Group;

    public Participant() {
    }

    public Participant(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Group getParticipant_Group() {
        return participant_Group;
    }

    public void setParticipant_Group(Group participant_Group) {
        this.participant_Group = participant_Group;
    }
}
