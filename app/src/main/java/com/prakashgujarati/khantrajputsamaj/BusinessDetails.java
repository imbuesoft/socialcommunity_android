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
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusinessDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessDetails extends BaseActivity {
    private ImageView iv_logo;
    private TextView business_title,business_category,bus_web,bus_email,bus_location,bus_call;

    private Business businessData = new Business();

    private int businessId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        getIntentData();
        iv_logo = findViewById(R.id.iv_logo);
        business_title = findViewById(R.id.business_title);
        business_category = findViewById(R.id.business_category);
        bus_call = findViewById(R.id.bus_call);
        bus_web = findViewById(R.id.bus_web);
        bus_email = findViewById(R.id.bus_email);
        bus_location = findViewById(R.id.bus_location);
        callBusinessDetailsApi();


    }

    private void getIntentData() {
        businessId = getIntent().getIntExtra(Constant.BundleExtra.BUSINESS_ID,0);
    }


    private void callBusinessDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseBusinessDetails> call = apiService.getBusinessDetailsDataResponse(
                businessId
        );
        call.enqueue(new Callback<MainResponseBusinessDetails>() {
            @Override
            public void onResponse(Call<MainResponseBusinessDetails> call, Response<MainResponseBusinessDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            businessData = response.body().getData();
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
            public void onFailure(Call<MainResponseBusinessDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }


 /*   public void addressmap(View view) {
        TextView address = (TextView) view;
        Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="
                +address.getText().toString()));
        startActivity(geoIntent);
    }
    */
 private void setupData() {

     business_title.setText(businessData.getCompany());
     business_category.setText(businessData.getCategory());
     bus_location.setText(businessData.getAddress());
     bus_email.setText(businessData.getEmail());
     bus_call.setText(businessData.getContact());
     bus_web.setText(businessData.getDescription());
     RequestOptions requestOptions = new RequestOptions();
     requestOptions = requestOptions.transforms(new CircleCrop());
     requestOptions.placeholder(R.drawable.ic_user);
     Glide.with(this)
             .load(ApiClient.BASE_URL + "" + businessData.getLogo())
             .apply(requestOptions)
             .into(iv_logo);
 }

}
