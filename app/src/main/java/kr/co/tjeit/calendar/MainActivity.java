package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjeit.calendar.adapter.GridViewAdapter;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.data.Schedule;
import kr.co.tjeit.calendar.data.User;
import kr.co.tjeit.calendar.fragment.BoardFragment;
import kr.co.tjeit.calendar.fragment.CalendarFragment;
import kr.co.tjeit.calendar.fragment.ScheduleFragment;
import kr.co.tjeit.calendar.fragment.SettingFragment;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class MainActivity extends BaseActivity {

    public static MainActivity mainActivity;

    private com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigation;
    private LinearLayout CalendarLayout;
    private LinearLayout FeedLayout;
    private LinearLayout BoardLayout;
    private LinearLayout SettingLayout;
    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView materialmenubutton;
    private MaterialMenuDrawable materialMenu;
    private android.support.v4.widget.DrawerLayout drawerLayout;

    private boolean isDrawerOpened;
    private LinearLayout drawer;
    private android.widget.ImageView calImgView;
    private android.widget.TextView userNameTxt;
    private android.widget.TextView userInfoTxt;
    private android.widget.Button calAddBtn;
    private android.widget.GridView groupGridView;
    private FrameLayout alertLayout;
    private TextView alertCountTxt;

    GridViewAdapter mAdapter;
    private ImageView alertBtn;

    Group mainGroup;
    private TextView groupNameTxt;

    long backPressedTimeInMillis = 0;

    int alertCoount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        bottomNavigation.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                initLayout();
                switch (index) {
                    case 0:
                        CalendarLayout.setVisibility(View.VISIBLE);
//                        toolBar.setBackgroundColor(getResources().getColor(R.color.firstColor));
                        break;
                    case 1:
                        FeedLayout.setVisibility(View.VISIBLE);
//                        toolBar.setBackgroundColor(getResources().getColor(R.color.secondColor));
                        break;
                    case 2:
                        BoardLayout.setVisibility(View.VISIBLE);
//                        toolBar.setBackgroundColor(getResources().getColor(R.color.thirdColor));
                        break;
                    case 3:
                        SettingLayout.setVisibility(View.VISIBLE);
//                        toolBar.setBackgroundColor(getResources().getColor(R.color.fourthColor));
                        break;
                }
            }
        });

        materialmenubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(drawer)) {
                    drawerLayout.closeDrawer(drawer);
                } else {
                    drawerLayout.openDrawer(drawer);
                }
            }
        });

        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                materialmenubutton.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        isDrawerOpened ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpened = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_IDLE) {
                    if (isDrawerOpened) {
                        materialmenubutton.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                    } else {
                        materialmenubutton.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                    }
                }
            }
        });

        calAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CreateGroupActivity.class);
                intent.putExtra("activity", "main");
                startActivity(intent);
            }
        });

        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlertActivity.class);
                startActivity(intent);
            }
        });

        groupGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainGroup = GlobalData.usersGroup.get(i);
                ContextUtil.setRecentGroupId(mContext, GlobalData.usersGroup.get(i).getId());
                drawerLayout.closeDrawer(drawer);
                getAllScheduleFromGroup(mainGroup.getId());
                groupNameTxt.setText(mainGroup.getName());
            }
        });
    }

    @Override
    public void setValues() {
        setTitle("");

        Log.d("확인", ContextUtil.getRecentGroupId(mContext) + "번");
        if (ContextUtil.getRecentGroupId(mContext) != -1) {
            for (Group g : GlobalData.usersGroup) {
                if (g.getId() == ContextUtil.getRecentGroupId(mContext)) {
                    Log.d("확인", g.getId() + "번");
                    mainGroup = g;
                }
            }
        } else {
            mainGroup = GlobalData.usersGroup.get(0);
            ContextUtil.setRecentGroupId(mContext, GlobalData.usersGroup.get(0).getId());
            Log.d("확인", GlobalData.usersGroup.get(0).getId() + "번");
        }

        getAllScheduleFromGroup(mainGroup.getId());
        groupNameTxt.setText(mainGroup.getName());
//        getAllScheduleFromGroup(mainGroup.getId());
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(materialMenu);
        setBottomNavi();

        User loginUser = ContextUtil.getUserData(mContext);
        userNameTxt.setText(loginUser.getName());
        userInfoTxt.setText(loginUser.getNickName());

        mAdapter = new GridViewAdapter(mContext, GlobalData.usersGroup);
        groupGridView.setAdapter(mAdapter);

        bottomNavigation.selectTab(0);
        bottomNavigation.isColoredBackground(false);
        bottomNavigation.setItemActiveColorWithoutColoredBackground(getResources().getColor(R.color.honey_flower));
    }

    public void getAllScheduleFromGroup(int id) {
        GlobalData.allSchedule.clear();
        GlobalData.allBoard.clear();
        ServerUtil.getAllSchedule(mContext, id, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray schedule = json.getJSONArray("schedule");
                    JSONArray board = json.getJSONArray("board");
                    for (int i=0; i<schedule.length(); i++) {
                        Schedule s = Schedule.getScheduleFromJson(schedule.getJSONObject(i));
                        GlobalData.allSchedule.add(s);
                    }
                    for (int i=0; i<board.length(); i++) {
                        Board b = Board.getBoardFromJson(board.getJSONObject(i));
                        GlobalData.allBoard.add(b);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CalendarFragment.calendarFragment.onResume();
                BoardFragment.boardFragment.onResume();
                ScheduleFragment.scheduleFragment.onResume();
                SettingFragment.settingFragment.onResume();
            }
        });
    }

    private void setBottomNavi() {
        int[] image = {R.drawable.ic_mic_black_24dp, R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_book_black_24dp, R.drawable.github_circle};
        int[] color = {ContextCompat.getColor(this, R.color.honey_flower), ContextCompat.getColor(this, R.color.honey_flower),
                ContextCompat.getColor(this, R.color.honey_flower), ContextCompat.getColor(this, R.color.honey_flower)};

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Calendar", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Schedule", color[1], image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Board", color[2], image[2]);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("Setting", color[3], image[3]);


        bottomNavigation.addTab(bottomNavigationItem);
        bottomNavigation.addTab(bottomNavigationItem1);
        bottomNavigation.addTab(bottomNavigationItem2);
        bottomNavigation.addTab(bottomNavigationItem3);
    }

    public void initLayout() {
        CalendarLayout.setVisibility(View.GONE);
        FeedLayout.setVisibility(View.GONE);
        BoardLayout.setVisibility(View.GONE);
        SettingLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserAlertCount();
        mAdapter.notifyDataSetChanged();
        groupNameTxt.setText(mainGroup.getName());
        Log.d("확인메인", ContextUtil.getUserData(mContext).getNickName());
        userInfoTxt.setText(ContextUtil.getUserData(mContext).getNickName());
    }

    private void getUserAlertCount() {
        ServerUtil.getAlertCount(mContext, ContextUtil.getUserData(mContext).getId(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONObject count = json.getJSONObject("count");
                    alertCoount = count.getInt("count");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (alertCoount > 0) {
                    alertLayout.setVisibility(View.VISIBLE);
                    alertCountTxt.setText(alertCoount + "");
                } else {
                    alertLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void bindViews() {
        this.alertCountTxt = (TextView) findViewById(R.id.alertCountTxt);
        this.alertLayout = (FrameLayout) findViewById(R.id.alertLayout);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        this.drawer = (LinearLayout) findViewById(R.id.drawer);
        this.groupGridView = (GridView) findViewById(R.id.groupGridView);
        this.calAddBtn = (Button) findViewById(R.id.calAddBtn);
        this.alertBtn = (ImageView) findViewById(R.id.alertBtn);
        this.userInfoTxt = (TextView) findViewById(R.id.userInfoTxt);
        this.userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        this.calImgView = (ImageView) findViewById(R.id.calImgView);
        this.bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        this.SettingLayout = (LinearLayout) findViewById(R.id.SettingLayout);
        this.BoardLayout = (LinearLayout) findViewById(R.id.BoardFragment);
        this.FeedLayout = (LinearLayout) findViewById(R.id.FeedLayout);
        this.CalendarLayout = (LinearLayout) findViewById(R.id.CalendarLayout);
        this.groupNameTxt = (TextView) findViewById(R.id.groupNameTxt);
        this.materialmenubutton = (MaterialMenuView) findViewById(R.id.material_menu_button);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }

    public Group returnMainGroup() {
        return mainGroup;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawer)) {
            drawerLayout.closeDrawer(drawer);
        }  else {
            checkBackPressedTime();
        }
    }

    private void checkBackPressedTime() {

        long currentTimeInMillis = System.currentTimeMillis();

        if (currentTimeInMillis - backPressedTimeInMillis < 2000) {
//            2초 이내에 백버튼을 다시 눌렀으니 종료해야 함.
            if (!ContextUtil.isAutoLogin(mContext).equals("1")) {
                ContextUtil.logout(mContext);
            }
            finish();
            return;
        } else {
//            최초로 백버튼을 눌렀거나, 혹은 2초 이상의 시간이 지난 후에 누름.
            Toast.makeText(mContext, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

        backPressedTimeInMillis = currentTimeInMillis;
    }
}
