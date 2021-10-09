package com.prakashgujarati.khantrajputsamaj;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.commans.BaseResponse;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Birthday_Form extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText et_name, et_birthdate, et_time, et_event, et_wishes;
    private AppCompatButton btn_birthday_create;

//    private boolean isForThumbnail = false;
//    private File thumbFile = null;
 //   private File newsImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_form);

        et_name = findViewById(R.id.et_name);
        et_birthdate = findViewById(R.id.et_birthdate);
        et_time = findViewById(R.id.et_time);
        et_event = findViewById(R.id.et_event);
        et_wishes = findViewById(R.id.et_wishes);
        btn_birthday_create = findViewById(R.id.btn_birthdayf_create);

        btn_birthday_create.setOnClickListener(this);
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_birthdayf_create:
                callAddBirthdayApi();
                break;
        }
    }
    private void callAddBirthdayApi() {
        showLoader();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        RequestBody rb_name = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_name.getText().toString().trim());
        RequestBody rb_birthdate = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_birthdate.getText().toString().trim());
        RequestBody rb_time = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_time.getText().toString().trim());
        RequestBody rb_place = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_event.getText().toString().trim());
        RequestBody rb_wishes = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_wishes.getText().toString().trim());

        Call<BaseResponse> call = apiService.getCreateBirthdayResponse(
                rb_name,
                rb_birthdate,
                rb_time,
                rb_place,
                rb_wishes
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

          /*  if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    if (isForThumbnail) {
                        Picasso.get().load(resultUri)
                                .placeholder(R.drawable.ic_user)
                                .into(iv_education_image);

                        //You can get File object from intent
                        thumbFile = new File(resultUri.getPath());

                    }

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            } else {

                //Image Uri will not be null for RESULT_OK
                Uri fileUri = data.getData();

                CropImage.activity(fileUri).start(this);

            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            showAlert(ImagePicker.Companion.getError(data));
        } else {
            showAlert("Task Cancelled");
        }*/
        }

    }
}