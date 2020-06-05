package com.designer.checkapp.helper;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String imageUrl;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
