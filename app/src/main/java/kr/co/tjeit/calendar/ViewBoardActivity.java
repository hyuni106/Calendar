package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
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
    private android.widget.EditText commentEdt;
    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private TextView likeTxt;
    private TextView commentBtn;
    private android.widget.ImageView likeBtn;

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

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setValues() {
        contentTxt.setText(view.getContent());

        writerTxt.setText(view.getWriter().getNickName());
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        createAtTxt.setText(myDateFormat.format(view.getCreatedAt().getTime()));

        mAdapter = new CommentAdapter(mContext, commentList);
        commentListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.commentBtn = (TextView) findViewById(R.id.commentBtn);
        this.commentEdt = (EditText) findViewById(R.id.commentEdt);
        this.likeBtn = (ImageView) findViewById(R.id.likeBtn);
        this.commentListView = (ListView) findViewById(R.id.commentListView);
        this.likeTxt = (TextView) findViewById(R.id.likeTxt);
        this.contentTxt = (TextView) findViewById(R.id.contentTxt);
        this.createAtTxt = (TextView) findViewById(R.id.createAtTxt);
        this.writerTxt = (TextView) findViewById(R.id.writerTxt);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);
        this.backBtn = (MaterialMenuView) findViewById(R.id.backBtn);
        this.toolBar = (Toolbar) findViewById(R.id.toolBar);
    }
}
