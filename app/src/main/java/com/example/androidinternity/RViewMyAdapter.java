package com.example.androidinternity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RViewMyAdapter extends RecyclerView.Adapter<RViewMyHolder> {

    Context context;
    ArrayList<RecyclerViewModel> models;

//    public RViewMyAdapter(Context context, ArrayList<RecyclerViewModel> models) {
//        this.context = context;
//        this.models = models;
//    }

    public RViewMyAdapter(ArrayList<RecyclerViewModel> list) {

        this.models=list;
    }

    @NonNull
    @Override
    public RViewMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row,null);

        return new RViewMyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewMyHolder holder, int position) {

        holder.name.setText(models.get(position).getName());
        holder.description.setText(models.get(position).getDesc());
        holder.image.setImageResource(models.get(position).getImage());
        holder.time.setText(models.get(position).getTime());
        holder.call.setImageResource(models.get(position).getCall());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
