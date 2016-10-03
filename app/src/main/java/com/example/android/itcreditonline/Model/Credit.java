package com.example.android.itcreditonline.Model;

import android.widget.TimePicker;

import java.util.Date;

/**
 * Created by Aydin on 29.9.2016 г..
 */

public class Credit {
    private int id;
    private int duration;
    private double amount;
    private String owner;

    public Credit(int id, int duration, double amount,String owner) {
        this.id = id;
        this.duration = duration;
        this.amount = amount;
        this.owner = owner;
    }

    public Credit(int duration, double amount, String owner) {
        this.duration = duration;
        this.amount = amount;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", duration=" + duration +
                ", amount=" + amount +
                ", owner='" + owner + '\'' +
                '}';
    }
}
