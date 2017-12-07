package kr.co.tjeit.calendar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import kr.co.tjeit.calendar.AlertActivity;
import kr.co.tjeit.calendar.MainActivity;
import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.data.Group;
import kr.co.tjeit.calendar.data.Participant;
import kr.co.tjeit.calendar.util.ContextUtil;
import kr.co.tjeit.calendar.util.GlobalData;
import kr.co.tjeit.calendar.util.ServerUtil;

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

        final Participant data = mList.get(position);

        TextView userNickTxt = (TextView) row.findViewById(R.id.userNickTxt);
        TextView groupNameTxt = (TextView) row.findViewById(R.id.groupNameTxt);
        TextView acceptBtn = (TextView) row.findViewById(R.id.acceptBtn);
        TextView rejectBtn = (TextView) row.findViewById(R.id.rejectBtn);

        userNickTxt.setText(data.getMember().getNickName());
        groupNameTxt.setText(data.getParticipant_Group().getName());

        View.OnClickListener participant = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = 0;
                String status_result = "";
                if (view.getId() == R.id.acceptBtn) {
                    status = 1;
                    status_result = "수락";
                } else {
                    status = 2;
                    status_result = "거절";
                }
                final String finalStatus_result = status_result;
                ServerUtil.updateParticipantStatus(mContext, status, data.getId(), ContextUtil.getUserData(getContext()).getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Toast.makeText(mContext, finalStatus_result + "하셨습니다.", Toast.LENGTH_SHORT).show();
                        try {
                            GlobalData.usersGroup.clear();
                            JSONArray group = json.getJSONArray("group");
                            for (int i=0; i<group.length(); i++) {
                                Group g = Group.getGroupFromJson(group.getJSONObject(i));
                                GlobalData.usersGroup.add(g);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AlertActivity.alertActivity.getUserAlert();
//                        notifyDataSetChanged();
                    }
                });
            }
        };

        acceptBtn.setOnClickListener(participant);
        rejectBtn.setOnClickListener(participant);

        return row;
    }
}
