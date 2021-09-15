package com.prakashgujarati.khantrajputsamaj.api;

import com.prakashgujarati.khantrajputsamaj.api.commans.BaseResponse;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusiness;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusinessDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidate;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidateDetails;

import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEmployee;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEmployeeDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLate;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLateDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNews;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNewsDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePlacement;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePlacementDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSports;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSportsDetails;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST(Constant.EndPoint.CANDIDATE_LIST)
    Call<MainResponseCandidate> getCandidateListResponse();

    @POST(Constant.EndPoint.CANDIDATE_DETAILS)
    Call<MainResponseCandidateDetails> getCandidateDetailsDataResponse(
            @Query(Constant.ApiKey.CANDIDATE_ID) int userId
    );

    @POST(Constant.EndPoint.BUSINESS_LIST)
    Call<MainResponseBusiness> getBusinessListResponse();

    @POST(Constant.EndPoint.BUSINESS_DETAILS)
    Call<MainResponseBusinessDetails> getBusinessDetailsDataResponse(
            @Query(Constant.ApiKey.BUSINESS_ID) int userId
    );

    @POST(Constant.EndPoint.NEWS_LIST)
    Call<MainResponseNews> getNewsListResponse();

    @POST(Constant.EndPoint.NEWS_DETAILS)
    Call<MainResponseNewsDetails> getNewsDetailsDataResponse(
            @Query(Constant.ApiKey.NEWS_ID) int userId
    );

    @POST(Constant.EndPoint.LATE_LIST)
    Call<MainResponseLate> getLateListResponse();

    @POST(Constant.EndPoint.LATE_DETAILS)
    Call<MainResponseLateDetails> getLateDetailsResponse(
            @Query(Constant.ApiKey.LATE_ID) int userId
    );

    @POST(Constant.EndPoint.EMPLOYEE_LIST)
    Call<MainResponseEmployee> getEmployeeListResponse();

    @POST(Constant.EndPoint.EMPLOYEE_DETAILS)
    Call<MainResponseEmployeeDetails> getEmployeeDetailsResponse(
            @Query(Constant.ApiKey.EMPLOYEE_ID) int userId
    );

    @POST(Constant.EndPoint.PLACEMENT_LIST)
    Call<MainResponsePlacement> getPlacementListResponse();

    @POST(Constant.EndPoint.PLACEMENT_DETAILS)
    Call<MainResponsePlacementDetails> getPlacementDetailsResponse(
            @Query(Constant.ApiKey.PLACEMENT_ID) int userId
    );

    @POST(Constant.EndPoint.SPORTS_LIST)
    Call<MainResponseSports> getSportsListResponse();

    @POST(Constant.EndPoint.SPORTS_DETAILS)
    Call<MainResponseSportsDetails> getSportsDetailsResponse(
            @Query(Constant.ApiKey.SPORTS_ID) int userId
    );

    @Multipart
    @POST(Constant.EndPoint.NEWS_CREATE)
    Call<BaseResponse> getCreateNewsResponse(
            @Part(Constant.ApiKey.HEADLINE) RequestBody rb_headLine,
            @Part(Constant.ApiKey.TITLE) RequestBody rb_title,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_category,
            @Part(Constant.ApiKey.DETAILS) RequestBody rb_details,
            @Part(Constant.ApiKey.REFERENCE) RequestBody rb_reference,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part thumbImage
    );
}
