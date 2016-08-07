package com.ucare.ucare.activities.main;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ucare.ucare.R;
import com.ucare.ucare.UCareApplication;
import com.ucare.ucare.activities.LoginActivity;
import com.ucare.ucare.activities.ProfileActivity;
import com.ucare.ucare.activities.main.fragments.OrganizationFragment;
import com.ucare.ucare.activities.main.fragments.PurposeFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private Toolbar toolbar;

    private TextView tvEmail;
    private TextView tvName;
    private View header;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavView.setItemIconTintList(null);

        header = mNavView.getHeaderView(0);


        tvName = (TextView) header.findViewById(R.id.tvName);
        tvEmail = (TextView) header.findViewById(R.id.tvEmail);

        if(UCareApplication.TDB.getString("userauth").isEmpty()) {
            tvName.setText("");
            tvEmail.setText("Please login first");
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            });
        } else {
            tvName.setText("Name");
            tvEmail.setText("Email");
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(i);
                }
            });
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> UCare - Purposes</font>"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);

                Fragment fragment;
                mDrawerLayout.closeDrawer(Gravity.START);
                switch(item.getItemId()) {
                    case R.id.nav_purpose:
                        fragment = new PurposeFragment();
                        mNavView.setCheckedItem(R.id.nav_purpose);
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> UCare - Purposes</font>"));
                        return true;
                    case R.id.nav_organization:
                        fragment = new OrganizationFragment();
                        mNavView.setCheckedItem(R.id.nav_purpose);
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> UCare - Organizations</font>"));
                        return true;
//                    case R.id.nav_update:
//                        fragment = new UpdateFragment();
//                        mNavView.setCheckedItem(R.id.nav_purpose);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
//                        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> UCare - Updates</font>"));
//                        return true;
                    default:
                        return true;
                }
            }
        });
        if (savedInstanceState == null) {
            initialize();
        }
    }

    private void initialize() {
        Fragment fragment = new PurposeFragment();
        mNavView.setCheckedItem(R.id.nav_purpose);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(UCareApplication.TDB.getString("userauth").isEmpty()) {
            tvName.setText("");
            tvEmail.setText("Please login first");
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            });
        } else {
            tvName.setText("Name");
            tvEmail.setText("Email");
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}
