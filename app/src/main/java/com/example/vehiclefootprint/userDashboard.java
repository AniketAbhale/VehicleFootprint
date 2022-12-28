package com.example.vehiclefootprint;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.logging.type.HttpRequest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class userDashboard extends AppCompatActivity {
    ProgressDialog dialog;
    FirebaseAuth auth;
    TextView user;
    String curruser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        auth = FirebaseAuth.getInstance();
         curruser=auth.getCurrentUser().getEmail();
        user=findViewById(R.id.user);
        user.setText("User ID: "+curruser);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");

    }

    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i =new Intent(userDashboard.this,userLogin.class);
        startActivity(i);
        finish();
    }


    public void next1(View view) {
        Intent i=new Intent(userDashboard.this,searchVehicle.class);
        startActivity(i);
    }

    public void searchcallan(View view) {
        dialog.show();
        Intent i=new Intent(userDashboard.this,searchCallan.class);
        startActivity(i);
        dialog.hide();
    }

    public void aboutus(View view) {
        Intent i=new Intent(userDashboard.this,aboutUs.class);
        startActivity(i);
    }

    public void submitfeedback(View view) {
        Intent i=new Intent(userDashboard.this,feedback.class);
        i.putExtra("uname",curruser);
        startActivity(i);
    }

    public void addcompain(View view) {
        Intent i=new Intent(userDashboard.this,addcomplain.class);
        startActivity(i);
    }
}
