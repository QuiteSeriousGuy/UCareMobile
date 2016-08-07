package com.ucare.ucare.activities.main.adapter;


import android.content.Context;
import android.content.Intent;
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
import com.ucare.ucare.activities.main.MainActivity;
import com.ucare.ucare.activities.purpose.PurposeDetailActivity;
import com.ucare.ucare.models.Purpose;
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
public class PurposeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener  {

    private Context context;
    private ArrayList<Purpose> purposeList;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public PurposeAdapter(Context context) {

        this.context = context;
        purposeList = new ArrayList<>();

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
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        imageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context).imageDownloader(new ImageDownloader(context)).build());
    }

    public void addItems(List newItems){

        purposeList.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed_purpose, parent, false);
        final PurposeViewHolder feedViewHolder = new PurposeViewHolder(view);

        return feedViewHolder;
    }

    @Override
    public void onClick(View view) {

        final int nPos = (int)view.getTag();
        final Purpose purpose = purposeList.get(nPos);

        Intent i = new Intent(context, PurposeDetailActivity.class);
        i.putExtra("purpose", purpose);
        ((MainActivity)context).startActivityForResult(i, 909);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PurposeViewHolder holder = (PurposeViewHolder) viewHolder;
        Purpose purpose = purposeList.get(position);

        holder.tvPurposeName.setText(purpose.name);
        holder.tvPurposeDate.setText(purpose.date);
        imageLoader.displayImage(purpose.image,holder.ivPurpose, options);

        holder.tvPurposeName.setOnClickListener(this);
        holder.tvPurposeDate.setOnClickListener(this);
        holder.ivPurpose.setOnClickListener(this);

        holder.tvPurposeName.setTag(position);
        holder.tvPurposeDate.setTag(position);
        holder.ivPurpose.setTag(position);

    }

    @Override
    public int getItemCount() {
        return purposeList.size();
    }

    public static class PurposeViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_purpose)
        ImageView ivPurpose;
        @InjectView(R.id.tv_purpose_date)
        TextView tvPurposeDate;
        @InjectView(R.id.tv_purpose_name)
        TextView tvPurposeName;

        public PurposeViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
