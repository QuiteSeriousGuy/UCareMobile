package com.ucare.ucare.activities.organization;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Button;

import com.ucare.ucare.R;
import com.ucare.ucare.activities.organization.adapter.OrganizationViewPagerAdapter;
import com.ucare.ucare.activities.organization.fragments.OrganizationDetailFragment;
import com.ucare.ucare.activities.organization.fragments.OrganizationPurposesFragment;
import com.ucare.ucare.customviews.PagerSlidingTabStrip;
import com.ucare.ucare.models.Organization;

public class OrganizationDetailActivity extends AppCompatActivity {

    public static ViewPager mPager;
    public PagerSlidingTabStrip mTabs;
    public OrganizationViewPagerAdapter mPagerAdapter;

    private OrganizationDetailFragment detailFragment;
    private OrganizationPurposesFragment purposesFragment;

    private Button btnDonate;
    private Organization organization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        organization = (Organization) getIntent().getSerializableExtra("organization");

        detailFragment = new OrganizationDetailFragment();
        detailFragment.addOrganization(organization);
        purposesFragment = new OrganizationPurposesFragment();

        btnDonate = (Button) findViewById(R.id.btn_donate);
        btnDonate.setVisibility(Button.GONE);
//        btnDonate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DonationDialog dialog = new DonationDialog();
//                dialog.addPurpose(purpose);
//                dialog.show(getSupportFragmentManager(), "");
//            }
//        });

        mPagerAdapter = new OrganizationViewPagerAdapter(this, this.getSupportFragmentManager());
        mPagerAdapter.setTabs(detailFragment, purposesFragment);

        setupTabAndViewPager();


        Organization organization = (Organization) getIntent().getSerializableExtra("organization");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(organization.name);
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
