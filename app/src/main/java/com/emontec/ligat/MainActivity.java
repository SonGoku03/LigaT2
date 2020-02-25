package com.emontec.ligat;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animation2,balon;
        @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            animation2 = (LottieAnimationView) findViewById(R.id.balon);
            animation2.setAnimation("dino.json");
            animation2.playAnimation();

            balon =(LottieAnimationView) findViewById(R.id.balon2);
            balon.setAnimation("balon2.json");
            balon.playAnimation();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent5 = new Intent(MainActivity.this, Inicio.class);
                    startActivity(intent5);
                    finish();
                }
            }, 2800);


    }

    }