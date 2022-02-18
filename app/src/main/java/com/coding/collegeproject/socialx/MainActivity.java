package com.coding.collegeproject.socialx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.coding.collegeproject.socialx.models.healine;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    SearchView searchView;
    GoogleSignInClient mGoogleSignInClient;
    TextView lgtbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lgtbtn=findViewById(R.id.logoutbtn);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        
        lgtbtn.setOnClickListener(view->{
            dialog=new ProgressDialog(this);
            dialog.setTitle("Logging Out...");
            dialog.show();
            signout();
        });
        
        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);

        searchView =findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener,null,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void signout() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    Intent intent = new Intent(MainActivity.this,Loginpage.class);
                    startActivity(intent);
                });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener(){

        @Override
        public void onError(String message) {

        }

        @Override
        public void onFetchData(List<healine> list, String message) {
            showNews(list);
            dialog.dismiss();
        }
    };

    private void showNews(List<healine> list) {
        recyclerView=findViewById(R.id.newview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter=new CustomAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

}