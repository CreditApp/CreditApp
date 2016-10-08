package com.example.android.itcreditonline;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class CalculatorFragment extends Fragment {
    private Activity activity;

    public CalculatorFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View row = inflater.inflate(R.layout.fragment_calculator, container, false);

        final TextView credit1TV = (TextView) row.findViewById(R.id.credit_1_tv);
        final TextView credit2TV = (TextView) row.findViewById(R.id.credit_2_tv);
        final TextView credit3TV = (TextView) row.findViewById(R.id.credit_3_tv);
        TextView result = (TextView) row.findViewById(R.id.result_end);
        EditText credit1ET = (EditText) row.findViewById(R.id.credit_1_et);
        EditText credit2ET = (EditText) row.findViewById(R.id.credit_2_et);
        final EditText credit3ET = (EditText) row.findViewById(R.id.credit_3_et);

        RadioGroup group = (RadioGroup) row.findViewById(R.id.radio_group);
        group.check(R.id.button_monthly_inst);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.button_monthly_inst:
                        credit1TV.setText("Loan amount: ");
                        credit2TV.setText("Term of the loan in months:");
                        credit3TV.setText("Annual interest rate:");
                        break;


                    case R.id.button_max_amount:
                        credit1TV.setText("Optimum monthly payment: ");
                        credit2TV.setText("How many months can pay:");
                        credit3TV.setText("Annual interest rate:");
                        break;

                    case R.id.button_credit_back:
                        credit1TV.setText("Loan amount: ");
                        credit2TV.setText("Amount of monthly installment:");
                        credit3TV.setText("Annual interest rate:");
                        break;
                    case R.id.button_annual_interest:
                        credit1TV.setText("Loan amount: ");
                        credit2TV.setText("Term of the loan in months:");
                        credit3TV.setText("Amount of monthly installment:");
                        break;


                }
            }
        });


        return row;
    }

}
