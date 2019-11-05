package com.test.network.service;



import com.test.network.request.LoginRequest;
import com.test.network.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface UserService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/login")
    Observable<LoginResponse> login(@Body LoginRequest json);


}
