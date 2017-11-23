package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

public class MainActivity extends BaseActivity {

    private com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigation;
    private android.widget.LinearLayout newsfeedLayout;
    private android.widget.LinearLayout searchMainLayout;
    private android.widget.LinearLayout searchLayout;
    private android.widget.LinearLayout writeLayout;

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

    }

    @Override
    public void setValues() {
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

        bottomNavigation.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                initLayout();
                switch (index) {
                    case 0:
                        newsfeedLayout.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        searchMainLayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        searchLayout.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        writeLayout.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    public void initLayout() {
        newsfeedLayout.setVisibility(View.GONE);
        searchMainLayout.setVisibility(View.GONE);
        searchLayout.setVisibility(View.GONE);
        writeLayout.setVisibility(View.GONE);
    }

    @Override
    public void bindViews() {
        this.bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        this.writeLayout = (LinearLayout) findViewById(R.id.writeLayout);
        this.searchLayout = (LinearLayout) findViewById(R.id.searchLayout);
        this.searchMainLayout = (LinearLayout) findViewById(R.id.searchMainLayout);
        this.newsfeedLayout = (LinearLayout) findViewById(R.id.newsfeedLayout);
    }
}
