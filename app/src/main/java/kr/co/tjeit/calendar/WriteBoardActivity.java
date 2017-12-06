package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

public class WriteBoardActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private com.balysv.materialmenu.MaterialMenuView checkBtn;
    private android.widget.EditText contentEdt;

    String today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerUtil.createBoard(mContext, contentEdt.getText().toString(), ContextUtil.getRecentGroupId(mContext), ContextUtil.getUserData(mContext).getId(),
                        new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                try {
                                    JSONObject board = json.getJSONObject("board");
                                    Board b = Board.getBoardFromJson(board);
                                    GlobalData.allBoard.add(b);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void setValues() {
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        today = myDateFormat.format(Calendar.getInstance().getTime());
    }

    @Override
    public void bindViews() {
        this.contentEdt = (EditText) findViewById(R.id.contentEdt);
        this.checkBtn = (MaterialMenuView) findViewById(R.id.checkBtn);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
