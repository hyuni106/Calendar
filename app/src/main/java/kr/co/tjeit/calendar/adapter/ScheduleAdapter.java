package kr.co.tjeit.calendar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        Schedule data = mList.get(position);

        TextView categoryTxt = (TextView) row.findViewById(R.id.categoryTxt);
        TextView dayTxt = (TextView) row.findViewById(R.id.dayTxt);
        TextView monthTxt = (TextView) row.findViewById(R.id.monthTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);
        TextView memoTxt = (TextView) row.findViewById(R.id.memoTxt);

        if (data.getTag() == 1) {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.firstColor));
        } else if (data.getTag() == 2) {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.secondColor));
        } else if (data.getTag() == 3) {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.thirdColor));
        } else {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.fourthColor));
        }

        monthTxt.setText(String.format(Locale.KOREA, "%d월", data.getStart_date().get(Calendar.MONTH) + 1));
        dayTxt.setText(data.getStart_date().get(Calendar.DAY_OF_MONTH) + "");
        contentTxt.setText(data.getTitle());
        memoTxt.setText(data.getMemo());


        return row;
    }
}
