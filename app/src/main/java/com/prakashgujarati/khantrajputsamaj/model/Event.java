package com.prakashgujarati.khantrajputsamaj.model;

import com.bumptech.glide.annotation.Excludes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {
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
    private String detailReport;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("news_image")
    @Expose
    private String newsImage;

    @SerializedName("reported_datetime")
    @Expose
    private String reportedDatetime;

    @SerializedName("reference")
    @Expose
    private String reference;

    @SerializedName("status")
    @Expose
    private String status;

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

    public String getDetailReport() {
        return detailReport;
    }

    public void setDetailReport(String detailReport) {
        this.detailReport = detailReport;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getReportedDatetime() {
        return reportedDatetime;
    }

    public void setReportedDatetime(String reportedDatetime) {
        this.reportedDatetime = reportedDatetime;
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
}
