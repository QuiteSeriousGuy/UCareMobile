package com.ucare.ucare.activities.main.adapter;


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
import com.ucare.ucare.models.Update;
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
public class UpdateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener  {

    private Context context;
    private ArrayList<Update> updateList;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public UpdateAdapter(Context context) {

        this.context = context;
        updateList = new ArrayList<>();

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

        updateList.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed_update, parent, false);
        final UpdateViewHolder feedViewHolder = new UpdateViewHolder(view);

        return feedViewHolder;
    }

    @Override
    public void onClick(View view) {

//        final int viewId = view.getId();
//        final int nPos = (int)view.getTag();
//        final Organization organization = organizationList.get(nPos);
//        Intent i = new Intent(context, PurposeDetailActivity.class);
//        context.startActivity(i);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        UpdateViewHolder holder = (UpdateViewHolder) viewHolder;
        Update update = updateList.get(position);

        holder.tvUpdateeName.setText(update.details);
        holder.tvUpdateDate.setText(update.date);
        if(!update.image.isEmpty()) {
            imageLoader.displayImage(update.image, holder.ivUpdate, options);
        } else {
            holder.ivUpdate.setVisibility(ImageView.GONE);
        }

        holder.tvUpdateeName.setOnClickListener(this);
        holder.tvUpdateDate.setOnClickListener(this);
        holder.ivUpdate.setOnClickListener(this);

        holder.tvUpdateeName.setTag(position);
        holder.tvUpdateDate.setTag(position);
        holder.ivUpdate.setTag(position);

    }

    @Override
    public int getItemCount() {
        return updateList.size();
    }

    public static class UpdateViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_update)
        ImageView ivUpdate;
        @InjectView(R.id.tv_update_date)
        TextView tvUpdateDate;
        @InjectView(R.id.tv_update_name)
        TextView tvUpdateeName;

        public UpdateViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
