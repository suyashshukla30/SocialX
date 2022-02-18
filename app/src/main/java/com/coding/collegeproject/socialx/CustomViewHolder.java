package com.coding.collegeproject.socialx;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder{

    TextView titleofnews,sourceofnews,hoursago,description;
    ImageView imageofnews;
    CardView cdview;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        titleofnews=itemView.findViewById(R.id.tilenews);
        sourceofnews=itemView.findViewById(R.id.sourcetext);
        imageofnews=itemView.findViewById(R.id.newsimg);
        hoursago=itemView.findViewById(R.id.publisheddate);
       description=itemView.findViewById(R.id.articledescription);
        cdview=itemView.findViewById(R.id.newscardview);
    }
}
