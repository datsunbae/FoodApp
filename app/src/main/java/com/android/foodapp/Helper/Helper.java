package com.android.foodapp.Helper;

import java.text.DecimalFormat;

public class Helper {
    public Helper(){}

    public String FormatPrice(double price){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return String.valueOf(formatter.format(price));
    }
}
