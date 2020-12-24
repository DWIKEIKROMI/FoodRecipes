package com.example.maskoki.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.example.maskoki.R;
import com.example.maskoki.api.JsonPlaceAPI;
import com.example.maskoki.databinding.ActivityDetailResepiBinding;
import com.example.maskoki.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailResepi extends AppCompatActivity {
    TextView textViewResult;
    ActivityDetailResepiBinding binding;
    Retrofit retrofit;
    JsonPlaceAPI jsonPlaceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resepi);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_resepi);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://maskoki.dhanifudin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Call<List<Post>> call;
        jsonPlaceAPI = retrofit.create(JsonPlaceAPI.class);

//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (!response.isSuccessful()){
//                    textViewResult.setText("Code :"+ response.code());
//                    return;
//                }
//                List<Post> posts = response.body();
//                for (Post post : posts){
//                    String content ="";
//                    content += "ID : "+post.getId() +"\n";
//                    content += "Title :"+post.getTitle() +"\n";
//                    content += "Description :"+post.getDescription() +"\n";
//                    content += "Image :"+post.getImage() +"\n";
//                    content += "Ingredients :"+post.getIngredients() +"\n";
//                    content += "instructions :"+post.getInstructions() +"\n\n";
//
//                    textViewResult.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
    }
}