package com.example.numreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText AmountTxt;
    private Button CalculateBtn;
    private TextView ValueView;
    private NumberConverter numberConverter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberConverter = new NumberConverter();

        AmountTxt = findViewById(R.id.amountText);
        CalculateBtn = findViewById(R.id.viewButton);
        ValueView = findViewById(R.id.resultView);

        CalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AmountTxt.getText().toString().isEmpty()){
                    String viewString = getReadAmount(AmountTxt.getText().toString());
                    ValueView.setText(viewString);
                }else {
                    ValueView.setText("Please Enter valid amount.");
                }
            }
        });
    }

    private String getReadAmount(String AmountStr){
        System.out.println("AmountStr:"+AmountStr);
        String array2[]= AmountStr.split("\\.");
        String RupeeAmountStr = "";
        String CentsAmountStr = "";
        if(array2.length == 2){
            RupeeAmountStr = array2[0];
            CentsAmountStr = array2[1];
        }else {
            RupeeAmountStr = AmountStr;
            CentsAmountStr = "00";
        }
        String RupeeInWords = numberConverter.convertRupees(RupeeAmountStr);
        String CentsInWords = numberConverter.convertCents(CentsAmountStr);
        if(CentsInWords.length()>0){
            return RupeeInWords+" and cents"+CentsInWords+" only.";
        }else {
            return RupeeInWords+" only.";
        }
    }
}
