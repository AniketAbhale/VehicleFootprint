package com.example.vehiclefootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class getprevdata extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextInputLayout rno;
    TextView prev,newo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getprevdata);
        rno=findViewById(R.id.vehiclenu);
        prev=findViewById(R.id.ptv1);
        newo=findViewById(R.id.ntv2);


    }

    public void search(View view) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("rtoregistration");
        Query checkuser=reference.orderByChild("rgno").equalTo(rno.getEditText().getText().toString().trim());
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    prev.setText("Previous Owner: ");
                    newo.setText("New Owner: ");
                    String rgno = dataSnapshot.child(rno.getEditText().getText().toString().trim()).child("rgno").getValue().toString().trim();
                    String prevname = dataSnapshot.child(rgno).child("prevower").getValue().toString().trim();
                    String newname = dataSnapshot.child(rgno).child("newowner").getValue().toString().trim();
                    prev.append(prevname);
                    newo.append(newname);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void back(View view) {
        finish();
    }
}