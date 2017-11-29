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
        allUser.clear();
        allUser.add(new User(0, "user1", "1992-10-01", "사용자1", "닉넴1"));
        allUser.add(new User(0, "user2", "1992-10-01", "사용자2", "닉넴2"));
        allUser.add(new User(0, "user3", "1992-10-01", "사용자3", "닉넴3"));
        allUser.add(new User(0, "user4", "1992-10-01", "사용자4", "닉넴4"));
        allUser.add(new User(0, "user5", "1992-10-01", "사용자5", "닉넴5"));

        allParticipantAlert.clear();
        allParticipantAlert.add(new Participant(0, 0));
        allParticipantAlert.add(new Participant(1, 0));
        allParticipantAlert.add(new Participant(2, 0));

        allParticipantAlert.get(0).setMember(allUser.get(0));
        allParticipantAlert.get(1).setMember(allUser.get(1));
        allParticipantAlert.get(2).setMember(allUser.get(2));
    }
}
