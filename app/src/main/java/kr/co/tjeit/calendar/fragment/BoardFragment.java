package kr.co.tjeit.calendar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.calendar.R;
import kr.co.tjeit.calendar.adapter.BoardAdapter;
import kr.co.tjeit.calendar.data.Board;

/**
 * Created by suhyu on 2017-11-24.
 */

public class BoardFragment extends Fragment {
    private android.widget.ListView boardListView;
    List<Board> mList = new ArrayList<>();
    BoardAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);
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
        mAdapter = new BoardAdapter(getContext(), mList);
        boardListView.setAdapter(mAdapter);
    }

    private void setupEvents() {

    }
}
