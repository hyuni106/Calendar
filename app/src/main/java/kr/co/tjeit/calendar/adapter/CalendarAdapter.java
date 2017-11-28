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
import kr.co.tjeit.calendar.data.Schedule;

/**
 * Created by the on 2017-11-28.
 */

public class CalendarAdapter extends ArrayAdapter<Schedule> {
    Context mContext;
    List<Schedule> mList;
    LayoutInflater inf;

    public CalendarAdapter(Context context, List<Schedule> list) {
        super(context, R.layout.calendar_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.calendar_list_item, null);
        }

        Schedule data = mList.get(position);

        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);
        contentTxt.setText(data.getContent());

        return row;
    }
}
