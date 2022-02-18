package com.coding.collegeproject.socialx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Loginpage extends AppCompatActivity {

    TextView item1,item3;
    TextView item2;
    EditText item4,item5;
    ImageView facebookView;
    Dialog dialog;
    int found=0;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        item1=findViewById(R.id.Loginintobtn);
        item3=findViewById(R.id.registertextlgn);
        item4=findViewById(R.id.emaillogin);
        item5=findViewById(R.id.lgnpassword);
        item2=findViewById(R.id.towardssignup);
        item2.setOnClickListener(view -> openactivity1());
        item3.setOnClickListener(view-> openactivity1());
        facebookView=findViewById(R.id.fimageView);
        facebookView.setOnClickListener(view->{
            Toast.makeText(getApplicationContext(), "Error mentioned on github README, Please Check!!",Toast.LENGTH_LONG).show();
        });

        //login by mail

        item1.setOnClickListener(view -> {
            String em=item4.getText().toString();
            int i=em.indexOf('.');
            if(em.contains(".com")){
                String key=em.substring(0,i)+','+em.substring(i+1);
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if(snapshot.child(key).exists()) {
                                found=1;
                                String passString= Objects.requireNonNull(snapshot.child(key).child("password").getValue()).toString();//Non-null is important so that nll value does checked
                                if(passString.equals(item5.getText().toString())) {
                                    Intent intent = new Intent(Loginpage.this, MainActivity.class);
                                    intent.putExtra("email", key);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                if(found==0) {
                                    Toast.makeText(getApplicationContext(), "NOT FOUND", Toast.LENGTH_LONG).show();
                                }}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }else
            {item4.setError("Not Valid");
            }
        });

        //

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //sign by google
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        //
        signInButton.setOnClickListener(v -> {

            switch (v.getId()) {
                case R.id.sign_in_button:

                    signIn();
                    break;
                // ...
            }
        });
    }

    private void openactivity1() {
        Intent intent = new Intent(Loginpage.this,Signuppage.class);
        startActivity(intent);
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account!=null){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }


    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("failed", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}