package com.coding.collegeproject.socialx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coding.collegeproject.socialx.models.healine;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{
    private Context  context;
    private List<healine> headline;

    public CustomAdapter(Context context, List<healine> headline) {
        this.context = context;
        this.headline = headline;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.newscard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
       holder.titleofnews.setText(headline.get(position).getTitle());
       holder.sourceofnews.setText(headline.get(position).getSource().getName());
       if(headline.get(position).getUrlToImage()!=null){
           Picasso.get().load(headline.get(position).getUrlToImage()).into(holder.imageofnews);
       }
    }

    @Override
    public int getItemCount() {
        return headline.size();
    }
}
