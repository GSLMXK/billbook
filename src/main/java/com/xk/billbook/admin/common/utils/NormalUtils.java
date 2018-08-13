package com.xk.billbook.admin.common.utils;

public class NormalUtils {


    //去除最后一个特殊符号
    public String subLastSymbol(String str, String Symbol){
        int indx = str.lastIndexOf(Symbol);
        if(indx!=-1){
            str = str.substring(0,indx)+str.substring(indx+1,str.length());
        }
        return str;
    }
}
