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
//This the the adapter class where the list from class headline is invoke and in set text
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
       holder.titleofnews.setText(headline.get(position).getTitle());//title of news
       holder.sourceofnews.setText(headline.get(position).getSource().getName());//source of news
        String dateStr = headline.get(position).getPublishedAt();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");//format for changing to hour ago
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            long time = sdf.parse(dateStr).getTime();
            long now = System.currentTimeMillis();
            CharSequence ago =
                    DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            holder.hoursago.setText(ago+" ");
        } catch (ParseException e) {
            e.printStackTrace();
        }


       holder.description.setText(headline.get(position).getDescription());//description added
       if(headline.get(position).getUrlToImage()!=null){
           Picasso.get().load(headline.get(position).getUrlToImage()).into(holder.imageofnews);
       }
    }

    @Override
    public int getItemCount() {
        return headline.size();
    }
}
