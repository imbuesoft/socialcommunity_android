package com.prakashgujarati.khantrajputsamaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.commans.BaseResponse;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Surname_FormActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText et_form_surname;
    private AppCompatButton btn_form_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surname_form);

        et_form_surname = findViewById(R.id.et_form_surname);
        btn_form_submit = findViewById(R.id.btn_form_submit);

        btn_form_submit.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_form_submit:
                callAddSurnameApi();
                break;
        }
    }

    private void callAddSurnameApi() {
        showLoader();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        RequestBody rb_surname = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_form_surname.getText().toString().trim());

        Call<BaseResponse> call = apiService.getCreateSurnameResponse(
                rb_surname
        );

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                hideLoader();

                if (response.code() == 200) {
                    if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                        showAlert("" + response.body().getMessage());
                        finish();
                    } else {
                        showError(response.body().getMessage());
                    }
                } else {
                    showError(response.message());
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                // Log error here since request failed
                hideLoader();
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }
}