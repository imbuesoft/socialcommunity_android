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
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseContactDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNewsDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Contact;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactDetails extends BaseActivity {

    private ImageView iv_contact_image;
    private TextView tv_contact_name,tv_contact_designation,tv_contact_mobile,tv_contact_email;

    private Contact contactdata = new Contact();

    private int contactId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        getIntentData();

        iv_contact_image = findViewById(R.id.iv_contact_image);
        tv_contact_name = findViewById(R.id.tv_contact_name);
        tv_contact_email = findViewById(R.id.tv_contact_email);
        tv_contact_mobile = findViewById(R.id.tv_contact_mobile);
        tv_contact_designation = findViewById(R.id.tv_contact_designation);
        callContactDetailsApi();

    }

    private void getIntentData() {
        contactId = getIntent().getIntExtra(Constant.BundleExtra.CONTACT_ID,0);
    }


    private void callContactDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseContactDetails> call = apiService.getContactDetailsResponse(
                contactId
        );
        call.enqueue(new Callback<MainResponseContactDetails>() {
            @Override
            public void onResponse(Call<MainResponseContactDetails> call, Response<MainResponseContactDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            contactdata = response.body().getData();
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
            public void onFailure(Call<MainResponseContactDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setupData() {
        tv_contact_name.setText(contactdata.getName());
        tv_contact_designation.setText(contactdata.getDesignation());
        tv_contact_mobile.setText(String.valueOf(contactdata.getMobile()));
        tv_contact_email.setText(contactdata.getEmail());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.IMAGE_URL + "" + contactdata.getPicture())
                .apply(requestOptions)
                .into(iv_contact_image);

    }
}