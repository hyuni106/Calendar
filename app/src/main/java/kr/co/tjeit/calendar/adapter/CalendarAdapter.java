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

        TextView categoryTxt = (TextView) row.findViewById(R.id.categoryTxt);
        TextView startTimeTxt = (TextView) row.findViewById(R.id.startTimeTxt);
        TextView endTimeTxt = (TextView) row.findViewById(R.id.endTimeTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);

        if (data.getTag() == 1) {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.firstColor));
        } else if (data.getTag() == 2) {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.secondColor));
        } else if (data.getTag() == 3) {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.thirdColor));
        } else {
            categoryTxt.setBackgroundColor(getContext().getResources().getColor(R.color.fourthColor));
        }

        contentTxt.setText(data.getTitle());

        SimpleDateFormat myDateFormat = new SimpleDateFormat("a hh:mm");
        startTimeTxt.setText(myDateFormat.format(data.getStart_date().getTime()));
        endTimeTxt.setText(myDateFormat.format(data.getEnd_date().getTime()));

        return row;
    }
}
