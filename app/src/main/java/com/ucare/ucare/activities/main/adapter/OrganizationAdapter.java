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
import com.ucare.ucare.activities.organization.OrganizationDetailActivity;
import com.ucare.ucare.models.Organization;
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
public class OrganizationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private Context context;
    private ArrayList<Organization> organizationList;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public OrganizationAdapter(Context context) {

        this.context = context;
        organizationList = new ArrayList<>();

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

        organizationList.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

        final int nPos = (int)view.getTag();
        final Organization organization = organizationList.get(nPos);

        Intent i = new Intent(context, OrganizationDetailActivity.class);
        i.putExtra("organization", organization);
        context.startActivity(i);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed_organization, parent, false);
        final PurposeViewHolder feedViewHolder = new PurposeViewHolder(view);

        return feedViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PurposeViewHolder holder = (PurposeViewHolder) viewHolder;
        Organization organization = organizationList.get(position);

        holder.tvOrganizationName.setText(organization.name);
        holder.tvOrganizationDate.setText(organization.date);
        imageLoader.displayImage(organization.image,holder.ivOrganization, options);

        holder.tvOrganizationName.setOnClickListener(this);
        holder.tvOrganizationDate.setOnClickListener(this);
        holder.ivOrganization.setOnClickListener(this);

        holder.tvOrganizationName.setTag(position);
        holder.tvOrganizationDate.setTag(position);
        holder.ivOrganization.setTag(position);

    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    public static class PurposeViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_organization)
        ImageView ivOrganization;
        @InjectView(R.id.tv_organization_name)
        TextView tvOrganizationName;
        @InjectView(R.id.tv_organization_date)
        TextView tvOrganizationDate;

        public PurposeViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
