package com.example.android.itcreditonline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.itcreditonline.Model.Credit;
import com.example.android.itcreditonline.Model.FeedItem;

import java.util.ArrayList;

/**
 * Created by Aydin on 3.10.2016 г..
 */

public class CreditsAdapter extends RecyclerView.Adapter<CreditsAdapter.CreditsVH> {
    ArrayList<Credit> credits;
    Context activity;

    public CreditsAdapter(Context activity,ArrayList<Credit> credits){
        this.credits = credits;
        this.activity = activity;

    }
    public class CreditsVH extends RecyclerView.ViewHolder{

        TextView id;
        TextView duration;
        TextView amount;
        public CreditsVH(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.credit_id);
            duration = (TextView) itemView.findViewById(R.id.credit_duration);
            amount = (TextView) itemView.findViewById(R.id.credit_amount);
        }
    }

    @Override
    public CreditsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_credits,parent,false);
        return new CreditsAdapter.CreditsVH(row);
    }

    @Override
    public void onBindViewHolder(CreditsVH holder, int position) {
        final Credit credit = credits.get(position);
        holder.id.setText("Credit: " +credit.getId()+"");
        holder.duration.setText("Duration: " + credit.getDuration()+" month.");
        holder.amount.setText("Amount: "+credit.getAmount()+" lv.");

    }

    @Override
    public int getItemCount() {
        return credits.size();
    }
}
