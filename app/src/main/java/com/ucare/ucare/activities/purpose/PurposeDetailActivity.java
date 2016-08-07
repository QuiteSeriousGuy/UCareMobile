package com.ucare.ucare.activities.purpose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ucare.ucare.R;
import com.ucare.ucare.UCareApplication;
import com.ucare.ucare.activities.LoginActivity;
import com.ucare.ucare.activities.purpose.adapter.PurposeViewPagerAdapter;
import com.ucare.ucare.activities.purpose.dialog.DonationDialog;
import com.ucare.ucare.activities.purpose.fragments.PurposeDetailFragment;
import com.ucare.ucare.activities.purpose.fragments.PurposeTransactionFragment;
import com.ucare.ucare.activities.purpose.fragments.PurposeUpdateFragment;
import com.ucare.ucare.customviews.PagerSlidingTabStrip;
import com.ucare.ucare.models.Purpose;

import me.drakeet.materialdialog.MaterialDialog;

public class PurposeDetailActivity extends AppCompatActivity {

    public static ViewPager mPager;
    public PagerSlidingTabStrip mTabs;
    public PurposeViewPagerAdapter mPagerAdapter;

    private PurposeDetailFragment detailFragment;
    private PurposeUpdateFragment updateFragment;
    private PurposeTransactionFragment transactionFragment;

    private Button btnDonate;
    private Purpose purpose;

    private MaterialDialog donateConfirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        purpose = (Purpose) getIntent().getSerializableExtra("purpose");

        detailFragment = new PurposeDetailFragment();
        detailFragment.addPurpose(purpose);
        updateFragment = new PurposeUpdateFragment();
        transactionFragment = new PurposeTransactionFragment();

        btnDonate = (Button) findViewById(R.id.btn_donate);
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UCareApplication.TDB.getString("userauth").isEmpty()){

                    donateConfirmDialog = new MaterialDialog(PurposeDetailActivity.this);
                    donateConfirmDialog.setTitle("Login to donate.")
                            .setMessage("Redirect to login page?")
                            .setPositiveButton("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(PurposeDetailActivity.this, LoginActivity.class);
                                    i.putExtra("isfast", true);
                                    startActivityForResult(i, 909);
                                }
                            })
                            .setNegativeButton("CANCEL", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    donateConfirmDialog.dismiss();
                                }
                            });

                    donateConfirmDialog.show();


                } else {
                    DonationDialog dialog = new DonationDialog();
                    dialog.addPurpose(purpose);
                    dialog.show(getSupportFragmentManager(), "");
                }
            }
        });

        mPagerAdapter = new PurposeViewPagerAdapter(this, this.getSupportFragmentManager());//((FragmentActivity)getActivity()).getSupportFragmentManager());
        mPagerAdapter.setTabs(detailFragment, updateFragment, transactionFragment);

        setupTabAndViewPager();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(purpose.name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        donateConfirmDialog.dismiss();
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

    public void setupTabAndViewPager() {
        mPager = (ViewPager) findViewById(R.id.pager_dashboard);
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_dashboard);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);
        mPager.setOffscreenPageLimit(2);
        mTabs.setViewPager(mPager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        mPager.setPageMargin(pageMargin);
        mTabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
            }
        });
    }
}
