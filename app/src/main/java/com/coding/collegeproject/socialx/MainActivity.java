package com.coding.collegeproject.socialx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.coding.collegeproject.socialx.models.healine;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);

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