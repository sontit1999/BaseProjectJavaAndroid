package com.example.basejavaandroid.base;

public class UtilsFunction {
    public static String ConvertFloatToString(float num){
        int phannguyen = (int)num;
        float phandu = num - phannguyen;
        if(phandu==0){
            return String.valueOf(phannguyen);
        }
        return String.valueOf(num);
    }
}
