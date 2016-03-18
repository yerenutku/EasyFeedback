package com.erenutku.easyfeedback.easyfeedback;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by yeutku on 17/01/16.
 */
public class EasyFeedback {
    private int REQUEST_CAMERA = 1761, SELECT_FILE = 1762;
    //region Private variables
    private Activity mActivity;
    private Intent emailIntent;
    private Uri selectedImageUri;
    private OnImageSelectedListener onImageSelectedListener;
    private static EasyFeedback easyfeedback;
    private EasyFeedbackModels models;
    //endregion



    //region Public variables
    //endregion

    public static EasyFeedback getInstance(Activity activity){
        if (easyfeedback==null){
            easyfeedback = new EasyFeedback(activity);
        }
        return easyfeedback;
    }
    public void setSubject(String subject){
        models.setSubject(subject);
    }
    public void setMessage(String message){
        models.setUserMessage(message);
    }
    public void setDeveloperEmailList(String[] emailList){
        models.setDeveloperEmailList(emailList);
    }

    private EasyFeedback(Activity activity) {
        mActivity = activity;
        models = new EasyFeedbackModels();
        models.setSubject("New EasyFeedback of your app!");
        models.setUserMessage("This is your user's message!");
        models.setAndroidVersionRelease(Build.VERSION.RELEASE);
        models.setAndroidApiLevel(Build.VERSION.SDK_INT);
        models.setDeviceBrand(Build.BRAND);
        models.setDeviceModel(Build.MODEL);
        models.setDeviceProduct(Build.PRODUCT);
        models.setAppName(mActivity.getString(mActivity.getApplicationInfo().labelRes));
        models.setAppVersion(BuildConfig.VERSION_NAME);
        emailIntent = new Intent(Intent.ACTION_SENDTO);
        if (activity instanceof OnImageSelectedListener){
            Boolean isImplemented = true;
            onImageSelectedListener = (OnImageSelectedListener) activity;
        }
    }

    public void pickImageFromGallery() {
                        Intent photoIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        photoIntent.setType("image/*");
                        mActivity.startActivityForResult(
                                Intent.createChooser(photoIntent, mActivity.getString(R.string.feedback_image_picker_select_file)),
                                SELECT_FILE);
    }

    /**
     * Method must be called and filled with calling activity onActivityResult function.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == SELECT_FILE){
                selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(mActivity, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                emailIntent.setType("image/jpeg");
                emailIntent.putExtra(Intent.EXTRA_STREAM,selectedImageUri);
                Log.i("EasyFeedback", "Image selected and intent is ready to use!");
                models.setImage(bm);
                onImageSelectedListener.onImageSelected(bm);

            }
        }
    }

    public void openFilledEmailIntent(){

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , models.getDeveloperEmailList());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, models.getSubject());
        emailIntent.putExtra(Intent.EXTRA_TEXT, ""+
        models.getUserMessage()+"\n\n\n"+
        "Android Version: "+models.getAndroidVersionRelease()+"\n"+
        "Android Api Level: "+models.getAndroidApiLevel()+"\n"+
        "Device Brand: "+models.getDeviceBrand()+"\n"+
        "Device Model: "+models.getDeviceModel()+"\n"+
        "Device Product: "+models.getDeviceProduct()+"\n"+
        "Application Name: "+models.getAppName()+"\n"+
        "Application Version: "+models.getAppVersion()+"\n\n"+
        "This mail generated by EasyFeedback Library");
        if (selectedImageUri!=null)emailIntent.putExtra(Intent.EXTRA_STREAM,selectedImageUri);
        try {
            mActivity.startActivity(Intent.createChooser(emailIntent, "Choose a mail application"));
        } catch (android.content.ActivityNotFoundException ex) {
            Log.e(getClass().getName(),"createChooser failed: "+ex.getMessage());
        }
    }
}
