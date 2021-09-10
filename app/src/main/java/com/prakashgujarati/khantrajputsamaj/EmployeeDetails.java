package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.prakashgujarati.khantrajputsamaj.api.ApiClient;
import com.prakashgujarati.khantrajputsamaj.api.ApiInterface;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEmployeeDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLateDetails;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.model.Late;
import com.prakashgujarati.khantrajputsamaj.model.SimpleItemFour;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetails extends BaseActivity {
    private ImageView employee_logo;
    private TextView employee_title,employee_category,bus_web,bus_call,bus_email,bus_location;
    private SimpleItemFour employeeData = new SimpleItemFour();

    private int employeeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_details);

        getIntentData();
        bus_call = findViewById(R.id.bus_call);
        bus_email = findViewById(R.id.bus_email);
        bus_location = findViewById(R.id.bus_location);
        bus_web = findViewById(R.id.bus_web);
        employee_title = findViewById(R.id.employee_title);
        employee_category = findViewById(R.id.employee_category);
        employee_logo = findViewById(R.id.employee_logo);

        callLateDetailsApi();
    }

    private void callLateDetailsApi() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MainResponseEmployeeDetails> call = apiService.getEmployeeDetailsResponse(
                employeeId
        );
        call.enqueue(new Callback<MainResponseEmployeeDetails>() {
            @Override
            public void onResponse(Call<MainResponseEmployeeDetails> call, Response<MainResponseEmployeeDetails> response) {
                hideLoader();
                try {
                    if (response.code() == 200) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                             employeeData = response.body().getData();
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
            public void onFailure(Call<MainResponseEmployeeDetails> call, Throwable t) {
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }


    private void getIntentData() {
        employeeId = getIntent().getIntExtra(Constant.BundleExtra.EMPLOYEE_ID, 0);
    }
    private void setupData() {
        bus_location.setText(employeeData.getAddress());
        bus_email.setText(employeeData.getEmail());
        bus_call.setText(employeeData.getContact());
        bus_web.setText(employeeData.getOffice());
        employee_title.setText(employeeData.getFirstName());
        employee_category.setText(employeeData.getCategory());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());
        requestOptions.placeholder(R.drawable.ic_user);
        Glide.with(this)
                .load(ApiClient.BASE_URL + "" + employeeData.getLogo())
                .apply(requestOptions)
                .into(employee_logo);
    }
}
