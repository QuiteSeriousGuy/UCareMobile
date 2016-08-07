package com.ucare.ucare.activities.purpose.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ucare.ucare.R;
import com.ucare.ucare.activities.purpose.adapter.UpdateAdapter;
import com.ucare.ucare.models.Update;

import java.util.ArrayList;

/**
 * Created by Knight on 06/08/2016.
 */
public class PurposeUpdateFragment extends Fragment {

    private RecyclerView rvFeed;
    private ProgressBar pbLoading;
    private UpdateAdapter updateAdapter;

    private LinearLayoutManager linearLayoutManager;

    public PurposeUpdateFragment() {
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

        updateAdapter = new UpdateAdapter(getActivity());
        rvFeed.setAdapter(updateAdapter);

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

        ArrayList<Update> updates = new ArrayList<>();
        updates.add(new Update("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id tincidunt risus, auctor dictum sapien. Suspendisse ut sem porttitor, tempor nunc tincidunt, imperdiet massa.", "http://opinion.inquirer.net/files/2015/11/yolanda.jpg", "November 19, 2015"));
        updates.add(new Update("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id tincidunt risus, auctor dictum sapien. Suspendisse ut sem porttitor, tempor nunc tincidunt, imperdiet massa.", "", "November 15, 2015"));
        updates.add(new Update("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id tincidunt risus, auctor dictum sapien. Suspendisse ut sem porttitor, tempor nunc tincidunt, imperdiet massa.", "http://opinion.inquirer.net/files/2015/11/yolanda.jpg", "November 10, 2015"));
        updates.add(new Update("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id tincidunt risus, auctor dictum sapien. Suspendisse ut sem porttitor, tempor nunc tincidunt, imperdiet massa.", "http://opinion.inquirer.net/files/2015/11/yolanda.jpg", "November 10, 2015"));
        updates.add(new Update("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id tincidunt risus, auctor dictum sapien. Suspendisse ut sem porttitor, tempor nunc tincidunt, imperdiet massa.", "", "November 15, 2015"));
        updateAdapter.addItems(updates);

        rvFeed.setVisibility(RecyclerView.VISIBLE);
        pbLoading.setVisibility(ProgressBar.GONE);
    }
}
