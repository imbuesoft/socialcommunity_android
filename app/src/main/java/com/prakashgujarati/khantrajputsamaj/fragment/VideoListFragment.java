package com.prakashgujarati.khantrajputsamaj.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.SimpleItemFourAdapter;
import com.prakashgujarati.khantrajputsamaj.adapter.VideoItemListAdapter;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.SimpleItemFour;
import com.prakashgujarati.khantrajputsamaj.model.Video;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private ArrayList<Video> employees;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_late, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.late_recycleview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

       // SimpleItemFour e1 = new SimpleItemFour("","Khant Rajput Samaj History in Gujarati","Prakash Gujarati","06-03-2020 17:03:00");

       // employees = new ArrayList<SimpleItemFour>();

       // employees.add(e1);

       // employees.add(e1);


        //VideoItemListAdapter simpleItemFourAdapter = new VideoItemListAdapter(getActivity(), employees);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        //recyclerView.setAdapter(simpleItemFourAdapter);
        //simpleItemFourAdapter.notifyDataSetChanged();

        return view;
    }

}
