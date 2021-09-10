package com.prakashgujarati.khantrajputsamaj.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.SportsAdapter;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSports;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.Sports;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Sports> sports = new ArrayList<>();
    private SportsAdapter sportsAdapter;

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

        sportsAdapter = new SportsAdapter(getActivity(), sports);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(sportsAdapter);
        sportsAdapter.notifyDataSetChanged();
        callSportsListApi();
        return view;
    }

    private void callSportsListApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseSports> call = apiService.getSportsListResponse();
        call.enqueue(new Callback<MainResponseSports>() {
            @Override
            public void onResponse(Call<MainResponseSports> call, Response<MainResponseSports> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            sports.clear();
                            sports.addAll(response.body().getData());
                            sportsAdapter.notifyDataSetChanged();
                        } else {
                            sports.clear();
                            sportsAdapter.notifyDataSetChanged();
                            showError(response.body().getMessage());
                        }
                    } else {
                        showError(response.message());
                    }
                } catch (Exception e) {
                    Log.d("TAG", "onResponse: " + e);
                }
            }

            @Override
            public void onFailure(Call<MainResponseSports> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }
}
