package kr.co.tjeit.calendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.data.Board;

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

        Board data = mList.get(position);

        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);
        TextView writerTxt = (TextView) row.findViewById(R.id.writerTxt);
        TextView createAtTxt = (TextView) row.findViewById(R.id.createAtTxt);
        TextView commentBtn = (TextView) row.findViewById(R.id.commentBtn);
        TextView likeBtn = (TextView) row.findViewById(R.id.likeBtn);
        TextView shareBtn = (TextView) row.findViewById(R.id.shareBtn);

        contentTxt.setText(data.getContent());
        createAtTxt.setText(data.getCreatedAt());

        return row;
    }
}
