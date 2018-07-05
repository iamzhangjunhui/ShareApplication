package com.example.kaylee.shareapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

/**
 * 分享简单的数据
 */
public class MainActivity extends AppCompatActivity {
private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        String action=intent.getAction();
        String type=intent.getType();
        if (TextUtils.equals(action,Intent.ACTION_SEND)&&TextUtils.equals(type,"text/plain")){
            Toast.makeText(this, intent.getStringExtra(Intent.EXTRA_TEXT), Toast.LENGTH_SHORT).show();
        }else  if (TextUtils.equals(action,Intent.ACTION_SEND)&&type.contains("image")){
            Uri uri=intent.getParcelableExtra(Intent.EXTRA_STREAM);
            img=findViewById(R.id.img);
            img.setImageURI(uri);
        }
//        ArrayList<Uri> imageUris = new ArrayList<Uri>();
//        imageUris.add(getUriFromDrawableRes(R.drawable.ic_launcher)); // Add your image URIs here
//        imageUris.add(getUriFromDrawableRes(R.mipmap.ic_launcher_round));
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
//        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
//        shareIntent.setType("image/*");
//        startActivity(Intent.createChooser(shareIntent, "Share images to.."));
    }
    /**
     * 得到资源文件中图片的Uri
     * @param id 资源id
     * @return Uri
     */
    public Uri getUriFromDrawableRes(int id) {
        Resources resources = getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);
    }
}
