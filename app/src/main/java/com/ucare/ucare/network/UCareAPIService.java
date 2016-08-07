package com.ucare.ucare.network;

import com.ucare.ucare.models.LoginResponse;
import com.ucare.ucare.models.Organization;
import com.ucare.ucare.models.Purpose;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UCareAPIService {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("api/")
    Call<List<Purpose>> getPurposes();


    @GET("api/")
    Call<List<Organization>> getOrganizations();

}
