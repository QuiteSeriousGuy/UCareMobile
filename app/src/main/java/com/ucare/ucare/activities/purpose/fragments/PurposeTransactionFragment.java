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
import com.ucare.ucare.activities.purpose.adapter.TransactionAdapter;
import com.ucare.ucare.models.Transaction;

import java.util.ArrayList;

/**
 * Created by Knight on 06/08/2016.
 */
public class PurposeTransactionFragment extends Fragment {

    private RecyclerView rvFeed;
    private ProgressBar pbLoading;
    private TransactionAdapter transactionAdapter;

    private LinearLayoutManager linearLayoutManager;

    public PurposeTransactionFragment() {
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

        transactionAdapter = new TransactionAdapter(getActivity());
        rvFeed.setAdapter(transactionAdapter);

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

        ArrayList<Transaction> updates = new ArrayList<>();
        updates.add(new Transaction("John", "November 19 2016", "10000", true, "My condolences"));
        updates.add(new Transaction("Mary", "November 18 2016", "5000", false, ""));
        updates.add(new Transaction("Steven", "November 18 2016", "9000", false, ""));
        updates.add(new Transaction("Carol", "November 19 2016", "9000", true, "My condolences"));
        transactionAdapter.addItems(updates);

        rvFeed.setVisibility(RecyclerView.VISIBLE);
        pbLoading.setVisibility(ProgressBar.GONE);
    }
}
