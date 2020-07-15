package com.ytowka;

import com.ytowka.responses.LicenceCheckResponse;
import com.ytowka.responses.LicenceRegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {
    @POST("/register")
    Call<LicenceRegisterResponse> register(@Body Config rc);
    @POST("/checkLicence")
    Call<LicenceCheckResponse> checkLicence(@Body Config rc);
}
