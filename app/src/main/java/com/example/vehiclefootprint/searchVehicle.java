package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class searchVehicle extends AppCompatActivity {
TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,tv19;
    TextInputLayout v1;
    Button btn;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicle);
         getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv1=findViewById(R.id.tv1);
        tv2= findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        tv6=findViewById(R.id.tv6);
        tv7=findViewById(R.id.tv7);
        tv8=findViewById(R.id.tv8);
        tv9=findViewById(R.id.tv9);
        tv10= findViewById(R.id.tv10);
        tv11=findViewById(R.id.tv11);
        tv12=findViewById(R.id.tv12);
        tv13=findViewById(R.id.tv13);
        tv14=findViewById(R.id.tv14);
        tv15=findViewById(R.id.tv15);
        tv16=findViewById(R.id.tv16);
        tv17=findViewById(R.id.tv17);
        tv18=findViewById(R.id.tv18);
        tv19=findViewById(R.id.tv19);
        v1=findViewById(R.id.vehiclenu);
        btn=findViewById(R.id.btn);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Searching Please Wait...");

    }
    public void done(View view) {

        if(!validateno()){ return; }
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "https://vehiclefootprint.000webhostapp.com/getdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String model="";
                        String rno="";
                        String fupto="";
                        String vcolor="";
                        String vclass="";
                        String paddress="";
                        String rplace="";
                        String insuranceno="";
                        String ftype="";
                        String oname="";
                        String blacklisted="";
                        String manu="";
                        String eno="";
                        String cno="";
                        String inname="";
                        String vcat="";
                        String inval="";
                        String curradd="";
                        String ownerno="";
                        Toast.makeText(searchVehicle.this, "Done", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsonObject.toString();
                            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("result"));
                            jsonObject2.toString();
                            JSONObject jsonObject3 = new JSONObject(jsonObject2.getString("extraction_output"));
                            jsonObject3.toString();
                            model=jsonObject3.getString("manufacturer_model");
                            rno=jsonObject3.getString("registration_number");
                            fupto=jsonObject3.getString("fitness_upto");
                            vcolor=jsonObject3.getString("colour");
                            vclass=jsonObject3.getString("vehicle_class");
                            paddress=jsonObject3.getString("permanent_address");
                            rplace=jsonObject3.getString("registered_place");
                            insuranceno=jsonObject3.getString("insurance_policy_no");
                            ftype=jsonObject3.getString("fuel_type");
                            oname=jsonObject3.getString("owner_name");
                            blacklisted=jsonObject3.getString("blacklist_status");
                            manu=jsonObject3.getString("manufacturer");
                            eno=jsonObject3.getString("engine_number");
                            cno=jsonObject3.getString("chassis_number");
                            inname=jsonObject3.getString("insurance_name");
                            vcat=jsonObject3.getString("vehicle_category");
                            inval=jsonObject3.getString("insurance_validity");
                            curradd=jsonObject3.getString("current_address");
                            ownerno=jsonObject3.getString("owner_serial_number");
                            if(ownerno.equals("1")){
                                ownerno="First Owner";
                            }else if(ownerno.equals("2")){
                                ownerno="Second Owner";
                            }else if(ownerno.equals("3")){
                                ownerno="Third Owner";
                            }else if(ownerno.equals("4")){
                                ownerno="Fourth Owner";
                            }
                            else{
                                ownerno="more than 4 owners";
                            }
                            System.out.println(jsonObject3);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                        System.out.println(response);
                        tv1.append(rno);
                        tv2.append(manu);
                        tv3.append(model);
                        tv4.append(vclass);
                        tv5.append(vcolor);
                        tv6.append(vcat);
                        tv7.append(ftype);
                        tv8.append(oname);
                        tv9.append(paddress);
                        tv10.append(curradd);
                        tv11.append(rplace);
                        tv12.append(fupto);
                        tv13.append(cno);
                        tv14.append(eno);
                        tv15.append(insuranceno);
                        tv16.append(inname);
                        tv17.append(inval);
                        if(blacklisted.equals("null")) {
                            tv18.append("Not Blacklisted");
                            tv18.setTextColor(Color.parseColor("#00FF00"));
                        }
                        else{
                            tv18.append(blacklisted);
                        }
                        tv19.append(ownerno);
                        v1.setEnabled(false);
                        btn.setEnabled(false);

                        response = null;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(searchVehicle.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("vehicleno", v1.getEditText().getText().toString().toUpperCase(Locale.ROOT).trim());
                return param;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(searchVehicle.this);
        requestQueue.add(request);
    }

    private boolean validateno() {
        String nameval = v1.getEditText().getText().toString();
        if (nameval.isEmpty()) {
            v1.setError("Field cannot be empty");
            return false;
        } else if (nameval.length() > 11) {
            v1.setError("should be less than 11 digits");
            return false;
        } else {
            v1.setError(null);
            return true;
        }

    }

    public void back(View view) {
        finish();
    }

    public void previousowner(View view) {
        Intent i=new Intent(searchVehicle.this,getprevdata.class);
        startActivity(i);
    }
}