package com.example.android.itcreditonline;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.itcreditonline.Model.Database.DBManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyFragment extends Fragment {
    Activity activity;
    private double total;

    public ApplyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_apply, container, false);
        final TextView sumTV = (TextView) root.findViewById(R.id.sum_text_view);
        final TextView timeTV = (TextView) root.findViewById(R.id.month_text_view);
        final TextView totalSumRet = (TextView) root.findViewById(R.id.total_sum);
        SeekBar seekBarSum = (SeekBar) root.findViewById(R.id.seekBarSum);
        SeekBar seekBarMonth = (SeekBar) root.findViewById(R.id.seekBarMonth);
        Button apply = (Button) root.findViewById(R.id.apply_credit_button);

        //TODO fix forumila
        seekBarSum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sumTV.setText(progress+100+"");
                total = progress+100;
                //totalSumRet.setText(total+ "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarMonth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeTV.setText(progress+1 + "" );
                total += progress*0.01;
                totalSumRet.setText(total+ "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = activity.getIntent().getStringExtra("loggedUser");
                DBManager.getInstance(activity).addCredit(Integer.parseInt(timeTV.getText().toString()),Integer.parseInt(sumTV.getText().toString()),username);
                Toast.makeText(activity, "Credit is added!", Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }

}
