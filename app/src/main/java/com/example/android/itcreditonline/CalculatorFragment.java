package com.example.android.itcreditonline;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CalculatorFragment extends Fragment {
    private Activity activity;
    private boolean checkRadioBtn1=true;
    private boolean checkRadioBtn2=false;
    private boolean checkRadioBtn3=false;
    private boolean checkRadioBtn4=false;

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
        final TextView result = (TextView) row.findViewById(R.id.result_TV);
        final EditText credit1ET = (EditText) row.findViewById(R.id.credit_1_et);
        final EditText credit2ET = (EditText) row.findViewById(R.id.credit_2_et);
        final EditText credit3ET = (EditText) row.findViewById(R.id.credit_3_et);
        final Button cleanBtn = (Button) row.findViewById(R.id.button_clean);
        final Button calculateBtn = (Button) row.findViewById(R.id.button_calculate);

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
                        credit1ET.setText("");
                        credit2ET.setText("");
                        credit3ET.setText("");
                        credit1ET.requestFocus();
                        checkRadioBtn1=true;
                        checkRadioBtn2=false;
                        checkRadioBtn3=false;
                        checkRadioBtn4=false;
                        result.setText(" ");
                        break;


                    case R.id.button_max_amount:
                        credit1TV.setText("Optimum monthly payment: ");
                        credit2TV.setText("How many months can pay:");
                        credit3TV.setText("Annual interest rate:");
                        credit1ET.setText("");
                        credit2ET.setText("");
                        credit3ET.setText("");
                        credit1ET.requestFocus();
                        checkRadioBtn1=false;
                        checkRadioBtn2=true;
                        checkRadioBtn3=false;
                        checkRadioBtn4=false;
                        result.setText(" ");
                        break;

                    case R.id.button_credit_back:
                        credit1TV.setText("Loan amount: ");
                        credit2TV.setText("Amount of monthly installment:");
                        credit3TV.setText("Annual interest rate:");
                        credit1ET.setText("");
                        credit2ET.setText("");
                        credit3ET.setText("");
                        credit1ET.requestFocus();
                        checkRadioBtn1=false;
                        checkRadioBtn2=false;
                        checkRadioBtn3=true;
                        checkRadioBtn4=false;
                        result.setText(" ");
                        break;
                    case R.id.button_annual_interest:
                        credit1TV.setText("Loan amount: ");
                        credit2TV.setText("Term of the loan in months:");
                        credit3TV.setText("Amount of monthly installment:");
                        credit1ET.setText("");
                        credit2ET.setText("");
                        credit3ET.setText("");
                        credit1ET.requestFocus();
                        checkRadioBtn1=false;
                        checkRadioBtn2=false;
                        checkRadioBtn3=false;
                        checkRadioBtn4=true;
                        result.setText(" ");
                        break;


                }
            }
        });

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit1ET.setText("");
                credit2ET.setText("");
                credit3ET.setText("");
                result.setText("");
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int credit1 = 0;
                int credit2 = 0;
                int credit3 = 0;
                if (!isInteger(credit1ET.getText().toString().trim()) || !isInteger(credit2ET.getText().toString().trim()) || !isInteger(credit3ET.getText().toString().trim())) {
                    Toast.makeText(activity, "Please enter valid data.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(checkRadioBtn1) {
                    credit1 = Integer.parseInt(credit1ET.getText().toString());
                    credit2 = Integer.parseInt(credit2ET.getText().toString());
                    credit3 = Integer.parseInt(credit3ET.getText().toString());
                    result.setText("Monthly: " + credit2/credit1 +" month."+ "\n" +
                            "All paid sum:" + credit1 +" lv." +"\n"+ "Year procent: " + credit3 + "%");

                }else if(checkRadioBtn2){
                    credit1 = Integer.parseInt(credit1ET.getText().toString());
                    credit2 = Integer.parseInt(credit2ET.getText().toString());
                    credit3 = Integer.parseInt(credit3ET.getText().toString());
                    result.setText("Maximum amount of credit: " + credit2/credit1+"lv." + "\n" +
                            "All paid sum:" + credit1 +"lv."+"\n"+ "Year procent: " + credit3 + "%");

                }else if(checkRadioBtn3){
                    credit1 = Integer.parseInt(credit1ET.getText().toString());
                    credit2 = Integer.parseInt(credit2ET.getText().toString());
                    credit3 = Integer.parseInt(credit3ET.getText().toString());
                    result.setText("Credit will be expired: " + ".....");

                }else if(checkRadioBtn4){
                    credit1 = Integer.parseInt(credit1ET.getText().toString());
                    credit2 = Integer.parseInt(credit2ET.getText().toString());
                    credit3 = Integer.parseInt(credit3ET.getText().toString());
                    result.setText("Year procent: " + credit2/credit1 +"%"+ "\n" +
                            "All paid sum:" + credit1 +" lv." +"\n"+ "Year procent: " + credit3 + "%");

                }





            }
        });


        return row;
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }
}
