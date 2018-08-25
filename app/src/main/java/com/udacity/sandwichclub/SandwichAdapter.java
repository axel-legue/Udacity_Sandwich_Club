package com.udacity.sandwichclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichHolder> {

    private List<Sandwich> sandwichList;
    private Context context;


    public SandwichAdapter(List<Sandwich> sandwichList, Context context) {
        this.sandwichList = sandwichList;
        this.context = context;
    }

    @NonNull
    @Override
    public SandwichHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sandwich_card, parent, false);
        return new SandwichHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SandwichHolder sandwichHolder, int i) {
        final Sandwich sandwich = sandwichList.get(i);

        if (sandwich.getMainName() != null && !TextUtils.isEmpty(sandwich.getMainName())) {
            sandwichHolder.mSandiwchNameTextView.setText(sandwich.getMainName());
        }
        if (sandwich.getImage() != null && !TextUtils.isEmpty(sandwich.getImage())) {
            Picasso.with(context).load(sandwich.getImage()).placeholder(R.mipmap.ic_launcher).into(sandwichHolder.mSandwichImageView);
        }

    }

    @Override
    public int getItemCount() {
        return sandwichList.size();
    }

    class SandwichHolder extends RecyclerView.ViewHolder {

        ImageView mSandwichImageView;
        TextView mSandiwchNameTextView;

        SandwichHolder(@NonNull View itemView) {
            super(itemView);
            mSandiwchNameTextView = itemView.findViewById(R.id.tv_main_name);
            mSandwichImageView = itemView.findViewById(R.id.iv_sandwich);
        }
    }
}
