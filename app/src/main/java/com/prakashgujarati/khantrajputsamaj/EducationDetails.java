package com.prakashgujarati.khantrajputsamaj;

import androidx.appcompat.app.AppCompatActivity;

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
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEducationDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.model.Education;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationDetails extends BaseActivity {
    private ImageView education_image;
    private TextView education_name,education_qualification,education_gender,education_note;

    private Education educationdata = new Education();

    private int educationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_details);

        getIntentData();
        education_image = findViewById(R.id.educationd_image);
        education_name = findViewById(R.id.educationd_name);
        education_qualification = findViewById(R.id.educationd_qualification);
        education_gender = findViewById(R.id.educationd_gender);
        education_note = findViewById(R.id.educationd_note);

        callEducationDetailsApi();

    }

    private void callEducationDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseEducationDetails> call = apiService.getEducationDetailsDataResponse(
                educationId
        );
        call.enqueue(new Callback<MainResponseEducationDetails>() {
            @Override
            public void onResponse(Call<MainResponseEducationDetails> call, Response<MainResponseEducationDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            educationdata = response.body().getData();
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
            public void onFailure(Call<MainResponseEducationDetails> call, Throwable t) {
//                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void getIntentData() {
        educationId = getIntent().getIntExtra(Constant.BundleExtra.EDUCATION_ID,0);
    }

    private void setupData() {

        education_name.setText(educationdata.getName());
        education_qualification.setText(educationdata.getQualification());
        education_gender.setText(educationdata.getGender());
        education_note.setText(Html.fromHtml(educationdata.getNote()));

     /*String imageUri = "https://khantrajputsamaj.in/api/busniess_list/public/busniess_logos/1628516476Screenshot from 2021-07-15 18-19-16.png";
     iv_logo = findViewById(R.id.iv_logo);
     Picasso.get().load(imageUri).into(iv_logo);*/
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.IMAGE_URL + "" + educationdata.getPicture())
                .apply(requestOptions)
                .into(education_image);
    }
}