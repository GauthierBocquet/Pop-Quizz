package com.android.popquizz;

import com.android.popquizz.bean.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface API {

    String BASE_URL = "https://opentdb.com/";

    @GET
    Call<Results> getQuestions(@Header("Content-Type") String type, @Url String urlChoice);
}