package com.ucare.ucare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ucare.ucare.R;
import com.ucare.ucare.UCareApplication;
import com.ucare.ucare.activities.main.MainActivity;

public class ProfileActivity extends AppCompatActivity {

    private Button btnLogout;
    private TextView tvUsername;
    private TextView tvName;
    private TextView tvBalance;
    private TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvBalance = (TextView) findViewById(R.id.tv_current_balance);
        tvPoints = (TextView) findViewById(R.id.tv_points);

        tvUsername.setText("Username: " + "juan");
        tvName.setText("Name: " + "Juan De La Cruz");
        tvBalance.setText("Current Balance: ₱" + "10000");
        tvPoints.setText("Points: " + "0");


        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UCareApplication.TDB.putString("userauth", "");
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
