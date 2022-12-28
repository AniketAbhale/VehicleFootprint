package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class addprevdetails extends AppCompatActivity {
    TextInputLayout rno,prevname,newname;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprevdetails);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rno=findViewById(R.id.vehiclenu);
        prevname=findViewById(R.id.prevname);
        newname=findViewById(R.id.newownername);
    }

    public void submit(View view) {
        String rgno=rno.getEditText().getText().toString().trim();
        String prevower=prevname.getEditText().getText().toString().trim();
        String newowner=newname.getEditText().getText().toString().trim();
        System.out.println(rgno+prevower+newowner);

       // savedata();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("rtoregistration").child(rgno).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    rnoExist();

                } else {


                    // User does not exist. NOW call createUserWithEmailAndPassword


                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("rtoregistration");

                    Userhelperclass2 userhelperclass = new Userhelperclass2(rgno,prevower,newowner);
                    reference.child(rgno).setValue(userhelperclass);

                    done();

                    // Your previous code here.

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    private void rnoExist() {
        Toast.makeText(this," Registration NO. ALREADY PRESENT !!",Toast.LENGTH_LONG).show();

    }
    public void done(){
        Toast.makeText(this,"Details Added Successfully",Toast.LENGTH_LONG).show();
        finish();
    }

    private void savedata() {
        // dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, AuthoirityLogin.url + "addodata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(addprevdetails.this, "" + response, Toast.LENGTH_SHORT).show();
                        if (response.toLowerCase().equals("Data Registered Successfully".toLowerCase())) {
                            // dialog.dismiss();
                            Intent i = new Intent(addprevdetails.this, AuthoirityLogin.class);
                            startActivity(i);
                        }
                        response = null;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(addprevdetails.this, "ID ALREADY PRESENT", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("rno", rno.getEditText().getText().toString().trim());
                param.put("prevowner", prevname.getEditText().getText().toString().trim());
                param.put("newowner", newname.getEditText().getText().toString().trim());
                return param;

            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(addprevdetails.this);
        requestQueue.add(request);



    }





}