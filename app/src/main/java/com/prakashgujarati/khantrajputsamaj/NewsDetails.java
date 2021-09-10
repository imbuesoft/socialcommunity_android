package com.prakashgujarati.khantrajputsamaj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusinessDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNewsDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.model.News;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetails extends BaseActivity {
    private ImageView iv_img,iv_pimg;
    private TextView user_name,desc,title;
    private News newsData = new News();

    private int newsId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getIntentData();
        title = findViewById(R.id.title);
        iv_img = findViewById(R.id.image);
        iv_pimg = findViewById(R.id.profile_image);
        user_name = findViewById(R.id.user_name);
        desc = findViewById(R.id.description);
        callNewsDetailsApi();
    }

    private void getIntentData() {
        newsId = getIntent().getIntExtra(Constant.BundleExtra.NEWS_ID,0);
    }

    private void callNewsDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseNewsDetails> call = apiService.getNewsDetailsDataResponse(
                newsId
        );
        call.enqueue(new Callback<MainResponseNewsDetails>() {
            @Override
            public void onResponse(Call<MainResponseNewsDetails> call, Response<MainResponseNewsDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            newsData = response.body().getData();
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
            public void onFailure(Call<MainResponseNewsDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setupData() {
        title.setText(newsData.getTitle());
        user_name.setText(newsData.getHeadline());
        desc.setText(newsData.getDetail_Report());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.BASE_URL + "" + newsData.getThumbnail())
                .apply(requestOptions)
                .into(iv_pimg);
       Glide.with(this)
                .load(ApiClient.BASE_URL + "" + newsData.getNews_Image())
                .apply(requestOptions)
                .into(iv_img);
    }
}
