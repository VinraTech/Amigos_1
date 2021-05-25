package com.amigos.amigos.AdapterClass;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.amigos.amigos.ModelClass.DashClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Views.RequestPageActivity;

import java.util.List;

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.DescViewHolder> {
    Context context;
    List<DashClass> dashClasses;



    public DashAdapter(Context conthext,List<DashClass> dashClasses) {
        this.context = conthext;
        this.dashClasses = dashClasses;


    }

    @NonNull
    @Override
    public DescViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboardlayout, parent, false);
        return new DescViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DescViewHolder holder, final int position) {

        DashClass dashClass = dashClasses.get(position);
        holder.tvTitle.setText(dashClass.getServicecenter());
        holder.imgservice.setImageResource(dashClass.getImage());
        holder.tvDesc.setText(dashClass.getDescription());

        holder.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RequestPageActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashClasses.size();
    }

    public static class DescViewHolder extends RecyclerView.ViewHolder {

        TextView tvDesc,tvTitle;
        ImageView imgservice;
        AppCompatButton btnRequest;



        public DescViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgservice = itemView.findViewById(R.id.imgservice);
            btnRequest = itemView.findViewById(R.id.btnRequest);


        }
    }
}

