package kr.co.tjeit.calendar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.User;

/**
 * Created by the on 2017-11-27.
 */

public class BoardAdapter extends ArrayAdapter<Board> {
    Context mContext;
    List<Board> mList;
    LayoutInflater inf;

    public BoardAdapter(Context context, List<Board> list) {
        super(context, R.layout.board_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.board_list_item, null);
        }

        final Board data = mList.get(position);

        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);
        TextView writerTxt = (TextView) row.findViewById(R.id.writerTxt);
        TextView createAtTxt = (TextView) row.findViewById(R.id.createAtTxt);
        final TextView likeTxt = (TextView) row.findViewById(R.id.likeTxt);

        contentTxt.setText(data.getContent());

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        createAtTxt.setText(myDateFormat.format(data.getCreatedAt().getTime()));

        writerTxt.setText(data.getWriter().getNickName());

        if (data.getLikeUser().size() != 0) {
            String like = String.format(Locale.KOREA, "좋아요 %d개", data.getLikeUser().size());
            likeTxt.setText(like);
        } else {
            likeTxt.setText("좋아요 0개");
        }

        return row;
    }
}
