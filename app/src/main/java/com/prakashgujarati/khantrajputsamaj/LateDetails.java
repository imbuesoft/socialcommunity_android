package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidateDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLate;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLateDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Business;
import com.prakashgujarati.khantrajputsamaj.model.Candidate;
import com.prakashgujarati.khantrajputsamaj.model.Late;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LateDetails extends BaseActivity {
    private ImageView iv_image;
    private TextView late_name,late_note;
    private Late lateData = new Late();

    private int lateId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.late_single);

        getIntentData();

        iv_image = findViewById(R.id.late_image);
        late_name = findViewById(R.id.late_name);
        late_note = findViewById(R.id.late_note_single);

        callLateDetailsApi();
    }

    private void callLateDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseLateDetails> call = apiService.getLateDetailsResponse(
                lateId
        );
        call.enqueue(new Callback<MainResponseLateDetails>() {
            @Override
            public void onResponse(Call<MainResponseLateDetails> call, Response<MainResponseLateDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            lateData = response.body().getData();
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
            public void onFailure(Call<MainResponseLateDetails> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }


    private void getIntentData() {
        lateId = getIntent().getIntExtra(Constant.BundleExtra.LATE_ID, 0);
    }
    private void setupData() {

        late_name.setText(lateData.getFirstName() + " " + lateData.getFirstName());
        late_note.setText(lateData.getLateDate()+" "+lateData.getLateDate());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.BASE_URL + "" + lateData.getPicture())
                .apply(requestOptions)
                .into(iv_image);



    }
}
