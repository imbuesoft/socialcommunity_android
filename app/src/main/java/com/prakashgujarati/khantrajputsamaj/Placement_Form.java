package com.prakashgujarati.khantrajputsamaj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Placement_Form extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView iv_placement_thumbnail;
    private AppCompatTextView tv_placement_thumbnail;
    private AppCompatEditText et_headline, et_title, et_placementcategory, et_placement_description, et_skills,et_educationqualification,et_reference_url,et_reporteddatetime;
    private AppCompatButton btn_placement_create;

    private boolean isForThumbnail = false;
    private File thumbFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_form);

        iv_placement_thumbnail = findViewById(R.id.iv_placement_thumbnail);
        tv_placement_thumbnail = findViewById(R.id.tv_placement_thumbnail);
        et_headline = findViewById(R.id.et_headline);
        et_title = findViewById(R.id.et_title);
        et_placementcategory = findViewById(R.id.et_placementcategory);
        et_placement_description = findViewById(R.id.et_placement_desription);
        et_skills = findViewById(R.id.et_skills);
        et_educationqualification = findViewById(R.id.et_educationqualification);
        et_reference_url = findViewById(R.id.et_referenceurl);
        et_reporteddatetime = findViewById(R.id.et_reporteddatetime);
        btn_placement_create = findViewById(R.id.btn_birthdayf_create);

        iv_placement_thumbnail.setOnClickListener(this);
        tv_placement_thumbnail.setOnClickListener(this);
        btn_placement_create.setOnClickListener(this);

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
            case R.id.iv_placement_thumbnail:
                isForThumbnail = true;
                if (isStoragePermissionGranted()) {
                    ImagePicker.Companion.with(this)
                            .compress(1024)            //Final image size will be less than 1 MB(Optional)
                            .start();
                }
                break;
            case R.id.tv_placement_thumbnail:

            case R.id.btn_birthdayf_create:
                callAddPlacementApi();
                break;
        }
    }

    private void callAddPlacementApi() {
        showLoader();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        MultipartBody.Part thumbImage = null;
        if (thumbFile != null) {
            thumbImage = MultipartBody.Part.createFormData("thumbnail",
                    thumbFile.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), thumbFile));
        }


        RequestBody rb_headline = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_headline.getText().toString().trim());
        RequestBody rb_title = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_title.getText().toString().trim());
        RequestBody rb_category = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_placementcategory.getText().toString().trim());
        RequestBody rb_description = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_placement_description.getText().toString().trim());
        RequestBody rb_skills = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_skills.getText().toString().trim());
        RequestBody rb_educationqualification = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_educationqualification.getText().toString().trim());
        RequestBody rb_referenceurl = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_reference_url.getText().toString().trim());
        RequestBody rb_reporteddatetime = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_reporteddatetime.getText().toString().trim());

        Call<BaseResponse> call = apiService.getCreatePlacementResponse(
                rb_headline,
                rb_title,
                rb_category,
                rb_description,
                rb_skills,
                rb_educationqualification,
                rb_referenceurl,
                rb_reporteddatetime,
                thumbImage
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

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    if (isForThumbnail) {
                        Picasso.get().load(resultUri)
                                .placeholder(R.drawable.ic_user)
                                .into(iv_placement_thumbnail);

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
        }
    }
}
