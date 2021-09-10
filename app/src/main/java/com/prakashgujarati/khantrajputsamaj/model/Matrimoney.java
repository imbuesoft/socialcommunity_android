package com.prakashgujarati.khantrajputsamaj.model;

public class Matrimoney {
    String image;
    String fname;
    String mname;
    String lname;
    String city;
    String height;
    String dob;
    String weight;
    String qualification;
    String maritstatus;
    String address;
    String contact;

    public Matrimoney(String image, String fname, String mname, String lname, String city, String height, String dob, String weight, String qualification, String maritstatus, String address, String contact) {
        this.image = image;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.city = city;
        this.height = height;
        this.dob = dob;
        this.weight = weight;
        this.qualification = qualification;
        this.maritstatus = maritstatus;
        this.address = address;
        this.contact = contact;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMaritstatus() {
        return maritstatus;
    }

    public void setMaritstatus(String maritstatus) {
        this.maritstatus = maritstatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
