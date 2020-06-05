package com.designer.checkapp.helper;

import com.google.gson.annotations.SerializedName;

public class PostResponse{
    @SerializedName("statusCode")
    private String statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("errorMessage")
    private String errorMessage;


    public void setStatusCode(String title){
        this.statusCode = statusCode;
    }
    public String getStatusCode(){
        return statusCode;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String setMessage(){
        return message;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

}