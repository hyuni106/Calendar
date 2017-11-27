package kr.co.tjeit.calendar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Schedule;

/**
 * Created by the on 2017-11-27.
 */

public class ScheduleAdapter extends ArrayAdapter<Schedule> {
    Context mContext;
    List<Schedule> mList;
    LayoutInflater inf;

    public ScheduleAdapter(Context context, List<Schedule> list) {
        super(context, R.layout.schedule_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.schedule_list_item, null);
        }

        return row;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
