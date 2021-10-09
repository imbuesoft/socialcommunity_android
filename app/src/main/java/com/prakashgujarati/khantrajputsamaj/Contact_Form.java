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
import android.widget.Toast;

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

public class Contact_Form extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView iv_contactf_image;
    private AppCompatTextView tv_contactf_image;
    private AppCompatEditText et_contactf_designation, et_contactf_name, et_contactf_mobile, et_contactf_email;
    private AppCompatButton btn_contactf_create;

    private boolean isForThumbnail = false;
    private File thumbFile = null;
    private File newsImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        iv_contactf_image = findViewById(R.id.iv_contactf_image);
        tv_contactf_image = findViewById(R.id.tv_contactf_image);
        et_contactf_name = findViewById(R.id.et_contactf_name);
        et_contactf_designation = findViewById(R.id.et_contactf_designation);
        et_contactf_mobile = findViewById(R.id.et_contactf_mobile);
        et_contactf_email = findViewById(R.id.et_contactf_email);
        btn_contactf_create = findViewById(R.id.btn_contactf_create);

        iv_contactf_image.setOnClickListener(this);
        tv_contactf_image.setOnClickListener(this);
        btn_contactf_create.setOnClickListener(this);
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
            case R.id.iv_contactf_image:
                isForThumbnail = true;
                if (isStoragePermissionGranted()) {
                    ImagePicker.Companion.with(this)
                            .compress(1024)            //Final image size will be less than 1 MB(Optional)
                            .start();
                }
                break;
            case R.id.tv_contactf_image:

            case R.id.btn_contactf_create:
                callAddContactApi();
                break;
        }
    }

    private void callAddContactApi() {

        showLoader();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //MultipartBody.Part image = null;
        MultipartBody.Part thumbImage = null;
        if (thumbFile != null) {
            thumbImage = MultipartBody.Part.createFormData("picture",
                    thumbFile.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), thumbFile));
        }

        RequestBody rb_name = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_contactf_name.getText().toString().trim());
        RequestBody rb_designation = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_contactf_designation.getText().toString().trim());
        RequestBody rb_mobile = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_contactf_mobile.getText().toString().trim());
        RequestBody rb_email = RequestBody.create(
                MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_contactf_email.getText().toString().trim());

        Call<BaseResponse> call = apiService.getCreateContactResponse(
                rb_name,
                rb_designation,
                rb_mobile,
                rb_email,
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
                                .into(iv_contactf_image);

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