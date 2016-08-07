package com.ucare.ucare.activities.organization.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ucare.ucare.R;
import com.ucare.ucare.activities.organization.adapter.OrganizationPurposesAdapter;
import com.ucare.ucare.models.Purpose;

import java.util.ArrayList;

/**
 * Created by Knight on 06/08/2016.
 */
public class OrganizationPurposesFragment extends Fragment {

    private RecyclerView rvFeed;
    private ProgressBar pbLoading;
    private OrganizationPurposesAdapter purposesAdapter;

    private LinearLayoutManager linearLayoutManager;

    public OrganizationPurposesFragment() {
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

        purposesAdapter = new OrganizationPurposesAdapter(getActivity());
        rvFeed.setAdapter(purposesAdapter);

//        Call<List<Purpose>> call = UCareApplication.mNetworkService.getPurposes("");
//
//        call.enqueue(new Callback<List<Purpose>>() {
//            @Override
//            public void onResponse(Call<List<Purpose>> call,
//                                   Response<List<Purpose>> response) {
//
//                purposeAdapter.addItems(response.body());
//                purposeAdapter.notifyDataSetChanged();
//                rvFeed.getAdapter().notifyDataSetChanged();
//
//                rvFeed.setVisibility(RecyclerView.VISIBLE);
//                pbLoading.setVisibility(ProgressBar.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<List<Purpose>> call, Throwable t) {
//            }
//        });

        ArrayList<Purpose> purposes = new ArrayList<>();
        purposes.add(new Purpose("Storm", "http://opinion.inquirer.net/files/2015/11/yolanda.jpg", "November 19, 2015"));
        purposes.add(new Purpose("Fire", "http://images.indianexpress.com/2016/04/uttarakhand-fire759.jpg", "July 6, 2015"));
        purposesAdapter.addItems(purposes);

        rvFeed.setVisibility(RecyclerView.VISIBLE);
        pbLoading.setVisibility(ProgressBar.GONE);
    }
}
