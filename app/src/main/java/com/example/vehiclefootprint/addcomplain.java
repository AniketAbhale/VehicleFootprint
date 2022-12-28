package com.example.vehiclefootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addcomplain extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namepattern = "[a-zA-Z., ]+";
    TextInputLayout Email,xname;
    EditText disc;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcomplain);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Email=findViewById(R.id.email);
        xname=findViewById(R.id.name);
        disc=findViewById(R.id.ed1);
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
    public Boolean validatedisc(){
        String nameval = disc.getText().toString();
        if(nameval.isEmpty()){
            disc.setError("Field cannot be empty");
            return false;
        } else if(!nameval.matches(namepattern)){
            disc.setError("Invalid Format");
            return false;
        }
        else {
            disc.setError(null);
            return true;
        }

    }

    public void submit(View view) {

        if(!validatename() | !validateemail() |validatedisc() ){ return;}

        String dis=disc.getText().toString().trim();
        Date cc = Calendar.getInstance().getTime();
        String c=cc.toString();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(cc);
        String filleddate=formattedDate.toString().trim();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("complains");
        String name=xname.getEditText().getText().toString().trim();
        String email=Email.getEditText().getText().toString().trim();
        complainuserhelperclass userhelperclass = new complainuserhelperclass(name,email,dis,filleddate);
        reference.child(name).setValue(userhelperclass);
        done();

    }
    public void done(){
        Toast.makeText(this,"Complain Filled Successfully.......",Toast.LENGTH_LONG).show();
        finish();
    }
}