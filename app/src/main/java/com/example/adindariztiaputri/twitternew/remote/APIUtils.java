package com.example.adindariztiaputri.twitternew.remote;

public class APIUtils {
    private APIUtils(){}

    public static final String BASE_URL = "http://tweet.makers.mindwork.space/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
