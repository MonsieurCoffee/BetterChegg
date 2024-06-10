package com.netlab.betterchegg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class profileActivity extends AppCompatActivity {
    private TextView username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView botnav = findViewById(R.id.bottomNavView);
        botnav.setSelectedItemId(R.id.profile);

        botnav.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home){
                startActivity(new Intent(getApplicationContext(), homeActivity.class));
                finish();
                return true;
            }
            if(item.getItemId() == R.id.upload){
                startActivity(new Intent(getApplicationContext(), uploadActivity.class));
                finish();
                return true;
            }

            if(item.getItemId() == R.id.upload){
                return true;
            }
            return false;
        });

    }
}
