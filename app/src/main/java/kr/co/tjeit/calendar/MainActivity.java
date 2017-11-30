package kr.co.tjeit.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import kr.co.tjeit.calendar.adapter.GridViewAdapter;
import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.util.GlobalData;

public class MainActivity extends BaseActivity {

    private com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigation;
    private LinearLayout CalendarLayout;
    private LinearLayout FeedLayout;
    private LinearLayout BoardFragment;
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

    GridViewAdapter mAdapter;
    private ImageView alertBtn;

    Group mainGroup;
    private TextView groupNameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        BoardFragment.setVisibility(View.VISIBLE);
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
                drawerLayout.closeDrawer(drawer);
                groupNameTxt.setText(mainGroup.getName());
            }
        });
    }

    @Override
    public void setValues() {
        setTitle("");
        groupNameTxt.setText(GlobalData.usersGroup.get(0).getName());
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(materialMenu);
        setBottomNavi();

        mAdapter = new GridViewAdapter(mContext, GlobalData.usersGroup);
        groupGridView.setAdapter(mAdapter);

        bottomNavigation.isColoredBackground(false);
        bottomNavigation.setItemActiveColorWithoutColoredBackground(getResources().getColor(R.color.honey_flower));
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
        BoardFragment.setVisibility(View.GONE);
        SettingLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void bindViews() {
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
        this.BoardFragment = (LinearLayout) findViewById(R.id.BoardFragment);
        this.FeedLayout = (LinearLayout) findViewById(R.id.FeedLayout);
        this.CalendarLayout = (LinearLayout) findViewById(R.id.CalendarLayout);
        this.groupNameTxt = (TextView) findViewById(R.id.groupNameTxt);
        this.materialmenubutton = (MaterialMenuView) findViewById(R.id.material_menu_button);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
