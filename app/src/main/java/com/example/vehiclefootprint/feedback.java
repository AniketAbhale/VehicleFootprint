package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class feedback extends AppCompatActivity {
    RatingBar ratingbar;
    Button b1;
    String namepattern = "[a-zA-Z ]+";
    TextInputLayout xname;
    String uname;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextInputLayout disc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        b1=findViewById(R.id.button);
        xname=findViewById(R.id.fed);
        Intent intent = getIntent();
         uname = intent.getStringExtra("uname");
         disc=findViewById(R.id.fed);


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

    public void submitrating(View view) {
        if(!validatename()  ){ return;}
        b1.setEnabled(false);
        String rating=String.valueOf(ratingbar.getRating());



        String dis=disc.getEditText().getText().toString().trim();
        Date cc = Calendar.getInstance().getTime();
        String c=cc.toString();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(cc);
        String filleddate=formattedDate.toString().trim();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("feedback");
        feedbackuserhelperclass userhelperclass = new feedbackuserhelperclass(uname,dis,filleddate,rating);
        reference.child(c).setValue(userhelperclass);
        done();

    }
    public void done(){
        Toast.makeText(this,"Feedback Filled Successfully.......",Toast.LENGTH_LONG).show();
        finish();
    }
}