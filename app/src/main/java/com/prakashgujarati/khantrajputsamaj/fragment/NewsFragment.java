package com.prakashgujarati.khantrajputsamaj.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.prakashgujarati.khantrajputsamaj.ActivityAddNews;
import com.prakashgujarati.khantrajputsamaj.Activity_AddSurname;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.adapter.BusinessListAdapter;
import com.prakashgujarati.khantrajputsamaj.adapter.NewsListAdapter;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusiness;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNews;
import com.prakashgujarati.khantrajputsamaj.commans.BaseFragment;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.model.News;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class NewsFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<News> news = new ArrayList<>();
    private NewsListAdapter newsListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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

        newsListAdapter = new NewsListAdapter(getActivity(), news);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(newsListAdapter);
        newsListAdapter.notifyDataSetChanged();
        callNewsListApi();



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_news, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_news:
                Intent i = new Intent(getContext(), ActivityAddNews.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void callNewsListApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseNews> call = apiService.getNewsListResponse();
        call.enqueue(new Callback<MainResponseNews>() {
            @Override
            public void onResponse(Call<MainResponseNews> call, Response<MainResponseNews> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            news.clear();
                            news.addAll(response.body().getData());
                            newsListAdapter.notifyDataSetChanged();
                        } else {
                            news.clear();
                            newsListAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<MainResponseNews> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }


}
