package com.example.androidinternity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RViewMyHolder extends RecyclerView.ViewHolder {

    ImageView image,call;
    TextView name,description,time;

    public RViewMyHolder(@NonNull View itemView) {
        super(itemView);
        this.image=itemView.findViewById(R.id.image);
        this.name=itemView.findViewById(R.id.name);
        this.description=itemView.findViewById(R.id.description);
        this.time=itemView.findViewById(R.id.time);
        this.call=itemView.findViewById(R.id.call);
    }
}
