package com.netlab.betterchegg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.netlab.betterchegg.api.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int registerButton = R.id.registerBtn;
    private static final int backButton = R.id.backBtn;
    private EditText username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.registerUsername);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        findViewById(registerButton).setOnClickListener(this);
        findViewById(backButton).setOnClickListener(this);
    }

    private boolean validateFields(){
        String name = username.getText().toString().trim();
        String emailaddress = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (emailaddress.isEmpty()) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pass.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void registerUser(){
        if(!validateFields()) {
            return;
        }

        String name = username.getText().toString().trim();
        String emailaddress = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .createUser(name, emailaddress, pass);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) { // Check for successful response (2xx code)
                    try {
                        String body = response.body().string();
                        if (body == null){
                            Toast.makeText(registerActivity.this, "Field Empty", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(registerActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle unsuccessful response (e.g., display an error message)
                    Toast.makeText(registerActivity.this, "Registration failed: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(registerActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void back(){
        startActivity(new Intent(this, loginActivity.class));
    }

    @Override
    public void onClick(View view){
        if(view.getId() == registerButton) {
            registerUser();
        } else if (view.getId() == backButton) {
            back();
        }
    }
}
