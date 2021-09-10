package com.prakashgujarati.khantrajputsamaj.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.PlacementListAdapter;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePlacement;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.Placement;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacementFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Placement> placement = new ArrayList<>();
    private PlacementListAdapter placementlistAdapter;

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


        placementlistAdapter = new PlacementListAdapter(getActivity(), placement);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(placementlistAdapter);
        placementlistAdapter.notifyDataSetChanged();

        callPlacementListApi();
        return view;
    }

    private void callPlacementListApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponsePlacement> call = apiService.getPlacementListResponse();
        call.enqueue(new Callback<MainResponsePlacement>() {
            @Override
            public void onResponse(Call<MainResponsePlacement> call, Response<MainResponsePlacement> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            placement.clear();
                            placement.addAll(response.body().getData());
                            placementlistAdapter.notifyDataSetChanged();
                        } else {
                            placement.clear();
                            placementlistAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<MainResponsePlacement> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }
    }

