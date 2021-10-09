package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEventDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Event;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetails extends BaseActivity {
    private ImageView iv_logo,image;
    private TextView title,description;

    private Event eventdata = new Event();

    private int eventsId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        getIntentData();
        iv_logo = findViewById(R.id.profile_image);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);

        callEventDetailsApi();
    }

    private void callEventDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseEventDetails> call = apiService.getEventDetailsResponse(
                eventsId
        );
        call.enqueue(new Callback<MainResponseEventDetails>() {
            @Override
            public void onResponse(Call<MainResponseEventDetails> call, Response<MainResponseEventDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            eventdata = response.body().getData();
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
            public void onFailure(Call<MainResponseEventDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void getIntentData() {
        eventsId = getIntent().getIntExtra(Constant.BundleExtra.EVENT_ID,0);
    }
    private void setupData() {

        title.setText(eventdata.getTitle() + " " + eventdata.getHeadline());
        description.setText(Html.fromHtml(eventdata.getDetailReport()));

     /*String imageUri = "https://khantrajputsamaj.in/api/busniess_list/public/busniess_logos/1628516476Screenshot from 2021-07-15 18-19-16.png";
     iv_logo = findViewById(R.id.iv_logo);
     Picasso.get().load(imageUri).into(iv_logo);*/
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.IMAGE_URL + "" + eventdata.getNewsImage())
                .apply(requestOptions)
                .into(iv_logo);
        Glide.with(this)
                .load(ApiClient.IMAGE_URL + "" + eventdata.getNewsImage())
                .apply(requestOptions)
                .into(image);
    }
}
