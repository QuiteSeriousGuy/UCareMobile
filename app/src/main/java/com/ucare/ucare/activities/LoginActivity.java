package com.ucare.ucare.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ucare.ucare.R;
import com.ucare.ucare.UCareApplication;
import com.ucare.ucare.activities.main.MainActivity;
import com.ucare.ucare.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        etUsername.setText("umbay.cm@gmail.com");
        etPassword.setText("abcd1234");
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                        "Logging in. Please wait...", true);
                Call<LoginResponse> call = UCareApplication.mNetworkService.login(etUsername.getText().toString(),
                        etPassword.getText().toString());

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call,
                                           Response<LoginResponse> response) {


                        if(response.isSuccessful()) {
                            if(Boolean.valueOf(response.body().successful)) {
                                UCareApplication.TDB.putString("userauth", response.body().authCode);
                                if(getIntent().getBooleanExtra("isfast", false)) {
                                    finish();
                                } else {
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid Username or Password 1", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Username or Password 2", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password 3", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
