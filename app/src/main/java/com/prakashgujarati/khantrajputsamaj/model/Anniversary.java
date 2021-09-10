package com.prakashgujarati.khantrajputsamaj.model;

public class Anniversary {
    String image;
    String user_id;
    String name;
    String adate;
    String time;
    String place;
    String type;

    public Anniversary(String image, String user_id, String name, String adate, String time, String place, String type) {
        this.image = image;
        this.user_id = user_id;
        this.name = name;
        this.adate = adate;
        this.time = time;
        this.place = place;
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
