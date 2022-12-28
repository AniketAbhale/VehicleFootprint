package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthoirityLogin extends AppCompatActivity {
    ProgressDialog dialog;
    TextInputLayout Email,Password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static String url = "https://vehiclefootprint.000webhostapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authoirity_login); getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        String status="";


        if (auth.getCurrentUser() != null) {
         //   startActivity(new Intent(userLogin.this, userDashboard.class));
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
         Intent i= new Intent(AuthoirityLogin.this,Authoritysignup.class);
        startActivity(i);
    }


    public void submit(View view) {
        String email=Email.getEditText().getText().toString().trim();
        String password=Password.getEditText().getText().toString().trim();
        if(!validateemail() | !validatepassword()){ return; }

        find();

       // dialog.show();
    }

    private void find() {
        StringRequest request = new StringRequest(Request.Method.POST, url + "login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AuthoirityLogin.this, "" + response, Toast.LENGTH_SHORT).show();
                        if (response.equals("logged in successfully")) {
                            SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("myemail", Email.getEditText().getText().toString());
                            editor.apply();
                            takedata();
                        }
                        else{
                            Email.getEditText().setText("");
                            Email.getEditText().setError("Please check login Email ID");
                            Password.getEditText().setText("");
                            Password.getEditText().setError("Please check Password");
                        }
                        response = null;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AuthoirityLogin.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("username", Email.getEditText().getText().toString());
                param.put("password", Password.getEditText().getText().toString());
                return param;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(AuthoirityLogin.this);
        requestQueue.add(request);
    }

    private void takedata() {

        StringRequest request = new StringRequest(Request.Method.POST, url+"getmyinfo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray ja=new JSONArray(response);

                            JSONObject job= ja.getJSONObject(0);
                            SharedPreferences sharedPreferences1 = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                            editor1.putString("myname", job.getString("fname"));
                            editor1.putString("myauthority", job.getString("authority"));
                            editor1.apply();
                            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("firstStart", false);
                            editor.apply();
                            Email.getEditText().setText("");
                            Password.getEditText().setText("");
                            System.out.println(job.getString("authority"));
                            if(job.getString("authority").equals("RTO")) {
                                Intent i = new Intent(AuthoirityLogin.this, rtoDashboard.class);
                                i.putExtra("user",job.getString("fname"));
                                startActivity(i);
                            }

                            finish();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AuthoirityLogin.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param = new HashMap<String,String>();
                param.put("query","SELECT fname,authority FROM users WHERE email ='"+Email.getEditText().getText().toString()+ "';");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AuthoirityLogin.this);
        requestQueue.add(request);
    }

    public void forget(View view) {
        Intent i=new Intent(AuthoirityLogin.this,passwordChange.class);
        startActivity(i);

    }
}