package com.prakashgujarati.khantrajputsamaj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Photo_Form_Activity extends BaseActivity implements View.OnClickListener {
    private AppCompatImageView iv_image_thumbnail;
    private AppCompatEditText et_category,et_location,et_description,et_eventtitle,et_date;
    private AppCompatTextView tv_uploadimage;
    private AppCompatButton btn_create;

    private boolean isForThumbnail = false;
    private File thumbFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_form);

        iv_image_thumbnail = findViewById(R.id.iv_image_thumbnail);
        et_category = findViewById(R.id.et_category);
        tv_uploadimage = findViewById(R.id.tv_uploadimage);
        et_location = findViewById(R.id.et_location);
        et_description = findViewById(R.id.et_description);
        et_eventtitle = findViewById(R.id.et_eventtitle);
        et_date = findViewById(R.id.et_date);
        btn_create = findViewById(R.id.btn_create);

        tv_uploadimage.setOnClickListener(this);
        iv_image_thumbnail.setOnClickListener(this);
        btn_create.setOnClickListener(this);
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
            case R.id.iv_image_thumbnail:
                isForThumbnail = true;
                if (isStoragePermissionGranted()) {
                    ImagePicker.Companion.with(this)
                            .compress(1024)            //Final image size will be less than 1 MB(Optional)
                            .start();
                }
                break;
            case R.id.tv_uploadimage:

            case R.id.btn_create:
                callAddPhotoApi();
                break;
        }
    }

    private void callAddPhotoApi() {
        showLoader();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        MultipartBody.Part thumbImage = null;
        if (thumbFile != null) {
            thumbImage = MultipartBody.Part.createFormData("path",
                    thumbFile.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), thumbFile));
        }

        RequestBody rb_photo_category = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_category.getText().toString().trim());
        RequestBody rb_photo_eventtitle = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_eventtitle.getText().toString().trim());
        RequestBody rb_photo_location = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_location.getText().toString().trim());
        RequestBody rb_photo_description = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_description.getText().toString().trim());
        RequestBody rb_photo_date = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_date.getText().toString().trim());


        Call<BaseResponse> call = apiService.getCreatePhotoResponse(
                rb_photo_category,
                rb_photo_eventtitle,
                rb_photo_location,
                rb_photo_description,
                rb_photo_date,
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
                                .into(iv_image_thumbnail);

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