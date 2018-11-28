package com.android.supay.test.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.supay.test.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    int PERMISSION_ALL = 1;
    private static final int CAMERA_PICTURE = 2342;
    private static final int GALLERY_PICTURE = 78;

    String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonCamera) public void buttonCameraOnClick(View view){
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            getCameraImage();
        }
    }

    private void getCameraImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PICTURE);
    }

    @OnClick(R.id.buttonMedia) public void buttonMediaOnClick(View view){
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            getMediaImage();
        }
    }

    private void getMediaImage() {
        Intent photoPickerIntent = new Intent( Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_PICTURE);
    }

    public static boolean hasPermissions(Context context, String[] permissions){
        if(context != null && permissions != null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    public Uri outputFileUri;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //User Give Permissions
        }else{
         //User dont give permissions
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_PICTURE:
                if(resultCode == Activity.RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(photo);
                }else{

                }
                break;
            case GALLERY_PICTURE:
                if(resultCode == RESULT_OK){
                    if(data != null){
                        Uri pickedImage = data.getData();
                        //Let's read picked image path using content resolver
                        String[] filePath = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
                        cursor.moveToFirst();
                        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        Bitmap bitmap = BitmapFactory.decodeFile( imagePath, options);
                        Uri myUri = Uri.parse(imagePath);

                        try{
                            imageView.setImageBitmap(bitmap);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        }
    }

}
