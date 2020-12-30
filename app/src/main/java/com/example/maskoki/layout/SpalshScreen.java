package com.example.maskoki.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.maskoki.R;
import com.example.maskoki.util.SharedPref;

public class SpalshScreen extends AppCompatActivity {
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);
        setContentView(R.layout.activity_spalsh_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPref.getLogin()){
                    startActivity(new Intent(SpalshScreen.this,MainActivity.class));
                }else{
                    startActivity(new Intent(SpalshScreen.this,Login.class));
                }

            }
        },2000);
    }
}