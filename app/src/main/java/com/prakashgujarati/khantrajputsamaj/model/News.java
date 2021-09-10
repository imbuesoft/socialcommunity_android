package com.prakashgujarati.khantrajputsamaj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("headline")
    @Expose
    private String headline;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("detail_report")
    @Expose
    private String detail_Report;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("news_image")
    @Expose
    private String news_Image;

    @SerializedName("reported_datetime")
    @Expose
    private String reported_Datetime;

    @SerializedName("reference")
    @Expose
    private String reference;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("done_by")
    @Expose
    private Integer doneBy;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail_Report() {
        return detail_Report;
    }

    public void setDetail_Report(String detail_Report) {
        this.detail_Report = detail_Report;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNews_Image() {
        return news_Image;
    }

    public void setNews_Image(String news_Image) {
        this.news_Image = news_Image;
    }

    public String getReported_Datetime() {
        return reported_Datetime;
    }

    public void setReported_Datetime(String reported_Datetime) {
        this.reported_Datetime = reported_Datetime;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(Integer doneBy) {
        this.doneBy = doneBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
