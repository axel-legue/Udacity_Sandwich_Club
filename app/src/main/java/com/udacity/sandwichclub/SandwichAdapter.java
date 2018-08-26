package com.udacity.sandwichclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichHolder> {

    private List<Sandwich> sandwichList;
    private Context context;
    private SandwichListener mSandwichListener;

    public interface SandwichListener {
        void onSandwichClicked(Sandwich sandwich);
    }


    public SandwichAdapter(List<Sandwich> sandwichList, Context context, SandwichListener sandwichListener) {
        this.sandwichList = sandwichList;
        this.context = context;
        this.mSandwichListener = sandwichListener;
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
            sandwichHolder.mSandwichNameTextView.setText(sandwich.getMainName());
        }
        if (sandwich.getImage() != null && !TextUtils.isEmpty(sandwich.getImage())) {
            Picasso.with(context).load(sandwich.getImage()).placeholder(R.drawable.placeholder).error(R.drawable.android_error).into(sandwichHolder.mSandwichImageView);
        }
        sandwichHolder.mSandwichWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSandwichListener.onSandwichClicked(sandwich);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sandwichList.size();
    }

    class SandwichHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_sandwich_wrapper)
        RelativeLayout mSandwichWrapper;
        @BindView(R.id.iv_sandwich)
        ImageView mSandwichImageView;
        @BindView(R.id.tv_main_name)
        TextView mSandwichNameTextView;

        SandwichHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
