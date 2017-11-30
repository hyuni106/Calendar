package kr.co.tjeit.calendar.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.data.User;

/**
 * Created by the on 2017-11-28.
 */

public class GlobalData {
    public static List<Schedule> allSchedule = new ArrayList<>();
    public static List<Group> usersGroup = new ArrayList<>();
    public static List<Board> allBoard = new ArrayList<>();
    public static List<User> allUser = new ArrayList<>();
    public static List<Participant> allParticipantAlert = new ArrayList<>();

    public static void initUserData() {
        allSchedule.clear();
        allUser.clear();
        allUser.add(new User(0, "user1", "1992-10-01", "사용자1", "닉넴1"));
        allUser.add(new User(1, "user2", "1992-10-01", "사용자2", "닉넴2"));
        allUser.add(new User(2, "user3", "1992-10-01", "사용자3", "닉넴3"));
        allUser.add(new User(3, "user4", "1992-10-01", "사용자4", "닉넴4"));
        allUser.add(new User(4, "user5", "1992-10-01", "사용자5", "닉넴5"));

        usersGroup.clear();
        usersGroup.add(new Group(0, "그룹1", "그룹1입니다"));
        usersGroup.add(new Group(1, "그룹2", "그룹2입니다"));
        usersGroup.add(new Group(2, "그룹3", "그룹3입니다"));

        allParticipantAlert.clear();
        allParticipantAlert.add(new Participant(0, 0));
        allParticipantAlert.add(new Participant(1, 0));
        allParticipantAlert.add(new Participant(2, 0));
        allParticipantAlert.add(new Participant(3, 1));
        allParticipantAlert.add(new Participant(4, 1));

        allParticipantAlert.get(0).setParticipant_User(allUser.get(0));
        allParticipantAlert.get(1).setParticipant_User(allUser.get(1));
        allParticipantAlert.get(2).setParticipant_User(allUser.get(2));
        allParticipantAlert.get(3).setMember(allUser.get(3));
        allParticipantAlert.get(4).setMember(allUser.get(4));

        allParticipantAlert.get(0).setParticipant_Group(usersGroup.get(0));
        allParticipantAlert.get(1).setParticipant_Group(usersGroup.get(1));
        allParticipantAlert.get(2).setParticipant_Group(usersGroup.get(2));
        allParticipantAlert.get(3).setParticipant_Group(usersGroup.get(0));
        allParticipantAlert.get(4).setParticipant_Group(usersGroup.get(0));
    }
}
