package com.prakashgujarati.khantrajputsamaj.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.LateListAdapter;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusiness;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLate;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.Late;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LateFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Late> lates = new ArrayList<>();
    private LateListAdapter lateListAdapter;

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


        // lates = new ArrayList<Late>();

        //lates.add(l1);
        //  lates.add(l1);


        lateListAdapter = new LateListAdapter(getActivity(), lates);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(lateListAdapter);
        lateListAdapter.notifyDataSetChanged();

        callLateListApi();
        return view;
    }

    private void callLateListApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseLate> call = apiService.getLateListResponse();
        call.enqueue(new Callback<MainResponseLate>() {
            @Override
            public void onResponse(Call<MainResponseLate> call, Response<MainResponseLate> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            lates.clear();
                            lates.addAll(response.body().getData());
                            lateListAdapter.notifyDataSetChanged();
                        } else {
                            lates.clear();
                            lateListAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<MainResponseLate> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }
}

