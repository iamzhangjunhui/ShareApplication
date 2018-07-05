package com.example.kaylee.shareapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSelectActivity extends AppCompatActivity {
    // The path to the root of this app's internal storage
    private File mPrivateRootDir;
    // The path to the "images" subdirectory
    private File mImagesDir;
    // Array of files in the images subdirectory
    File[] mImageFiles;
    // Array of filenames corresponding to mImageFiles
    String[] mImageFilenames;

    // Initialize the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_select);
        // Set up an Intent to send back to apps that request a file
        Intent mResultIntent =
                new Intent("com.example.kaylee.shareapplication.ACTION_RETURN_FILE");
        // Get the files/ subdirectory of internal storage
        mPrivateRootDir = getFilesDir();
        // Get the files/images subdirectory;
        mImagesDir = new File(mPrivateRootDir, "images");
        if (mImagesDir.exists()||mImagesDir.mkdirs()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
            savaImage(mImagesDir.getAbsolutePath(), bitmap);
            // Get the files in the images subdirectory
            mImageFiles = mImagesDir.listFiles();
            // Set the Activity's result to null to begin with
            setResult(Activity.RESULT_CANCELED, null);
        }


        /*
         * Display the file names in the ListView mFileListView.
         * Back the ListView with the array mImageFilenames, which
         * you can create by iterating through mImageFiles and
         * calling File.getAbsolutePath() for each File
         */
    }

    public void savaImage(String filename, Bitmap bitmap) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            //bitmap转文件对象
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}