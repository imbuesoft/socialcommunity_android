package com.prakashgujarati.khantrajputsamaj.model;

public class Book {
    String image;
    String title;
    String subtitle;
    String description;

    public Book(String image, String title, String subtitle, String description) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
