package com.example.adindariztiaputri.twitternew.remote;

import com.example.adindariztiaputri.twitternew.model.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("signin")
    Call<Token> signIn(@Field("email") String email,
                       @Field("password") String password);
}
