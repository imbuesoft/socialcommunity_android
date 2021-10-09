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

public class Candidate_Form extends BaseActivity implements View.OnClickListener {
    private AppCompatImageView ivThumbnail;
    private AppCompatTextView logo;
    private AppCompatEditText et_firstname, et_lastname, et_middilename, et_birthdate, et_birthtime,et_birthplace,et_height,et_weight,et_education,et_occupation,et_fathername,et_mothername,et_brothers,et_sisters,et_fatheroccupation,et_motheroccupation,et_fathercontact,et_contact,et_email,et_residentaddress,et_nativeaddress,et_maternal,et_maternalplace,et_hobbies,et_exceptations,et_remark;
    private AppCompatButton btncreate;

    private boolean isForThumbnail = false;
    private File thumbFile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_form);

        ivThumbnail = findViewById(R.id.iv_thumbnail);
        logo = findViewById(R.id.logo);
        et_firstname = findViewById(R.id.et_firstname);
        et_middilename = findViewById(R.id.et_middlename);
        et_lastname = findViewById(R.id.et_lastname);
        et_birthdate = findViewById(R.id.et_birthdate);
        et_birthtime = findViewById(R.id.et_birthtime);
        et_birthplace = findViewById(R.id.et_birthplace);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_Weight);
        et_education = findViewById(R.id.et_education);
        et_occupation = findViewById(R.id.et_occupation);
        et_fathername = findViewById(R.id.et_fathername);
        et_mothername = findViewById(R.id.et_mothername);
        et_brothers = findViewById(R.id.et_brothers);
        et_sisters = findViewById(R.id.et_sisters);
        et_fatheroccupation = findViewById(R.id.et_fatheroccupation);
        et_motheroccupation = findViewById(R.id.et_motheroccupation);
        et_fathercontact = findViewById(R.id.et_fathercontact);
        et_contact = findViewById(R.id.et_contact);
        et_email = findViewById(R.id.et_email);
        et_residentaddress = findViewById(R.id.et_residentaddress);
        et_nativeaddress = findViewById(R.id.et_nativeaddress);
        et_maternal = findViewById(R.id.et_maternal);
        et_maternalplace = findViewById(R.id.et_maternalplace);
        et_hobbies = findViewById(R.id.et_hobbies);
        et_exceptations = findViewById(R.id.et_Expectations);
        et_remark = findViewById(R.id.et_Remark);
        btncreate = findViewById(R.id.btn_create);

        ivThumbnail.setOnClickListener(this);
        logo.setOnClickListener(this);
        btncreate.setOnClickListener(this);
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
        case R.id.iv_thumbnail:
        case R.id.logo:
        isForThumbnail = true;
        if (isStoragePermissionGranted()) {
        ImagePicker.Companion.with(this)
        .compress(1024)            //Final image size will be less than 1 MB(Optional)
        .start();
        }
        break;

        case R.id.btn_create:
        callAddemployeeApi();
        break;
        }
        }
private void callAddemployeeApi() {
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
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_middilename.getText().toString().trim());
        RequestBody rb_lastname = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_lastname.getText().toString().trim());
        RequestBody rb_birthdate = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_birthdate.getText().toString().trim());
        RequestBody rb_birthplace = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_birthplace.getText().toString().trim());
        RequestBody rb_height = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_height.getText().toString().trim());
        RequestBody rb_weight = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_weight.getText().toString().trim());
        RequestBody rb_education = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_education.getText().toString().trim());
        RequestBody rb_occupation = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_occupation.getText().toString().trim());
        RequestBody rb_fathername = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_fathername.getText().toString().trim());
        RequestBody rb_mothername = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_mothername.getText().toString().trim());
        RequestBody rb_brothers = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_brothers.getText().toString().trim());
        RequestBody rb_sisters = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_sisters.getText().toString().trim());
        RequestBody rb_fatheroccupation = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_fatheroccupation.getText().toString().trim());
        RequestBody rb_motheroccupation = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_motheroccupation.getText().toString().trim());
        RequestBody rb_fathercontact = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_fathercontact.getText().toString().trim());
        RequestBody rb_contact = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_contact.getText().toString().trim());
        RequestBody rb_email = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_email.getText().toString().trim());
        RequestBody rb_residentaddress = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_residentaddress.getText().toString().trim());
        RequestBody rb_nativeaddress = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_nativeaddress.getText().toString().trim());
        RequestBody rb_maternal = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_maternal.getText().toString().trim());
        RequestBody rb_maternalplace = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_maternalplace.getText().toString().trim());
        RequestBody rb_hobbies = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_hobbies.getText().toString().trim());
        RequestBody rb_expectations = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_exceptations.getText().toString().trim());
        RequestBody rb_remark = RequestBody.create(
        MediaType.parse(Constant.ClickConstant.TEXT_PLAIN), et_remark.getText().toString().trim());
        Call<BaseResponse> call = apiService.getCreateCandidateResponse(
        rb_firstname,
        rb_middlename,
        rb_lastname,
        rb_birthdate,
        rb_birthplace,
        rb_height,
        rb_weight,
        rb_education,
        rb_occupation,
        rb_fathername,
        rb_mothername,
        rb_brothers,
        rb_sisters,
        rb_fatheroccupation,
        rb_motheroccupation,
        rb_fathercontact,
        rb_contact,
        rb_email,
        rb_residentaddress,
        rb_nativeaddress,
        rb_maternal,
        rb_maternalplace,
        rb_hobbies,
        rb_expectations,
        rb_remark,
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
                                .into(ivThumbnail);

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