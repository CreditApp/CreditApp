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


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {
    Activity activity;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    interface Communicator{
        public void notifyFragmentAction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        final TextView sumTV = (TextView) root.findViewById(R.id.sum_text_view);
        final TextView timeTV = (TextView) root.findViewById(R.id.month_text_view);
        TextView totalSumRet = (TextView) root.findViewById(R.id.total_sum);
        SeekBar seekBarSum = (SeekBar) root.findViewById(R.id.seekBarSum);
        seekBarSum.incrementProgressBy(50);
        SeekBar seekBarMonth = (SeekBar) root.findViewById(R.id.seekBarMonth);
        Button apply = (Button) root.findViewById(R.id.apply_credit_button);
        seekBarSum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sumTV.setText(progress+100+"" + " lv.");
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
                timeTV.setText(progress+1 + "" + " month");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return root;
    }

}
