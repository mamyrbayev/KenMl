package com.ereport.master.kenML.util;

import com.google.common.base.CharMatcher;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class StringUtil {
    public static String formatNumber(float num) throws ParseException {
        if(num != 0f){
            DecimalFormat decimalFormat = new DecimalFormat("0.0000000000");
            String str = decimalFormat.format(num);
            if(str.length() > 1){
                while (str.endsWith("0")) {
                    str = str.substring(0, str.length() - 1);
                }
            }
            int count = CharMatcher.is('0').countIn(str);
            String secondPart = str.substring(2, count + 2);
            String number = "0." + secondPart;
//            NumberFormat nf = NumberFormat.getInstance(Locale.US);
//            float f = nf.parse(finalPart).floatValue();
//            int c = count;
//            String format = "%."+c+"f";
//            String number = String.format(format, f);
//            number = number.replace(',', '.');
            if(number.length() > 1){
                while (number.endsWith("0")) {
                    number = number.substring(0, number.length() - 1);
                }
            }
            return number;
        }else{
            return "0";
        }
    }

    public static String formatNumberMillion(float num) throws ParseException {
        num = num/1000000;
        String number = String.format("%.1f", num);
        return number + " млн. ";
    }

    public static String formatNumberSpaces(float num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return rmComma(df.format(num));
    }

    public static String rmComma(String data){
        return data.replace(",", " ");
    }
}
