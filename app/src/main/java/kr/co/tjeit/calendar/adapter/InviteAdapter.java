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
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.data.User;

/**
 * Created by suhyu on 2017-11-30.
 */

public class InviteAdapter extends ArrayAdapter<User> {
    Context mContext;
    List<User> mList;
    LayoutInflater inf;

    public InviteAdapter(Context context, List<User> list) {
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

        User data = mList.get(position);

        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);
        TextView userNickTxt = (TextView) row.findViewById(R.id.userNickTxt);
        TextView waitingAcceptTxt = (TextView) row.findViewById(R.id.waitingAcceptTxt);

        waitingAcceptTxt.setVisibility(View.GONE);

        nameTxt.setText(data.getName());
        String nickname = String.format(Locale.KOREA, "( %s )", data.getNickName());
        userNickTxt.setText(nickname);

        return row;
    }
}