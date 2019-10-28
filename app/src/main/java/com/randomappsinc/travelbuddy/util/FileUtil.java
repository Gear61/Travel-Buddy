package com.randomappsinc.travelbuddy.util;


import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.randomappsinc.travelbuddy.common.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

    @Nullable
    public static File createImageFile(Context context) {
        File imageFile;
        try {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            String imageFileName = "TRAVEL_BUDDY_" + timeStamp + "_";
            File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            imageFile = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir);
        } catch (IOException exception) {
            return null;
        }
        return imageFile;
    }

    public static void deleteCameraImageWithUri(@Nullable Uri uri) {
        if (uri == null) {
            return;
        }

        String uriString = uri.toString();
        if (TextUtils.isEmpty(uriString)) {
            return;
        }

        String filePath = uriString.substring(uriString.lastIndexOf('/'));
        String completePath = Environment.getExternalStorageDirectory().getPath()
                + Constants.FILE_PROVIDER_PATH
                + filePath;
        File imageFile = new File(completePath);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }
}
