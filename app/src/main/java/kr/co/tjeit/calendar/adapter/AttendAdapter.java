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
import kr.co.tjeit.calendar.ViewScheduleActivity;
import kr.co.tjeit.calendar.data.Attend;

/**
 * Created by suhyu on 2017-12-09.
 */

public class AttendAdapter extends ArrayAdapter<Attend> {
    Context mContext;
    List<Attend> mList;
    LayoutInflater inf;

    public AttendAdapter(Context context, List<Attend> list) {
        super(context, R.layout.attend_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.attend_list_item, null);
        }

        final Attend data = mList.get(position);
        TextView nickNameTxt = (TextView) row.findViewById(R.id.nickNameTxt);

        nickNameTxt.setText(data.getAttendUser().getNickName());

        return row;
    }
}