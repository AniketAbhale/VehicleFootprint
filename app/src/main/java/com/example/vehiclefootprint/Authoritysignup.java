package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Authoritysignup extends AppCompatActivity {
    TextInputLayout Email,Password,xname,xphone,xuid;
    Spinner xspin;
    ProgressDialog dialog;
    String ids[]={ "Select Autority Type",
        "RTO",
        "BANK",
        "INSURANCE",
        "MECHANIC",
    "TRAFFIC AUTHORITY",
        "DEALER"};
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    String uid = "[A-Z0-9]{12}";
    String namepattern = "[a-zA-Z ]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authoritysignup);
         getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        xname=findViewById(R.id.name);
        xphone=findViewById(R.id.phone);
        xuid=findViewById(R.id.uid);
        xspin=findViewById(R.id.spinner);

    }
    public void go(View v){
        if(!validatename() | !validateemail() | !validatepassword() | !validatephone() | !validateroom()| !validatetype()){ return; }
        savedata();
    }

    private boolean validatetype() {
        String nameval =  ids[xspin.getSelectedItemPosition()];
        if(nameval.equals("Select Autority Type")){
            ((TextView)xspin.getSelectedView()).setError("Select Valid Authority");
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean validatename(){
        String nameval = xname.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xname.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(namepattern)){
            xname.setError("Invalid Format");
            return false;
        }
        else {
            xname.setError(null);
            return true;
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
    public Boolean validatephone(){
        String nameval = xphone.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xphone.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(MobilePattern)){
            xphone.setError("Invalid Format");
            return false;
        }
        else {
            xphone.setError(null);
            return true;
        }

    }
    public Boolean validateroom(){
        String nameval = xuid.getEditText().getText().toString();
        if(nameval.isEmpty()){
            xuid.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(uid)){
            xuid.setError("Invalid Format");
            return false;
        }
        else {
            xuid.setError(null);
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

    private void savedata() {
       // dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, AuthoirityLogin.url + "register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Authoritysignup.this, "" + response, Toast.LENGTH_SHORT).show();
                        if (response.toLowerCase().equals("User Registered Successfully".toLowerCase())) {
                           // dialog.dismiss();
                            Intent i = new Intent(Authoritysignup.this, AuthoirityLogin.class);
                            startActivity(i);
                        }
                        response = null;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Authoritysignup.this, "ID ALREADY PRESENT", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("email", Email.getEditText().getText().toString().trim());
                param.put("fname", xname.getEditText().getText().toString().trim());
                param.put("phno", xphone.getEditText().getText().toString().trim());
                param.put("uid", xuid.getEditText().getText().toString().trim());
                param.put("password", Password.getEditText().getText().toString().trim());
                param.put("authority", ids[xspin.getSelectedItemPosition()]);
                return param;

            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(Authoritysignup.this);
        requestQueue.add(request);



    }
}