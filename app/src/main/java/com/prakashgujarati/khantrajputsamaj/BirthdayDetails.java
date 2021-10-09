package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBirthdayDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Birthday;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BirthdayDetails extends BaseActivity {

    private TextView birthday_name,birthday_date,birthday_place,birthday_wishes,birthday_time;

    private Birthday birthdaydata = new Birthday();

    private int birthdayId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_details);

        getIntentData();
        birthday_name = findViewById(R.id.birthday_name);
        birthday_date = findViewById(R.id.birthday_date);
        birthday_place = findViewById(R.id.birthday_place);
        birthday_time = findViewById(R.id.birthday_time);
        birthday_wishes = findViewById(R.id.birthday_wishes);

        callBirthdayDetailsApi();


        }

        private void getIntentData() {
            birthdayId = getIntent().getIntExtra(Constant.BundleExtra.BIRTHDAY_ID,0);
        }


        private void callBirthdayDetailsApi() {
            showLoader();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<MainResponseBirthdayDetails> call = apiService.getBirthdayDetailsResponse(
                    birthdayId
            );
            call.enqueue(new Callback<MainResponseBirthdayDetails>() {
                @Override
                public void onResponse(Call<MainResponseBirthdayDetails> call, Response<MainResponseBirthdayDetails> response) {
                    hideLoader();
                    try {
                        if (response.code() == 200) {
                            if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                                birthdaydata = response.body().getData();
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
                public void onFailure(Call<MainResponseBirthdayDetails> call, Throwable t) {
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

            birthday_name.setText(birthdaydata.getName());
            birthday_time.setText(birthdaydata.getTime());
            birthday_place.setText(birthdaydata.getPlace());
            birthday_wishes.setText(Html.fromHtml(birthdaydata.getWishes()));
            birthday_date.setText(birthdaydata.getBirthdate());


        }

}
