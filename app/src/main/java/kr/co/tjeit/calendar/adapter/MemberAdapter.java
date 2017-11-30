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
import java.util.Locale;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.data.User;

/**
 * Created by suhyu on 2017-11-29.
 */

public class MemberAdapter extends ArrayAdapter<Participant> {
    Context mContext;
    List<Participant> mList;
    LayoutInflater inf;

    public MemberAdapter(Context context, List<Participant> list) {
        super(context, R.layout.member_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.member_list_item, null);
        }

        Participant data = mList.get(position);

        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);
        TextView userNickTxt = (TextView) row.findViewById(R.id.userNickTxt);
        TextView waitingAcceptTxt = (TextView) row.findViewById(R.id.waitingAcceptTxt);

        nameTxt.setText(data.getMember().getName());
        String nickname = String.format(Locale.KOREA, "( %s )", data.getMember().getNickName());
        userNickTxt.setText(nickname);

        if (data.getStatus() == 0) {
            waitingAcceptTxt.setVisibility(View.VISIBLE);
        } else {
            waitingAcceptTxt.setVisibility(View.GONE);
        }

        return row;
    }
}
