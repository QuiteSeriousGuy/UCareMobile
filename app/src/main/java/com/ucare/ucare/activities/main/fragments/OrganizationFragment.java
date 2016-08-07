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
import com.ucare.ucare.activities.main.adapter.OrganizationAdapter;
import com.ucare.ucare.models.Organization;

import java.util.ArrayList;

/**
 * Created by Knight on 06/08/2016.
 */
public class OrganizationFragment extends Fragment {

    private RecyclerView rvFeed;
    private ProgressBar pbLoading;
    private OrganizationAdapter organizationAdapter;

    private LinearLayoutManager linearLayoutManager;

    public OrganizationFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_organizations, container, false);

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

        organizationAdapter = new OrganizationAdapter(getActivity());
        rvFeed.setAdapter(organizationAdapter);

        ArrayList<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization("DSWD", "http://isacenter.org/wp-content/uploads/2015/12/dswd-logo-300.jpg", "Founded November 19, 1999"));
        organizations.add(new Organization("Red Cross", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Logo_Philippine_Red_Cross.svg/2000px-Logo_Philippine_Red_Cross.svg.png", "Founded July 6, 1999"));
        organizationAdapter.addItems(organizations);

        rvFeed.setVisibility(RecyclerView.VISIBLE);
        pbLoading.setVisibility(ProgressBar.GONE);
    }
}
