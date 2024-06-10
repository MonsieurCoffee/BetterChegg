package com.netlab.betterchegg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class homeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int logoutButton = R.id.logout;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(logoutButton).setOnClickListener(this);

        BottomNavigationView botnav = findViewById(R.id.bottomNavView);
        botnav.setSelectedItemId(R.id.home);

        botnav.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home){
                return true;
            }
            if(item.getItemId() == R.id.upload){
                startActivity(new Intent(getApplicationContext(), uploadActivity.class));
                finish();
                return true;
            }
            if(item.getItemId() == R.id.profile){
                startActivity(new Intent(getApplicationContext(), profileActivity.class));
                finish();
                return true;
            }
            return false;
        });

    }

    private void logoutUser(){
        startActivity(new Intent(this, loginActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == logoutButton) {
            logoutUser();
        }
    }
}
