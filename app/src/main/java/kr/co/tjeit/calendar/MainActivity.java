package kr.co.tjeit.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

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
    private int actionBarMenuState;

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
                        toolBar.setBackgroundColor(getResources().getColor(R.color.firstColor));
                        break;
                    case 1:
                        FeedLayout.setVisibility(View.VISIBLE);
                        toolBar.setBackgroundColor(getResources().getColor(R.color.secondColor));
                        break;
                    case 2:
                        BoardFragment.setVisibility(View.VISIBLE);
                        toolBar.setBackgroundColor(getResources().getColor(R.color.thirdColor));
                        break;
                    case 3:
                        SettingLayout.setVisibility(View.VISIBLE);
                        toolBar.setBackgroundColor(getResources().getColor(R.color.fourthColor));
                        break;
                }
            }
        });
    }

    @Override
    public void setValues() {
        setTitle("");
        setSupportActionBar(toolBar);
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        toolBar.setNavigationIcon(materialMenu);
        setBottomNavi();
    }

    private void setBottomNavi() {
        int[] image = {R.drawable.ic_mic_black_24dp, R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_book_black_24dp, R.drawable.github_circle};
        int[] color = {ContextCompat.getColor(this, R.color.firstColor), ContextCompat.getColor(this, R.color.secondColor),
                ContextCompat.getColor(this, R.color.thirdColor), ContextCompat.getColor(this, R.color.fourthColor)};

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Calendar", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Feed", color[1], image[1]);
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
    public void bindViews() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        this.bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        this.SettingLayout = (LinearLayout) findViewById(R.id.SettingLayout);
        this.BoardFragment = (LinearLayout) findViewById(R.id.BoardFragment);
        this.FeedLayout = (LinearLayout) findViewById(R.id.FeedLayout);
        this.CalendarLayout = (LinearLayout) findViewById(R.id.CalendarLayout);
        this.materialmenubutton = (MaterialMenuView) findViewById(R.id.material_menu_button);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
