package com.netlab.betterchegg.api;

import com.netlab.betterchegg.models.LoginResponse;
import com.netlab.betterchegg.models.User;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface API {
    @FormUrlEncoded
    @POST("/register")
    Call<ResponseBody> createUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @Multipart
    @POST("/upload")
    Call<ResponseBody> uploadPDF(
            @Part MultipartBody.Part pdfFilePart
    );

}
