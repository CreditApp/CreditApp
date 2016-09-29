package com.example.android.itcreditonline.Model;

import android.widget.TimePicker;

import java.util.Date;

/**
 * Created by Aydin on 29.9.2016 г..
 */

public class Credit {
    private int id;
    private TimePicker time;
    private Date duration;
    private double amount;

    public Credit(int id, TimePicker time,Date duration, double amount) {
        this.id = id;
        this.duration = duration;
        this.time = time;
        this.amount = amount;
    }
}
