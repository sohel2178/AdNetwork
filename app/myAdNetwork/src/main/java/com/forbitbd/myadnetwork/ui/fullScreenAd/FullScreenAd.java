package com.forbitbd.myadnetwork.ui.fullScreenAd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.forbitbd.myadnetwork.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FullScreenAd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FullScreenAd extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private byte[] imageArr;

    private ImageView ivAdImage,ivClose;

    private CloseListener listener;

    public FullScreenAd() {
        // Required empty public constructor
    }

    public void setCloseListener(CloseListener listener){
        this.listener = listener;
    }

   
    // TODO: Rename and change types and number of parameters
    public static FullScreenAd newInstance(byte[] imageArr) {
        FullScreenAd fragment = new FullScreenAd();
        Bundle args = new Bundle();
        args.putByteArray(ARG_PARAM1, imageArr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_NoTitleBar_Fullscreen);
        if (getArguments() != null) {
            imageArr = getArguments().getByteArray(ARG_PARAM1);

        }
        
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_full_screen_ad, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ivAdImage = view.findViewById(R.id.ivAdImage);
        ivClose = view.findViewById(R.id.ivClose);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageArr , 0, imageArr .length);
        ivAdImage.setImageBitmap(bitmap);

        if(listener!=null){
            listener.onShowAd();
        }



        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(listener!=null){
                    listener.onCloseAd();
                }
            }
        });
    }
}