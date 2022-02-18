package com.coding.collegeproject.socialx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coding.collegeproject.socialx.models.healine;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
       holder.titleofnews.setText(headline.get(position).getTitle());
       holder.sourceofnews.setText(headline.get(position).getSource().getName());
       holder.hoursago.setText(headline.get(position).getPublishedAt()+" ");
       holder.description.setText(headline.get(position).getDescription());
       if(headline.get(position).getUrlToImage()!=null){
           Picasso.get().load(headline.get(position).getUrlToImage()).into(holder.imageofnews);
       }
    }

    @Override
    public int getItemCount() {
        return headline.size();
    }
}
