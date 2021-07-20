package com.example.basejavaandroid.customview;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.ImageSlideAdapter;
import com.example.basejavaandroid.base.network.BaseApplication;

import java.io.File;
import java.util.ArrayList;

public class ImageReviewDialog extends DialogFragment {
    ViewPager viewPager;
    ImageView ibClose;
    ImageView ibDownload;
    TextView tvPositionItem;
    ImageSlideAdapter adapter;
    ArrayList<String> listImage;

    int currentPos;

    public ImageReviewDialog(ArrayList<String> listImage, int currentPos) {
        this.listImage = listImage;
        this.currentPos = currentPos;
    }

    public void setListImage(ArrayList<String> listImage, int currentPos) {
        this.listImage = listImage;
        this.currentPos = currentPos;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogThemeFullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_review, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewpager);
        ibClose = view.findViewById(R.id.ibClose);
        ibDownload = view.findViewById(R.id.ibDownload);
        tvPositionItem = view.findViewById(R.id.tvPositionItem);
        // init list image
        adapter = new ImageSlideAdapter(getContext(), listImage);
        viewPager.setAdapter(adapter);
        if (currentPos >= 0 && listImage != null && listImage.size() > 0) {
            viewPager.setCurrentItem(currentPos);
            tvPositionItem.setText((currentPos + 1) + "/" + listImage.size());
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPositionItem.setText((position + 1) + "/" + listImage.size());
                currentPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ibClose.setOnClickListener(v -> dismiss());
        ibDownload.setOnClickListener(v -> {
            downloadFile(listImage.get(currentPos));
        });
    }

    public void downloadFile(String uRl) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/AnhsirkDasarp");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "idol.jpeg");

        mgr.enqueue(request);

        // Open Download Manager to view File progress
        Toast.makeText(getActivity(), "Downloaded...", Toast.LENGTH_LONG).show();
    }

    public void saveImageFromImageview(ImageView imageView) {
        // Get the image from drawable resource as drawable object
        Drawable drawable = imageView.getDrawable();
        // Get the bitmap from drawable object
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                /*
                    public static final String insertImage (ContentResolver cr, Bitmap source,
                    String title, String description)
                        Insert an image and create a thumbnail for it.
                    Parameters
                        cr : The content resolver to use
                        source : The stream to use for the image
                        title : The name of the image
                        description : The description of the image
                    Returns
                        The URL to the newly created image, or null if the image
                        failed to be stored for any reason.
                */

        // Save image to gallery
        String savedImageURL = MediaStore.Images.Media.insertImage(
                BaseApplication.getContext().getContentResolver(),
                bitmap,
                "Bird",
                "Image of bird"
        );

        // Parse the gallery image url to uri
//        Uri savedImageURI = Uri.parse(savedImageURL);

        // Display saved image url to TextView
        Toast.makeText(getActivity(), "Image saved to gallery :" + savedImageURL, Toast.LENGTH_SHORT).show();
    }

}
