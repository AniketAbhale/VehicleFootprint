package com.example.vehiclefootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class userLogin extends AppCompatActivity {
    ProgressDialog dialog;
    TextInputLayout Email,Password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        String status="";


            if (auth.getCurrentUser() != null) {
                startActivity(new Intent(userLogin.this, userDashboard.class));
                finish();
            }



    }

    public Boolean validateemail(){
        String nameval = Email.getEditText().getText().toString();
        if(nameval.isEmpty()){
            Email.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(emailPattern)){
            Email.setError("Invalid Format");
            return false;
        }
        else {
            Email.setError(null);
            return true;
        }

    }
    public Boolean validatepassword() {
        String nameval = Password.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (nameval.length() < 6) {
            Password.setError("should be greater than 6 digits");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }

    public void next(View view) {
        Intent i= new Intent(userLogin.this,userSignup.class);
        startActivity(i);
    }

    public void submit(View view) {

    String email=Email.getEditText().getText().toString().trim();
    String password=Password.getEditText().getText().toString().trim();
        if(!validateemail() | !validatepassword()){ return; }
        dialog.show();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if(task.isSuccessful()) {
                    startActivity(new Intent(userLogin.this, userDashboard.class));
                    finish();
                } else {
                    Toast.makeText(userLogin.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void forget(View view) {
       Intent i=new Intent(userLogin.this,passwordChange.class);
       startActivity(i);

    }
}