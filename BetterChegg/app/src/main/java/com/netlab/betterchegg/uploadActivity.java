package com.netlab.betterchegg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class uploadActivity extends AppCompatActivity{
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_FILE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //NAVIGATION BAR
        BottomNavigationView botnav = findViewById(R.id.bottomNavView);
        botnav.setSelectedItemId(R.id.upload);

        botnav.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home){
                startActivity(new Intent(getApplicationContext(), homeActivity.class));
                finish();
                return true;
            }
            if(item.getItemId() == R.id.upload){
                return true;
            }
            if(item.getItemId() == R.id.profile){
                startActivity(new Intent(getApplicationContext(), profileActivity.class));
                finish();
                return true;
            }
            return false;
        });

        //UPLOAD
        Button upload_file=findViewById(R.id.upload);
        upload_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission greater than equal to marshmeellow we used run time permission
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission()){
                        filePicker();
                    }
                    else{
                        requestPermission();
                    }
                }
                else{
                    filePicker();
                }
            }
        });
    }

    private void filePicker(){
        Toast.makeText(uploadActivity.this, "File Picker Called", Toast.LENGTH_SHORT).show();
        Intent openfile = new Intent(Intent.ACTION_PICK);
        openfile.setType("file/*");
        startActivityForResult(openfile, REQUEST_FILE);
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(uploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(uploadActivity.this, "Give Permission", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(uploadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(uploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(uploadActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(uploadActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
