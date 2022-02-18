package com.coding.collegeproject.socialx;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder{

    TextView titleofnews,sourceofnews;
    ImageView imageofnews;
    CardView cdview;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        titleofnews=itemView.findViewById(R.id.tilenews);
        sourceofnews=itemView.findViewById(R.id.sourcetext);
        imageofnews=itemView.findViewById(R.id.newsimg);
        cdview=itemView.findViewById(R.id.newscardview);
    }
}
