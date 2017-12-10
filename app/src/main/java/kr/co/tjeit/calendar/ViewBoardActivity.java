package kr.co.tjeit.calendar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.calendar.adapter.CommentAdapter;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Comment;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.ServerUtil;

public class ViewBoardActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolBar;
    private com.balysv.materialmenu.MaterialMenuView backBtn;
    private android.widget.TextView writerTxt;
    private android.widget.TextView createAtTxt;
    private TextView likeCountTxt;
    private TextView contentTxt;
    private android.widget.ListView commentListView;

    Board board;
    List<Comment> commentList = new ArrayList<>();
    CommentAdapter mAdapter;
    private android.widget.EditText commentEdt;
    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private TextView likeTxt;
    private TextView commentBtn;
    private android.widget.ImageView likeBtn;

    boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_board);
        board = (Board) getIntent().getSerializableExtra("board_Item");
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
                ServerUtil.insertNewComment(mContext, commentEdt.getText().toString(), ContextUtil.getUserData(mContext).getId(), board.getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            JSONObject comment = json.getJSONObject("comment");
                            commentList.add(Comment.getCommentFromJson(comment));
                            mAdapter.notifyDataSetChanged();
                            commentEdt.setText("");
                            commentListView.smoothScrollToPosition(mAdapter.getCount() - 1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final int user_id = ContextUtil.getUserData(mContext).getId();
                final int board_id = board.getId();

                if (!isLiked) {
                    ServerUtil.insertLikeBoard(mContext, user_id, board_id, new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {
                            likeBtn.setImageResource(R.drawable.fill_heart);
                            String like = String.format(Locale.KOREA, "좋아요 %d개", board.getLikeUser().size() + 1);
                            likeTxt.setText(like);
                            board.getLikeUser().add(ContextUtil.getUserData(mContext));
                            isLiked = true;
                        }
                    });
                } else {
                    ServerUtil.deleteLikeBoard(mContext, user_id, board_id, new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {
                            likeBtn.setImageResource(R.drawable.empty_heart);
                            String like = String.format(Locale.KOREA, "좋아요 %d개", board.getLikeUser().size() - 1);
                            likeTxt.setText(like);
                            for (int i=0; i<board.getLikeUser().size(); i++) {
                                if (board.getLikeUser().get(i).getId() == user_id) {
                                    board.getLikeUser().remove(i);
                                }
                            }
                            isLiked = false;
                        }
                    });
                }
            }
        });
    }

    @Override
    public void setValues() {
        getAllComment();

        contentTxt.setText(board.getContent());

        writerTxt.setText(board.getWriter().getNickName());
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        createAtTxt.setText(myDateFormat.format(board.getCreatedAt().getTime()));

        if (board.getLikeUser().size() != 0) {
            String like = String.format(Locale.KOREA, "좋아요 %d개", board.getLikeUser().size());
            likeTxt.setText(like);
            for (int i=0; i<board.getLikeUser().size(); i++) {
                if (board.getLikeUser().get(i).getId() == ContextUtil.getUserData(mContext).getId()) {
                    isLiked = true;
                } else {
                    isLiked = false;
                }
            }
            if (isLiked) {
                likeBtn.setImageResource(R.drawable.fill_heart);
            }
        }

    }

    private void getAllComment() {
        commentList.clear();
        ServerUtil.getAllComment(mContext, board.getId(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    JSONArray comment = json.getJSONArray("comment");
                    for (int i=0; i<comment.length(); i++) {
                        Comment c = Comment.getCommentFromJson(comment.getJSONObject(i));
                        commentList.add(c);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter = new CommentAdapter(mContext, commentList);
                commentListView.setAdapter(mAdapter);
            }
        });
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
