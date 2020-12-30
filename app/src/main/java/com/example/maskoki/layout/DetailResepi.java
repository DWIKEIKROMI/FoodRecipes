package com.example.maskoki.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maskoki.AdapterKomen;
import com.example.maskoki.DeleteClick;
import com.example.maskoki.R;
import com.example.maskoki.api.JsonPlaceAPI;
import com.example.maskoki.databinding.ActivityDetailResepiBinding;
import com.example.maskoki.models.Komen;
import com.example.maskoki.models.Post;
import com.example.maskoki.util.SharedPref;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailResepi extends AppCompatActivity {
    TextView textViewResult;
    ActivityDetailResepiBinding binding;
    RecyclerView komen;
    AdapterKomen adapterKomen;
    JsonPlaceAPI jsonPlaceAPI;
    Post post;
    SharedPref sharedPref;
    public static String USERNAME = "";
    MutableLiveData<List<Komen>> ML = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resepi);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_resepi);
        sharedPref = new SharedPref(this);
        USERNAME = sharedPref.getUsername();
        post = getIntent().getExtras().getParcelable("post");
        binding.setMakanan(post);
        Glide.with(binding.imageView6.getContext()).load(post.getImage()).into(binding.imageView6);
        adapterKomen = new AdapterKomen(new DeleteClick() {
            @Override
            public void delete(Komen komen) {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPref.getToken();
                headers.put("Authorization", "Bearer "+token);
                final Call<ResponseBody> call = jsonPlaceAPI.hapuskomen(headers,komen.getId().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            return;
                        }
                        setLiveData();
                        tampilKomenML();
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                Toast.makeText(DetailResepi.this, "Komentar Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
            }
        }, USERNAME);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maskoki.dhanifudin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceAPI = retrofit.create(JsonPlaceAPI.class);
        setLiveData();
        tampilKomenML();

        binding.btnKomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment =  binding.inputKomen.getText().toString();
                Map<String, String> headers = new HashMap<>();
                String token = sharedPref.getToken();
                headers.put("Authorization", "Bearer "+token);
                final Call<ResponseBody> call = jsonPlaceAPI.inputkomen(headers,comment, post.getId().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            return;
                        }
                        binding.inputKomen.setText("");
                        setLiveData();
                        tampilKomenML();
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void setupRV(){
        komen = binding.komen;
        komen.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        komen.setAdapter(adapterKomen);
    }

    public void setLiveData(){
        final Call<List<Komen>> call = jsonPlaceAPI.getkomen(post.getId().toString());
        call.enqueue(new Callback<List<Komen>>() {
            @Override
            public void onResponse(Call<List<Komen>> call, Response<List<Komen>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code :"+ response.code());
                    return;
                }
                ML.setValue(response.body());
                setupRV();
            }
            @Override
            public void onFailure(Call<List<Komen>> call, Throwable t) {
            }
        });
    }

    public void tampilKomenML(){
        ML.observe(this, new Observer<List<Komen>>() {
            @Override
            public void onChanged(List<Komen> komen) {
                adapterKomen.setListMakanan(komen);
            }
        });
    }

}