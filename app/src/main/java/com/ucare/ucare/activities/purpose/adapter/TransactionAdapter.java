package com.ucare.ucare.activities.purpose.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.ucare.ucare.R;
import com.ucare.ucare.models.Transaction;
import com.ucare.ucare.utils.ImageDownloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Knight on 06/08/2016.
 */
public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener  {

    private Context context;
    private ArrayList<Transaction> transactionList;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public TransactionAdapter(Context context) {

        this.context = context;
        transactionList = new ArrayList<>();

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent","");

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ucare)
                .showImageForEmptyUri(R.drawable.ucare)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .extraForDownloader(headers)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();

        imageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context).imageDownloader(new ImageDownloader(context)).build());
    }

    public void addItems(List newItems){
        transactionList.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed_transaction, parent, false);
        final TransactionViewHolder feedViewHolder = new TransactionViewHolder(view);

        return feedViewHolder;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TransactionViewHolder holder = (TransactionViewHolder) viewHolder;
        Transaction transaction = transactionList.get(position);

        holder.tvTransactionDate.setText(transaction.date);

        if(transaction.isAnon){
            holder.tvTransactionName.setText("Anonymous, ");
        } else {
            holder.tvTransactionName.setText(transaction.donorName);
        }
        holder.tvTransactionAmount.setText("₱ " + transaction.amount);

        if(!transaction.message.isEmpty()) {
            holder.tvTransactionMessage.setText("\" " + transaction.message + " \"");
        } else {
            holder.tvTransactionMessage.setVisibility(ImageView.GONE);
        }

        holder.tvTransactionDate.setOnClickListener(this);
        holder.tvTransactionMessage.setOnClickListener(this);
        holder.tvTransactionName.setOnClickListener(this);
        holder.tvTransactionAmount.setOnClickListener(this);

        holder.tvTransactionDate.setTag(position);
        holder.tvTransactionMessage.setTag(position);
        holder.tvTransactionName.setTag(position);
        holder.tvTransactionAmount.setTag(position);

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_transcation_date)
        TextView tvTransactionDate;
        @InjectView(R.id.tv_transcation_name)
        TextView tvTransactionName;
        @InjectView(R.id.tv_transcation_amount)
        TextView tvTransactionAmount;
        @InjectView(R.id.tv_transcation_message)
        TextView tvTransactionMessage;

        public TransactionViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
