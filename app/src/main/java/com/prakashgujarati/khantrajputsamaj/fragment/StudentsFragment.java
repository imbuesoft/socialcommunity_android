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
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.SimpleItemFour;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private ArrayList<SimpleItemFour> students;

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

        //SimpleItemFour s1 = new SimpleItemFour("","(Dr) Prakash Gujarati","Ph.D. in Computer Science","Saurastra University, Rajkot");

        //students = new ArrayList<SimpleItemFour>();

        //students.add(s1);
        //students.add(s1);


        //SimpleItemFourAdapter simpleItemFourAdapter = new SimpleItemFourAdapter(getActivity(), students);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        //recyclerView.setAdapter(simpleItemFourAdapter);
       // simpleItemFourAdapter.notifyDataSetChanged();

        return view;
    }

}
