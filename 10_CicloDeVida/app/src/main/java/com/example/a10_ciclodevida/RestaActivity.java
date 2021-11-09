package com.example.a10_ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class RestaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("RestarActivity", "onCreate()");
        setContentView(R.layout.activity_raiz);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("RestarActivity", "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("RestarActivity", "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RestarActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("RestarActivity", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("RestarActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("RestarActivity", "onDestroy()");
    }
}