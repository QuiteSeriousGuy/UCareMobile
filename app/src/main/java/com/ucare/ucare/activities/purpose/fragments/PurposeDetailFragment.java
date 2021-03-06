package com.ucare.ucare.activities.purpose.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.ucare.ucare.models.Purpose;
import com.ucare.ucare.utils.ImageDownloader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knight on 06/08/2016.
 */
public class PurposeDetailFragment extends Fragment {

    TextView tvDescription;
    TextView tvDate;
    TextView tvTitle;
    ImageView ivImage;

    Purpose purpose;
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public PurposeDetailFragment() {
    }

    public void addPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        imageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(getActivity())
                .imageDownloader(new ImageDownloader(getActivity())).build());


        View rootView = inflater.inflate(R.layout.fragment_purpose_detail, container, false);

        tvDescription = (TextView)rootView.findViewById(R.id.tvDescription);
        tvDate = (TextView)rootView.findViewById(R.id.tvDate);
        tvTitle = (TextView)rootView.findViewById(R.id.tvTitle);
        ivImage = (ImageView)rootView.findViewById(R.id.ivImage);

        tvTitle.setText(purpose.name);
        tvDate.setText(purpose.date);
        imageLoader.displayImage(purpose.image,ivImage, options);


        return rootView;
    }

}
