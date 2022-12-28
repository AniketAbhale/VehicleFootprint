package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class rtoDashboard extends AppCompatActivity {
TextView u;
    ProgressDialog dialog;
    FirebaseAuth auth;
    TextView user;
    String curruser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rto_dashboard);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        u=findViewById(R.id.user);
        Intent intent = getIntent();
        String name = intent.getStringExtra("user");
        u.append(name);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
    }
    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i =new Intent(rtoDashboard.this,userLogin.class);
        startActivity(i);
        finish();
    }


    public void next1(View view) {
        Intent i=new Intent(rtoDashboard.this,searchVehicle.class);
        startActivity(i);
    }

    public void searchcallan(View view) {
        dialog.show();
        Intent i=new Intent(rtoDashboard.this,searchCallan.class);
        startActivity(i);
        dialog.hide();
    }

    public void aboutus(View view) {
        Intent i=new Intent(rtoDashboard.this,aboutUs.class);
        startActivity(i);
    }

    public void submitfeedback(View view) {
        Intent i=new Intent(rtoDashboard.this,feedback.class);
        i.putExtra("uname",curruser);
        startActivity(i);
    }

    public void addcompain(View view) {
        Intent i=new Intent(rtoDashboard.this,addcomplain.class);
        startActivity(i);
    }


    public void next0(View view) {
        Intent i=new Intent(rtoDashboard.this,addprevdetails.class);
        startActivity(i);
    }
}