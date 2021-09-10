package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePlacementDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Placement;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacementDetails extends BaseActivity {
    private TextView placement_title,placement_category,bus_web,bus_call,bus_email,bus_location;
    private ImageView placement_image;
    private Placement placementdata = new Placement();
    private int placementiId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_details);
        getIntentData();

        placement_image = findViewById(R.id.placementdetail_image);
        placement_title = findViewById(R.id.placementdetail_title);
        placement_category = findViewById(R.id.placementdetail_category);
        bus_web = findViewById(R.id.bus_web);
        bus_call = findViewById(R.id.bus_call);
        bus_email = findViewById(R.id.bus_email);
        bus_location = findViewById(R.id.bus_location);
        callPlacementDetailsApi();
    }
    private void getIntentData() {
        placementiId = getIntent().getIntExtra(Constant.BundleExtra.PLACEMENT_ID,0);
    }

    private void callPlacementDetailsApi() {

        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponsePlacementDetails> call = apiService.getPlacementDetailsResponse(
                placementiId
        );
        call.enqueue(new Callback<MainResponsePlacementDetails>() {
            @Override
            public void onResponse(Call<MainResponsePlacementDetails> call, Response<MainResponsePlacementDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            placementdata = (Placement) response.body().getData();
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
            public void onFailure(Call<MainResponsePlacementDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setupData() {
        bus_web.setText(placementdata.getEducationQualification());
        bus_email.setText(placementdata.getHeadline());
        bus_call.setText(placementdata.getDescription());
        bus_location.setText(placementdata.getSkills());
        placement_title.setText(placementdata.getTitle());
        placement_category.setText(placementdata.getCategory());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.BASE_URL + "" + placementdata.getThumbnail())
                .apply(requestOptions)
                .into(placement_image);

    }
}



