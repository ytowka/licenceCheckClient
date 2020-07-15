package com.ytowka;

import com.ytowka.responses.LicenceCheckResponse;
import com.ytowka.responses.LicenceRegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LicenceChecker {
    //public static String baseUrl = "http://localhost:8080";
    public static String baseUrl = "https://ytowkas-test-app0.herokuapp.com/";
    Config rc;
    LicenceRegisterResponse regResponse;
    LicenceCheckResponse checkResponse;

    public LicenceChecker(String key){
        //rc = new Config(key,Signature.MotherBoardSignature());
        rc = new Config(key,Signature.MotherBoardSignature());
    }
    public boolean registerLicence(){
        if(registerLicenceStatus() == 0){
            return true;
        }
        return false;
    }
    public void onRegisterComplete(int status){

    }
    public void onCheckComplete(int status){
    }
    public int registerLicenceStatus(){
        regResponse = new LicenceRegisterResponse();
        regResponse.status = -1;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<LicenceRegisterResponse> call = service.register(rc);
        call.enqueue(new Callback<LicenceRegisterResponse>() {
            @Override
            public void onResponse(Call<LicenceRegisterResponse> call, Response<LicenceRegisterResponse> response) {
                System.out.println(response.body().status +": success");
                regResponse = response.body();
                onRegisterComplete(response.body().status);
            }
            @Override
            public void onFailure(Call<LicenceRegisterResponse> call, Throwable t) {
                System.out.println("fail");
            }
        });
        return regResponse.status;
    }
    // 0 - license valid
    // 1 - licence invalid
    // 2 - licence already used
    //-1 - failed to check
    public int getLicenseStatus(){
        checkResponse = new LicenceCheckResponse();
        checkResponse.status = -1;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<LicenceCheckResponse> call = service.checkLicence(rc);
        call.enqueue(new Callback<LicenceCheckResponse>() {
            @Override
            public void onResponse(Call<LicenceCheckResponse> call, Response<LicenceCheckResponse> response) {
                System.out.println(response.body().status +": success");
                checkResponse = response.body();
                onCheckComplete(response.body().status);
            }
            @Override
            public void onFailure(Call<LicenceCheckResponse> call, Throwable t) {
                System.out.println("fail");
            }
        });
        return checkResponse.status;
    }
    public boolean getLicence(){
        if(getLicenseStatus() == 0){
            return true;
        }else{
            return false;
        }
    }
}
