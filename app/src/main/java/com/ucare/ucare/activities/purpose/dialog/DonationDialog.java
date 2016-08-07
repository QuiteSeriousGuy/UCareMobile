package com.ucare.ucare.activities.purpose.dialog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.ucare.ucare.R;
import com.ucare.ucare.models.Purpose;
import com.ucare.ucare.utils.ImageDownloader;

import java.util.HashMap;
import java.util.Map;

import me.drakeet.materialdialog.MaterialDialog;

public class DonationDialog extends DialogFragment {

    ImageView ivPurpose;

    TextView tvPurposeName;
    TextView tvPurposeDate;
    EditText etMessage;
    EditText etAmount;
    Switch swIsAnon;

    Button btnSend;

    Purpose purpose;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public void addPurpose(Purpose purpose){
        this.purpose = purpose;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

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

        imageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(getActivity())
                .imageDownloader(new ImageDownloader(getActivity())).build());

        View view = inflater.inflate(R.layout.dialog_donation, container,false);
        tvPurposeName = (TextView) view.findViewById(R.id.tv_purpose_name);
        tvPurposeDate = (TextView) view.findViewById(R.id.tv_purpose_date);
        ivPurpose = (ImageView) view.findViewById(R.id.iv_purpose);
        etMessage = (EditText) view.findViewById(R.id.et_message);
        etAmount = (EditText) view.findViewById(R.id.et_amount);
        btnSend = (Button) view.findViewById(R.id.send);
        swIsAnon = (Switch) view.findViewById(R.id.sw_is_anon);

        tvPurposeName.setText(purpose.name);
        tvPurposeDate.setText(purpose.date);
        imageLoader.displayImage(purpose.image, ivPurpose, options);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etAmount.getText().toString().isEmpty()) {
                    final MaterialDialog donateConfirmDialog = new MaterialDialog(getActivity());

                    String isAnon = swIsAnon.isChecked() ? "anonymously " : "";
                    donateConfirmDialog.setTitle("Confirm Donation")
                            .setMessage("Donate ₱" + etAmount.getText() + " " + isAnon + "to the " + purpose.name + " purpose?")
                            .setPositiveButton("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    donateConfirmDialog.dismiss();
                                    setEnabledAll(false);
                                    pringSequence();
                                }
                            })
                            .setNegativeButton("CANCEL", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    donateConfirmDialog.dismiss();
                                }
                            });

                    donateConfirmDialog.show();
                } else {
                    Toast.makeText(getActivity(), "Please put a valid amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void setEnabledAll(Boolean enable){
        this.setCancelable(enable);
        etMessage.setEnabled(enable);
        etAmount.setEnabled(enable);
        swIsAnon.setEnabled(enable);
        btnSend.setEnabled(enable);
        if(enable){
            btnSend.setText("Send");
        } else {
            btnSend.setText("Processing....");
        }
    }

    public void pringSequence(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                final MaterialDialog successConfirmDialog = new MaterialDialog(getActivity());
                successConfirmDialog.setTitle("Donation success!")
                        .setMessage("Thank you for you generosity!")
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                successConfirmDialog.dismiss();
                                DonationDialog.this.dismiss();
                            }
                        });
                successConfirmDialog.show();
            }
        }, 2000);
    }

}
