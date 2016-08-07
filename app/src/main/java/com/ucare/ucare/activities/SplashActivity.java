package com.ucare.ucare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ucare.ucare.R;
import com.ucare.ucare.UCareApplication;
import com.ucare.ucare.activities.main.MainActivity;
import com.ucare.ucare.models.Organization;
import com.ucare.ucare.models.Purpose;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                proceed();
            }
        }, 1200);


        Call<List<Purpose>> purposeCall = UCareApplication.mNetworkService.getPurposes();
        purposeCall.enqueue(new Callback<List<Purpose>>() {
            @Override
            public void onResponse(Call<List<Purpose>> call,
                                   final Response<List<Purpose>> purposes) {
                Call<List<Organization>> organizationCall = UCareApplication.mNetworkService.getOrganizations();
                organizationCall.enqueue(new Callback<List<Organization>>() {
                        @Override
                        public void onResponse(Call<List<Organization>> call,
                                               Response<List<Organization>> organizations) {
                            purposes.body();
                            organizations.body();
                        }
                        @Override
                        public void onFailure(Call<List<Organization>> call, Throwable t) {
                        }
                    });
                }
            @Override
            public void onFailure(Call<List<Purpose>> call, Throwable t) {
            }
        });

    }

    public void proceed(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}
