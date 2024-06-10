package com.netlab.betterchegg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.netlab.betterchegg.api.RetrofitClient;
import com.netlab.betterchegg.models.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int registerId = R.id.registerText;
    private static final int loginId = R.id.loginButton;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(loginId).setOnClickListener(this);
        findViewById(registerId).setOnClickListener(this);

        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
    }

    private void userRegister(){
        startActivity(new Intent(this, registerActivity.class));
    }
    private void loginSuccess(){
        startActivity(new Intent(this, homeActivity.class));
    }
    private void userLogin(){
        String emailadd = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailadd) || TextUtils.isEmpty(pass)){
            Toast.makeText(loginActivity.this, "Field Empty", Toast.LENGTH_LONG).show();
        }
        else {
            Call<LoginResponse> call = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .userLogin(emailadd, pass);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        loginSuccess();
                    }
                    else {
                        Toast.makeText(loginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(loginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == registerId){
            userRegister();
        } else if (view.getId() == loginId) {
            userLogin();
        }
    }
}
