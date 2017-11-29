package kr.co.tjeit.calendar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.ViewBoardActivity;
import kr.co.tjeit.calendar.WriteBoardActivity;
import kr.co.tjeit.calendar.adapter.BoardAdapter;
import kr.co.tjeit.calendar.data.Board;
import kr.co.tjeit.calendar.util.GlobalData;

/**
 * Created by suhyu on 2017-11-24.
 */

public class BoardFragment extends Fragment {
    private android.widget.ListView boardListView;
    List<Board> mList = new ArrayList<>();
    BoardAdapter mAdapter;
    private com.melnykov.fab.FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);
        this.fab = (FloatingActionButton) v.findViewById(R.id.fab);
        this.boardListView = (ListView) v.findViewById(R.id.boardListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {
        mAdapter = new BoardAdapter(getContext(), GlobalData.allBoard);
        boardListView.setAdapter(mAdapter);
    }

    private void setupEvents() {
        boardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ViewBoardActivity.class);
                intent.putExtra("board_Item", GlobalData.allBoard.get(position));
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WriteBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
