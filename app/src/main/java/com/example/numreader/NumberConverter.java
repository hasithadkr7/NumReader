package com.example.numreader;

import java.util.*;

public class NumberConverter {
    //12,325,342.12
    //Twelve Million ,Three Hundred And Twenty Five Thousand ,Three Hundred And Forty Two, And Cents Twelve Only
    //412,325,342
    //Four Hundred And Twelve Million ,Three Hundred And Twenty Five Thousand ,Three Hundred And Forty Two Only
    //21,412,300,342
    //Twenty One Billion ,Four Hundred And Twelve Million ,Three Hundred Thousand ,Three Hundred And Forty Two Only
    //Billion,Million,Thousand,Rs
    private static final String[] amountNames = {
            "",
            " thousand",
            " million",
            " billion",
            " trillion"
    };

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private String convertSubAmount(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        if (soFar.length()>0) {
            return numNames[number] + " hundred and" + soFar;
        }else {
            return numNames[number] + " hundred" + soFar;
        }

    }

    public String convertCents(String CentsAmountStr){
        String CentsInWords = "";
        if(CentsAmountStr.length()==2){
            CentsInWords = convertSubAmount(Integer.parseInt(CentsAmountStr));
        }else {
            CentsInWords = "";
        }
        return CentsInWords;
    }

    public String convertRupees(String RsAmountStr){
        String RupeeInWords = "";
        ArrayList RupeeInWordsArray = new ArrayList();
        ArrayList Amounts = new ArrayList();
        if (RsAmountStr.length()>=3){
            for(int i=RsAmountStr.length();i>0;i-=3){
                String SubAmount = "";
                if(i>=3){
                    SubAmount = RsAmountStr.substring(i-3,i);
                }else{
                    SubAmount = RsAmountStr.substring(0,i);
                }
                Amounts.add(SubAmount);
            }
            for (int i=0;i<Amounts.size();i++){
                int SubAmount = Integer.parseInt(Amounts.get(i).toString());
                String SubAmountInWords = convertSubAmount(SubAmount)+amountNames[i];
                RupeeInWordsArray.add(SubAmountInWords);
            }

            for (int i=RupeeInWordsArray.size()-1;i>=0;i--){
                RupeeInWords = RupeeInWords + RupeeInWordsArray.get(i);
            }
        }else{
            RupeeInWords = convertSubAmount(Integer.parseInt(RsAmountStr));
        }
        return RupeeInWords;
    }
}
