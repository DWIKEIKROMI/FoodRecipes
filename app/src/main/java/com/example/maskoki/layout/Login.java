package com.example.maskoki.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.maskoki.R;
import com.example.maskoki.api.JsonPlaceAPI;
import com.example.maskoki.databinding.ActivityLoginBinding;
import com.example.maskoki.models.LoginM;
import com.example.maskoki.util.SharedPref;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    Retrofit retrofit;
    JsonPlaceAPI jsonPlaceAPI;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sharedPref = new SharedPref(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://maskoki.dhanifudin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Call<ResponseBody> call;

        jsonPlaceAPI = retrofit.create(JsonPlaceAPI.class);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<LoginM> call = jsonPlaceAPI.Login(binding.usernameL.getText().toString(),
                        binding.passwordL.getText().toString());
                call.enqueue(new Callback<LoginM>() {
                    @Override
                    public void onResponse(Call<LoginM> call, Response<LoginM> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Login.this, "Selamat Datang", Toast.LENGTH_SHORT).show();
                            sharedPref.saveBoolean(SharedPref.SP_LOGIN,true);
                            sharedPref.saveString(SharedPref.SP_TOKEN,response.body().getToken());
                            sharedPref.saveString(SharedPref.SP_USERNAME,response.body().getUsername());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            intent.putExtra("username", binding.usernameL.getText().toString());
//                            intent.putExtra("token",response.body().getToken());
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginM> call, Throwable t) {

                    }
                });
            }
        });

        binding.btnRegisL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registrasi.class);
                startActivity(intent);
            }
        });
    }
}