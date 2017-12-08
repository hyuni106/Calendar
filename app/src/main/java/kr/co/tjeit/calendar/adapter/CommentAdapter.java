package kr.co.tjeit.calendar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.data.Comment;
import kr.co.tjeit.calendar.data.Participant;

/**
 * Created by suhyu on 2017-11-29.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {
    Context mContext;
    List<Comment> mList;
    LayoutInflater inf;

    public CommentAdapter(Context context, List<Comment> list) {
        super(context, R.layout.comment_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.comment_list_item, null);
        }

        Comment data = mList.get(position);

        TextView writerTxt = (TextView) row.findViewById(R.id.writerTxt);
        TextView commentTxt = (TextView) row.findViewById(R.id.commentTxt);

        commentTxt.setText(data.getContent());
        writerTxt.setText(data.getWriter().getNickName());

        return row;
    }
}
