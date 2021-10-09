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

public class Late_Form extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView iv_lateimage;
    private AppCompatTextView tv_lateimage;
    private AppCompatEditText et_firstname, et_middlename, et_lastname, et_latedate, et_birthdate,et_gujaratisavant,et_address,et_shradhhanjali,et_notification,et_contact;
    private AppCompatButton btn_create;

    private boolean isForThumbnail = false;
    private File thumbFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_late_form);

        iv_lateimage = findViewById(R.id.iv_lateimage);
        tv_lateimage = findViewById(R.id.tv_lateimage);
        et_firstname = findViewById(R.id.et_firstname);
        et_middlename = findViewById(R.id.et_middlename);
        et_lastname = findViewById(R.id.et_lastname);
        et_latedate = findViewById(R.id.et_latedate);
        et_birthdate = findViewById(R.id.et_birthdate);
        et_gujaratisavant = findViewById(R.id.et_gujaratisavant);
        et_address = findViewById(R.id.et_address);
        et_shradhhanjali = findViewById(R.id.et_shradhhanjali);
        et_notification = findViewById(R.id.et_notification);
        et_contact = findViewById(R.id.et_contact);
        btn_create = findViewById(R.id.btn_birthdayf_create);

        iv_lateimage.setOnClickListener(this);
        tv_lateimage.setOnClickListener(this);
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
            case R.id.iv_lateimage:
                isForThumbnail = true;
                if (isStoragePermissionGranted()) {
                    ImagePicker.Companion.with(this)
                            .compress(1024)            //Final image size will be less than 1 MB(Optional)
                            .start();
                }
                break;
            case R.id.tv_lateimage:

            case R.id.btn_birthdayf_create:
                callAddLateApi();
                break;
        }
    }

    private void callAddLateApi() {
        showLoader();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        MultipartBody.Part thumbImage = null;
        if (thumbFile != null) {
            thumbImage = MultipartBody.Part.createFormData("picture",
                    thumbFile.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), thumbFile));
        }


        RequestBody rb_firstname = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_firstname.getText().toString().trim());
        RequestBody rb_middlename = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_middlename.getText().toString().trim());
        RequestBody rb_lastname = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_lastname.getText().toString().trim());
        RequestBody rb_latedate = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_latedate.getText().toString().trim());
        RequestBody rb_birthdate = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_birthdate.getText().toString().trim());
        RequestBody rb_gujarati_savant = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_gujaratisavant.getText().toString().trim());
        RequestBody rb_address = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_address.getText().toString().trim());
        RequestBody rb_shradhhanjali = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_shradhhanjali.getText().toString().trim());
        RequestBody rb_notifications = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_notification.getText().toString().trim());
        RequestBody rb_contact = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_contact.getText().toString().trim());



        Call<BaseResponse> call = apiService.getCreateLateResponse(
                rb_firstname,
                rb_middlename,
                rb_lastname,
                rb_latedate,
                rb_birthdate,
                rb_gujarati_savant,
                rb_address,
                rb_shradhhanjali,
                rb_notifications,
                rb_contact,
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
                                .into(iv_lateimage);

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