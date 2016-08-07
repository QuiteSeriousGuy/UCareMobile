package com.ucare.ucare.activities.purpose.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucare.ucare.R;
import com.ucare.ucare.activities.purpose.fragments.PurposeDetailFragment;
import com.ucare.ucare.activities.purpose.fragments.PurposeTransactionFragment;
import com.ucare.ucare.activities.purpose.fragments.PurposeUpdateFragment;
import com.ucare.ucare.customviews.PagerSlidingTabStrip;


public class PurposeViewPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

    final int PAGE_COUNT = 3;
    private Context mContext;
    private ViewGroup tab;

    public PurposeViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    private PurposeDetailFragment detailFragment;
    private PurposeUpdateFragment updateFragment;
    private PurposeTransactionFragment transactionFragment;

    public void setTabs(PurposeDetailFragment detailFragment, PurposeUpdateFragment updateFragment,
                        PurposeTransactionFragment transactionFragment){
        this.detailFragment = detailFragment;
        this.updateFragment = updateFragment;
        this.transactionFragment = transactionFragment;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return detailFragment;
            case 1:
                return updateFragment;
            case 2:
                return transactionFragment;
        }
        return null;
    }

    private final String[] TEXT = {"Detail", "Updates", "Donations"};

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