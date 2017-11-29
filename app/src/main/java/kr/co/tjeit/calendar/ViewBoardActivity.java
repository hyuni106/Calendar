package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.adapter.CommentAdapter;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Comment;

public class ViewBoardActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.TextView writerTxt;
    private android.widget.TextView createAtTxt;
    private TextView likeCountTxt;
    private TextView contentTxt;
    private android.widget.ListView commentListView;

    Board view;
    List<Comment> commentList = new ArrayList<>();
    CommentAdapter mAdapter;
    private android.widget.Button commentBtn;
    private android.widget.EditText commentEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_board);
        view = (Board) getIntent().getSerializableExtra("board_Item");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentList.add(new Comment(commentList.size() + 1, commentEdt.getText().toString()));
                mAdapter.notifyDataSetChanged();
                commentEdt.setText("");
            }
        });
    }

    @Override
    public void setValues() {
        contentTxt.setText(view.getContent());
        writerTxt.setText(view.getCreatedAt());
        mAdapter = new CommentAdapter(mContext, commentList);
        commentListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.commentBtn = (Button) findViewById(R.id.commentBtn);
        this.commentEdt = (EditText) findViewById(R.id.commentEdt);
        this.commentListView = (ListView) findViewById(R.id.commentListView);
        this.likeCountTxt = (TextView) findViewById(R.id.likeCountTxt);
        this.createAtTxt = (TextView) findViewById(R.id.createAtTxt);
        this.writerTxt = (TextView) findViewById(R.id.writerTxt);
        this.contentTxt = (TextView) findViewById(R.id.contentTxt);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
