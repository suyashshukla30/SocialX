package com.coding.collegeproject.socialx;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class Signuppage extends AppCompatActivity {
TextView item1,item2,item3;
EditText item4 ,item5,item6,item7;
CheckBox cb;
String key="";
    CountryCodePicker item8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        item1=findViewById(R.id.towardslogin);
        item2=findViewById(R.id.textsignedin);
        item3=findViewById(R.id.registerbtn);
        cb=findViewById(R.id.checkBox);
        item1.setOnClickListener(view -> openactivity());
        item2.setOnClickListener(view -> openactivity());
        item4=findViewById(R.id.signupname);
        item5=findViewById(R.id.emailfromsignup);
        item6=findViewById(R.id.phonenumber);
        item7=findViewById(R.id.passwordfromsignup);
        item8=findViewById(R.id.ccp);

        //
        item3.setOnClickListener(view->{
            String email=item5.getText().toString();
            String passwordString=item7.getText().toString();
            if(email.contains(".com")) {
                int i = email.indexOf('.');
               key = email.substring(0, i) + ',' + email.substring(i + 1);
            }
            if(!email.isEmpty()&&!passwordString.isEmpty()) {




                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference(key);
                                    myRef.child("name").setValue(item4.getText().toString());
                                    myRef.child("password").setValue(item7.getText().toString());
                                    myRef.child("number").setValue(item8+""+item6.getText().toString());
                                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                                    Intent intent =new Intent(Signuppage.this,Loginpage.class);
                                    startActivity(intent);




            }
            else
            {
                item5.setError("Only .com domain allowed");
            }
        });
    }

    private void openactivity() {
        Intent intent=new Intent(Signuppage.this,Loginpage.class);
        startActivity(intent);
    }

}