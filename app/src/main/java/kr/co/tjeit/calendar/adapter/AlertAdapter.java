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
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Participant;

/**
 * Created by suhyu on 2017-11-29.
 */

public class AlertAdapter extends ArrayAdapter<Participant> {
    Context mContext;
    List<Participant> mList;
    LayoutInflater inf;

    public AlertAdapter(Context context, List<Participant> list) {
        super(context, R.layout.alert_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.alert_list_item, null);
        }

        Participant data = mList.get(position);

        TextView userNickTxt = (TextView) row.findViewById(R.id.userNickTxt);
        TextView groupNameTxt = (TextView) row.findViewById(R.id.groupNameTxt);
        TextView acceptBtn = (TextView) row.findViewById(R.id.acceptBtn);
        TextView rejectBtn = (TextView) row.findViewById(R.id.rejectBtn);

        userNickTxt.setText(data.getMember().getNickName());
        groupNameTxt.setText(data.getParticipant_Group().getName());

        return row;
    }
}
