package com.zthl.nxp.utils;

public class StringUtil {
    public static String trimString(String s)
    {
        if (s.indexOf("67A")!=-1){
            s=s.substring(2);
            s=s.substring(0,s.length()-1);
        }
        return s;
    }
}
