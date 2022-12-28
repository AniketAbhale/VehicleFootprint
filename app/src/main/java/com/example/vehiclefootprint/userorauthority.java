package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class userorauthority extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userorauthority);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void user(View view) {
        Intent i=new Intent(userorauthority.this,userLogin.class);
        startActivity(i);
    }

    public void authority(View view) {
        Intent i=new Intent(userorauthority.this, AuthoirityLogin.class);
        startActivity(i);
    }
}