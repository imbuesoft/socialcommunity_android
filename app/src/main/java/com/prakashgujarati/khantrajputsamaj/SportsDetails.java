package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNewsDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSportsDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.News;
import com.prakashgujarati.khantrajputsamaj.model.Sports;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsDetails extends BaseActivity {
    private ImageView iv_img,iv_pimg;
    private TextView user_name,desc,title;
    private Sports sportsData = new Sports();
    private int sportsId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_details);
        getIntentData();
        title = findViewById(R.id.title);
        iv_img = findViewById(R.id.image);
        iv_pimg = findViewById(R.id.profile_image);
        user_name = findViewById(R.id.user_name);
        desc = findViewById(R.id.description);
        callSportsDetailsApi();
    }
    private void getIntentData() {
        sportsId = getIntent().getIntExtra(Constant.BundleExtra.SPORTS_ID,0);
    }
    private void callSportsDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseSportsDetails> call = apiService.getSportsDetailsResponse(
                sportsId
        );
        call.enqueue(new Callback<MainResponseSportsDetails>() {
            @Override
            public void onResponse(Call<MainResponseSportsDetails> call, Response<MainResponseSportsDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            sportsData = response.body().getData();
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
            public void onFailure(Call<MainResponseSportsDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }
    private void setupData() {
        title.setText(sportsData.getTitle());
        user_name.setText(sportsData.getHeadline());
        desc.setText(Html.fromHtml(sportsData.getDetail_Report()));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.IMAGE_URL + "" + sportsData.getThumbnail())
                .apply(requestOptions)
                .into(iv_pimg);
        Glide.with(this)
                .load(ApiClient.IMAGE_URL + "" + sportsData.getThumbnail())
                .apply(requestOptions)
                .into(iv_img);
    }
}
