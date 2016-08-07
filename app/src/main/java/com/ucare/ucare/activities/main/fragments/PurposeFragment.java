package com.ucare.ucare.activities.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ucare.ucare.R;
import com.ucare.ucare.activities.main.adapter.PurposeAdapter;
import com.ucare.ucare.models.Purpose;

import java.util.ArrayList;

/**
 * Created by Knight on 06/08/2016.
 */
public class PurposeFragment extends Fragment {

    private RecyclerView rvFeed;
    private ProgressBar pbLoading;
    private PurposeAdapter purposeAdapter;

    private LinearLayoutManager linearLayoutManager;

    public PurposeFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_purposes, container, false);

        linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };

        rvFeed = (RecyclerView) rootView.findViewById(R.id.rvFeed);

        pbLoading = (ProgressBar) rootView.findViewById(R.id.empty);

        rvFeed.setLayoutManager(linearLayoutManager);

        setupFeed();

        return rootView;
    }


    private void setupFeed() {

        rvFeed.setVisibility(RecyclerView.GONE);
        pbLoading.setVisibility(ProgressBar.VISIBLE);

        purposeAdapter = new PurposeAdapter(getActivity());
        rvFeed.setAdapter(purposeAdapter);

        ArrayList<Purpose> purposes = new ArrayList<>();
        purposes.add(new Purpose("Yolanda", "http://opinion.inquirer.net/files/2015/11/yolanda.jpg", "November 19, 2015"));
        purposes.add(new Purpose("Sirao Fire", "http://images.indianexpress.com/2016/04/uttarakhand-fire759.jpg", "July 6, 2015"));
        purposes.add(new Purpose("Earthquake", "http://i2.cdn.turner.com/cnnnext/dam/assets/160104101442-india-imphal-earthquake-large-169.jpg", "June 17, 2015"));
        purposeAdapter.addItems(purposes);

        rvFeed.setVisibility(RecyclerView.VISIBLE);
        pbLoading.setVisibility(ProgressBar.GONE);
    }
}
