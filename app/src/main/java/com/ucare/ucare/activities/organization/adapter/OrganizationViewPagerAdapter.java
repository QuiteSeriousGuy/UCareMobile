package com.ucare.ucare.activities.organization.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucare.ucare.R;
import com.ucare.ucare.activities.organization.fragments.OrganizationDetailFragment;
import com.ucare.ucare.activities.organization.fragments.OrganizationPurposesFragment;
import com.ucare.ucare.customviews.PagerSlidingTabStrip;


public class OrganizationViewPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

    final int PAGE_COUNT = 2;
    private Context mContext;
    private ViewGroup tab;

    public OrganizationViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    private OrganizationDetailFragment detailFragment;
    private OrganizationPurposesFragment purposesFragment;

    public void setTabs(OrganizationDetailFragment detailFragment, OrganizationPurposesFragment purposesFragment){
        this.detailFragment = detailFragment;
        this.purposesFragment = purposesFragment;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return detailFragment;
            case 1:
                return purposesFragment;
        }
        return null;
    }

    private final String[] TEXT = {"Detail", "Purposes"};

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {
        tab = parent;
        View text = LayoutInflater.from(mContext).inflate(R.layout.text, tab, false);
       ((TextView) text.findViewById(R.id.text)).setText(TEXT[position]);
        return text;
    }


}