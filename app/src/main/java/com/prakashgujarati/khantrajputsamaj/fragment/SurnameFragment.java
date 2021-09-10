package com.prakashgujarati.khantrajputsamaj.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.Activity_AddSurname;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.SurnameListAdapter;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SurnameFragment extends BaseFragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> surnames;
    private TextView alphabet, lbl_text;

    public SurnameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_surname, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.surname_recycleview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        surnames = new ArrayList<String>();

        surnames.add("Gujarati");
        surnames.add("Gohel");
        surnames.add("Lalakiya");
        surnames.add("Makwana");
        surnames.add("Sarvaiya");
        surnames.add("Solanki");
        surnames.add("Zala");

        SurnameListAdapter surnameListAdapter = new SurnameListAdapter(getActivity(), surnames);
        recyclerView.setAdapter(surnameListAdapter);
        surnameListAdapter.notifyDataSetChanged();
        return inflater.inflate(R.layout.surname_custom_text,container,false);
        //return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_surname, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_surname:
                Intent i = new Intent(getContext(), Activity_AddSurname.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
