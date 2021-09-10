package com.prakashgujarati.khantrajputsamaj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Late{
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("middle_name")
    @Expose
    private String middleName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("late_date")
    @Expose
    private String lateDate;

    @SerializedName("birth_date")
    @Expose
    private String birthDate;

    @SerializedName("gujarati_savant")
    @Expose
    private String gujaratiSavant;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("shradhhanjali")
    @Expose
    private String shradhhanjali;

    @SerializedName("notifications")
    @Expose
    private String notifications;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("done_by")
    @Expose
    private String doneBy;


    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Late(Integer id, String firstName, String middleName, String lastName, String lateDate, String birthDate, String gujaratiSavant, String address, String shradhhanjali, String notifications, String contact, String picture, String status, String createdAt, String doneBy, String updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.lateDate = lateDate;
        this.birthDate = birthDate;
        this.gujaratiSavant = gujaratiSavant;
        this.address = address;
        this.shradhhanjali = shradhhanjali;
        this.notifications = notifications;
        this.contact = contact;
        this.picture = picture;
        this.status = status;
        this.createdAt = createdAt;
        this.doneBy = doneBy;
        this.updatedAt = updatedAt;
    }

    public Late() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLateDate() {
        return lateDate;
    }

    public void setLateDate(String lateDate) {
        this.lateDate = lateDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGujaratiSavant() {
        return gujaratiSavant;
    }

    public void setGujaratiSavant(String gujaratiSavant) {
        this.gujaratiSavant = gujaratiSavant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShradhhanjali() {
        return shradhhanjali;
    }

    public void setShradhhanjali(String shradhhanjali) {
        this.shradhhanjali = shradhhanjali;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
}
