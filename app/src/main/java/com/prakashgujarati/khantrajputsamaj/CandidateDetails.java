package com.prakashgujarati.khantrajputsamaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.adapter.ProfileDetailAdapter;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidate;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidateDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Candidate;
import com.prakashgujarati.khantrajputsamaj.model.CandidateData;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateDetails extends BaseActivity {
    private ImageView ivProfiledetailPic;
    private TextView tvProfiledetailName;

    private Candidate candidateData = new Candidate();

    private int candidateId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_details);

        getIntentData();

        ivProfiledetailPic = findViewById(R.id.iv_profiledetail_pic);
        tvProfiledetailName = findViewById(R.id.tv_profiledetail_name);

        callCandidateDetailsApi();
    }

    private void getIntentData() {
        candidateId = getIntent().getIntExtra(Constant.BundleExtra.CANDIDATE_ID, 0);
    }

    private void callCandidateDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseCandidateDetails> call = apiService.getCandidateDetailsDataResponse(
                candidateId
        );
        call.enqueue(new Callback<MainResponseCandidateDetails>() {
            @Override
            public void onResponse(Call<MainResponseCandidateDetails> call, Response<MainResponseCandidateDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            candidateData = response.body().getData();
                            setupData();
                        } else {
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
            public void onFailure(Call<MainResponseCandidateDetails> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setupData() {

        tvProfiledetailName.setText(candidateData.getFirstName() + " " + candidateData.getLastName());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.BASE_URL + "" + candidateData.getPicture())
                .apply(requestOptions)
                .into(ivProfiledetailPic);
    }

}
