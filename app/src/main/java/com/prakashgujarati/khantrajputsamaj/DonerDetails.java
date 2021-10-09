package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseDonerDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Doner;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonerDetails extends BaseActivity {
    private TextView doner_title,doner_description,doner_type;

    private Doner donerData = new Doner();

    private int donerId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner_details);

        getIntentdata();
        doner_title = findViewById(R.id.doner_details_title);
        doner_description = findViewById(R.id.doner_details_description);
        doner_type = findViewById(R.id.doner_details_type);

        callDonerDetailsApi();

    }

    private void getIntentdata() {
        donerId = getIntent().getIntExtra(Constant.BundleExtra.DONER_ID,0);
    }

    private void callDonerDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseDonerDetails> call = apiService.getDonerDetailsResponse(
                donerId
        );
        call.enqueue(new Callback<MainResponseDonerDetails>() {
            @Override
            public void onResponse(Call<MainResponseDonerDetails> call, Response<MainResponseDonerDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            donerData = response.body().getData();
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
            public void onFailure(Call<MainResponseDonerDetails> call, Throwable t) {
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

        doner_title.setText(donerData.getTitle());
        doner_type.setText(donerData.getType());
        doner_description.setText(Html.fromHtml(donerData.getDescription()));

    }

}