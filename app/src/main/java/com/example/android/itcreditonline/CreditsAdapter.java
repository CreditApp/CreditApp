package com.example.android.itcreditonline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.itcreditonline.Model.Credit;

import java.util.ArrayList;

/**
 * Created by Aydin on 3.10.2016 г..
 */

class CreditsAdapter extends RecyclerView.Adapter<CreditsAdapter.CreditsVH> {
    private ArrayList<Credit> credits;
    private Context activity;

    CreditsAdapter(Context activity, ArrayList<Credit> credits) {
        this.credits = credits;
        this.activity = activity;

    }

    class CreditsVH extends RecyclerView.ViewHolder {

        TextView duration;
        TextView amount;

        CreditsVH(View itemView) {
            super(itemView);
            duration = (TextView) itemView.findViewById(R.id.credit_duration);
            amount = (TextView) itemView.findViewById(R.id.credit_amount);
        }
    }

    @Override
    public CreditsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(activity).inflate(R.layout.adapter_credits, parent, false);
        return new CreditsAdapter.CreditsVH(row);
    }

    @Override
    public void onBindViewHolder(CreditsVH holder, int position) {
        final Credit credit = credits.get(position);
        holder.duration.setText("Duration: " + credit.getDuration() + " month.");
        holder.amount.setText("Amount: " + credit.getAmount() + " lv.");

    }

    @Override
    public int getItemCount() {
        return credits.size();
    }
}
