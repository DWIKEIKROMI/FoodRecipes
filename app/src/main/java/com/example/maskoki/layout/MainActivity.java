package com.example.maskoki.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.maskoki.Adapter;
import com.example.maskoki.ClickListener;
import com.example.maskoki.R;
import com.example.maskoki.api.JsonPlaceAPI;
import com.example.maskoki.databinding.ActivityMainBinding;
import com.example.maskoki.models.Post;
import com.example.maskoki.util.SharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    ActivityMainBinding binding;
    Retrofit retrofit;
    JsonPlaceAPI jsonPlaceAPI;
    private RecyclerView recyclerView;
    Adapter adapter;
    List<Post> posts;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        sharedPref = new SharedPref(this);
        binding.Tvname.setText("Hello, "+sharedPref.getUsername()+"!");
        adapter = new Adapter(new ClickListener() {
            @Override
            public void onClickListener(Post post) {
                Intent intent = new Intent(getApplicationContext(),DetailResepi.class);
                intent.putExtra("post", post);
                intent.putExtra("token",getIntent().getStringExtra("token"));
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        textViewResult = binding.textViewResult;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://maskoki.dhanifudin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceAPI = retrofit.create(JsonPlaceAPI.class);

        Call<List<Post>> call = jsonPlaceAPI.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code :"+ response.code());
                    return;
                }
                posts = response.body();
                for (Post post : posts){
                    String content ="";
                    content += "ID : "+post.getId() +"\n";
                    content += "Title :"+post.getTitle() +"\n";
                    content += "Description :"+post.getDescription() +"\n";
                    content += "Image :"+post.getImage() +"\n";
                    content += "Ingredients :"+post.getIngredients() +"\n";
                    content += "instructions :"+post.getInstructions() +"\n\n";
                    textViewResult.append(content);
                }
                adapter.setListMakanan(posts);
                setupRV();
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
    public void setupRV(){
        recyclerView = binding.rvResep;
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(adapter);
    }
}