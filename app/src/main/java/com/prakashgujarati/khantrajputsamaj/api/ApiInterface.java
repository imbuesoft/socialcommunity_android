package com.prakashgujarati.khantrajputsamaj.api;

import com.prakashgujarati.khantrajputsamaj.api.commans.BaseResponse;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBirthday;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBirthdayDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusiness;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseBusinessDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidate;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseCandidateDetails;

import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseContact;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseContactDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseDoner;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseDonerDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEducation;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEducationDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEmployee;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEmployeeDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEvent;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseEventDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLate;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseLateDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNews;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseNewsDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePhoto;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePhotoDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePlacement;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponsePlacementDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSports;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSportsDetails;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseSurname;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseVideo;
import com.prakashgujarati.khantrajputsamaj.api.response.MainResponseVideoDetails;
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

    @POST(Constant.EndPoint.EDUCATION_LIST)
    Call<MainResponseEducation> getEducationListResponse();

    @POST(Constant.EndPoint.EDUCATION_DETAILS)
    Call<MainResponseEducationDetails> getEducationDetailsDataResponse(
            @Query(Constant.ApiKey.EDUCATION_ID) int userId
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

    @POST(Constant.EndPoint.EVENT_LIST)
    Call<MainResponseEvent> getEventListResponse();

    @POST(Constant.EndPoint.EVENT_DETAILS)
    Call<MainResponseEventDetails> getEventDetailsResponse(
            @Query(Constant.ApiKey.EVENT_ID) int userId
    );

    @POST(Constant.EndPoint.SPORTS_LIST)
    Call<MainResponseSports> getSportsListResponse();

    @POST(Constant.EndPoint.SPORTS_DETAILS)
    Call<MainResponseSportsDetails> getSportsDetailsResponse(
            @Query(Constant.ApiKey.SPORTS_ID) int userId
    );

    @POST(Constant.EndPoint.BIRTHDAY_LIST)
    Call<MainResponseBirthday> getBirthdayListResponse();

    @POST(Constant.EndPoint.BIRTHDAY_DETAILS)
    Call<MainResponseBirthdayDetails> getBirthdayDetailsResponse(
            @Query(Constant.ApiKey.BIRTHDAY_ID) int userId
    );

    @POST(Constant.EndPoint.DONER_LIST)
    Call<MainResponseDoner> getDonerListResponse();

    @POST(Constant.EndPoint.DONER_DETAILS)
    Call<MainResponseDonerDetails> getDonerDetailsResponse(
            @Query(Constant.ApiKey.DONER_ID) int userId
    );

    @POST(Constant.EndPoint.CONTACT_LIST)
    Call<MainResponseContact> getContactListResponse();

    @POST(Constant.EndPoint.CONTACT_DETAILS)
    Call<MainResponseContactDetails> getContactDetailsResponse(
            @Query(Constant.ApiKey.CONTACT_ID) int userId
    );

    @POST(Constant.EndPoint.SURNAME_LIST)
    Call<MainResponseSurname> getSurnameListResponse();

    @Multipart
    @POST(Constant.EndPoint.SURNAME_CREATE)
    Call<BaseResponse> getCreateSurnameResponse(
            @Part(Constant.ApiKey.SURNAME) RequestBody rb_surname
    );

    @POST(Constant.EndPoint.PHOTO_LIST)
    Call<MainResponsePhoto> getPhotoListResponse();

    @POST(Constant.EndPoint.PHOTO_VIEW)
    Call<MainResponsePhotoDetails> getPhotoDetailsResponse(
            @Query(Constant.ApiKey.PHOTO_ID) int userId
    );

    @POST(Constant.EndPoint.VIDEO_LIST)
    Call<MainResponseVideo> getVideoListResponse();

    @POST(Constant.EndPoint.VIDEO_DETAILS)
    Call<MainResponseVideoDetails> getVideoDetailsResponse(
            @Query(Constant.ApiKey.VIDEO_ID) int userId
    );


    @Multipart
    @POST(Constant.EndPoint.EVENT_CREATE)
    Call<BaseResponse> getCreateEventResponse(
            @Part(Constant.ApiKey.HEADLINE) RequestBody rb_headLine,
            @Part(Constant.ApiKey.TITLE) RequestBody rb_title,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_category,
            @Part(Constant.ApiKey.DETAILS) RequestBody rb_details,
            @Part(Constant.ApiKey.REFERENCE) RequestBody rb_reference,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.LATE_CREATE)
    Call<BaseResponse> getCreateLateResponse(
            @Part(Constant.ApiKey.FIRST_NAME) RequestBody rb_firstname,
            @Part(Constant.ApiKey.MIDDLE_NAME) RequestBody rb_middlename,
            @Part(Constant.ApiKey.LAST_NAME) RequestBody rb_lastname,
            @Part(Constant.ApiKey.LATE_DATE) RequestBody rb_latedate,
            @Part(Constant.ApiKey.BIRTH_DATE) RequestBody rb_birthdate,
            @Part(Constant.ApiKey.GUJARATI_SAVANT) RequestBody rb_gujaratisavant,
            @Part(Constant.ApiKey.ADDRESS) RequestBody rb_address,
            @Part(Constant.ApiKey.SHRADHHANJALI) RequestBody rb_shradhhanjali,
            @Part(Constant.ApiKey.NOTIFICATION) RequestBody rb_notifications,
            @Part(Constant.ApiKey.CONTACT) RequestBody rb_contact,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.PLACEMENT_CREATE)
    Call<BaseResponse> getCreatePlacementResponse(
            @Part(Constant.ApiKey.HEADLINE) RequestBody rb_headline,
            @Part(Constant.ApiKey.TITLE) RequestBody rb_title,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_category,
            @Part(Constant.ApiKey.DESCRIPTION) RequestBody rb_description,
            @Part(Constant.ApiKey.SKILLS) RequestBody rb_skills,
            @Part(Constant.ApiKey.EDUCATIONAL_QUALIFICATION) RequestBody rb_educationqualification,
            @Part(Constant.ApiKey.REFERENCE_URL) RequestBody rb_referenceurl,
            @Part(Constant.ApiKey.REPORTED_DATETIME) RequestBody rb_reporteddatetime,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.EDUCATION_CREATE)
    Call<BaseResponse> getCreateEducationResponse(
            @Part(Constant.ApiKey.NAME) RequestBody rb_name,
            @Part(Constant.ApiKey.GENDER) RequestBody rb_gender,
            @Part(Constant.ApiKey.QUALIFICATION) RequestBody rb_qualification,
            @Part(Constant.ApiKey.NOTE) RequestBody rb_note,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.NEWS_CREATE)
    Call<BaseResponse> getCreateNewsResponse(
            @Part(Constant.ApiKey.HEADLINE) RequestBody rb_news_headLine,
            @Part(Constant.ApiKey.TITLE) RequestBody rb_news_title,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb__news_category,
            @Part(Constant.ApiKey.DETAILS) RequestBody rb__news_details,
            @Part(Constant.ApiKey.REFERENCE) RequestBody rb_news_reference,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.BIRTHDAY_CREATE)
    Call<BaseResponse> getCreateBirthdayResponse(
            @Part(Constant.ApiKey.NAME) RequestBody rb_name,
            @Part(Constant.ApiKey.BIRTHDATE) RequestBody rb_birthdate,
            @Part(Constant.ApiKey.TIME) RequestBody rb_time,
            @Part(Constant.ApiKey.PLACE) RequestBody rb_place,
            @Part(Constant.ApiKey.WISHES) RequestBody rb_wishes
    );

    @Multipart
    @POST(Constant.EndPoint.BUSNIESS_CREATE)
    Call<BaseResponse> getCreateBusniessResponse(
            @Part(Constant.ApiKey.FIRST_NAME) RequestBody rb_firstname,
            @Part(Constant.ApiKey.MIDDLE_NAME) RequestBody rb_middlename,
            @Part(Constant.ApiKey.LAST_NAME) RequestBody rb_lastname,
            @Part(Constant.ApiKey.COMPANY) RequestBody rb_company,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_category,
            @Part(Constant.ApiKey.DESCRIPTION) RequestBody rb_description,
            @Part(Constant.ApiKey.CONTACT) RequestBody rb_contact,
            @Part(Constant.ApiKey.EMAIL) RequestBody rb_email,
            @Part(Constant.ApiKey.ADDRESS) RequestBody rb_address,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.EMPLOYEE_CREATE)
    Call<BaseResponse> getCreateEmployeeResponse(
            @Part(Constant.ApiKey.FIRST_NAME) RequestBody rb_firstname,
            @Part(Constant.ApiKey.MIDDLE_NAME) RequestBody rb_middlename,
            @Part(Constant.ApiKey.LAST_NAME) RequestBody rb_lastname,
            @Part(Constant.ApiKey.OFFICE) RequestBody rb_office,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_category,
            @Part(Constant.ApiKey.DESIGNATION) RequestBody rb_designation,
            @Part(Constant.ApiKey.CONTACT) RequestBody rb_contact,
            @Part(Constant.ApiKey.EMAIL) RequestBody rb_email,
            @Part(Constant.ApiKey.ADDRESS) RequestBody rb_address,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.CANDIDATE_CREATE)
    Call<BaseResponse> getCreateCandidateResponse(
            @Part(Constant.ApiKey.FIRST_NAME) RequestBody rb_firstname,
            @Part(Constant.ApiKey.MIDDLE_NAME) RequestBody rb_middlename,
            @Part(Constant.ApiKey.LAST_NAME) RequestBody rb_lastname,
            @Part(Constant.ApiKey.BIRTH_DATE) RequestBody rb_birthdate,
            @Part(Constant.ApiKey.BIRTH_PLACE) RequestBody rb_birthplace,
            @Part(Constant.ApiKey.HEIGHT) RequestBody rb_height,
            @Part(Constant.ApiKey.WEIGHT) RequestBody rb_weight,
            @Part(Constant.ApiKey.EDUCATION) RequestBody rb_education,
            @Part(Constant.ApiKey.OCCUPATION) RequestBody rb_occupation,
            @Part(Constant.ApiKey.FATHER_NAME) RequestBody rb_fathername,
            @Part(Constant.ApiKey.MOTHER_NAME) RequestBody rb_mothername,
            @Part(Constant.ApiKey.BROTHERS) RequestBody rb_brothers,
            @Part(Constant.ApiKey.SISTERS) RequestBody rb_sisters,
            @Part(Constant.ApiKey.FATHER_OCCUPATION) RequestBody rb_fatheroccupation,
            @Part(Constant.ApiKey.MOTHER_OCCUPATION) RequestBody rb_motheroccupation,
            @Part(Constant.ApiKey.FATHER_CONTACT) RequestBody rb_fathercontact,
            @Part(Constant.ApiKey.CONTACT) RequestBody rb_contact,
            @Part(Constant.ApiKey.EMAIL) RequestBody rb_email,
            @Part(Constant.ApiKey.RESIDENT_ADDRESS) RequestBody rb_residentaddress,
            @Part(Constant.ApiKey.NATIVE_ADDRESS) RequestBody rb_nativeaddress,
            @Part(Constant.ApiKey.MATERNAL) RequestBody rb_maternal,
            @Part(Constant.ApiKey.MATERNAL_PLACE) RequestBody rb_maternalplace,
            @Part(Constant.ApiKey.HOBBIES) RequestBody rb_hobbies,
            @Part(Constant.ApiKey.EXPECTATIONS) RequestBody rb_expectations,
            @Part(Constant.ApiKey.REMARK) RequestBody rb_remark,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.CONTACT_CREATE)
    Call<BaseResponse> getCreateContactResponse(
            @Part(Constant.ApiKey.NAME) RequestBody rb_name,
            @Part(Constant.ApiKey.DESIGNATION) RequestBody rb_designation,
            @Part(Constant.ApiKey.MOBILE) RequestBody rb_mobile,
            @Part(Constant.ApiKey.EMAIL) RequestBody rb_email,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.SPORTS_CREATE)
    Call<BaseResponse> getCreateSportsResponse(
            @Part(Constant.ApiKey.HEADLINE) RequestBody rb_sports_headLine,
            @Part(Constant.ApiKey.TITLE) RequestBody rb_sports_title,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_sports_category,
            @Part(Constant.ApiKey.DETAILS) RequestBody rb_sports_details,
            @Part(Constant.ApiKey.REFERENCE) RequestBody rb_sports_reference,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.DONOR_CREATE)
    Call<BaseResponse> getCreateDonorResponse(
            @Part(Constant.ApiKey.TITLE) RequestBody rb_donor_title,
            @Part(Constant.ApiKey.DESCRIPTION) RequestBody rb_donor_description,
            @Part(Constant.ApiKey.TYPE) RequestBody rb_donor_type

    );

    @Multipart
    @POST(Constant.EndPoint.PHOTO_CREATE)
    Call<BaseResponse> getCreatePhotoResponse(
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_photo_category,
            @Part(Constant.ApiKey.EVENT_TITLE) RequestBody rb_photo_eventtitle,
            @Part(Constant.ApiKey.LOCATION) RequestBody rb_photo_location,
            @Part(Constant.ApiKey.DESCRIPTION) RequestBody rb_photo_description,
            @Part(Constant.ApiKey.DATE) RequestBody rb_photo_date,
            @Part MultipartBody.Part thumbImage
    );

    @Multipart
    @POST(Constant.EndPoint.VIDEO_CREATE)
    Call<BaseResponse> getCreateVideoResponse(
            @Part(Constant.ApiKey.EVENT_TITLE) RequestBody rb_event_title,
            @Part(Constant.ApiKey.DESCRIPTION) RequestBody rb_description,
            @Part(Constant.ApiKey.LOCATION) RequestBody rb_location,
            @Part(Constant.ApiKey.DATE) RequestBody rb_date,
            @Part(Constant.ApiKey.PATH) RequestBody rb_path,
            @Part(Constant.ApiKey.CATEGORY) RequestBody rb_category

    );
}
