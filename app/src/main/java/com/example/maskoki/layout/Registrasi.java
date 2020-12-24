package com.example.maskoki.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.maskoki.R;
import com.example.maskoki.api.JsonPlaceAPI;
import com.example.maskoki.databinding.ActivityRegistrasiBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrasi extends AppCompatActivity {
    ActivityRegistrasiBinding binding;
    Retrofit retrofit;
    JsonPlaceAPI jsonPlaceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registrasi);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://maskoki.dhanifudin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Call<ResponseBody> call;
        jsonPlaceAPI = retrofit.create(JsonPlaceAPI.class);
        binding.btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = jsonPlaceAPI.Register(binding.username.getText().toString(),
                        binding.name.getText().toString(),
                        binding.password.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Registrasi.this, "Yee Kenek", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });


//        Intent intent = new ContactsContract.Intents()
    }
}